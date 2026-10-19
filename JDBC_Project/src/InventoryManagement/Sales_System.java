
package InventoryManagement;

import java.sql.*;

public class Sales_System {

    public static void recordSale(int productId, int quantity)
            throws SQLException {

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero.");
        }

        String checkSql =
            "SELECT price, stock_quantity FROM products WHERE product_id=? FOR UPDATE";

        String saleSql =
            "INSERT INTO sales(product_id, quantity_sold, sale_price, total_amount, sale_date) " +
            "VALUES(?,?,?,?,CURDATE())";

        String stockSql =
            "UPDATE products SET stock_quantity=stock_quantity-? WHERE product_id=?";

        try (Connection con = InventoryDB_Connection.getConnection()) {

            con.setAutoCommit(false);

            try {
                double price;
                int stock;

                try (PreparedStatement ps = con.prepareStatement(checkSql)) {
                    ps.setInt(1, productId);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException("Product Not Found!");
                        }

                        price = rs.getDouble("price");
                        stock = rs.getInt("stock_quantity");
                    }
                }

                if (stock < quantity) {
                    throw new SQLException(
                        "Insufficient stock! Available stock: " + stock);
                }

                try (PreparedStatement ps = con.prepareStatement(saleSql)) {
                    ps.setInt(1, productId);
                    ps.setInt(2, quantity);
                    ps.setDouble(3, price);
                    ps.setDouble(4, price * quantity);

                    ps.executeUpdate();
                }

                try (PreparedStatement ps = con.prepareStatement(stockSql)) {
                    ps.setInt(1, quantity);
                    ps.setInt(2, productId);
                    ps.executeUpdate();
                }

                con.commit();

                System.out.println("Sale Recorded Successfully!");
                System.out.println("Total Amount: " + (price * quantity));

            } catch (SQLException | RuntimeException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public static void viewSales() throws SQLException {

        String sql = "SELECT * FROM sales ORDER BY sale_id";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- SALES DETAILS ---");

            while (rs.next()) {
                System.out.println("Sale ID: " + rs.getInt("sale_id"));
                System.out.println("Product ID: " + rs.getInt("product_id"));
                System.out.println("Quantity: " + rs.getInt("quantity_sold"));
                System.out.println("Sale Price: " + rs.getDouble("sale_price"));
                System.out.println("Total: " + rs.getDouble("total_amount"));
                System.out.println("Date: " + rs.getDate("sale_date"));
                System.out.println("---------------------");
            }
        }
    }

    public static void updateSale(int saleId, int newQuantity)
            throws SQLException {

        if (newQuantity <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero.");
        }

        String findSql =
            "SELECT product_id, quantity_sold, sale_price FROM sales WHERE sale_id=?";

        String stockSql =
            "UPDATE products SET stock_quantity=stock_quantity+?-? " +
            "WHERE product_id=? AND stock_quantity+? >= ?";

        String saleSql =
            "UPDATE sales SET quantity_sold=?, total_amount=? WHERE sale_id=?";

        try (Connection con = InventoryDB_Connection.getConnection()) {

            con.setAutoCommit(false);

            try {
                int productId;
                int oldQuantity;
                double price;

                try (PreparedStatement ps = con.prepareStatement(findSql)) {
                    ps.setInt(1, saleId);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException("Sale ID Not Found!");
                        }

                        productId = rs.getInt("product_id");
                        oldQuantity = rs.getInt("quantity_sold");
                        price = rs.getDouble("sale_price");
                    }
                }

                try (PreparedStatement ps = con.prepareStatement(stockSql)) {
                    ps.setInt(1, oldQuantity);
                    ps.setInt(2, newQuantity);
                    ps.setInt(3, productId);
                    ps.setInt(4, oldQuantity);
                    ps.setInt(5, newQuantity);

                    if (ps.executeUpdate() == 0) {
                        throw new SQLException(
                            "Product not found or insufficient stock!");
                    }
                }

                try (PreparedStatement ps = con.prepareStatement(saleSql)) {
                    ps.setInt(1, newQuantity);
                    ps.setDouble(2, price * newQuantity);
                    ps.setInt(3, saleId);

                    ps.executeUpdate();
                }

                con.commit();
                System.out.println("Sale Updated Successfully!");

            } catch (SQLException | RuntimeException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public static void deleteSale(int saleId) throws SQLException {

        String findSql =
            "SELECT product_id, quantity_sold FROM sales WHERE sale_id=?";

        String stockSql =
            "UPDATE products SET stock_quantity=stock_quantity+? WHERE product_id=?";

        String deleteSql =
            "DELETE FROM sales WHERE sale_id=?";

        try (Connection con = InventoryDB_Connection.getConnection()) {

            con.setAutoCommit(false);

            try {
                int productId;
                int quantity;

                try (PreparedStatement ps = con.prepareStatement(findSql)) {
                    ps.setInt(1, saleId);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            throw new SQLException("Sale ID Not Found!");
                        }

                        productId = rs.getInt("product_id");
                        quantity = rs.getInt("quantity_sold");
                    }
                }

                try (PreparedStatement ps = con.prepareStatement(stockSql)) {
                    ps.setInt(1, quantity);
                    ps.setInt(2, productId);
                    ps.executeUpdate();
                }

                try (PreparedStatement ps = con.prepareStatement(deleteSql)) {
                    ps.setInt(1, saleId);
                    ps.executeUpdate();
                }

                con.commit();
                System.out.println(
                    "Sale Deleted Successfully! Stock Restored.");

            } catch (SQLException | RuntimeException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}
