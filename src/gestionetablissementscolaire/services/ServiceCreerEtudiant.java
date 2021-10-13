package gestionetablissementscolaire.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import gestionetablissementscolaire.domaine.Etudiant;
import gestionetablissementscolaire.domaine.Personne;
import gestionetablissementscolaire.jdbc.ConnexionBD;

public class ServiceCreerEtudiant extends JFrame {

	Etudiant etudiant = new Etudiant();
	ConnexionBD cn = new ConnexionBD();

	JButton btnCreer = new JButton("Créer");
	JButton btnEffacer = new JButton("Effacer");

	JLabel labelForNom = new JLabel();
	JTextField fieldNom = new JTextField();
	JLabel labelForPrenom = new JLabel();
	JTextField fieldPrenom = new JTextField();
	JLabel labelForDateNaiss = new JLabel();
	JTextField fieldDateNaiss = new JTextField();
	JLabel validDate = new JLabel();

	JLabel labelForMail = new JLabel();
	JLabel validMail = new JLabel();
	JTextField fieldMail = new JTextField();
	JLabel labelForAdresse = new JLabel();
	JTextField fieldAdresse = new JTextField();
	JLabel labelForTel = new JLabel();
	JTextField fieldTel = new JTextField();
	JLabel validTel = new JLabel();
	JLabel labelForId = new JLabel();
	JTextField fieldId = new JTextField();

	Border bordureR = new EtchedBorder(Color.red, null);
	Border bordure = new EtchedBorder(Color.lightGray, null);

	private static final long serialVersionUID = 7559090243112963908L;

	@SuppressWarnings("static-access")
	public ServiceCreerEtudiant() {
		super("Créer un étudiant");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(560, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.lightGray);

		JLabel titre_pn = new JLabel("Veuillez saisir les informations concernant l'étudiant : ");
		titre_pn.setBounds(80, 10, 400, 20);
		titre_pn.setFont(new Font("Callibri", 20, 15));
		contentPane.add(titre_pn);

		labelForNom.setText("Nom : ");
		labelForNom.setBounds(5, 53, 50, 10);
		fieldNom.setBounds(130, 47, 250, 28);
		
		labelForPrenom.setText("Prénom : ");
		labelForPrenom.setBounds(5, 101, 60, 10);
		fieldPrenom.setBounds(130, 92, 250, 28);

		labelForDateNaiss.setText("Date de Naissance : ");
		labelForDateNaiss.setBounds(5, 145, 120, 10);
		fieldDateNaiss.setBounds(130, 137, 250, 28);
		fieldDateNaiss.setText("exemple: 01/01/2000");
		fieldDateNaiss.setForeground(Color.GRAY);
		validDate.setBounds(130, 165, 220, 13);
		validDate.setFont(new Font("Callibri", Font.ITALIC, 13));
		validDate.setForeground(Color.red);
		fieldDateNaiss.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (fieldDateNaiss.getText().matches(etudiant.regExForDate)) {

					validDate.setText("");
					fieldDateNaiss.setBorder(bordure);

				} else {

					if (fieldDateNaiss.getText().length() == 0) {
						fieldDateNaiss.setBorder(bordure);
						validDate.setText("");
					}
				}

			}

		});
		fieldDateNaiss.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

				if (fieldDateNaiss.getText().equals("exemple: 01/01/2000")) {
					fieldDateNaiss.setText("");
					fieldDateNaiss.setForeground(Color.black);
				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (fieldDateNaiss.getText().isEmpty()) {
					fieldDateNaiss.setText("exemple: 01/01/2000");
					fieldDateNaiss.setForeground(Color.GRAY);
				} else {

					if (!fieldDateNaiss.getText().matches(etudiant.regExForDate)) {
						validDate.setText("Format de date incorrect");
						fieldDateNaiss.setBorder(bordureR);
					}
				}

			}

		});

		labelForMail.setText("Email : ");
		labelForMail.setBounds(5, 191, 45, 10);
		fieldMail.setBounds(130, 182, 250, 28);
		fieldMail.setText("email@exemple.com");
		fieldMail.setForeground(Color.GRAY);
		validMail.setBounds(130, 210, 260, 13);
		validMail.setFont(new Font("Callibri", Font.ITALIC, 13));
		validMail.setForeground(Color.red);
		fieldMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (fieldMail.getText().matches(etudiant.regExForEmail)) {
					validMail.setText("");
					fieldMail.setBorder(bordure);

				} else {

					if (fieldMail.getText().length() == 0) {
						fieldMail.setBorder(bordure);
						validMail.setText("");
					}

				}

			}

		});
		fieldMail.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (fieldMail.getText().equals("email@exemple.com")) {
					fieldMail.setText("");
					fieldMail.setForeground(Color.black);
				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (fieldMail.getText().isEmpty()) {
					fieldMail.setText("email@exemple.com");
					fieldMail.setForeground(Color.GRAY);

				} else {
					if (!fieldMail.getText().matches(etudiant.regExForEmail)) {

						validMail.setText("L'email entré est invalide");
						fieldMail.setBorder(bordureR);
					}

				}

			}

		});

		labelForAdresse.setText("Adresse : ");
		labelForAdresse.setBounds(5, 233, 60, 10);
		fieldAdresse.setBounds(130, 227, 250, 28);

		labelForTel.setText("Téléphone : ");
		labelForTel.setBounds(5, 277, 80, 15);
		fieldTel.setBounds(130, 272, 250, 28);
		fieldTel.setText("+225 01020304");
		fieldTel.setForeground(Color.GRAY);
		validTel.setBounds(130, 300, 250, 13);
		validTel.setFont(new Font("Callibri", Font.ITALIC, 13));
		validTel.setForeground(Color.red);
		fieldTel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (fieldTel.getText().matches(etudiant.regExForNumber)) {
					validTel.setText("");
					fieldTel.setBorder(bordure);
				} else {

					if (fieldTel.getText().length() == 0) {
						fieldTel.setBorder(bordure);
						validTel.setText("");
					}

				}

			}

		});
		fieldTel.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

				if (fieldTel.getText().equals("+225 01020304")) {
					fieldTel.setText("");
					fieldTel.setForeground(Color.BLACK);
				}

			}

			@Override
			public void focusLost(FocusEvent e) {

				if (fieldTel.getText().isEmpty()) {
					fieldTel.setText("+225 01020304");
					fieldTel.setForeground(Color.GRAY);
				} else {
					if (!fieldTel.getText().matches(etudiant.regExForNumber)) {
						validTel.setText("Numéro de téléphone invalide");
						fieldTel.setBorder(bordureR);
					}
				}

			}

		});
		
		labelForId.setText("Identifiant : ");
		labelForId.setBounds(5, 324, 80, 10);
		fieldId.setBounds(130, 317, 250, 28);
		fieldId.setText(getIdAuto(8));
		fieldId.setBackground(Color.lightGray);
		fieldId.setEditable(false);
			

		// Configuration du boutton Effacer
		btnEffacer.setBounds(135, 370, 120, 40);
		btnEffacer.setForeground(Color.blue);
		btnEffacer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// On change la couleur du texte de notre bouton lorsqu'on le survol
				btnEffacer.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// On revient à l'état initial quand on quitte notre boutton
				btnEffacer.setForeground(Color.blue);

			}
		});
		btnEffacer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(ServiceCreerEtudiant.this,
						"Voulez-vous vraiment effacer toutes vos saisies ?", "Effacer les saisies",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {

					fieldNom.setText("");
					fieldPrenom.setText("");
					fieldDateNaiss.setText("exemple: 01/01/2000");
					fieldMail.setText("email@exemple.com");
					fieldAdresse.setText("");
					fieldTel.setText("+225 01020304");

					fieldDateNaiss.setBorder(bordure);
					fieldDateNaiss.setForeground(Color.GRAY);
					validDate.setText("");
					fieldMail.setBorder(bordure);
					fieldMail.setForeground(Color.GRAY);
					validMail.setText("");
					fieldTel.setBorder(bordure);
					fieldTel.setForeground(Color.GRAY);
					validTel.setText("");
				}

			}
		});

		// Configuration du bouton Creer
		btnCreer.setBounds(265, 370, 120, 40);
		btnCreer.setForeground(Color.blue);
		btnCreer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// On change la couleur du texte de notre bouton lorsqu'on le survol
				btnCreer.setForeground(Color.green);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// On revient Ã  l'Ã©tat initial quand on quitte notre bouton
				btnCreer.setForeground(Color.blue);
			}
		});

		btnCreer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if ( fieldNom.getText().length() != 0
						&& fieldDateNaiss.getText().length() != 0 && fieldPrenom.getText().length() != 0
						&& fieldMail.getText().length() != 0 && fieldAdresse.getText().length() != 0
						&& fieldTel.getText().length() != 0 && fieldDateNaiss.getText().matches(Personne.regExForDate)
						&& fieldMail.getText().matches(Personne.regExForEmail)
						&& fieldTel.getText().matches(Personne.regExForNumber)) {

					// Connection Ã  la base de données afin de vérifier que l'id généré n'a pas déjà
					// été attribué à un étudiant
					String url = "jdbc:mysql://localhost/gestion_scolaire";
					String login = "root";
					String password = "";
					Connection connect = null;
					PreparedStatement pState = null;
					ResultSet result = null;

					String sql = "SELECT * FROM etudiant WHERE id = ? ";

					try {
						Class.forName("com.mysql.jdbc.Driver");
						connect = DriverManager.getConnection(url, login, password);
						pState = connect.prepareStatement(sql);
						pState.setString(1, fieldId.getText());

						result = pState.executeQuery();

						if (result.next()) {
							
							int confirm = JOptionPane.showConfirmDialog(ServiceCreerEtudiant.this,
									"Cet identifiant a déjà été attribué à un étudiant.\nGénérer un nouvel identifiant ?", "Erreur",
									JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
							
							if (confirm == JOptionPane.YES_OPTION) {
								fieldId.setText(getIdAuto(8));
							}
							
							
						} else {

							int confirmEnvoi = JOptionPane.showConfirmDialog(ServiceCreerEtudiant.this,
									"Voulez-vous vraiment créer cet étudiant ?", "confirmer",
									JOptionPane.YES_NO_OPTION);

							if (confirmEnvoi == JOptionPane.YES_OPTION) {

								etudiant.setNom(fieldNom.getText());
								etudiant.setPrenom(fieldPrenom.getText());
								etudiant.setDateNaissance(fieldDateNaiss.getText());
								etudiant.setEmail(fieldMail.getText());
								etudiant.setAdresse(fieldAdresse.getText());
								etudiant.setTelephone(fieldTel.getText());
								
								JOptionPane.showMessageDialog(ServiceCreerEtudiant.this, "L'étudiant "
										+ etudiant.getPrenom() + " " + etudiant.getNom() + " a bien été crée.");

								cn.sauverEnBase(fieldId.getText(), etudiant.getNom().toUpperCase(), etudiant.getPrenom().toUpperCase(),
										etudiant.getEmail(), etudiant.getAdresse(), etudiant.getTelephone(),
										etudiant.getDateNaissance());
								
								//On vide tous les champs et on génère un nouveau code Id
								fieldNom.setText("");
								fieldPrenom.setText("");
								fieldDateNaiss.setText("exemple: 01/01/2000");
								fieldDateNaiss.setForeground(Color.GRAY);
								fieldMail.setText("email@exemple.com");
								fieldMail.setForeground(Color.GRAY);
								fieldAdresse.setText("");
								fieldTel.setText("+225 01020304");
								fieldTel.setForeground(Color.GRAY);
								fieldId.setText(getIdAuto(8));

								// dispose();

							}
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException cnfe) {
						cnfe.printStackTrace();

					} finally {

						try {

							connect.close();
							pState.close();

						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(ServiceCreerEtudiant.this,
							"Veuillez renseigner correctement tous les champs", "Message",
							JOptionPane.INFORMATION_MESSAGE);
				}

				// *********
			}

		});

		// Ajout d'une img de fond
		JLabel img_lbl = new JLabel();
		ImageIcon icon = new ImageIcon("images/fond3.png");
		img_lbl.setBounds(new Rectangle(550, 500));
		img_lbl.setIcon(icon);

		// Ajout de nos différents composants graphiques sur notre fenetre
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
		contentPane.add(btnEffacer);
		contentPane.add(btnCreer);

		contentPane.add(img_lbl);

	}
	
	//méthode pour générer un id auto
	public static String getIdAuto(int n) {
		
		//On choisit au hasard un caractère à partir de cette chaine de caractères
		String maChaine = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
		
		StringBuilder sb = new StringBuilder(n);
		
		for (int i = 0; i < n; i++) {
			
			 //index représente un nombre compris entre 0 et la longueur de la chaine de caractères
			int index = (int) (maChaine.length() * Math.random());
			//On ajoute à  notre constructeur de chaine de caractères automatique le caractère se trouvant à l'indice spécifié
			sb.append(maChaine.charAt(index));
		}
		
		return sb.toString();
	}

	public static void main() throws Exception {
		// Modification de l'aperçu avec le lookAndFeelNimbus
		UIManager.setLookAndFeel(new NimbusLookAndFeel());

		ServiceCreerEtudiant sce = new ServiceCreerEtudiant();
		sce.setVisible(true);
		
	}

}
