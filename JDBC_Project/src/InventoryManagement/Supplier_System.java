
package InventoryManagement;

import java.sql.*;

public class Supplier_System {

    public static void addSupplier(String name, String phone, String city) throws SQLException {

        String sql = "INSERT INTO suppliers(supplier_name, phone, city) VALUES(?,?,?)";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, city);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Supplier Added Successfully!");
            }
        }
    }

    public static void viewSuppliers() throws SQLException {

        String sql = "SELECT * FROM suppliers";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- SUPPLIER DETAILS ---");

            while (rs.next()) {
                System.out.println("Supplier ID: " + rs.getInt("supplier_id"));
                System.out.println("Name: " + rs.getString("supplier_name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("City: " + rs.getString("city"));
                System.out.println("-----------------------");
            }
        }
    }

    public static void searchByID(int id) throws SQLException {

        String sql = "SELECT * FROM suppliers WHERE supplier_id=?";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Supplier ID: " + rs.getInt("supplier_id"));
                    System.out.println("Name: " + rs.getString("supplier_name"));
                    System.out.println("Phone: " + rs.getString("phone"));
                    System.out.println("City: " + rs.getString("city"));
                } else {
                    System.out.println("Supplier Not Found!");
                }
            }
        }
    }

    public static void updateSupplier(int id, String name, String phone, String city)throws SQLException {

        String sql = "UPDATE suppliers SET supplier_name=?, phone=?, city=? WHERE supplier_id=?";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, city);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Supplier Updated Successfully!");
            } else {
                System.out.println("Supplier ID Not Found!");
            }
        }
    }

    public static void deleteSupplier(int id) throws SQLException {

        String sql = "DELETE FROM suppliers WHERE supplier_id=?";

        try (Connection con = InventoryDB_Connection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Supplier Deleted Successfully!");
            } else {
                System.out.println("Supplier ID Not Found!");
            }
        }
    }
}
