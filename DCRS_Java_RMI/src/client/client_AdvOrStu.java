package client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
//import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Logger;

import dcrs_interfaces.AddInterface;
public class client_AdvOrStu{

	/**
	 * 
	 */
	static Writer writer =null;
	static String file_path = "C:\\Users\\shabnam\\Music\\distributed\\project 1\\";
	
	static Scanner scanner = new Scanner(System.in);
	//static HashMap<String,HashMap<String,String>> stu = new HashMap<>();
	public static AddInterface setObject(String course_name,int port,String userID,Writer writer) throws Exception{
		System.out.println("****Redirecting to "+course_name+" department****");
		writer.write("\r\n****Redirecting " +userID+" to "+course_name+" department****"+java.time.LocalDateTime.now());
		Registry registry = LocateRegistry.getRegistry(port);
		AddInterface Obj = (AddInterface)registry.lookup(course_name+"_interface");
		return Obj;
	}
	public static String[] add_course_details(Writer writer,String userID) throws Exception{
		System.out.println("Enter the course name followed by the size??");
		String add_course = scanner.next().toUpperCase();
		String add_size = scanner.next().toUpperCase();
		String ar[]=new String[3];
		if(add_course.substring(0,4).equalsIgnoreCase(userID.substring(0,4))& Integer.parseInt(add_size)>0){
			System.out.println("To which semester you want to add the course "+add_course+" of size "+add_size);
			String add_sem = scanner.next();
			if(add_sem.equalsIgnoreCase("FALL")||add_sem.equalsIgnoreCase("WINTER")||add_sem.equalsIgnoreCase("SUMMER")){
				ar[0]=add_course.toUpperCase();ar[1]=add_sem.toUpperCase();ar[2]=add_size;
				writer.write("\r\n"+userID +" wants to ADD course " +ar[0]+" "+ar[1]+" of size "+ar[2]+"\t"+java.time.LocalDateTime.now());
				return ar;
			}
			else{
				ar[0] = "Failure";
				String arb = "****INVALID: Check the request,add course details not valid.\n NOTE: Add course of your department of size>0 for valid terms****";
				System.out.println(arb);
				writer.write("\r\n"+userID+" "+arb+"\t"+java.time.LocalDateTime.now());
				return ar;
			}
		}
		else{
			ar[0] = "Failure";
			String arb = "****INVALID: Check the request,add course details not valid.\n NOTE: Add course of your department of size>0 for valid terms****";
			System.out.println(arb);
			writer.write("\r\n"+userID+" "+arb+"\t"+java.time.LocalDateTime.now());
			return ar;
		}
	}
	public static String[] remove_course_details(Writer writer,String userID) throws Exception{
		System.out.println("Enter the course name followed by the semester");
		String remove_course = scanner.next().toUpperCase();
		String remove_sem = scanner.next().toUpperCase();
		String ar[]=new String[2];
		if(remove_course.substring(0,4).equalsIgnoreCase(userID.substring(0,4))& 
				(remove_sem.equalsIgnoreCase("FALL")||remove_sem.equalsIgnoreCase("WINTER")||remove_sem.equalsIgnoreCase("SUMMER"))){
				ar[0]=remove_course.toUpperCase();ar[1]=remove_sem.toUpperCase();
				writer.write("\r\n"+userID +" wants to REMOVE course " +ar[0]+" "+ar[1]+"\t"+java.time.LocalDateTime.now());
				return ar;
			}
		else{
			ar[0] = "Failure";
			String arb = "****INVALID: Check the request,Remove course details not valid.\n NOTE: Remove course of your department for valid terms****";
			System.out.println(arb);
			writer.write("\r\n"+userID+" "+arb+"\t"+java.time.LocalDateTime.now());
			return ar;
		}
	}
	public static String list_course_details(Writer writer,String userID) throws Exception{
		System.out.println("Enter the Semester for which you want the details");
		String list_sem = scanner.next().toUpperCase();
		if(list_sem.equalsIgnoreCase("FALL")||list_sem.equalsIgnoreCase("WINTER")||list_sem.equalsIgnoreCase("SUMMER")){
				writer.write("\r\n"+userID +" wants to LIST course for " +list_sem+"\t"+java.time.LocalDateTime.now());
				return list_sem;
		}
		else{
			String ar = "Failure";
			String arb = "****INVALID: Check the request semester.****";
			System.out.println(arb);
			writer.write("\r\n"+userID+" "+arb+"\t"+java.time.LocalDateTime.now());
			return ar;
		}
	}
	public static String[] enroll_course(String stuID,Writer writer) throws Exception{
		System.out.println("Enter the Semester and course name for which you want to enroll");
		String enroll_course_sem = scanner.next().toUpperCase();
		String enroll_course = scanner.next().toUpperCase();
		String ar[]=new String[2];
		if((enroll_course.substring(0,4).equalsIgnoreCase("COMP")||enroll_course.substring(0,4).equalsIgnoreCase("SOEN")||
				enroll_course.substring(0,4).equalsIgnoreCase("INSE")) & (enroll_course_sem.equalsIgnoreCase("FALL")
						||enroll_course_sem.equalsIgnoreCase("WINTER")||enroll_course_sem.equalsIgnoreCase("SUMMER"))){
			ar[0]=enroll_course_sem;ar[1]=enroll_course;
			writer.write("\r\nStudent wants to ENROLL course " +ar[0]+" "+ar[1]+"\t"+java.time.LocalDateTime.now());
			return ar;
		}
		else{
			ar[0] = "Failure";
			String arb = "****INVALID: Check the request,Enroll course details not valid.\n NOTE: ENROLL course for valid department and valid terms****";
			System.out.println(arb);
			writer.write("\r\n"+arb+"\t"+java.time.LocalDateTime.now());
			return ar;
		}
	}
	public static String[] drop_course(Writer writer) throws IOException{
		System.out.println("Enter the Semester and course name you want to drop");
		String drop_course_sem = scanner.next().toUpperCase();
		String drop_course = scanner.next().toUpperCase();
		String ar[]=new String[2];
		if((drop_course.substring(0,4).equalsIgnoreCase("COMP")||drop_course.substring(0,4).equalsIgnoreCase("SOEN")||
				drop_course.substring(0,4).equalsIgnoreCase("INSE")) & (drop_course_sem.equalsIgnoreCase("FALL")
						||drop_course_sem.equalsIgnoreCase("WINTER")||drop_course_sem.equalsIgnoreCase("SUMMER"))){
			ar[0]=drop_course_sem;ar[1]=drop_course;
			writer.write("\r\nStudent wants to ENROLL course " +ar[0]+" "+ar[1]+"\t"+java.time.LocalDateTime.now());
			return ar;
		}
		else{
			ar[0] = "Failure";
			String arb = "****INVALID: Check the request,DROP course details not valid.\n NOTE: DROP course for valid department and valid terms****";
			System.out.println(arb);
			writer.write("\r\n"+arb+"\t"+java.time.LocalDateTime.now());
			return ar;
		}
	}
	public static String valid_user(String userID) throws Adv_Stu_Exception{
		if(userID.substring(0,4).equalsIgnoreCase("COMP")||userID.substring(0,4).equalsIgnoreCase("SOEN")||
				userID.substring(0,4).equalsIgnoreCase("INSE")){
			if(userID.substring(4,5).equalsIgnoreCase("A")||userID.substring(4,5).equalsIgnoreCase("S"))
				return userID;
			else
				throw new Adv_Stu_Exception("*****INVALID : UserID should belong to Advisor/Student***** " +userID);
		}
		else
			throw new Adv_Stu_Exception("*****INVALID : No such department available***** " +userID);
	}

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int comp_port = 2962;
		int inse_port = 2963;
		int soen_port = 2964;
		int port = 0;
		System.out.println("Enter your userID");
		String userID =scanner.next().toUpperCase();
		try{
			userID = valid_user(userID);
		}
		catch(Adv_Stu_Exception ex){
			System.out.println(ex);
			System.out.println("Re-Enter your userID");
			userID =scanner.next().toUpperCase();
			userID = valid_user(userID);
		}
		String department = userID.substring(0, 4);
		if(department.equalsIgnoreCase("COMP"))
			port=comp_port;
		else if(department.equalsIgnoreCase("SOEN"))
			port = soen_port;
		else if(department.equalsIgnoreCase("INSE"))
			port=inse_port;
		
		/***********Checking for USERID identity as Advisor or Student************/
		
		if(userID.substring(4,5).equalsIgnoreCase("A")){
			try{
				String log_file = file_path+"\\Advisor"+userID;
				writer = new BufferedWriter(new FileWriter(log_file+".txt",true));
				}
			catch(IOException ex){
				System.out.println(ex);
				}
			System.out.println("What would you like to do??");
			System.out.println("1. Add course , 2. Remove course ,3. List course availability");
			int operation =scanner.nextInt();
			switch (operation) {
			
			/**************Add course for all department******************/
			
			case 1:      
				System.out.println("Advisor wants to Add course");
				writer.write("\r\n"+userID +" Advisor wants to ADD course \t"+java.time.LocalDateTime.now());
				
				String str[] = add_course_details(writer,userID);
				if(str[0].equalsIgnoreCase("Failure")){
					writer.close();
					break;
				}
				else{
					String res[] = setObject(department,port,userID,writer).addCourse(str[0],str[1],Integer.parseInt(str[2]));
					System.out.println("\r\n"+res[0]+"\r\n"+res[1]+"\r\n"+res[2]+"\r\n"+res[3]);
					writer.write("\r\n"+res[0]+"\r\n"+res[1]+"\r\n"+res[2]+"\r\n"+res[3]+"\t"+java.time.LocalDateTime.now());
				}
				writer.close();
				break;
				
			case 2 :

				/**************Remove course for all department******************/
				
				System.out.println("Advisor wants to Remove course");
				writer.write("\r\n"+userID +" Advisor wants to REMOVE course \t"+java.time.LocalDateTime.now());
				String str1[] = remove_course_details(writer,userID);
				if(str1[0].equalsIgnoreCase("Failure")){
					writer.close();
					break;
				}
				else{
					String res[]=setObject(department,port,userID,writer).removeCourse(str1[0],str1[1]);
						System.out.println("\r\n"+res[0]+"\r\n"+res[1]+"\r\n"+res[2]+"\r\n"+res[3]);
						writer.write("\r\n"+res[0]+"\r\n"+res[1]+"\r\n"+res[2]+"\r\n"+res[3]+"\t"+java.time.LocalDateTime.now());
						}
				writer.close();
				break;
				
			case 3:
				
				/*************List course availability******************/
				
				System.out.println("Advisor wants to know the course availability");
				writer.write("\r\n"+userID +" Advisor wants to know the course availability \t"+java.time.LocalDateTime.now());
				 
				String data=null;
				String str2 = list_course_details(writer,userID);
				if(department.equalsIgnoreCase("COMP"))
					data = setObject("COMP",comp_port,userID,writer).listCourseAvailability(soen_port,inse_port,str2,userID);
				
				else if(department.equalsIgnoreCase("INSE"))
					data = setObject("INSE",inse_port,userID,writer).listCourseAvailability(comp_port,soen_port,str2,userID);
				
				else if(department.equalsIgnoreCase("SOEN"))
					data =setObject("SOEN",soen_port,userID,writer).listCourseAvailability(inse_port,comp_port,str2,userID);
				System.out.println(data);
				writer.write("\r\n"+data+"\t"+java.time.LocalDateTime.now());
				writer.close();
				break;
			default:
				System.out.println("****INVALID REQUEST****");
				writer.close();
				break;
			}	
		}
		else if(userID.substring(4,5).equalsIgnoreCase("S"))
		{
			try{
				String log_file = file_path+"\\Student"+userID;
				writer = new BufferedWriter(new FileWriter(log_file+".txt",true));
				}
			catch(IOException ex){
				System.out.println(ex);
				}
			System.out.println("What would you like to do??");
			System.out.println("1. Enroll course , 2. Drop course ,3. Get class schedule");
			//TODO add exception for string input 
			int operation =scanner.nextInt();
			switch (operation) {
			
			/*************Enroll course for all department******************/
			
			case 1:      
				System.out.println("Student wants to Enroll course");
				writer.write("\r\n"+userID +" Student wants to ENROLL course \t"+java.time.LocalDateTime.now());
				int p=0;
				String[] for_writer=new String[4];
				String str[] = enroll_course(userID,writer);
				if(str[0].equalsIgnoreCase("Failure")){
					writer.close();
					break;
				}
				else{
					if(str[1].substring(0, 4).equals("COMP"))
						p=comp_port;
					else if(str[1].substring(0, 4).equals("SOEN"))
						p = soen_port;
					else if(str[1].substring(0, 4).equals("INSE"))
						p=inse_port;
					for_writer =setObject(department,port,userID,writer).enrollCourse(userID, str[1],p, str[0]);
					if(for_writer[0].contains("Failure")){
						System.out.println(for_writer[0]);
						writer.write("\r\n"+for_writer[0]+"\t"+java.time.LocalDateTime.now());
					}
					else{
					System.out.println("\r\n"+for_writer[0]+"\r\n"+for_writer[1]+"\r\n"+for_writer[2]+"\r\n"+for_writer[3]);
					writer.write("\r\n"+for_writer[0]+"\r\n"+for_writer[1]+"\r\n"+for_writer[2]+"\r\n"+for_writer[3]+"\t"+java.time.LocalDateTime.now());
					}
				}
				writer.close();
				break;
			case 2:      
				System.out.println("Student wants to Drop course");
				writer.write("\r\n"+userID +" Student wants to DROP course \t"+java.time.LocalDateTime.now());
				String[] for_writer1=new String[5];
				int port1=0;
				String student_data=null;
				String str1[] = drop_course(writer);
				if(str1[0].equalsIgnoreCase("Failure")){
					writer.close();
					break;
				}
				if(str1[1].substring(0, 4).equals("COMP"))
					port1=comp_port;
				else if(str1[1].substring(0, 4).equals("INSE"))
					port1=inse_port;
				else if(str1[1].substring(0,4).equals("SOEN"))
					port1 = soen_port;
				student_data = setObject(department,port,userID,writer).dropCourseFromStu(userID, str1[1],str1[0]);
				for_writer1 =setObject(str1[1].substring(0, 4),port1,userID,writer).dropCourse(userID, str1[1],str1[0]);
				if(for_writer1[0].contains("Failure")){
					System.out.println(for_writer1[0]);
					writer.write("\r\n"+for_writer1[0]+"\t"+java.time.LocalDateTime.now());
				}
				else{
				System.out.println("\r\n"+for_writer1[0]+"\r\n"+for_writer1[1]+"\r\n"+for_writer1[2]+"\r\n"+for_writer1[3]+"\r\n"+for_writer1[4]);
				writer.write("\r\n"+for_writer1[0]+"\r\n"+for_writer1[1]+"\r\n"+for_writer1[2]+"\r\n"+for_writer1[3]+"\r\n"+for_writer1[4]+"\t"+java.time.LocalDateTime.now());
				}
			writer.close();
			break;
			case 3:      
				System.out.println("Student wants the class schedule");
				writer.write("\r\n"+userID +" Student wants the CLASS SCHEDULE\t"+java.time.LocalDateTime.now());
				String schedule=setObject(department,port,userID,writer).getClassSchedule(userID);
				System.out.println(schedule);
				writer.write("\r\nClass schedule for "+userID+" is "+schedule+"\t"+java.time.LocalDateTime.now());
				writer.close();
				break;
			default:
				System.out.println("****INVALID REQUEST****");
				break;
			}
		}
		else{
			Logger.getLogger("****INVALID USERID****");
			System.out.println("****INVALID USERID****");
		}
		
		
		scanner.close();
		
	}
	
}
