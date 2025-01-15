package Reports.SalesSummary;

import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Reports.*;

public class PanelDailySalesReport extends JPanel {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Create the panel.
     */
    public PanelDailySalesReport() {
        setBackground(new Color(0, 255, 255));
        Connect();

        setBounds(0, 0, 600, 493);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("DAILY SALES REPORT");
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
            model.addColumn("Quantity Sold");
            model.addColumn("Total Price");
            model.addColumn("Sales Date");

            try {
                // Query to fetch daily sales data
                String query = """
                    SELECT 
                        sku,
                        SUM(item_qty) AS total_quantity,
                        SUM(item_qty * item_price) AS total_price,
                        DATE(fk_sales_date) AS sales_date
                    FROM 
                        item_sales
                    WHERE 
                        DATE(fk_sales_date) = CURDATE()
                    GROUP BY 
                        sku, DATE(fk_sales_date)
                    ORDER BY 
                        sales_date DESC;
                """;

                pst = con.prepareStatement(query);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String sku = rs.getString("sku");
                    int quantitySold = rs.getInt("total_quantity");
                    double totalPrice = rs.getDouble("total_price");
                    Date salesDate = rs.getDate("sales_date");

                    model.addRow(new Object[]{sku, quantitySold, totalPrice, salesDate});
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
