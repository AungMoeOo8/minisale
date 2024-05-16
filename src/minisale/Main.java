package minisale;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import data_classes.DatabaseCredential;
import db_queries.GetAllProducts;
import ui.SaleFrame;

public class Main {

	public static void main(String[] args) {

		DatabaseCredential credential = new DatabaseCredential("localhost", 3306, "root", "Aa145156167!", "minisale");

		try {
			DatabaseConnector.connect(credential);

			GetAllProducts qAllProducts = new GetAllProducts();
			qAllProducts.runOnSuccess((products) -> {
				Global.addProducts(products);
			});

			qAllProducts.runOnFail((reason) -> System.out.println("main error"));

			Database.execute(qAllProducts);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleFrame frame = new SaleFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}

}
