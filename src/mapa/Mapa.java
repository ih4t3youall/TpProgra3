package mapa;

import grafico.Area;
import grafico.DibujoTDA;
import grafico.Punto;

public class Mapa {
    static int VALOR_MAXIMO = 3;
	private int[][] grilla;
	
    public Mapa() {
    	grilla = new int[DibujoTDA.LARGO][DibujoTDA.ALTO];
    }

	public int[][] getGrilla() {
		return grilla;
	}
	
	public void setArea(Area area) {
		int[] c = area.getCoordenadas();
		for(int w = 0; w < c[2]; w++){
			for(int h = 0; h < c[3]; h++){
				grilla[c[0]+w][c[1]+h] = area.getColorOrdinal();
			}
		}
	}
	
	public int getDensidad(int x, int y) {
		return grilla[x][y];
	}
	
	public int getDensidad(Punto p) {
		return grilla[p.getX()][p.getY()];
	}
	
	public boolean puntoValido(Punto p) {
		if (p.getX() < 0 || p.getX() >= DibujoTDA.LARGO ) 
			return false; 
		if (p.getY() < 0 || p.getY() >= DibujoTDA.ALTO)
			return false;
		if (grilla[p.getX()][p.getY()] >= VALOR_MAXIMO){
			return false;
		}
		else 
			return true;
	}
}
