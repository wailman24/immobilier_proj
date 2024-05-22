package immoblier_Project;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class agents {

	private JFrame frame;
	private JTextField txtNumAgt;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTable tableagt;
	
	private Connection connection;
	private Statement statement;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agents window = new agents();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public agents() {
		initialize();
	}

	public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","mansour_ouahchia","wail");
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ELMOKRETAR", "nabil");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 796, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Num agent : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 178, 138, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNomAgent = new JLabel("Nom agent : ");
		lblNomAgent.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomAgent.setBounds(10, 237, 138, 30);
		frame.getContentPane().add(lblNomAgent);
		
		JLabel lblPrenomAgent = new JLabel("Prenom agent : ");
		lblPrenomAgent.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrenomAgent.setBounds(10, 296, 138, 30);
		frame.getContentPane().add(lblPrenomAgent);
		
		txtNumAgt = new JTextField();
		txtNumAgt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumAgt.setBounds(174, 177, 226, 38);
		frame.getContentPane().add(txtNumAgt);
		txtNumAgt.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNom.setColumns(10);
		txtNom.setBounds(174, 236, 226, 38);
		frame.getContentPane().add(txtNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(174, 295, 226, 38);
		frame.getContentPane().add(txtPrenom);
		
		JButton btnajouteragt = new JButton("ajouter");
		btnajouteragt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String TNumAgt =txtNumAgt.getText();
				String TNom =txtNom.getText();
				String TPrenom =txtPrenom.getText();
				
				String query = " INSERT INTO Agent VALUES ('"+TNumAgt+"','"+TNom+"','"+TPrenom+"')";
				
				try {
					statement=connection.createStatement();
					statement.execute(query);
					
					txtNumAgt.setText("");
					txtNom.setText("");
					txtPrenom.setText("");
		
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(frame, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnajouteragt.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnajouteragt.setBounds(12, 349, 124, 37);
		frame.getContentPane().add(btnajouteragt);
		
		JButton btnSupprimeragt = new JButton("supprimer");
		btnSupprimeragt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TNumAgt =txtNumAgt.getText();
				String query = " DELETE FROM Agent WHERE NumAgt='"+TNumAgt+"'";
				
				try {
					statement=connection.createStatement();
					statement.execute(query);
					
					txtNumAgt.setText("");
					txtNom.setText("");
					txtPrenom.setText("");
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(frame, "Error: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimeragt.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSupprimeragt.setBounds(146, 349, 124, 37);
		frame.getContentPane().add(btnSupprimeragt);
		
		JButton btnAfficheragt = new JButton("afficher");
		btnAfficheragt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "SELECT * FROM Agent";
				
				try {
					statement=connection.createStatement();
					ResultSet res=statement.executeQuery(query);
					
					tableagt.setModel(DbUtils.resultSetToTableModel(res));
					
				}catch(Exception e3) {
					JOptionPane.showMessageDialog(frame, "Error: " + e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAfficheragt.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAfficheragt.setBounds(280, 349, 124, 37);
		frame.getContentPane().add(btnAfficheragt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(423, 96, 349, 442);
		frame.getContentPane().add(scrollPane);
		
		tableagt = new JTable();
		scrollPane.setViewportView(tableagt);
		

		JButton btnAddBiens = new JButton("biens");
		btnAddBiens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                // Open the Biens window
                tryone biensWindow = new tryone();
                biensWindow.setVisible(true);

                // Close the current window
                frame.dispose();
				
			}
		});
		btnAddBiens.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddBiens.setBounds(598, 255, 151, 37);
		frame.getContentPane().add(btnAddBiens);
		

		JLabel lblNewLabel_1 = new JLabel("Gestion des Agents");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_1.setBounds(235, 29, 292, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("main");
		btnNewButton.setIcon(new ImageIcon("home.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
				window.setVisible(true);
				
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(10, 10, 124, 101);
		frame.getContentPane().add(btnNewButton);
	}

}
