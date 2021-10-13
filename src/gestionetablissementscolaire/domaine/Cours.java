package gestionetablissementscolaire.domaine;

public class Cours {
	private String themeDuCours;
	private int nbreHeures;
	private int id;
	
	public Cours() {
		super();
	}
	
	public Cours(String themeCours) {
		super();
		this.themeDuCours = themeCours;
	}
	
	public Cours(int id, String themeCours, int heures) {
		this.id = id;
		this.themeDuCours = themeCours;
		this.nbreHeures = heures;
	}
	
	public String getThemeDuCours() {
		return themeDuCours;
	}

	
	public void setThemeDuCours(String themeDuCours) {
		this.themeDuCours = themeDuCours;
	}

	
	public int getNbreHeures() {
		return nbreHeures;
	}

	
	public void setNbreHeures(int nbreHeures) {
		this.nbreHeures = nbreHeures;
	}
	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getThemeDuCours();
	}
	
}
