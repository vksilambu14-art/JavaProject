package InventoryManagement;

public class Product {
 private int productId;
 private String productName;
 private Category category;
 private double price;
 private int stock;
 public Product(int productId,String productName,Category category,double price,int stock) {
	 this.productId=productId;
	 this.productName=productName;
	 this.category=category;
	 this.price=price;
	 this.stock=stock;
 }
 public int getProductId() {
	 return productId;
 }
 public void setProductId(int productId) {
	 this.productId=productId;
 }
 public String getProductName() {
	 return productName;
 }
 public void setProductName(String productName) {
	 this.productName=productName;
 }
 public Category getCategory() {
	 return category;
 }
 public double getPrice() {
	 return price;
	 
 }
 public void setPrice(double price) {
	 this.price=price;
 }
 public int getStock() {
	 return stock;
 }
 public void setStock(int stock) {
	 this.stock=stock;
 }
}
