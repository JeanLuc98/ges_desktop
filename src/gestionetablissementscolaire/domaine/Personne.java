package gestionetablissementscolaire.domaine;

import java.util.Scanner;

/**
 * Création de la classe Personne dont hérite plusieurs classes
 * 
 * @author Jean Luc Assamoi, Abdoul Karim Balde
 *
 */
public class Personne {

	public static final Scanner CLAVIER = new Scanner(System.in);

	public static String regExForDate = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)?([0-9]{2})$"; // jj/mm/aa jj/mm/aaaa
	public static String regExForEmail = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$";
	public static String regExForNumber = "^(\\+|00){1}\\d{1,3}\\s?\\d{8,}$"; 
	public static String regExForId = "^\\d{1,}$"; 
	
	private int id;
	private String nom;
	private String prenom;
	private String dateNaissance;
	private String email;
	private String adresse;
	private String telephone;

	public Personne() {
		super();
	}
	
	public Personne(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public Personne(int id, String nom, String prenom, String dateNaissance, String email, String adresse,
			String telephone) {

		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.adresse = adresse;
		this.telephone = telephone;
	}

	public Personne(int id, String nom, String prenom, String email, String adresse, String telephone) {

		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.telephone = telephone;
	}

	// Génération des getters et setters pour tous nos propriétés privées
	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id < 0 ){
			System.out.println("Veuillez entrer un nombre entier positif.");
		}
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {

		if (!isValidDate(dateNaissance)) {
			System.out.println("La date entrée est invalide. Entrer une date au format \"jj/mm/aaaa\"");
		}
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!isValidEmail(email)) {
			System.out.println("L'email saisi est invalide."); // avec syserr nous avons un problème.
		}
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String tel) {
		if (!isValidTelNumber(tel)) {
			System.out.println("Numéro de téléphone invalide. Il doit contenir que des chiffres et au moins 8.");
		}
		this.telephone = tel;
	}
	
	

	public static boolean isValidEmail(String email) {

		return email.matches(regExForEmail);
	}

	public static boolean isValidDate(String date) {

		return date.matches(regExForDate);
	}

	public static boolean isValidTelNumber(String tel) {
		
		return tel.matches(regExForNumber);
	}
	

	@Override
	public String toString() {

		return this.id + " " + this.nom + " " + this.prenom + " " + this.email + " " + this.adresse + " " + this.telephone;
	}
}
