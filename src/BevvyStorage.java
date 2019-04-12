import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class BevvyStorage {
	private String storageFile;
	private String goalStorageFile;
	
	ArrayList<String> tempList = new ArrayList<String>();
	
	private boolean lineFound;
	
	private BufferedWriter writer;
	private BufferedReader reader;
	
	public BevvyStorage() {
		storageFile = "storage.csv";
		goalStorageFile = "goalStorage.csv";
		writer = null;
		reader = null;
	}
	
	public void removeFromStorage(String line) {
		try {
			reader = new BufferedReader(new FileReader(storageFile));
			lineFound = false;
			String entryLine = null;
			while ((entryLine = reader.readLine()) != null) {
				if(!entryLine.equals(line)) {			
					tempList.add(entryLine);
				}
				else if(entryLine.equals(line) && lineFound == true) {
					tempList.add(entryLine);
				}
				else{
					lineFound = true;
				}
			}
			writer = new BufferedWriter(new FileWriter(storageFile, false));
			writer.write("");
			writer = new BufferedWriter(new FileWriter(storageFile, true));
			for (int x = 0; x < tempList.size(); x++) {
				writer.write(tempList.get(x) + "\n");
			}
			tempList.clear();
		} catch (IOException io) {
			System.out.println("Error writing new entry");
			System.out.println(io);
		} finally {
			try {
				writer.close();
			} catch (IOException closeError) {
				System.out.println("Error closing file stream");
				System.out.println(closeError);
			}
		}
	}
	
	public void addEntry(DataEntry newEntry) {
		try {
			writer = new BufferedWriter(new FileWriter(storageFile, true));
			writer.write(newEntry.getDate() + ",");
			writer.write(newEntry.getTime() + ",");
			writer.write(newEntry.getAmount() + ",\n");
		} catch (IOException io) {
			System.out.println("Error writing new entry");
			System.out.println(io);
		} finally {
			try {
				writer.close();
			} catch (IOException closeError) {
				System.out.println("Error closing file stream");
				System.out.println(closeError);
			}
		}
	}
	
	public void addGoal(Goal newGoal) {
		try {
			writer = new BufferedWriter(new FileWriter(goalStorageFile, true));
			writer.write(newGoal.getStartDate() + ",");
			writer.write(newGoal.getStartTime() + ",");
			writer.write(newGoal.getEndDate() + ",");
			writer.write(newGoal.getEndTime() + ",");
			writer.write(newGoal.getTotalAmount() + ",\n");
		} catch (IOException io) {
			System.out.println("Error writing new goal");
			System.out.println(io);
		} finally {
			try {
				writer.close();
			} catch (IOException closeError) {
				System.out.println("Error closing file stream");
				System.out.println(closeError);
			}
		}
	}
	
	private DataEntry fromString(String line) {
		String elements[] = line.split(",");
		DataEntry newEntry = new DataEntry(elements[0], elements[1], elements[2]);
		return newEntry;
	}
	
	private Goal goalFromString(String line) {
		String elements[] = line.split(",");
		Goal newGoal = new Goal(elements[0], elements[1], elements[2], elements[3], elements[4]);
		return newGoal;
	}
	
	public List<DataEntry> readStorage() {
		List<DataEntry> entries = new ArrayList<DataEntry>();
		try {
			reader = new BufferedReader(new FileReader(storageFile));
			String entryLine = null;
			while ((entryLine = reader.readLine()) != null) {
				DataEntry newEntry = fromString(entryLine);
				entries.add(newEntry);
			}
		} catch (IOException io) {
			System.out.println("Error reading entries from the file");
			System.out.println(io);
		} finally {
			try {
				reader.close();
			} catch (IOException closeError) {
				System.out.println("Error closing file stream");
				System.out.println(closeError);
			}
		}
		
		return entries;
	}
	
	public List<Goal> readGoalStorage() {
		List<Goal> goals = new ArrayList<Goal>();
		try {
			reader = new BufferedReader(new FileReader(goalStorageFile));
			String goalLine = null;
			while ((goalLine = reader.readLine()) != null) {
				Goal newGoal = goalFromString(goalLine);
				goals.add(newGoal);
			}
		} catch (IOException io) {
			System.out.println("Error reading goals from the file");
			System.out.println(io);
		} finally {
			try {
				reader.close();
			} catch (IOException closeError) {
				System.out.println("Error closing file stream");
				System.out.println(closeError);
			}
		}
		
		
		return goals;
	}
	
}
