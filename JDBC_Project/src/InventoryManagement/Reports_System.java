
package InventoryManagement;

import java.sql.*;

public class Reports_System {

    public static void totalSalesReport() throws SQLException {

        String sql =
            "SELECT COUNT(*) AS total_sales, " +
            "COALESCE(SUM(total_amount),0) AS revenue, " +
            "COALESCE(AVG(total_amount),0) AS average_sale " +
            "FROM sales";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                System.out.println("\n--- TOTAL SALES REPORT ---");
                System.out.println("Number of Sales: " +
                    rs.getInt("total_sales"));
                System.out.println("Total Revenue: " +
                    rs.getDouble("revenue"));
                System.out.println("Average Sale: " +
                    rs.getDouble("average_sale"));
            }
        }
    }

    public static void stockReport() throws SQLException {

        String sql =
            "SELECT product_id, product_name, stock_quantity " +
            "FROM products ORDER BY stock_quantity ASC";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- STOCK REPORT ---");

            while (rs.next()) {
                System.out.println(
                    "Product ID: " + rs.getInt("product_id") +
                    " | Name: " + rs.getString("product_name") +
                    " | Stock: " + rs.getInt("stock_quantity")
                );
            }
        }
    }

    public static void productWiseSalesReport() throws SQLException {

        String sql ="SELECT p.product_name, " +
            "SUM(s.quantity_sold) AS units_sold, " +
            "SUM(s.total_amount) AS revenue " +
            "FROM products p JOIN sales s " +
            "ON p.product_id=s.product_id " +
            "GROUP BY p.product_id, p.product_name " +
            "ORDER BY revenue DESC";
        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n--- PRODUCT-WISE SALES REPORT ---");
            while (rs.next()) {
                System.out.println("Product: " + rs.getString("product_name") +
                    " | Units Sold: " + rs.getInt("units_sold") +
                    " | Revenue: " + rs.getDouble("revenue")
                );
            }
        }
    }

    public static void highRevenueProducts() throws SQLException {
        String sql ="SELECT p.product_name, " +
            "SUM(s.total_amount) AS revenue " +
            "FROM products p JOIN sales s " +
            "ON p.product_id=s.product_id " +
            "GROUP BY p.product_id, p.product_name " +
            "HAVING SUM(s.total_amount) > 10000 " +
            "ORDER BY revenue DESC";
        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n--- PRODUCTS WITH REVENUE  ---");
            while (rs.next()) {
                System.out.println("Product: " + rs.getString("product_name") +
                    " | Revenue: " + rs.getDouble("revenue"));
            }
        }
    }
}
