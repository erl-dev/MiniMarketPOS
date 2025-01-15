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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import MainWindows.PanelReports;
import Reports.InventoryStatus.*;

public class PanelInventoryStatus extends JPanel {
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	/**
	 * Create the panel.
	 */
	public PanelInventoryStatus() {
		setBackground(new Color(0, 255, 255));
		Connect();
		
		setBounds(0, 0, 600, 493);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INVENTORY STATUS");
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
		btnExit.setBounds(180, 408, 193, 32);
		add(btnExit);
		
		JButton btnLowStockReport = new JButton("LOW STOCK REPORT");
		btnLowStockReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Create an instance of PanelAddItem
                PanelLowStockReport panelLowStockReport = new PanelLowStockReport();
                
                // Clear the current panel
                removeAll();
                
                // Add the new panel to the receiving panel
                add(panelLowStockReport);
                
                // Repaint the receiving panel to reflect the changes
                revalidate();
                repaint();
			}
		});
		btnLowStockReport.setBounds(304, 153, 193, 48);
		add(btnLowStockReport);
		
		JButton btnOverallStocks = new JButton("OVERALL STOCKS");
		btnOverallStocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Create an instance of PanelAddItem
                PanelOverallStocksReport panelOverallStocksReport = new PanelOverallStocksReport();
                
                // Clear the current panel
                removeAll();
                
                // Add the new panel to the receiving panel
                add(panelOverallStocksReport);
                
                // Repaint the receiving panel to reflect the changes
                revalidate();
                repaint();
			}
		});
		btnOverallStocks.setBounds(62, 153, 193, 48);
		add(btnOverallStocks);
		
		JButton btnOutOfStock = new JButton("OUT OF STOCK REPORT");
		btnOutOfStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
                PanelOutOfStockReport panelOutOfStockReport = new PanelOutOfStockReport();
                
                removeAll();
                
           
                add(panelOutOfStockReport);
              
                revalidate();
                repaint();
			}
		});
		btnOutOfStock.setBounds(62, 245, 193, 48);
		add(btnOutOfStock);
		
		JButton btnAgeingReport = new JButton("AGEING REPORT");
		btnAgeingReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
                PanelAgeingReport panelAgeingReport = new PanelAgeingReport();
                
                removeAll();
                
           
                add(panelAgeingReport);
              
                revalidate();
                repaint();
			}
		});
		btnAgeingReport.setBounds(304, 245, 193, 48);
		add(btnAgeingReport);
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
