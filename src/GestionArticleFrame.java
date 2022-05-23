import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class GestionArticleFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionArticleFrame frame = new GestionArticleFrame();
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
	public GestionArticleFrame() {
		setTitle("Gestion des Articles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 927, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 239, 169);
		contentPane.add(panel);
		
		JLabel lblIdArticle = new JLabel("ID Article");
		lblIdArticle.setBounds(7, 25, 82, 14);
		panel.add(lblIdArticle);
		
		JLabel lblLibell = new JLabel("Libell\u00E9");
		lblLibell.setVerticalAlignment(SwingConstants.TOP);
		lblLibell.setBounds(7, 53, 111, 14);
		panel.add(lblLibell);
		
		JLabel lblPrixHt = new JLabel("Prix HT");
		lblPrixHt.setBounds(6, 81, 115, 14);
		panel.add(lblPrixHt);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(79, 22, 151, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(80, 50, 150, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(80, 78, 150, 20);
		panel.add(textField_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("TVA %");
		lblNewLabel_2_1.setBounds(7, 109, 82, 14);
		panel.add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(79, 106, 151, 20);
		panel.add(textField_3);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Qantit\u00E9 ");
		lblNewLabel_2_1_1.setBounds(7, 137, 82, 14);
		panel.add(lblNewLabel_2_1_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(79, 134, 151, 20);
		panel.add(textField_4);
		
		JButton btnNewButton = new JButton("Registrer");
		btnNewButton.setBounds(10, 191, 89, 23);
		contentPane.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Rechrcher une Article", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(533, 11, 368, 53);
		contentPane.add(panel_1);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(10, 24, 108, 23);
		panel_1.add(btnRechercher);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(128, 25, 230, 20);
		panel_1.add(textField_5);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(249, 65, 652, 115);
		contentPane.add(panel_5);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Libell\u00E9", "Prix HT", "TVA %", "Qantit\u00E9"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(47);
		table.setBounds(10, 25, 632, 75);
		panel_5.add(table);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(249, 191, 99, 23);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(353, 191, 103, 23);
		contentPane.add(btnSupprimer);
	}

}
