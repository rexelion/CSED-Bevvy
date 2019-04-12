import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Goal {

	final static String dateTimeFormat = "dd MMMMMMMMM yyyy hh:mma";
	private SimpleDateFormat format;

	private Date startDateTime;
	private Date endDateTime;
	private String totalAmount;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	
	public Goal(Date startDateTime, Date endDateTime, String totalAmount, String startDate, String endDate, String startTime, String endTime) {
		format = new SimpleDateFormat(dateTimeFormat);
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.totalAmount = totalAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Goal(String startDate, String startTime, String endDate, String endTime, String totalAmount) {
		format = new SimpleDateFormat(dateTimeFormat);
		try {
			this.startDateTime = format.parse(startDate + " " + startTime);
			this.endDateTime = format.parse(endDate + " " + endTime);
		} catch (ParseException pe) {}
		this.startTime = startTime;
		this.endTime = endTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalAmount = totalAmount; 
	}
	
	public boolean dateInGoal(Date date) {
		return (startDateTime.before(date)) && (endDateTime.after(date));
	}
	
	public Date getStartDateTime() {
		return startDateTime;
	}
	
	public Date getEndDateTime() {
		return endDateTime;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public String getTotalAmount() {
		return totalAmount;
	}
}
