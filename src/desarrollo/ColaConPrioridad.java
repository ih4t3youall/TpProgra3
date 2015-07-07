package desarrollo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

import mapa.Nodo;

import comparador.Comparador;

public class ColaConPrioridad {

	LinkedList<Nodo> nodos;

	public ColaConPrioridad() {

		nodos = new LinkedList<Nodo>();

	}

	public void acolar(Nodo nodo) {

		nodos.add(nodo);

	}

	public LinkedList<Nodo> getLista() {

		Collections.sort(nodos, new Comparador());
		
	
		return nodos;
	}

	public Nodo getPrimerElementoNoVisitado() {
		Collections.sort(nodos, new Comparador());
		nodos.size();
		for (int i = 0; i < nodos.size(); i++) {
			
			if (!nodos.get(i).isNodoVisitado()){
				nodos.get(i).setNodoVisitado(true);
				Nodo first = nodos.getFirst();
				nodos.removeFirst();
				return first;
				
			}
			
		}
		
		
		
		
		return null;

	}
	
	
	public Nodo getPrimerElemento(int i) {
		Collections.sort(nodos, new Comparador());
		nodos.size();
		
				return nodos.get(i);
				

	}
	
	public Nodo getPrimerElemento() {
		Collections.sort(nodos, new Comparador());
		nodos.size();
		
				return nodos.get(0);
				

	}

	
	
	

	public void acolarConjunto(Vector<Nodo> vecNodo) {

		
		
		for (Nodo nodo : vecNodo) {

			if (!nodoVisitado(nodo)) {
				
				nodos.add(nodo);
			}
		}

		Collections.sort(nodos, new Comparador());
	}
	
	private boolean nodoVisitado(Nodo nodoExistente){
		
		for (Nodo nodo : nodos) {
			
			if (!nodosDistintos(nodoExistente, nodo)) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	//devuelve true si son distintos
		private boolean nodosDistintos(Nodo nodo1, Nodo nodo2) {

			if ((nodo1.getUbicacion().getX() == nodo2.getUbicacion().getX())
					&& (nodo1.getUbicacion().getY() == nodo2.getUbicacion().getY())) {
				return false;

			}
			return true;

		}
		

}
