package immoblier_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;

public class RendezVous {

	private JFrame frame;
	private JTextField rendez_vous;
	private JLabel lblNumagt;
	private JLabel lblNewLabel_2;
	private JTextField agent;
	private JTextField client;

	private Connection connection;
	private Statement statement;
	private JLabel lblLaDate;
	private JTextField date;
	private JButton btnafficher;
	private JTable tableR;
	private JLabel lblNewLabel_1;
	private JTextField bien;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RendezVous window = new RendezVous();
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
	
	public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
	
	public RendezVous() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "mansour_ouahchia", "wail");
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ELMOKRETAR", "nabil");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 906, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Rendez vous");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(33, 139, 120, 40);
		frame.getContentPane().add(lblNewLabel);

		rendez_vous = new JTextField();
		rendez_vous.setBounds(183, 139, 260, 40);
		frame.getContentPane().add(rendez_vous);
		rendez_vous.setColumns(10);

		lblNumagt = new JLabel("Agent");
		lblNumagt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumagt.setBounds(33, 189, 120, 40);
		frame.getContentPane().add(lblNumagt);

		lblNewLabel_2 = new JLabel("Client");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(33, 239, 120, 40);
		frame.getContentPane().add(lblNewLabel_2);

		agent = new JTextField();
		agent.setColumns(10);
		agent.setBounds(183, 189, 260, 40);
		frame.getContentPane().add(agent);

		client = new JTextField();
		client.setColumns(10);
		client.setBounds(183, 239, 260, 40);
		frame.getContentPane().add(client);

		JButton btninserer = new JButton("inserer");
		btninserer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LocalDate today = LocalDate.now();
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String rdv = rendez_vous.getText();
				String agt = agent.getText();
				String clt = client.getText();
				String Bien = bien.getText();
				String Date = date.getText();

				LocalDate userDate;
				try {
					userDate = LocalDate.parse(Date, inputFormatter);
					System.out.println(userDate);				
					} catch (DateTimeParseException e2) {
					JOptionPane.showMessageDialog(btninserer,"Invalide date format. Enter la date on format de (dd-mm-yyyy) format.");
					return;
				}

				if (today.isEqual(userDate) || (today.isBefore(userDate))) {

					String checkQuery = "SELECT * FROM AgentBiens WHERE NumAgt = " + agt + " AND NumBiens = " + Bien;
					try {
						Statement checkStatement = connection.createStatement();
						ResultSet resultSet = checkStatement.executeQuery(checkQuery);

						if (resultSet.next()) {

							String query = "INSERT INTO RDV VALUES ('" + rdv + "' , '" + agt + "' , '" + clt + "',"+ Bien + ", TO_DATE('"+Date+"','DD-MM-YYYY'))";
							System.out.println(query);
							
							//String query = "INSERT INTO RDV (NumRdv,NumAgt,NumClt,Date_rdv) VALUES (" + rdv + " , " + agt + " , " + clt + ",'"+Date+"')";

							try {
								statement = connection.createStatement();
								statement.executeQuery(query);

								rendez_vous.setText("");
								agent.setText("");
								client.setText("");
								bien.setText("");
								date.setText("");

								JOptionPane.showMessageDialog(btninserer, "le rendez vous a bien ete inserer ! ");

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								// e1.printStackTrace();
								JOptionPane.showMessageDialog(btninserer,"Les cases nom,prenom et typeClient sont obligatoire!");
							}
						} else {
							 //Property does not belong to the agent, show error message
							JOptionPane.showMessageDialog(btninserer,"Le bien selectionné n'appartient pas à l'agent!");
						}
					} catch (SQLException e1) {
						// Handle error during check query execution
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(btninserer, "La date est incorrecte ");
				}
			}

		});
		btninserer.setBackground(new Color(70, 130, 180));
		btninserer.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btninserer.setBounds(10, 389, 150, 40);
		frame.getContentPane().add(btninserer);

		lblLaDate = new JLabel("Date");
		lblLaDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLaDate.setBounds(33, 339, 120, 40);
		frame.getContentPane().add(lblLaDate);

		date = new JTextField();
		date.setColumns(10);
		date.setBounds(183, 339, 260, 40);
		frame.getContentPane().add(date);

		btnafficher = new JButton("afficher");
		btnafficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String query = "SELECT NumRdv,NumAgt,NumClt,NumBien,TO_CHAR(Date_rdv, 'DD-MM-YYYY') AS Date_rdv FROM RDV";

				try {
					statement = connection.createStatement();
					ResultSet res = statement.executeQuery(query);

					tableR.setModel(DbUtils.resultSetToTableModel(res));

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnafficher.setBackground(new Color(70, 130, 180));
		btnafficher.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnafficher.setBounds(10, 452, 472, 40);
		frame.getContentPane().add(btnafficher);

		JScrollPane tableE = new JScrollPane();
		tableE.setBounds(492, 46, 390, 446);
		frame.getContentPane().add(tableE);

		tableR = new JTable();
		tableE.setViewportView(tableR);

		JButton btnModifier = new JButton("modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LocalDate today = LocalDate.now();
				DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String rdv = rendez_vous.getText();
				String agt = agent.getText();
				String clt = client.getText();
				String Bien = bien.getText();
				String Date = date.getText();

				LocalDate userDate;
				try {
					userDate = LocalDate.parse(Date, inputFormatter);
				} catch (DateTimeParseException e2) {
					JOptionPane.showMessageDialog(btninserer,
							"Invalide date format. Enter la date on format de (dd-mm-yyyy) format.");
					return;
				}

				if (today.isEqual(userDate) || (today.isBefore(userDate))) {

					String checkQuery = "SELECT * FROM RDV WHERE NumRdv = '" + rdv + "'";
					boolean rdvExists = false;
					try {
						Statement checkStatement = connection.createStatement();
						ResultSet resultSet = checkStatement.executeQuery(checkQuery);
						rdvExists = resultSet.next();

						resultSet.close();
						checkStatement.close();
					} catch (SQLException e3) {
						e3.printStackTrace();
						JOptionPane.showMessageDialog(btninserer, "Une erreur est survenue. Veuillez réessayer.");
						return;
					}

					if (rdvExists) {

						String updateQuery = " UPDATE RDV SET NumAgt = '" + agt + "', NumClt='" + clt + "',NumBiens='"
								+ Bien + "', Date_rdv='" + Date + "' WHERE NumRdv = '" + rdv + "' ";
						try {
							statement = connection.createStatement();
							statement.executeUpdate(updateQuery);
							
							rendez_vous.setText("");
							agent.setText("");
							client.setText("");
							bien.setText("");
							date.setText("");
							
							JOptionPane.showMessageDialog(btninserer, "le rendez vous a bien ete modifier ! ");

						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(btninserer,
									"Une erreur est survenue lors de la modification.");
						}
					} else {

						JOptionPane.showMessageDialog(btninserer,
								"Le rendez-vous avec le numéro " + rdv + " n'existe pas dans la table RDV.");
					}
				} else {
					JOptionPane.showMessageDialog(btninserer, "La date est incorrecte ");
				}

			}
		});

		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnModifier.setBackground(new Color(70, 130, 180));
		btnModifier.setBounds(172, 389, 150, 40);
		frame.getContentPane().add(btnModifier);

		JButton btnSuprimer = new JButton("suprimer");
		btnSuprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String rdv = rendez_vous.getText();

				String checkQuery = "SELECT * FROM RDV WHERE NumRdv = '" + rdv + "'";
				boolean rdvExists = false;
				try {
					Statement checkStatement = connection.createStatement();
					ResultSet resultSet = checkStatement.executeQuery(checkQuery);
					rdvExists = resultSet.next();

					resultSet.close();
					checkStatement.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
					JOptionPane.showMessageDialog(btninserer, "Une erreur est survenue. Veuillez réessayer.");
					return;
				}

				if (rdvExists) {

					String query = " DELETE FROM RDV WHERE NumRdv='" + rdv + "'";
					try {
						statement = connection.createStatement();
						statement.executeUpdate(query);

						rendez_vous.setText("");
						agent.setText("");
						client.setText("");
						bien.setText("");
						date.setText("");

						JOptionPane.showMessageDialog(btninserer, "le rendez vous a bien ete suprimer ! ");

					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(btninserer, "Une erreur est survenue lors de la supression.");
					}
				} else {

					JOptionPane.showMessageDialog(btninserer,
							"Le rendez-vous avec le numéro " + rdv + " n'existe pas dans la table RDV.");
				}
			}

		});
		btnSuprimer.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSuprimer.setBackground(new Color(70, 130, 180));
		btnSuprimer.setBounds(336, 389, 150, 40);
		frame.getContentPane().add(btnSuprimer);
		
		JButton btnClose = new JButton("main");
		btnClose.setIcon(new ImageIcon("home.png"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
				window.setVisible(true);
				
				frame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBackground(new Color(255, 255, 255));
		btnClose.setBounds(10, 6, 143, 95);
		frame.getContentPane().add(btnClose);

		lblNewLabel_1 = new JLabel("Bien");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(33, 289, 120, 40);
		frame.getContentPane().add(lblNewLabel_1);

		bien = new JTextField();
		bien.setColumns(10);
		bien.setBounds(183, 289, 260, 40);
		frame.getContentPane().add(bien);
	}
}