package immoblier_Project;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

public class affectBiens {

	private JFrame frame;
	private JTextField txtNumAgt;
	private JTextField txtNumBiens;
	
	private Connection connection; 
	private Statement statement;
	private JTable tableagtbien;
	private JTextField txtNumaffect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					affectBiens window = new affectBiens();
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
	public affectBiens() {
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
		frame.setBounds(100, 100, 915, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Numero de l'agent :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(26, 93, 195, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNumeroDeBien = new JLabel("Numero de Bien :");
		lblNumeroDeBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumeroDeBien.setBounds(26, 154, 195, 39);
		frame.getContentPane().add(lblNumeroDeBien);
		
		txtNumAgt = new JTextField();
		txtNumAgt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumAgt.setBounds(240, 93, 207, 39);
		frame.getContentPane().add(txtNumAgt);
		txtNumAgt.setColumns(10);
		
		txtNumBiens = new JTextField();
		txtNumBiens.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumBiens.setColumns(10);
		txtNumBiens.setBounds(240, 154, 207, 39);
		frame.getContentPane().add(txtNumBiens);
		
		JButton btnajouter = new JButton("ajouter");
		btnajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String TNumaffect =txtNumaffect.getText();
				String TNumAgt =txtNumAgt.getText();
				String TNumBiens =txtNumBiens.getText();
				
				
				String query = " INSERT INTO AgentBiens VALUES ('"+TNumaffect+"','"+TNumAgt+"','"+TNumBiens+"',sysdate)";
				
				try {
					statement=connection.createStatement();
					statement.execute(query);
					
					txtNumaffect.setText("");
					txtNumAgt.setText("");
					txtNumBiens.setText("");
					
		
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(frame, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnajouter.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnajouter.setBounds(43, 227, 152, 44);
		frame.getContentPane().add(btnajouter);
		
		JButton btnsupprimer = new JButton("supprimer");
		btnsupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String TNumaffect =txtNumaffect.getText();
				String query = " DELETE FROM AgentBiens WHERE Numaffect='"+TNumaffect+"'";
				
				try {
					statement=connection.createStatement();
					statement.execute(query);
					
					txtNumaffect.setText("");
					txtNumAgt.setText("");
					txtNumBiens.setText("");
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(frame, "Error: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnsupprimer.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnsupprimer.setBounds(231, 227, 152, 44);
		frame.getContentPane().add(btnsupprimer);
		
		JButton btnafficher = new JButton("afficher");
		btnafficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "SELECT * FROM AgentBiens";
				
				try {
					statement=connection.createStatement();
					ResultSet res=statement.executeQuery(query);
					
					tableagtbien.setModel(DbUtils.resultSetToTableModel(res));
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(frame, "Error: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnafficher.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnafficher.setBounds(419, 227, 152, 44);
		frame.getContentPane().add(btnafficher);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 281, 881, 216);
		frame.getContentPane().add(scrollPane);
		
		tableagtbien = new JTable();
		scrollPane.setViewportView(tableagtbien);
		
		txtNumaffect = new JTextField();
		txtNumaffect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumaffect.setColumns(10);
		txtNumaffect.setBounds(240, 35, 207, 39);
		frame.getContentPane().add(txtNumaffect);
		
		JLabel lblNumeroDeLaffectation = new JLabel("Numero de l'affectation :");
		lblNumeroDeLaffectation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumeroDeLaffectation.setBounds(26, 35, 204, 39);
		frame.getContentPane().add(lblNumeroDeLaffectation);
		
		JButton btnBiens = new JButton("Biens");
		btnBiens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tryone BienWindow = new tryone();
				BienWindow.setVisible(true);

                // Close the current window
                frame.dispose();
			}
		});
		btnBiens.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBiens.setBounds(606, 227, 152, 44);
		frame.getContentPane().add(btnBiens);
	}

}
