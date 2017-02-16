package com.stride.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.stride.bean.CaptureBean;
import com.stride.bean.ChocolateBean;


/**
 * @author Badrinath Bhalkiker
 * Driver class where the values are read from orders.csv and writing the output to redemptions.csv withn output folder
 */
public class Solution {
	
	// Place holders for Cash, price, wrappers and type as per orders.csv
	
	private static final int CASH = 0;
	private static final int PRICE = 1;
	private static final int WRAPPERS = 2;
	private static final int TYPE = 3;
	
	/**
	 * Method to read the CSV file which is passed as a parameter from main
	 * Gets the values from CSV and set's it to the Chocolate Bean 
	 * @param orders.csv
	 */
	@SuppressWarnings("unused")
	public static void readCsvFile(String fileName) {
		BufferedReader fileReader = null;
		try {
			List<ChocolateBean> chocolates = new ArrayList<ChocolateBean>();
			String line = "";
			fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();
			while ((line = fileReader.readLine()) != null) {
				String[] tokens = line.split(",");
				if (tokens.length > 0) {
					if (line == null) {
						System.out.println("Nothing Fancy here");
					} else if (line.isEmpty() || line.trim().equals("") || line.trim().equals("\n")) {
						// Do nothng
					} else {
						String cash = tokens[CASH].trim(); 
						String price = tokens[PRICE].trim();
						String wrappers = tokens[WRAPPERS].trim();
						ChocolateBean chocolate = new ChocolateBean(Integer.parseInt(cash), Integer.parseInt(price),
								Integer.parseInt(wrappers), tokens[TYPE].trim()); // Set the values after reading the values from orders.csv
						chocolates.add(chocolate);
					}
				}
			}
			// Iterate each value and send to method solve for getting the result
			Iterator<ChocolateBean> itr = chocolates.iterator();
			Solution sol = new Solution();
			CaptureBean cap = new CaptureBean();
			List<CaptureBean> csvList = new ArrayList<CaptureBean>();
			while (itr.hasNext()) {
				ChocolateBean choco = (ChocolateBean) itr.next();
				cap = sol.solve(choco.getCash(), choco.getPrice(), choco.getWrappers(), choco.getType());
				csvList.add(cap);
			}
			sol.writeToCsv(csvList); // function call to write the csv
		} catch (Exception e) {
			System.out.println("Error in reading CsvFile");
			System.out.println(e.getMessage());
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing the file");
				System.out.println(e.getMessage());
			}
		}

	}

	/**
	 * This method is used to calculate the number of milk,dark, white and sugar free chocolates  
	 * @param cash
	 * @param price
	 * @param wrap
	 * @param type
	 * @return object of CaptureBean which has the type and map object
	 */
	public CaptureBean solve(int cash, int price, int wrap, String type) {
		Map<String, Integer> chocoList = new HashMap<String, Integer>();
		CaptureBean c = new CaptureBean();
		int result = 0;
		int bought = cash / price;
		result += bought;
		type = type.replaceAll("'", "");
		int wrappers = bought;
		int secondResults = 0;
		int secondWrap = 0;
		int thirdResults = 0;
		int thirdWrap = 0;
		while (wrappers >= wrap) {
			int freeChoco = wrappers / wrap;
			result += freeChoco;
			wrappers = wrappers - freeChoco * wrap + freeChoco;
			if (!type.equals("dark")) {
				secondResults += freeChoco;
				secondWrap = secondResults;
				if (secondResults >= wrap) {
					while (secondWrap >= wrap) {
						int freeSecondChoco = secondWrap / wrap;
						secondResults += freeSecondChoco;
						secondWrap = secondWrap - freeSecondChoco * wrap + freeSecondChoco;
						if (!(type.equals("sugar free"))) {
							thirdResults += freeSecondChoco;
							thirdWrap = thirdResults;
							if (thirdResults >= wrap) {
								while (thirdWrap >= wrap) {
									int freeThirdChoco = thirdWrap / wrap;
									thirdResults += freeThirdChoco;
									thirdWrap = thirdWrap - freeThirdChoco * wrap + freeThirdChoco;
								}
							}
						}
					}
				}
			}
		}
		chocoList.put("result", result);
		chocoList.put("secondResults", secondResults);
		chocoList.put("thirdResults", thirdResults);
		c.setType(type);
		c.setChocos(chocoList);
		return c;
	}

	/**
	 * This method is used to write the elements to CSV file within the output folder
	 * @param List of CaptureBean which has a type and a map reference
	 */
	public void writeToCsv(List<CaptureBean> csv) {
		Map<String, Integer> chocola;
		String fileName = "redemptions.csv";
		String dir = "output";
		FileWriter fileWriter = null;
		try {
			new File(dir).mkdir();
			File file = new File(dir + "/" + fileName);
			Iterator<CaptureBean> itr = csv.iterator();
			fileWriter = new FileWriter(file);
			while (itr.hasNext()) {
				fileWriter.append('\n');
				CaptureBean c = (CaptureBean) itr.next();
				if (c.getType().equalsIgnoreCase("dark")) {
					chocola = c.getChocos();
					fileWriter.append(("milk " + chocola.get("secondResults") + " dark " + chocola.get("result")
							+ " white " + chocola.get("thirdResults") + " sugar free 0"));
				}
				if (c.getType().equalsIgnoreCase("milk")) {
					chocola = new HashMap<String, Integer>();
					chocola = c.getChocos();
					fileWriter.append("milk " + chocola.get("result") + " dark " + chocola.get("thirdResults")
							+ " white 0" + " sugar free " + chocola.get("secondResults"));
				}
				if (c.getType().equalsIgnoreCase("white")) {
					chocola = new HashMap<String, Integer>();
					chocola = c.getChocos();
					fileWriter.append("milk 0" + " dark " + chocola.get("thirdResults") + " white "
							+ chocola.get("result") + " sugar free " + chocola.get("secondResults"));
				}
				if (c.getType().equalsIgnoreCase("sugar free")) {
					chocola = new HashMap<String, Integer>();
					chocola = c.getChocos();
					fileWriter.append("milk " + chocola.get("thirdResults") + " dark " + chocola.get("secondResults")
							+ " white " + chocola.get("thirdResults") + " sugar free " + chocola.get("result"));
				}
			}
			//System.out.println("File created successfully");
		} catch (Exception e) {
			System.out.println("Error in writing CsvFile");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}
}
