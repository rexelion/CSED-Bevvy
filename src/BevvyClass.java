import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class BevvyClass {
	private BevvyStorage storage;
	private BevvyInput input;
	
	public BevvyClass() {
		storage = new BevvyStorage();
		input = new BevvyInput();
	}
	
	private void processCommand(String command) {
		switch (command.charAt(0)) {
		case 'A':
			DataEntry newEntry = input.readEntry();
			if (newEntry != null) {
				storage.addEntry(newEntry);
			}
			break;
		case 'R':
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
			List<DataEntry> ar = storage.readStorage();
			System.out.println("Would you like to sort by (D)ate or (A)mount");
			String option = "D";
			boolean validOption = false;
			while (!validOption) {
				try {
					option = inputReader.readLine();
				} catch (IOException io) {
				}
				switch (option.charAt(0)) {
					case 'A':
						Collections.sort(ar, new DataEntryAmountComparator());
						validOption = true;
						break;
					case 'D':
						Collections.sort(ar, new DataEntryDateComparator());
						validOption = true;
						break;
					default:
						System.out.println("Incorrect option, (D)ate or (A)mount");
				}
			}
			for (DataEntry entry: ar) {
				System.out.println(entry.getDate() + " " + entry.getTime() + ": " + entry.getAmount());
			}
			break;
		default:
			System.out.println("Unknown command");
		}
	}
	
	public void run() {
		while (true) {
			String command = input.readCommand();
			processCommand(command);
		}
	}
}
