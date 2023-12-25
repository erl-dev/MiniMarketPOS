import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
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
	public LoginWindow() {
		
		Connect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(272, 11, 166, 56);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblLogin);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(97, 94, 505, 201);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(27, 56, 156, 29);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 23));
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(27, 111, 156, 29);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 23));
		panel.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(193, 56, 264, 29);
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtUsername.setColumns(10);
		panel.add(txtUsername);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(193, 111, 264, 29);
		txtPass.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(txtPass);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        String username = txtUsername.getText();
		        String password = new String(txtPass.getPassword());
		        String role = null;
		       
		        
		        try {
					pst = con.prepareStatement("select role from users where username =? and password =?");
					pst.setString(1, username);
			        pst.setString(2, password);
			       ;
			        
			        rs= pst.executeQuery();
			        
			       
			        if(rs.next()){
				        	
				          role = rs.getString(1);
				          
				          if(role.equals("admin")) {
				        	  JOptionPane.showMessageDialog(null,"Login Successfull!");
				        	  DashboardWindow dashboardWindow = new DashboardWindow();
				        	  dashboardWindow.setVisible(true);
				        	  dispose();
				          } else if (role.equals("analyst")) {
				        	  JOptionPane.showMessageDialog(null,"Login Successfull!");
				        	  DashboardWindow dashboardWindow = new DashboardWindow();
				        	  dashboardWindow.setVisible(true);
				        	  dispose();
				          } else {
				        	  JOptionPane.showMessageDialog(null,"Login Successfull!");
						      BillingWindow window = new BillingWindow();
						      window.frame.setVisible(true);
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
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogin.setBounds(144, 323, 126, 56);
		contentPane.add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow registerWindow = new RegisterWindow();
	            registerWindow.setVisible(true);
	            dispose();
				
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRegister.setBounds(296, 323, 142, 56);
		contentPane.add(btnRegister);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(467, 323, 126, 56);
		contentPane.add(btnExit);
				
	}
	
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/minimarketposdb", "root", "");
		}
		
		catch (ClassNotFoundException ex) {
			
		}
		catch (SQLException ex) {
			
		}
	}
}
