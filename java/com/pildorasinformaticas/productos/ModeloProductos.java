package com.pildorasinformaticas.productos;

import java.sql.*;
import java.util.*;
import javax.sql.*;

public class ModeloProductos {

	// tengo establecida la conexion ya en el archivo xml context

	// javax.sql.DataSource
	private DataSource origenDatos;

	// el constructor almacena dentro de origenDatos el pool de conexion
	public ModeloProductos(DataSource origenDatos) {

		this.origenDatos = origenDatos;

	}

	// ...............................................................................................................
	// obtengo el listado de productos resultado de la consulta
	// obligo a encerrar el codigo en un try catch para evitar que el programa caiga
	// si hay error de conexion
	public List<Productos> getProductos() throws Exception {

		List<Productos> productos = new ArrayList<>();

		Connection miConexion = null;
		Statement mst = null;
		ResultSet rs = null;

		// ..................................establecer la conexion con el pool de
		// conexiones
		miConexion = origenDatos.getConnection();

		// ..................................crear sentencia sql y statement
		String miSQL = "SELECT * FROM PRODUCTOS";

		mst = miConexion.createStatement();

		// ..................................ejecutar sentencia sql y meterla en el rs
		rs = mst.executeQuery(miSQL);

		// ..................................recorrer resultset obtenido con la
		// sentencia sql
		while (rs.next()) {

			String cA = rs.getString("CÓDIGOARTÍCULO");

			String sec = rs.getString("SECCIÓN");

			String nA = rs.getString("NOMBREARTÍCULO");

			double pre = rs.getDouble("PRECIO");

			String imp = rs.getString("IMPORTADO");

			String pO = rs.getString("PAÍSDEORIGEN");

			// objeto de tipo producto temporal, a cada vuelta almaceno lo que obtengo del
			// registro en ese objeto
			// Productos tempProd= new Productos(sec, nA, imp, pO);
			Productos tempProd = new Productos(cA, sec, nA, pre, imp, pO);

			// agregarlo al array
			productos.add(tempProd);
		}

		return productos;
	}

	// ............................................................................................................

	public void agregarElNuevoProducto(Productos nuevoProducto) throws Exception {

		// insertar el objeto que le llega x parametro en la bbdd
		// obtener la conexion conla bbdd, por un pool de conexiones
		Connection miConexion = null;

		PreparedStatement mist = null;

		try {
			miConexion = origenDatos.getConnection();

			// crear la instruccion sql que inserte el producto, crear la consulta preparada

			String sql = "INSERT INTO PRODUCTOS (CÓDIGOARTÍCULO,SECCIÓN,NOMBREARTÍCULO,PRECIO, IMPORTADO,PAÍSDEORIGEN)VALUES(?,?,?,?,?,?)";

			mist = miConexion.prepareStatement(sql);

			// establecer los parametros para el producto
			mist.setString(1, nuevoProducto.getcArt());
			mist.setString(2, nuevoProducto.getSeccion());
			mist.setString(3, nuevoProducto.getnArt());
			mist.setDouble(4, nuevoProducto.getPrecio());
			mist.setString(5, nuevoProducto.getImportado());
			mist.setString(6, nuevoProducto.getpOrig());

			// ejecutar la instruccion sql

			mist.execute();

		} catch (Exception e) {

		}finally {
			
			mist.close();
			miConexion.close();
			
		}

	}

	// ...........................................................................................

	public Productos getProducto(String codigoArticulo) {

		Productos elProducto = null;
		Connection miConexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String codigoAr = codigoArticulo;

		try {

			// establecer la conexion con la bbdd
			miConexion = origenDatos.getConnection();

			// sql que busque el producto con el codigo articulo quele pase
			String sql = "SELECT * FROM PRODUCTOS WHERE CÓDIGOARTÍCULO=?";

			// crear la consulta preparada
			ps = miConexion.prepareStatement(sql);

			// establecer sus parametros
			ps.setString(1, codigoAr);

			// ejecutar consulta
			rs = ps.executeQuery();

			// obtener datos de respuesta
			if (rs.next()) {
				String cArt=rs.getString("CÓDIGOARTÍCULO");

				String sec = rs.getString("SECCIÓN");

				String nA = rs.getString("NOMBREARTÍCULO");

				double pre = rs.getDouble("PRECIO");

				String imp = rs.getString("IMPORTADO");

				String pO = rs.getString("PAÍSDEORIGEN");

				// objeto de tipo producto temporal, a cada vuelta almaceno lo que obtengo del
				// registro en ese objeto
				
				elProducto = new Productos(cArt,sec, nA, pre, imp, pO);

			} else {

				throw new Exception("no hemos encontrado ese producto, con codigo: " + codigoAr);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return elProducto;
	}
	
	//.................................................................................................

	public void actualizarProducto(Productos productoActualizado) throws Exception{
	
		Connection miConexion=null;
		PreparedStatement mst=null;
		
		try {
		
		//conexion con la bbdd
		miConexion=origenDatos.getConnection();
		
		
		//sql
		String sql="UPDATE PRODUCTOS SET SECCIÓN=?, NOMBREARTÍCULO=?, PRECIO=?, IMPORTADO=?, PAÍSDEORIGEN=? WHERE CÓDIGOARTÍCULO=?";
		
		//consulta preparada
		mst= miConexion.prepareStatement(sql);
		
		//establecer parametros, es set porque quiero establecerla, no obtenerla
		mst.setString(6,productoActualizado.getcArt());
		
		mst.setString(1,productoActualizado.getSeccion());
		mst.setString(2,productoActualizado.getnArt());
		mst.setDouble(3,productoActualizado.getPrecio());
		mst.setString(4,productoActualizado.getImportado());
		mst.setString(5,productoActualizado.getpOrig());
		
		//ejecutar consulta
		mst.execute();
		
		}finally {
			
			mst.close();
			miConexion.close();
			
		}
	}

	
	//..................................................................................
	public void eliminarProducto(String cA) throws Exception {
		
		Connection miConexion=null;
		PreparedStatement mst=null;
		try {
		//establecer la conexion con la bbdd
		miConexion=origenDatos.getConnection();
		
		
		//sql de eliminacion
		String sql="DELETE FROM PRODUCTOS WHERE CÓDIGOARTÍCULO=?";
		
		//consulta preparada
		mst= miConexion.prepareStatement(sql);
		
		
		//establecer parametros de consulta
		mst.setString(1, cA);
		
		//ejecutar sentencia sql
		mst.execute();
		}finally {
			
			mst.close();
			miConexion.close();
			
		}
		
		
	}

}
