package Utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class UBean
{
	public static ArrayList<Field> obtenerAtributos(Object o)
	{
		Class clazz = o.getClass();
		Field[] a = clazz.getDeclaredFields();
		ArrayList<Field> b = new ArrayList<Field>();
		//System.out.println(clazz.getDeclaredFields().toString());
		for(int i=0;i<a.length;i++)
		{
			b.add(a[i]);
			//System.out.println(a[i]);
		}
		
		return b;
		//return a;
		
	}
	
	public static void ejecutarSet(Object o,String att,Object valor) 
	{
		Class clazz = o.getClass();
		try {
			Field a = clazz.getDeclaredField(att);
			
			String convirtiendo = a.getName().substring(0, 1).toUpperCase() + a.getName().substring(1);
			
			//System.out.println(a.getName());
			String prueba = "set" + convirtiendo;
			//System.out.println(prueba);
			Method m = clazz.getMethod(prueba,valor.getClass());
			m.invoke(o, valor);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(valor);
		
		
		
	}
	
	public static Object ejecutarGet(Object o,String att)
	{
		
		Class clazz = o.getClass();
		Method m;
		try {
			
			Field a = clazz.getDeclaredField(att);
			String convirtiendo = a.getName().substring(0, 1).toUpperCase() + a.getName().substring(1);
			
			String prueba = "get"+convirtiendo;
			
			m = clazz.getDeclaredMethod(prueba);
			
			//System.out.println(prueba);
			
			Object valor = m.invoke(o);
			return valor;
			//System.out.println(valor.getClass());
			
			
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	

}
