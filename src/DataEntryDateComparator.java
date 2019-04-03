import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DataEntryDateComparator implements Comparator<DataEntry> {
    public int compare(DataEntry a, DataEntry b) {
        Date date1 = a.getDateTime();
        Date date2 = b.getDateTime();
        return date1.compareTo(date2);

    }
}
