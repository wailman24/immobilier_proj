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
import javax.swing.JSplitPane;
import javax.swing.JSeparator;

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
			
			connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","mansour_ouahchia","wail");
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 868, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		textFieldTypeBiens = new JTextField();
		textFieldTypeBiens.setBounds(155, 223, 168, 29);
		frame.getContentPane().add(textFieldTypeBiens);
		textFieldTypeBiens.setColumns(10);
		
		textFieldLocal = new JTextField();
		textFieldLocal.setColumns(10);
		textFieldLocal.setBounds(155, 262, 168, 29);
		frame.getContentPane().add(textFieldLocal);
		
		textFieldPrixVent = new JTextField();
		textFieldPrixVent.setColumns(10);
		textFieldPrixVent.setBounds(155, 301, 168, 29);
		frame.getContentPane().add(textFieldPrixVent);
		
		textFieldPrixLocal = new JTextField();
		textFieldPrixLocal.setColumns(10);
		textFieldPrixLocal.setBounds(155, 340, 168, 29);
		frame.getContentPane().add(textFieldPrixLocal);
		
		textFieldTaille = new JTextField();
		textFieldTaille.setColumns(10);
		textFieldTaille.setBounds(155, 379, 168, 29);
		frame.getContentPane().add(textFieldTaille);
		
		JButton btnRecherche = new JButton("Rechercher");
		btnRecherche.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		btnRecherche.setBounds(10, 428, 117, 42);
		frame.getContentPane().add(btnRecherche);
		
		textFieldNumeroBiens = new JTextField();
		textFieldNumeroBiens.setColumns(10);
		textFieldNumeroBiens.setBounds(168, 81, 168, 29);
		frame.getContentPane().add(textFieldNumeroBiens);
		
		lblPour = new JLabel("Pour:");
		lblPour.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPour.setBounds(10, 125, 125, 23);
		frame.getContentPane().add(lblPour);
		
		textFieldPour = new JTextField();
		textFieldPour.setColumns(10);
		textFieldPour.setBounds(168, 120, 168, 29);
		frame.getContentPane().add(textFieldPour);
		
		JButton btnDemande = new JButton("Demande");
		btnDemande.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		btnDemande.setBounds(10, 159, 125, 42);
		frame.getContentPane().add(btnDemande);
		
		lblNumeroBiens = new JLabel("Numero de biens:");
		lblNumeroBiens.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumeroBiens.setBounds(10, 82, 148, 23);
		frame.getContentPane().add(lblNumeroBiens);
		
		lblTypeBiens = new JLabel("Type de Biens:");
		lblTypeBiens.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTypeBiens.setBounds(10, 224, 148, 23);
		frame.getContentPane().add(lblTypeBiens);
		
		lblLocal = new JLabel("Localisation:");
		lblLocal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLocal.setBounds(10, 263, 106, 23);
		frame.getContentPane().add(lblLocal);
		
		lblPrixVent = new JLabel("Prix de Vent:");
		lblPrixVent.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrixVent.setBounds(10, 302, 125, 23);
		frame.getContentPane().add(lblPrixVent);
		
		lblPrixLocal = new JLabel("Prix de Location:");
		lblPrixLocal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrixLocal.setBounds(10, 340, 148, 23);
		frame.getContentPane().add(lblPrixLocal);
		
		lblTaille = new JLabel("La taille:");
		lblTaille.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTaille.setBounds(10, 381, 106, 23);
		frame.getContentPane().add(lblTaille);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(377, 81, 467, 389);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnClose = new JButton("Main");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
				window.setVisible(true);
				
				frame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClose.setBackground(new Color(255, 255, 255));
		btnClose.setBounds(10, 10, 89, 50);
		frame.getContentPane().add(btnClose);
		
		String query = "Select * From biensImmobiliers";
		try {
			statement = connection.createStatement();
			ResultSet res = statement.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(res));
			
			JSeparator separator = new JSeparator();
			separator.setBounds(7, 211, 329, 2);
			frame.getContentPane().add(separator);
			
			JLabel lblNewLabel = new JLabel("Recherche du biens");
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
			lblNewLabel.setBounds(298, 10, 259, 42);
			frame.getContentPane().add(lblNewLabel);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
