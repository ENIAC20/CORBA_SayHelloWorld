package client;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import sample.HelloWorld;
import sample.HelloWorldHelper;

public class Hello_client {
	private ORB orb;
    private org.omg.CORBA.Object objRef;
    private NamingContextExt ncRef;
    private HelloWorld helloWorld;
    
    public static void main(String[] args){
    	Hello_client hello_client = new Hello_client();
    	hello_client.init();
    	hello_client.work();
    }
    
    private void init(){
    	System.out.println("client.HelloWorld init config start....");
        try {
        	String[] args = {};
            Properties properties = new Properties();
            properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //指定ORB的ip地址
            properties.put("org.omg.CORBA.ORBInitialPort", "1050");       //指定ORB的端口

            //创建一个ORB实例
            orb = ORB.init(args, properties);
            
            //获取根名称上下文
            objRef = orb.resolve_initial_references("NameService");
            ncRef = NamingContextExtHelper.narrow(objRef);
            
            helloWorld = HelloWorldHelper.narrow(ncRef.resolve_str("HelloWorld"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    	System.out.println("client.HelloWorld init config end....");
    }
    
    private void work(){
    	String str = helloWorld.sayHello();
    	System.out.println(str);
    }
}
