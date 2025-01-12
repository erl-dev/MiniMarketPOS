package Reports;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelSalesSummary extends JPanel {
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	/**
	 * Create the panel.
	 */
	public PanelSalesSummary() {
		setBackground(new Color(0, 255, 255));
		Connect();
		
		setBounds(0, 0, 600, 493);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SALES SUMMARY");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(103, 0, 350, 106);
		add(lblNewLabel);
	}
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/minimarketposdb", "root", "erl07pogi");
			
		}
		
		catch (ClassNotFoundException ex) {
			
		}
		catch (SQLException ex) {
			
		}
	}
}
