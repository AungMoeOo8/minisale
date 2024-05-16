package data_classes;

import java.sql.Date;

public class Invoice {
	public int id;
	public History[] histories;
	public float totalAmt;
	public Date createdAt;

	public Invoice(int id, History[] histories, Date createdAt) {
		this.id = id;
		this.histories = histories;
		this.createdAt = createdAt;
	}
}
