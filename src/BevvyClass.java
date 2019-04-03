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
			List<DataEntry> ar = storage.readStorage();
			String option = input.readOption("AD", "Would you like to sort by (D)ate or (A)mount");
			switch (option.charAt(0)) {
				case 'A':
					Collections.sort(ar, new DataEntryAmountComparator());
					break;
				case 'D':
					Collections.sort(ar, new DataEntryDateComparator());
					break;
				default:
					System.out.println("Incorrect option, (D)ate or (A)mount");
			}
			for (DataEntry entry: ar) {
				System.out.println(entry.getDate() + " " + entry.getTime() + ": " + entry.getAmount());
			}
			break;
		case 'G':
			Goal newGoal = input.readGoal();
			storage.addGoal(newGoal);
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
