
package InventoryManagement;

import java.sql.*;
import java.util.*;

public class InventoryMain {
    static Scanner sc = new Scanner(System.in);
    public static int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a number.");
            }
        }
    }
    public static String readText(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }
    public static double readDouble(String message) {

        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a valid amount.");
            }
        }
    }

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            InventoryDB_Connection.createDatabaseAndTables();

            while (true) {

                System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
                System.out.println("1. Add Supplier");
                System.out.println("2. View Suppliers");
                System.out.println("3. Search Supplier");
                System.out.println("4. Update Supplier");
                System.out.println("5. Delete Supplier");
                System.out.println("6. Add Product");
                System.out.println("7. View Products");
                System.out.println("8. Search Product");
                System.out.println("9. Update Product");
                System.out.println("10. Delete Product");
                System.out.println("11. Update Stock");
                System.out.println("12. Batch Add Products");
                System.out.println("13. Record Sale");
                System.out.println("14. View Sales");
                System.out.println("15. Update Sale");
                System.out.println("16. Delete Sale");
                System.out.println("17. Total Sales Report");
                System.out.println("18. Stock Report");
                System.out.println("19. Product-wise Sales Report");
                System.out.println("20. High Revenue Products");
                System.out.println("0. Exit");

                int choice = readInt("Enter your choice: ");

                try {
                    switch (choice) {

                        case 1: {
                            String name = readText("Supplier Name: ");
                            String phone = readText("Phone Number: ");
                            String city = readText("City: ");

                            Supplier_System.addSupplier(name, phone, city);
                            break;
                        }

                        case 2:
                            Supplier_System.viewSuppliers();
                            break;

                        case 3: {
                            int id = readInt("Enter Supplier ID: ");
                            Supplier_System.searchByID(id);
                            break;
                        }

                        case 4: {
                            int id = readInt("Enter Supplier ID: ");
                            String name = readText("New Supplier Name: ");
                            String phone = readText("New Phone Number: ");
                            String city = readText("New City: ");

                            Supplier_System.updateSupplier(
                                    id, name, phone, city);
                            break;
                        }

                        case 5: {
                            int id = readInt("Enter Supplier ID: ");
                            Supplier_System.deleteSupplier(id);
                            break;
                        }

                        case 6: {
                            String name = readText("Product Name: ");
                            int supplierId = readInt("Supplier ID: ");
                            double price = readDouble("Product Price: ");
                            int stock = readInt("Stock Quantity: ");

                            Product_System.addProduct(name, supplierId, price, stock);
                            break;
                        }

                        case 7:
                            Product_System.viewProducts();
                            break;

                        case 8: {
                            int id = readInt("Enter Product ID: ");
                            Product_System.searchProduct(id);
                            break;
                        }

                        case 9: {
                            int id = readInt("Enter Product ID: ");
                            String name = readText("New Product Name: ");
                            double price = readDouble("New Price: ");

                            Product_System.updateProduct(id, name, price);
                            break;
                        }

                        case 10: {
                            int id = readInt("Enter Product ID: ");
                            Product_System.deleteProduct(id);
                            break;
                        }

                        case 11: {
                            int id = readInt("Enter Product ID: ");
                            int quantity = readInt("Enter New Stock Quantity: ");

                            Product_System.updateStock(id, quantity);
                            break;
                        }

                        case 12: {
                            int count = readInt("How many products do you want to add? ");

                            Product_System.batchAddProducts(count);
                            break;
                        }

                        case 13: {
                            int productId = readInt("Enter Product ID: ");
                            int quantity = readInt("Quantity Sold: ");

                            Sales_System.recordSale(productId, quantity);
                            break;
                        }

                        case 14:
                            Sales_System.viewSales();
                            break;

                        case 15: {
                            int saleId = readInt("Enter Sale ID: ");
                            int quantity = readInt("Enter New Quantity: ");

                            Sales_System.updateSale(saleId, quantity);
                            break;
                        }

                        case 16: {
                            int saleId = readInt("Enter Sale ID: ");
                            Sales_System.deleteSale(saleId);
                            break;
                        }

                        case 17:
                            Reports_System.totalSalesReport();
                            break;

                        case 18:
                            Reports_System.stockReport();
                            break;

                        case 19:
                            Reports_System.productWiseSalesReport();
                            break;

                        case 20:
                            Reports_System.highRevenueProducts();
                            break;

                        case 0:
                            System.out.println("Thank you for using Inventory Management System!");
                            sc.close();
                            return;

                        default:
                            System.out.println("Invalid choice! Please select 0 to 20.");
                    }

                } catch (SQLException e) {
                    System.out.println("Database Error: " + e.getMessage());

                } catch (IllegalArgumentException e) {
                    System.out.println("Input Error: " + e.getMessage());
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");

        } catch (SQLException e) {
            System.out.println("Database Connection Error: " + e.getMessage());
        }
    }
}
