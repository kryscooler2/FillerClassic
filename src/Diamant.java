
public class Diamant {

	private char couleur;
	private int positionx;
	private int positiony;
	private char tabcorres[] = {'r','v','o','j','b','i'};
	
	/*
	 * Constructeur
	 */
	public Diamant(int ppositionx, int ppositiony){
		positionx=ppositionx;
		positiony=ppositiony;
		int couleurrand=(int)(Math.random()*6);
		couleur=tabcorres[couleurrand];
	}

	/*
	 * D�finit la couleur d'un diamant
	 */
	public void setCouleur(char couleur){	
		this.couleur = couleur;
	}
	
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

