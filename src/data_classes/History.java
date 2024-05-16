package data_classes;

public class History {
	int id;
	int invoiceId;
	public Product product;
	public int quantity;
	public float amount;

	public History(int id, int invoiceId, Product product, int quantity) {
		this.id = id;
		this.invoiceId = invoiceId;
		this.product = product;
		this.quantity = quantity;

		float reducedAmount = product.price * (product.discount / 100);
		float singleProductAmount = product.price - reducedAmount;
		this.amount = singleProductAmount * quantity;
	}

}
