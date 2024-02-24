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

public class PanelAddItem extends JPanel {
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtSKU;
	private JTextField txtItemName;
	private JTextField txtItemPrice;

	/**
	 * Create the panel.
	 */
	public PanelAddItem() {
		setBackground(new Color(0, 255, 255));
		Connect();
		
		setBounds(0, 0, 600, 493);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD ITEM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(103, 0, 350, 106);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 98, 502, 297);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblSKU = new JLabel("SKU");
		lblSKU.setBounds(39, 52, 125, 32);
		panel.add(lblSKU);
		
		txtSKU = new JTextField();
		txtSKU.setBounds(197, 52, 217, 32);
		panel.add(txtSKU);
		txtSKU.setColumns(10);
		
		JLabel lblItemName = new JLabel("ITEM NAME");
		lblItemName.setBounds(39, 100, 125, 32);
		panel.add(lblItemName);
		
		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		txtItemName.setBounds(197, 100, 217, 32);
		panel.add(txtItemName);
		
		JLabel lblItemPrice = new JLabel("ITEM PRICE");
		lblItemPrice.setBounds(39, 150, 125, 32);
		panel.add(lblItemPrice);
		
		txtItemPrice = new JTextField();
		txtItemPrice.setColumns(10);
		txtItemPrice.setBounds(197, 150, 217, 32);
		panel.add(txtItemPrice);
		
		JButton btnAddItem = new JButton("ADD ITEM");
		btnAddItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sku, itemName, itemPrice;
				
				sku = txtSKU.getText();
				itemName = txtItemName.getText();
				itemPrice = txtItemPrice.getText();
				
				if (sku.isEmpty() || itemName.isEmpty() || itemPrice.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Input first the details above!");
					
				}else {
		            try {
		                // Check if the SKU already exists in the database
		                PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM items WHERE sku = ?");
		                checkStmt.setString(1, sku);
		                ResultSet resultSet = checkStmt.executeQuery();

		                if (resultSet.next()) {
		                    JOptionPane.showMessageDialog(null, "SKU already exists!");
		                } else {
		                    // If SKU doesn't exist, add the new item
		                    pst = con.prepareStatement("INSERT INTO items(sku, itemname, price) VALUES (?, ?, ?)");
		                    pst.setString(1, sku);
		                    pst.setString(2, itemName);
		                    pst.setString(3, itemPrice);
		                    pst.executeUpdate();
		                    JOptionPane.showMessageDialog(null, "Item Added Successfully!");

		                    // Clear input fields after adding the item
		                    txtSKU.setText("");
		                    txtItemName.setText("");
		                    txtItemPrice.setText("");
		                }
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        }
				
			}
		});
		btnAddItem.setBounds(63, 211, 193, 32);
		panel.add(btnAddItem);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Create an instance of PanelAddItem
                PanelReceiving panelReceiving = new PanelReceiving();
                
                // Clear the current panel
                removeAll();
                
                // Add the new panel to the receiving panel
                add(panelReceiving);
                
                // Repaint the receiving panel to reflect the changes
                revalidate();
                repaint();
			}
		});
		btnExit.setBounds(266, 211, 193, 32);
		panel.add(btnExit);
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
