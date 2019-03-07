import java.io.*;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BevvyInput {
	private BufferedReader inputReader;
	final static String dateFormat = "dd/mm/yyyy";
	
	public BevvyInput() {
		inputReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public Boolean isDateValid(String dateString){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			if(sdf.format(sdf.parse(dateString)).equals(dateString)){
				return true;
			}
		}catch(ParseException pe){}
		return false;
	}

	public Boolean isNumInputValid(String num){
		try{
			int intTest = Integer.parseInt(num);
			if (intTest > 0){
				return true;
			}
			else{
				return false;
			}
		}catch(NumberFormatException e){
			return false;
		}
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
	
	public DataEntry readEntry() {
		DataEntry newEntry = null;
		try {
			System.out.println("Please enter the date of consumption: ");
	        String date = inputReader.readLine();
	        //validates date input
	        Boolean isValid = isDateValid(date);
	        while(!isValid){
                System.out.println("Invalid format. Please enter the date of consumption (DD/MM/YYYY): ");
                date = inputReader.readLine();
                isValid = isDateValid(date);
            }
	        System.out.println("Please enter the amount consumed: ");
	        String amount = inputReader.readLine();
	        //validates alcohol input
	        isValid = isNumInputValid(amount);
            while(!isValid){
                System.out.println("Invalid, please enter the amount consumed: ");
                amount = inputReader.readLine();
                isValid = isNumInputValid(amount);
            }
	        newEntry = new DataEntry(date, amount);
		} catch (IOException io) {
			System.out.println("Error reading data entry");
			System.out.println(io);
		}
		return newEntry;
	}
}
