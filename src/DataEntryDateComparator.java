import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DataEntryDateComparator implements Comparator<DataEntry> {
    public int compare(DataEntry a, DataEntry b) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(a.getDate());
            date2 = sdf.parse(b.getDate());
        }
        catch (ParseException e) {
            return 0;
        }
        return date1.compareTo(date2);

    }
}
