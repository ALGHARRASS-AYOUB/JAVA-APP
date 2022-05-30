import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionArticleFrame extends JFrame {

	private JPanel contentPaneAritcles;
	private JTextField id_F;
	private JTextField libel_F;
	private JTextField prixHT_F;
	private JTextField TVA_F;
	private JTextField Qte_F;
	private JTextField search_F;
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

	Connection connexion = MainFrame.cx;
	 java.sql.PreparedStatement st;
	 String by,inCol,gestionPaneOption;
	 
	 int id_article;
	 String libel;
	 int prixHt,TVA,Qte;
	 
	 String optionSearch="id_article",searchBy;

	 ResultSet result;
	 ResultSetMetaData metadata;
	
	/**
	 * Create the frame.
	 */
	public GestionArticleFrame() {
//		connectDB();
		setTitle("Gestion des Articles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1033, 465);
		contentPaneAritcles = new JPanel();
		contentPaneAritcles.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneAritcles);
		contentPaneAritcles.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 239, 207);
		contentPaneAritcles.add(panel);
		
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
		
		id_F = new JTextField();
		id_F.setEditable(false);
		id_F.setText("auto");
		id_F.setColumns(10);
		id_F.setBounds(79, 22, 151, 20);
		panel.add(id_F);
		
		libel_F = new JTextField();
		libel_F.setColumns(10);
		libel_F.setBounds(80, 50, 150, 20);
		panel.add(libel_F);
		
		prixHT_F = new JTextField();
		prixHT_F.setColumns(10);
		prixHT_F.setBounds(80, 78, 111, 20);
		panel.add(prixHT_F);
		
		JLabel lblNewLabel_2_1 = new JLabel("TVA %");
		lblNewLabel_2_1.setBounds(7, 109, 82, 14);
		panel.add(lblNewLabel_2_1);
		
		TVA_F = new JTextField();
		TVA_F.setText("20");
		TVA_F.setColumns(10);
		TVA_F.setBounds(79, 106, 111, 20);
		panel.add(TVA_F);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Qantit\u00E9 ");
		lblNewLabel_2_1_1.setBounds(7, 137, 82, 14);
		panel.add(lblNewLabel_2_1_1);
		
		Qte_F = new JTextField();
		Qte_F.setColumns(10);
		Qte_F.setBounds(79, 134, 151, 20);
		panel.add(Qte_F);
		
		JButton btnRegistrer = new JButton("Registrer");
		btnRegistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				libel=libel_F.getText();
				prixHt=Integer.parseInt(prixHT_F.getText());
				TVA=Integer.parseInt(TVA_F.getText());
				Qte=Integer.parseInt(Qte_F.getText());
				
				//les requetes
				try {
					st=connexion.prepareStatement("INSERT INTO `articles`(`libelle`, `prix HT`, `TVA`, `quantite`) VALUES (?,?,?,?)");
					st.setString(1, libel);	st.setInt(2, prixHt); st.setInt(3, TVA);st.setInt(4,Qte);
					st.executeUpdate();
					
					
					JOptionPane.showMessageDialog( btnRegistrer,"registered");
					
					libel_F.setText("");prixHT_F.setText("");TVA_F.setText("");Qte_F.setText("");
					
				} catch (SQLException ex) {
					ex.printStackTrace();	
					JOptionPane.showMessageDialog( btnRegistrer,"failled .... ");
				}
				
				
				
				
			}
		});
		btnRegistrer.setBounds(140, 173, 89, 23);
		panel.add(btnRegistrer);
		
		JLabel lblNewLabel = new JLabel("DH");
		lblNewLabel.setBounds(199, 81, 30, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Rechrcher une Article", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(396, 11, 456, 53);
		contentPaneAritcles.add(panel_1);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					st=connexion.prepareStatement("select * from articles where "+optionSearch+"="+search_F.getText());
					chercher(st);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRechercher.setBounds(10, 24, 108, 23);
		panel_1.add(btnRechercher);
		
		search_F = new JTextField();
		search_F.setColumns(10);
		search_F.setBounds(128, 25, 196, 20);
		panel_1.add(search_F);
		
		JComboBox optionsInSearch = new JComboBox();
		optionsInSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionSearch=(String) optionsInSearch.getSelectedItem();
			}
		});
		optionsInSearch.setModel(new DefaultComboBoxModel(new String[] {"id_article", "libelle"}));
		optionsInSearch.setBounds(350, 24, 97, 22);
		panel_1.add(optionsInSearch);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(249, 84, 768, 302);
		contentPaneAritcles.add(panel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 748, 266);
		panel_5.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel Df=(DefaultTableModel) table.getModel();
				try {
					id_F.setText(Df.getValueAt(table.getSelectedRow(), 0).toString());
					libel_F.setText(Df.getValueAt(table.getSelectedRow(), 1).toString());
					prixHT_F.setText(Df.getValueAt(table.getSelectedRow(), 2).toString());
					TVA_F.setText(Df.getValueAt(table.getSelectedRow(), 3).toString());
					Qte_F.setText(Df.getValueAt(table.getSelectedRow(), 4).toString());
					
				} catch (NullPointerException ne) {
					throw new NullPointerException();
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			
				{null, null, null, null, null},
				{null, null, null, "", null},
			},
			new String[] {
				"ID", "Libell\u00E9", "Prix HT", "TVA %", "Qantit\u00E9"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(47);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id_article=Integer.parseInt(id_F.getText());
				libel=libel_F.getText();
				prixHt=Integer.parseInt(prixHT_F.getText());
				TVA=Integer.parseInt(TVA_F.getText());
				Qte=Integer.parseInt(Qte_F.getText());
				try {
					st=connexion.prepareStatement("UPDATE `articles` SET `id_article`=?,`libelle`=?,`prix HT`=?,`TVA`=?,`quantite`=? where id_article="+id_article);
					st.setInt(1, id_article);st.setString(2, libel); st.setInt(3, prixHt);	st.setInt(4, TVA); st.setInt(5, Qte);
					st.executeUpdate();
					JOptionPane.showMessageDialog(btnModifier, "modifié .");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(btnModifier, "modification echouée ! .");
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setBounds(257, 392, 99, 23);
		contentPaneAritcles.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df=(DefaultTableModel) table.getModel();
				int rowIndex=table.getSelectedRow();
				
				try {					
					 id_article=Integer.parseInt(id_F.getText());
					
					st=connexion.prepareStatement("delete from articles where id_article="+id_article);
					st.executeUpdate();
					
					
					int dialogResult=JOptionPane.showConfirmDialog(null," veux tu supprimer cette article ?","Attention",JOptionPane.YES_NO_OPTION);
					id_F.setText(""); prixHT_F.setText(""); TVA_F.setText(""); Qte_F.setText("");
					
				} catch (SQLException ex) {
					ex.printStackTrace();	

				}
			}
			
		});
		btnSupprimer.setBounds(366, 392, 103, 23);
		contentPaneAritcles.add(btnSupprimer);
		
		JButton displayAllBtn = new JButton("Afficher tous");
		displayAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 		try {
							st=connexion.prepareStatement("select * from articles");
							chercher(st);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					 }
			
		});
		displayAllBtn.setBounds(721, 68, 131, 16);
		contentPaneAritcles.add(displayAllBtn);
		
		JComboBox gestionCombox = new JComboBox();
		gestionCombox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionPaneOption=(String) gestionCombox.getSelectedItem();
				System.out.println(gestionPaneOption);
				if(gestionPaneOption.equals("Gestion Clients")) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								GestionClientFrame frame = new GestionClientFrame();
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
				}
					if(gestionPaneOption.equals("Gestion Commandes")) {
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
					dispose();
			}
		});
		gestionCombox.setModel(new DefaultComboBoxModel(new String[] {"Gestion Clients", "Gestion Commandes"}));
		gestionCombox.setBounds(869, 4, 148, 22);
		contentPaneAritcles.add(gestionCombox);
		

	}
void  chercher(java.sql.PreparedStatement st) {
		

		//les requetes
		DefaultTableModel Df=(DefaultTableModel) table.getModel();
		try {
			result=st.executeQuery();
			System.out.println("the result of search : "+result);
			metadata=result.getMetaData();
			
			Df.setRowCount(0);
//			System.out.println(result);
			while(result.next()) {
				Vector vect=new Vector();
				for(int i=1;i<=metadata.getColumnCount();i++) {
					
					vect.add(result.getString("id_article"));
					vect.add(result.getString("libelle"));
					vect.add(result.getString("prix HT"));
					vect.add(result.getString("TVA"));
					vect.add(result.getString("quantite"));
						}
				
				Df.addRow(vect);	
			}	
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			Df.removeRow(id_article);	
		}
	}
}

