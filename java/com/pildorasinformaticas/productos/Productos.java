package com.pildorasinformaticas.productos;

import java.util.*;

public class Productos {

	private String cArt, seccion, nArt, importado,pOrig;
	private double precio;
	
	
	//sobrecarga de constructores
	public Productos(String cArt, String seccion, String nArt, double precio, String importado, String pOrig) {
		
		this.cArt = cArt;
		this.seccion = seccion;
		this.nArt = nArt;
		this.importado = importado;
		this.pOrig = pOrig;
		this.precio = precio;
		
	}
	
	public Productos( String seccion, String nArt, double precio, String importado, String pOrig) {
		
		
		this.seccion = seccion;
		this.nArt = nArt;
		this.importado = importado;
		this.pOrig = pOrig;
		this.precio = precio;
		
	}

	public Productos(String seccion, String nArt, String importado, String pOrig) {
		
		this.seccion = seccion;
		this.nArt = nArt;
		this.importado = importado;
		this.pOrig = pOrig;
	
	
	}

	
	//getter y setter
	public String getcArt() {
		return cArt;
	}

	public void setcArt(String cArt) {
		this.cArt = cArt;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getnArt() {
		return nArt;
	}

	public void setnArt(String nArt) {
		this.nArt = nArt;
	}

	public String getImportado() {
		return importado;
	}

	public void setImportado(String importado) {
		this.importado = importado;
	}

	public String getpOrig() {
		return pOrig;
	}

	public void setpOrig(String pOrig) {
		this.pOrig = pOrig;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Productos [cArt=" + cArt + ", seccion=" + seccion + ", nArt=" + nArt + ", importado=" + importado
				+ ", pOrig=" + pOrig +  "]";
	}

	

	
	
	
	
	
}
