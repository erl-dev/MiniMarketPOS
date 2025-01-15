package MainWindows;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Reports.PanelInventoryStatus;
import Reports.PanelSalesSummary;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelReports extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelReports() {
		setBackground(new Color(128, 255, 255));
		setBounds(0, 0, 600, 493);
		setLayout(null);	
		setVisible(true);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(31, 51, 506, 392);
		add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REPORTS");
		lblNewLabel.setBounds(99, 5, 288, 44);
		mainPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnInventoryStatus = new JButton("INVENTORY STATUS");
		btnInventoryStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInventoryStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Create an instance of PanelAddItem
                PanelInventoryStatus panelInventoryStatus = new PanelInventoryStatus();
                
                // Clear the current panel
                removeAll();
                
                // Add the new panel to the receiving panel
                add(panelInventoryStatus);
                
                // Repaint the receiving panel to reflect the changes
                revalidate();
                repaint();
			}
		});
		btnInventoryStatus.setBounds(109, 218, 288, 44);
		mainPanel.add(btnInventoryStatus);
		
		JButton btnSalesSummary = new JButton("SALES SUMMARY");
		btnSalesSummary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Create an instance of PanelAddItem
                PanelSalesSummary panelSalesSummary = new PanelSalesSummary();
                
                // Clear the current panel
                removeAll();
                
                // Add the new panel to the receiving panel
                add(panelSalesSummary);
                
                // Repaint the receiving panel to reflect the changes
                revalidate();
                repaint();
			}
		});
		btnSalesSummary.setBounds(109, 119, 288, 44);
		mainPanel.add(btnSalesSummary);
	}
}
