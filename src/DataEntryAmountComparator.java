import java.util.Comparator;

public class DataEntryAmountComparator implements Comparator<DataEntry> {
    public int compare(DataEntry a, DataEntry b){
        float amount1 = 0;
        float amount2 = 0;
        try {
            amount1 = Float.parseFloat(a.getAmount());
            amount2 = Float.parseFloat(b.getAmount());
        } catch (NumberFormatException e) {
            return 0;
        }

        float diff = amount1 - amount2;
        if (diff == 0)
        	return 0;
        else if (diff < 0)
        	return -1;
        else
        	return 1;

    }

}
