
public class Diamant {

	private char couleur;
	private int positionx;
	private int positiony;
	private boolean controle;
		
	/*
	 * Constructeur
	 */
	public Diamant(int ppositionx, int ppositiony){
		positionx=ppositionx;
		positiony=ppositiony;
		controle=false;
		int couleurrand=(int)(Math.random()*6);			
		char tabcorres[]={'r','v','o','j','b','i'};
		couleur=tabcorres[couleurrand];
	}
		
	/*
	 * Crée un diamant de couleur aléatoire
	 *
	public Diamant( int ppositionx, int ppositiony, char pcouleur){
		positionx=ppositionx;
		positiony=ppositiony;
		controle=false;
		couleur=pcouleur;
	}
	*/
	
	/*
	 * Retourne la couleur du diamant
	 */
	public char getCouleur(){
		return couleur;
	}
	
	/*
	 * Retourne la position en x du diamant
	 */
	public int getPositionX(){
		return positionx;
	}
		
	/*
	 * Retourne la position en y du diamant
	 */
	public int getPositionY(){
		return positiony;
	}
		
	/*
	 * Dit si un diamant est contrôlé ou pas
	 */
	public boolean isControled(){
		return controle;
	}
	
	/*
	 * Active le contrôle d'un diamant et change la couleur
	 */
	public void setContoled(char pcouleur) {
		controle=true;
		couleur=pcouleur;
	}
}

