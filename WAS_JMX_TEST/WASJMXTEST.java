import java.lang.*;
import java.io.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.openmbean.CompositeData;
import javax.management.ObjectName;

public class WASJMXTEST
{
public static void main(String[] args)
{
if (args.length != 2)
{
System.out.println("Invalid number of arguments");
System.exit(1);
}
String host = args[0].toString();
String port = args[1].toString();
try 
{
javax.management.remote.JMXServiceURL url = new javax.management.remote.JMXServiceURL("service:jmx:rmi://"+host+"/jndi/rmi://"+host+":"+port+"/jmxrmi");
javax.management.remote.JMXConnector jmxc = javax.management.remote.JMXConnectorFactory.connect(url, null);
jmxc.connect();
Object memoryMbean = null;
Object osMbean = null;
javax.management.openmbean.CompositeData cd = null;
jmxc.getMBeanServerConnection().invoke(new javax.management.ObjectName("java.lang:type=Memory"), "gc", null, null);
memoryMbean = jmxc.getMBeanServerConnection().getAttribute(new javax.management.ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");
cd = (CompositeData) memoryMbean;
osMbean = jmxc.getMBeanServerConnection().getAttribute(new javax.management.ObjectName("java.lang:type=OperatingSystem"),"ProcessCpuTime");
System.out.println("Used memory: " + " " + cd.get("used") + "\nUsed cpu: " + osMbean);
System.exit(0);
}
catch (Exception e)
{
e.printStackTrace();
}
}
}
