public class Joueur {
	private String nom;
	private int score;//  ce score correspont au score du tour actuel
	private int scoretotal;
	private Diamant[] controled;
	
	public Joueur(String pnom){
		nom=pnom;
		score=0;
		scoretotal=0;
	}
	
	public String getNom(){
		return nom;
	}
	public int getScore(){
		return score;
	}
	public int getScoretotal(){
		return scoretotal;
	}
	
	public void modifscore(int pscore){
		score=pscore;
		scoretotal=scoretotal + score;
	}
	

	/*
	 * Dit si un diamant est contrôlé ou pas, par le joueur
	 */
	
	public boolean hasDiamant(Diamant diamant) {
		boolean res = false;
		int i = 0;
		
		do {
			if(diamant.getPositionX() == controled[i].getPositionX() && diamant.getPositionY() == controled[i].getPositionY()) {
				res = true;
			}
			i++;
		} while (i < scoretotal && !res);

		return res;
	}
	
	/*
	 * Active le contrôle d'un diamant et change la couleur
	 */
	public void setControled(Diamant controled) {
		this.controled[scoretotal] = controled;
		scoretotal++;
	}
	
	public Diamant[] getControledDiamants() {
		return controled;
	}
}
