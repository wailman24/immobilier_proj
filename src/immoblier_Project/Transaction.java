package immoblier_Project;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

public class Transaction {

    private JFrame frame;
    private JTextField NumTran;
    private JTextField NumClt;
    private JTextField NumBiens;
    private JTextField Date_T;
    private JTextField montant;
    private Connection connection;
    private Statement statement;
    private JTable tabletran;
    private JTextField TypeTran;

    /**
     * Launch the application.
     */
   /* public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Application window = new Application();
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
    
    public Transaction() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish connection
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ProjetPoo", "oculus");

            // Connection established successfully
            System.out.println("Connection established successfully!");
        } catch (ClassNotFoundException e) {
            // Handle errors related to JDBC driver loading
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }

        frame = new JFrame();
        frame.getContentPane().setForeground(new Color(0, 0, 0));
        frame.getContentPane().setBackground(new Color(64, 128, 128));
        frame.setBounds(100, 100, 1363, 791);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Ajouter une transaction :");
        lblNewLabel.setBackground(new Color(64, 128, 128));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 57, 415, 54);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        frame.getContentPane().add(lblNewLabel);

        NumTran = new JTextField();
        NumTran.setBounds(224, 159, 189, 36);
        frame.getContentPane().add(NumTran);
        NumTran.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Numéro Transaction :");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(25, 159, 189, 36);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Numéro Client :");
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setBounds(25, 324, 189, 33);
        lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        frame.getContentPane().add(lblNewLabel_1_1);

        NumClt = new JTextField();
        NumClt.setBounds(224, 324, 189, 36);
        NumClt.setColumns(10);
        frame.getContentPane().add(NumClt);

        JLabel lblNewLabel_1_2 = new JLabel("Numéro Biens :");
        lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_2.setBounds(25, 402, 189, 36);
        lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        frame.getContentPane().add(lblNewLabel_1_2);

        NumBiens = new JTextField();
        NumBiens.setBounds(224, 402, 189, 36);
        NumBiens.setColumns(10);
        frame.getContentPane().add(NumBiens);

        JLabel lblNewLabel_1_3 = new JLabel("Date Transaction :");
        lblNewLabel_1_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_3.setBounds(25, 486, 189, 33);
        lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        frame.getContentPane().add(lblNewLabel_1_3);

        Date_T = new JTextField();
        Date_T.setBounds(224, 483, 189, 36);
        Date_T.setColumns(10);
        frame.getContentPane().add(Date_T);

        JLabel lblNewLabel_1_4 = new JLabel("montant Transaction :");
        lblNewLabel_1_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_4.setBounds(25, 568, 189, 33);
        lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        frame.getContentPane().add(lblNewLabel_1_4);

        montant = new JTextField();
        montant.setBounds(224, 565, 189, 36);
        montant.setColumns(10);
        frame.getContentPane().add(montant);

        JButton btnaddtran = new JButton("Confirmation");
        btnaddtran.setBounds(208, 646, 217, 45);
        btnaddtran.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int Tran = Integer.parseInt(NumTran.getText());
                int Clt = Integer.parseInt(NumClt.getText());
                int Biens = Integer.parseInt(NumBiens.getText());
                String DateStr = Date_T.getText(); // Chaîne de date
                String type = TypeTran.getText();


                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy"); // Format de date attendu
                Date date = null;

                try {
                    date = dateFormat.parse(DateStr); // Convertir la chaîne de date en objet Date
                } catch (ParseException ex) {
                    ex.printStackTrace(); // Gérer les erreurs de conversion
                }

                int mont = Integer.parseInt(montant.getText());
                String formattedDate = dateFormat.format(date);

                System.out.println(Tran + "-" + type + "-" + Clt + "-" + Biens + "-" + formattedDate + "-" + mont);

                String query = "INSERT INTO Transac values(" + Tran + ",'" + type + "'," + Clt + "," + Biens + ",'" + formattedDate + "'," + mont + ")";
                String query1 = "select nom , prenom from Client where numClt ="+ Clt +""; 
                String query2 = "select * from biensImmobiliers where NumBiens ="+ Biens +""; 
                
                
                
                try {
                    statement = connection.createStatement();

                    statement.executeUpdate(query);
                    ResultSet res = statement.executeQuery(query1);
                    res.next();
                    String nom =res.getString(1);
                    String prenom = res.getString(2);
                    
                    ResultSet res1 = statement.executeQuery(query2);
                    res1.next();
                    String typebiens = res1.getString(2);
                    String localisation= res1.getString(3);
                    int surface = res1.getInt(4);
                    int nbr_chambre =res1.getInt(5);

                    NumTran.setText("");
                    TypeTran.setText("");
                    NumClt.setText("");
                    NumBiens.setText("");
                    Date_T.setText("");
                    montant.setText("");

                    // Générer le fichier de contrat
                    String contenuContrat = 
                    		  "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n"
                    		+ "										Contrat de "+ type +"\r\n"
                    		+ "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n" +
                            "Numéro de Transaction : " + Tran + "\n" +
                    		"Type Transaction : " + type + "\n" +
                            "Numéro de Client : " + Clt + "\n" +
                            "Nom du Client : " + nom + "\n" +
                            "Prénom du Client : " + prenom + "\n" +
                            "Numéro de Biens : " + Biens + "\n" +
                            "Date de Transaction : " + formattedDate + "\n" +
                            "Montant de la Transaction : " + mont + "\n\n" +
                            "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n" +
                            "Informations sur la transaction :\n\n"+
                            "	 Le bien numéro "+Biens+" a été mis en "+type+",\n"+
                            "	Pour le client numéro "+Clt+" definie par son nom "+nom+", et son prénom "+prenom+",\n"+
                            "	le "+formattedDate+" pour un montant de "+mont+" DA. \n\n" +

                            "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n" +

                            "Conformité sur le contrat :\n\n"+
                            "	 Ce contrat certifie que le client "+nom+" "+prenom+" a effectué une transaction de "+type+",\n"+
                            "   	concernant la/le "+typebiens+", sous le numéro "+Biens+". Le montant de la transaction a été dûment enregistré,\n"+
                            "   	et accepté par toutes les parties concernées.\n\n"+
                            "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
                            "Information sur le biens concerné:\n\n"+
                            "	 Le/La "+typebiens+" se situe à l'adresse "+localisation+",\n"+
                            "	avec une surface de "+surface+" m2,\n"+
                            "	pour un total de "+nbr_chambre+" chambres.\n\n\n"+

                            "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n"+
                            "                                                                                        ||                                                                                                   \n"+
                            "	signature de l'agent	: .........................				||				signature du client : ............................. 								  \n"+
                            "                                                                                        ||                                                                                                   \n"+
                            "                                                                                        ||                                                                                                   \n"+
                            "                                                                                        ||                                                                                                   \n"+
                            "                                                                                        ||                                                                                                   \n"+
                            "                                                                                        ||                                                                                                   \n"+
                            "                                                                                        ||                                                                                                   \n"+
                            "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
                    
                    
                    String nomFichier = "Contrat_" + Tran + ".txt";
                    genererContrat(nomFichier, contenuContrat);

                    JOptionPane.showMessageDialog(btnaddtran, "Transaction enregistrée et contrat généré !");

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnaddtran.setBackground(new Color(255, 255, 255));
        btnaddtran.setForeground(new Color(0, 0, 0));
        btnaddtran.setFont(new Font("Tahoma", Font.PLAIN, 16));
        frame.getContentPane().add(btnaddtran);

        JButton btndisplay = new JButton("Liste des transactions");
        btndisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String query = "SELECT * FROM Transac";

                try {
                    statement = connection.createStatement();
                    ResultSet res = statement.executeQuery(query);

                    tabletran.setModel(DbUtils.resultSetToTableModel(res));

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btndisplay.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btndisplay.setBackground(new Color(255, 255, 255));
        btndisplay.setBounds(473, 80, 222, 45);
        frame.getContentPane().add(btndisplay);
        
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
		btnClose.setBounds(10, 6, 63, 20);
		frame.getContentPane().add(btnClose);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
        scrollPane.setBounds(473, 159, 809, 428);
        frame.getContentPane().add(scrollPane);

        tabletran = new JTable();
        scrollPane.setViewportView(tabletran);
        
        TypeTran = new JTextField();
        TypeTran.setColumns(10);
        TypeTran.setBounds(224, 241, 189, 36);
        frame.getContentPane().add(TypeTran);
        
        JLabel lblNewLabel_1_4_1 = new JLabel("Type Transaction :");
        lblNewLabel_1_4_1.setForeground(Color.WHITE);
        lblNewLabel_1_4_1.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblNewLabel_1_4_1.setBounds(25, 241, 189, 36);
        frame.getContentPane().add(lblNewLabel_1_4_1);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public static void genererContrat(String nomFichier, String contenuContrat) {
        String directoryPath = "contrats";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdir();  // Crée le répertoire s'il n'existe pas
        }

        String filePath = directoryPath + File.separator + nomFichier;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(contenuContrat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
