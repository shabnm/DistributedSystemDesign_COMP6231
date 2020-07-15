package servers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import dcrs_interfaces.implementInterface;

public final class COMP_server{
	
	static Writer writer =null;
	static String file_path = "C:\\Users\\shabnam\\Music\\distributed\\project 1\\";
	
	static HashMap<String, Integer> fall_course_details= new HashMap<String, Integer>();
	static HashMap<String, Integer> winter_course_details= new HashMap<String, Integer>();
	static HashMap<String, Integer> summer_course_details= new HashMap<String, Integer>();
	static HashMap<String, HashMap<String , Integer>> comp_sem_details= new HashMap<String, HashMap<String , Integer>>();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try{
			String log_file = file_path+"COMP_Server";
			writer = new BufferedWriter(new FileWriter(log_file+".txt",true));
			}
		catch(IOException ex){
			System.out.println(ex);
			}
		implementInterface stub = new implementInterface();
		Registry registry = LocateRegistry.createRegistry(2962);
		registry.bind("COMP_interface", stub);
		System.out.println("COMP Server is started");
		writer.write("\r\nCOMP Server is started");
		DatagramSocket aSocket = null;
		
		String course_name = "COMP";
		String semester_name;

		for(int i =1 ;i<=3;i++){
			for(int j =1;j<=2;j++){
				if(i==1){
					semester_name = "FALL";
					fall_course_details.put(course_name+j, 0);
					comp_sem_details.put(semester_name, fall_course_details);
				}
				else if(i==2){
					semester_name = "WINTER";
					winter_course_details.put(course_name+j, i+j);
					comp_sem_details.put(semester_name, winter_course_details);
				}
				else{
					semester_name = "SUMMER";
					summer_course_details.put(course_name+j, i+j);
					comp_sem_details.put(semester_name, summer_course_details);
				}	
			}
		}
		System.out.println(comp_sem_details);
		writer.write("\r\nServer has courses : "+comp_sem_details.toString());
		try {
			aSocket = new DatagramSocket(2962);
			byte[] buffer = new byte[1000];
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				String req = new String(request.getData(),request.getOffset(),request.getLength(),StandardCharsets.UTF_8);
				writer.write("\r\nReceived request from :" +request.getPort()+" "+req);
				byte[] data1=null;
				String[] enroll_request = req.split(" ");
				try{
					if(enroll_request.length==1){
						HashMap<String, Integer> data = comp_sem_details.get(req);
						writer.write("\r\n" +comp_sem_details.get(req));
						data1=data.toString().getBytes();
					}
					else{
						if(comp_sem_details.get(enroll_request[0]).containsKey(enroll_request[1])==false){
							System.out.println("ERROR : no course available for "+enroll_request[0]+" term" +""+enroll_request[1]);
							writer.write("\r\nERROR : no course available for "+enroll_request[0]+" term" +""+enroll_request[1]);
							data1 = "Failure : No course".getBytes();
						}
						else{
							System.out.println("BEFORE : "+comp_sem_details);
							int seat = comp_sem_details.get(enroll_request[0]).get(enroll_request[1]);
							if(seat==0){
								System.out.println("ERROR : no seats available");
								writer.write("\r\nERROR : no seats available");
								data1 = "Failure : No seat".getBytes();
							}
							else{
								comp_sem_details.get(enroll_request[0]).put(enroll_request[1],seat-1);
								System.out.println("Success : Course size reduced by 1");
								writer.write("Success : Course size reduced by 1");
								System.out.println("AFTER : "+comp_sem_details);
								data1 = "Success : Course size reduced by 1".getBytes();
							}
						}
					}
					DatagramPacket reply = new DatagramPacket(data1, data1.length, request.getAddress(),request.getPort());
					aSocket.send(reply);
					writer.write("\r\nReply sent to "+request.getPort() );
				}
				catch(NullPointerException ne){
					System.out.println("Caught" +ne);
					writer.write("\r\nCaught" +ne);
				}
			}
			
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
			writer.write("\r\nSocket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
			writer.write("IO: " + e.getMessage());
		} finally {
			writer.close();
			if (aSocket != null){
				aSocket.close();
			}
		}
	}
	/**********To get detail of COMP department
	 * @throws IOException *********/
	public HashMap<String , HashMap<String,Integer>> getSemDetails() throws IOException{
		writer.write("\r\nSem Details : "+comp_sem_details.toString());
		return comp_sem_details;	
	}
	/****To get detail related to semester****/
	public HashMap<String,Integer> getSemDetails(String sem){
		return comp_sem_details.get(sem);	
	}
	/****To get the seat availability of each term****/
	public Integer getSeatDetails(String sem,String course_name){
		return comp_sem_details.get(sem).get(course_name);	
	}
	/****To add courses****/
	public String setSemDetails(String course_name,String sem,int size){
		if(sem.equalsIgnoreCase("FALL")){
			fall_course_details.put(course_name, size);	
			comp_sem_details.put(sem, fall_course_details);
			return "Success";
		}
		else if(sem.equalsIgnoreCase("WINTER")){
			winter_course_details.put(course_name, size);
			comp_sem_details.put(sem, winter_course_details);
			return "Success";
		}
		else if(sem.equalsIgnoreCase("SUMMER")){
			summer_course_details.put(course_name, size);
			comp_sem_details.put(sem, summer_course_details);
			return "Success";
		}
		else{
			System.out.println("****INVALID SEMESTER****");
			return "Failure";
		}
		
	}
	public void delSemDetails(String course_name, String sem){
		if(comp_sem_details.get(sem).get(course_name)!=null)
			comp_sem_details.get(sem).remove(course_name);
		else
			System.out.println("****Course not present in required term****");
	}

	public String sendMessage(int serverPort,String sem) {
		DatagramSocket aSocket = null;
		DatagramPacket reply = null;
		try {
			aSocket = new DatagramSocket();
			String msg = sem;
			byte[] message = msg.getBytes();
			InetAddress aHost = InetAddress.getByName("localhost");
			DatagramPacket request = new DatagramPacket(message, msg.length(), aHost, serverPort);
			aSocket.send(request);
			System.out.println("Request message sent from the client to server with port number " + serverPort + " is: "+msg);
			byte[] buffer = new byte[2000];
			reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			System.out.println("Reply received from the server with port number " + serverPort + " is: "
					+ new String(reply.getData()));
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
		return new String(reply.getData(),reply.getOffset(),reply.getLength(),StandardCharsets.UTF_8);
	}
	public String sendMessage(int serverPort,String sem,String courseId) {
		DatagramSocket aSocket = null;
		DatagramPacket reply = null;
		try {
			aSocket = new DatagramSocket();
			String msg = sem+" "+courseId;
			byte[] message = msg.getBytes();
			InetAddress aHost = InetAddress.getByName("localhost");
			DatagramPacket request = new DatagramPacket(message, msg.length(), aHost, serverPort);
			aSocket.send(request);
			System.out.println("Request message sent from the client to server with port number " + serverPort + " is: "+msg);
			byte[] buffer = new byte[2000];
			reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			System.out.println("Reply received from the server with port number " + serverPort + " is: "
					+ new String(reply.getData()));
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
		return new String(reply.getData(),reply.getOffset(),reply.getLength(),StandardCharsets.UTF_8);
	}
	

}
