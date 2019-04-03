import java.io.*;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.text.DecimalFormat;

public class BevvyInput {
	private BufferedReader inputReader;
	final static String dateTimeFormat = "dd/MM/yyyy HH:mm";
	
	public BevvyInput() {
		inputReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public Date toDate(String dateString){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
			Date date = sdf.parse(dateString);
			if(sdf.format(date).equals(dateString)){
				return date;
			}
		}catch(ParseException pe){}
		return null;
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
	
	private Date readTimestamp() {
		String timestamp = "";
		Date date = null;
		try {
			System.out.println("Please enter the date and the time of consumption: ");
	        timestamp = inputReader.readLine();
	        //validates date input
	        date = toDate(timestamp);
	        Boolean isValid = date != null;
	        while(!isValid){
                System.out.println("Invalid format. Please enter the date and time of consumption (DD/MM/YYYY HH/MM): ");
                timestamp = inputReader.readLine();
                date = toDate(timestamp);
                isValid = date != null;
            }
		} catch (IOException io) {
			
		}
		
		return date;
	}
	
	private int readVolume() {
		String volume = "";
		System.out.println("Please enter the volume of beverage consumed (integer ml): ");
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
		System.out.println("Please enter the alcohol concentration (integer): ");
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
		System.out.println("Please enter the amount consued (units)");
		try {
			amount = inputReader.readLine();
	        //validates alcohol input
	        Boolean isValid = isNumInputValid(amount);
	        while(!isValid){
	            System.out.println("Invalid, please enter the amount consumed (units): ");
	            amount = inputReader.readLine();
	            isValid = isNumInputValid(amount);
	        }
		} catch (IOException io) {}
		return amount;
	}
	
	private String readAmountVolume() {
		int volume = readVolume();
		int percentage = readPercentage();
		float units = (float)(volume*percentage)/1000;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(1);
		return df.format(units);
	}
	
	private String readAmount() {
		String option = "";
		String amount = "";
		option = readOption("UV", "Please enter the amount consumed: \n(U)nits or (V)olume and percentage: ");
    	switch (option.charAt(0)) {
        	case 'U':
        		amount = readAmountUnits();
        		break;
        	case 'V':
        		amount = readAmountVolume();
        		break;
        	default:
        		System.out.println("Incorrect option, (V)olume or (U)nits");
    	}
		return amount;
	}
	
	public DataEntry readEntry() {
		Date date = readTimestamp();
		String amount = readAmount();
		DataEntry newEntry = new DataEntry(date, amount);
		return newEntry;
	}
	
	public String readOption(String possibleOptions, String prompt) {
		String result = "";
		System.out.println(prompt);
		boolean validOption = false;
		while (true) {
			try {
				result = inputReader.readLine();
			} catch (IOException io) {}
			validOption = possibleOptions.contains(result);
			if (validOption) {
				break;
			} else {
				System.out.println("Incorrect option");
				System.out.println(prompt);
			}
		}
		return result;
	}
}
