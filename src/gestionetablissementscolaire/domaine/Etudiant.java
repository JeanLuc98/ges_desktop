package gestionetablissementscolaire.domaine;

public class Etudiant extends Personne{
	
	public Etudiant() {
		super();
	}
	
	public Etudiant(String nom,String prenom) {
		super(nom,prenom);
	}
	
	public Etudiant(int id,String nom,String prenom,String dateNaissance,String email,String adresse,String telephone) {
		
		super(id,nom,prenom,dateNaissance,email,adresse,telephone);
	}
	
	public void afficherCoordoneesEtudiant() {
		System.out.println("coordonnées de l'étudiant : ");
		System.out.println("Nom               : " + getNom());
		System.out.println("Prenom            : " + getPrenom());
		System.out.println("Date de Naissance : " + getDateNaissance());
		System.out.println("Email             : " + getEmail());
		System.out.println("Adresse           : " + getAdresse());
		System.out.println("Téléphone         : " + getTelephone());
	}

//	@Override
//	public String toString() {
//		return "Etudiant [Identifiant = " + getId() + ", Nom = " + getNom() + ", Prenom = " + getPrenom()
//				+ ", Date de Naissance = " + getDateNaissance() + ", Email = " + getEmail() + ", Adresse = "
//				+ getAdresse() + ", Telephone = " + getTelephone() + "]";
//	}
	
	@Override
	public String toString() {
		return  this.getPrenom() + " " + this.getNom();
					}
	
	
}
