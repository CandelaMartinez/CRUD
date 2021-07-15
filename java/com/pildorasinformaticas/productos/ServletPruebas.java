package com.pildorasinformaticas.productos;

import java.io.*;
import java.sql.*;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//ojo, importar este paquete de dataSource
import javax.sql.DataSource;






@WebServlet("/ServletPruebas")
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//defino el datasource
	@Resource(name="jdbc/productos")
	
	//variable pool conection donde almacenar mi pool
	private DataSource miPool=null;
       
  
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//crear objeto printwriter
		PrintWriter salida= response.getWriter();
		
		//la respuesta va a ser del tipo texto plano
		response.setContentType("text/plain");
		
		//crear conexion con bbdd, usar el pool
		Connection miConexion=null;
		Statement mist=null;
		ResultSet rs=null;
		
		try {
			
			//uso el pool de conexiones para conextar con la bbdd
			miConexion=miPool.getConnection();
			
			String miSQL="SELECT * FROM PRODUCTOS";
			
			mist= miConexion.createStatement();
			
			//tengo todos los articulos de la tabla de productos en el resulset
			rs= mist.executeQuery(miSQL);
			
			while(rs.next()) {
				
				String s=rs.getString(3);
				
				salida.println(s);
				
			}
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
