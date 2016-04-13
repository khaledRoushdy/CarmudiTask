package com.carmudi.Utilities;

public class Utilities {

	public static boolean checkPriceWithinRange(int from, int to, int number) {
		if (number >= from && number <= to) {
			return true;
		} else
			return false;
	}

	public static int removeCurrencyFromPrice(String price){
		String newPrice=price.replace("AED","").replace(",","").replace(" ", "");
		return Integer.parseInt(newPrice);
	
	}
	

}
