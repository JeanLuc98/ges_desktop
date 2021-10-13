package gestionetablissementscolaire.presentation;

import java.util.Scanner;

import gestionetablissementscolaire.domaine.Cours;
import gestionetablissementscolaire.domaine.Etudiant;
import gestionetablissementscolaire.domaine.ResponsableEtudes;

/**
 * Création de notre classe principale "Main"
 * 
 * @authors JEAN LUC ASSAMOI, ABDOUL KARIM BALDE
 *
 */

public class Console {

	public static void main(String[] args) {

		/**
		 * Initialisation de nos differentes classes (Creation d'objets)
		 */
		ResponsableEtudes re = new ResponsableEtudes();
		Cours monCours = new Cours();
		Etudiant etudiant = new Etudiant();

		char reponse = ' ';
		byte choix = 0;

		Scanner clavier = new Scanner(System.in);

		/**
		 * Affichage du menu Begin
		 */
		System.out.println();
		System.out.println("*************** Bienvenue sur notre Application de Gestion d'Etablissemnet Scolaire. ***************");
		System.out.println();
		System.out.println("*************** Menu Principal. **************");
		
		System.out.println("*************************************************");
		System.out.println("*						*");
		System.out.println("*	1 - Creer un etudiant			*");
		System.out.println("*	2 - Associer un cours a un Etudiant	*");
		System.out.println("*	3 - Lire les infos d'un etudiant	*");
		System.out.println("*	4 - Modifier les infos d'un etudiant	*");
		System.out.println("*	5 - Supprimer un etudiant		*");
		System.out.println("*	6 - Lister l'ensemble des étudiants	*");
		System.out.println("*						*");
		System.out.println("*************************************************");

		/* End */

		do {

			try {

				do {
					System.out.println("Que voulez-vous faire ?");
					System.out.print("Choix: ");
					choix = clavier.nextByte();
				} while (choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix != 5 && choix != 6);

				System.out.println();

				switch (choix) {
				case 1:
					System.out.println("--------------- Entrez les informations de l'etudiant. ---------------");
					re.creerEtudiant(etudiant);
					break;

				case 2:
					System.out.println(
							"------------- Saisissez le Theme du cours et les coordonnées de l'étudiant concerné. -------------");
					re.associerCoursAEtudiant(monCours, etudiant);
					break;

				case 3:
					System.out.println("--------------- Informations sur l'etudiant. ---------------");

					re.lireInfosEtudiant(etudiant);
					break;

				case 4:
					System.out.println("--------------- Saisissez les nouvelles informations. ---------------");
					re.modifierInfosEtudiant(etudiant);
					break;

				case 5:
					System.out.println("--------------- Suppression de l'etudiant " + etudiant.getPrenom() + " "
							+ etudiant.getNom() + ". ---------------");
					re.supprimerEtudiant(etudiant);
					break;

				case 6:
					System.out.println("Désolé, vous n'avez pas accès à cette fonctionnalité.");
					break;
					
					default: System.out.println("Veuillez entrer un nombre entre 1 et 6");
				}

			} catch (Exception e) {
				/**
				 * On recupere les erreurs s'il y'en a et on les affiche.
				 */
				e.printStackTrace();
			}
			do {
				System.out.println("Voulez-vous continuer l'execution du programme ? (oui/non)");
				reponse = clavier.next().toLowerCase().charAt(0);
				/*
				 * pour éviter que la méthode next() lise notre ligne au niveau de notre
				 * dernière instruction, nous lui signalons de lire cette ligne
				 * System.out.print(clavier.nextLine());
				 */
				System.out.print(clavier.nextLine());

			} while (reponse != 'o' && reponse != 'n');

		} while (reponse == 'o');

		System.out.println("Appuyer sur la touche <<Entrer>> pour quuiter...");
		clavier.nextLine();

		clavier.close();
	}

}
