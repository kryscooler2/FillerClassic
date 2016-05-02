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
			Joueur joueur = new Joueur(nomJoueur);
			player[i]=joueur;
		}
		
		Grille grille1 = new Grille(player, 7, 7);
		continuer = true;
		
		while(!grille1.isPartieFinie() && continuer){
			for(int i = 0; i < nbrjoueur; i++) {
				boolean correct;
				do {
					System.out.println("C'est à " + player[i].getNom() + " de jouer :");
					correct = grille1.setCoup(sc2.nextLine(), i);
					if(correct){
						grille1.afficherGrille();					
					}
					
					else {
						System.out.println("La couleur que vous avez tapé n'est pas correcte.");
					}
				} while(!correct);
			}
		}
		
		/*for (int i=0;i<nbrjoueur;i++){
			System.out.println("C'est à "  + player[i].getNom() + " de jouer");
			sc2.nextLine();
		}*/
			
		sc.close();
		sc2.close();
	}
}
