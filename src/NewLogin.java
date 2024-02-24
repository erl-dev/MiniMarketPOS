import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewLogin frame = new NewLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	/**
	 * Create the frame.
	 */
	public NewLogin() {
		
		Connect();
		
		setBackground(new Color(47, 79, 79));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 534);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(73, 165, 164));
		panel.setBounds(0, 0, 1004, 534);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBackground(new Color(8, 61, 65));
		panel_1.setBounds(0, 0, 505, 534);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("D:\\ELDEN\\FROM FLASH DRIVE\\Vertere\\2nd Laptop Files\\eclipse-workspace\\MiniMarketPOS\\Images\\User Shield_96px.png"));
		lblNewLabel.setBounds(0, 91, 505, 105);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(194, 242, 252));
		lblNewLabel_1.setBackground(new Color(194, 242, 252));
		lblNewLabel_1.setBounds(0, 223, 505, 28);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon("D:\\ELDEN\\FROM FLASH DRIVE\\Vertere\\2nd Laptop Files\\eclipse-workspace\\MiniMarketPOS\\Images\\Circled User Male_24px.png"));
		lblNewLabel_2.setBounds(130, 251, 34, 28);
		panel_1.add(lblNewLabel_2);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Calibri", Font.BOLD, 14));
		txtUsername.setBorder(new LineBorder(new Color(194, 242, 252)));
		txtUsername.setBackground(new Color(73, 165, 164));
		txtUsername.setBounds(161, 251, 200, 28);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(194, 242, 252));
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1_1.setBackground(new Color(194, 242, 252));
		lblNewLabel_1_1.setBounds(0, 290, 505, 28);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("D:\\ELDEN\\FROM FLASH DRIVE\\Vertere\\2nd Laptop Files\\eclipse-workspace\\MiniMarketPOS\\Images\\Show Password_24px.png"));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(130, 319, 34, 28);
		panel_1.add(lblNewLabel_2_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		txtPassword.setColumns(10);
		txtPassword.setBorder(new LineBorder(new Color(194, 242, 252)));
		txtPassword.setBackground(new Color(73, 165, 164));
		txtPassword.setBounds(161, 319, 200, 28);
		panel_1.add(txtPassword);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
		        String password = new String(txtPassword.getPassword());
		        String role = null;
		       
		        
		        try {
					pst = con.prepareStatement("select role from users where username =? and password =?");
					pst.setString(1, username);
			        pst.setString(2, password);
			       ;
			        
			        rs= pst.executeQuery();
			        
			       
			        if(rs.next()){
				        	
				          role = rs.getString(1);
				          
				          if(role.equals("Cashier")) {
				        	  JOptionPane.showMessageDialog(null,"Login Successfull!");
				        	  BillingWindow billingWindow = new BillingWindow();
				        	  billingWindow.frame.setVisible(true);
				        	  billingWindow.frame.setLocationRelativeTo(null);
				        	  dispose();
				          } else if (role.equals("Admin")) {
				        	  JOptionPane.showMessageDialog(null,"Login Successfull!");
				        	  DashboardWindow dashboardWindow = new DashboardWindow();
				        	  dashboardWindow.setVisible(true);
				        	  dispose();
				          } 					      
				          
		            }
		            
		            else
		            {
		                JOptionPane.showMessageDialog(null,"UserName and Password do not Match");
		      
		                
		            }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setForeground(new Color(8, 61, 65));
		btnLogin.setBackground(new Color(194, 242, 252));
		btnLogin.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 20));
		btnLogin.setBounds(161, 369, 200, 28);
		panel_1.add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow registerWindow = new RegisterWindow();
	            registerWindow.setVisible(true);
	            dispose();
			}
		});
		btnRegister.setForeground(new Color(8, 61, 65));
		btnRegister.setFont(new Font("Calibri", Font.BOLD, 20));
		btnRegister.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRegister.setBackground(new Color(194, 242, 252));
		btnRegister.setBounds(161, 408, 200, 28);
		panel_1.add(btnRegister);
		
		JLabel btnClosed = new JLabel("");
		btnClosed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					NewLogin.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClosed.setIcon(new ImageIcon(getClass().getResource("/Close Window_32px_9.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnClosed.setIcon(new ImageIcon(getClass().getResource("/Close Window_32px_8.png")));
			}
		});
		btnClosed.setHorizontalAlignment(SwingConstants.CENTER);
		btnClosed.setIcon(new ImageIcon("D:\\ELDEN\\FROM FLASH DRIVE\\Vertere\\2nd Laptop Files\\eclipse-workspace\\MiniMarketPOS\\Images\\Close Window_32px_8.png"));
		btnClosed.setBounds(972, 0, 32, 32);
		panel.add(btnClosed);
		
		JLabel btnMinimized = new JLabel("");
		btnMinimized.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMinimized.setIcon(new ImageIcon(getClass().getResource("/Minimize Window_32px_9.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMinimized.setIcon(new ImageIcon(getClass().getResource("/Minimize Window_32px_8.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				NewLogin.this.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btnMinimized.setIcon(new ImageIcon("D:\\ELDEN\\FROM FLASH DRIVE\\Vertere\\2nd Laptop Files\\eclipse-workspace\\MiniMarketPOS\\Images\\Minimize Window_32px_8.png"));
		btnMinimized.setHorizontalAlignment(SwingConstants.CENTER);
		btnMinimized.setBounds(934, 0, 32, 32);
		panel.add(btnMinimized);
		
		JLabel lblNewLabel_4 = new JLabel("P.O.S.");
		lblNewLabel_4.setForeground(new Color(194, 242, 252));
		lblNewLabel_4.setIcon(new ImageIcon("D:\\ELDEN\\FROM FLASH DRIVE\\Vertere\\2nd Laptop Files\\eclipse-workspace\\MiniMarketPOS\\Images\\Shopping Cart Loaded_80px.png"));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 99));
		lblNewLabel_4.setBounds(506, 133, 498, 117);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("POINT OF SALES SYSTEM");
		lblNewLabel_5.setForeground(new Color(194, 242, 252));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 47));
		lblNewLabel_5.setBounds(506, 261, 498, 91);
		panel.add(lblNewLabel_5);
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
