import java.awt.Color;
import java.awt.Component;
 
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class BevvyCellRenderer implements TableCellRenderer{
	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);
 
        Object total = table.getModel().getValueAt(row, 5);
        Object current = table.getModel().getValueAt(row,  4);
        
        float total_f = Float.parseFloat(total.toString());
        float current_f = Float.parseFloat(current.toString());
        
        Color color;
        
        if (current_f >= total_f) {
        	color = Color.RED;
        } else {
        	color = Color.BLACK;
        }
        
        c.setForeground(color);
        
        return c;
    }
}
