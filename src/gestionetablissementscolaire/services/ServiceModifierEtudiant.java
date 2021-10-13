package gestionetablissementscolaire.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import gestionetablissementscolaire.domaine.Etudiant;
import gestionetablissementscolaire.domaine.Personne;

public class ServiceModifierEtudiant extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel modif_lbl = new JLabel("Nom de l'étudiant à modifier : ");
	JComboBox<String> modif_txt = new JComboBox<>();
	JButton modif = new JButton("Modifier");
	JButton btnAppliquer = new JButton("Appliquer");
	JButton btnAnnuler = new JButton("Annuler");

	JLabel labelForNom = new JLabel();
	JTextField fieldNom = new JTextField();
	JLabel labelForPrenom = new JLabel();
	JTextField fieldPrenom = new JTextField();
	JLabel labelForDateNaiss = new JLabel();
	JTextField fieldDateNaiss = new JTextField();
	JLabel validDate = new JLabel();
	JLabel labelForMail = new JLabel();
	JTextField fieldMail = new JTextField();
	JLabel validMail = new JLabel();
	JLabel labelForAdresse = new JLabel();
	JTextField fieldAdresse = new JTextField();
	JLabel labelForTel = new JLabel();
	JTextField fieldTel = new JTextField();
	JLabel validTel = new JLabel();
	JLabel labelForId = new JLabel();
	JTextField fieldId = new JTextField();

	Border bordureR = new EtchedBorder(Color.red, null);
	Border bordure = new EtchedBorder(Color.lightGray, null);

	String nom;
	String prenom;

	Etudiant etudiant = new Etudiant();

	public ServiceModifierEtudiant() {
		super("Modifier les informations d'un étudiant");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(660, 520);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.lightGray);

		modif_lbl.setBounds(10, 35, 250, 15);
		modif_txt.setBounds(185, 30, 260, 25);
		modif_txt.setEditable(true);
		AutoCompleteDecorator.decorate(modif_txt);

		validDate.setBounds(385, 217, 220, 13);
		validDate.setFont(new Font("Callibri", Font.ITALIC, 13));
		validDate.setForeground(Color.red);

		validMail.setBounds(385, 253, 260, 13);
		validMail.setFont(new Font("Callibri", Font.ITALIC, 13));
		validMail.setForeground(Color.red);

		validTel.setBounds(385, 321, 250, 13);
		validTel.setFont(new Font("Callibri", Font.ITALIC, 13));
		validTel.setForeground(Color.red);

		JLabel titre_pn = new JLabel();
		titre_pn.setBounds(133, 90, 250, 20);
		titre_pn.setFont(new Font("Callibri", 20, 15));

		modif.setBounds(450, 30, 120, 27);
		modif.setForeground(new Color(6, 48, 252));
		modif.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				modif.setForeground(Color.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				modif.setForeground(new Color(6, 48, 252));
			}
		});

		modif.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (modif_txt.getSelectedItem().toString().isEmpty()) {
					JOptionPane.showMessageDialog(ServiceModifierEtudiant.this, "Veuillez sélectionner un étudiant");

				} else {

					titre_pn.setText("Modifier les informations de l'étudiant: ");

					labelForNom.setText("Nom ");
					labelForNom.setBounds(5, 150, 50, 10);
					fieldNom.setBounds(130, 140, 250, 28);

					labelForPrenom.setText("Prénom ");
					labelForPrenom.setBounds(5, 180, 60, 10);
					fieldPrenom.setBounds(130, 174, 250, 28);

					labelForDateNaiss.setText("Date de Naissance ");
					labelForDateNaiss.setBounds(5, 220, 120, 10);
					fieldDateNaiss.setBounds(130, 214, 250, 28);
					fieldDateNaiss.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {
							if (fieldDateNaiss.getText().matches(Personne.regExForDate)) {
								validDate.setText("");
								fieldDateNaiss.setBorder(bordure);
							} else {
								validDate.setText("Format de date incorrect");
								fieldDateNaiss.setBorder(bordureR);
							}

							if (fieldDateNaiss.getText().length() == 0) {
								fieldDateNaiss.setBorder(bordure);
								validDate.setText("");
							}
						}

					});

					labelForMail.setText("Email ");
					labelForMail.setBounds(5, 260, 45, 10);
					fieldMail.setBounds(130, 250, 250, 28);
					fieldMail.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {
							if (fieldMail.getText().matches(Personne.regExForEmail)) {
								validMail.setText("");
								fieldMail.setBorder(bordure);
							} else {
								validMail.setText("L'email entré est invalide");
								fieldMail.setBorder(bordureR);
							}

							if (fieldMail.getText().length() == 0) {
								fieldMail.setBorder(bordure);
								validMail.setText("");
							}
						}

					});

					labelForAdresse.setText("Adresse ");
					labelForAdresse.setBounds(5, 288, 60, 10);
					fieldAdresse.setBounds(130, 282, 250, 28);

					labelForTel.setText("Téléphone ");
					labelForTel.setBounds(5, 324, 80, 15);
					fieldTel.setBounds(130, 318, 250, 28);
					fieldTel.addKeyListener(new KeyAdapter() {
						@Override
						public void keyReleased(KeyEvent e) {
							if (fieldTel.getText().matches(Personne.regExForNumber)) {
								validTel.setText("");
								fieldTel.setBorder(bordure);
							} else {
								validTel.setText("Numéro de téléphone invalide");
								fieldTel.setBorder(bordureR);
							}

							if (fieldTel.getText().length() == 0) {
								// revenir Ã  l'affichage normale quand le champs est vide
								fieldTel.setBorder(bordure);
								validTel.setText("");
							}
						}

					});

					labelForId.setText("Identifiant ");
					labelForId.setBounds(5, 360, 80, 10);
					fieldId.setBounds(130, 354, 250, 28);
					fieldId.setBackground(Color.lightGray);
					fieldId.setEditable(false);

					// recupeartion du nom et du prÃ©nom dans notre combobox
					String[] separateur = modif_txt.getSelectedItem().toString().split(" ", 2);

					nom = separateur[0];
					prenom = separateur[1];

					etudiant.setNom(nom);
					etudiant.setPrenom(prenom);

					infosEtudiantEnBase(etudiant.getNom(), etudiant.getPrenom());

					// Configuration et affichage des boutons quand on clique sur le btn modifier
					btnAnnuler.setBounds(120, 410, 120, 40);
					btnAnnuler.setForeground(Color.blue);

					btnAppliquer.setBounds(270, 410, 120, 40);
					btnAppliquer.setForeground(Color.blue);

				}

			}

		});

		// Configuration du boutton Annuler
		btnAnnuler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// On change la couleur du texte de notre bouton lorsqu'on le survol
				btnAnnuler.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// On revient Ã  l'Ã©tat initial quand on quitte notre boutton
				btnAnnuler.setForeground(Color.blue);

			}
		});
		btnAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(ServiceModifierEtudiant.this,
						"Voulez-vous vraiment annuler ?", "Annuler", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {

					dispose();
				}

			}
		});

		// Configuration du bouton Appliquer
		btnAppliquer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// On change la couleur du texte de notre bouton lorsqu'on le survol
				btnAppliquer.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// On revient Ã  l'Ã©tat initial quand on quitte notre bouton
				btnAppliquer.setForeground(Color.blue);
			}
		});

		btnAppliquer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (fieldNom.getText().length() != 0 && fieldPrenom.getText().length() != 0
						&& fieldDateNaiss.getText().length() != 0 && fieldMail.getText().length() != 0
						&& fieldAdresse.getText().length() != 0 && fieldTel.getText().length() != 0
						&& fieldDateNaiss.getText().matches(Personne.regExForDate)
						&& fieldMail.getText().matches(Personne.regExForEmail)
						&& fieldTel.getText().matches(Personne.regExForNumber)) {

					int confirmEnvoi = JOptionPane.showConfirmDialog(ServiceModifierEtudiant.this,
							"Voulez-vous vraiment mofidier les informations de cet étudiant ?", "confirmer",
							JOptionPane.YES_NO_OPTION);

					if (confirmEnvoi == JOptionPane.YES_OPTION) {

						etudiant.setNom(fieldNom.getText().toString());
						etudiant.setPrenom(fieldPrenom.getText().toString());
						etudiant.setDateNaissance(fieldDateNaiss.getText().toString());
						etudiant.setEmail(fieldMail.getText().toString());
						etudiant.setAdresse(fieldAdresse.getText().toString());
						etudiant.setTelephone(fieldTel.getText().toString());

						modifierEtudiantEnBase(fieldId.getText(), etudiant.getNom(), etudiant.getPrenom(),
								etudiant.getDateNaissance(), etudiant.getEmail(), etudiant.getAdresse(),
								etudiant.getTelephone());

						JOptionPane.showMessageDialog(ServiceModifierEtudiant.this, "L'étudiant " + etudiant.getPrenom()
								+ " " + etudiant.getNom() + " a bien été modifié.");

						dispose();

					}

				} else {
					JOptionPane.showMessageDialog(ServiceModifierEtudiant.this,
							"Veuillez renseigner correctement tous les champs", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}

		});

		// Ajout d'une img de fond
		JLabel img_lbl = new JLabel();
		ImageIcon icon = new ImageIcon("images/fond3.png");
		img_lbl.setBounds(new Rectangle(650, 520));
		img_lbl.setIcon(icon);

		contentPane.add(modif_lbl);
		contentPane.add(modif_txt);
		contentPane.add(modif);
		contentPane.add(titre_pn);
		contentPane.add(labelForNom);
		contentPane.add(fieldNom);
		contentPane.add(labelForPrenom);
		contentPane.add(fieldPrenom);
		contentPane.add(labelForDateNaiss);
		contentPane.add(fieldDateNaiss);
		contentPane.add(validDate);
		contentPane.add(labelForMail);
		contentPane.add(fieldMail);
		contentPane.add(validMail);
		contentPane.add(labelForAdresse);
		contentPane.add(fieldAdresse);
		contentPane.add(labelForTel);
		contentPane.add(fieldTel);
		contentPane.add(validTel);
		contentPane.add(labelForId);
		contentPane.add(fieldId);
		contentPane.add(btnAnnuler);
		contentPane.add(btnAppliquer);

		contentPane.add(img_lbl);

		etudiantEnBase();
	}

	// ************************ Connexion Ã  la base de donnÃ©es ****************

	// Informations d'accÃ¨s Ã  la base de donnÃ©es
	String url = "jdbc:mysql://localhost/gestion_scolaire";
	String login = "root";
	String password = "";
	Connection connect = null;
	Statement state = null;
	ResultSet result = null;

	public void infosEtudiantEnBase(String nom_etudiant, String prenom_etudiant) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, password);
			state = connect.createStatement();

			String sql = "SELECT * FROM etudiant WHERE `nom` = '" + nom_etudiant + "' AND `prenom` = '"
					+ prenom_etudiant + "' ";

			result = state.executeQuery(sql);

			while (result.next()) {

				// On recupÃ¨re les donnÃ©es de l'Ã©tudiant sÃ©lectionnÃ© et on les affiche dans nos
				// diffÃ©rents champs de texte
				fieldNom.setText(result.getString("nom"));
				fieldPrenom.setText(result.getString("prenom"));
				fieldDateNaiss.setText(result.getString("datedenaissance"));
				fieldMail.setText(result.getString("email"));
				fieldAdresse.setText(result.getString("adresse"));
				fieldTel.setText(result.getString("telephone"));
				fieldId.setText(result.getString("identifiant"));

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

	private void etudiantEnBase() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, password);
			state = connect.createStatement();

			String sql = "SELECT * FROM etudiant";

			result = state.executeQuery(sql);

			while (result.next()) {
				modif_txt.addItem(result.getString("nom") + " " + result.getString("prenom"));
				modif_txt.setSelectedItem(null);

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

	private void modifierEtudiantEnBase(String id, String n_Nom, String n_Prenom, String n_DateNaiss, String n_Email,
			String n_Adresse, String n_Tel) {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, password);
			state = connect.createStatement();

			String sqlRequest = "UPDATE `etudiant` SET `Identifiant`='" + id + "',`Nom`='" + n_Nom + "',`Prenom`='"
					+ n_Prenom + "',`DateDeNaissance`='" + n_DateNaiss + "',`Email`='" + n_Email + "',`adresse`='"
					+ n_Adresse + "',`Telephone`='" + n_Tel + "' WHERE `nom`= '" + nom + "' AND `prenom` = '" + prenom
					+ "' ";

			state.executeUpdate(sqlRequest);

		} catch (SQLException e) {
			e.printStackTrace();

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

		ServiceModifierEtudiant sme = new ServiceModifierEtudiant();
		sme.setVisible(true);
	}

}
