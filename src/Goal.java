import java.util.Date;

public class Goal {

	private Date startDate;
	private Date endDate;
	private String totalAmount;
	
	public Goal(Date startDate, Date endDate, String totalAmount) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalAmount = totalAmount;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public String getTotalAmount() {
		return totalAmount;
	}
}
