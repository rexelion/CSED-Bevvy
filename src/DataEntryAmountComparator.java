import java.util.Comparator;

public class DataEntryAmountComparator implements Comparator<DataEntry> {
    public int compare(DataEntry a, DataEntry b){
        int amount1 = 0;
        int amount2 = 0;
        try {
            amount1 = Integer.parseInt(a.getAmount());
            amount2 = Integer.parseInt(b.getAmount());
        } catch (NumberFormatException e) {
            return 0;
        }

        return amount1 - amount2;

    }

}
