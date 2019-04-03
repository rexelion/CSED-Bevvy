import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Goal {

	final static String dateTimeFormat = "dd/MM/yyyy HH:mm";
	private SimpleDateFormat format;

	private Date startDate;
	private Date endDate;
	private String totalAmount;
	
	public Goal(Date startDate, Date endDate, String totalAmount) {
		format = new SimpleDateFormat(dateTimeFormat);
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalAmount = totalAmount;
	}
	
	public Goal(String startDate, String startTime, String endDate, String endTime, String totalAmount) {
		format = new SimpleDateFormat(dateTimeFormat);
		try {
			this.startDate = format.parse(startDate + " " + startTime);
			this.endDate = format.parse(endDate + " " + endTime);
		} catch (ParseException pe) {}
		this.totalAmount = totalAmount; 
	}
	
	public Date getStartDateTime() {
		return startDate;
	}
	
	public Date getEndDateTime() {
		return endDate;
	}
	
	public String getStartDate() {
		return format.format(startDate).split(" ")[0];
	}
	
	public String getStartTime() {
		return format.format(startDate).split(" ")[1];
	}
	
	public String getEndDate() {
		return format.format(endDate).split(" ")[0];
	}
	
	public String getEndTime() {
		return format.format(endDate).split(" ")[1];
	}
	
	
	public String getTotalAmount() {
		return totalAmount;
	}
}
