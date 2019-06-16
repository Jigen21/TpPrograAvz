package Prueba;

import Servicios.Consultas;
import Utilidades.UBean;

public class Main {

	public static void main(String[] args)
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
		Persona b = (Persona) Consultas.obtenerPorId(Persona.class,9);
		System.out.println(b.toString());
		b.setNombre("Modificadodsasd");
		Consultas.Modificar(b);
		
		Consultas.Eliminar(b);
		
		
		//System.out.println("??");
		

	}

}
