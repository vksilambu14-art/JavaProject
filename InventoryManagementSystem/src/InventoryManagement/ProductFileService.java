package InventoryManagement;
import java.io.*;
import java.util.ArrayList;
public class ProductFileService {
	private String fileName="C:\\Users\\Dell\\Desktop\\sampledata\\product.txt";
	public void saveProduct(ArrayList<Product>products) {
		try {
			FileWriter fw=new FileWriter(fileName);
			for(Product product: products) {
				 fw.write(
		                    product.getProductId() + "," +
		                    product.getProductName() + "," +
		                    product.getCategory() + "," +
		                    product.getPrice() + "," +
		                    product.getStock() +
		                    "\n"
		                );
			}
			fw.close();
			System.out.println("Products saved successfully.");
			
		}catch(IOException e) {
			System.out.println("Error While saving File");
			 e.printStackTrace();
		}
	}
	public ArrayList<Product>loadProducts(){
		ArrayList<Product>products=new ArrayList<>();
		try {
			File file= new File(fileName);
			if(!file.exists()) {
				return products;
			}
			BufferedReader br =new BufferedReader( new FileReader(fileName));

			String line;
			while((line=br.readLine())!=null) {
				String[]data=line.split(",");
				int productId=Integer.parseInt(data[0]);
				String productName=data[1];
				Category category=Category.valueOf(data[2]);
				double price=Double.parseDouble(data[3]);
				int stock=Integer.parseInt(data[4]);
				Product product = new Product(productId,productName,category,price,stock);
				products.add(product);
			}
			br.close();
		}catch(IOException e) {
			System.out.println("Error while loading file.");
		}
		return products;
	}
	
	
	
	
	

}
