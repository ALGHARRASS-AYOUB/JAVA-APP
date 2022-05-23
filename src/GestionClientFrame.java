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

public class GestionClientFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idClient;
	private JTextField addLivraison;
	private JTextField addFacturation;
	private JTextField email;
	private JTextField chercherInput;
	private JTextField nom;
	private JTextField prenom;
	private JTextField nomEse;
	private JTextField status;
	private JTextField no;
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

	Connection connexion = null;
	 java.sql.PreparedStatement st;
	 ResultSet result;
	 ResultSetMetaData metadata;
	/**
	 * Create the frame.
	 */
	public GestionClientFrame() {
		setTitle("Gestion Clients");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 928, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(0, 11, 408, 432);
		contentPane.add(panel);
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
		idClient.setEnabled(false);
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
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(189, 106, 200, 20);
		panel.add(email);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(3, 175, 192, 213);
		panel.add(panel_2);
		panel_2.setLayout(null);

		
		JRadioButton clientP = new JRadioButton("Client Physique");
		clientP.setBounds(3, 155, 109, 23);
		panel.add(clientP);
		
		JRadioButton clientE = new JRadioButton("Client Entreprise");
		clientE.setBounds(200, 162, 109, 23);
		panel.add(clientE);
		
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(6, 27, 42, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Prenom");
		lblNewLabel_1_1.setBounds(5, 52, 54, 14);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel(" Date naissance");
		lblNewLabel_1_2.setBounds(7, 82, 118, 14);
		panel_2.add(lblNewLabel_1_2);
		
		nom = new JTextField();
		nom.setBounds(52, 25, 135, 20);
		panel_2.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(52, 50, 135, 20);
		panel_2.add(prenom);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Genre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(6, 132, 173, 46);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JRadioButton genreF = new JRadioButton("feminin");
		genreF.setBounds(6, 16, 69, 23);
		panel_4.add(genreF);
		
		JRadioButton genreM = new JRadioButton("masculin");
		genreM.setBounds(77, 16, 90, 23);
		panel_4.add(genreM);
		
		date = new JTextField();
		date.setColumns(10);
		date.setBounds(24, 102, 162, 20);
		panel_2.add(date);
		
		nssF = new JTextField();
		nssF.setColumns(10);
		nssF.setBounds(52, 189, 135, 20);
		panel_2.add(nssF);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("NSS");
		lblNewLabel_1_1_1.setBounds(5, 191, 54, 14);
		panel_2.add(lblNewLabel_1_1_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(199, 175, 201, 213);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		nomEse = new JTextField();
		nomEse.setColumns(10);
		nomEse.setBounds(57, 26, 135, 20);
		panel_3.add(nomEse);
		
		JLabel lblNewLabel_1_3 = new JLabel("Nom");
		lblNewLabel_1_3.setBounds(10, 29, 42, 14);
		panel_3.add(lblNewLabel_1_3);
		
		status = new JTextField();
		status.setColumns(10);
		status.setBounds(57, 57, 135, 20);
		panel_3.add(status);
		
		JLabel lblNewLabel_1_4 = new JLabel("Status");
		lblNewLabel_1_4.setBounds(10, 60, 58, 14);
		panel_3.add(lblNewLabel_1_4);
		
		no = new JTextField();
		no.setColumns(10);
		no.setBounds(103, 88, 86, 20);
		panel_3.add(no);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("N\u00B0 National");
		lblNewLabel_1_4_1.setBounds(9, 91, 93, 14);
		panel_3.add(lblNewLabel_1_4_1);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Acceder au Drive......... [OK]");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("le driver n est pas accessible .");
			System.exit(0);
		}
		try {
			connexion =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncommerce","root","");
			System.out.println("acces au serveur mysql .............[OK]");
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			System.out.println("impossible d'acceder au serveur mysql");
			System.exit(0);
		}
		
		
		JButton register = new JButton("Registrer");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				 int id,no=0;
				 Sexe genre = null;
				 String sname=null,name = null,addL,addF,telnum,email,nss=null,status = null,dateN = null;
				 
				 addL=addLivraison.getText();
				 addF=addFacturation.getText();
				 email=GestionClientFrame.this.email.getText();
				 telnum=tel.getText();
				 
				 
//				 CLIENT PHUSIQUE 
//					
				 if(clientP.isSelected()) {
					 name=nom.getText();
					 sname=prenom.getText();
					 email=GestionClientFrame.this.email.getText();
					 dateN=date.getText();
					 if(genreF.isSelected())
						 genre=Sexe.feminin;
					 if(genreM.isSelected())
						 genre=Sexe.masculin;
					 dateN=date.getText();
					 nss=nssF.getText();
				 }

	
					 if(clientE.isSelected()) {
					 name=nomEse.getText();
					 status=GestionClientFrame.this.status.getText();
					 no=Integer.parseInt(GestionClientFrame.this.no.getText());
				}
				
				 
				
				
				
				//etape two : test de connection avec le serveur 
				//les requetes
				try {
					
					
					st=connexion.prepareStatement("INSERT INTO `clients`(`nom`,`prenom`, `tel`, `email`, `dateNaissance`, `genre`, `addresseFacturation`, `addresseLivraison`, `nss`, `status`, `numeroNational`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
					st.setString(1, name);
					st.setString(2, sname);
					st.setString(3, telnum);
					st.setString(4, email);
					st.setString(5, dateN);
					st.setString(6, String.valueOf(genre));
					st.setString(7, addF);
					st.setString(8, addL);
					st.setString(9, nss);
					st.setString(10, status);
					st.setInt(11, no);
					st.executeUpdate();
					
					JOptionPane.showMessageDialog(register,"registered");
					addLivraison.setText("");
					 addFacturation.setText("");
					 GestionClientFrame.this.email.setText("");
					 tel.setText("");
					 
					nom.setText("");
					prenom.setText("");
					GestionClientFrame.this.email.setText("");
					date.setText("");
					genreF.setSelected(false);
					genreM.setSelected(false);
					nssF.setText("");
					nomEse.setText("");
					GestionClientFrame.this.status.setText("");
					GestionClientFrame.this.no.setText("");
					addLivraison.requestFocus();

					
				} catch (SQLException ex) {
					ex.printStackTrace();	

				}
			}
		});
		

		register.setBounds(146, 399, 89, 23);
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
		panel_1.setBounds(513, 11, 399, 53);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String by=chercherInput.getText();
				chercher(by);
			}
		});
		btnRechercher.setBounds(10, 24, 102, 23);
		panel_1.add(btnRechercher);
		
		chercherInput = new JTextField();
		chercherInput.setColumns(10);
		chercherInput.setBounds(139, 25, 230, 20);
		panel_1.add(chercherInput);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(418, 64, 484, 330);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "adresse de livraison", "adresse de facture", "tel", "Email", "nom", "prenom", "Date de naissance", "Genre", "NSS", "Status juridique", "numero national"
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
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(9).setPreferredWidth(61);
		table.getColumnModel().getColumn(11).setPreferredWidth(59);
		table.getColumnModel().getColumn(11).setMaxWidth(68);
		table.setBounds(10, 24, 464, 295);
		panel_5.add(table);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(419, 409, 102, 23);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(531, 409, 102, 23);
		contentPane.add(btnSupprimer);
	}
	void  chercher(Object by) {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Acceder au Drive......... [OK]");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("le driver n est pas accessible .");
			System.exit(0);
		}
		try {
			connexion =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncommerce","root","");
			System.out.println("acces au serveur mysql .............[OK]");
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			System.out.println("impossible d'acceder au serveur mysql");
			System.exit(0);
		}
		 

		//les requetes
		try {
			
			st=connexion.prepareStatement("select * from clients where nss="+(String)by);
			if(st==null)
				st=connexion.prepareStatement("select * from clients where nom="+(String)by);
			if(st==null)
				st=connexion.prepareStatement("select * from clients where no="+(String)by);
			result=st.executeQuery();
			System.out.println(result);
			metadata=result.getMetaData();
			
			DefaultTableModel Df=(DefaultTableModel) table.getModel();
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

		}
	}
	
}
