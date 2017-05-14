import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalenderProgram{
    static JLabel lblMonth, lblYear, lable, lblEvent, lblLoc,lblDate,lblTimeS,lblTimeE;
    static JButton btnPrev, btnNext, btnNewEvent, btnConfirm , btnClear;
    static JTable tblCalendar;
    static JComboBox<String> cmbYear, cmbStart, cmbEnd;
    static JFrame frmMain, frame;
    static Container pane, popPane;
    static DefaultTableModel mtblCalendar; //Table model
    static JScrollPane stblCalendar; //The scroll pane
    static JPanel pnlCalendar;
    static JPanel pnlGrid;
    static JPanel pnlEvent;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    static JTextField eventName, eventLoc, eventDate;
    static JTextArea Area;
   
    
    
    
    public static void main (String args[]){
        //Look and feel
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}
        
        //Prepare frame
        frmMain = new JFrame ("Brighton University Calendar"); //Create frame
        frmMain.setSize(1100, 375); //Set size to 1100x375 pixels
        pane = frmMain.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
        
        //Set new popup frame
        frame = new JFrame("New Event");
   
        popPane =  frame.getContentPane();
        popPane.setLayout(null);
        
        //Create controls
        lblMonth = new JLabel ("January");
        lblYear = new JLabel ("Change year:");
        cmbYear = new JComboBox();
        btnPrev = new JButton ("Previous");
        btnNext = new JButton ("Next");
        mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);
        pnlCalendar = new JPanel(null);
        pnlGrid = new JPanel(null);
        pnlEvent = new JPanel(null);
        btnNewEvent = new JButton("Create New Event");
        cmbStart = new JComboBox();
        cmbEnd = new JComboBox();
        eventName = new JTextField();
        eventLoc = new JTextField();
        lblLoc = new JLabel("Event Name");
        lblEvent = new JLabel("Event Location");
        eventDate = new JTextField();
        btnConfirm = new JButton("Confirm");
        lblDate = new JLabel("Date (dd/mm/yy/)");
        lblTimeS = new JLabel("Start Time (mm/hh)");
        lblTimeE = new JLabel("End Time (mm/hh)");
        Area = new JTextArea();
        btnClear = new JButton("Clear");
       
      
        
        
        //Set border
        pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
        
        //Register action listeners
        btnPrev.addActionListener(new btnPrev_Action());
        btnNext.addActionListener(new btnNext_Action());
        cmbYear.addActionListener(new cmbYear_Action());
        btnNewEvent.addActionListener(new btn_Event());
        btnConfirm.addActionListener(new btn_Confirm());
        btnClear.addActionListener(new btn_Cancel());
        
        //Add controls to pane
        pane.add(pnlCalendar);
        pnlCalendar.add(lblMonth);			//Month
        pnlCalendar.add(lblYear);			//Year Label
        pnlCalendar.add(cmbYear);			//Year 
        pnlCalendar.add(btnPrev);			//Previous Button
        pnlCalendar.add(btnNext);			//Next Button
        pnlCalendar.add(btnNewEvent);		//
        pane.add(pnlGrid);					//
        popPane.add(pnlEvent);				//
        pnlGrid.add(stblCalendar);			//
        pnlEvent.add(cmbStart);				//Event Start Time
        pnlEvent.add(cmbEnd);				//Event End Time
        pnlEvent.add(lblLoc);				//Event Location Label 
        pnlEvent.add(lblEvent);				//Event Name Label
        pnlEvent.add(eventName);			//Event Name
        pnlEvent.add(eventLoc);				//Event Location
        pnlEvent.add(eventDate);			//Event Date
        pnlEvent.add(btnConfirm);			//Event Confirm Button
        pnlEvent.add(lblDate);				//Event Date Label
        pnlEvent.add(lblTimeS);				//Event Start Time Label 
        pnlEvent.add(lblTimeE);				//Event End Time Label
        pnlGrid.add(btnClear);
        pnlGrid.add(Area);
      
        //Set bounds
        pnlCalendar.setBounds(0, 0, 300, 335); //Setting the (x position, y position, width ,height)
        pnlGrid.setBounds(310,0,1000,500);
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
        lblYear.setBounds(160-lblYear.getPreferredSize().width/2, 130, 80, 20);
        cmbYear.setBounds(110, 150, 100, 50);
        btnPrev.setBounds(40, 70, 90, 50);
        btnNext.setBounds(180, 70, 90, 50);
        stblCalendar.setBounds(0, 50, 300, 250);
        btnNewEvent.setBounds(90, 220,150,50);
        pnlEvent.setBounds(0,0,640, 400);
        cmbEnd.setBounds(120,200,80,30);
        cmbStart.setBounds(120,150,80,30);
        eventName.setBounds(100, 30, 180, 30);
        eventLoc.setBounds(100, 100, 180, 30);
        lblEvent.setBounds(0,30,80,30);
        lblLoc.setBounds(0,100,70,30);
        lblDate.setBounds(0, 250, 100, 30);
        lblTimeS.setBounds(0, 150, 100, 30);
        lblTimeE.setBounds(0, 200, 100, 30);
        eventDate.setBounds(120, 250, 80, 30);
        btnConfirm.setBounds(105,320,100,70);
        Area.setBounds(320, 10, 450, 270);
        btnClear.setBounds(500,285,80, 60);

        
       //adding combo items
        cmbStart.addItem("00:00"); //adding the times 00:00 - 24:00 to each Combo Box)
        cmbStart.addItem("01:00"); 
        cmbStart.addItem("02:00");
        cmbStart.addItem("03:00");
        cmbStart.addItem("04:00");
        cmbStart.addItem("05:00");
        cmbStart.addItem("06:00");
        cmbStart.addItem("07:00");
        cmbStart.addItem("08:00");
        cmbStart.addItem("09:00");
        cmbStart.addItem("10:00");
        cmbStart.addItem("11:00");
        cmbStart.addItem("12:00");
        cmbStart.addItem("13:00");
        cmbStart.addItem("14:00");
        cmbStart.addItem("15:00");
        cmbStart.addItem("16:00");
        cmbStart.addItem("17:00");
        cmbStart.addItem("18:00");
        cmbStart.addItem("19:00");
        cmbStart.addItem("20:00");
        cmbStart.addItem("21:00");
        cmbStart.addItem("22:00");
        cmbStart.addItem("23:00");
        cmbStart.addItem("24:00");
        
        cmbEnd.addItem("00:00");
        cmbEnd.addItem("01:00");
        cmbEnd.addItem("02:00");
        cmbEnd.addItem("03:00");
        cmbEnd.addItem("04:00");
        cmbEnd.addItem("05:00");
        cmbEnd.addItem("06:00");
        cmbEnd.addItem("07:00");
        cmbEnd.addItem("08:00");
        cmbEnd.addItem("09:00");
        cmbEnd.addItem("10:00");
        cmbEnd.addItem("11:00");
        cmbEnd.addItem("12:00");
        cmbEnd.addItem("13:00");
        cmbEnd.addItem("14:00");
        cmbEnd.addItem("15:00");
        cmbEnd.addItem("16:00");
        cmbEnd.addItem("17:00");
        cmbEnd.addItem("18:00");
        cmbEnd.addItem("19:00");
        cmbEnd.addItem("20:00");
        cmbEnd.addItem("21:00");
        cmbEnd.addItem("22:00");
        cmbEnd.addItem("23:00");
        cmbEnd.addItem("24:00");
        
        //Make frame visible
        frmMain.setResizable(false); //boolean values on objects
        frmMain.setVisible(true);
        Area.setEnabled(false);
        
        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;
        
        //Add headers
        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
        for (int i=0; i<7; i++){
            mtblCalendar.addColumn(headers[i]);
        }
        
        tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background
        
        //No resize/reorder
        tblCalendar.getTableHeader().setResizingAllowed(false);	//Disables header resizing
        tblCalendar.getTableHeader().setReorderingAllowed(false); //Disables re-ordering header
        
        //Single cell selection
        tblCalendar.setColumnSelectionAllowed(true);	//Allows selection of columns
        tblCalendar.setRowSelectionAllowed(true);		//Allows selection of rows
        tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	// Allows selections of single cells
        
        //Set row/column count
        tblCalendar.setRowHeight(38); //Sets hight of rows
        mtblCalendar.setColumnCount(7);	//Sets table width
        mtblCalendar.setRowCount(6);	//Sets table height
        
        //Populate table
        for (int i=realYear-100; i<=realYear+100; i++){
            cmbYear.addItem(String.valueOf(i));
        }
        
        //Refresh calendar
        refreshCalendar (realMonth, realYear); //Refresh calendar
    }
    
    public static void refreshCalendar(int month, int year){
        //Variables
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int nod, som; //Number Of Days, Start Of Month
        
        //Allow/disallow buttons
        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
        if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
        lblMonth.setText(months[month]); //Refresh the month label (at the top)
        lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
        
        //Clear table
        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                mtblCalendar.setValueAt(null, i, j);
            }
        }
        
        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        
        //Draw calendar
        for (int i=1; i<=nod; i++){
            int row = new Integer((i+som-2)/7);
            int column  =  (i+som-2)%7;
            mtblCalendar.setValueAt(i, row, column);
        }
        
        //Apply renderers
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
    }
    
    static class tblCalendarRenderer extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6){ //Week-end
                setBackground(new Color(255, 228, 122));
            }
            else{ //Week
                setBackground(new Color(128, 191, 255));
            }
            if (value != null){
                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
                    setBackground(new Color(255, 255, 255));
                }
            }
            setBorder(null);
            setForeground(Color.black);
            return this;
        }
    }
    
    
    
    static class btn_Cancel implements ActionListener{
    	public void actionPerformed (ActionEvent e){
    		Area.setText(null); //When this button is pressed, it will clear the Text Area 
    	}
    }
    
    //Button for confirm in create event window
    static class btn_Confirm implements ActionListener{
    	public void actionPerformed (ActionEvent e){
    		String TimeSdb = (String)cmbStart.getSelectedItem(); //takes the values of the fields 
    		String TimeEdb = (String)cmbEnd.getSelectedItem(); //and sets them as variables to be 
    		String Eventdb = eventDate.getText(); //used in the database
    		String Locdb = eventLoc.getText();
    		String Namedb = eventName.getText();
    		
    		Area.append("Event Location: "); //writes down all of the values into the Text Area
    		Area.append(Namedb);
    		Area.append("\n");
    		Area.append("Event Name: ");
    		Area.append(Locdb);
    		Area.append("\n");
    		Area.append("Event Start Time: ");
    		Area.append(TimeSdb + "\t\t" + "Event End Time: " + TimeEdb + "\n");
    		Area.append("Event Date: " + Eventdb + "\n\n");
    		
    	} 
    }
    
    static class btn_Event implements ActionListener{
    	public void actionPerformed (ActionEvent e){
    		frame.setVisible(true); // Makes the event window visible when the 
    		frame.setSize(300, 600); //"Create New Event" button is clicked
    	}
    }
    
    static class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 0){ //Back one year
                currentMonth = 11;
                currentYear -= 1;
            }
            else{ //Back one month
                currentMonth -= 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (currentMonth == 11){ //Forward one year
                currentMonth = 0;
                currentYear += 1;
            }
            else{ //Forward one month
                currentMonth += 1;
            }
            refreshCalendar(currentMonth, currentYear);
        }
    }
    static class cmbYear_Action implements ActionListener{ //Changes the year on the calendar
        public void actionPerformed (ActionEvent e){
            if (cmbYear.getSelectedItem() != null){
                String b = cmbYear.getSelectedItem().toString();
                currentYear = Integer.parseInt(b);
                refreshCalendar(currentMonth, currentYear);
            }
        }
    }
}