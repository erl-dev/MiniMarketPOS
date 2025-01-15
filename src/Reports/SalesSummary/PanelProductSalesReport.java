package Reports.SalesSummary;

import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Reports.*;

public class PanelProductSalesReport extends JPanel {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Create the panel.
     */
    public PanelProductSalesReport() {
        setBackground(new Color(0, 255, 255));
        Connect();

        setBounds(0, 0, 600, 493);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("PRODUCT SALES REPORT");
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
                PanelSalesSummary panelSalesSummary = new PanelSalesSummary();
                removeAll();
                add(panelSalesSummary);
                revalidate();
                repaint();
            }
        });
        btnExit.setBounds(321, 451, 200, 30);
        add(btnExit);

        btnGenerateReport.addActionListener(e -> {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("SKU");
            model.addColumn("Item Name");
            model.addColumn("Price");
            model.addColumn("Quantity Sold");
            model.addColumn("Total Price");

            try {
                // Query to fetch product sales data
                String query = """
                    SELECT 
                        items.sku,
                        items.itemname,
                        items.price,
                        SUM(item_sales.item_qty) AS total_quantity,
                        SUM(item_sales.item_qty * item_sales.item_price) AS total_price
                    FROM 
                        item_sales
                    INNER JOIN 
                        items
                    ON 
                        item_sales.sku = items.sku
                    WHERE 
                        DATE(item_sales.fk_sales_date) = CURDATE()
                    GROUP BY 
                        items.sku, items.itemname, items.price
                    ORDER BY 
                        items.itemname ASC;
                """;

                pst = con.prepareStatement(query);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String itemName = rs.getString("itemname");
                    String price = rs.getString("price");
                    int quantitySold = rs.getInt("total_quantity");
                    double totalPrice = rs.getDouble("total_price");

                    model.addRow(new Object[]{sku, itemName, price, quantitySold, totalPrice});
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
