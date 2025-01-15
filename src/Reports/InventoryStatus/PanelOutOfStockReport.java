package Reports.InventoryStatus;

import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Reports.PanelInventoryStatus;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelOutOfStockReport extends JPanel {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Create the panel.
     */
    public PanelOutOfStockReport() {
        setBackground(new Color(0, 255, 255));
        Connect();

        setBounds(0, 0, 600, 493);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("OUT OF STOCK REPORT");
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
            model.addColumn("Item ID");
            model.addColumn("SKU");
            model.addColumn("Item Name");
            model.addColumn("Price");
            model.addColumn("Remaining Stock");

            try {
                String query = """
                    SELECT 
                        i.iditems AS item_id,
                        i.sku,
                        i.itemname,
                        i.price,
                        COALESCE(SUM(ir.qty), 0) - COALESCE(SUM(isales.item_qty), 0) AS remaining_stock
                    FROM 
                        items i
                    LEFT JOIN 
                        item_receiving ir ON i.sku = ir.sku
                    LEFT JOIN 
                        item_sales isales ON i.sku = isales.sku
                    GROUP BY 
                        i.iditems, i.sku, i.itemname, i.price
                    HAVING 
                        remaining_stock = 0
                    ORDER BY 
                        i.itemname ASC;
                """;

                pst = con.prepareStatement(query);
                rs = pst.executeQuery();

                while (rs.next()) {
                    int itemId = rs.getInt("item_id");
                    String sku = rs.getString("sku");
                    String itemName = rs.getString("itemname");
                    String price = rs.getString("price");
                    int remainingStock = rs.getInt("remaining_stock");

                    model.addRow(new Object[]{itemId, sku, itemName, price, remainingStock});
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
