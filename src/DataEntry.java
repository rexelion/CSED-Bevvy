import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataEntry {
	
	final static String dateTimeFormat = "dd/MM/yyyy HH:mm";
	
	
	private SimpleDateFormat format;
	private Date dateTime;
	private String amount;
	
	public DataEntry(String date, String time, String amount) {
		format = new SimpleDateFormat(dateTimeFormat);
		try{
			dateTime = format.parse(date + " " + time);
		}catch(ParseException pe){}
		this.amount = amount;
	}
	
	public DataEntry(Date date, String amount) {
		format = new SimpleDateFormat(dateTimeFormat);
		this.dateTime = date;
		this.amount = amount;
	}
	
	public String getDate() {
		return format.format(dateTime).split(" ")[0];
	}
	
	public String getTime() {
		return format.format(dateTime).split(" ")[1];
	}
	
	public String getAmount() {
		return amount;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
}
