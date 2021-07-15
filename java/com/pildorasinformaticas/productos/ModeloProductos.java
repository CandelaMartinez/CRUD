package com.pildorasinformaticas.productos;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.*;

public class ModeloProductos {

	//javax.sql.DataSource
	private DataSource origenDatos;
	
	//el constructor almacena dentro de origenDatos el pool de conexion
	public ModeloProductos(DataSource origenDatos) {
		
		this.origenDatos= origenDatos;
		
	}
	
	//obtengo el listado de productos resultado de la consulta
	//obligo a encerrar el codigo en un try catch para evitar que el programa caiga si hay error de conexion
	public List<Productos> getProductos() throws Exception{
		
		List<Productos> productos= new ArrayList<>();
		
		Connection miConexion= null;
		Statement mst= null;
		ResultSet rs= null;
		
		//..................................establecer la conexion con el pool de conexiones
		miConexion= origenDatos.getConnection();
		
		//..................................crear sentencia sql y statement
		String miSQL="SELECT * FROM PRODUCTOS";
		
		mst= miConexion.createStatement();
		
		//..................................ejecutar sentencia sql y meterla en el rs
		rs= mst.executeQuery(miSQL);
		
		//..................................recorrer resultset obtenido con la sentencia sql
		while(rs.next()) {
			
			String cA= rs.getString("CÓDIGOARTÍCULO");
			String sec=rs.getString("SECCIÓN");
			String nA= rs.getString("NOMBREARTÍCULO");
			//double pre= rs.getDouble("PRECIO");
			//Date fe=rs.getDate("FECHA");
			String imp=rs.getString("IMPORTADO");
			String pO= rs.getString("PAÍSDEORIGEN");
			
			//objeto de tipo producto temporal, a cada vuelta almaceno lo que obtengo del registro en ese objeto
			Productos tempProd= new Productos(sec, nA, imp, pO);
			
			//agregarlo al array
			productos.add(tempProd);
		}
		
		
		return productos;
	}

	public void agregarElNuevoProducto(Productos nuevoProducto) {
		
		//insertar el objeto que le llega x parametro en la bbdd
		//obtener la conexion conla bbdd
		
		//crear la instruccion sql que inserte el producto
		
		//establecer los parametros para el producto
		
		//ejecutar la instruccion sql
		
		
	}
	
}
