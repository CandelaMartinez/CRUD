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

	// comunicarse con el modelo para intercambiar informacion
	// variable de modelo
	private ModeloProductos modeloProductos;

	// pool de conexion a la bbdd
	// defino el datasource
	@Resource(name = "jdbc/productos")

	// variable pool conection donde almacenar mi pool
	private DataSource miPool = null;

	// .................................................................................................................
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

	// .............................................................................................................
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// leer el parametro hidden que le llega desde el formulario
		String elComando = request.getParameter("instruccion");

		// si no se envia el parametro(xk no estoy usando el form1), listar productos

		if (elComando == null)
			elComando = "listar";

		// redirigir el flujo de ejecucion al metodo adecuado

		switch (elComando) {

		case "listar":
			obtenerProductos(request, response);
			break;

		case "insertarBBDD":
			agregarProductos(request, response);
			break;

		case "cargar":
			try {

				cargarProductos(request, response);

			} catch (Exception e) {

				e.printStackTrace();
			}
			break;

		case "actualizarBBDD":
			
			try {
				actualizarProductos(request,response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			 break;
			 
		case "eliminar":
			
			try {
				eliminarProducto(request,response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			break;
			
		default:
			obtenerProductos(request, response);

		}

	}
	
	//.........................................................................................

	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// leer el codigo articulo, nombre que uso en el formulario ListaProductos
		String cA = request.getParameter("cArticulo" );
		
		//borrar producto de la bbdd
		modeloProductos.eliminarProducto(cA);
		
		//volver al listado de productos
		obtenerProductos(request, response);
	}
	
	
	//.....................................................................................................

	private void actualizarProductos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// leer los datos que le vienen del formulario de actualizar
		String cA = request.getParameter("cArt");
		String sec = request.getParameter("seccion");
		String nart = request.getParameter("nArt");
		double pre = Double.parseDouble(request.getParameter("precio"));
		String imp = request.getParameter("imp");
		String po = request.getParameter("pO");
		
		//crear un obj producto conla info del formulario
		Productos productoActualizado= new Productos(cA, sec, nart, pre, imp, po);
		
		
		//actualizar la bbdd con esa info, enviar el obj al modelo para que lo haga
		modeloProductos.actualizarProducto(productoActualizado);
		
		//volver al listado con la info actualizada
		obtenerProductos(request, response);
		
	}

	// .......................................................................................
	private void cargarProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// leer el codigo articulo que viene del listado
		String codigoArticulo = request.getParameter("cArticulo");

		// enviar codigo articulo a modelo
		Productos elProducto = modeloProductos.getProducto(codigoArticulo);

		// colocar atributo correspondiente al codigo articulo

		request.setAttribute("PRODUCTOACTUALIZAR", elProducto);

		// enviar la informacion de ese articulo al formulario actualizar

		RequestDispatcher rd = request.getRequestDispatcher("/actualiza_producto.jsp");

		rd.forward(request, response);

	}

	// .......................................................................................................
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doPost(req, resp);
//	}

	// .......................................................................................................
	private void agregarProductos(HttpServletRequest request, HttpServletResponse response) {

		// leer la informacion del prod que viene del form
		String cA = request.getParameter("codigo");
		String sec = request.getParameter("seccion");
		String nart = request.getParameter("nArt");
		double pre = Double.parseDouble(request.getParameter("precio"));
		String imp = request.getParameter("imp");
		String po = request.getParameter("pO");

		// crear un objeto de tipo producto con esa informacion
		Productos nuevoProducto = new Productos(cA, sec, nart, pre, imp, po);
		// Productos nuevoProducto= new Productos(sec, nart, imp, po);

		// enviar el obj al modelo, insertar el obj en la bbdd
		try {
			modeloProductos.agregarElNuevoProducto(nuevoProducto);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		// volver a listar la tabla de productos
		obtenerProductos(request, response);

	}

	// ....................................................................................................

	private void obtenerProductos(HttpServletRequest request, HttpServletResponse response) {

		List<Productos> productos;
		try {

			//guardo un arraylist de productos con todos sus campos
			productos = modeloProductos.getProductos();

			//llamo a ese arraylist lista productos
			request.setAttribute("LISTAPRODUCTOS", productos);

			//llamo al formulario listado de productos
			RequestDispatcher miDis = request.getRequestDispatcher("/ListaProductos.jsp");

			miDis.forward(request, response);

		} catch (Exception e) {

		}

	}

}
