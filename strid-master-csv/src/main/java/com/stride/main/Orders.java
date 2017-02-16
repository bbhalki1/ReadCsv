package com.stride.main;

/**
 * @author Badrinath Bhalkiker
 * Main Class file where the values are read from orders.csv and sent to the method readCsvFile()
 */
public class Orders {

	public static void main(String[] args) {
		Solution.readCsvFile("orders.csv");
	}
}
