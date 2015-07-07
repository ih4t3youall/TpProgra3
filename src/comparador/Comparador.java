package comparador;

import java.util.Comparator;

import mapa.Nodo;

public class Comparador implements Comparator<Nodo> {

	@Override
	public int compare(Nodo o1, Nodo o2) {
		return o1.getDistancia() -o2.getDistancia();
		
//		if (o1.getDistancia() < o2.getDistancia()) {
//			return 1;
//		}
//		
//		if (o1.getDistancia() > o2.getDistancia()) {
//			return -1;
//		}
//		return 0;
		
	}

}
