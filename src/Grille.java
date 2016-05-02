
public class Grille {
	private Diamant[][] diamants;
	private int nbrJoueur;
	private boolean partieFinie;
	
	public Grille(int nbrJoueur,int ligne, int colonne){
		this.nbrJoueur 		= nbrJoueur;
		
		this.diamants = new Diamant[ligne][colonne];
		for (int i=0;i<ligne;i++){
			for (int j=0;j<colonne;j++){
				Diamant diamant1 = new Diamant(i,j);
				diamants[i][j] = diamant1;
			}
		}
		for (int i=0;i<4;i++){//corrige les angles
			int x=0;
			int x1=0;
			int y=0;
			int y1=0;
			switch (i){
				case 0:
					x=0;
					x1=1;
					y=colonne-1;
					y1=colonne-2;
					break;
				case 1:
					x=ligne-1;
					x1=ligne-2;
					y=0;
					y1=1;
					break;
				case 2:
					x=0;
					x1=1;
					y=0;
					y1=1;
					break;
				case 3:
					x=ligne-1;
					x1=ligne-2;
					y=colonne-1;
					y1=colonne-2;
					break;
				default:
					break;
			}
			while (diamants[x][y].getCouleur()==diamants[x1][y1].getCouleur()){
				Diamant diamant1=new Diamant(x1,y1);
				diamants[x1][y1]=diamant1;
			}
			if (i<nbrJoueur){
				diamants[x][y].setContoled(diamants[x][y].getCouleur());
			}
		}
		//System.out.println(player[1].getNom());
		//affichergrille(diamants);
		isPartieFinie();
	}
	
	public static void affichergrille(Diamant diamants[][]){
		for (int i=0;i<diamants[0].length;i++){
			for(int j=diamants.length-1;j>=0;j--){
				char couleurdiamant=diamants[j][i].getCouleur();
				if (diamants[j][i].isControled()){
					couleurdiamant=Character.toUpperCase(couleurdiamant);
				}
				System.out.print(couleurdiamant);
			}
			System.out.println("");
			
			
			/*for (int j=0;j<diamants.length;j++){
				System.out.print("_");
			}
			System.out.println("");*/
		}
	}

	public boolean isPartieFinie() {
		int nombreDiamants = diamants.length * diamants[0].length;
		
		for(int i = 0; i < nbrJoueur; i++) {
			switch(nbrJoueur) {
				case 2:
					break;
				
				case 3:
					break;
				
				case 4:
					break;
			}
		}
		
		return partieFinie;
	}

	public void setPartieFinie(boolean partieFinie) {
		this.partieFinie = partieFinie;
	}
}
