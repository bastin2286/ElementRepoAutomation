package dbInsertJson;

import java.io.IOException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import tcsLib.dbUtils;


public class DbInsert {
	  
	public static void main(String[] args) throws Exception
	{
		
				try 
				{
					dbInsert(args[0],args[1],args[2],args[3],args[4]); 
					System.out.println("The Json document is inserted into database successfully!!");
				}
				catch (IOException e) 
				{
			         
			         e.printStackTrace();
			    }

	}
	
	public static void dbInsert(String db_server,String db_port,String db_name,String db_className,String json_path) throws Exception 
	{
		int port = Integer.parseInt(db_port);
		DB db = dbUtils.dbGetDatabase(db_server,port, db_name);
		DBCollection collection = db.getCollection(db_className);
		dbUtils.InsertJsonData(collection, json_path);
		 
	}
	
}