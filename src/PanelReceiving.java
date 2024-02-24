import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelReceiving extends JPanel {
	
	

	/**
	 * Create the panel.
	 */
	public PanelReceiving() {
		setBackground(new Color(0, 255, 255));
		
		setBounds(0, 0, 600, 493);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RECEIVING STOCKS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(103, 0, 350, 106);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 98, 502, 297);
		add(panel);
		panel.setLayout(null);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Create an instance of PanelAddItem
                PanelAddItem panelAddItem = new PanelAddItem();
                
                // Clear the current panel
                removeAll();
                
                // Add the new panel to the receiving panel
                add(panelAddItem);
                
                // Repaint the receiving panel to reflect the changes
                revalidate();
                repaint();
			}
		});
		btnAddItem.setBounds(103, 57, 273, 64);
		panel.add(btnAddItem);
		
		JButton btnAddInventory = new JButton("Add Inventory");
		btnAddInventory.setBounds(103, 148, 273, 64);
		panel.add(btnAddInventory);
	}
	
}
