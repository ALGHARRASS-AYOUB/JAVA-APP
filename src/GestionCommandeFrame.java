import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

public class GestionCommandeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionCommandeFrame frame = new GestionCommandeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionCommandeFrame() {
		setTitle("Gestion Commande");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 249, 169);
		contentPane.add(panel);
		
		JLabel lblNCommande = new JLabel("N\u00B0 Commande");
		lblNCommande.setBounds(7, 25, 88, 14);
		panel.add(lblNCommande);
		
		JLabel lblLibell = new JLabel("ID Client");
		lblLibell.setVerticalAlignment(SwingConstants.TOP);
		lblLibell.setBounds(7, 53, 111, 14);
		panel.add(lblLibell);
		
		JLabel lblPrixHt = new JLabel("ID Article");
		lblPrixHt.setBounds(6, 81, 115, 14);
		panel.add(lblPrixHt);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(91, 22, 151, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(92, 50, 150, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(92, 78, 150, 20);
		panel.add(textField_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Rechrcher un Commande", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(494, 11, 407, 53);
		contentPane.add(panel_1);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(10, 24, 147, 23);
		panel_1.add(btnRechercher);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(167, 25, 230, 20);
		panel_1.add(textField_5);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(249, 65, 652, 115);
		contentPane.add(panel_5);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"N\u00B0 Commande", "ID Client", "ID Article"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(79);
		table.setBounds(10, 25, 632, 75);
		panel_5.add(table);
		
		JButton btnNewButton = new JButton("Registrer");
		btnNewButton.setBounds(10, 191, 101, 23);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(259, 191, 99, 23);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(368, 191, 110, 23);
		contentPane.add(btnSupprimer);
	}

}
