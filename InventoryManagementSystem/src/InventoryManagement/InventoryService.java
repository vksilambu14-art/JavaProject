package InventoryManagement;
import java.util.ArrayList;
public class InventoryService {
private ArrayList<Product>products;
public InventoryService() {
	products=new ArrayList<>();
}
public void setProducts(ArrayList<Product> products) {
    this.products = products;
}

public void addProduct(Product product) {
	products.add(product);
	System.out.println("Product added Successfully");
}
public Product searchProductId(int id) {
	for(Product product:products) {
		if(product.getProductId()==id) {
			return product;
		}
	}
	return null;
}
public void updatePrice(int id, double newPrice) {
	Product product=searchProductId(id);
	if(product!=null) {
		product.setPrice(newPrice);
		System.out.println("Price updated successfully.");
	}
	else {
		  System.out.println("Product not found.");
	}
}
public void addStock(int id,int quantity) {
	Product product=searchProductId(id);
	if(product!=null) {
		product.setStock(product.getStock()+quantity);
		System.out.println("Stock Add Successfully");
	}
	else {
		System.out.println("Product Not Found");
	}
}
public void sellProduct(int id,int quantity) {
	Product product=searchProductId(id);
	if(product!=null) {
		if(product.getStock()>=quantity) {
			product.setStock(product.getStock()-quantity);
			System.out.println("Product sold Successfully");
		}
		
		else {
			System.out.println("Insufficient Stock");
		}
	}
	else {
		System.out.println("Product Not Found");
	}
}
public void showAllProducts() {
	if(products.isEmpty()) {
		System.out.println("No products available.");
        return;
	}
	for(Product product:products) {
		 System.out.println("----------------------------");
         System.out.println("Product ID   : " + product.getProductId());
         System.out.println("Product Name : " + product.getProductName());
         System.out.println("Category     : " + product.getCategory());
         System.out.println("Price        : " + product.getPrice());
         System.out.println("Stock        : " + product.getStock());
	}
}
public void lowStockProducts() {
	boolean found= false;
	for(Product product: products) {
			if(product.getStock()<5) {
				System.out.println("----------------------------");
		         System.out.println("Product ID   : " + product.getProductId());
		         System.out.println("Product Name : " + product.getProductName());
		         System.out.println("Stock        : " + product.getStock());
		         found=true;
				
			}
	}
	if(!found) {
		System.out.println("No low stock products.");
	}
}
public void deleteProduct(int id) {
	Product product=searchProductId(id);
	if(product!=null) {
		products.remove(product);
		System.out.println("Product deleted successfully.");
	}
	else {
		System.out.println("Product not found.");
	}
}
public ArrayList<Product>getProducts(){
	return products;
}











}
