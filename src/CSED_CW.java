import java.io.*;

public class CSED_CW {

    public void storeDataInCSV(String d, String a) {

        try{
            FileWriter fileWriter = new FileWriter("storage.csv");
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
                System.out.println("(A)dd data or (R)ead data");
                String command = reader.readLine();
                
                //Close on an End-of-file (EOF) (Ctrl-D on the terminal)
                if(command == null)
                {   
                  //Exit code 0 for a graceful exit
                  System.exit(0);
                }
                
                //Otherwise, (attempt to) process the character
                else if (command.charAt(0) == 'A' /* && command.length == 1*/){
                    System.out.println("Please enter the date of consumption: ");
                    String date = reader.readLine();
                    System.out.println("Please enter the amount consumed: ");
                    String amount = reader.readLine();
                    csed.storeDataInCSV(date, amount);
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