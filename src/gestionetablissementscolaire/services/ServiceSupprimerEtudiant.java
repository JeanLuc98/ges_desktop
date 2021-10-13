package gestionetablissementscolaire.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import gestionetablissementscolaire.domaine.Etudiant;
import gestionetablissementscolaire.jdbc.ConnexionBD;

public class ServiceSupprimerEtudiant extends JFrame {

	JLabel supp_lbl = new JLabel("Sélectionner l'étudiant à supprimer : ");
	JComboBox<String> supp_txt = new JComboBox<>();
	JButton sup = new JButton("Supprimer");
	JButton annul = new JButton("Annuler");
	JButton entrer = new JButton("Entrer");

	JLabel labelForNom = new JLabel();
	public JLabel lbl_nom = new JLabel();
	JLabel labelForPrenom = new JLabel();
	public JLabel lbl_prenom = new JLabel();
	JLabel labelForDateNaiss = new JLabel();
	public JLabel lbl_dateNaiss = new JLabel();
	JLabel labelForMail = new JLabel();
	public JLabel lbl_mail = new JLabel();
	JLabel labelForAdresse = new JLabel();
	public JLabel lbl_adresse = new JLabel();
	JLabel labelForTel = new JLabel();
	public JLabel lbl_tel = new JLabel();
	JLabel labelForId = new JLabel();
	public JLabel lbl_id = new JLabel();

	Etudiant etudiant = new Etudiant();
	ConnexionBD cn = new ConnexionBD();

	String nom;
	String prenom;

	private static final long serialVersionUID = 5498629354255689429L;

	public ServiceSupprimerEtudiant() {
		super("Supprimer un étudiant");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(660, 420);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.lightGray);

		supp_lbl.setBounds(5, 35, 200, 15);
		supp_txt.setBounds(210, 29, 250, 28);
		supp_txt.setEditable(true);

		entrer.setBounds(465, 28, 90, 29);
		entrer.setForeground(Color.BLUE);
		entrer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					if (supp_txt.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(ServiceSupprimerEtudiant.this,
								"Veuillez sélectionner l'étudiant à supprimer");

					} else {

						labelForNom.setText("Nom ");
						labelForNom.setBounds(5, 115, 50, 10);
						lbl_nom.setBounds(185, 107, 250, 28);
						lbl_nom.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForPrenom.setText("Prénom ");
						labelForPrenom.setBounds(5, 140, 60, 10);
						lbl_prenom.setBounds(185, 132, 250, 28);
						lbl_prenom.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForDateNaiss.setText("Date de Naissance ");
						labelForDateNaiss.setBounds(5, 165, 120, 10);
						lbl_dateNaiss.setBounds(185, 157, 250, 28);
						lbl_dateNaiss.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForMail.setText("Email ");
						labelForMail.setBounds(5, 190, 45, 10);
						lbl_mail.setBounds(185, 182, 250, 28);
						lbl_mail.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForAdresse.setText("Adresse ");
						labelForAdresse.setBounds(5, 215, 60, 10);
						lbl_adresse.setBounds(185, 207, 250, 28);
						lbl_adresse.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForTel.setText("Téléphone ");
						labelForTel.setBounds(5, 240, 80, 15);
						lbl_tel.setBounds(185, 232, 250, 28);
						lbl_tel.setFont(new Font("Callibri", Font.BOLD, 16));

						labelForId.setText("Identifiant ");
						labelForId.setBounds(5, 265, 80, 10);
						lbl_id.setBounds(185, 257, 250, 28);
						lbl_id.setFont(new Font("Callibri", Font.BOLD, 16));

						sup.setBounds(330, 325, 120, 28);
						sup.setBackground(new Color(153, 18, 4));
						sup.setForeground(Color.WHITE);
						
						annul.setBounds(180, 325, 120, 28);
						annul.setForeground(Color.BLUE);
						
						annul.setVisible(true);
						sup.setVisible(true);

						// ****************** Connexion Ã  la base de donnÃ©es *********************
						String url = "jdbc:mysql://localhost/gestion_scolaire";
						String login = "root";
						String password = "";
						Connection connect = null;
						Statement state = null;
						ResultSet result = null;

						// recupeartion du nom et du prÃ©nom dans notre combobox
						String[] separateur = supp_txt.getSelectedItem().toString().split(" ", 2);

						nom = separateur[0];
						prenom = separateur[1];

						etudiant.setNom(nom);
						etudiant.setPrenom(prenom);

						try {
							Class.forName("com.mysql.jdbc.Driver");
							connect = DriverManager.getConnection(url, login, password);
							state = connect.createStatement();

							String sql = "SELECT * FROM `etudiant` WHERE `nom` = '" + etudiant.getNom()
									+ "' AND `prenom` = '" + etudiant.getPrenom() + "' ";

							result = state.executeQuery(sql);

							while (result.next()) {

								lbl_nom.setText(result.getString("nom"));
								lbl_prenom.setText(result.getString("prenom"));
								lbl_dateNaiss.setText(result.getString("datedenaissance"));
								lbl_mail.setText(result.getString("email"));
								lbl_adresse.setText(result.getString("adresse"));
								lbl_tel.setText(result.getString("telephone"));
								lbl_id.setText(result.getString("id"));

							}

						} catch (SQLException e1) {

							e1.printStackTrace();
						} catch (ClassNotFoundException e3) {
							e3.printStackTrace();

						} finally {

							try {

								connect.close();
								state.close();

							} catch (SQLException e4) {
								e4.printStackTrace();
							}
						}

					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}

		});

		sup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sup.setBackground(new Color(251, 27, 4));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				sup.setBackground(new Color(153, 18, 4));
			}
		});
		sup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int confirm = JOptionPane.showConfirmDialog(ServiceSupprimerEtudiant.this,
						"Voulez-vous vraiment supprimer cet étudiant ?", "Confirmer suppression",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {

					etudiant.setNom(nom);
					etudiant.setPrenom(prenom);

					JOptionPane.showMessageDialog(ServiceSupprimerEtudiant.this,
							"L'étudiant " + lbl_prenom.getText() + " " + lbl_nom.getText() + " a bien été supprimé");
					cn.supprimerEnBase(etudiant.getNom(), etudiant.getPrenom());

					dispose();
				}

			}
		});
		
		annul.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				supp_txt.setSelectedItem(null);
				
				labelForNom.setText("");
				lbl_nom.setText("");
				labelForPrenom.setText("");
				lbl_prenom.setText("");
				labelForDateNaiss.setText("");
				lbl_dateNaiss.setText("");
				labelForMail.setText("");
				lbl_mail.setText("");
				labelForAdresse.setText("");
				lbl_adresse.setText("");
				labelForTel.setText("");
				lbl_tel.setText("");
				labelForId.setText("");
				lbl_id.setText("");
				
				annul.setVisible(false);
				sup.setVisible(false);
			}
		});

		// Ajout d'une img de fond
		JLabel img_lbl = new JLabel();
		ImageIcon icon = new ImageIcon("images/fond3.png");
		img_lbl.setBounds(new Rectangle(650, 420));
		img_lbl.setIcon(icon);

		contentPane.add(supp_lbl);
		contentPane.add(supp_txt);
		contentPane.add(sup);
		contentPane.add(annul);
		contentPane.add(entrer);

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

	private void etudiantEnBase() {

		// Informations d'accÃ¨s Ã  la base de donnÃ©es
		String url = "jdbc:mysql://localhost/gestion_scolaire";
		String login = "root";
		String password = "";
		Connection connect = null;
		Statement state = null;
		ResultSet result = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url, login, password);
			state = connect.createStatement();

			String sql = "SELECT * FROM etudiant";

			result = state.executeQuery(sql);

			while (result.next()) {
				supp_txt.addItem(result.getString("nom") + " " + result.getString("prenom"));
				supp_txt.setSelectedItem(null);

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

		ServiceSupprimerEtudiant sse = new ServiceSupprimerEtudiant();
		sse.setVisible(true);
	}

}
