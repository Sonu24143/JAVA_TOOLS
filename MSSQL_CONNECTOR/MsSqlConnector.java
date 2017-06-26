import java.io.File;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import com.microsoft.sqlserver.jdbc.*;

public class MsSqlConnector 
{
public static String ip = "";
public static String port = "";
public static String database_name = "";
public static String sql_file = "";
public static String domain = "";
public static String connectionUrl = "";
public static void main(String[] args) 
{
if ( args.length != 4 && args.length != 5 )
{
System.out.println("Incorrect number of arguments, Exiting...");
System.exit(1);
}
if ( args.length == 4 )
{
ip = args[0].toString();
port = args[1].toString();
domain = args[2].toString();
sql_file = args[3].toString();
connectionUrl = "jdbc:sqlserver://"+ip+":"+port+";domain="+domain+";";
}
if ( args.length == 5 )
{
ip = args[0].toString();
port = args[1].toString();
domain = args[2].toString();
database_name = args[3].toString();
sql_file = args[4].toString();
String connectionUrl = "jdbc:sqlserver://"+ip+":"+port+";domain="+domain+";databaseName="+database_name+";";
}
Connection con = null;
Statement stmt = null;
ResultSet rs = null;
try 
{
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
con = DriverManager.getConnection(connectionUrl , "sa" , "sa123");
//String SQL = "SELECT name FROM master.dbo.sysdatabases;";
String SQL = new Scanner(new File(sql_file)).useDelimiter("\\Z").next();
stmt = con.createStatement();
rs = stmt.executeQuery(SQL);
ResultSetMetaData rsmd = rs.getMetaData();
int columnsNumber = rsmd.getColumnCount();
while (rs.next()) 
{
for (int i = 1; i <= columnsNumber; i++) 
{
if (i > 1) 
System.out.print(",  ");
String columnValue = rs.getString(i);
System.out.print(columnValue + " " + rsmd.getColumnName(i));
}
System.out.println("");
}
}
catch (Exception e) 
{
e.printStackTrace();
}
finally 
{
if (rs != null) try { rs.close(); } catch(Exception e) {}
if (stmt != null) try { stmt.close(); } catch(Exception e) {}
if (con != null) try { con.close(); } catch(Exception e) {}
}
}
}
