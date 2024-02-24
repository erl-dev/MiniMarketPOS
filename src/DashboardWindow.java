import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class DashboardWindow extends JFrame {

	private JPanel contentPane;
	
	private Image imgLogo = new ImageIcon(this.getClass().getResource("/store.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image imgHomeIcon = new ImageIcon(this.getClass().getResource("/house.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image imgSalesIcon = new ImageIcon(this.getClass().getResource("/market-share.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image imgReceivingIcon = new ImageIcon(this.getClass().getResource("/purchaser.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image imgReportsIcon = new ImageIcon(this.getClass().getResource("/folder.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image imgDataIcon = new ImageIcon(this.getClass().getResource("/data.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	
	private PanelHome panelHomePage;
	private PanelSales panelSalesPage;
	private PanelReceiving panelReceivingPage;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashboardWindow frame = new DashboardWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DashboardWindow() {
		setBackground(new Color(47, 79, 79));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 534);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelHomePage = new PanelHome();
		panelSalesPage = new PanelSales();
		panelReceivingPage = new PanelReceiving();
		
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(0, 128, 128));
		panelMenu.setBounds(0, 0, 384, 534);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setBounds(72, 11, 229, 114);
		lblIcon.setIcon(new ImageIcon(imgLogo));
		panelMenu.add(lblIcon);
		
		JPanel panelHome = new JPanel();
		panelHome.addMouseListener(new PanelButtonMouseAdapter(panelHome) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelHomePage);
			}
		});
		panelHome.setBackground(new Color(0, 139, 139));
		panelHome.setBounds(0, 136, 384, 62);
		panelMenu.add(panelHome);
		panelHome.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOME");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(129, 11, 218, 40);
		panelHome.add(lblNewLabel);
		
		JLabel lblHomeIcon = new JLabel("");
		lblHomeIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomeIcon.setBounds(57, 0, 62, 62);
		lblHomeIcon.setIcon(new ImageIcon(imgHomeIcon));
		panelHome.add(lblHomeIcon);
		
		JPanel panelSales = new JPanel();
		panelSales.addMouseListener(new PanelButtonMouseAdapter(panelSales) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelSalesPage);
			}
		});
		panelSales.setBackground(new Color(0, 139, 139));
		panelSales.setBounds(0, 198, 384, 62);	
		panelMenu.add(panelSales);
		panelSales.setLayout(null);
		
		JLabel lblSales = new JLabel("SALES");
		lblSales.setForeground(Color.WHITE);
		lblSales.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSales.setBounds(129, 11, 218, 40);
		panelSales.add(lblSales);
		
		JLabel lblSalesIcon = new JLabel("");
		lblSalesIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesIcon.setBounds(59, 0, 62, 62);
		lblSalesIcon.setIcon(new ImageIcon(imgSalesIcon));
		panelSales.add(lblSalesIcon);
		
		JPanel panelReceiving = new JPanel();
		panelReceiving.addMouseListener(new PanelButtonMouseAdapter(panelReceiving) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelReceivingPage);
			}
		});
		panelReceiving.setBackground(new Color(0, 139, 139));
		panelReceiving.setBounds(0, 261, 384, 62);
		panelMenu.add(panelReceiving);
		panelReceiving.setLayout(null);
		
		JLabel lblReceiving = new JLabel("RECEIVING");
		lblReceiving.setForeground(Color.WHITE);
		lblReceiving.setFont(new Font("Dialog", Font.BOLD, 20));
		lblReceiving.setBounds(129, 11, 218, 40);
		panelReceiving.add(lblReceiving);
		
		JLabel lblReceivingIcon = new JLabel("");
		lblReceivingIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceivingIcon.setBounds(61, 0, 62, 62);
		lblReceivingIcon.setIcon(new ImageIcon(imgReceivingIcon));
		panelReceiving.add(lblReceivingIcon);
		
		JPanel panelReports = new JPanel();
		panelReports.addMouseListener(new PanelButtonMouseAdapter(panelReports));
		panelReports.setBackground(new Color(0, 139, 139));
		panelReports.setBounds(0, 324, 384, 62);
		panelMenu.add(panelReports);
		panelReports.setLayout(null);
		
		JLabel lblReports = new JLabel("REPORTS");
		lblReports.setForeground(Color.WHITE);
		lblReports.setFont(new Font("Dialog", Font.BOLD, 20));
		lblReports.setBounds(128, 11, 218, 40);
		panelReports.add(lblReports);
		
		JLabel lblReportsIcon = new JLabel("");
		lblReportsIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportsIcon.setBounds(63, 0, 62, 62);
		lblReportsIcon.setIcon(new ImageIcon(imgReportsIcon));
		panelReports.add(lblReportsIcon);
		
		JPanel panelData = new JPanel();
		panelData.addMouseListener(new PanelButtonMouseAdapter(panelData));
		panelData.setBackground(new Color(0, 139, 139));
		panelData.setBounds(0, 383, 384, 62);
		panelMenu.add(panelData);
		panelData.setLayout(null);
		
		JLabel lblData = new JLabel("DATA");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Dialog", Font.BOLD, 20));
		lblData.setBounds(129, 11, 218, 40);
		panelData.add(lblData);
		
		JLabel lblDataIcon = new JLabel("");
		lblDataIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataIcon.setBounds(64, 0, 62, 62);
		lblDataIcon.setIcon(new ImageIcon(imgDataIcon));
		panelData.add(lblDataIcon);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
					DashboardWindow.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblClose.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				lblClose.setForeground(Color.WHITE);
			}
			
		});
		lblClose.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(new Color(255, 255, 255));
		lblClose.setBounds(974, 0, 30, 30);
		contentPane.add(lblClose);
		
		JPanel panelMain = new JPanel();
		panelMain.setBounds(394, 30, 600, 493);
		contentPane.add(panelMain);
		
		panelMain.setLayout(new CardLayout());
	    panelMain.add(panelHomePage, "Home");
	    panelMain.add(panelSalesPage, "Sales");
		panelMain.add(panelReceivingPage, "Receiving");
	    panelHomePage.setVisible(true);


		menuClicked(panelHomePage);
	}
	
	public void menuClicked(JPanel panel){
		panelHomePage.setVisible(false);
		panelSalesPage.setVisible(false);
		panelReceivingPage.setVisible(false);
		
		panel.setVisible(true);
	}
	
	private class PanelButtonMouseAdapter extends MouseAdapter{
		
		JPanel panel;
			
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(112, 128, 144));
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(0, 139, 139));
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(60, 179, 113));
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(112, 128, 144));
		}
	}
}
