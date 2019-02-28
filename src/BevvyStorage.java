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
}
