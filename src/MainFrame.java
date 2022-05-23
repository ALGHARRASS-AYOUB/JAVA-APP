import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

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

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Gestion De Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionClientFrame().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(110, 11, 188, 49);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gestion des Articles");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionArticleFrame().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(111, 78, 188, 49);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Gestion des Commandes");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GestionCommandeFrame().setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(114, 150, 188, 49);
		contentPane.add(btnNewButton_1_1);
	}
}
