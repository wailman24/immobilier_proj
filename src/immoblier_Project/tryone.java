package immoblier_Project;

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
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

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
	private JTextField textFieldNbrCh;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

	/*public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTxtNumBiens() {
		return txtNumBiens;
	}

	public void setTxtNumBiens(JTextField txtNumBiens) {
		this.txtNumBiens = txtNumBiens;
	}

	public JTextField getTxtTypeBiens() {
		return txtTypeBiens;
	}

	public void setTxtTypeBiens(JTextField txtTypeBiens) {
		this.txtTypeBiens = txtTypeBiens;
	}

	public JTextField getTxtlocalisation() {
		return txtlocalisation;
	}

	public void setTxtlocalisation(JTextField txtlocalisation) {
		this.txtlocalisation = txtlocalisation;
	}

	public JTextField getTxtPrixVent() {
		return txtPrixVent;
	}

	public void setTxtPrixVent(JTextField txtPrixVent) {
		this.txtPrixVent = txtPrixVent;
	}

	public JTextField getTxtprixLocation() {
		return txtprixLocation;
	}

	public void setTxtprixLocation(JTextField txtprixLocation) {
		this.txtprixLocation = txtprixLocation;
	}

	public JTextField getTxttaille() {
		return txttaille;
	}

	public void setTxttaille(JTextField txttaille) {
		this.txttaille = txttaille;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public JTable getTableE() {
		return tableE;
	}

	public void setTableE(JTable tableE) {
		this.tableE = tableE;
	}*/

	/**
	 * Create the application.
	 */
	public tryone() {
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
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1020, 663);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JButton btnajouter = new JButton("ajouter bien");
		btnajouter.setForeground(new Color(0, 0, 205));
		btnajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TNumBiens =txtNumBiens.getText();
				String TTypeBiens =txtTypeBiens.getText();
				String Tlocalisation =txtlocalisation.getText();
				String TPrixVent =txtPrixVent.getText();
				String TprixLocation =txtprixLocation.getText();
				String Ttaille =txttaille.getText();
				String NbrCh = textFieldNbrCh.getText();
				
				
				String query = " INSERT INTO biensImmobiliers VALUES ("+TNumBiens+",'"+TTypeBiens+"','"+Tlocalisation+"',"+TPrixVent+","+TprixLocation+","+Ttaille+","+NbrCh+")";
				String ch = txtTypeBiens.getText();
				if(ch.contentEquals("villa") || ch.contentEquals("appartement") || ch.contentEquals("logement")) {
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
					//JOptionPane.showMessageDialog(frame, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					JOptionPane.showMessageDialog(btnajouter,"Les cases type Bien,localisation,prix vente,prix location et surafce sont obligatoire!");
				}
				}else {
					JOptionPane.showMessageDialog(btnajouter,"La valeur inserée pour le type est non valide!");
				}
				
				
			}
		});
		btnajouter.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnajouter.setBounds(10, 532, 152, 43);
		frame.getContentPane().add(btnajouter);
		
		JLabel lblNewLabel = new JLabel("Numero bien : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 132, 134, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTypeBien = new JLabel("type bien : ");
		lblTypeBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTypeBien.setBounds(10, 185, 134, 43);
		frame.getContentPane().add(lblTypeBien);
		
		JLabel lblLocalisation = new JLabel("localisation : ");
		lblLocalisation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLocalisation.setBounds(10, 238, 134, 43);
		frame.getContentPane().add(lblLocalisation);
		
		JLabel lblPrixVente = new JLabel("prix vente : ");
		lblPrixVente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrixVente.setBounds(10, 291, 134, 43);
		frame.getContentPane().add(lblPrixVente);
		
		JLabel lblPrixLocation = new JLabel("prix location : ");
		lblPrixLocation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrixLocation.setBounds(10, 344, 134, 43);
		frame.getContentPane().add(lblPrixLocation);
		
		JLabel lblTaille = new JLabel("taille : ");
		lblTaille.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTaille.setBounds(10, 397, 134, 43);
		frame.getContentPane().add(lblTaille);
		
		txtNumBiens = new JTextField();
		txtNumBiens.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumBiens.setBounds(140, 133, 280, 40);
		frame.getContentPane().add(txtNumBiens);
		txtNumBiens.setColumns(10);
		
		txtTypeBiens = new JTextField();
		txtTypeBiens.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTypeBiens.setColumns(10);
		txtTypeBiens.setBounds(140, 185, 280, 40);
		frame.getContentPane().add(txtTypeBiens);
		
		txtlocalisation = new JTextField();
		txtlocalisation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtlocalisation.setColumns(10);
		txtlocalisation.setBounds(140, 239, 280, 40);
		frame.getContentPane().add(txtlocalisation);
		
		txtPrixVent = new JTextField();
		txtPrixVent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPrixVent.setColumns(10);
		txtPrixVent.setBounds(140, 291, 280, 40);
		frame.getContentPane().add(txtPrixVent);
		
		txtprixLocation = new JTextField();
		txtprixLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtprixLocation.setColumns(10);
		txtprixLocation.setBounds(140, 344, 280, 40);
		frame.getContentPane().add(txtprixLocation);
		
		txttaille = new JTextField();
		txttaille.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txttaille.setColumns(10);
		txttaille.setBounds(140, 397, 280, 40);
		frame.getContentPane().add(txttaille);
		
		JButton btnNewButton = new JButton("Afficher les biens");
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT * FROM biensImmobiliers";
				
				try {
					statement=connection.createStatement();
					ResultSet res=statement.executeQuery(query);
					
					tableE.setModel(DbUtils.resultSetToTableModel(res));
					
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(frame, "Error: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(499, 531, 189, 43);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(470, 96, 526, 343);
		frame.getContentPane().add(scrollPane);
		
		tableE = new JTable();
		scrollPane.setViewportView(tableE);
		
		JButton btnSupprimerBien = new JButton("supprimer bien");
		btnSupprimerBien.setForeground(new Color(0, 0, 205));
		btnSupprimerBien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String TNumBiens =txtNumBiens.getText();
				
				
				
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
					
					
				}catch(Exception e3) {
					JOptionPane.showMessageDialog(frame, "Error: " + e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnSupprimerBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSupprimerBien.setBounds(172, 532, 155, 43);
		frame.getContentPane().add(btnSupprimerBien);
		
		JButton btnModifierBien = new JButton("modifier bien");
		btnModifierBien.setForeground(new Color(0, 0, 205));
		btnModifierBien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*String TNumBiens =txtNumBiens.getText();
				String TTypeBiens =txtTypeBiens.getText();
				String Tlocalisation =txtlocalisation.getText();
				String TPrixVent =txtPrixVent.getText();
				String TprixLocation =txtprixLocation.getText();
				String Ttaille =txttaille.getText();*/
				if(!txtNumBiens.getText().isEmpty()) {
					String query = "Update biensImmobiliers set ";
					//String query = "Update Client set ";
					boolean exist=false;
					if(!txtTypeBiens.getText().isEmpty()) {query = query + "TypeBiens = '"+txtTypeBiens.getText()+"' ";exist=true;}
					if(!txtlocalisation.getText().isEmpty()) {
						if(exist) {query = query + ", localisation = '"+txtlocalisation.getText()+"' ";}
						else {query = query + "localisation = '"+txtlocalisation.getText()+"' ";exist=true;}}
					if(!txtPrixVent.getText().isEmpty()) {
						if(exist) {query = query + ", PrixVent = "+txtPrixVent.getText()+" ";}
						else {query = query + "PrixVent = '"+txtPrixVent.getText()+"' ";exist=true;}}
					if(!txtprixLocation.getText().isEmpty()) {
						if(exist) {query = query + ", prixLocation = "+txtprixLocation.getText()+" ";}
						else {query = query + "prixLocation = '"+txtprixLocation.getText()+"' ";exist=true;}}
					if(!txttaille.getText().isEmpty()) {
						if(exist) {query = query + ", taille = "+txttaille.getText()+" ";}
						else {query = query + "taille = '"+txttaille.getText()+"' ";exist=true;}}
					if(!textFieldNbrCh.getText().isEmpty()) {
						if(exist) {query = query + ", NbrCh = "+textFieldNbrCh.getText()+" ";}
						else {query = query + "NbrCh = '"+textFieldNbrCh.getText()+"' ";exist=true;}}
					if(query.contentEquals("Update biensImmobiliers set ")) {
						JOptionPane.showMessageDialog(btnModifierBien, "Met une modification!");
						//System.out.println(query);
						}
					else {
						query = query +"Where NumBiens = "+txtNumBiens.getText();
						System.out.println(query);
						try {
							statement = connection.createStatement();
							statement.executeQuery(query);
							txtNumBiens.setText("");
							txtTypeBiens.setText("");
							txtlocalisation.setText("");
							txttaille.setText("");
							textFieldNbrCh.setText("");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				else {JOptionPane.showMessageDialog(btnModifierBien, "Saisir un matricule d'un client!");}
			}
		});
		btnModifierBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModifierBien.setBounds(337, 532, 152, 43);
		frame.getContentPane().add(btnModifierBien);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion des Biens");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 31));
		lblNewLabel_1.setBounds(300, 10, 594, 76);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Main");
		btnNewButton_1.setIcon(new ImageIcon("home.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
				window.setVisible(true);
				
				frame.dispose();
			}
		});

		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(10, 10, 134, 99);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNbrCh = new JLabel("Nombre de chambre");
		lblNbrCh.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNbrCh.setBounds(10, 464, 204, 43);
		frame.getContentPane().add(lblNbrCh);
		
		textFieldNbrCh = new JTextField();
		textFieldNbrCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNbrCh.setColumns(10);
		textFieldNbrCh.setBounds(236, 465, 280, 40);
		frame.getContentPane().add(textFieldNbrCh);
	}
}