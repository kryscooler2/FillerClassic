
public class Diamant {

	private char couleur;
	private int positionx;
	private int positiony;
	private boolean controle;
	private char tabcorres[] = {'r','v','o','j','b','i'};
	/*
	 * Constructeur
	 */
	public Diamant(int ppositionx, int ppositiony){
		positionx=ppositionx;
		positiony=ppositiony;
		controle=false;
		int couleurrand=(int)(Math.random()*6);
		couleur=tabcorres[couleurrand];
	}
		
	/*
	 * Cr�e un diamant de couleur al�atoire
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
		
	public char[] RetourneTouteLesCouleurs() {
		return tabcorres;
	}
}

