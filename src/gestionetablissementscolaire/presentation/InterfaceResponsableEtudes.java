package gestionetablissementscolaire.presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import gestionetablissementscolaire.services.ServiceAssocierCoursAEtudiant;
import gestionetablissementscolaire.services.ServiceCreerEtudiant;
import gestionetablissementscolaire.services.ServiceInfosEtudiant;
import gestionetablissementscolaire.services.ServiceModifierEtudiant;
import gestionetablissementscolaire.services.ServiceSupprimerEtudiant;

public class InterfaceResponsableEtudes extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton btnCreerEtudiant = new JButton("CrÈer un Ètudiant");
	private JButton btnAssocierCoursAEtudiant = new JButton("Associer un cours ‡ un Ètudiant");
	private JButton btnLireInfosEtudiant = new JButton("Lire les infos d'un Ètudiant");
	private JButton btnModifierInfosEtudiant = new JButton("Modifier les infos d'un Ètudiant");
	private JButton btnSupprimerEtudiant = new JButton("Supprimer un Ètudiant");

	JPanel espaceAdmin, espaceEtudiants;
	Border borderAdmin, borderEtudiants;
	TitledBorder titleAdmin, titleEtudiants;

	public InterfaceResponsableEtudes() {

		super("Gestion Etablissement Scolaire");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);

		JLabel titre_lbl = new JLabel("GESTION ETABLISSEMENT SCOLAIRE");
		titre_lbl.setBounds(125, 20, 600, 30);
		//titre_lbl.setForeground(new Color(23, 41, 131));
		//titre_lbl.setForeground(Color.CYAN);
		titre_lbl.setFont(new Font("Algerian", 30, 30));
		contentPane.add(titre_lbl);

		JLabel txt_lbl = new JLabel("Espace rÈservÈ au responsable des Ètudes");
		txt_lbl.setBounds(210, 80, 600, 30);
		txt_lbl.setForeground(Color.BLUE);
		txt_lbl.setFont(new Font("Callibri", 30, 20));
		contentPane.add(txt_lbl);

		// Redimensionner nos diff√©rents boutons
		btnCreerEtudiant.setBounds(270, 140, 250, 30);
		btnAssocierCoursAEtudiant.setBounds(270, 180, 250, 30);
		btnLireInfosEtudiant.setBounds(270, 220, 250, 30);
		btnModifierInfosEtudiant.setBounds(270, 260, 250, 30);
		btnSupprimerEtudiant.setBounds(270, 300, 250, 30);

		// On cr√©e diff√©rentes actions sur nos boutons
		btnCreerEtudiant.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceCreerEtudiant sce = new ServiceCreerEtudiant();
				try {
					sce.main();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		btnCreerEtudiant.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreerEtudiant.setForeground(Color.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCreerEtudiant.setForeground(Color.black);
			}
		});

		btnAssocierCoursAEtudiant.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceAssocierCoursAEtudiant sac = new ServiceAssocierCoursAEtudiant();
				try {
					sac.main();
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		btnAssocierCoursAEtudiant.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAssocierCoursAEtudiant.setForeground(Color.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAssocierCoursAEtudiant.setForeground(Color.black);
			}
		});

		btnLireInfosEtudiant.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceInfosEtudiant sie = new ServiceInfosEtudiant();
				try {
					sie.main();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnLireInfosEtudiant.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnLireInfosEtudiant.setForeground(Color.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLireInfosEtudiant.setForeground(Color.black);
			}
		});

		btnModifierInfosEtudiant.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceModifierEtudiant sme = new ServiceModifierEtudiant();
				try {
					sme.main();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnModifierInfosEtudiant.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnModifierInfosEtudiant.setForeground(Color.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnModifierInfosEtudiant.setForeground(Color.black);
			}
		});

		btnSupprimerEtudiant.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceSupprimerEtudiant sse = new ServiceSupprimerEtudiant();
				try {
					sse.main();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnSupprimerEtudiant.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnSupprimerEtudiant.setForeground(Color.blue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSupprimerEtudiant.setForeground(Color.black);
			}
		});

		// Cr√©ation de l'espace administration
		// espaceAdmin();

		// Ajout d'une image d'arri√®re plan
		ImageIcon icon = new ImageIcon("images/bombay.jpg");
		JLabel img_lbl = new JLabel();
		img_lbl.setBounds(new Rectangle(800, 500));
		img_lbl.setIcon(icon);

		contentPane.add(btnCreerEtudiant);
		contentPane.add(btnAssocierCoursAEtudiant);
		contentPane.add(btnLireInfosEtudiant);
		contentPane.add(btnModifierInfosEtudiant);
		contentPane.add(btnSupprimerEtudiant);

		contentPane.add(img_lbl);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showConfirmDialog(InterfaceResponsableEtudes.this,
						"Etes-vous sur de vouloir quitter ?", "Confirmer", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
	}

	/**
	 * On cr√©e une action au niveau de nos diff√©rents boutons. Lorsqu'on clique sur
	 * un bouton, on ouvre une nouvelle fenetre qui nous permettra bien √©videmment
	 * d'effectuer la tache associ√©e √† ce bouton.
	 * 
	 */

	public void main() throws Exception {

		// Modification de l'aper√ßu avec le NimbusLookAndFeel
		UIManager.setLookAndFeel(new NimbusLookAndFeel());

		// on instancie notre classe
		InterfaceResponsableEtudes application = new InterfaceResponsableEtudes();

		// On la rend visible
		application.setVisible(true);

	}

}
