package Reports.InventoryStatus;

import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Reports.PanelInventoryStatus;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelAgeingReport extends JPanel {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Create the panel.
     */
    public PanelAgeingReport() {
        setBackground(new Color(0, 255, 255));
        Connect();

        setBounds(0, 0, 600, 493);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("AGEING REPORT");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(103, 0, 350, 50);
        add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(27, 46, 551, 394);
        add(scrollPane);

        JTable table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnGenerateReport = new JButton("Generate Report");
        btnGenerateReport.setBounds(89, 451, 200, 30);
        add(btnGenerateReport);

        JButton btnExit = new JButton("EXIT");
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PanelInventoryStatus panelInventoryStatus = new PanelInventoryStatus();
                removeAll();
                add(panelInventoryStatus);
                revalidate();
                repaint();
            }
        });
        btnExit.setBounds(321, 451, 200, 30);
        add(btnExit);

        btnGenerateReport.addActionListener(e -> {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("SKU");
            model.addColumn("Received Date");
            model.addColumn("Expiry Date");
            model.addColumn("Remaining Stock");
            model.addColumn("Days in Stock");
            model.addColumn("Days to Expiry");

            try {
                // Updated query focusing on item_receiving and item_sales tables
                String query = """
                    SELECT 
                        ir.sku,
                        ir.received_date,
                        ir.expiry_date,
                        COALESCE(SUM(ir.qty), 0) - COALESCE(SUM(isales.item_qty), 0) AS remaining_stock,
                        DATEDIFF(CURDATE(), ir.received_date) AS days_in_stock,
                        DATEDIFF(ir.expiry_date, CURDATE()) AS days_to_expiry
                    FROM 
                        item_receiving ir
                    LEFT JOIN 
                        item_sales isales ON ir.sku = isales.sku
                    GROUP BY 
                        ir.sku, ir.received_date, ir.expiry_date
                    HAVING 
                        remaining_stock > 0
                    ORDER BY 
                        days_in_stock DESC;
                """;

                pst = con.prepareStatement(query);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String sku = rs.getString("sku");
                    Date receivedDate = rs.getDate("received_date");
                    Date expiryDate = rs.getDate("expiry_date");
                    int remainingStock = rs.getInt("remaining_stock");
                    int daysInStock = rs.getInt("days_in_stock");
                    int daysToExpiry = rs.getInt("days_to_expiry");

                    model.addRow(new Object[]{sku, receivedDate, expiryDate, remainingStock, daysInStock, daysToExpiry});
                }

                table.setModel(model);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fetching data: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/minimarketposdb", "root", "erl07pogi");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + ex.getMessage());
        }
    }
}
