import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelSales extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelSales() {
		setBounds(0, 0, 600, 493);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THIS IS SALES!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(105, 152, 350, 106);
		add(lblNewLabel);
	}

}
