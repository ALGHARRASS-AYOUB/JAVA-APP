import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class MainFrame extends JFrame   {

	private JPanel statusDB_L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static boolean connected=false;
	public static Connection cx=null;
	

	
	JButton clientBtn = new JButton("Gestion De Client");
	JButton articlesBtn = new JButton("Gestion des Articles");
	JButton commandeBtn = new JButton("Gestion des Commandes");
	JButton connectBtn = new JButton("connecter");
	JLabel userLabel = new JLabel("utilisateur");
	private final JTextField user_F = new JTextField();
	private final JLabel passwordLabel = new JLabel("mot de passe");
	private JPasswordField password_F;
	private final JLabel statusInfo = new JLabel("Etat de base donn\u00E9s: ");
	private final JLabel statusDBLabel = new JLabel("non connect\u00E9e");
	private final JLabel hint = new JLabel("connecter d'abord !");
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		user_F.setBounds(95, 21, 180, 20);
		user_F.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 300);
		statusDB_L = new JPanel();
		statusDB_L.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(statusDB_L);
		statusDB_L.setLayout(null);
		clientBtn.setBounds(508, 7, 188, 23);
		articlesBtn.setBounds(508, 38, 188, 23);
		commandeBtn.setBounds(508, 67, 188, 23);
		
		statusDB_L.add(clientBtn);
		statusDB_L.add(articlesBtn);
		statusDB_L.add(commandeBtn);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Base de Donn\u00E9s", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 319, 202);
		statusDB_L.add(panel);
		panel.setLayout(null);
		
		userLabel.setBounds(10, 24, 57, 14);
		panel.add(userLabel);
		passwordLabel.setBounds(10, 62, 87, 14);
		
		panel.add(passwordLabel);
		
		panel.add(user_F);
		connectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass=new String(password_F.getPassword());
				System.out.println("info db : "+user_F.getText()+"and pass: "+pass);
				if(connectDB(user_F.getText(),pass)) {
					connected=true;
					hint.setText("");
					statusDBLabel.setText("Connecté");
					
					clientBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new GestionClientFrame().setVisible(true);
							
							dispose();
							
						}
					});
					
					articlesBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new GestionArticleFrame().setVisible(true);
							dispose();
						}
					});
					
					commandeBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							new GestionCommandeFrame().setVisible(true);
							dispose();
						}
					});

				}
					
			}
		});
		
		connectBtn.setBounds(186, 168, 96, 23);
		panel.add(connectBtn);
		
		password_F = new JPasswordField();
		password_F.setBounds(95, 59, 180, 20);
		panel.add(password_F);
		
		JCheckBox show_check_F = new JCheckBox("afficher mot de passe");
		show_check_F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(show_check_F.isSelected())
					password_F.setEchoChar((char) 0);
				else
					password_F.setEchoChar('•');
			}
		});
		show_check_F.setBounds(133, 86, 180, 14);
		panel.add(show_check_F);
		statusInfo.setBounds(10, 236, 124, 14);
		
		statusDB_L.add(statusInfo);
		statusDBLabel.setBounds(150, 236, 91, 14);
		
		statusDB_L.add(statusDBLabel);
		hint.setBounds(540, 236, 156, 14);
		
		statusDB_L.add(hint);
	}
	boolean connectDB(String user,String password) {
		boolean flag;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Acceder au Drive......... [OK]");
			flag= true;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("le driver n est pas accessible .");
			System.exit(0);
			JOptionPane.showMessageDialog(clientBtn, "impossible de trouver le driver" );
			flag= false;
		}
		try {
			cx =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncommerce",user,password);
			System.out.println("acces au serveur mysql .............[OK]");
			flag= true;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(clientBtn, "impossible d'établir une connexion avec la base de donnés" );
			ex.printStackTrace();
			System.out.println("impossible d'acceder au serveur mysql \n utilisateur ou mot de passe incorrect");
			System.exit(0);
			flag= false;
		}
		finally {
			return flag;
		}

	}
}

