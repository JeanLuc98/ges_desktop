package gestionetablissementscolaire.presentation;

import java.awt.Color; 
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 * @author Jean Luc Assamoi
 * 
 * @version 1.0 
 *
 * @description Cette première version du logiciel ne peut etre utilisée que par
 *              le responsable des études ou le directeur. Le directeur réalise
 *              les memes taches que le responsable des études, mais a en plus
 *              une fonctionnalité lui permettant de lister l'ensemble des
 *              étudiants de l'établissement.
 */

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField userField = new JTextField();
	private JLabel userlabel = new JLabel();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel passwordLabel = new JLabel();

	private JButton login = new JButton();
	private JButton reset = new JButton();
	private JButton afficher = new JButton("Afficher");
	private JButton masquer = new JButton("Masquer");

	public Main() {
		super("Authentification");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(530, 250);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);

		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(null);

//		JLabel lbl_panel = new JLabel("Authentifiez-vous!");
//		lbl_panel.setBounds(169, 12, 190, 15);
//		lbl_panel.setForeground(Color.BLUE);
//		lbl_panel.setFont(new Font("Callibri", Font.BOLD, 20));

		userlabel.setBounds(80, 50, 75, 15);
		userlabel.setText("Username");
		userlabel.setForeground(Color.LIGHT_GRAY);
		userlabel.setFont(new Font("Callibri", Font.BOLD, 15));
		userField.setBounds(158, 43, 200, 28);

		passwordLabel.setBounds(80, 97, 75, 15);
		passwordLabel.setText("Password");
		passwordLabel.setForeground(Color.LIGHT_GRAY);
		passwordLabel.setFont(new Font("Callibri", Font.BOLD, 15));
		passwordField.setBounds(158, 90, 200, 28);

		afficher.setBounds(370, 92, 80, 25);
		afficher.setForeground(Color.BLUE);
		afficher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				passwordField.setEchoChar((char) 0);
				afficher.setVisible(false);
				masquer.setVisible(true);
			}
		});
		masquer.setBounds(370, 92, 80, 25);
		masquer.setForeground(Color.BLUE);
		masquer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				passwordField.setEchoChar('*');
				masquer.setVisible(false);
				afficher.setVisible(true);
			}
		});

		reset.setBounds(160, 135, 90, 30);
		reset.setText("Reset");
		reset.setForeground(new Color(226, 0, 26));
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userField.setText("");
				passwordField.setText("");
			}
		});

		login.setBounds(258, 135, 90, 30);
		login.setText("Login");
		login.setForeground(Color.BLUE);
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!userField.getText().isEmpty() && !String.valueOf(passwordField.getPassword()).isEmpty()) {

					// Connexion à  la base de données afin de vérifier la valeur de user et password
					String url = "jdbc:mysql://localhost/gestion_scolaire";
					String login = "root";
					String password = "";
					Connection connexion = null;
					PreparedStatement pState = null, pState2 = null;
					ResultSet result = null, result2 = null;

					String sqlRequest = "SELECT * FROM `directeur` WHERE `nom`=? AND `password`=?";
					String sqlRequest2 = "SELECT * FROM `responsableEtudes` WHERE `nom`=? AND `password`=?";

					try {

						Class.forName("com.mysql.jdbc.Driver");
						connexion = DriverManager.getConnection(url, login, password);
						pState = connexion.prepareStatement(sqlRequest);
						pState2 = connexion.prepareStatement(sqlRequest2);

						pState.setString(1, userField.getText());
						pState.setString(2, String.valueOf(passwordField.getPassword()));
						pState2.setString(1, userField.getText());
						pState2.setString(2, String.valueOf(passwordField.getPassword()));

						result = pState.executeQuery();
						result2 = pState2.executeQuery();

						if (result.next()) {
							InterfaceDirecteur application = new InterfaceDirecteur();

							try {
								application.main();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							dispose();

						} else if (result2.next()) {
							// InterfaceDirecteur application = new InterfaceDirecteur();
							InterfaceResponsableEtudes ire = new InterfaceResponsableEtudes();

							try {
								ire.main();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							dispose();

						}

						else {
							JOptionPane.showMessageDialog(Main.this, "Nom d'utilisateur ou mot de passe incorrect",
									"Erreur", JOptionPane.ERROR_MESSAGE);
						}

					} catch (ClassNotFoundException e1) {

						e1.printStackTrace();
					} catch (SQLException e1) {

						e1.printStackTrace();
					} finally {
						try {

							connexion.close();
							pState.close();
							pState2.close();

						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}

				} else {
					JOptionPane.showMessageDialog(Main.this, "Veuillez renseigner tous les champs");
				}

			}
		});

		// Ajout d'une image d'arriÃ¨re plan
		ImageIcon icon = new ImageIcon("images/bombay.jpg");
		JLabel img_lbl = new JLabel();
		img_lbl.setBounds(new Rectangle(530, 250));
		img_lbl.setIcon(icon);

		//panel.add(lbl_panel);
		panel.add(userlabel);
		panel.add(userField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(afficher);
		panel.add(masquer);
		panel.add(reset);
		panel.add(login);

		panel.add(img_lbl);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showConfirmDialog(Main.this, "Voulez-vous quitter ?", "Confirmer",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
	}

	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());

		Main main = new Main();
		main.setVisible(true);
	}

}
