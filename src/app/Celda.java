package app;

public class Celda {

	int distancia = 0; // distancia a destino
	int recorrido = 0;// acumulacion del recorrido
	int peso = 0; // distancia mas recorrido, de referencia para buscar otro
					// camino.

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(int recorrido) {
		this.recorrido = recorrido;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

}
