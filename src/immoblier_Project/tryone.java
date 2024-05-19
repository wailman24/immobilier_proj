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
			
			connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ELMOKRETAR","nabil");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 887, 663);
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
					JOptionPane.showMessageDialog(frame, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnajouter.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnajouter.setBounds(550, 239, 221, 43);
		frame.getContentPane().add(btnajouter);
		
		JLabel lblNewLabel = new JLabel("Numero bien : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(68, 130, 134, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTypeBien = new JLabel("type bien : ");
		lblTypeBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTypeBien.setBounds(68, 186, 134, 43);
		frame.getContentPane().add(lblTypeBien);
		
		JLabel lblLocalisation = new JLabel("localisation : ");
		lblLocalisation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLocalisation.setBounds(68, 239, 134, 43);
		frame.getContentPane().add(lblLocalisation);
		
		JLabel lblPrixVente = new JLabel("prix vente : ");
		lblPrixVente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrixVente.setBounds(68, 291, 134, 43);
		frame.getContentPane().add(lblPrixVente);
		
		JLabel lblPrixLocation = new JLabel("prix location : ");
		lblPrixLocation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrixLocation.setBounds(68, 344, 134, 43);
		frame.getContentPane().add(lblPrixLocation);
		
		JLabel lblTaille = new JLabel("taille : ");
		lblTaille.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTaille.setBounds(68, 397, 134, 43);
		frame.getContentPane().add(lblTaille);
		
		txtNumBiens = new JTextField();
		txtNumBiens.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumBiens.setBounds(200, 130, 280, 40);
		frame.getContentPane().add(txtNumBiens);
		txtNumBiens.setColumns(10);
		
		txtTypeBiens = new JTextField();
		txtTypeBiens.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTypeBiens.setColumns(10);
		txtTypeBiens.setBounds(200, 183, 280, 40);
		frame.getContentPane().add(txtTypeBiens);
		
		txtlocalisation = new JTextField();
		txtlocalisation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtlocalisation.setColumns(10);
		txtlocalisation.setBounds(200, 239, 280, 40);
		frame.getContentPane().add(txtlocalisation);
		
		txtPrixVent = new JTextField();
		txtPrixVent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPrixVent.setColumns(10);
		txtPrixVent.setBounds(200, 292, 280, 40);
		frame.getContentPane().add(txtPrixVent);
		
		txtprixLocation = new JTextField();
		txtprixLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtprixLocation.setColumns(10);
		txtprixLocation.setBounds(200, 347, 280, 40);
		frame.getContentPane().add(txtprixLocation);
		
		txttaille = new JTextField();
		txttaille.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txttaille.setColumns(10);
		txttaille.setBounds(200, 397, 280, 40);
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
		btnNewButton.setBounds(550, 396, 221, 43);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 450, 853, 158);
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
		btnSupprimerBien.setBounds(550, 291, 221, 43);
		frame.getContentPane().add(btnSupprimerBien);
		
		JButton btnModifierBien = new JButton("modifier bien");
		btnModifierBien.setForeground(new Color(0, 0, 205));
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
					
					
				}catch(Exception e4) {
					JOptionPane.showMessageDialog(frame, "Error: " + e4.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifierBien.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModifierBien.setBounds(550, 343, 221, 43);
		frame.getContentPane().add(btnModifierBien);
		
		JButton btnaffecter = new JButton("affecter des biens");
		btnaffecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                affectBiens affectWindow = new affectBiens();
                affectWindow.setVisible(true);

                // Close the current window
                frame.dispose();
				
			}
		});
		btnaffecter.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnaffecter.setBounds(550, 116, 221, 43);
		frame.getContentPane().add(btnaffecter);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion des Biens");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 31));
		lblNewLabel_1.setBounds(246, 30, 594, 76);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnAgents = new JButton("agents");
		btnAgents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				agents agentWindow = new agents();
				agentWindow.setVisible(true);

                // Close the current window
                frame.dispose();
			}
		});
		btnAgents.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAgents.setBounds(550, 181, 221, 43);
		frame.getContentPane().add(btnAgents);
		
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
	}
}
