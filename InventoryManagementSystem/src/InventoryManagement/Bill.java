package InventoryManagement;

public class Bill {
public double calculateTotal(Product product,int quantity) {
	if(product.getStock()>=quantity) {
		double total=product.getPrice()*quantity;
		return total;
	}
	else {
		 System.out.println("Insufficient stock.");
         return 0;
	}
}
}
