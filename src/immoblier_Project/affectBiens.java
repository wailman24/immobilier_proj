package immoblier_Project;

import java.awt.Color;
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
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSlider;

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
	/*public static void main(String[] args) {
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
	}*/

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
			
			connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ELMOKRETAR","nabil");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 915, 544);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Numero de l'agent :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 149, 195, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNumeroDeBien = new JLabel("Numero de Bien :");
		lblNumeroDeBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumeroDeBien.setBounds(10, 210, 195, 39);
		frame.getContentPane().add(lblNumeroDeBien);
		
		txtNumAgt = new JTextField();
		txtNumAgt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumAgt.setBounds(224, 149, 207, 39);
		frame.getContentPane().add(txtNumAgt);
		txtNumAgt.setColumns(10);
		
		txtNumBiens = new JTextField();
		txtNumBiens.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumBiens.setColumns(10);
		txtNumBiens.setBounds(224, 210, 207, 39);
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
		btnajouter.setBounds(10, 290, 152, 44);
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
		btnsupprimer.setBounds(172, 290, 152, 44);
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
		btnafficher.setBounds(334, 290, 152, 44);
		frame.getContentPane().add(btnafficher);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(496, 91, 395, 406);
		frame.getContentPane().add(scrollPane);
		
		tableagtbien = new JTable();
		scrollPane.setViewportView(tableagtbien);
		
		txtNumaffect = new JTextField();
		txtNumaffect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumaffect.setColumns(10);
		txtNumaffect.setBounds(224, 91, 207, 39);
		frame.getContentPane().add(txtNumaffect);
		
		JLabel lblNumeroDeLaffectation = new JLabel("Numero de l'affectation :");
		lblNumeroDeLaffectation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumeroDeLaffectation.setBounds(10, 91, 204, 39);
		frame.getContentPane().add(lblNumeroDeLaffectation);
		
		JLabel lblNewLabel_1 = new JLabel("Affectation de bien");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(301, 30, 328, 39);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("main");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
				window.setVisible(true);
				
				frame.dispose();
			}
		});
<<<<<<< HEAD
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(10, 10, 66, 39);
		frame.getContentPane().add(btnNewButton);
=======
		btnBiens.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBiens.setBounds(606, 227, 152, 44);
		frame.getContentPane().add(btnBiens);
		
		JButton btnClose = new JButton("<--");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
				window.setVisible(true);
				
				frame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClose.setBackground(new Color(255, 255, 255));
		btnClose.setBounds(0, 0, 65, 24);
		frame.getContentPane().add(btnClose);
>>>>>>> d81dcefa8481bacb255210bf77913c6dd9caed28
	}
}
