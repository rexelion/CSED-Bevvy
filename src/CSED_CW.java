import java.io.*;
import java.lang.Object;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class CSED_CW {
    final static String pattern = "dd/MM/yyyy";

    public static boolean isDateValid(String dateString){
       try{
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            if (sdf.format(sdf.parse(dateString)).equals(dateString)){
                return true;
            }
        }
        catch (ParseException pe) {}

        return false;
    }

    public static Boolean isNumInputValid(String num){
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

    public void storeDataInCSV(String d, String a) {

        try{
            FileWriter fileWriter = new FileWriter("storage.csv", true);
            fileWriter.append(d);
            fileWriter.append("\n");
            fileWriter.append(a);
            fileWriter.append("\n");
            fileWriter.close();
        }catch(Exception e){
            System.out.println("Error creating file");
        }

        //search if the date already exists in the csv file,
        //if it does then add the data to that date instead of 
        //creating a new entry into the csv file under the same date
    }

    public static void main(String[] args) {
        CSED_CW csed = new CSED_CW();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            //Keep on accepting input from the command-line
            while(true) {
                System.out.println("(A)dd data or (V)iew data");
                String command = reader.readLine();
                
                //Close on an End-of-file (EOF) (Ctrl-D on the terminal)
                if(command == null)
                {   
                  //Exit code 0 for a graceful exit
                  System.exit(0);
                }
                
                //Otherwise, (attempt to) process the character
                else if (command.charAt(0) == 'A' && command.length() == 1){
                    //need to validate user inputs
                    System.out.println("Please enter the date of consumption (DD/MM/YYYY): ");
                    String date = reader.readLine();
                    Boolean isValid = isDateValid(date);
                    while(!isValid){
                        System.out.println("Invalid format. Please enter the date of consumption (DD/MM/YYYY): ");
                        date = reader.readLine();
                        isValid = isDateValid(date);
                    }
                    System.out.println("Please enter the amount consumed: ");
                    String amount = reader.readLine();
                    isValid = isNumInputValid(amount);
                    while(!isValid){
                        System.out.println("Invalid, please enter the amount consumed: ");
                        amount = reader.readLine();
                        isValid = isNumInputValid(amount);
                    }
                    csed.storeDataInCSV(date, amount);

                }
                else if (command.charAt(0) == 'V' && command.length() == 1){
                    System.out.println("Please enter the date you want to view (DD/MM/YY): ");
                    Boolean dateFound = false;
                    //need to validate viewDate
                    String viewDate = reader.readLine();
                    Boolean isValid = isDateValid(viewDate);
                    while(!isValid){
                        System.out.println("Invalid format. Please enter the date you want to view (DD/MM/YYYY): ");
                        viewDate = reader.readLine();
                        isValid = isDateValid(viewDate);
                    }
                    try{
                        Scanner inputStream = new Scanner(new File("storage.csv"));
                        while (inputStream.hasNextLine() && dateFound == false){
                            String data = inputStream.nextLine();
                            if (data.equals(viewDate)){
                                System.out.println(inputStream.nextLine());
                                dateFound = true;
                            }
                        }
                        if (dateFound == false){
                            System.out.println("No data found under this date");
                        }
                        inputStream.close();
                    }catch(FileNotFoundException e){
                        //e.printStackException();
                        System.out.println("File not found error");
                    }
                }
                else{
                    System.out.println("Unexpected input");
                }
            }
        } catch(IOException e) {
          System.err.println(e.getMessage());
          System.exit(1);
        }
    }

}