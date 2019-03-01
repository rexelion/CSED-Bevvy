import java.io.*;

public class BevvyStorage {
	private String storageFile;
	
	private BufferedWriter writer;
	private BufferedReader reader;
	
	public BevvyStorage() {
		storageFile = "storage.csv";
		writer = null;
		reader = null;
	}
	
	public void addEntry(DataEntry newEntry) {
		try {
			writer = new BufferedWriter(new FileWriter(storageFile));
			writer.write(newEntry.getDate() + ",");
			writer.write(newEntry.getAmount() + ",\n");
		} catch (IOException io) {
			System.out.println("Error writing new entry");
			System.out.println(io);
		} finally {
			try {
				writer.close();
			} catch (IOException closeError){
				System.out.println("Error closing file stream");
				System.out.println(closeError);
			}
		}
	}
}
