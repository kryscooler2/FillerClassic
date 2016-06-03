import java.util.ArrayList;
import java.util.List;

public class Joueur {
	private String nom;
	private int score;//  ce score correspont au score du tour actuel
	private int scoretotal;
	private List<Diamant> controled;
	
	public Joueur(String pnom){
		nom=pnom;
		score=0;
		scoretotal=0;
		controled = new ArrayList<>();
	}
	
	public String getNom(){
		return nom;
	}
	public int getScore(){
		return score;
	}
	
	public double getScoreTotalPercent(){
		return (scoretotal * 100) / (Grille.ligne * Grille.colonne);
	}
	
	public void setScore(int nombreDiamantsGagnes) {
		score = nombreDiamantsGagnes;
	}

	/*
	 * Dit si un diamant est contrôlé ou pas, par le joueur
	 */
	
	public boolean hasDiamant(Diamant diamant) {
		boolean res = false;
		int i = 0;
		
		// On compare les positions des 
		do {
				if(diamant.getPositionX() == controled.get(i).getPositionX() &&
					diamant.getPositionY() == controled.get(i).getPositionY()) {
				res = true;
			}
			i++;
		} while (i < controled.size() && !res);

		return res;
	}
	
	/*
	 * Active le contrôle d'un diamant et change la couleur
	 */
	public void setControled(Diamant newDiamant) {
		this.controled.add(newDiamant);
		scoretotal++;
	}
	
	public List<Diamant> getControledDiamants() {
		return controled;
	}
}
