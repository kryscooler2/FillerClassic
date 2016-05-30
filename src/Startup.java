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
		
		Grille grille1 = new Grille(player, 7, 7);
		boolean premierTour = true;
		continuer = true;
		
		while (continuer){
			while(!grille1.isPartieFinie()){				// Penser à inverser les deux lignes
				for(int i = 0; i < nbrjoueur; i++) {		// Penser à inverser les deux lignes
					boolean correct;
					do {
						System.out.print("Les couleurs disponibles sont : ");
						char [] couleursDispo = grille1.proposeCouleur();
						for(int k = 0; k < 6 - nbrjoueur; k++) {
							System.out.print(couleursDispo[k] + " ");
						}
						System.out.println("\nC'est à " + player[i].getNom() + " de jouer :");
						//System.out.println(i);

						correct = grille1.setCoup((sc2.nextLine()).charAt(0), i);
						if(correct){
							if(premierTour){
								grille1.afficherGrille(i + 1);
								premierTour = false;
							}
							else {
								grille1.afficherGrille(i - 1);
								premierTour = true;
							}
						}
						
						else {
							System.out.println("La couleur que vous avez tapé n'est pas correcte.");
						}
					} while(!correct);
				}
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
