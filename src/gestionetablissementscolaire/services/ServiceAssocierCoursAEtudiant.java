package gestionetablissementscolaire.services;

import java.awt.Color;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import gestionetablissementscolaire.domaine.Cours;

public class ServiceAssocierCoursAEtudiant extends JFrame {

	private static final long serialVersionUID = -1596203714750936059L;

	List<Cours> listeDesCours = new ArrayList<>();

	JButton btnAssocier = new JButton("Associer");
	JButton btnAnnuler = new JButton("Annuler");

	JLabel labelTheme = new JLabel("Thème du cours : ");
	JComboBox<String> mesCours = new JComboBox<>();

	JLabel labelForNom = new JLabel("Nom de l'étudiant : ");
	JComboBox<String> nom = new JComboBox<>();

	public ServiceAssocierCoursAEtudiant() {
		super("Associer un cours à un étudiant");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.lightGray);

		labelTheme.setBounds(5, 50, 150, 10);
		mesCours.setBounds(140, 45, 200, 22);
		mesCours.setEditable(true);

		AutoCompleteDecorator.decorate(mesCours);

		labelForNom.setBounds(5, 100, 150, 10);
		nom.setBounds(140, 95, 200, 22);
		nom.setEditable(true);
		AutoCompleteDecorator.decorate(nom);

		btnAnnuler.setBounds(70, 200, 120, 40);
		btnAnnuler.setForeground(Color.blue);
		btnAnnuler.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAnnuler.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAnnuler.setForeground(Color.blue);
			}
		});

		btnAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(ServiceAssocierCoursAEtudiant.this,
						"Etes-vous sur de vouloir annuler ?", "Annuler", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
				}

			}
		});

		btnAssocier.setBounds(200, 200, 120, 40);
		btnAssocier.setForeground(Color.blue);
		btnAssocier.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAssocier.setForeground(Color.GREEN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAssocier.setForeground(Color.blue);
			}
		});

		btnAssocier.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (mesCours.getSelectedItem() != null && nom.getSelectedItem() != null) {
					
					JOptionPane.showMessageDialog(ServiceAssocierCoursAEtudiant.this, "Le cours de "
							+ mesCours.getSelectedItem() + " a bien été associé à  l'étudiant " + nom.getSelectedItem());
					dispose();

				} else {
					JOptionPane.showMessageDialog(ServiceAssocierCoursAEtudiant.this,
							"Veuillez renseigner tous les champs");
				}

			}
		});

		// Ajout d'une img de fond
		JLabel img_lbl = new JLabel();
		ImageIcon icon = new ImageIcon("images/fond3.png");
		img_lbl.setBounds(new Rectangle(400, 300));
		img_lbl.setIcon(icon);

		contentPane.add(labelTheme);
		contentPane.add(mesCours);
		contentPane.add(labelForNom);
		contentPane.add(nom);
		contentPane.add(btnAnnuler);
		contentPane.add(btnAssocier);

		contentPane.add(img_lbl);

		etudiantEnBase();
		coursEnBase();
	}

	private void etudiantEnBase() {
		// Informations d'accès à la base de données
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
				nom.addItem(result.getString("nom") + " " + result.getString("prenom"));
				nom.setSelectedItem(null);

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

	private void coursEnBase() {
		// Informations d'accès à la base de données
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

			String sql = "SELECT * FROM cours";

			result = state.executeQuery(sql);

			while (result.next()) {
				mesCours.addItem(result.getString("themeducours"));
				mesCours.setSelectedItem(null);

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

	public static void main() {
		ServiceAssocierCoursAEtudiant window = new ServiceAssocierCoursAEtudiant();
		window.setVisible(true);

	}

}
