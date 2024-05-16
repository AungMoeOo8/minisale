package data_classes;

public class Product {
	public int id;
	public String name;
	public float price;
	public float discount;
	
	public Product(int id, String name, float price, float discount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
	}
}
