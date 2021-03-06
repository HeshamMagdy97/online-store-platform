package onlineStore;

import java.sql.SQLException;
import java.util.ArrayList;

public class productController {

	ProductDBHandler productDBHandler = new ProductDBHandler();
	
	public void SuggestProduct(String productName, String productID, String brand, String category, boolean type) {
		
	}
	
	public ArrayList<Product> Search(float price, String brandID, String category, String name) throws SQLException {
		ArrayList<Product> product = null;
		ArrayList<Product> productprice = null;
		ArrayList<Product> productbrand = null;
		ArrayList<Product> productcategory = null;
		ArrayList<Product> productname = null;
		
		boolean start = false;
		
		if(brandID != "") {
			productbrand = productDBHandler.getProductByBrand(brandID);
			if(productbrand == null)return null;
			SortByID(productbrand);
			if(!start) {
				product = productbrand;
			}
			start = true;
		}
		if(price != -1) {
			productprice = productDBHandler.getProductByPrice(price);
			if(productprice == null)return null;
			SortByID(productprice);
			if(!start) {
				product = productprice;
			}else {
				AddExist(product,productprice);
			}
			start = true;
		}
		if(category != "") {
			productcategory = productDBHandler.getProductByCategory(category);
			if(productcategory == null)return null;
			SortByID(productcategory);
			if(!start) {
				product = productcategory;
			}else {
				AddExist(product,productcategory);
			}
			start = true;
		}
		if(name != "") {
			productname = productDBHandler.getProductByName(name);
			if(productname == null)return null;
			SortByID(productname);
			if(!start) {
				product = productname;
			}else {
				AddExist(product,productname);
			}
			start = true;
		}
	
		return product;
	}
	
	private void AddExist(ArrayList<Product> from ,ArrayList<Product> to) {
		for(int i = 0 ; i < from.size() ; i++) {
			boolean founded = false;
			for(int j = 0 ; j < to.size() ; j++) {
				if(from.get(i).productID == to.get(j).productID)
				{
					founded = true;
					break;
				}
			}
			if(!founded) {
				from.remove(i);
			}
		}
		
		
	}
	
	private void SortByID(ArrayList<Product> list) {
		for(int i = 0 ; i < list.size() ;i++) {
			for(int j = i ; j< list.size() ; j++) {
				if(list.get(i).productID.compareTo(list.get(j).productID) > 0 || list.get(j).tybe){
					Product temp = list.get(i);
					list.set(i,list.get(j));
					list.set(j,temp);
				}
			}
		}
	}
	
//
//  public Product ViewProduct(String prouctID);
//
//  public Void Compare(String productID_1, String productID_2);

}