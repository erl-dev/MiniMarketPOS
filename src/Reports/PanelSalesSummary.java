package Reports;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import MainWindows.PanelReports;
import Reports.SalesSummary.*;

import java.awt.Color;
import javax.swing.JButton;
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
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Create an instance of PanelAddItem
                PanelReports panelReports = new PanelReports();
                
                // Clear the current panel
                removeAll();
                
                // Add the new panel to the receiving panel
                add(panelReports);
                
                // Repaint the receiving panel to reflect the changes
                revalidate();
                repaint();
			}
		});
		btnExit.setBounds(182, 365, 193, 32);
		add(btnExit);
		
		JButton btnDailySalesReport = new JButton("DAILY SALES REPORT");
		btnDailySalesReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Create an instance of PanelAddItem
                PanelDailySalesReport panelDailySalesReport = new PanelDailySalesReport();
                
                // Clear the current panel
                removeAll();
                
                // Add the new panel to the receiving panel
                add(panelDailySalesReport);
                
                // Repaint the receiving panel to reflect the changes
                revalidate();
                repaint();
			}
		});
		btnDailySalesReport.setBounds(64, 117, 193, 48);
		add(btnDailySalesReport);
		
		JButton btnMonthlySalesReport = new JButton("MONTHLY SALES REPORT");
		btnMonthlySalesReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelMonthlySalesReport panelMonthlySalesReport = new PanelMonthlySalesReport();
                
                removeAll();
                add(panelMonthlySalesReport);
                revalidate();
                repaint();
			}
		});
		btnMonthlySalesReport.setBounds(301, 117, 193, 48);
		add(btnMonthlySalesReport);
		
		JButton btnProductSalesReport = new JButton("PRODUCT SALES REPORT");
		btnProductSalesReport.setBounds(64, 231, 193, 48);
		add(btnProductSalesReport);
		
		JButton btnSalesByCategory = new JButton("SALES BY CATEGORY");
		btnSalesByCategory.setBounds(301, 231, 193, 48);
		add(btnSalesByCategory);
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
