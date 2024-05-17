package immoblier_Project;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class tryone {

	private JFrame frame;
	private JTextField txtNumBiens;
	private JTextField txtTypeBiens;
	private JTextField txtlocalisation;
	private JTextField txtPrixVent;
	private JTextField txtprixLocation;
	private JTextField txttaille;
	
	private Connection connection;
	private Statement statement;
	private JTable tableE;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tryone window = new tryone();
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
	public tryone() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","mansour_ouahchia","wail");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 887, 759);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnajouter = new JButton("ajouter bien");
		btnajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TNumBiens =txtNumBiens.getText();
				String TTypeBiens =txtTypeBiens.getText();
				String Tlocalisation =txtlocalisation.getText();
				String TPrixVent =txtPrixVent.getText();
				String TprixLocation =txtprixLocation.getText();
				String Ttaille =txttaille.getText();
				
				
				String query = " INSERT INTO biensImmobiliers VALUES ('"+TNumBiens+"','"+TTypeBiens+"','"+Tlocalisation+"','"+TPrixVent+"','"+TprixLocation+"','"+Ttaille + "')";
				
				try {
					statement=connection.createStatement();
					statement.execute(query);
					
					txtNumBiens.setText("");
					txtTypeBiens.setText("");
					txtlocalisation.setText("");
					txtPrixVent.setText("");
					txtprixLocation.setText("");
					txttaille.setText("");
					
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnajouter.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnajouter.setBounds(533, 37, 221, 43);
		frame.getContentPane().add(btnajouter);
		
		JLabel lblNewLabel = new JLabel("Numero bien : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(51, 37, 134, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTypeBien = new JLabel("type bien : ");
		lblTypeBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTypeBien.setBounds(51, 93, 134, 43);
		frame.getContentPane().add(lblTypeBien);
		
		JLabel lblLocalisation = new JLabel("localisation : ");
		lblLocalisation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLocalisation.setBounds(51, 146, 134, 43);
		frame.getContentPane().add(lblLocalisation);
		
		JLabel lblPrixVente = new JLabel("prix vente : ");
		lblPrixVente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrixVente.setBounds(51, 198, 134, 43);
		frame.getContentPane().add(lblPrixVente);
		
		JLabel lblPrixLocation = new JLabel("prix location : ");
		lblPrixLocation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrixLocation.setBounds(51, 251, 134, 43);
		frame.getContentPane().add(lblPrixLocation);
		
		JLabel lblTaille = new JLabel("taille : ");
		lblTaille.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTaille.setBounds(51, 304, 134, 43);
		frame.getContentPane().add(lblTaille);
		
		txtNumBiens = new JTextField();
		txtNumBiens.setBounds(183, 37, 280, 40);
		frame.getContentPane().add(txtNumBiens);
		txtNumBiens.setColumns(10);
		
		txtTypeBiens = new JTextField();
		txtTypeBiens.setColumns(10);
		txtTypeBiens.setBounds(183, 90, 280, 40);
		frame.getContentPane().add(txtTypeBiens);
		
		txtlocalisation = new JTextField();
		txtlocalisation.setColumns(10);
		txtlocalisation.setBounds(183, 146, 280, 40);
		frame.getContentPane().add(txtlocalisation);
		
		txtPrixVent = new JTextField();
		txtPrixVent.setColumns(10);
		txtPrixVent.setBounds(183, 199, 280, 40);
		frame.getContentPane().add(txtPrixVent);
		
		txtprixLocation = new JTextField();
		txtprixLocation.setColumns(10);
		txtprixLocation.setBounds(183, 254, 280, 40);
		frame.getContentPane().add(txtprixLocation);
		
		txttaille = new JTextField();
		txttaille.setColumns(10);
		txttaille.setBounds(183, 304, 280, 40);
		frame.getContentPane().add(txttaille);
		
		JButton btnNewButton = new JButton("Afficher les biens");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT * FROM biensImmobiliers";
				
				try {
					statement=connection.createStatement();
					ResultSet res=statement.executeQuery(query);
					
					tableE.setModel(DbUtils.resultSetToTableModel(res));
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(533, 303, 221, 43);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 372, 853, 340);
		frame.getContentPane().add(scrollPane);
		
		tableE = new JTable();
		scrollPane.setViewportView(tableE);
		
		JButton btnSupprimerBien = new JButton("supprimer bien");
		btnSupprimerBien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TNumBiens =txtNumBiens.getText();
				String TTypeBiens =txtTypeBiens.getText();
				String Tlocalisation =txtlocalisation.getText();
				String TPrixVent =txtPrixVent.getText();
				String TprixLocation =txtprixLocation.getText();
				String Ttaille =txttaille.getText();
				
				
				String query = " DELETE FROM biensImmobiliers WHERE NumBiens='"+TNumBiens+"'";
				
				try {
					statement=connection.createStatement();
					statement.execute(query);
					
					txtNumBiens.setText("");
					txtTypeBiens.setText("");
					txtlocalisation.setText("");
					txtPrixVent.setText("");
					txtprixLocation.setText("");
					txttaille.setText("");
					
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnSupprimerBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSupprimerBien.setBounds(533, 106, 221, 43);
		frame.getContentPane().add(btnSupprimerBien);
		
		JButton btnModifierBien = new JButton("modifier bien");
		btnModifierBien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TNumBiens =txtNumBiens.getText();
				String TTypeBiens =txtTypeBiens.getText();
				String Tlocalisation =txtlocalisation.getText();
				String TPrixVent =txtPrixVent.getText();
				String TprixLocation =txtprixLocation.getText();
				String Ttaille =txttaille.getText();
				
				String query = " UPDATE biensImmobiliers SET TypeBiens = '"+TTypeBiens+"', localisation='"+Tlocalisation+"',PrixVent='"+TPrixVent+"', prixLocation='"+TprixLocation+"', taille='"+Ttaille+"'   WHERE NumBiens = '"+TNumBiens+"' ";
						
				try {
					statement=connection.createStatement();
					statement.execute(query);
					
					txtNumBiens.setText("");
					txtTypeBiens.setText("");
					txtlocalisation.setText("");
					txtPrixVent.setText("");
					txtprixLocation.setText("");
					txttaille.setText("");
					
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModifierBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModifierBien.setBounds(533, 175, 221, 43);
		frame.getContentPane().add(btnModifierBien);
	}
}
