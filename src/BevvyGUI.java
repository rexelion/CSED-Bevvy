import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;


public class BevvyGUI {
	public JFrame frame = new JFrame();
	public JFrame calFrame = new JFrame();
	JButton addData = new JButton("Add data");
	JButton readData = new JButton("Read data");
	JButton storeData;
	JButton chooseDate;
	JButton acceptDate;
	JButton cancelCal;
	JButton backButton;
	JFormattedTextField inputDate;
	JComboBox<String> inputUnits;
	JComboBox<String> inputVol;
	JComboBox<String> inputPercent;
	DatePicker datePicker;
	TimePicker timePicker;
	boolean drawAddAdded = false;
	boolean drawCalAdded = false;
	boolean dateSelected = false;
	String unitsAmount;
	String volAmount;
	String percentAmount;
	String date;
	String time;
	
	String[] units = { "Select amount of units", "0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11"};
	String[] volumes = { "Select volume (ml)", "0", "50", "100", "150", "200", "250", "300", "350", "400", "450", "500", "550", "600", "650", "700", "750", "800", "850", "900", "950", "1000", "1050", "1100"};
	String[] percents = { "Select percentage", "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100"};
	public void drawBase() {
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.add(addData);
		frame.add(readData);
		drawMain();
	}
	
	public void drawMain() {
		dateSelected = false;
		addData.setVisible(true);
		readData.setVisible(true);
		addData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addData.setVisible(false);
				readData.setVisible(false);
				drawAdd();
			}
		});
		
		readData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//addData.setVisible(false);
				//readData.setVisible(false);
				//drawRead();
			}
		});
		
		frame.setVisible(true);
	}
	
	public void drawAdd() {
		//only create the fields once
		if (!drawAddAdded) {
			chooseDate = new JButton("Choose date");
			chooseDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					calendar();
				}
			});
			
			inputUnits = new JComboBox<String>(units);
			inputUnits.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == inputUnits) {
						unitsAmount = (String) inputUnits.getSelectedItem();
					}
				}
			});
			
			inputVol = new JComboBox<String>(volumes);
			inputVol.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == inputVol) {
						volAmount = (String) inputVol.getSelectedItem();
					}
				}
			});
			
			inputPercent = new JComboBox<String>(percents);
			inputPercent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == inputPercent) {
						percentAmount = (String) inputPercent.getSelectedItem();
					}
				}
			});
			
			storeData = new JButton("Store data");
			backButton = new JButton("Back");

			frame.add(chooseDate);
			frame.add(inputUnits);
			frame.add(inputVol);
			frame.add(inputPercent);
			frame.add(storeData);
			frame.add(backButton);
			drawAddAdded = true;
		}
		inputUnits.setSelectedIndex(0);
		inputVol.setSelectedIndex(0);
		inputPercent.setSelectedIndex(0);
		chooseDate.setVisible(true);
		inputUnits.setVisible(true);
		inputVol.setVisible(true);
		inputPercent.setVisible(true);
		storeData.setVisible(true);
		backButton.setVisible(true);
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				storeData.setVisible(false);
				backButton.setVisible(false);
				inputUnits.setVisible(false);
				inputVol.setVisible(false);
				inputPercent.setVisible(false);
				chooseDate.setVisible(false);
				drawMain();
			}
		});
		
		storeData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(!unitsAmount.equals(units[0]) && (!volAmount.equals(volumes[0]) || !percentAmount.equals(percents[0]))) && dateSelected && (!unitsAmount.equals(units[0]) || (!volAmount.equals(volumes[0]) && !percentAmount.equals(percents[0])) )) {
					//store data into csv
					backButton.setVisible(false);
					storeData.setVisible(false);
					inputUnits.setVisible(false);
					inputVol.setVisible(false);
					inputPercent.setVisible(false);
					chooseDate.setVisible(false);
					drawMain();
				}
				else {
					//System.out.println("Please select a unit amount and date");
				}
			}
		});
	}
    
	public void calendar() {
		if(!drawCalAdded) {
	        calFrame.setTitle("Choose date");
	        calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        calFrame.setLayout(new FlowLayout());
	        calFrame.setSize(new Dimension(640, 480));
	        calFrame.setLocationRelativeTo(null);
	
	        // Create a date picker, and add it to the frame.
	        datePicker = new DatePicker();
	        calFrame.add(datePicker);
	
	        // Create a time picker, and add it to the frame.
	        timePicker = new TimePicker();
	        calFrame.add(timePicker);
	       
	        acceptDate = new JButton("Accept date");
	        calFrame.add(acceptDate);
	        
	        cancelCal = new JButton("Cancel");
	        calFrame.add(cancelCal);
	        
	        drawCalAdded = true;
		}
		calFrame.setVisible(true);
		
		acceptDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = datePicker.getText();
				time = timePicker.getText();
				if (!date.equals("") && !time.equals("")) {
					dateSelected = true;
					calFrame.setVisible(false);
					//System.out.println(date);
					//System.out.println(time);
				}
				else {
					//System.out.println("Please give a date and time");
				}
			}
		});
		
		cancelCal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calFrame.setVisible(false);
			}
		});
	}
	
	public static void main(String[] args) {
		BevvyGUI gui = new BevvyGUI();
		gui.drawBase();
	}

}
