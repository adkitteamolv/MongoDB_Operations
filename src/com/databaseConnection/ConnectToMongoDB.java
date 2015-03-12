package com.databaseConnection;

import org.bson.BasicBSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ConnectToMongoDB {
	private static final Object TRUE = null;
	static MongoClient client;
	
	public static void main(String[] args) {
		
		
		
		try{
			
			//Creating Mongo client
			client=new MongoClient();
		
		
			//Connecting/Creating to database "test" 
			DB db=client.getDB("test");
		
			System.out.println("Connect to test database"+db);
		
			//Setting database test with options
			DBObject option=BasicDBObjectBuilder.start().add("capped", TRUE)
													.add("autoIndexID","true")
													.get();
			
			//Creating New Collection "MyCollection"
			DBCollection collection=db.createCollection("MyCollection",option);
			System.out.println("Collection created :"+collection);
		
			//Switch to created collection
			collection=db.getCollection("MyCollection");
			System.out.println("Mycollection selected : "+collection);
		
			//Insert Document in collection
				    
		    DBObject[] doc={BasicDBObjectBuilder.start()
		    								 .add("name", "MongoDB database tutorial")
		    								 .add("title", "mongodb")
		    								 .add("description", "database")
		                 					 .add("likes", "100")
		                 					 .add("url", "http://www.tutorialspoint.com/mongodb/")
		                 					 .add("by", "tutorials point")
		                 					 .get()};
		    //Inserting document to collection 
		    collection.insert(doc);
		    
		    //creating nested documents
		    BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()  
		    		  .add("database", "javaDB")  
		    		  .add("table", "Mongo description");  
		    		   
		    
		    DBObject[] doc1={BasicDBObjectBuilder.start()
					 .add("name", "No SQL DB")
					 .add("title", "Mongo DB")
					 .add("description", "No SQL database")
					 .add("likes", "50")
					 .add("url", "http://www.tutorialspoint.com/mongodb/")
					 .add("by", "tutorials point")
					 .add("database", documentBuilder.get()).get()};

		    //Inserting document to collection		   
		    collection.insert(doc1);
		
		    System.out.println("Document inserted successfully");
		    
		    //finding particular document with where condition from collection
		   BasicDBObject where = new BasicDBObject();
		   where.put("title", "Mongo DB");
		    
		    DBCursor cursor = collection.find(where);
	         int i=1;
	         while (cursor.hasNext()) { 
	            System.out.println("Inserted Document: "+i); 
	            System.out.println(cursor.next()); 
	            i++;
	         }
		    
		    //updating inserted document
	         BasicDBObject updatedoc= new BasicDBObject();
	         updatedoc.append("likes", "50");
	         	         
	         BasicDBObject docupd = new BasicDBObject();
		     docupd.append("$set", new BasicDBObject().append("likes", "70"));
		     
		     collection.update(updatedoc, docupd);
		     
		     BasicDBObject whereupdated=new BasicDBObject();
		     whereupdated.put("likes", "70");
		     
		     DBCursor cursor1 = collection.find(whereupdated);
	         int j=1;
	         while (cursor1.hasNext()) { 
	            System.out.println("updated Document: "+j); 
	            System.out.println(cursor1.next()); 
	            j++;
	         }
		         
	         collection.drop();
			System.out.println("Collection Dropped sucessfully");
	         
		
		}catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		
	
		
		client.close();
		 
		
		
		
		

	}

}
