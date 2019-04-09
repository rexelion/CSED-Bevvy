import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataEntry {
	
	final static String dateTimeFormat = "dd MMMMMMMMM yyyy hh:mma";
	
	
	private SimpleDateFormat formatter;
	private Date dateTime;
	private String date;
	private String time;
	private String amount;
	
	public DataEntry(String date, String time, String amount) {
		formatter = new SimpleDateFormat(dateTimeFormat);
		//System.out.println(date);
		try{
			//System.out.println("in");
			dateTime = formatter.parse(date + " " + time);
			
			//System.out.println(formatter.format(dateTime));
		}catch(ParseException pe){
			System.out.println("parse exception");
		}
		//System.out.println("out");
		//dateTime = (date + " " + time);
		this.date = date;
		this.time = time;
		this.amount = amount;
		//System.out.println(date);
		//System.out.println(time);
	}
	
	/*public DataEntry(Date date, String amount) {
		format = new SimpleDateFormat(dateTimeFormat);
		this.dateTime = date;
		this.amount = amount;
	}*/
	
	public String getDate() {
		return /*format.format(dateTime).split(" ")[0]*/ date;
	}
	
	public String getTime() {
		return /*format.format(dateTime).split(" ")[1]*/ time;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public Date getDateTime() {
		return dateTime;
	}
}
