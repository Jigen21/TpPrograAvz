package Servicios;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import Anotaciones.Columna;
import Anotaciones.Id;
import Anotaciones.Tabla;
import Utilidades.UBean;
import Utilidades.UConexion;

public class Consultas
{
	public static void Guardar(Object o)
	{
		
		//UConexion connTraida = UConexion.getConexion();
		Connection conn = UConexion.getConexion();
		
		ArrayList<Columna> columnas = new ArrayList<Columna>();
		Field IdTraido;
		
		ArrayList<Field> traidos = UBean.obtenerAtributos(o);
		
		for(Field f : traidos)
		{
			if(f.getAnnotation(Columna.class) != null)
			{
				columnas.add(f.getAnnotation(Columna.class));
			}
			
			if(f.getAnnotation(Id.class) != null)
			{
				IdTraido=f;
			}
			
		}
		
		Class clazz = o.getClass();
		Tabla tabla = (Tabla) clazz.getAnnotation(Tabla.class);
		//System.out.println(tabla);
		//System.out.println(columnas.get(0).nombre());
		Object valorUno = UBean.ejecutarGet(o,columnas.get(0).nombre());
		//System.out.println(valor.toString());
		Object valorDos = UBean.ejecutarGet(o, columnas.get(1).nombre());
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO "+ tabla.nombre() + " (");
		for(Columna c : columnas)
		{
			sb.append(c.nombre() + ",");
		}
		
		//sb.append(") VALUES (");
		sb.replace(sb.length()-1,sb.length(),") VALUES (");
		
		for(Field f : traidos)
		{
			if(f.getAnnotation(Columna.class) != null)
			{
				Object valor = UBean.ejecutarGet(o,f.getName());
				if(valor.getClass().equals(String.class))
				{
					sb.append("'").append(valor.toString()).append("'").append(",");
				}
				else
				{
					sb.append(valor).append(",");
				}
			}
			
			
		}
		
		sb.replace(sb.length()-1, sb.length(),")");
		
		System.out.println(sb.toString());
		
		
		PreparedStatement ps;
		try {
			//ps = conn.prepareStatement("INSERT INTO personas (nombre,apellido) VALUES('jeje','jojo')");
			//ps = conn.prepareStatement(
			//		"INSERT INTO personas ("+columnas.get(0).nombre()+","+columnas.get(1).nombre()+")"
			//		+ " VALUES('"+ valorUno.toString() +"','"+valorDos.toString()+"')");
			ps = conn.prepareStatement(sb.toString());
			ps.execute();
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	public static void Modificar(Object o)
	{
		
		Connection conn = UConexion.getConexion();
		
		ArrayList<Columna> columnas = new ArrayList<Columna>();
		Field IdTraido = null;
		
		ArrayList<Field> traidos = UBean.obtenerAtributos(o);
		
		for(Field f : traidos)
		{
			if(f.getAnnotation(Columna.class) != null)
			{
				columnas.add(f.getAnnotation(Columna.class));
			}
			
			if(f.getAnnotation(Id.class) != null)
			{
				IdTraido=f;
			}
			
		}
		
		Class clazz = o.getClass();
		Tabla tabla = (Tabla) clazz.getAnnotation(Tabla.class);
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "+ tabla.nombre() + " SET ");
		
		for(Field f : traidos)
		{
			if(f.getAnnotation(Columna.class)!= null)
			{
				sb.append(f.getAnnotation(Columna.class).nombre()).append(" = ").append("'")
				.append(UBean.ejecutarGet(o, f.getName())).append("'").append(",");
			}
		}
		
		sb.replace(sb.length()-1, sb.length(),"");
		
		sb.append(" WHERE " + IdTraido.getName()).append(" = ")
		.append(UBean.ejecutarGet(o,IdTraido.getName()));
		
		System.out.println(sb.toString());
		
		try {
			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	public static void Eliminar(Object o)
	{
		Class clazz = o.getClass();
		
		Connection conn = UConexion.getConexion();
		
		Tabla tabla = (Tabla) clazz.getAnnotation(Tabla.class);
		
		ArrayList<Field> traidos = UBean.obtenerAtributos(o);
		ArrayList<Columna> columnas = new ArrayList<Columna>();
		Field IdTraido = null;
		
		for(Field f : traidos)
		{
			if(f.getAnnotation(Columna.class) != null)
			{
				columnas.add(f.getAnnotation(Columna.class));
			}
			else if(f.getAnnotation(Id.class) != null)
			{
				IdTraido = f;
			}
			
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tabla.nombre() +
					" WHERE " + IdTraido.getName() + " = " + UBean.ejecutarGet(o,IdTraido.getName()));
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static Object obtenerPorId(Class c,Object id)
	{
		System.out.println(id);
		Connection conn = UConexion.getConexion();
		
		
		
		//ArrayList<Field> traidos = UBean.obtenerAtributos(c);
		
		ArrayList<Columna> columnas = new ArrayList<Columna>();
		Field IdTraido = null;
		Tabla tabla = (Tabla) c.getAnnotation(Tabla.class);
		System.out.println(tabla.nombre());
		
		//Class clazz = id.getClass();
		
		Object o = null;
		
		Constructor[] constructors = c.getConstructors();
		
		for(Constructor con : constructors)
		{
			if(con.getParameterCount()==0)
			{
				try {
					o = con.newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		ArrayList<Field> traidos = UBean.obtenerAtributos(o);
		
		for(Field f : traidos)
		{
			System.out.println(f);
			if(f.getAnnotation(Columna.class) != null)
			{
				columnas.add(f.getAnnotation(Columna.class));
				//System.out.println(f.getName());
			}
			else if (f.getAnnotation(Id.class) != null)
			{
				IdTraido = f;
			}
		}
		
		
		
		
		//System.out.println(columnas.get(0).toString());
		//System.out.println(IdTraido.getName());
		String prueba = "SELECT FROM " + tabla.nombre() +" WHERE " + IdTraido.getName() + " = " + id;
		
		try {
			PreparedStatement ps = conn.prepareStatement
					("SELECT * FROM " + tabla.nombre() +
							" WHERE " + IdTraido.getName() + " = " + id);
			
			System.out.println(prueba);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next())
			{
				for(Field f : traidos)
				{
					if(f.getAnnotation(Columna.class) != null)
					{
						UBean.ejecutarSet(o,f.getName(), rs.getObject(f.getAnnotation(Columna.class).nombre()));
					}
				}
			}
			
			UBean.ejecutarSet(o,IdTraido.getName(),id);
			System.out.println(o);
			
			return o;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
		
		
	}
	
	public static void prueba()
	{
		//UConexion conn = UConexion.getConexion();
		System.out.println("asdads");
	}
	

}
