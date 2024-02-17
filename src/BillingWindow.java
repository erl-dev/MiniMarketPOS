import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BillingWindow {

	JFrame frame;
	private JTextField txtSku;
	private JTextField txtItemName;
	private JTextField txtPrice;
	private JTextField txtTotal;
	private JTable tableBilling;
	boolean isItemFound = false;
    boolean isPrinted = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillingWindow window = new BillingWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BillingWindow() {
		initialize();
		Connect();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtCash;
	private JTextField txtTotalAmt;
	private JTextField txtBal;
	
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(76, 76, 109));
		frame.setBounds(100, 100, 1208, 656);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Image img = new ImageIcon(this.getClass().getResource("/smsupermarketicon.png")).getImage();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(27, 156, 133));
		panel.setBounds(20, 11, 756, 193);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mini Market Welcome!");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(289, 11, 240, 40);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SKU");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(54, 62, 44, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ITEM NAME");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(189, 62, 94, 26);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("QTY");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(360, 62, 44, 26);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("PRICE");
		lblNewLabel_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1.setBounds(487, 62, 73, 26);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("AMOUNT");
		lblNewLabel_1_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2_1_1.setBounds(647, 62, 73, 26);
		panel.add(lblNewLabel_1_2_1_1);
		
		txtSku = new JTextField();
		txtSku.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtSku.setBackground(new Color(0, 0, 0));
		txtSku.setForeground(new Color(255, 255, 0));
		txtSku.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String sku = txtSku.getText();
					
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost/minimarketposdb", "root", "erl07pogi");
						pst = con.prepareStatement("select * from items where sku = ?");
		                pst.setString(1, sku);
		                
		                rs = pst.executeQuery();
		                
		                if(rs.next() == false)
{    
		                    JOptionPane.showMessageDialog(null, "SKU Code Not Found");  
		                }
		                else{
		                     String itemName = rs.getString("itemname");
		                     String price = rs.getString("price");
		                     txtItemName.setText(itemName.trim());
		                     txtPrice.setText(price.trim());
		                    
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
		txtSku.setBounds(10, 99, 128, 40);
		panel.add(txtSku);
		txtSku.setColumns(10);
		
		txtItemName = new JTextField();
		txtItemName.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtItemName.setColumns(10);
		txtItemName.setBounds(170, 99, 128, 40);
		panel.add(txtItemName);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtPrice.setColumns(10);
		txtPrice.setBounds(467, 99, 128, 40);
		panel.add(txtPrice);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtTotal.setColumns(10);
		txtTotal.setBounds(618, 99, 128, 40);
		panel.add(txtTotal);
		
		JSpinner spinnerQty = new JSpinner();
		spinnerQty.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				int qty = Integer.parseInt(spinnerQty.getValue().toString());
				
				int price = Integer.parseInt(txtPrice.getText());
				
				int total = qty * price;
				
				txtTotal.setText(String.valueOf(total));
					
			}
			
			
		});
		spinnerQty.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spinnerQty.setBounds(347, 96, 66, 40);
		panel.add(spinnerQty);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//getting the table
				DefaultTableModel model = new DefaultTableModel();
				model = (DefaultTableModel)tableBilling.getModel();
				
				//adding item on the table
				model.addRow(new Object[] {
						txtSku.getText(),
						txtItemName.getText(),
						spinnerQty.getValue().toString(),
						txtPrice.getText(),
						txtTotal.getText(),
				});
				
				//updating the total amount on every item added in the table
				int sum = 0;
				
				for (int i = 0; i < tableBilling.getRowCount(); i++) {
					sum = sum + Integer.parseInt(tableBilling.getValueAt(i, 4).toString());
				}
				
				txtTotalAmt.setText(Integer.toString(sum));
				
				//setting the value to blank on every item added
				txtSku.setText("");
				txtItemName.setText("");
				spinnerQty.setValue(0);
				txtPrice.setText("");
				txtTotal.setText("");
				
				txtSku.requestFocus();
			}
		});
	
		
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(603, 150, 143, 26);
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 215, 756, 254);
		frame.getContentPane().add(scrollPane);
		
		tableBilling = new JTable();
		scrollPane.setViewportView(tableBilling);
		tableBilling.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SKU", "Item Name", "Qty", "Price", "Amount"
			}
		));
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = new DefaultTableModel();
				model = (DefaultTableModel)tableBilling.getModel();
				
				//delete row
				if (tableBilling.getSelectedRowCount() == 1) {
					//if single row is selected than delete
					model.removeRow(tableBilling.getSelectedRow());
				} else {
					if (tableBilling.getRowCount() == 0) {
						//if table is empty then display message
						JOptionPane.showMessageDialog(null, "Table is Empty!");  
					} else {
						//if table is not empty but row is not selected or multiple row is selected
						JOptionPane.showMessageDialog(null, "Please select a single row of item to delete");  
					}
				}
				
				// to refresh the total amount when deleting
				int sum = 0;
				
				for (int i = 0; i < tableBilling.getRowCount(); i++) {
					sum = sum + Integer.parseInt(tableBilling.getValueAt(i, 4).toString());
				}
				
				txtTotalAmt.setText(Integer.toString(sum));
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(633, 490, 143, 33);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("TOTAL");
		lblNewLabel_1_2_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_2.setBounds(20, 481, 80, 39);
		frame.getContentPane().add(lblNewLabel_1_2_1_2);
		
		JLabel lblNewLabel_1_2_1_2_1 = new JLabel("CASH");
		lblNewLabel_1_2_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_2_1.setBounds(240, 480, 80, 39);
		frame.getContentPane().add(lblNewLabel_1_2_1_2_1);
		
		JLabel lblNewLabel_1_2_1_2_2 = new JLabel("BALANCE");
		lblNewLabel_1_2_1_2_2.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2_1_2_2.setBounds(452, 484, 124, 39);
		frame.getContentPane().add(lblNewLabel_1_2_1_2_2);
		
		txtCash = new JTextField();
		txtCash.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCash.setColumns(10);
		txtCash.setBounds(207, 516, 152, 33);
		frame.getContentPane().add(txtCash);
		
		txtTotalAmt = new JTextField();
		txtTotalAmt.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtTotalAmt.setColumns(10);
		txtTotalAmt.setBounds(20, 516, 152, 33);
		frame.getContentPane().add(txtTotalAmt);
		
		txtBal = new JTextField();
		txtBal.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtBal.setColumns(10);
		txtBal.setBounds(428, 516, 152, 33);
		frame.getContentPane().add(txtBal);
		
		JButton btnPay = new JButton("PAY");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Balance();
			}
		});
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPay.setBounds(633, 541, 143, 33);
		frame.getContentPane().add(btnPay);
		
		
		
		JTextArea txtReceipt = new JTextArea();
		txtReceipt.setBounds(786, 11, 398, 538);
		frame.getContentPane().add(txtReceipt);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!isPrinted) {
					String total = txtTotalAmt.getText();
					String cash = txtCash.getText();
					String bal = txtBal.getText();
					
					DefaultTableModel model = new DefaultTableModel();
					model = (DefaultTableModel)tableBilling.getModel();
					

					txtReceipt.setText(txtReceipt.getText() + "*************************************************************************************\n");
					txtReceipt.setText(txtReceipt.getText() + "                                     MINIMARKET                                     \n");
					txtReceipt.setText(txtReceipt.getText() + "*************************************************************************************\n");
	      
					//Heading
					txtReceipt.setText(txtReceipt.getText() + "SKU" + "\t\t" + "Item Name" + "\t\t" + "Amount" + "\n"  );
					 
					 
					 for(int i = 0; i < model.getRowCount(); i++)
					 {
					     
					     String sku = (String)model.getValueAt(i, 0);
					     String itemName = (String)model.getValueAt(i, 1);
					     String amount = (String)model.getValueAt(i, 4);
					     
					     txtReceipt.setText(txtReceipt.getText() + sku  + "\t" + "\t" + itemName + "\t" + amount  + "\n"  );
	   
					 }
					 
					 txtReceipt.setText(txtReceipt.getText() + "\n\n\n\n\n\n\n\n\n\n");    
					 
					 txtReceipt.setText(txtReceipt.getText() + "\t" + "\t" + "\t" + "     Subtotal :" + total + "\n");
					 txtReceipt.setText(txtReceipt.getText() + "\t" + "\t" + "\t" + "     Pay :" + cash + "\n");
					 txtReceipt.setText(txtReceipt.getText() + "\t" + "\t" + "\t" + "     Balance :" + bal + "\n");
					 txtReceipt.setText(txtReceipt.getText() + "\n");
					 txtReceipt.setText(txtReceipt.getText() + "***********************************************************************************\n");
					 txtReceipt.setText(txtReceipt.getText() + "                             THANK YOU COME AGAIN!!!             \n");
					 
					 isPrinted = true;
				}
				
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPrint.setBounds(902, 560, 143, 33);
		frame.getContentPane().add(btnPrint);
			
	}
	
	public void Balance() {
		int total = Integer.parseInt(txtTotalAmt.getText());
		int cash = Integer.parseInt(txtCash.getText()); 
				
		int bal = cash - total;
		
		txtBal.setText(String.valueOf(bal));
	}
	
	public void Bill() {
		
		
		
	}
}
