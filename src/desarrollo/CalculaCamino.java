package desarrollo;

import grafico.Punto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import mapa.Camino;
import mapa.Mapa;
import mapa.Nodo;

public class CalculaCamino {
	private static int PESO = 10;
	private Camino camino;
	private List<Punto> cmc;
	private int densidadMinima;
	private int caminoReferente;
	private Nodo nodoDestino;
	int contador=0;
	private ColaConPrioridad cola = new ColaConPrioridad();
	private Set<Punto> puntosVisitados = new HashSet<Punto>();
	
	public CalculaCamino(List<Punto> cmc, Camino camino) {
		
		this.camino = camino;
		this.cmc = cmc ;
		
		Nodo origen = this.camino.getOrigen();
		Nodo destino = this.camino.getDestino();
		
		nodoDestino = this.camino.getDestino();
		
		
		this.caminoReferente = caminoDirecto(origen, destino);
		
		Nodo neoNodo = new Nodo(origen.getUbicacion());
		neoNodo.setDistanciaAcumulada(0);
		
		//es el primer nodo por lo tanto es la misma distancia que la distancia minima
		neoNodo.setDistanciaDestino(this.caminoReferente);
		
		
		Vector<Nodo> movimientosPosibles = movimientosPosibles(neoNodo);
		
		cola.acolarConjunto(movimientosPosibles);
		
		Nodo primerElemento = cola.getPrimerElementoNoVisitado();
		primerElemento.setNodoVisitado(true);
		puntosVisitados.add(new Punto(primerElemento.getUbicacion().getX(), primerElemento.getUbicacion().getY()));
		neoNodo.setNodoAnterior(null);
		primerElemento.setNodoAnterior(neoNodo);
		
		caminar(primerElemento);

	}

	
	public void caminar(Nodo nodo){
		
		
		while (nodosDistintos(nodo, nodoDestino)) {
			
	
			
			if(nodo.getDistancia() <= this.caminoReferente){
				
			Vector<Nodo> vectorMovimientosPosibles = movimientosPosibles(nodo);
			ColaConPrioridad colaAux = new ColaConPrioridad();
			colaAux.acolarConjunto(vectorMovimientosPosibles);
			Nodo primerElementoNoVisitado = colaAux.getPrimerElementoNoVisitado();
			cola.acolarConjunto(vectorMovimientosPosibles);
			//ACA CAMINA
			try{
			nodo = new Nodo(primerElementoNoVisitado.getUbicacion());
			}catch(NullPointerException e){
				nodo=cola.getPrimerElemento();
				e.printStackTrace();
				
			}
			nodo = primerElementoNoVisitado;
			puntosVisitados.add(new Punto(nodo.getUbicacion().getX(), nodo.getUbicacion().getY()));
			
			}else {
				nodo=cola.getPrimerElementoNoVisitado();
				  if(nodosDistintos(nodo, nodoDestino)){
					     this.caminoReferente = nodo.getDistanciaDestino();
					     Vector<Nodo> vectorMovimientosPosibles = movimientosPosibles(nodo);
					     cola.acolarConjunto(vectorMovimientosPosibles);
					    }
				
				
			}
			
		}

		
		while (nodo.getNodoAnterior() != null) {
			
			this.cmc.add(nodo.getUbicacion());
			nodo = nodo.getNodoAnterior();
		}
		
		
		
		
	}
	
	// true = diagonal, flase = recto
	private boolean camineDiagonal(Nodo origen, Nodo nodo) {

		int o_y = origen.getUbicacion().getY();
		int o_x = origen.getUbicacion().getX();

		int n_x = nodo.getUbicacion().getX();
		int n_y = nodo.getUbicacion().getY();

		if (o_y != n_y && o_x != n_x) {

			return true;

		}

		return false;

	}

	private Vector<Nodo> movimientosPosibles(Nodo origen) {
		Vector<Nodo> vecNodo = new Vector<Nodo>();
		int x = origen.getUbicacion().getX();
		int y = origen.getUbicacion().getY();

		if (x - 1 >= 0) {
			
			agregarAVector(x - 1, y, origen, vecNodo);
		}

		if (x + 1 <= 400) {
			agregarAVector(x + 1, y, origen, vecNodo);
		}

		if (y + 1 <= 400) {

			agregarAVector(x, y + 1, origen, vecNodo);
		}
		if (y - 1 >= 0) {

			agregarAVector(x, y - 1, origen, vecNodo);

		}

		if ((x + 1 <= 400) && (y + 1 <= 400)) {
			agregarAVector(x + 1, y + 1, origen, vecNodo);

		}

		if ((x - 1 >= 0) &&( y + 1 <= 400)) {
			agregarAVector(x - 1, y + 1, origen, vecNodo);

		}

		if ((x - 1 >= 0) && (y - 1 >= 0)) {
			agregarAVector(x - 1, y - 1, origen, vecNodo);

		}
		if ((x + 1 <= 400) && (y - 1 >= 0)) {

			agregarAVector(x + 1, y - 1, origen, vecNodo);

		}

		return vecNodo;

	}

	private void agregarAVector(int x, int y, Nodo nodoOrigen,
			Vector<Nodo> vecNodo) {

		Nodo nuevoNodo = new Nodo(new Punto(x, y));
		int multiplicador =1;
		int movimiento = 0;
		if(camineDiagonal(nodoOrigen, nuevoNodo)){
			movimiento = 141;
			
		} else {
			movimiento =100;
			
		}
	
		
		
		Mapa mapa = this.camino.getMapa();
		int densidad = mapa.getDensidad(x, y);
		densidad++;
		multiplicador = densidad *100;
		
		
		//aca carga los datos de distancia acumulada y distancia a destino re calculadas
		nuevoNodo.setDistanciaAcumulada(nodoOrigen.getDistanciaAcumulada()+(movimiento*multiplicador));
		nuevoNodo.setDistanciaDestino(this.caminoDirecto(nuevoNodo, this.camino.getDestino()));
		nuevoNodo.setNodoAnterior(nodoOrigen);
		if (densidad != 4 && nodoVisitado(nuevoNodo)) {
			vecNodo.add(nuevoNodo);
		}
		

	}
	

	
	
	private boolean nodoVisitado(Nodo nodo){
		
		for (Punto punto : puntosVisitados) {
			
			if (!nodosDistintos(nodo, new Nodo(punto))) {
				return false;
			}
			
		}
		
		return true;
		
	}

	// no toma en cuenta obstaculos
	private int caminoDirecto(Nodo origen, Nodo destino) {

		int movimientosY = 0;
		int movimientosX = 0;
		int movimientoTotal;

		int origenX = origen.getUbicacion().getX();
		int origenY = origen.getUbicacion().getY();

		int destinoX = destino.getUbicacion().getX();
		int destinoY = destino.getUbicacion().getY();

		if (mayorQue(origenY, destinoY)) {

			movimientosY = origenY - destinoY;

		} else {

			movimientosY = destinoY - origenY;
		}

		if (mayorQue(origenX, destinoX)) {

			movimientosX = origenX - destinoX;

		} else {

			movimientosX = destinoX - origenX;
		}

		if (movimientosX == 0 || movimientosY == 0) {
			movimientoTotal = (movimientosX + movimientosY) * 100;
		}else{
		
		if (mayorQue(movimientosX, movimientosY)) {

			int movimientosDiagonal = movimientosY * 141;
			int movimientosLineal = (movimientosX - movimientosY) * 100;

			movimientoTotal = movimientosDiagonal + movimientosLineal;

		} else {
			int movimientosDiagonal = movimientosX * 141;
			int movimientosLineal = (movimientosY - movimientosX) * 100;
			movimientoTotal = movimientosDiagonal + movimientosLineal;
		}
		}

		return movimientoTotal;
	}

	private boolean mayorQue(int x, int y) {

		if (x >= y) {
			return true;

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
