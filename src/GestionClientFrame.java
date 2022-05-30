import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.attribute.AclEntry;
import java.security.KeyStore.TrustedCertificateEntry;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GestionClientFrame extends JFrame  {

	private JPanel contentPaneClients;
	private JTextField idClient;
	private JTextField addLivraison;
	private JTextField addFacturation;
	private JTextField emailF;
	private JTextField chercherInput;
	private JTextField nom;
	private JTextField prenom;
	private JTextField nomEse;
	private JTextField statusF;
	private JTextField noF;
	private JTable table;
	private JTextField date;
	private JTextField tel;
	private JTextField nssF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	
	 java.sql.PreparedStatement st;
	 String by,inCol,gestionPaneOption;
	 
	 int id;
	int no=0;
	 Sexe genre = null;
	 String sname=null,name = null,addL,addF,telnum,email,nss=null,status = null,dateN = null;
	 
	 Connection connexion=MainFrame.cx;
	 ResultSet result;
	 ResultSetMetaData metadata;
	/**
	 * Create the frame.
	 */
	public GestionClientFrame() {

		

		
		setTitle("Gestion Clients");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPaneClients = new JPanel();
		contentPaneClients.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneClients);
		contentPaneClients.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 408, 505);
		contentPaneClients.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID Client");
		lblNewLabel.setBounds(7, 25, 82, 14);
		panel.add(lblNewLabel);
		
		JLabel lblModifier = new JLabel("Adresse De Livraison");
		lblModifier.setVerticalAlignment(SwingConstants.TOP);
		lblModifier.setBounds(7, 53, 160, 14);
		panel.add(lblModifier);
		
		JLabel lblRechercher = new JLabel("Adresse De Facturation");
		lblRechercher.setBounds(3, 81, 164, 14);
		panel.add(lblRechercher);
		
		idClient = new JTextField();
		idClient.setText("auto");
		idClient.setEditable(false);
//		idClient.setEnabled(false);
		idClient.setToolTipText("");
		idClient.setBounds(189, 22, 200, 20);
		panel.add(idClient);
		idClient.setColumns(10);
		
		addLivraison = new JTextField();
		addLivraison.setColumns(10);
		addLivraison.setBounds(190, 50, 199, 20);
		panel.add(addLivraison);
		
		addFacturation = new JTextField();
		addFacturation.setColumns(10);
		addFacturation.setBounds(190, 78, 199, 20);
		panel.add(addFacturation);
		
		JLabel lblNewLabel_2_1 = new JLabel("Email");
		lblNewLabel_2_1.setBounds(7, 109, 82, 14);
		panel.add(lblNewLabel_2_1);
		
		emailF = new JTextField();
		emailF.setColumns(10);
		emailF.setBounds(189, 106, 200, 20);
		panel.add(emailF);
		
		JPanel physicPanel = new JPanel();
		physicPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		physicPanel.setBounds(3, 184, 192, 276);
		panel.add(physicPanel);
		physicPanel.setVisible(true);
		physicPanel.setLayout(null);
		
		
		JPanel entreprisePanel = new JPanel();
		entreprisePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		entreprisePanel.setBounds(199, 185, 201, 203);
		panel.add(entreprisePanel);
		entreprisePanel.setVisible(false);
		entreprisePanel.setLayout(null);


		
		String []clientOption= {"client Physique","Entreprise"};
		JComboBox clientType = new JComboBox(clientOption);
		by="client Physique";
		clientType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				by=(String) clientType.getSelectedItem();
				if(by.equals("client Physique")) {
					physicPanel.setVisible(true);
					entreprisePanel.setVisible(false);
					
				}
				if(by.equals("Entreprise")) {
					physicPanel.setVisible(false);
					entreprisePanel.setVisible(true);
					
				}
				System.out.println(by);
			}
		});
		clientType.setBounds(7, 157, 160, 22);
		panel.add(clientType);
		

		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(6, 27, 42, 14);
		physicPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom");
		lblNewLabel_1_1.setBounds(5, 52, 54, 14);
		physicPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel(" Date naissance");
		lblNewLabel_1_2.setBounds(7, 82, 118, 14);
		physicPanel.add(lblNewLabel_1_2);
		
		nom = new JTextField();
		nom.setBounds(52, 25, 135, 20);
		physicPanel.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(52, 50, 135, 20);
		physicPanel.add(prenom);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Genre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(6, 132, 173, 46);
		physicPanel.add(panel_4);
		panel_4.setLayout(null);
		
		JRadioButton genreM = new JRadioButton("masculin");
		genreM.setSelected(true);
		genreM.setBounds(77, 16, 90, 23);
		panel_4.add(genreM);
		
		JRadioButton genreF = new JRadioButton("feminin");
		genreF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				genreM.setSelected(false);
			}
		});
		genreF.setBounds(6, 16, 69, 23);
		panel_4.add(genreF);
		genreM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				genreF.setSelected(false);
			}
		});
		
		date = new JTextField();
		date.setColumns(10);
		date.setBounds(24, 102, 162, 20);
		physicPanel.add(date);
		
		nssF = new JTextField();
		nssF.setColumns(10);
		nssF.setBounds(52, 208, 135, 20);
		physicPanel.add(nssF);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("NSS");
		lblNewLabel_1_1_1.setBounds(5, 211, 54, 14);
		physicPanel.add(lblNewLabel_1_1_1);
		

		
		nomEse = new JTextField();
		nomEse.setColumns(10);
		nomEse.setBounds(57, 26, 135, 20);
		entreprisePanel.add(nomEse);
		
		JLabel lblNewLabel_1_3 = new JLabel("Nom");
		lblNewLabel_1_3.setBounds(10, 29, 42, 14);
		entreprisePanel.add(lblNewLabel_1_3);
		
		statusF = new JTextField();
		statusF.setColumns(10);
		statusF.setBounds(57, 57, 135, 20);
		entreprisePanel.add(statusF);
		
		JLabel lblNewLabel_1_4 = new JLabel("Status");
		lblNewLabel_1_4.setBounds(10, 60, 58, 14);
		entreprisePanel.add(lblNewLabel_1_4);
		
		noF = new JTextField();
		noF.setColumns(10);
		noF.setBounds(103, 88, 86, 20);
		entreprisePanel.add(noF);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("N\u00B0 National");
		lblNewLabel_1_4_1.setBounds(9, 91, 93, 14);
		entreprisePanel.add(lblNewLabel_1_4_1);

		
		
		JButton register = new JButton("Registrer");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				 
				 addL=addLivraison.getText();
				 addF=addFacturation.getText();
				 email=GestionClientFrame.this.emailF.getText();
				 telnum=tel.getText();
				 
				 
/*				 --------------- CLIENT PHUSIQUE -------------------*/ 
				
				 if(by.equals("client Physique")) {
					 name=nom.getText();
					 sname=prenom.getText();
					 email=GestionClientFrame.this.emailF.getText();
					 dateN=date.getText();
					 if(genreF.isSelected())
						 genre=Sexe.feminin;
					 if(genreM.isSelected())
						 genre=Sexe.masculin;
					 nss=nssF.getText();
				 }

	/*--------------------------------------------------------------------------*/
				 /*------------------------------------- client entreprise -------------------------------------*/
				 
				 else if(by.equals("Entreprise")) {
					 name=nomEse.getText();
					 sname=null;
					 email=GestionClientFrame.this.emailF.getText();
					 status=GestionClientFrame.this.statusF.getText();
					 no=Integer.parseInt(GestionClientFrame.this.noF.getText());
					 nss=null;dateN=null;genre=null;
				}
				 
				 else {
					 
					 JOptionPane.showMessageDialog(register,"faillure .... !");
				 }
				 /*--------------------------------------------------------------------------*/
				
				
				//les requetes
				try {
					st=connexion.prepareStatement("INSERT INTO `clients`(`nom`,`prenom`, `tel`, `email`, `dateNaissance`, `genre`, `addresseFacturation`, `addresseLivraison`, `nss`, `status`, `numeroNational`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
					st.setString(1, name); st.setString(2, sname);	st.setString(3, telnum); st.setString(4, email);
					st.setString(5, dateN);	st.setString(6, String.valueOf(genre)); st.setString(7, addF);	st.setString(8, addL);	st.setString(9, nss);	st.setString(10, status);	st.setInt(11, no);
					st.executeUpdate();
					
					
					JOptionPane.showMessageDialog(register,"registered");
					addLivraison.setText(""); addFacturation.setText(""); GestionClientFrame.this.emailF.setText(""); tel.setText("");
					nom.setText("");	prenom.setText("");GestionClientFrame.this.emailF.setText("");date.setText("");genreF.setSelected(false);genreM.setSelected(false);
					nssF.setText("");	nomEse.setText("");GestionClientFrame.this.statusF.setText("");	GestionClientFrame.this.noF.setText("");	addLivraison.requestFocus();

				} catch (SQLException ex) {
					ex.printStackTrace();	

				}
			}
		});
		

		register.setBounds(149, 471, 89, 23);
		panel.add(register);
		
		tel = new JTextField();
		tel.setColumns(10);
		tel.setBounds(189, 134, 200, 20);
		panel.add(tel);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("tel");
		lblNewLabel_2_1_1.setBounds(7, 137, 82, 14);
		panel.add(lblNewLabel_2_1_1);
		

		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Rechrcher un client ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(513, 11, 518, 53);
		contentPaneClients.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						st=connexion.prepareStatement("select * from clients where "+inCol+"="+(String)chercherInput.getText());
						chercher(st);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnRechercher.setBounds(10, 24, 102, 23);
		panel_1.add(btnRechercher);
		
		chercherInput = new JTextField();
		chercherInput.setColumns(10);
		chercherInput.setBounds(139, 25, 265, 20);
		panel_1.add(chercherInput);
		String []options= {"id","nom","nss","no"};
		JComboBox comboBox = new JComboBox(options);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inCol=(String) comboBox.getSelectedItem();
				System.out.println(inCol);
			}
		});
		comboBox.setBounds(407, 24, 92, 22);
		panel_1.add(comboBox);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(408, 85, 776, 431);
		contentPaneClients.add(panel_5);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 24, 756, 396);
		panel_5.add(scrollPane);
		//		------------------------------------------------------------------------------------------------------------
		//		--------------------------------------- get Values into fields  ---------------------------------------------------------------------
				table = new JTable();
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						DefaultTableModel Df=(DefaultTableModel) table.getModel();
						try {
							idClient.setText(Df.getValueAt(table.getSelectedRow(), 0).toString());
							addLivraison.setText(Df.getValueAt(table.getSelectedRow(), 8).toString());
							addFacturation.setText(Df.getValueAt(table.getSelectedRow(), 7).toString());
							tel.setText(Df.getValueAt(table.getSelectedRow(), 3).toString());
							emailF.setText(Df.getValueAt(table.getSelectedRow(), 4).toString());
							if(by.equals("client Physique")) {
								
								nomEse.setText("");
								statusF.setText("");
								noF.setText("");
								nom.setText(Df.getValueAt(table.getSelectedRow(), 1).toString());
								prenom.setText(Df.getValueAt(table.getSelectedRow(), 2).toString());
								date.setText(Df.getValueAt(table.getSelectedRow(), 5).toString());
								if (Df.getValueAt(table.getSelectedRow(),6).toString().equals("feminin"))
									genreF.setSelected(true);
								else 
									genreM.setSelected(true);
								nssF.setText(Df.getValueAt(table.getSelectedRow(), 9).toString());								
							}
							 if(by.equals("Entreprise")) {
								 nom.setText("");
								 prenom.setText("");
								 genreF.setSelected(false);
								 genreM.setSelected(false);
								 nssF.setText("");
								 date.setText("");
								 nomEse.setText(Df.getValueAt(table.getSelectedRow(), 1).toString());
								 statusF.setText(Df.getValueAt(table.getSelectedRow(), 10).toString());
								 noF.setText(Df.getValueAt(table.getSelectedRow(), 11).toString());
							 }
							
						} catch (NullPointerException ne) {
							
						}
					}
				});
				//		------------------------------------------------------------------------------------------------------------
						table.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, "", null, null, "", null, null, null, null, null},
								{null, null, null, null, null, null, null, null, null, null, null, null},
								{null, null, null, null, null, null, null, null, null, null, null, null},
								{null, null, null, null, null, null, null, null, null, null, null, null},
								{null, null, null, null, null, null, null, null, null, null, null, null},
							},
							new String[] {
								"ID", "nom", "prenom", "tel", "Email", "Date de naissance", "Genre", "adresse de livraison", "adresse de facture", "NSS", "Status juridique", "numero national"
							}
						) {
							Class[] columnTypes = new Class[] {
								Integer.class, String.class, String.class, String.class, String.class, Object.class, String.class, String.class, String.class, String.class, String.class, Integer.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
						table.getColumnModel().getColumn(0).setPreferredWidth(38);
						table.getColumnModel().getColumn(1).setPreferredWidth(83);
						table.getColumnModel().getColumn(2).setPreferredWidth(83);
						table.getColumnModel().getColumn(3).setPreferredWidth(80);
						table.getColumnModel().getColumn(4).setPreferredWidth(80);
						table.getColumnModel().getColumn(6).setPreferredWidth(61);
						table.getColumnModel().getColumn(7).setPreferredWidth(100);
						table.getColumnModel().getColumn(8).setPreferredWidth(101);
						table.getColumnModel().getColumn(9).setPreferredWidth(61);
						table.getColumnModel().getColumn(10).setPreferredWidth(60);
						table.getColumnModel().getColumn(11).setPreferredWidth(50);
						table.getColumnModel().getColumn(11).setMaxWidth(68);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel Df=(DefaultTableModel) table.getModel();
				int rowIndex=table.getSelectedRow();
				
				try {
					
					 id=Integer.parseInt(GestionClientFrame.this.idClient.getText());
					 addL=addLivraison.getText();
					 addF=addFacturation.getText();
					 email=GestionClientFrame.this.emailF.getText();
					 telnum=tel.getText();
					 
					 
	/*				 --------------- CLIENT PHUSIQUE -------------------*/ 
					
					 if(by.equals("client Physique")) {
						 name=nom.getText();
						 sname=prenom.getText();
						 email=GestionClientFrame.this.emailF.getText();
						 dateN=date.getText();
						 if(genreF.isSelected())
							 genre=Sexe.feminin;
						 if(genreM.isSelected())
							 genre=Sexe.masculin;
						 nss=nssF.getText();
					 }

		/*--------------------------------------------------------------------------*/
					 /*------------------------------------- client entreprise -------------------------------------*/
					 
					 else if(by.equals("Entreprise")) {
						 name=nomEse.getText();
						 sname=null;
						 email=GestionClientFrame.this.emailF.getText();
						 status=GestionClientFrame.this.statusF.getText();
						 no=Integer.parseInt(GestionClientFrame.this.noF.getText());
						 nss=null;dateN=null;genre=null;
				
					 }

		/*--------------------------------------------------------------------------*/
					 /*------------------------------------- client entreprise -------------------------------------*/
					 
					 else if(by.equals("Entreprise")) {
						 name=nomEse.getText();
						 status=GestionClientFrame.this.statusF.getText();
						 no=Integer.parseInt(GestionClientFrame.this.noF.getText());
					}
					 
					 else {
						 
						 JOptionPane.showMessageDialog(register,"faillure .... !");
					 }
					 /*--------------------------------------------------------------------------*/
					
					st=connexion.prepareStatement("UPDATE `clients` SET `id`=?,`nom`=?,`prenom`=?,`tel`=?,`email`=?,`dateNaissance`=?,`genre`=?,`addresseFacturation`=?,`addresseLivraison`=?,`nss`=?,`status`=?,`numeroNational`=? where id="+id);
					st.setString(2, name); st.setString(3, sname);	st.setString(4, telnum); st.setString(5, email);
					st.setString(6, dateN);	st.setString(7, String.valueOf(genre)); st.setString(8, addF);	st.setString(9, addL);	st.setString(10, nss);	st.setString(11, status);	st.setInt(12, no); 
					st.setInt(1, id);
					st.executeUpdate();
					
					
					JOptionPane.showMessageDialog(btnModifier,"modifié");
					addLivraison.setText(""); addFacturation.setText(""); GestionClientFrame.this.emailF.setText(""); tel.setText("");
					nom.setText("");	prenom.setText("");GestionClientFrame.this.emailF.setText("");date.setText("");genreF.setSelected(false);genreM.setSelected(false);
					nssF.setText("");	nomEse.setText("");GestionClientFrame.this.statusF.setText("");	GestionClientFrame.this.noF.setText("");	addLivraison.requestFocus();

				} catch (SQLException ex) {
					ex.printStackTrace();	

				}
				
			}
		});
		btnModifier.setBounds(418, 527, 102, 23);
		contentPaneClients.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel Df=(DefaultTableModel) table.getModel();
				int rowIndex=table.getSelectedRow();
				
				try {					
					 id=Integer.parseInt(GestionClientFrame.this.idClient.getText());

					 /*--------------------------------------------------------------------------*/
					
					st=connexion.prepareStatement("delete from clients where id="+id);
					st.executeUpdate();
					
					
					int dialogResult=JOptionPane.showConfirmDialog(null,"tu veux supprimer ce client ?","Attention",JOptionPane.YES_NO_OPTION);
					addLivraison.setText(""); addFacturation.setText(""); GestionClientFrame.this.emailF.setText(""); tel.setText("");
					nom.setText("");	prenom.setText("");GestionClientFrame.this.emailF.setText("");date.setText("");genreF.setSelected(false);genreM.setSelected(false);
					nssF.setText("");	nomEse.setText("");GestionClientFrame.this.statusF.setText("");	GestionClientFrame.this.noF.setText("");	addLivraison.requestFocus();

				} catch (SQLException ex) {
					ex.printStackTrace();	

				}
				
			}
		});
		btnSupprimer.setBounds(530, 527, 102, 23);
		contentPaneClients.add(btnSupprimer);
		
		JButton displayAllBtn = new JButton("Afficher tous");
		displayAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 		try {
							st=connexion.prepareStatement("select * from clients");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						chercher(st);
					 }
			
		});
		displayAllBtn.setBounds(917, 65, 114, 16);
		contentPaneClients.add(displayAllBtn);
		JComboBox gestionCombox = new JComboBox();
		gestionCombox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionPaneOption=(String) gestionCombox.getSelectedItem();
				System.out.println(gestionPaneOption);
				if(gestionPaneOption.equals("Gestion Articles")) {
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
		gestionCombox.setModel(new DefaultComboBoxModel(new String[] {"Gestion Clients", "Gestion Articles", "Gestion Commandes"}));
		gestionCombox.setBounds(1037, 7, 147, 22);
		contentPaneClients.add(gestionCombox);
		
		
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
					
					vect.add(result.getString("id"));
					vect.add(result.getString("nom"));
					vect.add(result.getString("prenom"));
					vect.add(result.getString("tel"));
					vect.add(result.getString("email"));
					vect.add(result.getString("dateNaissance"));
					vect.add(result.getString("genre"));
					vect.add(result.getString("addresseFacturation"));
					vect.add(result.getString("addresseLivraison"));
					vect.add(result.getString("nss"));
					vect.add(result.getString("status"));
					vect.add(result.getString("numeroNational"));
				}
				
				Df.addRow(vect);	
			}	
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			Df.removeRow(id);	
		}
	}

}
