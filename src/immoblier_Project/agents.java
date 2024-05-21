package immoblier_Project;

import java.awt.EventQueue;

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
	public static void main(String[] args) {
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
	}

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
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 796, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Num agent : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(8, 121, 138, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNomAgent = new JLabel("Nom agent : ");
		lblNomAgent.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomAgent.setBounds(8, 180, 138, 30);
		frame.getContentPane().add(lblNomAgent);
		
		JLabel lblPrenomAgent = new JLabel("Prenom agent : ");
		lblPrenomAgent.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrenomAgent.setBounds(8, 239, 138, 30);
		frame.getContentPane().add(lblPrenomAgent);
		
		txtNumAgt = new JTextField();
		txtNumAgt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumAgt.setBounds(172, 120, 226, 38);
		frame.getContentPane().add(txtNumAgt);
		txtNumAgt.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNom.setColumns(10);
		txtNom.setBounds(172, 179, 226, 38);
		frame.getContentPane().add(txtNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(172, 238, 226, 38);
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
		btnajouteragt.setBounds(10, 292, 124, 37);
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
		btnSupprimeragt.setBounds(144, 292, 124, 37);
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
		btnAfficheragt.setBounds(278, 292, 124, 37);
		frame.getContentPane().add(btnAfficheragt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(423, 96, 339, 442);
		frame.getContentPane().add(scrollPane);
		
		tableagt = new JTable();
		scrollPane.setViewportView(tableagt);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion des Agents");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_1.setBounds(235, 29, 292, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("main");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
				window.setVisible(true);
				
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(10, 10, 65, 49);
		frame.getContentPane().add(btnNewButton);
	}

}
