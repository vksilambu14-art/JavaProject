
package InventoryManagement;

import java.util.*;
import java.sql.*;

public class InventoryDB_Connection {
   
    static final String url = "jdbc:mysql://localhost:3306/inventory_management";
    static final String user = "root";
    static final String pass = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public static void createDatabaseAndTables() throws SQLException {
        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement()) {

            st.executeUpdate(
                "CREATE DATABASE IF NOT EXISTS inventory_management"
            );
        }

        try (Connection con = getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS suppliers (" +
                "supplier_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "supplier_name VARCHAR(100) NOT NULL, " +
                "phone VARCHAR(15), city VARCHAR(50))"
            );

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS products (" +
                "product_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "product_name VARCHAR(100) NOT NULL, " +
                "supplier_id INT, " +
                "price DECIMAL(10,2) NOT NULL, " +
                "stock_quantity INT NOT NULL DEFAULT 0, " +
                "FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id))"
            );

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS sales (" +
                "sale_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "product_id INT NOT NULL, " +
                "quantity_sold INT NOT NULL, " +
                "sale_price DECIMAL(10,2) NOT NULL, " +
                "total_amount DECIMAL(10,2) NOT NULL, " +
                "sale_date DATE NOT NULL, " +
                "FOREIGN KEY (product_id) REFERENCES products(product_id))"
            );
        }

        System.out.println("Database and tables are ready!");
    }

    public static void main(String[] args) throws SQLException {
        createDatabaseAndTables();
    }
}
