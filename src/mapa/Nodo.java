package mapa;

import grafico.Punto;

import java.util.Vector;

public class Nodo {
	private Punto ubicacion;

	private Vector<Punto> seleccionAnterior;
	boolean nodoVisitado;
	private Nodo nodoAnterior;

	// variables nuevas
	private int distanciaAcumulada;
	private int distanciaDestino;

	public Nodo(Punto ubicacion) {
		this.ubicacion = ubicacion;

		seleccionAnterior = new Vector<Punto>();

	}

	public int getDistanciaAcumulada() {
		return distanciaAcumulada;
	}

	public void setDistanciaAcumulada(int distanciaAcumulada) {
		this.distanciaAcumulada = distanciaAcumulada;
	}

	public int getDistancia() {
		return distanciaAcumulada + distanciaDestino;
	}


	public Nodo getNodoAnterior() {
		return nodoAnterior;
	}

	public void setNodoAnterior(Nodo nodoAnterior) {
		this.nodoAnterior = nodoAnterior;
	}

	public boolean isNodoVisitado() {
		return nodoVisitado;
	}

	public void setNodoVisitado(boolean nodoVisitado) {
		this.nodoVisitado = nodoVisitado;
	}

	public int getDistanciaDestino() {
		return distanciaDestino;
	}

	public void setDistanciaDestino(int distanciaDestino) {
		this.distanciaDestino = distanciaDestino;
	}

	public void setSeleccionAnterior(Vector<Punto> seleccionAnterior) {
		this.seleccionAnterior = seleccionAnterior;
	}

	public void agregarPuntosDescartados(Punto p) {

		seleccionAnterior.add(p);

	}

	public Vector<Punto> getSeleccionAnterior() {

		return seleccionAnterior;

	}

	public void setUbicacion(Punto ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Punto getUbicacion() {
		return ubicacion;
	}

}
