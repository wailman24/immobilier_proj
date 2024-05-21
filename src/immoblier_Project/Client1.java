package immoblier_Project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.Color;

public class Client1 {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblMat;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblNumeroTel;
	private JLabel lblEmail;
	private JLabel lblType;
	private JTextField textFieldMat;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldNumeroTel;
	private JTextField textFieldEmail;
	private JTextField textFieldType;
	//private JButton btnAjouter;
	//private JButton btnModifier;
	//private JButton btnSupprimer;
	//private JButton btnAfficher;
	//private JButton btnClose;
	public static Connection connection;
	public static Statement statement;
	public static int nbrClt = 0;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client1 window = new Client1();
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
	public Client1(int i ) {
		if (i>0) {initialize();}
		else {initialize_1();}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","mansour_ouahchia","wail");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ELMOKRETAR", "nabil");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 965, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		String ch = "Select count(*) From Client";
		try {
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery(ch);
			res.next();
			nbrClt = res.getInt(1);
			System.out.println(nbrClt);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("La gestion des clients");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(375, 10, 281, 32);
		frame.getContentPane().add(lblNewLabel);

		lblMat = new JLabel("Matricule de client:");
		lblMat.setBackground(Color.WHITE);
		lblMat.setForeground(Color.BLACK);
		lblMat.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMat.setBounds(29, 69, 160, 32);
		frame.getContentPane().add(lblMat);

		lblNom = new JLabel("Nom de client:");
		lblNom.setBackground(Color.WHITE);
		lblNom.setForeground(Color.BLACK);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNom.setBounds(29, 107, 140, 32);
		frame.getContentPane().add(lblNom);

		lblPrenom = new JLabel("Prenom de client:");
		lblPrenom.setBackground(Color.WHITE);
		lblPrenom.setForeground(Color.BLACK);
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrenom.setBounds(29, 144, 154, 32);
		frame.getContentPane().add(lblPrenom);

		lblNumeroTel = new JLabel("Numero de telephone:");
		lblNumeroTel.setBackground(Color.WHITE);
		lblNumeroTel.setForeground(Color.BLACK);
		lblNumeroTel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumeroTel.setBounds(29, 180, 193, 32);
		frame.getContentPane().add(lblNumeroTel);

		lblEmail = new JLabel("Email:");
		lblEmail.setBackground(Color.WHITE);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(29, 220, 111, 32);
		frame.getContentPane().add(lblEmail);

		lblType = new JLabel("Type de client:");
		lblType.setBackground(Color.WHITE);
		lblType.setForeground(Color.BLACK);
		lblType.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblType.setBounds(29, 262, 140, 32);
		frame.getContentPane().add(lblType);

		textFieldMat = new JTextField();
		textFieldMat.setBounds(219, 69, 218, 31);
		frame.getContentPane().add(textFieldMat);
		textFieldMat.setColumns(10);

		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(219, 104, 218, 31);
		frame.getContentPane().add(textFieldNom);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(219, 141, 218, 33);
		frame.getContentPane().add(textFieldPrenom);

		textFieldNumeroTel = new JTextField();
		textFieldNumeroTel.setColumns(10);
		textFieldNumeroTel.setBounds(219, 181, 218, 31);
		frame.getContentPane().add(textFieldNumeroTel);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(219, 222, 218, 32);
		frame.getContentPane().add(textFieldEmail);
		
		textFieldType = new JTextField();
		textFieldType.setColumns(10);
		textFieldType.setBounds(219, 264, 218, 32);
		frame.getContentPane().add(textFieldType);


		

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Client c = new Client(textField_1.getText(), textField_2.getText(), textField_3.getText(),
						//textField_4.getText(), textField_5.getText());
				nbrClt++;
				String query = "Insert into Client values (" + nbrClt + ",'" + textFieldNom.getText() + "','" + textFieldPrenom.getText()
						+ "','" + textFieldNumeroTel.getText() + "','" + textFieldEmail.getText() + "','" + textFieldType.getText() + "')";
				textFieldNom.setText("");
				textFieldPrenom.setText("");
				textFieldNumeroTel.setText("");
				textFieldEmail.setText("");
				textFieldType.setText("");
				System.out.println(query);
				try {
					statement = connection.createStatement();
					statement.execute(query);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter.setBounds(10, 334, 127, 40);
		frame.getContentPane().add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldMat.getText().isEmpty()) {
					String query = "Update Client set ";
					//String query = "Update Client set ";
					boolean exist=false;
					if(!textFieldNom.getText().isEmpty()) {query = query + "Nom = '"+textFieldNom.getText()+"' ";exist=true;}
					if(!textFieldPrenom.getText().isEmpty()) {
						if(exist) {query = query + ", Prenom = '"+textFieldPrenom.getText()+"' ";}
						else {query = query + "Prenom = '"+textFieldPrenom.getText()+"' ";exist=true;}}
					if(!textFieldNumeroTel.getText().isEmpty()) {
						if(exist) {query = query + ", numerotel = "+textFieldNumeroTel.getText()+" ";}
						else {query = query + "numerotel = '"+textFieldNumeroTel.getText()+"' ";exist=true;}}
					if(!textFieldEmail.getText().isEmpty()) {
						if(exist) {query = query + ", email = "+textFieldEmail.getText()+" ";}
						else {query = query + "email = '"+textFieldEmail.getText()+"' ";exist=true;}}
					if(!textFieldType.getText().isEmpty()) {
						if(exist) {query = query + ", typeClt = "+textFieldType.getText();}
						else {query = query + "typeClt = '"+textFieldType.getText()+"' ";exist=true;}}
					if(query.contentEquals("Update Client set ")) {
						JOptionPane.showMessageDialog(btnModifier, "Met une modification!");
						//System.out.println(query);
						}
					else {
						query = query +"Where numClt = "+textFieldMat.getText();
						System.out.println(query);
						try {
							statement = connection.createStatement();
							statement.executeQuery(query);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else {JOptionPane.showMessageDialog(btnModifier, "Saisir un matricule d'un client!");}
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnModifier.setBounds(147, 334, 127, 40);
		frame.getContentPane().add(btnModifier);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(487, 69, 452, 454);
		frame.getContentPane().add(scrollPane);

		JTable table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "Delete From Client where NumClt="+textFieldMat.getText();
				System.out.println(query);
				try {
					statement = connection.createStatement();
					statement.executeQuery(query);
					nbrClt--;
					System.out.println(nbrClt);
					textFieldMat.setText("");
					query = "Select * From Client order by NumClt";
					ResultSet res = statement.executeQuery(query);
					table.setModel(DbUtils.resultSetToTableModel(res));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSupprimer.setBounds(284, 334, 127, 40);
		frame.getContentPane().add(btnSupprimer);
		
		JButton btnClose = new JButton("main");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
				window.setVisible(true);
				
				frame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBackground(new Color(255, 255, 255));
		btnClose.setBounds(10, 6, 76, 53);
		frame.getContentPane().add(btnClose);
		
		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "Select * From Client order by NumClt";
				try {
					statement = connection.createStatement();
					ResultSet res = statement.executeQuery(query);
					table.setModel(DbUtils.resultSetToTableModel(res));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAfficher.setBounds(10, 394, 401, 40);
		frame.getContentPane().add(btnAfficher);
		
		JButton btnHistorique = new JButton("Historique");
		btnHistorique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Historique windowH = new Historique(textFieldMat.getText());
				windowH.setVisible(true);
			}
		});
		btnHistorique.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHistorique.setBounds(10, 445, 401, 40);
		frame.getContentPane().add(btnHistorique);
		
	}
	
	private void initialize_1() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ELMOKRETAR", "nabil");
			System.out.println("succ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 128, 128));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(150, 150, 605, 450);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String ch = "Select count(*) From Client";
		try {
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery(ch);
			res.next();
			nbrClt = res.getInt(1);
			System.out.println(nbrClt);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//p.setBackground(new Color(0, 128, 128));
		//p.setForeground(new Color(0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("La gestion des clients");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(170, 11, 199, 32);
		frame.getContentPane().add(lblNewLabel);

		lblMat = new JLabel("Matriculede client:");
		lblMat.setForeground(new Color(255, 255, 255));
		lblMat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMat.setBounds(0, 69, 111, 32);
		frame.getContentPane().add(lblMat);

		lblNom = new JLabel("Nom de client:");
		lblNom.setForeground(new Color(255, 255, 255));
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNom.setBounds(0, 106, 111, 32);
		frame.getContentPane().add(lblNom);

		lblPrenom = new JLabel("Prenom de client:");
		lblPrenom.setForeground(new Color(255, 255, 255));
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrenom.setBounds(0, 145, 111, 32);
		frame.getContentPane().add(lblPrenom);

		lblNumeroTel = new JLabel("Numero de telephone:");
		lblNumeroTel.setForeground(new Color(255, 255, 255));
		lblNumeroTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroTel.setBounds(0, 186, 137, 32);
		frame.getContentPane().add(lblNumeroTel);

		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(0, 226, 111, 32);
		frame.getContentPane().add(lblEmail);

		lblType = new JLabel("Type de client:");
		lblType.setForeground(new Color(255, 255, 255));
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblType.setBounds(0, 264, 111, 32);
		frame.getContentPane().add(lblType);

		textFieldMat = new JTextField();
		textFieldMat.setBounds(141, 77, 111, 24);
		frame.getContentPane().add(textFieldMat);
		textFieldMat.setColumns(10);

		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(141, 114, 111, 24);
		frame.getContentPane().add(textFieldNom);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(141, 153, 111, 24);
		frame.getContentPane().add(textFieldPrenom);

		textFieldNumeroTel = new JTextField();
		textFieldNumeroTel.setColumns(10);
		textFieldNumeroTel.setBounds(141, 194, 111, 24);
		frame.getContentPane().add(textFieldNumeroTel);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(141, 234, 111, 24);
		frame.getContentPane().add(textFieldEmail);

		textFieldType = new JTextField();
		textFieldType.setColumns(10);
		textFieldType.setBounds(141, 272, 111, 24);
		frame.getContentPane().add(textFieldType);
		

		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        nbrClt++;
		        String query = "Insert into Client values (" + nbrClt + ",'" + textFieldNom.getText() + "','" + textFieldPrenom.getText()
				        + "','" + textFieldNumeroTel.getText() + "','" + textFieldEmail.getText() + "','" + textFieldType.getText() + "')";
		        textFieldNom.setText("");
		        textFieldPrenom.setText("");
		        textFieldNumeroTel.setText("");
		        textFieldEmail.setText("");
		        textFieldType.setText("");
		        System.out.println(query);
		        try {
			        statement = connection.createStatement();
			        statement.execute(query);
		        } catch (Exception e1) {
			        // TODO Auto-generated catch block
			        e1.printStackTrace();
		        }

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(405, 74, 145, 40);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("<--");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
				//window.setVisible(true);
				
				frame.dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(0, 0, 61, 25);
		frame.getContentPane().add(btnNewButton_2);
	}
}
