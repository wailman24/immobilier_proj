package immoblier_Project;

import java.awt.Color;
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

public class Recherche {

	private JFrame frame;
	public static Connection connection;
	public static Statement statement;
	private JTextField textFieldTypeBiens;
	private JTextField textFieldLocal;
	private JTextField textFieldPrixVent;
	private JTextField textFieldPrixLocal;
	private JTextField textFieldTaille;
	private JTextField textFieldNumeroBiens;
	private JTextField textFieldPour;
	private JLabel lblPour;
	private JLabel lblNumeroBiens;
	private JLabel lblTypeBiens;
	private JLabel lblLocal;
	private JLabel lblPrixVent;
	private JLabel lblPrixLocal;
	private JLabel lblTaille;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recherche window = new Recherche();
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
	public Recherche(int n) {
		initialize(n);
	}

	public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int n) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ELMOKRETAR","nabil");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldTypeBiens = new JTextField();
		textFieldTypeBiens.setBounds(10, 46, 106, 20);
		frame.getContentPane().add(textFieldTypeBiens);
		textFieldTypeBiens.setColumns(10);
		
		textFieldLocal = new JTextField();
		textFieldLocal.setColumns(10);
		textFieldLocal.setBounds(126, 46, 106, 20);
		frame.getContentPane().add(textFieldLocal);
		
		textFieldPrixVent = new JTextField();
		textFieldPrixVent.setColumns(10);
		textFieldPrixVent.setBounds(242, 46, 106, 20);
		frame.getContentPane().add(textFieldPrixVent);
		
		textFieldPrixLocal = new JTextField();
		textFieldPrixLocal.setColumns(10);
		textFieldPrixLocal.setBounds(358, 46, 106, 20);
		frame.getContentPane().add(textFieldPrixLocal);
		
		textFieldTaille = new JTextField();
		textFieldTaille.setColumns(10);
		textFieldTaille.setBounds(474, 46, 106, 20);
		frame.getContentPane().add(textFieldTaille);
		
		JButton btnRecherche = new JButton("Rechercher");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "Select * From biensImmobiliers where ";
				boolean exist=false;
				if(!textFieldTypeBiens.getText().isEmpty()) {query = query + "TypeBiens = '"+textFieldTypeBiens.getText()+"' ";exist=true;}
				if(!textFieldLocal.getText().isEmpty()) {
					if(exist) {query = query + "and localisation = '"+textFieldLocal.getText()+"' ";}
					else {query = query + "localisation = '"+textFieldLocal.getText()+"' ";exist=true;}}
				if(!textFieldPrixVent.getText().isEmpty()) {
					if(exist) {query = query + "and PrixVent = "+textFieldPrixVent.getText()+" ";}
					else {query = query + "PrixVent = '"+textFieldPrixVent.getText()+"' ";exist=true;}}
				if(!textFieldPrixLocal.getText().isEmpty()) {
					if(exist) {query = query + "and prixLocation = "+textFieldPrixLocal.getText()+" ";}
					else {query = query + "prixLocation = '"+textFieldPrixLocal.getText()+"' ";exist=true;}}
				if(!textFieldTaille.getText().isEmpty()) {
					if(exist) {query = query + "and taille = "+textFieldTaille.getText();}
					else {query = query + "taille = '"+textFieldTaille.getText()+"' ";exist=true;}}
				if(query.contentEquals("Select * From biensImmobiliers where ")) {query = query.substring(0, 30);}
				System.out.println(query);
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
		btnRecherche.setBounds(244, 72, 102, 23);
		frame.getContentPane().add(btnRecherche);
		
		textFieldNumeroBiens = new JTextField();
		textFieldNumeroBiens.setColumns(10);
		textFieldNumeroBiens.setBounds(126, 138, 106, 20);
		frame.getContentPane().add(textFieldNumeroBiens);
		
		lblPour = new JLabel("          Pour:");
		lblPour.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPour.setBounds(10, 166, 106, 23);
		frame.getContentPane().add(lblPour);
		
		textFieldPour = new JTextField();
		textFieldPour.setColumns(10);
		textFieldPour.setBounds(126, 168, 106, 20);
		frame.getContentPane().add(textFieldPour);
		
		JButton btnDemande = new JButton("Demande");
		btnDemande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ch = textFieldNumeroBiens.getText();
				String ch1 = textFieldPour.getText();
				textFieldNumeroBiens.setText("");
				textFieldPour.setText("");
				String ch2 = "Select count(*) From Demande";
				
				try {
					statement = connection.createStatement();
					ResultSet res = statement.executeQuery(ch2);
					res.next();
					int nbr = res.getInt(1)+1;
					System.out.println(nbr);
					String query = "Insert into Demande values("+nbr+","+n+","+ch+",'"+ch1+"',SYSDATE)";
					System.out.println(query);
					statement.executeQuery(query);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDemande.setBounds(259, 167, 89, 23);
		frame.getContentPane().add(btnDemande);
		
		lblNumeroBiens = new JLabel("Numero de biens:");
		lblNumeroBiens.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNumeroBiens.setBounds(10, 136, 106, 23);
		frame.getContentPane().add(lblNumeroBiens);
		
		lblTypeBiens = new JLabel("Type de Biens:");
		lblTypeBiens.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTypeBiens.setBounds(10, 23, 106, 23);
		frame.getContentPane().add(lblTypeBiens);
		
		lblLocal = new JLabel("Localisation:");
		lblLocal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLocal.setBounds(126, 23, 106, 23);
		frame.getContentPane().add(lblLocal);
		
		lblPrixVent = new JLabel("Prix de Vent:");
		lblPrixVent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrixVent.setBounds(242, 23, 106, 23);
		frame.getContentPane().add(lblPrixVent);
		
		lblPrixLocal = new JLabel("Prix de Location:");
		lblPrixLocal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrixLocal.setBounds(358, 23, 106, 23);
		frame.getContentPane().add(lblPrixLocal);
		
		lblTaille = new JLabel("La taille:");
		lblTaille.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTaille.setBounds(474, 23, 106, 23);
		frame.getContentPane().add(lblTaille);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 205, 571, 126);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
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
		
		String query = "Select * From biensImmobiliers";
		try {
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(res));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
