package Prueba;

import java.sql.SQLException;

import Servicios.Consultas;
import Utilidades.UBean;

public class Main {

	public static void main(String[] args) throws SQLException
	{
		
		//Persona a = new Persona("nombresito","Apellidito");
		//System.out.println(a.getNombre());
		//UBean.obtenerAtributos(a);
		//UBean.ejecutarSet(a,"Nombre", "nuevito");
		//System.out.println(a.getNombre());
		//UBean.ejecutarGet(a,"Nombre");
		
	
		// TODO Auto-generated method stub
		//UConexion.
		//Consultas.prueba();
		//Consultas.Guardar(a);
		//Consultas.Modificar(a);
		
		//Persona b = (Persona) Consultas.obtenerPorId(Persona.class,9);
		//System.out.println(b.toString());
		//b.setNombre("Modificadodsasd");
		//Consultas.Modificar(b);
		Persona p = new Persona("prueba","dos");
		p.setId(15);
		
		 //p.setId((Integer) Consultas.Guardar(p));
		 
		 System.out.println(p.toString());
		
		
		 Consultas.Modificar(p);
		//Consultas.Eliminar(p);
		 
		 //Persona c = (Persona) Consultas.obtenerPorId(Persona.class,5);
		 //System.out.println(c.toString());
		
		
		//System.out.println("??");
		 //asd
		

	}

}
