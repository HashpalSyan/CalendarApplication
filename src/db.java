//prerequisites import into eclipse
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData; 




public class db
{
	//URL to Apache database
    public static String dbURL = "jdbc:derby://localhost:1527/Calender;Create=true;user=admin;password=12345";
    public static String tableName = "event";
    // jdbc Connection
    public static Connection conn = null;
    public static Statement stmt = null;

    public static void main(String[] args)
    {
        createConnection();
        //adds information to the 'event table'
        event("Appointment", "Watts Building w106", "31/12/2017", "1.00pm", "2.00pm");
        selectEvent();
        shutdown();
    }
    
    public static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
    //insert data 
    public static void event(String Name , String Location, String Date, String StartTime, String EndTime)
    {
        try
        {
        	
            stmt = conn.createStatement();
            //sql insert to the columns that are in the event table
            stmt.execute("insert into " + tableName + " values ('" +
                    Name + "','" + Location + "','" + Date + "','" + StartTime+"','"+ EndTime +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    //retrieve data that has been store on the event table
    public static void selectEvent()
    {
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-----------------------------------------------------------------------------------------------");

            while(results.next())
            {
                String Name = results.getString(1);
                String Location = results.getString(2);
                String Date = results.getString(3);
                String StartTime = results.getString(4);
                String EndTime = results.getString(5);
                System.out.println(Name + "\t\t" + Location+ "\t\t" + Date + "\t\t" + StartTime + "\t\t" + EndTime);
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public static void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }
}