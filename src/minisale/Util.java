package minisale;

public class Util {
	public static String formatDate(String str) {
		String[] tokens = str.split("-");
		String year = tokens[0];
		String month = tokens[1];
		String day = tokens[2];
		return day + "/" + month + "/" + year;
	}
}
