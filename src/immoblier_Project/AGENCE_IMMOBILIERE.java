package immoblier_Project;

import javax.swing.JFrame;
import javax.swing.JButton;
//import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Image;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
//import javax.swing.JScrollPane;

public class AGENCE_IMMOBILIERE {

	JFrame frame1;
	JPanel panel;
	JPanel panel_1;
	JPanel panel_2;
	JTable table;
	public static Connection connection;
	public static Statement statement;
	JTextField textField;
	
	boolean exist;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public AGENCE_IMMOBILIERE() {
		initialize();
	}
	
	public void setVisible(boolean visible) {
        frame1.setVisible(visible);
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "mansour_ouahchia", "wail");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ELMOKRETAR", "nabil");
			System.out.println("succ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame1 = new JFrame();
		frame1.setResizable(false);
		frame1.getContentPane().setBackground(new Color(255, 255, 255));
		frame1.getContentPane().setForeground(new Color(0, 0, 0));
		frame1.setBounds(100, 100, 768, 535);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		frame1.setLocationRelativeTo(null);
		
		 panel = new JPanel() {
	            @Override
	            protected void paintComponent(java.awt.Graphics g) {
	                super.paintComponent(g);
	                ImageIcon icon = new ImageIcon("C:\\Users\\Asus\\OneDrive\\Desktop\\11567.jpg"); // Replace with your image path
	                Image image = icon.getImage();
	                int width = panel.getWidth();
	                int height = panel.getHeight();
	                g.drawImage(image, 0, 0, width, height, this);
	            }
	        };
	        panel.setBackground(Color.WHITE);
	        panel.setBounds(0, 0, 754, 498);
	        frame1.getContentPane().add(panel);
	        panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 754, 38);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Biens");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ajouter une demande");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
//				ges_Clt.setVisible(false);
				
				panel_2 = new JPanel();
				panel_2.setBackground(new Color(240, 240, 240));
				panel_2.setBounds(107, 109, 384, 135);
				panel.add(panel_2);
				panel_2.setLayout(null);
				
				textField = new JTextField();
				textField.setText("");
				textField.setBounds(10, 30, 171, 27);
				panel_2.add(textField);
				textField.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("Entrer votre email");
				lblNewLabel.setForeground(new Color(0, 0, 0));
				lblNewLabel.setBounds(212, 36, 105, 25);
				panel_2.add(lblNewLabel);
				
				JButton btnNewButton = new JButton("Confirmer");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String email = textField.getText();
						String ch = "Select count(*) From Client where email='"+email+"'";
						String ch_1 = "Select NumClt From Client where email='"+email+"'";
						try {
							statement = connection.createStatement();
							ResultSet res = statement.executeQuery(ch);
							res.next();
							if(res.getInt(1) != 0) {
								res = statement.executeQuery(ch_1);
								res.next();
								Recherche windowR = new Recherche(res.getInt(1));
								windowR.setVisible(true);
								
								frame1.dispose();
								/*System.out.println(c.toString());
                                panel_2.setVisible(false);
								panel.setVisible(false);
								panel_1 = new JPanel();
								panel_1.setBounds(0, 0, 589, 411);
								frame1.getContentPane().add(panel_1);
								panel_1.setLayout(null);
								c.rechercher(panel_1,panel,ges_biens);*/
							}
							else { JOptionPane.showMessageDialog(btnNewButton, "Ce email n'existe pas!"); }
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				btnNewButton.setBounds(144, 68, 95, 25);
				panel_2.add(btnNewButton);
				
				JButton btnCreeNouveauCompte = new JButton("Cree nouveau compte");
				btnCreeNouveauCompte.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Client1 windowClt = new Client1(0);
						windowClt.setVisible(true);
						
						
					}
				});
				btnCreeNouveauCompte.setBounds(105, 101, 175, 25);
				panel_2.add(btnCreeNouveauCompte);
				
				JButton btnNewButton_2 = new JButton("x");
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						panel_2.setVisible(false);
						panel.setVisible(true);
						//ges_biens.setVisible(true);
						//ges_Clt.setVisible(true);
					}
				});
				btnNewButton_2.setToolTipText("Close");
				btnNewButton_2.setBackground(new Color(255, 255, 255));
				btnNewButton_2.setBounds(345, 0, 41, 19);
				panel_2.add(btnNewButton_2);
				btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				
				panel.setVisible(true);
			}
				
			
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Gestion des Biens");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							tryone window = new tryone();
							window.setVisible(true);
							//window.getFrame().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame1.setVisible(false);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Transaction");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transaction windowR = new Transaction();
				windowR.setVisible(true);
				
				frame1.setVisible(false);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_2 = new JMenu("clients");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Gestion des Clients");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*panel.setVisible(false);
				ges_biens.setVisible(false);
				JPanel panel_4 = new JPanel();
				//panel_4.setBounds(0, 0, 589, 411);
				panel_4.setBounds(0, 0, 949, 531);
				frame1.getContentPane().add(panel_4);
				panel_4.setLayout(null);*/
				
				//Client.ajouteClient(frame1,panel_4,panel,ges_Clt);
				Client1 windowClt = new Client1(1);
				windowClt.setVisible(true);
				
				frame1.dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Rendez-Vous");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RendezVous windowR = new RendezVous();
				windowR.setVisible(true);
				
				frame1.setVisible(false);
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_1 = new JMenu("agents");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Gestion des Agents");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agents windowClt = new agents();
				windowClt.setVisible(true);
				
				frame1.dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("affectation des biens");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affectBiens windowClt = new affectBiens();
				windowClt.setVisible(true);
				
				frame1.dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		//btnNewButton_1_1.setVisible(false);
		
		//

		/*String ch = "Select count(*) From Client";
		try {
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery(ch);
			res.next();
			Client.nbrClt = res.getInt(1);
			//System.out.println("=" + Client.nbrClt);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		

	}

	public JFrame getFrame() {
		return frame1;
	}

	public void setFrame(JFrame frame) {
		this.frame1 = frame;
	}
}