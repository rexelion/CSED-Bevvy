import java.io.*;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class BevvyInput {
	private BufferedReader inputReader;
	final static String dateTimeFormat = "dd/MM/yyyy HH:mm";
	
	public BevvyInput() {
		inputReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public Boolean isDateValid(String dateString){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
			if(sdf.format(sdf.parse(dateString)).equals(dateString)){
				return true;
			}
		}catch(ParseException pe){}
		return false;
	}

	public Boolean isNumInputValid(String num){
		try{
			float floatTest = Float.parseFloat(num);
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(1);
			if ((floatTest > 0) && (df.format(floatTest).equals(num))){
				return true;
			}
			else{
				return false;
			}
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	private Boolean isInteger(String num) {
		try {
			int number = Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {}
		return false;
	}
	
	public String readCommand() {
		String command = "";
		System.out.println("(A)dd data or (R)ead data");
		try {
			command = inputReader.readLine();
		} catch (IOException io) {
			System.out.println("Error reading command from the user");
			System.out.println(io);
		}
		return command;
	}
	
	private String readTimestamp() {
		String timestamp = "";
		try {
			System.out.println("Please enter the date and the time of consumption: ");
	        timestamp = inputReader.readLine();
	        //validates date input
	        Boolean isValid = isDateValid(timestamp);
	        while(!isValid){
                System.out.println("Invalid format. Please enter the date and time of consumption (DD/MM/YYYY HH/MM): ");
                timestamp = inputReader.readLine();
                isValid = isDateValid(timestamp);
            }
		} catch (IOException io) {
			
		}
		
		return timestamp;
	}
	
	private int readVolume() {
		String volume = "";
		System.out.println("Please enter the volume of beverage consumed: ");
		int volumeInt = 0;
		try {
			volume = inputReader.readLine();
			boolean isValid = isInteger(volume);
			if (isValid) {
				volumeInt = Integer.parseInt(volume);
				isValid = volumeInt > 0;
			}
			while (!isValid) {
				System.out.println("Incorrect volume, please enter positive integer: ");
				volume = inputReader.readLine();
				isValid = isInteger(volume);
				if (isValid) {
					volumeInt = Integer.parseInt(volume);
					isValid = volumeInt > 0;
				}
			}
		} catch (IOException io) {}
		return volumeInt;
	}
	
	private int readPercentage() {
		String percentage = "";
		System.out.println("Please enter the alcohol concentration: ");
		int percentageInt = 0;
		try {
			percentage = inputReader.readLine();
			boolean isValid = isInteger(percentage);
			if (isValid) {
				percentageInt = Integer.parseInt(percentage);
				isValid = percentageInt > 0;
			}
			while (!isValid) {
				System.out.println("Incorrect volume, please enter positive integer: ");
				percentage = inputReader.readLine();
				isValid = isInteger(percentage);
				if (isValid) {
					percentageInt = Integer.parseInt(percentage);
					isValid = (percentageInt > 0) && (percentageInt <= 100);
				}
			}
		} catch (IOException io) {}
		return percentageInt;
	}
	
	private String readAmountUnits() {
		String amount = "";
		try {
			amount = inputReader.readLine();
	        //validates alcohol input
	        Boolean isValid = isNumInputValid(amount);
	        while(!isValid){
	            System.out.println("Invalid, please enter the amount consumed: ");
	            amount = inputReader.readLine();
	            isValid = isNumInputValid(amount);
	        }
		} catch (IOException io) {}
		return amount;
	}
	
	private String readAmountVolume() {
		int volume = readVolume();
		int percentage = readPercentage();
		float units = volume*percentage/10;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(1);
		return df.format(units);
	}
	
	private String readAmount() {
		String amount = "";
		try {
	        System.out.println("Please enter the amount consumed: ");
	        amount = inputReader.readLine();
	        //validates alcohol input
	        Boolean isValid = isNumInputValid(amount);
            while(!isValid){
                System.out.println("Invalid, please enter the amount consumed: ");
                amount = inputReader.readLine();
                isValid = isNumInputValid(amount);
            }
		} catch (IOException io) {
			System.out.println("Error reading data entry");
			System.out.println(io);
		}
		return amount;
	}
	
	public DataEntry readEntry() {
		String timestamp = readTimestamp();
		String amount = readAmount();
		String timeDateArray[] = timestamp.split(" ");
		DataEntry newEntry = new DataEntry(timeDateArray[0], timeDateArray[1], amount);
		return newEntry;
	}
}
