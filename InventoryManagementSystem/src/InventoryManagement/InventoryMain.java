package InventoryManagement;
import java.util.Scanner;
import java.util.ArrayList;
public class InventoryMain {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		InventoryService service=new InventoryService();
		Bill bill=new Bill();
		ProductFileService fileService =new ProductFileService();
		service.setProducts(fileService.loadProducts());
		int choice;
		do {
			 	System.out.println();
			 	System.out.println("================================");
			 	System.out.println(" INVENTORY MANAGEMENT SYSTEM  ");
			 	System.out.println("================================");
			 	System.out.println("1. Add Product");
	            System.out.println("2. Search Product");
	            System.out.println("3. Update Product Price");
	            System.out.println("4. Add Stock");
	            System.out.println("5. Sell Product");
	            System.out.println("6. Show All Products");
	            System.out.println("7. Low Stock Products");
	            System.out.println("8. Calculate Bill");
	            System.out.println("9. Delete Product");
	            System.out.println("10. Save Products");
	            System.out.println("11. Exit");
	            System.out.print("Enter choice : ");
	            choice = sc.nextInt();
	            sc.nextLine();
	            switch(choice) {
	            case 1:
	            System.out.print("Enter Product ID : ");
	            int productId =sc.nextInt();
	            sc.nextLine();
	            System.out.print("Enter Product Name : ");
	            String productName =sc.nextLine();
	            System.out.println("1. LAPTOP");
                System.out.println("2. MOBILE");
                System.out.println("3. ACCESSORY");
                System.out.println("4. HOME_APPLIANCE");
                System.out.print("Enter Category : ");
                int categoryChoice = sc.nextInt();
                Category category;
                if (categoryChoice == 1) {

                    category = Category.LAPTOP;

                } else if (categoryChoice == 2) {

                    category = Category.MOBILE;

                } else if (categoryChoice == 3) {

                    category = Category.ACCESSORY;

                } else {

                    category = Category.HOME_APPLIANCE;
                }
                System.out.print("Enter Price : ");
                double price = sc.nextDouble();
                System.out.print("Enter Stock : ");
                int stock = sc.nextInt();
                Product product=new Product( productId,productName,category,price,stock);
                service.addProduct(product);
                break;
	            case 2:
	            	System.out.print("Enter Product ID : ");
                    int searchId = sc.nextInt();
                    Product found=service.searchProductId(searchId);
                    if(found!=null) {
                    	 System.out.println("----------------------------");
                         System.out.println("Product ID   : " + found.getProductId());
                         System.out.println("Product Name : " + found.getProductName());
                         System.out.println("Category     : " + found.getCategory());
                         System.out.println("Price        : " + found.getPrice());
                         System.out.println("Stock       : " + found.getStock());
                    }
                    else {

                        System.out.println("Product not found.");
                    }
                    break;
                    
	            case 3:
	            	System.out.print("Enter Product ID : ");
                    int priceId = sc.nextInt();
                    System.out.print("Enter New Price : ");
                    double newPrice = sc.nextDouble();
                    service.updatePrice(priceId, newPrice);
                    break;
	            case 4:
	            	System.out.print("Enter Product ID : ");
                    int stockId = sc.nextInt();
                    System.out.print("Enter Stock Quantity : ");
                    int quantity = sc.nextInt();
                    service.addStock(stockId, quantity);
                    break;
	            case 5:
	            	System.out.print("Enter Product ID : ");
                    int sellId = sc.nextInt();
                    System.out.print("Enter Quantity : ");
                    int sellQuantity = sc.nextInt();
                    service.sellProduct(sellId,sellQuantity);
                    break;
	            case 6:
	            	service.showAllProducts();
                    break;
	            case 7:
	            	 service.lowStockProducts();
	                    break;
	            case 8:
	            	System.out.print("Enter Product ID : ");
                    int billId = sc.nextInt();
                    System.out.print("Enter Quantity : ");
                    int billQuantity = sc.nextInt();
                    Product billProduct=service.searchProductId(billId);
                    if(billProduct !=null) {
                    	double total=bill.calculateTotal(billProduct,billQuantity);
                    	System.out.println();
                        System.out.println("========== BILL ==========");
                    	System.out.println("Product : " + billProduct.getProductName());
                        System.out.println("Price : " +billProduct.getPrice());
                        System.out.println("Quantity : " +billQuantity);
                        System.out.println("Total Bill : " +total);
                    }
                    else {

                        System.out.println("Product not found.");
                    }
                    break;
	            case 9:
                    System.out.print("Enter Product ID : ");
                    int deleteId = sc.nextInt();
                    service.deleteProduct(deleteId);
                    break;
	            case 10: 
	            	fileService.saveProduct(service.getProducts());
	            	break;
	            case 11:
	            	fileService.saveProduct(service.getProducts());
                    System.out.println("Thank you!");
                    break;
	            default:

                    System.out.println(
                            "Invalid choice."
                    );
	            }
	            
	            }
	            while (choice != 11);

	            sc.close();
	}

}
