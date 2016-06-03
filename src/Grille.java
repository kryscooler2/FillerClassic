import java.util.List;

public class Grille {
	private Diamant[][] diamants;
	private Joueur[] joueur;
	private boolean partieFinie;
	private char couleursDispo[];
	public static int ligne;
	public static int colonne;

	public Grille(Joueur[] joueur,int ligne, int colonne){
		this.joueur 		 = joueur;
		Grille.ligne		 = ligne;
		Grille.colonne		 = colonne;
		this.diamants 		 = new Diamant[ligne][colonne];
		char[] tmpPosCouleur = new char[4];
		int pair 			 = 0;
		
		for (int i=0;i<ligne;i++){
			for (int j=0;j<colonne - pair;j++){
				Diamant diamant1 = new Diamant(i,j);
				diamants[i][j] = diamant1;
				if(pair == 0) {
					pair = 1;
				}
				else {
					pair = 0;
				}
			}
		}
		this.couleursDispo   = new char[diamants[0][0].RetourneTouteLesCouleurs().length - joueur.length];

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
				joueur[i].setControled(diamants[x][y]);
			}
		}
		afficherGrille(0); // Le premier affichage (basique) avec l'étoile à côté du joueur 1 
		//isPartieFinie();
	}
	
	public void afficherGrille(int indexJoueurActuel){
		int pair = 0;
		for (int i=0;i<diamants.length;i++){
			for(int j=0;j<diamants[i].length - pair;j++){
				char couleurdiamant=diamants[i][j].getCouleur();
				for (int k = 0; k < joueur.length; k++) {
					if (joueur[k].hasDiamant(diamants[i][j])){
						couleurdiamant=Character.toUpperCase(couleurdiamant);
					}
				}
					
				// Joueur 1
				if(indexJoueurActuel == 0 && i == diamants.length - 1 && j == 0) {
					System.out.print("<" + couleurdiamant + ">* ");
				}
				
				// Joueur 2
				else if(indexJoueurActuel == 1 && i == 0 && j == diamants[i].length - 1) {
					System.out.print("<" + couleurdiamant + ">% ");
				}
				
				// Joueur 3
				else if(indexJoueurActuel == 2 && i == diamants.length - 1 && j == diamants[i].length - 1) {
					System.out.print("<" + couleurdiamant + ">^ ");
				}
				
				// Joueur 4	
				else if(indexJoueurActuel == 3 && i == 0 && j == 0) {
					System.out.print("<" + couleurdiamant + ">¤ ");
				}
				
				else {
					if(Character.isUpperCase(couleurdiamant)){
						System.out.print("<" + couleurdiamant + ">  ");
					}
					else{
						System.out.print(couleurdiamant + "    ");
					}
				}
			}
			System.out.println("");
		}
	}

	public boolean isPartieFinie() {
		for(int i = 0; i < joueur.length; i++) {
			switch(joueur.length) {
				case 2:
					partieFinie = joueur[i].getScoreTotalPercent() < 50 ? false : true;
					break;
				
				case 3:
					partieFinie = joueur[i].getScoreTotalPercent() < 34 ? false : true;
					break;
				
				case 4:
					partieFinie = joueur[i].getScoreTotalPercent() < 25 ? false : true;
					break;
			}
		}
		
		return partieFinie;
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
			c3=diamants[0][0].getCouleur();		
		}
		
		char tab[]=diamants[0][0].RetourneTouteLesCouleurs();
		int j = 0;
		
		for (int i=0; i<tab.length; i++){
			if(tab[i]!=c0 && tab[i]!=c1 && tab[i]!=c2  && tab[i]!=c3) {
				this.couleursDispo[j] = tab[i];
				j++;
			}
		}
		
		return this.couleursDispo;
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
	
	public boolean setCoup(char color, int indexJoueur) {
		boolean correct = false;
		for(int i = 0; i < couleursDispo.length; i++) {
			if(couleursDispo[i] == color) {
				correct = true;
			}
		}
		
		if(!correct)
			return correct;
		
		switch(color) {
			case 'r':
				nouvelleCouleur(color, indexJoueur);
				break;
				
			case 'v':
				nouvelleCouleur(color, indexJoueur);
				break;
			
			case 'o':
				nouvelleCouleur(color, indexJoueur);
				break;
			
			case 'j':
				nouvelleCouleur(color, indexJoueur);
				break;
			
			case 'b':
				nouvelleCouleur(color, indexJoueur);
				break;
			
			case 'i':
				nouvelleCouleur(color, indexJoueur);
				break;
			
			default :
				correct = false;
				return correct;
		}
		
		return correct;
	}

	private void nouvelleCouleur(char color, int indexJoueur) {
		List<Diamant> diamantsControles = joueur[indexJoueur].getControledDiamants();
		int nombreDiamantsGagnes = 0;

		// On change la couleur de tous les diamants contrôlés
		for(int i = 0; i < diamantsControles.size(); i++) {
			diamantsControles.get(i).setCouleur(color);
		}
		
		for(int i = 0; i < diamantsControles.size(); i++) {
			Diamant hautDroite;
			Diamant basDroite;
			Diamant basGauche;
			Diamant hautGauche;
			
			//System.out.println(diamantsControles[i].getPositionX() + " . y:" + diamantsControles[i].getPositionY());

			// Si on n'est pas dans le coin en bas à droite
			if (diamantsControles.get(i).getPositionX() == diamants.length - 1 && diamantsControles.get(i).getPositionY() == diamants.length - 1)
			{
				hautGauche = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() - 1];

				if(color == hautGauche.getCouleur()) {
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(hautGauche)) {
						joueur[indexJoueur].setControled(hautGauche);
						nombreDiamantsGagnes++;
					}
				}
			}
			
			// Si on n'est pas dans le coin en bas à gauche
			else if (diamantsControles.get(i).getPositionX() == diamants.length - 1 && diamantsControles.get(i).getPositionY() == 0)
			{
				hautDroite = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() + 1];
				
				if(color == hautDroite.getCouleur()) {
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(hautDroite)) {
						joueur[indexJoueur].setControled(hautDroite);
						nombreDiamantsGagnes++;
					}
				}
			}
			
			// Si on n'est pas dans le coin en haut à droite
			else if (diamantsControles.get(i).getPositionX() == 0 && diamantsControles.get(i).getPositionY() == diamants.length - 1)
			{
				basGauche  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() - 1];

				if(color == basGauche.getCouleur()) {
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(basGauche)) {
						joueur[indexJoueur].setControled(basGauche);
						nombreDiamantsGagnes++;
					}
				}
			}
						

			// Si on n'est pas dans le coin en haut à gauche
			else if (diamantsControles.get(i).getPositionX() == 0 && diamantsControles.get(i).getPositionY() == 0)
			{
				basDroite  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() + 1];
										
				if(color == basDroite.getCouleur()) {
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(basDroite)) {
						joueur[indexJoueur].setControled(basDroite);
						nombreDiamantsGagnes++;
					}
				}
			}
				
			// La colonne de gauche sauf les coins
			else if(diamantsControles.get(i).getPositionY() == 0 && (diamantsControles.get(i).getPositionX() != 0 || diamantsControles.get(i).getPositionX() != diamants.length - 1)){
				hautDroite = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() + 1];
				basDroite  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() + 1];
					
				if(color == hautDroite.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(hautDroite)) {
						joueur[indexJoueur].setControled(hautDroite);
						nombreDiamantsGagnes++;
					}
				}
					
				if(color == basDroite.getCouleur()) { // Diagonale en bas à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(basDroite)) {
						joueur[indexJoueur].setControled(basDroite);
						nombreDiamantsGagnes++;
					}
				}
			}
				
			// La colonne de droite sauf les coins
			else if(diamantsControles.get(i).getPositionY() == diamants.length - 1  && (diamantsControles.get(i).getPositionX() != 0 || diamantsControles.get(i).getPositionX() != diamants.length - 1)){
				basGauche  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() - 1];
				hautGauche = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() - 1];
					
				if(color == hautGauche.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(hautGauche)) {
						joueur[indexJoueur].setControled(hautGauche);
						nombreDiamantsGagnes++;
					}
				}
					
				if(color == basGauche.getCouleur()) { // Diagonale en bas à gauche
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(basGauche)) {
						joueur[indexJoueur].setControled(basGauche);
						nombreDiamantsGagnes++;
					}
				}
			}

			// La ligne du haut sauf les coins
			else if(diamantsControles.get(i).getPositionX() == 0 && (diamantsControles.get(i).getPositionY() != 0 || diamantsControles.get(i).getPositionY() != diamants.length - 1)){
				basGauche  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() - 1];
				basDroite  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() + 1];
				
				if(color == basDroite.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(basDroite)) {
						joueur[indexJoueur].setControled(basDroite);
						nombreDiamantsGagnes++;
					}
				}
					
				if(color == basGauche.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(basGauche)) {
						joueur[indexJoueur].setControled(basGauche);
						nombreDiamantsGagnes++;
					}
				}
			}
				
			// La ligne du bas sauf les coins
			else if(diamantsControles.get(i).getPositionX() == diamants[0].length - 1 && (diamantsControles.get(i).getPositionY() != 0 || diamantsControles.get(i).getPositionY() != diamants.length - 1)){
				hautDroite = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() + 1];
				hautGauche = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() - 1];
				
				if(color == hautGauche.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(hautGauche)) {
						joueur[indexJoueur].setControled(hautGauche);
						nombreDiamantsGagnes++;
					}
				}
				
				if(color == hautDroite.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(hautDroite)) {
						joueur[indexJoueur].setControled(hautDroite);
						nombreDiamantsGagnes++;
					}
				}
			}
			
			else {
				hautDroite = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() + 1];
				hautGauche = diamants[diamantsControles.get(i).getPositionX() - 1][diamantsControles.get(i).getPositionY() - 1];
				basGauche  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() - 1];
				basDroite  = diamants[diamantsControles.get(i).getPositionX() + 1][diamantsControles.get(i).getPositionY() + 1];
				
				if(color == basDroite.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(basDroite)) {
						joueur[indexJoueur].setControled(basDroite);
						nombreDiamantsGagnes++;
					}
				}
					
				if(color == basGauche.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(basGauche)) {
						joueur[indexJoueur].setControled(basGauche);
						nombreDiamantsGagnes++;
					}
				}
				
				if(color == hautGauche.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(hautGauche)) {
						joueur[indexJoueur].setControled(hautGauche);
						nombreDiamantsGagnes++;
					}
				}
				
				if(color == hautDroite.getCouleur()) { // Diagonale en haut à droite
					// Si le diamant n'est pas déjà contrôlé
					if(!joueur[indexJoueur].hasDiamant(hautDroite)) {
						joueur[indexJoueur].setControled(hautDroite);
						nombreDiamantsGagnes++;
					}
				}
			}
		}
		
		joueur[indexJoueur].setScore(nombreDiamantsGagnes);
	}
}
