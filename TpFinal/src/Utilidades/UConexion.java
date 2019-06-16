package Utilidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class UConexion
{
	//public static UConexion uconexion = null;
	public static Connection conexion = null;
	
	private UConexion()
	{
		
	}
	
	//public static  UConexion getConexion()
	public static Connection getConexion()
	{
		if(conexion==null)
		{
			
			//Connection conexion = null;
			Properties properties = new Properties();
		    InputStream input = null;
		
			
			try
			{
				//properties.load(Thread.currentThread().getContextClassLoader()
					//	.getResourceAsStream("config.properties"));
				
				input = new FileInputStream("framework.properties");

		        // load a properties file
		        properties.load(input);
				
				String driver = properties.getProperty("key.driver");
				String url = properties.getProperty("key.url");
				String user = properties.getProperty("key.username");
				String password = properties.getProperty("key.password");
				
				Class.forName(driver);
				conexion = DriverManager.getConnection(url,user,password);
			}
			catch(Exception e)
			{
				System.out.println("Error en getConexion: " + e.getMessage());
			}
			
			
			
			
		}
		
		
		
		return conexion;
		
	}
	

}
