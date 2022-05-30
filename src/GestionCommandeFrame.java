import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;


import com.mysql.jdbc.PreparedStatement;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;

public class GestionCommandeFrame extends JFrame {

	private JPanel contentPaneCommandes;
	private JTextField id_commande_F;
	private JTextField id_client_F;
	private JTextField id_article_F;
	private JTextField searchCommande_F;
	private JTable commandes_T;
	private JTextField searchClient_F;
	private JTable clients_T;

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
	
	Connection connexion=MainFrame.cx;
	DefaultTableModel DfA;
	 
	java.sql.PreparedStatement st;
	ResultSet result;
	 ResultSetMetaData metadata;
	
	String inColClients="id",inColArticles="id_article",inColCommande="id_commande",dateString;
	Integer id_client=null,id_article=null,id_commande=null;
	String addrF=null,name=null,email=null;
	int noN,idClient;
	long sumHT=0,TTC=0;
	String libelle=null;
	int prix;
	int tva;
	int quantite;
	
	
	private JTextField searchArticle_F;
	private JTable articles_T;
	private JTextField date_F;
	private JTable facture_T;
	
	/**
	 * Create the frame.
	 */
	public GestionCommandeFrame() {
		setTitle("Gestion Commande");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(010, 010, 1322, 687);
		contentPaneCommandes = new JPanel();
		contentPaneCommandes.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneCommandes);
		contentPaneCommandes.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 7, 1288, 623);
		contentPaneCommandes.add(tabbedPane);
		
		JTabbedPane Clients_P = new JTabbedPane(JTabbedPane.TOP);
		Clients_P.add(new GestionClientFrame().getContentPane());
		
		tabbedPane.addTab("Clients", null, Clients_P, null);
		
		JTabbedPane Articles_P = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Articles", null, Articles_P, null);
		Articles_P.add(new GestionArticleFrame().getContentPane());
		
		JPanel commande_P = new JPanel();
		tabbedPane.addTab("Commandes", null, commande_P, null);
		commande_P.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 431, 267, 164);
		commande_P.add(panel);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
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
		
		id_commande_F = new JTextField();
		id_commande_F.setText("auto");
		id_commande_F.setEditable(false);
		id_commande_F.setColumns(10);
		id_commande_F.setBounds(91, 22, 151, 20);
		panel.add(id_commande_F);
		
		id_client_F = new JTextField();
		id_client_F.setColumns(10);
		id_client_F.setBounds(92, 50, 150, 20);
		panel.add(id_client_F);
		
		id_article_F = new JTextField();
		id_article_F.setColumns(10);
		id_article_F.setBounds(92, 78, 150, 20);
		panel.add(id_article_F);
		
		JLabel idClient_L = new JLabel("-");
		JLabel name_L = new JLabel("-");
		JLabel addr_L = new JLabel("-");

		
		JButton commanderBtn = new JButton("passer Commande");
		commanderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)throws NumberFormatException {
			      java.util.Date date = new java.util.Date();
			      long t = date.getTime();
			      java.sql.Date sqlDate = new java.sql.Date(t);
			      java.sql.Time sqlTime = new java.sql.Time(t);
				java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);
				dateString=sqlTimestamp.toString();
				if(quantite==0) 
					JOptionPane.showMessageDialog(commanderBtn, "article non valable dans le stock");
				else {
					try {
						id_article=Integer.parseInt(GestionCommandeFrame.this.id_article_F.getText());
						id_client=Integer.parseInt(GestionCommandeFrame.this.id_client_F.getText());					
						}catch(NumberFormatException ex)  {ex.printStackTrace()	;}

				 /*--------------------------------------------------------------------------*/
				
				//les requetes
					
					try {
						st=connexion.prepareStatement("UPDATE `articles` SET `id_article`=?,`libelle`=?,`prix HT`=?,`TVA`=?,`quantite`=? where id_article="+id_article);
						st.setInt(1, id_article);st.setString(2, libelle); st.setInt(3, prix);	st.setInt(4, tva); st.setInt(5, quantite-1);
						st.executeUpdate();
						
					
					st=connexion.prepareStatement("INSERT INTO `commandes`(`id_client`, `id_article`,`date`) VALUES (?,?,?)");
					st.setInt(1, id_client); st.setInt(2, id_article);	 st.setTimestamp(3, sqlTimestamp);
					st.executeUpdate();
					
					
					JOptionPane.showMessageDialog(commanderBtn,"commande effectuée");
					

				} catch (SQLException ex) {
					ex.printStackTrace();	
					JOptionPane.showMessageDialog(commanderBtn,"commande echouée !");					
				}
				id_commande_F.setText("");
				id_article_F.setText("");
				id_client_F.setText("");
				date_F.setText("");
				
				id_commande=id_article=id_client=0;
				dateString=null;
				}
			}
		});
		commanderBtn.setBounds(86, 136, 156, 17);
		panel.add(commanderBtn);
		
		JLabel datestamp = new JLabel("DATE");
		datestamp.setBounds(16, 108, 115, 14);
		panel.add(datestamp);
		
		date_F = new JTextField();
		date_F.setText("auto");
		date_F.setEditable(false);
		date_F.setColumns(10);
		date_F.setBounds(94, 105, 150, 20);
		panel.add(date_F);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(268, 349, 395, 217);
		commande_P.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 376, 181);
		panel_5.add(scrollPane);
		
		commandes_T = new JTable();
		commandes_T.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel Df_Commande=(DefaultTableModel) commandes_T.getModel();
//				DefaultTableModel Df_clients=(DefaultTableModel) clients_T.getModel();
//				DefaultTableModel Df_articles=(DefaultTableModel) articles_T.getModel();
				try {
					
					id_commande_F.setText(Df_Commande.getValueAt(commandes_T.getSelectedRow(), 0).toString());
					id_client_F.setText(Df_Commande.getValueAt(commandes_T.getSelectedRow(), 1).toString());
					id_article_F.setText(Df_Commande.getValueAt(commandes_T.getSelectedRow(), 2).toString());
					date_F.setText(Df_Commande.getValueAt(commandes_T.getSelectedRow(), 3).toString());
					
				} catch (NullPointerException ne) {
					throw new NullPointerException();
				}
			}
			
			
		});
		scrollPane.setViewportView(commandes_T);
		commandes_T.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null,null},
				{null, null, null,null},
				{null, null, null,null},
	
			},
			new String[] {
				"N\u00B0 Commande", "ID Client", "ID Article","date"
			}
		));
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 id_commande=Integer.parseInt(id_commande_F.getText());
				 id_article=Integer.parseInt(id_article_F.getText());
				 id_client=Integer.parseInt(id_client_F.getText());

				 
			
				 /*--------------------------------------------------------------------------*/
				
				
				//les requetes
				try {
					
					st=connexion.prepareStatement("UPDATE `commandes` SET `id_commande`=?,`id_client`=?,`id_article`=? where id="+id_commande);
					st.setInt(2, id_client); st.setInt(3, id_article);	
					st.executeUpdate();
					
					JOptionPane.showMessageDialog(btnModifier,"modifié");

				} catch (SQLException ex) {
					ex.printStackTrace();	
					JOptionPane.showMessageDialog(commanderBtn,"commande echouée !");					
				}
				
				id_commande_F.setText("");
				id_article_F.setText("");
				id_client_F.setText("");
				date_F.setText("");
				
				id_commande=id_article=id_client=0;
				dateString=null;
			}
		});
		btnModifier.setBounds(273, 567, 99, 23);
		commande_P.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				DefaultTableModel Df=(DefaultTableModel) commandes_T.getModel();
				int rowIndex=commandes_T.getSelectedRow();
				
				try {					
					 id_article=Integer.parseInt(id_commande_F.getText());
					
					st=connexion.prepareStatement("delete from commandes where id_article="+id_commande);
					st.executeUpdate();
					
					
					int dialogResult=JOptionPane.showConfirmDialog(null," veux tu supprimer cette commande ?","Attention",JOptionPane.YES_NO_OPTION);
				
					id_commande_F.setText("");
					id_article_F.setText("");
					id_client_F.setText("");
					
				} catch (SQLException ex) {
					ex.printStackTrace();	

				}
			
			}
		});
		btnSupprimer.setBounds(376, 567, 110, 23);
		commande_P.add(btnSupprimer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 349, 262, 82);
		commande_P.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Rechrcher un Commande", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton chercherCommandeBtn = new JButton("Rechercher");
		chercherCommandeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					st=connexion.prepareStatement("select * from commandes where "+inColCommande+"="+(String)searchCommande_F.getText());
					chercherCommande(st,commandes_T);

				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(chercherCommandeBtn, "aucunne commande n'était enregistrée");
				}
			}
		});
		chercherCommandeBtn.setBounds(10, 52, 111, 23);
		panel_1.add(chercherCommandeBtn);
		
		searchCommande_F = new JTextField();
		searchCommande_F.setColumns(10);
		searchCommande_F.setBounds(12, 25, 109, 20);
		panel_1.add(searchCommande_F);
		
		JButton displayAllCommande = new JButton("Afficher tous");
		displayAllCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					st=connexion.prepareStatement("select * from commandes");
					chercherCommande(st,commandes_T);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		displayAllCommande.setBounds(131, 57, 121, 17);
		panel_1.add(displayAllCommande);
		
		JLabel idcommande_L = new JLabel("id_commande");
		idcommande_L.setBounds(137, 25, 102, 17);
		panel_1.add(idcommande_L);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(6, 10, 304, 84);
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "Rechrcher un client ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		commande_P.add(panel_1_1);
		
		JButton chercherClientBtn = new JButton("Rechercher");
		chercherClientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					st=connexion.prepareStatement("select * from clients where "+inColClients+"="+(String)searchClient_F.getText());
					chercherClient(st,clients_T);

				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(chercherClientBtn, "client non trouvée");
				}
		}
			
		});
		chercherClientBtn.setBounds(10, 50, 102, 23);
		panel_1_1.add(chercherClientBtn);
		
		searchClient_F = new JTextField();
		searchClient_F.setColumns(10);
		searchClient_F.setBounds(10, 25, 152, 20);
		panel_1_1.add(searchClient_F);
		String []optionsClients= {"id","nom","nss","no"};
		JComboBox comboBoxClients = new JComboBox(optionsClients);
		comboBoxClients.setBounds(173, 23, 111, 22);
		panel_1_1.add(comboBoxClients);
		
		
		comboBoxClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inColClients=(String) comboBoxClients.getSelectedItem();
				System.out.println(inColClients);
			}
		});
		
		JButton displayAllClients = new JButton("Afficher tous");
		displayAllClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		 		try {
					st=connexion.prepareStatement("select * from clients");
					chercherClient(st,clients_T);
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(displayAllClients, "aucun client trouvée");
				}
			}
		});
		displayAllClients.setBounds(173, 56, 117, 17);
		panel_1_1.add(displayAllClients);
		
		JPanel informationClients = new JPanel();
		informationClients.setBounds(311, 6, 962, 151);
		informationClients.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		commande_P.add(informationClients);
		informationClients.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 942, 129);
		informationClients.add(scrollPane_1);
		
		clients_T = new JTable();
		scrollPane_1.setViewportView(clients_T);
		
		clients_T.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_commande_F.setText("");
				date_F.setText("");
				idClient_L.setText("");
				name_L.setText("");
				addr_L.setText("");
				DefaultTableModel Df=(DefaultTableModel) clients_T.getModel();
				
				try {
					id_client_F.setText(Df.getValueAt(clients_T.getSelectedRow(), 0).toString());
					id_client=Integer.parseInt(Df.getValueAt(clients_T.getSelectedRow(), 0).toString());
					name=Df.getValueAt(clients_T.getSelectedRow(), 1).toString()+" "+Df.getValueAt(clients_T.getSelectedRow(), 2).toString();
					addrF=Df.getValueAt(clients_T.getSelectedRow(), 7).toString();
					email=Df.getValueAt(clients_T.getSelectedRow(), 4).toString();
					
					idClient_L.setText(id_client.toString());
					name_L.setText(name);
					addr_L.setText(addrF);
					
					
					
				} catch (NullPointerException ne) {}
				
					DfA=(DefaultTableModel) facture_T.getModel();
				
				try {
					st=connexion.prepareStatement("SELECT articles.libelle, articles.`prix HT`, articles.TVA FROM articles JOIN commandes ON articles.id_article=commandes.id_article JOIN clients on clients.id=commandes.id_client WHERE id="+id_client);
					result=st.executeQuery();
					metadata=result.getMetaData();
					 sumHT=0;
					 TTC=0;
					DfA.setRowCount(0);
					while(result.next()) {
						Vector vect=new Vector();
						for(int i=1;i<=metadata.getColumnCount();i++) {
							vect.add(result.getString("libelle"));
							vect.add(result.getString("prix HT"));
							vect.add(result.getString("TVA"));
								sumHT+=result.getInt("prix HT");
								TTC+=result.getInt("prix HT")*result.getInt("TVA")/100;
								}
						
						DfA.addRow(vect);	
					}	
					
				} catch (SQLException ex) {
					ex.printStackTrace();	
				}
			}
		});
		//		------------------------------------------------------------------------------------------------------------
		clients_T.setModel(new DefaultTableModel(
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
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Rechrcher une Article", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_2.setBounds(4, 172, 304, 91);
		commande_P.add(panel_1_2);
		
		JButton chercherArticleBtn = new JButton("Rechercher");
		chercherArticleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				st=connexion.prepareStatement("select * from articles where "+inColArticles+"="+searchArticle_F.getText());
				chercherArticle(st,articles_T);

			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(chercherArticleBtn, "article non trouvée");
			}
			}
		});
		chercherArticleBtn.setBounds(10, 50, 108, 23);
		panel_1_2.add(chercherArticleBtn);
		
		searchArticle_F = new JTextField();
		searchArticle_F.setColumns(10);
		searchArticle_F.setBounds(10, 25, 161, 20);
		panel_1_2.add(searchArticle_F);
		
		JComboBox comboBoxArticles = new JComboBox();
		comboBoxArticles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				inColArticles=(String) comboBoxArticles.getSelectedItem();
			}
		});
		comboBoxArticles.setModel(new DefaultComboBoxModel(new String[] {"id_article", "libelle"}));
		comboBoxArticles.setBounds(350, 24, 97, 22);
		panel_1_2.add(comboBoxArticles);
		
		JButton displayAllArticles = new JButton("Afficher tous");
		displayAllArticles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					st=connexion.prepareStatement("select * from articles");
					chercherArticle(st,articles_T);
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(chercherArticleBtn, "stock est vide");
				}
			
			 }
			
		});
		displayAllArticles.setBounds(181, 55, 111, 17);
		panel_1_2.add(displayAllArticles);
		
		JComboBox comboBoxAritcles = new JComboBox(new Object[]{"id_article", "libelle"});
		comboBoxAritcles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inColArticles=(String) comboBoxAritcles.getSelectedItem();
			}
		});
		comboBoxAritcles.setBounds(181, 24, 111, 22);
		panel_1_2.add(comboBoxAritcles);
		
		JPanel informationArticles = new JPanel();
		informationArticles.setLayout(null);
		informationArticles.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informationArticles.setBounds(314, 172, 959, 151);
		commande_P.add(informationArticles);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 22, 939, 118);
		informationArticles.add(scrollPane_2);
		
		articles_T = new JTable();
		scrollPane_2.setViewportView(articles_T);
		articles_T.setModel(new DefaultTableModel(
				new Object[][] {
				
					{null, null, null, null, null},
					{null, null, null, "", null},
				},
				new String[] {
					"ID", "Libell\u00E9", "Prix HT", "TVA %", "Qantit\u00E9"
				}
			));
		
		JPanel facture_P = new JPanel();
		facture_P.setLayout(null);
		facture_P.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "facture", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		facture_P.setBounds(671, 349, 395, 217);
		commande_P.add(facture_P);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 21, 375, 185);
		facture_P.add(scrollPane_3);
		
		facture_T = new JTable();
		scrollPane_3.setViewportView(facture_T);
		facture_T.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Libell\u00E9e article", "Montant", "TVA %"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		facture_T.getColumnModel().getColumn(0).setPreferredWidth(90);
		
		JButton exportBtn = new JButton("Exporter");
		exportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
				   LocalDateTime now = LocalDateTime.now();  
				   MessageFormat header=new MessageFormat("Facture de :"+now+" nom client: "+name+" Email: "+email+" adresse de facturation: "+addrF+"\n");

				   MessageFormat footer=new MessageFormat("\n\tMontant HT: "+String.valueOf(sumHT)+".    Montant TTC: "+String.valueOf(TTC));
				    try {
						facture_T.print(JTable.PrintMode.FIT_WIDTH,header,footer);
					} catch (PrinterException e1) {
						e1.printStackTrace();
					}
				   
				   
			}
		});
		exportBtn.setBounds(673, 567, 99, 23);
		commande_P.add(exportBtn);
		
		JLabel nom = new JLabel("Nom: ");
		nom.setBounds(1068, 374, 46, 14);
		commande_P.add(nom);
		
		JLabel addr = new JLabel("adresse Facturation:");
		addr.setBounds(1068, 399, 118, 14);
		commande_P.add(addr);
		
		JLabel id = new JLabel("id client:");
		id.setBounds(1067, 349, 56, 14);
		commande_P.add(id);
		

		idClient_L.setBounds(1133, 349, 110, 14);
		commande_P.add(idClient_L);
		
		name_L.setBounds(1124, 374, 149, 14);
		commande_P.add(name_L);
		
		addr_L.setBounds(1068, 419, 205, 14);
		commande_P.add(addr_L);
		articles_T.getColumnModel().getColumn(0).setPreferredWidth(47);
		articles_T.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				id_commande_F.setText("");
				date_F.setText("");
				DefaultTableModel Df=(DefaultTableModel) articles_T.getModel();
				try {
					id_article_F.setText(Df.getValueAt(articles_T.getSelectedRow(), 0).toString());
					id_article=Integer.parseInt(Df.getValueAt(articles_T.getSelectedRow(), 0).toString());
					 libelle=Df.getValueAt(articles_T.getSelectedRow(), 1).toString();
					 prix=Integer.parseInt(Df.getValueAt(articles_T.getSelectedRow(), 2).toString());
					 tva=Integer.parseInt(Df.getValueAt(articles_T.getSelectedRow(), 3).toString());
					 quantite=Integer.parseInt(Df.getValueAt(articles_T.getSelectedRow(), 4).toString());
				} catch (NullPointerException ne) {
					throw new NullPointerException();
				}
			}
		});
		clients_T.getColumnModel().getColumn(0).setPreferredWidth(38);
		clients_T.getColumnModel().getColumn(1).setPreferredWidth(83);
		clients_T.getColumnModel().getColumn(2).setPreferredWidth(83);
		clients_T.getColumnModel().getColumn(3).setPreferredWidth(80);
		clients_T.getColumnModel().getColumn(4).setPreferredWidth(80);
		clients_T.getColumnModel().getColumn(6).setPreferredWidth(61);
		clients_T.getColumnModel().getColumn(7).setPreferredWidth(100);
		clients_T.getColumnModel().getColumn(8).setPreferredWidth(101);
		clients_T.getColumnModel().getColumn(9).setPreferredWidth(61);
		clients_T.getColumnModel().getColumn(10).setPreferredWidth(60);
		clients_T.getColumnModel().getColumn(11).setPreferredWidth(50);
		clients_T.getColumnModel().getColumn(11).setMaxWidth(68);
		commandes_T.getColumnModel().getColumn(0).setPreferredWidth(79);
	}
	
	void  chercherClient(java.sql.PreparedStatement st,JTable table) {
		

		//les requetes
		DefaultTableModel Df=(DefaultTableModel) table.getModel();
		try {
			result=st.executeQuery();
			System.out.println("the result of search : "+result);
			metadata=result.getMetaData();
			
			Df.setRowCount(0);

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
			Df.removeRow(id_client);	
		}
	}
	void  chercherArticle(java.sql.PreparedStatement st,JTable table) {
		

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
	void  chercherCommande(java.sql.PreparedStatement st,JTable table) {


	//les requetes
	DefaultTableModel Df=(DefaultTableModel) table.getModel();
	try {
		result=st.executeQuery();
		System.out.println("the result of search : "+result);
		metadata=result.getMetaData();
		
		Df.setRowCount(0);
//		System.out.println(result);
		while(result.next()) {
			Vector vect=new Vector();
			for(int i=1;i<=metadata.getColumnCount();i++) {
				
				vect.add(result.getString("id_commande"));
				vect.add(result.getString("id_client"));
				vect.add(result.getString("id_article"));
				vect.add(result.getString("date"));
					}
			
			Df.addRow(vect);	
		}	
		
	} catch (SQLException ex) {
		ex.printStackTrace();
		Df.removeRow(id_commande);	
	 }
		}
	
	 void pdfPrint(JTable table){ 

		}
	 

}
