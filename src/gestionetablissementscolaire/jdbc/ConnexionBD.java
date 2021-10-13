package gestionetablissementscolaire.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD {

	// Informations d'accès à la base de données
	static String url = "jdbc:mysql://localhost/gestion_scolaire";
	static String login = "root";
	static String password = "";
	static Connection connect = null;
	static Statement state = null, state2 = null;
	static ResultSet result = null;

	public void sauverEnBase(String id, String nom, String prenom, String mail, String adresse, String tel,
			String dateNaiss) {

		try {

			// Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			// Récupération de la connexion
			connect = DriverManager.getConnection(url, login, password);
			// Création d'un statement
			state = connect.createStatement();

			// Nos requetes sql
			String sql = "INSERT INTO `etudiant`(`Identifiant`, `Nom`, `Prenom`,  `DateDeNaissance`, `Email`, `Adresse`, `Telephone`) VALUES ( '"
					+ id + "' , '" + nom + "' , '" + prenom + "',  '" + dateNaiss + "', '" + mail + "',  '" + adresse
					+ "',  '" + tel + "')";

			// Exécution des requetes
			state.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {

			try {
				// Libérer les ressources de la memoire
				connect.close();
				state.close();

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void supprimerEnBase(String nom, String prenom) {

		try {

			// Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			// Récupération de la connexion
			connect = DriverManager.getConnection(url, login, password);
			// Création d'un statement
			state = connect.createStatement();

			String sql = " DELETE FROM `etudiant` WHERE `nom` = '" + nom + "' AND `prenom` = '" + prenom + "' ";

			// Exécution dess requetes
			state.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {

			try {
				// Libérer les ressources de la memoire
				connect.close();
				state.close();

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

}
