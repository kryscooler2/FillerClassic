import java.util.InputMismatchException;
import java.util.Scanner;


public class Startup {
	public static void main(String[] args){
		versionConsole();
	}
	
	public static void versionConsole() {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		int nbrjoueur = 0;
		boolean continuer = true;
		
		System.out.println("******* Jeu des 6 couleurs *******\n");
		
		while(continuer) {
			System.out.println("Choisissez le nombre de joueurs :");
			System.out.println("1. Deux joueurs");
			System.out.println("2. Trois joueurs");
			System.out.println("3. Quatre joueurs");

			nbrjoueur = sc.nextInt();
			try{
				if(nbrjoueur >= 1 && nbrjoueur <= 3)
				{
					continuer = false;
					nbrjoueur++;
					
				}
			} catch (InputMismatchException e) {
			      System.out.println("You entered bad data." );
			}
		}
		
		Joueur[] player = new Joueur [nbrjoueur];
		for (int i=0;i<nbrjoueur;i++){
			System.out.println("Nom du joueur "  + String.valueOf(i + 1));
			String nomJoueur = sc2.nextLine();
			player[i] = new Joueur(nomJoueur);
		}
		
		Grille grille1 = new Grille(player, 5, 5);
		boolean premierTour = true;
		continuer = true;
		
		while (continuer){
			int i = 0;
			while(!grille1.isPartieFinie()){				// Penser � inverser les deux lignes
				if(i == player.length) {
					i = 0;
				}
					boolean correct;
					do {
						System.out.print("\nLes couleurs disponibles pour " + player[i].getNom() +" sont : ");
						char [] couleursDispo = grille1.proposeCouleur();
						for(int k = 0; k < 6 - nbrjoueur; k++) {
							System.out.print(couleursDispo[k] + " ");
						}
						System.out.println("\nScore : " + player[i].getScoreTotalPercent() + "%");
						System.out.println("C'est � vous de jouer :");
						//System.out.println(i);

						correct = grille1.setCoup((sc2.nextLine()).charAt(0), i);
						if(correct){
							System.out.println("Vous avez gagn� " + player[i].getScore() + " diamant" +
							((player[i].getScore() == 1) ? "" : "s") + " \n\n");
							
							// R�gler l'histoire de l'�toile pour plusieurs joueurs
							if(premierTour){
								grille1.afficherGrille(i + (nbrjoueur - 1));
								premierTour = false;
							}
							else {
								grille1.afficherGrille(i - (nbrjoueur - 1));
								premierTour = true;
							}
						}
						
						else {
							System.out.println("La couleur que vous avez tap� n'est pas correcte.");
						}
					} while(!correct);
					i++;
				}
			
			
			System.out.println("Rejouer? (O/N)");
			String choix = sc2.nextLine();
			
			if(choix != "O" || choix != "o"){
				System.out.println("Au revoir");
				continuer = false;
			}				
		}
			
		
		sc.close();
		sc2.close();
	}
}
