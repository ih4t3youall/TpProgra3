package mapa;

import grafico.DibujoTDA;
import grafico.PoliLinea;
import grafico.Punto;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import comparador.Comparador;

import desarrollo.CalculaCamino;

import app.Inicio;

public class Camino {
	private Mapa mapa;
	private Nodo origen;
	private Nodo destino;
	
	public Camino(Mapa mapa){
		this.mapa = mapa;
	}
	public Mapa getMapa() {
		return mapa;
	}
	public Nodo getOrigen() {
		return origen;
	}
	public void setOrigen(Nodo origen) {
		this.origen = origen;
	}
	public void setOrigen(Punto punto) {
		this.origen = new Nodo(punto);
	}
	public Nodo getDestino() {
		return destino;
	}
	public void setDestino(Nodo destino) {
		this.destino = destino;
	}
	public void setDestino(Punto punto) {
		this.destino = new Nodo(punto);
	}
	
	public DibujoTDA buscarCamino(){
		List<Punto> cmc = new ArrayList<Punto>();
		
	
		
		
		new CalculaCamino(cmc,this);
		
		
		return dibujarCamino(cmc);
	}
	

	
	private DibujoTDA dibujarCamino(List<Punto> cmc){
		int[][] xy = new int[2][cmc.size()];
		int index = 0;
		for(Punto p : cmc){
			xy[0][index] = p.getX();
			xy[1][index] = p.getY();
			index++;
		}
		Inicio.camino = new Camino(Inicio.mapa);
		return new PoliLinea(xy[0],xy[1],Color.red);
	}
}
