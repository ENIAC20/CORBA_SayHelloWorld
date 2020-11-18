package server;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import sample.HelloWorld;
import sample.HelloWorldHelper;
import sample.HelloWorldImpl;

public class Hello_server {
	private ORB orb;
    private POA rootPOA;
    private org.omg.CORBA.Object obj;
    private org.omg.CORBA.Object ref;
    private org.omg.CORBA.Object objRef;
    private HelloWorldImpl helloWorldImpl;
    private HelloWorld helloref;
    private NamingContextExt ncRef;

	public static void main(String[] args){
		Hello_server hello_server = new Hello_server();
		hello_server.init();
	}
	
	private void init(){
		try {
			String[] args = {};
            Properties properties = new Properties();

            properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //ָ��ORB��ip��ַ
            properties.put("org.omg.CORBA.ORBInitialPort", "1050");       //ָ��ORB�Ķ˿�

            //����һ��ORBʵ��
            orb = ORB.init(args, properties);  //��ʼ��orb

            //�õ�RootPOA������,������POAManager,�൱��������server
            obj = orb.resolve_initial_references("RootPOA");
            rootPOA = POAHelper.narrow(obj);
            rootPOA.the_POAManager().activate();
            
            //����һ��helloWorldImplʵ��
            helloWorldImpl = new HelloWorldImpl();
            
            //�ӷ����еõ����������,��ע�ᵽ������
            ref = rootPOA.servant_to_reference(helloWorldImpl);
            helloref = HelloWorldHelper.narrow(ref);
            
            //�õ�һ����������������
            objRef = orb.resolve_initial_references("NameService");
            ncRef = NamingContextExtHelper.narrow(objRef);

            //�������������а��������
            String name = "HelloWorld";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, helloref);
            
            System.out.println("server.HelloWorld is ready and waiting....");
            
            //�����̷߳���,�ȴ��ͻ��˵���
            orb.run();
            
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
