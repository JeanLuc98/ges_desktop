package gestionetablissementscolaire.domaine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

/**
 * On a déclaré les tableaux dynamiques "tousLesEtudiants" et "tousLesCours",
 * pour y stocker respectivement l'ensemble des étudiants de l'établissement et
 * l'ensemble des cours disponibles.
 * 
 * 
 * @authors JEAN LUC ASSAMOI, ABDOUL KARIM BALDE
 *
 */
public class ResponsableEtudes extends Personne {

	Collection<Cours> tousLesCours = new ArrayList<Cours>();
	Set<Etudiant> tousLesEtudiants = new HashSet<Etudiant>();

	Personne person = new Personne();

	public ResponsableEtudes(int id, String nom, String prenom, String email, String adresse, String telephone) {
		super(id, nom, prenom, email, adresse, telephone);
	}

	public ResponsableEtudes() {
		super();
	}
	

	public void creerEtudiant(Etudiant etudiant) {

		System.out.print("Prénom de l'étudiant : ");
		etudiant.setPrenom(CLAVIER.nextLine());

		System.out.print("Nom de l'étudiant : ");
		etudiant.setNom(CLAVIER.nextLine());

		do {
			System.out.print("Date de naissance : ");
			etudiant.setDateNaissance(CLAVIER.nextLine());

		} while (!etudiant.getDateNaissance().matches(regExForDate));

		do {
			System.out.print("Email de l'étudiant : ");
			etudiant.setEmail(CLAVIER.nextLine());

		} while (!etudiant.getEmail().matches(regExForEmail));

		System.out.print("Adresse de l'étudiant : ");
		etudiant.setAdresse(CLAVIER.nextLine());

		do {
			System.out.print("Numéro de téléphone : ");
			etudiant.setTelephone(CLAVIER.nextLine());

		} while (!etudiant.getTelephone().matches(regExForNumber));
		
		
		do	{
			try {
				System.out.print("Identifiant de l'étudiant : ");
				etudiant.setId(CLAVIER.nextInt());
				System.out.println(CLAVIER.nextLine());

			}catch (InputMismatchException e) {
				System.out.println("L'identifiant doit etre un entier.");
			} 

		} while (etudiant.getId() < 0) ;

		System.out.println("L'étudiant " + etudiant.getPrenom().toUpperCase() + " "
				+ etudiant.getNom().toUpperCase() + " a bien été crée.");
		System.out.println();
	
		tousLesEtudiants.add(etudiant);

	}
	

	public void associerCoursAEtudiant(Cours cours, Etudiant assCoursEtudiant) {

		System.out.print("Thème du cours : ");
		cours.setThemeDuCours(CLAVIER.nextLine());

		System.out.print("Prénom de l'étudiant : ");
		assCoursEtudiant.setPrenom(CLAVIER.nextLine());

		System.out.print("Nom de l'étudiant : ");
		assCoursEtudiant.setNom(CLAVIER.nextLine());

		System.out.println("Le cours de " + cours.getThemeDuCours().toUpperCase() + " a été associé à l'étudiant "
				+ assCoursEtudiant.getPrenom().toUpperCase() + " " + assCoursEtudiant.getNom().toUpperCase());
		System.out.println();

	}

	@SuppressWarnings("unlikely-arg-type")
	public Etudiant lireInfosEtudiant(Etudiant infoEtudiant) {
		System.out.print("Entrez le nom de l'étudiant: ");
		infoEtudiant.setNom(CLAVIER.nextLine());
		if (tousLesEtudiants.contains(infoEtudiant.getNom())) {

			System.out.println("------ Informations sur l'etudiant " + infoEtudiant.getNom() + ". ------");

			System.out.println(infoEtudiant.getId() + " " + infoEtudiant.getNom() + " " + infoEtudiant.getPrenom() + " "
					+ infoEtudiant.getDateNaissance() + " " + infoEtudiant.getEmail() + " " + infoEtudiant.getAdresse() + " "
					+ infoEtudiant.getTelephone());
		} else {
			System.out.println("Aucune information n'a été trouvée.");
		}
		System.out.println();
		return infoEtudiant;
	}

	public Etudiant modifierInfosEtudiant(Etudiant modifEtudiant) {

		System.out.print("Nouveau prénom de l'étudiant : ");
		modifEtudiant.setPrenom(CLAVIER.nextLine());

		System.out.print("Nouveau nom de l'étudiant : ");
		modifEtudiant.setNom(CLAVIER.nextLine());

		do {
			System.out.print("Nouvelle date de naissance : ");
			modifEtudiant.setDateNaissance(CLAVIER.nextLine());
		} while (!modifEtudiant.getDateNaissance().matches(regExForDate));

		do {
			System.out.print("Nouveau email de l'étudiant : ");
			modifEtudiant.setEmail(CLAVIER.nextLine());
		} while (!modifEtudiant.getEmail().matches(regExForEmail));

		System.out.print("Nouvelle adresse de l'étudiant : ");
		modifEtudiant.setAdresse(CLAVIER.nextLine());

		do {
			System.out.print("Nouveau numéro de téléphone : ");
			modifEtudiant.setTelephone(CLAVIER.nextLine());
		} while (!modifEtudiant.getTelephone().matches(regExForNumber));

		
		
			try {
				System.out.print("Nouvel identifiant de l'étudiant : ");
				modifEtudiant.setId(CLAVIER.nextInt());
				
			} catch (InputMismatchException e) {
				System.out.println("Cette valeur n'est pas un nombre.");
			}

			
		tousLesEtudiants.add(modifEtudiant);
		System.out.println("Les informations de l'étudiant ont été modifiées.");
		System.out.println();

		return modifEtudiant;
	}

	public void supprimerEtudiant(Etudiant supEudiant) {
		
		// on supprime l'étudiant de notre collection
		tousLesEtudiants.remove(supEudiant); 
		
		System.out.println("L'étudiant " + supEudiant.getPrenom().toUpperCase() + " "
				+ supEudiant.getNom().toUpperCase() + " a été supprimé.");
		System.out.println();
	}
	
	@Override
	public String toString() {
		return getPrenom() + " " + getNom();
	}
}
