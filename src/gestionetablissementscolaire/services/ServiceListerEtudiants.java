package gestionetablissementscolaire.services;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class ServiceListerEtudiants extends JFrame {

	private static final long serialVersionUID = 1317449973194976787L;

	public ServiceListerEtudiants() {
		super("Liste de tous les �tudiants");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);

		JPanel contentPane = (JPanel) getContentPane();
		// contentPane.setBackground(Color.lightGray);
		// contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

		JLabel label = new JLabel("Liste des �tudiants de l'�tablissement");
		label.setFont(new Font("Callibri", Font.TRUETYPE_FONT, 20));

		contentPane.add(label, BorderLayout.NORTH);

		// ********************* Connexion à ma base de données **********************

		// Informations d'accès à la base de données
		String url = "jdbc:mysql://localhost/gestion_scolaire";
		String login = "root";
		String password = "";
		Connection connect = null;
		Statement state = null;
		ResultSet result = null;

		try {

			// Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
			// Récupération de la connexion
			connect = DriverManager.getConnection(url, login, password);
			// Création d'un statement
			state = connect.createStatement();

			String sql = "SELECT * FROM etudiant ORDER BY nom, prenom"; // sql request

			// Exécution de la requete
			result = state.executeQuery(sql);
			ResultSetMetaData metaData = result.getMetaData();

			// Récupérer et stocker le nombre de colonnes de ma bd dans un tableau de
			// colonnes.
			String[] colonnes = new String[metaData.getColumnCount()];
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				colonnes[i - 1] = metaData.getColumnName(i); // On récupère le nom de la colonne désignée
			}

			// Récupérer chaque ligne de ma bd et la stocker dans notre List<Object[]>.
			List<Object[]> data = new ArrayList<>();

			while (result.next()) {

				Object[] ligne = new Object[metaData.getColumnCount()];
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					ligne[i - 1] = result.getObject(i); // On recupère la valeur de la colonne désignée dans la ligne
					// courante
				}

				data.add(ligne);
			}

			// Conversion de notre List<> en tableau -->
			// data.stream().toArray(Object[][]::new)
			JTable table = new JTable(data.stream().toArray(Object[][]::new), colonnes);
			table.setEnabled(false); // Empecher l'utilisateur de modifier des valeurs de notre table
			JScrollPane scroll = new JScrollPane(table);
			contentPane.add(scroll, BorderLayout.CENTER);

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

	public static void main() throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());

		ServiceListerEtudiants sListe = new ServiceListerEtudiants();
		sListe.setVisible(true);
	}

}
