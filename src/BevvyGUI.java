import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;


public class BevvyGUI {
	
	private BevvyStorage storage;
	
	public JFrame frame = new JFrame();
	public JFrame calFrame = new JFrame();
	JButton addData = new JButton("Add data");
	JButton readData = new JButton("Read data");
	JButton goalsButton = new JButton("Goals");
	JButton addGoal = new JButton("Add goal");
	JButton readGoals = new JButton("Read goals");
	JButton trendsButton = new JButton("Trends");
	JButton storeData;
	JButton chooseDate;
	JButton acceptDate;
	JButton cancelCal;
	JButton backButton;
	JButton backButton2;
	JButton backButton3;
	JButton sortDate;
	JButton sortAmount;
	JFormattedTextField inputDate;
	JComboBox<String> inputUnits;
	JComboBox<String> inputVol;
	JComboBox<String> inputPercent;
	DatePicker datePicker;
	TimePicker timePicker;
	JTable table;
	JScrollPane scrollPane;
	boolean drawAddAdded = false;
	boolean drawReadAdded = false;
	boolean drawCalAdded = false;
	boolean drawCal2Added = false;
	boolean drawGoalsAdded = false;
	boolean dateSelected = false;
	String unitsAmount;
	String volAmount;
	String percentAmount;
	String date;
	String time;
	String amount;
	boolean dateSorted = false;
	boolean amountSorted = false;
	
	List<DataEntry> ar;
	
	Object[] dates;
	Object[] times;
	Object[] amounts;
	
	ArrayList<String> tempDates = new ArrayList<String>();
	ArrayList<String> tempTimes = new ArrayList<String>();
	ArrayList<String> tempAmounts = new ArrayList<String>();
	
	String[] units = { "Select amount of units", "0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11"};
	String[] volumes = { "Select volume (ml)", "0", "50", "100", "150", "200", "250", "300", "350", "400", "450", "500", "550", "600", "650", "700", "750", "800", "850", "900", "950", "1000", "1050", "1100"};
	String[] percents = { "Select percentage", "0", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100"};
	
	public BevvyGUI() {
		storage = new BevvyStorage();
	}
	
	public void drawBase() {
		frame.setResizable(false);
		frame.setSize(610, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		addData.setPreferredSize(new Dimension(300, 100));
		readData.setPreferredSize(new Dimension(300, 100));
		goalsButton.setPreferredSize(new Dimension(300, 100));
		trendsButton.setPreferredSize(new Dimension(300, 100));
		frame.add(addData);
		frame.add(readData);
		frame.add(goalsButton);
		frame.add(trendsButton);
		drawMain();
	}
	
	public void drawMain() {
		dateSelected = false;
		addData.setVisible(true);
		readData.setVisible(true);
		goalsButton.setVisible(true);
		trendsButton.setVisible(true);
		
		addData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addData.setVisible(false);
				readData.setVisible(false);
				goalsButton.setVisible(false);
				trendsButton.setVisible(false);
				drawAdd();
			}
		});
		
		readData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addData.setVisible(false);
				readData.setVisible(false);
				goalsButton.setVisible(false);
				trendsButton.setVisible(false);
				drawRead();
			}
		});
		
		goalsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addData.setVisible(false);
				readData.setVisible(false);
				goalsButton.setVisible(false);
				trendsButton.setVisible(false);
				drawGoals();
			}
		});
		
		trendsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//addData.setVisible(false);
				//readData.setVisible(false);
				//goalsButton.setVisible(false);
				//trendsButton.setVisible(false);
				//drawTrends();
			}
		});
		
		frame.setVisible(true);
	}

	public void addGoal() {
		if(!drawCal2Added) {
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
	
	public void drawGoals() {
		//adding goal: start date, end date, units
		//reading goal
		if (!drawGoalsAdded) {
			backButton3 = new JButton("Back");
			frame.add(addGoal);
			frame.add(readGoals);
			frame.add(backButton3);
			
			addGoal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addGoal.setVisible(false);
					readGoals.setVisible(false);
					backButton3.setVisible(false);

					addGoal();
				}
			});
			
			backButton3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addGoal.setVisible(false);
					readGoals.setVisible(false);
					backButton3.setVisible(false);

					drawMain();
				}
			});
			drawGoalsAdded = true;
		}
		addGoal.setVisible(true);
		readGoals.setVisible(true);
		backButton3.setVisible(true);
		
	}
	
	@SuppressWarnings("serial")
	public void drawRead() {
		ar = storage.readStorage();
		if (!drawReadAdded) {
			DefaultTableModel model = new DefaultTableModel();
			table = new JTable(model) {
				public boolean isCellEditable(int row, int column){  
			        return false;  
			      } 
				
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			           Component component = super.prepareRenderer(renderer, row, column);
			           int rendererWidth = component.getPreferredSize().width;
			           TableColumn tableColumn = getColumnModel().getColumn(column);
			           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			           return component;
			        }
			};
			//table.setPreferredScrollableViewportSize(table.getPreferredSize());
			
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			sortDate = new JButton("Sort by date");
			sortAmount = new JButton("Sort by amount");
			backButton2 = new JButton("Back");
			frame.add(sortAmount);
			frame.add(sortDate);
			frame.add(table);
			scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			frame.add(scrollPane);
			//table.setSize(100, 100);
			frame.add(backButton2);
			
			backButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sortAmount.setVisible(false);
					sortDate.setVisible(false);
					table.setVisible(false);
					backButton2.setVisible(false);
					scrollPane.setVisible(false);
					model.setColumnCount(0);
					tempDates.clear();
					tempTimes.clear();
					tempAmounts.clear();
					dates = null;
					times = null;
					amounts = null;
					//ar = null;
					dateSorted = false;
					amountSorted = false;
					drawMain();
				}
			});
			
			sortDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!dateSorted) {
						if (amountSorted) {
							model.setColumnCount(0);
							tempDates.clear();
							tempTimes.clear();
							tempAmounts.clear();
							dates = null;
							times = null;
							amounts = null;
							//ar = null;
						}
						
						Collections.sort(ar, new DataEntryDateComparator());
						for (DataEntry entry: ar) {
							tempDates.add(entry.getDate());
							tempTimes.add(entry.getTime());
							tempAmounts.add(entry.getAmount());
							
						}
						dates = tempDates.toArray();
						times = tempTimes.toArray();
						amounts = tempAmounts.toArray();
						model.addColumn("Date", dates);
						model.addColumn("Time", times);
						model.addColumn("Units", amounts);
						dateSorted = true;
						amountSorted = false;
					}
				}
			});
			
			sortAmount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!amountSorted) {
						
						if (dateSorted) {
							model.setColumnCount(0);
							tempDates.clear();
							tempTimes.clear();
							tempAmounts.clear();
							dates = null;
							times = null;
							amounts = null;
							//ar = null;
						}
						
						Collections.sort(ar, new DataEntryAmountComparator());
						for (DataEntry entry: ar) {
							tempDates.add(entry.getDate());
							tempTimes.add(entry.getTime());
							tempAmounts.add(entry.getAmount());
							
						}
						dates = tempDates.toArray();
						times = tempTimes.toArray();
						amounts = tempAmounts.toArray();
						//make dates, times and amounts into a list
						//instead of adding columns, add the data in individually...
						//...by looping through the lists
						//try adding the lists as a parameter to the default table model
						model.addColumn("Date", dates);
						model.addColumn("Time", times);
						model.addColumn("Units", amounts);
						dateSorted = false;
						amountSorted = true;
					}
				}
			});
			
			drawReadAdded = true;
		}
		
		sortDate.setVisible(true);
		sortAmount.setVisible(true);
		table.setVisible(true);
		scrollPane.setVisible(true);
		backButton2.setVisible(true);
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
					if (!unitsAmount.equals(units[0])) {
						int unit = Integer.parseInt(unitsAmount);
						float floatAmount = (float)(unit);
						DecimalFormat df = new DecimalFormat();
						df.setMaximumFractionDigits(1);
						amount = Float.toString(floatAmount);
					}
					else {
						int volume = Integer.parseInt(volAmount);
						int percentage = Integer.parseInt(percentAmount);
						float floatAmount = (float)((volume*percentage)/1000);
						DecimalFormat df = new DecimalFormat();
						df.setMaximumFractionDigits(1);
						amount = Float.toString(floatAmount);
					}
					//store data into csv
					DataEntry newEntry = new DataEntry(date, time, amount);
					storage.addEntry(newEntry);
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
	
	public void run() {
		drawBase();
	}

}
