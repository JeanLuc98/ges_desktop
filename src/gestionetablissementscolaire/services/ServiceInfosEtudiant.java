package gestionetablissementscolaire.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import gestionetablissementscolaire.domaine.Etudiant;

public class ServiceInfosEtudiant extends JFrame {

	private static final long serialVersionUID = -4467952119342878400L;

	JLabel recherche_lbl = new JLabel("Rechercher : ");
	JComboBox<String> recherche_txt = new JComboBox<>();
	JButton lire = new JButton("Lire infos");

	JLabel labelForNom = new JLabel();
	JLabel lbl_nom = new JLabel();
	JLabel labelForPrenom = new JLabel();
	JLabel lbl_prenom = new JLabel();
	JLabel labelForDateNaiss = new JLabel();
	JLabel lbl_dateNaiss = new JLabel();
	JLabel labelForMail = new JLabel();
	JLabel lbl_mail = new JLabel();
	JLabel labelForAdresse = new JLabel();
	JLabel lbl_adresse = new JLabel();
	JLabel labelForTel = new JLabel();
	JLabel lbl_tel = new JLabel();
	JLabel labelForId = new JLabel();
	JLabel lbl_id = new JLabel();

	Etudiant etudiant = new Etudiant();

	public ServiceInfosEtudiant() {
		super("Lire les informations d'un Ètudiant");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(460, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.lightGray);

		recherche_lbl.setBounds(50, 25, 150, 12);
		recherche_txt.setBounds(130, 20, 250, 25);
		recherche_txt.setEditable(true);

		AutoCompleteDecorator.decorate(recherche_txt);

		JLabel titre_pn = new JLabel();
		titre_pn.setBounds(90, 85, 400, 20);
		titre_pn.setFont(new Font("Callibri", Font.BOLD, 15));
		titre_pn.setForeground(Color.BLUE);
		contentPane.add(titre_pn);

		lire.setBounds(170, 55, 120, 28);
		lire.setForeground(Color.blue);
		lire.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					if (!recherche_txt.getSelectedItem().toString().isEmpty()) {
						
						titre_pn.setText("Les informations concernant l'Ètudiant : ");

						// Affichage de nos champs avec leur contenu
						labelForNom.setText("Nom ");
						labelForNom.setBounds(5, 115, 50, 10);
						lbl_nom.setBounds(140, 106, 250, 28);
						lbl_nom.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForPrenom.setText("PrÈnom ");
						labelForPrenom.setBounds(5, 140, 60, 10);
						lbl_prenom.setBounds(140, 131, 250, 28);
						lbl_prenom.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForDateNaiss.setText("Date de Naissance ");
						labelForDateNaiss.setBounds(5, 165, 120, 10);
						lbl_dateNaiss.setBounds(140, 156, 250, 28);
						lbl_dateNaiss.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForMail.setText("Email ");
						labelForMail.setBounds(5, 190, 45, 10);
						lbl_mail.setBounds(140, 181, 250, 28);
						lbl_mail.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForAdresse.setText("Adresse ");
						labelForAdresse.setBounds(5, 215, 60, 10);
						lbl_adresse.setBounds(140, 206, 250, 28);
						lbl_adresse.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForTel.setText("TÈlÈphone ");
						labelForTel.setBounds(5, 240, 80, 15);
						lbl_tel.setBounds(140, 231, 250, 28);
						lbl_tel.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForId.setText("Identifiant ");
						labelForId.setBounds(5, 265, 80, 10);
						lbl_id.setBounds(140, 256, 250, 28);
						lbl_id.setFont(new Font("Callibri", Font.BOLD, 16));

						// recupeartion du nom et du prÈnom dans notre combobox
						String[] separateur = recherche_txt.getSelectedItem().toString().split(" ", 2);
						String nom;
						String prenom;

						nom = separateur[0];
						prenom = separateur[1];

						etudiant.setNom(nom);
						etudiant.setPrenom(prenom);

						infosEtudiantEnBase(etudiant.getNom(), etudiant.getPrenom());

							
					} else {JOptionPane.showMessageDialog(ServiceInfosEtudiant.this, "Veuillez sÈlectionner un Ètudiant");}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		// Ajout d'une img de fond
		JLabel img_lbl = new JLabel();
		ImageIcon icon = new ImageIcon("images/fond3.png");
		img_lbl.setBounds(new Rectangle(450, 350));
		img_lbl.setIcon(icon);

		contentPane.add(recherche_lbl);
		contentPane.add(recherche_txt);
		contentPane.add(lire);
		contentPane.add(labelForNom);
		contentPane.add(lbl_nom);
		contentPane.add(labelForPrenom);
		contentPane.add(lbl_prenom);
		contentPane.add(labelForDateNaiss);
		contentPane.add(lbl_dateNaiss);
		contentPane.add(labelForMail);
		contentPane.add(lbl_mail);
		contentPane.add(labelForAdresse);
		contentPane.add(lbl_adresse);
		contentPane.add(labelForTel);
		contentPane.add(lbl_tel);
		contentPane.add(labelForId);
		contentPane.add(lbl_id);

		contentPane.add(img_lbl);

		etudiantEnBase();
		

	}

	// ************************ Connexion √† la base de donn√©es ****************

	// Informations d'acc√®s √† la base de donn√©es
	String url = "jdbc:mysql://localhost/gestion_scolaire";
	String login = "root";
	String password = "";
	Connection connect = null;
	Statement state = null;
	ResultSet result = null;

	private void etudiantEnBase() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, password);
			state = connect.createStatement();

			String sql = "SELECT * FROM etudiant";

			result = state.executeQuery(sql);

			while (result.next()) {
				recherche_txt.addItem(result.getString("nom") + " " + result.getString("prenom"));
				recherche_txt.setSelectedItem(null);

			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {

			try {

				connect.close();
				state.close();

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void infosEtudiantEnBase(String nom_etudiant, String prenom_etudiant) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, password);
			state = connect.createStatement();

			String sql = "SELECT * FROM etudiant WHERE `nom` = '" + nom_etudiant + "' AND `prenom` = '"
					+ prenom_etudiant + "' ";

			result = state.executeQuery(sql);

			while (result.next()) {

				// On recup√®re les donn√©es de l'√©tudiant s√©lectionn√© et on les affiche dans nos
				// diff√©rents champs de texte
				lbl_nom.setText(result.getString("nom"));
				lbl_prenom.setText(result.getString("prenom"));
				lbl_dateNaiss.setText(result.getString("datedenaissance"));
				lbl_mail.setText(result.getString("email"));
				lbl_adresse.setText(result.getString("adresse"));
				lbl_tel.setText(result.getString("telephone"));
				lbl_id.setText(result.getString("identifiant"));

			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {

			try {

				connect.close();
				state.close();

			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main() throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());

		ServiceInfosEtudiant sie = new ServiceInfosEtudiant();
		sie.setVisible(true);
	}

}
