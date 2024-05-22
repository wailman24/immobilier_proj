package immoblier_Project;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

public class Historique {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JScrollPane scrollPane_1;
	private JLabel lblLeTransaction;
	private JTable table_2;
	private JScrollPane scrollPane_2;
	
	public static Connection connection;
	public static Statement statement;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historique window = new Historique();
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
	
	public Historique(String n) {
		initialize(n);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String n) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","mansour_ouahchia","wail");
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ELMOKRETAR", "nabil");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		frame = new JFrame();
		frame.setBounds(1, 1, 965, 570);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Les demandes:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 115, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 929, 103);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblLes = new JLabel("Le rendez-vous:");
		lblLes.setForeground(Color.BLACK);
		lblLes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLes.setBounds(10, 157, 128, 26);
		frame.getContentPane().add(lblLes);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 188, 929, 103);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		lblLeTransaction = new JLabel("Le transaction:");
		lblLeTransaction.setForeground(Color.BLACK);
		lblLeTransaction.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLeTransaction.setBounds(10, 298, 128, 26);
		frame.getContentPane().add(lblLeTransaction);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 328, 929, 103);
		frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		String query1 = "Select * From Demande where NumClt = "+n;
		String query2 = "SELECT NumRdv,NumAgt,NumClt,NumBien,TO_CHAR(Date_rdv, 'DD-MM-YYYY') AS Date_rdv From RDV where NumClt = "+n;
		String query3 = "SELECT NumTran,TypeTran,NumClt,NumBiens,montant,TO_CHAR(Date_T, 'DD-MM-YYYY') AS Date_T From Transac where NumClt = "+n;
		
		try {
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery(query1);
			table.setModel(DbUtils.resultSetToTableModel(res));
			res = statement.executeQuery(query2);
			table_1.setModel(DbUtils.resultSetToTableModel(res));
			res = statement.executeQuery(query3);
			table_2.setModel(DbUtils.resultSetToTableModel(res));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}