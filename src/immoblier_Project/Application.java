package immoblier_Project;

import java.awt.EventQueue;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AGENCE_IMMOBILIERE window = new AGENCE_IMMOBILIERE();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
