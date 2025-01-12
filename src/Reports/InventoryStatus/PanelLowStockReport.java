package Reports.InventoryStatus;

import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Reports.PanelInventoryStatus;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelLowStockReport extends JPanel {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Create the panel.
     */
    public PanelLowStockReport() {
        setBackground(new Color(0, 255, 255));
        Connect();

        setBounds(0, 0, 600, 493);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("LOW STOCK REPORT");
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
            model.addColumn("Description");
            model.addColumn("Total Received");
            model.addColumn("Total Sold");
            model.addColumn("Remaining Stock");

            try {
                String query = """
                    SELECT 
                        ir.sku,
                        ir.item_description,
                        SUM(ir.qty) AS total_received,
                        COALESCE(SUM(isales.item_qty), 0) AS total_sold,
                        SUM(ir.qty) - COALESCE(SUM(isales.item_qty), 0) AS remaining_stock
                    FROM 
                        item_receiving ir
                    LEFT JOIN 
                        item_sales isales ON ir.sku = isales.sku
                    GROUP BY 
                        ir.sku, ir.item_description
                    HAVING 
                        remaining_stock < 10
                    ORDER BY 
                        remaining_stock ASC;
                """;

                pst = con.prepareStatement(query);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String description = rs.getString("item_description");
                    int totalReceived = rs.getInt("total_received");
                    int totalSold = rs.getInt("total_sold");
                    int remainingStock = rs.getInt("remaining_stock");

                    model.addRow(new Object[]{sku, description, totalReceived, totalSold, remainingStock});
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
