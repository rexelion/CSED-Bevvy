
public class DataEntry {
	private String date;
	private String time;
	private String amount;
	
	public DataEntry(String date, String time, String amount) {
		this.date = date;
		this.time = time;
		this.amount = amount;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
	public String getAmount() {
		return amount;
	}
}
