package MainWindows;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.DefaultListCellRenderer;


public class NewRegister extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JPasswordField txtPassword;
	
	private static int currentUserId; // Static variable to hold the current user's ID

	 // Setter method to set the user ID after login
	 public static void setCurrentUserId(int userId) {
	     currentUserId = userId;
	 }

	    // Getter method to retrieve the current user's ID
	 public static int getCurrentUserId() {
	    return currentUserId;
	 }
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewRegister frame = new NewRegister();
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
	private JTextField txtLastName;
	private JTextField txtUsername;
	/**
	 * Create the frame.
	 */
	public NewRegister() {
		
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
		lblNewLabel.setBounds(0, 0, 505, 105);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("FIRST NAME");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(194, 242, 252));
		lblNewLabel_1.setBackground(new Color(194, 242, 252));
		lblNewLabel_1.setBounds(0, 102, 505, 28);
		panel_1.add(lblNewLabel_1);
		
		txtFirstName = new JTextField();
		txtFirstName.setFont(new Font("Calibri", Font.BOLD, 14));
		txtFirstName.setBorder(new LineBorder(new Color(194, 242, 252)));
		txtFirstName.setBackground(new Color(73, 165, 164));
		txtFirstName.setBounds(161, 133, 200, 28);
		panel_1.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(194, 242, 252));
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1_1.setBackground(new Color(194, 242, 252));
		lblNewLabel_1_1.setBounds(0, 307, 505, 28);
		panel_1.add(lblNewLabel_1_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Calibri", Font.BOLD, 14));
		txtPassword.setColumns(10);
		txtPassword.setBorder(new LineBorder(new Color(194, 242, 252)));
		txtPassword.setBackground(new Color(73, 165, 164));
		txtPassword.setBounds(161, 336, 200, 28);
		panel_1.add(txtPassword);
		
		JComboBox cmbRole = new JComboBox();
		cmbRole.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Cashier", "Analyst"}));
		cmbRole.setBounds(161, 399, 200, 28);

		// Set the background and foreground directly
		cmbRole.setBackground(new Color(73, 165, 164)); // Match text field's background
		cmbRole.setForeground(new Color(0, 0, 0)); // Match text field's text color

		// Customize the renderer
		cmbRole.setRenderer(new DefaultListCellRenderer() {
		    @Override
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        if (isSelected) {
		            c.setBackground(new Color(73, 165, 164)); // Background color for selected item
		            c.setForeground(new Color(255, 255, 255)); // Foreground color for selected item
		        } else {
		            c.setBackground(new Color(73, 165, 164)); // Background color for non-selected item
		            c.setForeground(new Color(255, 255, 255)); // Foreground color for non-selected item
		        }
		        return c;
		    }
		});
		panel_1.add(cmbRole);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Get values from input fields
		        String fName, lName, user, role, password;
		        fName = txtFirstName.getText();
		        lName = txtLastName.getText();
		        user = txtUsername.getText();
		        password = new String(txtPassword.getPassword());
		        role = cmbRole.getSelectedItem().toString();

		        // Check if any of the required fields are empty
		        if (fName.isEmpty() || lName.isEmpty() || user.isEmpty() || password.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please fill out all the fields!");
		        } else {
		            // Hash the password
		            String hashedPassword = hashPassword(password);

		            // Check if password hashing was successful
		            if (hashedPassword != null) {
		                try {
		                    // Check if the username already exists in the database
		                    pst = con.prepareStatement("SELECT * FROM users WHERE username = ?");
		                    pst.setString(1, user);
		                    ResultSet resultSet = pst.executeQuery();

		                    if (resultSet.next()) {
		                        // Username already exists
		                        JOptionPane.showMessageDialog(null, "Username already exists!");
		                    } else {
		                        // Username doesn't exist, proceed with registration
		                        pst = con.prepareStatement("INSERT INTO users(username, password, role, firstname, lastname) VALUES (?, ?, ?, ?, ?)");
		                        pst.setString(1, user);
		                        pst.setString(2, hashedPassword); // Store hashed password
		                        pst.setString(3, role);
		                        pst.setString(4, fName);
		                        pst.setString(5, lName);

		                        pst.executeUpdate();
		                        JOptionPane.showMessageDialog(null, "Registered Successfully!");

		                        // Clear input fields after registration
		                        txtFirstName.setText("");
		                        txtLastName.setText("");
		                        txtUsername.setText("");
		                        txtPassword.setText("");
		                    }
		                } catch (SQLException e1) {
		                    e1.printStackTrace();
		                }
		            } else {
		                JOptionPane.showMessageDialog(null, "Error hashing password!");
		            }
		        }
		    }
		});
		btnRegister.setForeground(new Color(8, 61, 65));
		btnRegister.setBackground(new Color(194, 242, 252));
		btnRegister.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnRegister.setFont(new Font("Calibri", Font.BOLD, 20));
		btnRegister.setBounds(47, 469, 200, 28);
		panel_1.add(btnRegister);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewLogin newLogin = new NewLogin();
				newLogin.setVisible(true);
	            dispose();
			}
		});
		btnCancel.setForeground(new Color(8, 61, 65));
		btnCancel.setFont(new Font("Calibri", Font.BOLD, 20));
		btnCancel.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCancel.setBackground(new Color(194, 242, 252));
		btnCancel.setBounds(282, 469, 200, 28);
		panel_1.add(btnCancel);
		
		JLabel lblNewLabel_1_2 = new JLabel("LAST NAME");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(new Color(194, 242, 252));
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1_2.setBackground(new Color(194, 242, 252));
		lblNewLabel_1_2.setBounds(0, 170, 505, 28);
		panel_1.add(lblNewLabel_1_2);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Calibri", Font.BOLD, 14));
		txtLastName.setColumns(10);
		txtLastName.setBorder(new LineBorder(new Color(194, 242, 252)));
		txtLastName.setBackground(new Color(73, 165, 164));
		txtLastName.setBounds(161, 201, 200, 28);
		panel_1.add(txtLastName);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("USERNAME");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1.setForeground(new Color(194, 242, 252));
		lblNewLabel_1_2_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1_2_1.setBackground(new Color(194, 242, 252));
		lblNewLabel_1_2_1.setBounds(0, 237, 505, 28);
		panel_1.add(lblNewLabel_1_2_1);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Calibri", Font.BOLD, 14));
		txtUsername.setColumns(10);
		txtUsername.setBorder(new LineBorder(new Color(194, 242, 252)));
		txtUsername.setBackground(new Color(73, 165, 164));
		txtUsername.setBounds(161, 268, 200, 28);
		panel_1.add(txtUsername);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ROLE TYPE");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(new Color(194, 242, 252));
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBackground(new Color(194, 242, 252));
		lblNewLabel_1_1_1.setBounds(0, 373, 505, 28);
		panel_1.add(lblNewLabel_1_1_1);
		
		

		
		JLabel btnClosed = new JLabel("");
		btnClosed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					NewRegister.this.dispose();
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
				NewRegister.this.setExtendedState(JFrame.ICONIFIED);
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
	
	private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
