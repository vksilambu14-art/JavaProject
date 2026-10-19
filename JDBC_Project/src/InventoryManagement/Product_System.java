
package InventoryManagement;

import java.sql.*;
import java.util.*;

public class Product_System {
    static Scanner sc = new Scanner(System.in);
    public static void addProduct(String name, int supplierId,double price, int stock)throws SQLException {
        String sql = "INSERT INTO products "
                   + "(product_name, supplier_id, price, stock_quantity) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, supplierId);
            ps.setDouble(3, price);
            ps.setInt(4, stock);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product added successfully!");
            }
        }
    }
    public static void viewProducts() throws SQLException {
        String sql = "SELECT * FROM products";
        try (Connection con = InventoryDB_Connection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("Product ID : "+ rs.getInt("product_id"));
                System.out.println("Product Name : " + rs.getString("product_name"));
                System.out.println("Supplier ID : " + rs.getInt("supplier_id"));
                System.out.println("Price : " + rs.getDouble("price"));
                System.out.println("Stock : "+ rs.getInt("stock_quantity"));
                System.out.println("-------------------------");
            }

            if (!found) {
                System.out.println("No products found!");
            }
        }
    }

    public static void searchProduct(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Product ID : "+ rs.getInt("product_id"));
                    System.out.println("Product Name : " + rs.getString("product_name"));
                    System.out.println("Supplier ID : "+ rs.getInt("supplier_id"));
                    System.out.println("Price : "+ rs.getDouble("price"));
                    System.out.println("Stock : "+ rs.getInt("stock_quantity"));
                } else {
                    System.out.println("Product not found!");
                }
            }
        }
    }


    public static void updateProduct(int id, String name,double price)throws SQLException {
        String sql = "UPDATE products "
                   + "SET product_name = ?, price = ? "
                   + "WHERE product_id = ?";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product ID not found!");
            }
        }
    }
    public static void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product ID not found!");
            }
        }
    }
    public static void updateStock(int id, int quantity)throws SQLException {

        if (quantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative!");
        }
        String sql = "UPDATE products "
                   + "SET stock_quantity = ? "
                   + "WHERE product_id = ?";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Stock updated successfully!");
            } else {
                System.out.println("Product ID not found!");
            }
        }
    }

    public static void batchAddProducts(int count)throws SQLException {
        if (count <= 0) {
            throw new IllegalArgumentException( "Product count must be greater than zero!");
        }
        String sql = "INSERT INTO products "
                   + "(product_name, supplier_id, price, stock_quantity) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            int i = 1;
            while (i <= count) {
                System.out.println("\nEnter Product " + i + " Details");
                System.out.print("Product Name: ");
                String name = sc.nextLine().trim();
                System.out.print("Supplier ID: ");
                int supplierId = Integer.parseInt(sc.nextLine());
                System.out.print("Product Price: ");
                double price = Double.parseDouble(sc.nextLine());
                System.out.print("Stock Quantity: ");
                int stock = Integer.parseInt(sc.nextLine());
                if (name.isEmpty() || price < 0 || stock < 0) {
                    throw new IllegalArgumentException("Invalid product details!");
                }

                ps.setString(1, name);
                ps.setInt(2, supplierId);
                ps.setDouble(3, price);
                ps.setInt(4, stock);
                ps.addBatch();
                i++;
            }

            int[] result = ps.executeBatch();
            System.out.println(result.length+ " products added successfully!");
        }
    }
}
