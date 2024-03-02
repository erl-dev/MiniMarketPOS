import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PanelAddInventory extends JPanel {
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtSKU;
	private JTextField txtItemName;
	private JTextField txtItemPrice;
	private JTextField txtQty;

	/**
	 * Create the panel.
	 */
	public PanelAddInventory() {
		setBackground(new Color(0, 255, 255));
		Connect();
		
		setBounds(0, 0, 600, 493);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD INVENTORY");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(103, 0, 350, 106);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 98, 502, 297);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblSKU = new JLabel("SKU");
		lblSKU.setBounds(38, 73, 125, 26);
		panel.add(lblSKU);
		
		txtSKU = new JTextField();
		txtSKU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String sku = txtSKU.getText();
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost/minimarketposdb", "root", "erl07pogi");
						pst = con.prepareStatement("select * from items where sku = ?");
		                pst.setString(1, sku);
		                
		                rs = pst.executeQuery();
		                
		                if(rs.next() == false){    
		                    JOptionPane.showMessageDialog(null, "SKU Code Not Found");  
		                }
		                else{
		                     String itemName = rs.getString("itemname");
		                     String price = rs.getString("price");
		                     txtItemName.setText(itemName.trim());
		                     txtItemPrice.setText(price.trim());
		                    
		                }
						
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		txtSKU.setBounds(196, 79, 217, 20);
		panel.add(txtSKU);
		txtSKU.setColumns(10);
		
		JLabel lblItemName = new JLabel("ITEM NAME");
		lblItemName.setBounds(38, 110, 125, 20);
		panel.add(lblItemName);
		
		txtItemName = new JTextField();
		txtItemName.setColumns(10);
		txtItemName.setBounds(196, 110, 217, 20);
		panel.add(txtItemName);
		
		JLabel lblItemPrice = new JLabel("ITEM PRICE");
		lblItemPrice.setBounds(38, 141, 125, 26);
		panel.add(lblItemPrice);
		
		txtItemPrice = new JTextField();
		txtItemPrice.setColumns(10);
		txtItemPrice.setBounds(196, 141, 217, 26);
		panel.add(txtItemPrice);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("MM-dd-yyyy");
		dateChooser.setBounds(196, 37, 217, 20);
		panel.add(dateChooser);
		
		JButton btnAddInventory = new JButton("ADD INVENTORY");
		btnAddInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String sku, itemName, qty, itemPrice;
				
				Date rcvDate = dateChooser.getDate();
				java.sql.Date sqlDate = null;

				if (rcvDate != null) {
				    // Convert java.util.Date to java.sql.Date
				    sqlDate = new java.sql.Date(rcvDate.getTime());
				} else {
				    // Handle case when no date is selected
					 JOptionPane.showMessageDialog(null, "Received Date Can't be Empty!");
				}

				sku = txtSKU.getText();
				itemName = txtItemName.getText();
				qty = txtQty.getText();
				itemPrice = txtItemPrice.getText();
				
				if (sku.isEmpty() || itemName.isEmpty() || itemPrice.isEmpty() || qty.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Input first the details above!");
					
				}else {
		            try {
		                // Check if the SKU already exists in the database
		                PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM items WHERE sku = ?");
		                checkStmt.setString(1, sku);
		                ResultSet resultSet = checkStmt.executeQuery();

		                if (resultSet.next()) {
		                    int itemId = resultSet.getInt("iditems"); // Assuming iditems is the primary key of the items table
		                    // If SKU exists, add the new inventory item
		                    pst = con.prepareStatement("INSERT INTO item_receiving(received_date, sku, item_description, qty, price, fk_items_id) VALUES (?, ?, ?, ?, ?, ?)");
		                    pst.setDate(1, sqlDate);
		                    pst.setString(2, sku);
		                    pst.setString(3, itemName);
		                    pst.setString(4, qty);
		                    pst.setString(5, itemPrice);
		                    pst.setInt(6, itemId); // Set the foreign key value
		                    pst.executeUpdate();
		                    JOptionPane.showMessageDialog(null, "Inventory Added Successfully!");

		                    // Clear input fields after adding the item inventory
		                    dateChooser.setDate(null); // Clear the date chooser
		                    txtSKU.setText("");
		                    txtItemName.setText("");
		                    txtItemPrice.setText("");
		                    txtQty.setText("");
		                } else {
		                    JOptionPane.showMessageDialog(null, "SKU does not exist!");
		                }	            		                
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        }
				
			}
		});
		btnAddInventory.setBounds(62, 238, 193, 32);
		panel.add(btnAddInventory);
		
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
		btnExit.setBounds(265, 238, 193, 32);
		panel.add(btnExit);
		
		JLabel lblReceivedDate = new JLabel("RECEIVED DATE");
		lblReceivedDate.setBounds(38, 36, 125, 20);
		panel.add(lblReceivedDate);
		
		JLabel lblQty = new JLabel("QUANTITY");
		lblQty.setBounds(38, 178, 125, 26);
		panel.add(lblQty);
		
		txtQty = new JTextField();
		txtQty.setColumns(10);
		txtQty.setBounds(196, 178, 217, 26);
		panel.add(txtQty);
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
