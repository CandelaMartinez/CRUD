package com.pildorasinformaticas.productos;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// variable de modelo
	private ModeloProductos modeloProductos;

	// pool de conexion a la bbdd
	// defino el datasource
	@Resource(name = "jdbc/productos")

	// variable pool conection donde almacenar mi pool
	private DataSource miPool = null;

	// metodo init del servlet desde el cual arranca la app
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			// inicio la conexion
			modeloProductos = new ModeloProductos(miPool);

		} catch (Exception e) {

			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// leer el parametro hidden que le llega desde el formulario
		String elComando = request.getParameter("instruccion");

		// si no se envia el parametro(xk no estoy usando el form), listar productos

		if (elComando == null)
			elComando = "listar";

		// redirigir el flujo de ejecucion al metodo adecuado, segun el if

		switch (elComando) {

		case "listar":
			obtenerProductos(request, response);
			break;

		case "insertarBBDD":
			agregarProductos(request, response);
			break;

		default:
			obtenerProductos(request, response);

		}

	}

	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {
		
		//leer la informacion del prod que viene del form
		String sec= request.getParameter("seccion");
		String nart= request.getParameter("nArt");
		String imp= request.getParameter("imp");
		String po= request.getParameter("pO");
		
		
		//crear un objeto de tipo producto con esa informacion
		Productos nuevoProducto= new Productos(sec, nart, imp, po);
		
		//enviar el obj al modelo, insertar el obj en la bbdd
		modeloProductos.agregarElNuevoProducto(nuevoProducto);
		
		//volver a listar la tabla de productos
		obtenerProductos(request, response);
		
		
	}

	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {
		List<Productos> productos;

		try {
			// obtener la lista de productos desde el modelo
			productos = modeloProductos.getProductos();

			// agregar la lista de productos al request
			request.setAttribute("LISTAPRODUCTOS", productos);

			// enviar ese request a la pagina jsp
			RequestDispatcher rd = request.getRequestDispatcher("/ListaProductos.jsp");
			rd.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
