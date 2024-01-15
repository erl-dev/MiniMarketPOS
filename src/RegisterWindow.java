import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow frame = new RegisterWindow();
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
	public RegisterWindow() {
		
		
		Connect();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 494);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(57, 181, 224));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRATION");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(209, 11, 300, 49);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(90, 71, 505, 291);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblFirstName.setBounds(10, 24, 156, 29);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblLastName.setBounds(10, 75, 156, 29);
		panel.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblUsername.setBounds(10, 126, 156, 29);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblPassword.setBounds(10, 181, 156, 29);
		panel.add(lblPassword);
		
		JLabel lblRoleType = new JLabel("Role Type:");
		lblRoleType.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblRoleType.setBounds(10, 232, 156, 40);
		panel.add(lblRoleType);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtFirstName.setBounds(176, 24, 264, 29);
		panel.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtLastName.setColumns(10);
		txtLastName.setBounds(176, 75, 264, 29);
		panel.add(txtLastName);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtUsername.setColumns(10);
		txtUsername.setBounds(176, 126, 264, 29);
		panel.add(txtUsername);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtPass.setBounds(176, 181, 264, 29);
		panel.add(txtPass);
		
		JComboBox cmbRole = new JComboBox();
		cmbRole.setFont(new Font("Tahoma", Font.BOLD, 20));
		cmbRole.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Cashier", "Analyst"}));
		cmbRole.setBounds(176, 228, 264, 40);
		panel.add(cmbRole);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fName, lName, user, role, password;
				
				
				fName = txtFirstName.getText();
				lName = txtLastName.getText();
				user = txtUsername.getText();	
				password = new String(txtPass.getPassword());
				role = cmbRole.getSelectedItem().toString();
				
				if (fName.isEmpty() || lName.isEmpty() || user.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Input first the details above!");
					
				} else {
					try {
						pst = con.prepareStatement("insert into users(username,password,role,firstname,lastname)values(?,?,?,?,?)");
						pst.setString(1, user);
						pst.setString(2, password);
						pst.setString(3, role);
						pst.setString(4, fName);
						pst.setString(5, lName);
						
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Registered Successfully!");
						
						txtFirstName.setText("");
						txtLastName.setText("");
						txtUsername.setText("");
						txtPass.setText("");
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
				
				
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnRegister.setBounds(121, 373, 188, 59);
		contentPane.add(btnRegister);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginWindow logInWindow = new LoginWindow();
				logInWindow.setVisible(true);
				dispose();
				
			}
		});	
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCancel.setBounds(353, 373, 188, 59);
		contentPane.add(btnCancel);
	}
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/minimarketposdb", "root", "erl07pogi");
			System.out.print("connected");
		}
		
		catch (ClassNotFoundException ex) {
			
		}
		catch (SQLException ex) {
			
		}
	}
	
	
}
