public class Grille {
	private Diamant[][] diamants;
	private Joueur[] joueur;
	private boolean partieFinie;
	
	public Grille(Joueur[] joueur,int ligne, int colonne){
		this.joueur 		= joueur;
		this.diamants 		= new Diamant[ligne][colonne];
		char[] tmpPosCouleur = new char[4];
		
		for (int i=0;i<ligne;i++){
			for (int j=0;j<colonne;j++){
				Diamant diamant1 = new Diamant(i,j);
				diamants[i][j] = diamant1;
			}
		}
		for (int i=0;i<joueur.length;i++){//corrige les angles
			int x=0;
			int x1=0;
			int y=0;
			int y1=0;
			switch (i){
				case 0:
					x=ligne-1;
					x1=ligne-2;
					y=0;
					y1=1;
					tmpPosCouleur[0] = diamants[x][y].getCouleur();
					System.out.println("Case 0 :" + tmpPosCouleur[0]);
					break;
				case 1:
					x=0;
					x1=1;
					y=colonne-1;
					y1=colonne-2;
					tmpPosCouleur[1] = diamants[x][y].getCouleur();

					while (tmpPosCouleur[0] == tmpPosCouleur[1]){
						Diamant diamant=new Diamant(x,y);
						diamants[x][y]=diamant;
						tmpPosCouleur[1] = diamants[x][y].getCouleur();
					}
					break;
				case 2:
					x=ligne-1;
					x1=ligne-2;
					y=colonne-1;
					y1=colonne-2;
					tmpPosCouleur[2] = diamants[x][y].getCouleur();
					
					while ((tmpPosCouleur[0] == tmpPosCouleur[2]) || (tmpPosCouleur[1] == tmpPosCouleur[2])){
						Diamant diamant=new Diamant(x,y);
						diamants[x][y]=diamant;
					}
					break;
				case 3:
					x=0;
					x1=1;
					y=0;
					y1=1;
					tmpPosCouleur[3] = diamants[x][y].getCouleur();
					
					while ((tmpPosCouleur[0] == tmpPosCouleur[3]) || (tmpPosCouleur[1] == tmpPosCouleur[3]) || (tmpPosCouleur[2] == tmpPosCouleur[3])){
						Diamant diamant=new Diamant(x,y);
						diamants[x][y]=diamant;
					}
					break;
				default:
					break;
			}
			while (diamants[x][y].getCouleur()==diamants[x1][y1].getCouleur()){
				Diamant diamant1=new Diamant(x1,y1);
				diamants[x1][y1]=diamant1;
			}
			if (i<joueur.length){
				diamants[x][y].setContoled(diamants[x][y].getCouleur());
			}
		}
		//System.out.println(player[1].getNom());
		afficherGrille();
		//isPartieFinie();
	}
	
	public void afficherGrille(){
		for (int i=0;i<diamants.length;i++){
			for(int j=0;j<diamants[i].length;j++){
				char couleurdiamant=diamants[i][j].getCouleur();
				if (diamants[i][j].isControled()){
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
		/*int nombreDiamants = diamants.length * diamants[0].length;
		
		for(int i = 0; i < joueur.length; i++) {
			switch(joueur.length) {
				case 2:
					break;
				
				case 3:
					break;
				
				case 4:
					break;
			}
		}
		
		return partieFinie;*/return false;
	}
	


	public char [] proposeCouleur(){
		char c0 =diamants[diamants.length-1][0].getCouleur();
		char c1=diamants[0][diamants[0].length-1].getCouleur();
		
		char c2=0;
		
		char c3 = 0;
		
		if (joueur.length <3) {
			c2=c1;	
			c3=c1;
		}
		
		else if( joueur.length<4) {
			c2=diamants[diamants.length-1][diamants[1].length-1].getCouleur();
			c3=c1;
		}
		
		else{
			c3=diamants[0][diamants[1].length-1].getCouleur();		
		}
		
		System.out.println(c0);
			
		System.out.println(c1);
			
		System.out.println(c2);
			
		System.out.println(c3);
			
			
		char tab[]=diamants[0][0].RetourneTouteLesCouleurs();
		char retour[] = new char[diamants[0][0].RetourneTouteLesCouleurs().length - joueur.length];
		
		for (int i=0; i<tab.length; i++){
			if(tab[i]!=c0 && tab[i]!=c1 && tab[i]!=c2  && tab[i]!=c3)
				retour[i] = tab[i];
		}
		
		return retour;
	}

	
	public void setPartieFinie(boolean partieFinie) {
		this.partieFinie = partieFinie;
	}
	
	/**
	 * 
	 * Cette méthode définit l'état de la grille après un coup du joueur
	 * 
	 * @param color Choix de la couleur à jouer
	 * @param indexJoueur le numéro du joueur actuel 
	 * 
	 * @return True si le coup est correct, False sinon
	 * 
	 */
	
	public boolean setCoup(String color, int indexJoueur) {
		switch(color) {
			case "r":
				nextPos(color, indexJoueur + 1);
				break;
				
			case "v":
				nextPos(color, indexJoueur + 1);
				break;
			
			case "o":
				nextPos(color, indexJoueur + 1);
				break;
			
			case "j":
				nextPos(color, indexJoueur + 1);
				break;
			
			case "b":
				nextPos(color, indexJoueur + 1);
				break;
			
			case "i":
				nextPos(color, indexJoueur + 1);
				break;
			
			default :
				// Erreur de saisie
				return false;
		}
		
		return true;
	}

	private void nextPos(String color, int indexJoueur) {
		switch (indexJoueur) {
			// Joueur 1
			case 1:
				// bas à droite
				break;
			// Joueur 1
			case 2:
				// bas à gauche
				break;
		}
	}
}
