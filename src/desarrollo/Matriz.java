package desarrollo;

import grafico.Punto;

public class Matriz {

	int [][] matriz;
	
	
	public Matriz(){
		

		for (int i = 0; i < 399; i++) {
			
			
			for (int j = 0; j < 399; j++) {
				
				matriz[i][j] =0;
				
			}
			
		}
		
	}
	
	
	public void marcarVisitado(Punto punto){
		
		int x = punto.getX();
		int y = punto.getY();
		
		matriz[x][y] =1;
		
	}
	
	public boolean isVisitado(Punto punto){
		
		int x = punto.getX();
		int y = punto.getY();
		
		if (matriz[x][y] == 1) {
			return true;
		}else {
			return false;
			
		}
		
		
	}
	
	
	
}
