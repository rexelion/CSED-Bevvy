import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import java.awt.Color;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
//import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
//import org.jfree.chart.ui.ApplicationFrame;
//import org.jfree.chart.ui.UIUtils;
//import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;


public class BevvyGUI {
	
	private BevvyStorage storage;
	
	public JFrame frame = new JFrame();
	public JFrame calFrame = new JFrame();
	public JFrame calFrame2 = new JFrame();
	public JFrame reminder = new JFrame();
	public JFrame trends = new JFrame();
	DefaultTableModel model2;
	JTextArea textArea;
	JButton addData = new JButton("Add data");
	JButton readData = new JButton("Read data");
	JButton goalsButton = new JButton("Goals");
	JButton addGoal = new JButton("Add goal");
	JButton readGoals = new JButton("Read goals");
	JButton trendsButton = new JButton("Trends");
	JButton storeData;
	JButton chooseDate;
	JButton acceptDate;
	JButton acceptGoal;
	JButton cancelCal;
	JButton cancelCal2;
	JButton backButton;
	JButton backButton2;
	JButton backButton3;
	JButton backButton4;
	JButton backButton5;
	JButton exit;
	JButton sortDate;
	JButton sortAmount;
	JButton removeRow;
	JButton removeRow2;
	JFormattedTextField inputDate;
	JComboBox<String> inputUnits;
	JComboBox<String> inputUnitsGoal;
	JComboBox<String> inputVol;
	JComboBox<String> inputPercent;
	DatePicker datePicker;
	TimePicker timePicker;
	DatePicker goalPicker1;
	DatePicker goalPicker2;
	TimePicker timePicker2;
	TimePicker timePicker3;
	JTable table;
	JTable table2;
	JScrollPane scrollPane;
	JScrollPane scrollPane2;
	boolean drawAddAdded = false;
	boolean drawReadAdded = false;
	boolean drawCalAdded = false;
	boolean drawCal2Added = false;
	boolean drawGoalsAdded = false;
	boolean readGoalsAdded = false;
	boolean dateSelected = false;
	boolean goalDateSelected = false;
	String unitsAmount;
	String volAmount;
	String percentAmount;
	String date;
	String time;
	String amount;
	
	String startDate;
	String endDate;
	String startTime;
	String endTime;
	Date start;
	Date end;
	String unitsGoal;
	
	boolean dateSorted = false;
	boolean amountSorted = false;
	boolean unitChosen = false;
	
	List<DataEntry> ar;
	//List<Goal> goals;
	
	Object[] dates;
	Object[] times;
	Object[] amounts;
	
	Date[] dateTime;
	
	Object[] startDates;
	Object[] startTimes;
	Object[] endDates;
	Object[] endTimes;
	Object[] unitsConsumed;
	Object[] unitGoals;
	
	ArrayList<String> tempDates = new ArrayList<String>();
	ArrayList<String> tempTimes = new ArrayList<String>();
	ArrayList<String> tempAmounts = new ArrayList<String>();
	
	ArrayList<String> tempDatesChart = new ArrayList<String>();
	ArrayList<String> tempAmountsChart = new ArrayList<String>();
	
	ArrayList<String> tempDateTime = new ArrayList<String>();
	ArrayList<String> tempDayAmount = new ArrayList<String>();
	
	ArrayList<String> tempStartDates = new ArrayList<String>();
	ArrayList<String> tempStartTimes = new ArrayList<String>();
	ArrayList<String> tempEndDates = new ArrayList<String>();
	ArrayList<String> tempEndTimes = new ArrayList<String>();
	ArrayList<String> tempUnitsConsumed = new ArrayList<String>();
	ArrayList<String> tempUnitGoals = new ArrayList<String>();
	
	String[] units = { "Select amount of units", "0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "12", "12.5", "13", "13.5", "14"};
	String[] volumes = { "Select volume (ml)", "0", "10", "20", "30", "40", "50", "100", "150", "200", "250", "300", "350", "400", "450", "500", "550", "600", "650", "700", "750", "800", "850", "900", "950", "1000", "1050", "1100"};
	String[] percents = { "Select percentage", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95", "100"};
	
	float diffInDays;
	
	
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
		
		trends.setResizable(false);
		trends.setSize(560, 550);
		trends.setLocationRelativeTo(null);
		trends.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		trends.setLayout(new FlowLayout());
		
		frame.add(addData);
		frame.add(readData);
		frame.add(goalsButton);
		frame.add(trendsButton);
		drawMain();
		drawReminder();
	}
	
	public void drawReminder() {
		
		reminder.setResizable(false);
		reminder.setSize(450, 350);
		reminder.setLocationRelativeTo(null);
		reminder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		reminder.setLayout(new FlowLayout());
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMMMMMM yyyy hh:mma");
		Date currentDate = new Date();
		
		ar = storage.readStorage();
		
		float total = 0;

		for (DataEntry entry: ar) {
			tempDateTime.add(entry.getDate() + " " + entry.getTime());
			tempDayAmount.add(entry.getAmount());
		}	
		
		for (int x = 0; x < tempDateTime.size(); x++) {
			Date pastDate = null;
			try {
				pastDate = formatter.parse(tempDateTime.get(x));
			} catch (ParseException e) {}
			float currentDateFl = currentDate.getTime();
			float pastDateFl = pastDate.getTime();
			diffInDays = ((currentDateFl - pastDateFl)
			        / (1000 * 60 * 60 * 24) );
			if (diffInDays < 7  && diffInDays > 0) {
				total += Float.parseFloat(tempDayAmount.get(x));
			}
		}

		textArea = new JTextArea(2, 30);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFocusable(false);
		float limit;
		
		if (total > 14) {
			limit = total-14;
			textArea.setText(":( You have gone over the weekly limit of 14 units, you drank "+total+" units this week, "+limit+" more than recommended.");
		}else if (total < 14){
			limit = 14-total;
			textArea.setText(":) Congratulations, you are under the weekly limit of 14 units, you drank "+total+" units this past week, you can safetly drink "+limit+" more to be within the recommended limit");
		}else {
			textArea.setText("You are at the recommended weekly limit for alcohol, please don't drink anymore today.");
		}

		exit = new JButton("Exit");
		
		exit.setPreferredSize(new Dimension(90, 60));
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reminder.dispose();
			}
		});
		
		
		reminder.add(textArea);
		reminder.add(exit);
		reminder.setVisible(true);
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
				ChartPanel chartPanel = (ChartPanel) createDemoPanel();
		        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		        trends.setContentPane(chartPanel);
		        trends.setVisible(true);
			}
		});
		
		frame.setVisible(true);
		
	}
	
	public JPanel createDemoPanel() {
        JFreeChart chart = drawTrends(createDataset());
        ChartPanel panel = new ChartPanel(chart, false);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        return panel;
    }
	
	public JFreeChart drawTrends(XYDataset dataset) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
	            "Alcohol consumption over time",  // title
	            "Date",             // x-axis label
	            "Units consumed",   // y-axis label
	            dataset);
		 chart.setBackgroundPaint(Color.WHITE);

	        XYPlot plot = (XYPlot) chart.getPlot();
	        plot.setBackgroundPaint(Color.LIGHT_GRAY);
	        plot.setDomainGridlinePaint(Color.WHITE);
	        plot.setRangeGridlinePaint(Color.WHITE);
	        plot.setAxisOffset(new org.jfree.ui.RectangleInsets(5.0, 5.0, 5.0, 5.0));
	        plot.setDomainCrosshairVisible(true);
	        plot.setRangeCrosshairVisible(true);

	        XYItemRenderer r = plot.getRenderer();
	        if (r instanceof XYLineAndShapeRenderer) {
	            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
	            renderer.setBaseShapesVisible(true);
	            renderer.setBaseShapesFilled(true);
	            renderer.setDrawSeriesLineAsPath(true);
	        }

	        DateAxis axis = (DateAxis) plot.getDomainAxis();
	        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

	        return chart;

	}
	
	private XYDataset createDataset() {
		//to improve: new TimeSeries for each month
		//			  or button to move between graphs of different months
        TimeSeries s1 = new TimeSeries("Trend over time");
        tempAmountsChart.clear();
        tempDatesChart.clear();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMMMMMM yyyy");
		
		ar = storage.readStorage();

		for (DataEntry entry: ar) {
			if (tempDatesChart.contains((entry.getDate()))){
				int a = tempDatesChart.indexOf((entry.getDate()/* + " "+ entry.getTime()*/));
				tempAmountsChart.set(a, Float.toString(Float.parseFloat(tempAmountsChart.get(a)) + Float.parseFloat(entry.getAmount())));
			}else {
				tempDatesChart.add(entry.getDate());
				tempAmountsChart.add(entry.getAmount());
			}
		}	
		Date chartDate = null;
        for (int x = 0; x < tempDatesChart.size(); x++) {
    		try {
    			chartDate = formatter.parse(tempDatesChart.get(x));
    			s1.add(new Day(chartDate), Float.parseFloat(tempAmountsChart.get(x)));
    		} catch (ParseException e) {}
        }
		
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;

    }

	
	
	private String countConsumption(Goal goal) {
		String total = "";
		double totalD = 0;
		List<DataEntry> entries = storage.readStorage();
		
		for (DataEntry entry: entries) {
			if (goal.dateInGoal(entry.getDateTime())) {
				try {
					double amount = Double.parseDouble(entry.getAmount());
					totalD += amount;
				} catch (NumberFormatException e) {}
			}
		}
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(1);
		total = df.format(totalD);
		return total;
	}
	
	@SuppressWarnings("serial")
	public void readGoal() {
		if (!readGoalsAdded) {
			model2 = new DefaultTableModel();
			table2 = new JTable(model2) {
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
			
			
			
			backButton4 = new JButton("Back");
			removeRow2 = new JButton("Remove");
			frame.add(table2);
			scrollPane2 = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			frame.add(scrollPane2);
			frame.add(backButton4);
			frame.add(removeRow2);
			
			removeRow2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeSelectedRows2(table2, "goalStorage.csv");
				}
			});
			
			backButton4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					table2.setVisible(false);
					backButton4.setVisible(false);
					scrollPane2.setVisible(false);
					removeRow2.setVisible(false);
					model2.setColumnCount(0);
					tempStartDates.clear();
					tempStartTimes.clear();
					tempEndDates.clear();
					tempEndTimes.clear();
					tempUnitGoals.clear();
					startDates = null;
					startTimes = null;
					endDates = null;
					endTimes = null;
					unitGoals = null;
					
					drawGoals();
				}
			});
			
			readGoalsAdded = true;
		}
		
		
		table2.setVisible(false);
		backButton4.setVisible(false);
		scrollPane2.setVisible(false);
		model2.setColumnCount(0);
		tempStartDates.clear();
		tempStartTimes.clear();
		tempEndDates.clear();
		tempEndTimes.clear();
		tempUnitGoals.clear();
		tempUnitsConsumed.clear();
		startDates = null;
		startTimes = null;
		endDates = null;
		endTimes = null;
		unitGoals = null;
		unitsConsumed = null;
		
		table2.setVisible(true);
		scrollPane2.setVisible(true);
		backButton4.setVisible(true);
		removeRow2.setVisible(true);
	}
	
	public void addGoal() {
		if(!drawCal2Added) {
	        calFrame2.setTitle("Choose start and end date/time for goal");
	        calFrame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	        calFrame2.setLayout(new FlowLayout());
	        calFrame2.setResizable(false);
	        calFrame2.setSize(new Dimension(550, 480));
	        calFrame2.setLocationRelativeTo(null);
	
	        // Create a date picker, and add it to the frame.
	        goalPicker1 = new DatePicker();
	        calFrame2.add(goalPicker1);
	
	        // Create a time picker, and add it to the frame.
	        timePicker2 = new TimePicker();
	        calFrame2.add(timePicker2);
	        
	        goalPicker2 = new DatePicker();
	        calFrame2.add(goalPicker2);
	        
	        timePicker3 = new TimePicker();
	        calFrame2.add(timePicker3);
	        
	        inputUnitsGoal = new JComboBox<String>(units);
	        calFrame2.add(inputUnitsGoal);
	        
			inputUnitsGoal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == inputUnitsGoal) {
						unitChosen = true;
						unitsGoal = (String) inputUnitsGoal.getSelectedItem();
					}
				}
			});
	       
	        acceptGoal = new JButton("Accept date");
	        calFrame2.add(acceptGoal);
	        
	        cancelCal2 = new JButton("Cancel");
	        calFrame2.add(cancelCal2);
	        
	        drawCal2Added = true;
		}
		calFrame2.setVisible(true);
		
		acceptGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startDate = goalPicker1.getText();
				endDate = goalPicker2.getText();
				startTime = timePicker2.getText();
				endTime = timePicker3.getText();
				try {
					start = new SimpleDateFormat("dd MMMMMMMMM yyyy hh:mma").parse(startDate + " " + startTime);
					end = new SimpleDateFormat("dd MMMMMMMMM yyyy hh:mma").parse(endDate + " " + endTime);
				} catch (ParseException e1) {}
				
				if (!startDate.equals("") && !endDate.equals("") && !startTime.equals("") && !endTime.equals("") && unitChosen) {
					if (!unitsGoal.equals(units[0]) && (start.compareTo(end) < 0)) {
						Goal newGoal = new Goal(start, end, unitsGoal, startDate, endDate, startTime, endTime);
						storage.addGoal(newGoal);
						calFrame2.setVisible(false);
					}
					
				}else {
					JOptionPane.showMessageDialog(calFrame2, "Data entered is invalid", "Invalid", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		cancelCal2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calFrame2.setVisible(false);
			}
		});
	}
	
	public void drawGoals() {
		if (!drawGoalsAdded) {
			backButton3 = new JButton("Back");
			frame.add(addGoal);
			frame.add(readGoals);
			frame.add(backButton3);
			
			addGoal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addGoal();
				}
			});
			
			readGoals.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addGoal.setVisible(false);
					readGoals.setVisible(false);
					backButton3.setVisible(false);
					readGoal();
					
					List<Goal> goals = storage.readGoalStorage();
					for (Goal goal: goals) {
						//System.out.println(goal.getStartDate());
						String total = countConsumption(goal);
						tempStartDates.add(goal.getStartDate());
						tempStartTimes.add(goal.getStartTime());
						tempEndDates.add(goal.getEndDate());
						tempEndTimes.add(goal.getEndTime());
						tempUnitsConsumed.add(total);
						tempUnitGoals.add(goal.getTotalAmount());
					}
					
					startDates = tempStartDates.toArray();
					startTimes = tempStartTimes.toArray();
					endDates = tempEndDates.toArray();
					endTimes = tempEndTimes.toArray();
					unitsConsumed = tempUnitsConsumed.toArray();
					unitGoals = tempUnitGoals.toArray();
					model2.addColumn("Start Date", startDates);
					model2.addColumn("Start Time", startTimes);
					model2.addColumn("End Date", endDates);
					model2.addColumn("End Time", endTimes);
					model2.addColumn("Units", unitsConsumed);
					model2.addColumn("Units Goal", unitGoals);
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

			sortDate = new JButton("Sort by date");
			sortAmount = new JButton("Sort by amount");
			removeRow = new JButton("Remove data");
			backButton2 = new JButton("Back");
			frame.add(sortAmount);
			frame.add(sortDate);
			frame.add(table);
			scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			frame.add(scrollPane);
			//table.setSize(100, 100);
			frame.add(backButton2);
			frame.add(removeRow);
			
			backButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sortAmount.setVisible(false);
					sortDate.setVisible(false);
					table.setVisible(false);
					backButton2.setVisible(false);
					scrollPane.setVisible(false);
					removeRow.setVisible(false);
					model.setColumnCount(0);
					tempDates.clear();
					tempTimes.clear();
					tempAmounts.clear();
					dates = null;
					times = null;
					amounts = null;
					dateSorted = false;
					amountSorted = false;
					drawMain();
				}
			});
			
			sortDate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ar = storage.readStorage();
					if (!dateSorted) {
						if (amountSorted) {
							model.setColumnCount(0);
							tempDates.clear();
							tempTimes.clear();
							tempAmounts.clear();
							dates = null;
							times = null;
							amounts = null;
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
					ar = storage.readStorage();
					if (!amountSorted) {
						if (dateSorted) {
							model.setColumnCount(0);
							tempDates.clear();
							tempTimes.clear();
							tempAmounts.clear();
							dates = null;
							times = null;
							amounts = null;
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
						model.addColumn("Date", dates);
						model.addColumn("Time", times);
						model.addColumn("Units", amounts);
						dateSorted = false;
						amountSorted = true;
					}
				}
			});
			
			removeRow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeSelectedRows(table, "storage.csv");
				}
			});
			
			drawReadAdded = true;
		}
		
		sortDate.setVisible(true);
		sortAmount.setVisible(true);
		table.setVisible(true);
		scrollPane.setVisible(true);
		backButton2.setVisible(true);
		removeRow.setVisible(true);
	}
	
	public void removeSelectedRows(JTable table, String file){
		   DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		   int[] rows = table.getSelectedRows();
		   for(int i=0;i<rows.length;i++){
		     String line = "";
		     for (int x = 0; x < 3; x++) {
		    	 line += model.getValueAt(rows[i]-i, x) + ","; 
		     }
		     model.removeRow(rows[i]-i);
		     storage.removeFromStorage(line, file);
		   }
		}
	
	public void removeSelectedRows2(JTable table, String file){
		   DefaultTableModel model = (DefaultTableModel) this.table2.getModel();
		   int[] rows = table.getSelectedRows();
		   for(int i=0;i<rows.length;i++){
		     String line = "";
		     for (int x = 0; x < 4; x++) {
		    	 line += model.getValueAt(rows[i]-i, x) + ","; 
		     }
		     line += model.getValueAt(rows[i]-i, 5) + ",";
		     model.removeRow(rows[i]-i);
		     System.out.println(line);
		     storage.removeFromStorage(line, file);
		   }
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
						float unit = Float.parseFloat(unitsAmount);
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
				}else {
					JOptionPane.showMessageDialog(frame, "Data entered is invalid", "Invalid", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
    
	public void calendar() {
		if(!drawCalAdded) {
	        calFrame.setTitle("Choose date");
	        calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        calFrame.setLayout(new FlowLayout());
	        calFrame.setResizable(false);
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
				}else {
					JOptionPane.showMessageDialog(calFrame, "Data entered is invalid", "Invalid", JOptionPane.WARNING_MESSAGE);
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
