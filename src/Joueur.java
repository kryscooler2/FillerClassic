public class Joueur {
	private String nom;
	private int score;//  ce score correspont au score du tour actuelle
	private int scoretotal;
	private Diamant[] controled;
	
	public Joueur(){
		nom="inconnue";
		score=0;
		scoretotal=0;
	}
	
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
}
