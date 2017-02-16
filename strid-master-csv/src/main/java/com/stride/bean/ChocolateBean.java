package com.stride.bean;

/**
 * @author Badrinath Bhalkiker
 * Bean class for setting and getting the cash,price,wrappers and type
 */
public class ChocolateBean {

	private int cash;
	private int price;
	private int wrappers;
	private String type;

	public ChocolateBean(int cash, int price, int wrappers, String type) {
		this.cash = cash;
		this.price = price;
		this.wrappers = wrappers;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getWrappers() {
		return wrappers;
	}

	public void setWrappers(int wrappers) {
		this.wrappers = wrappers;
	}

	@Override
	public String toString() {
		return "Chocolate [cash=" + cash + ", price=" + price + ", wrappers=" + wrappers + ", type=" + type + "]";
	}
}