package dcrs_interfaces;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import dcrs_interfaces.AddInterface;
import servers.COMP_server;
import servers.INSE_server;
import servers.SOEN_server;;

public class implementInterface extends UnicastRemoteObject implements AddInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public implementInterface() throws Exception {
		super();
		
		// TODO Auto-generated constructor stub
	}
	static HashMap<String,HashMap<String,ArrayList<String>>> stu = new HashMap<>();
	static HashMap<String,Integer> userData=new HashMap<>();
	int count =0;
	@Override
	public String[] addCourse(String courseId, String sem, int size) throws Exception {
		// TODO Auto-generated method stub
		String res;
		String[] str =new String[4]; 
		String department = courseId.substring(0,4);
		System.out.println("Invoke department : "+department);
		str[1]="Invoke department : "+department;
		System.out.println("****Adding course "+courseId+" with size " +size+ " in "+sem+" term****");
		str[2]= "****Adding course "+courseId+" with size " +size+ " in "+sem+" term****";
		try{
		switch (department) {
		case "COMP":
			COMP_server comp = new COMP_server();
			if(comp.getSemDetails(sem).containsKey(courseId)){
				System.out.println("INVALID : "+sem+" already contains "+courseId+" "+comp.getSemDetails(sem));
				str[3] = "INVALID : "+sem+" already contains "+courseId+" "+comp.getSemDetails(sem);
				str[0] = "Failure";
				return str;
			}
			res = comp.setSemDetails(courseId, sem,size);
			System.out.println(res + " "+comp.getSemDetails());
			str[3] = res +" Updated list is "+comp.getSemDetails();
			str[0]="Success";
			return str;
		case "SOEN":
			SOEN_server soen = new SOEN_server();
			if(soen.getSemDetails(sem).containsKey(courseId)){
				System.out.println("INVALID : "+sem+" already contains "+courseId+" "+soen.getSemDetails(sem));
				str[3] = "INVALID : "+sem+" already contains "+courseId+" "+soen.getSemDetails(sem);
				str[0] = "Failure";
				return str;
			}
			res = soen.setSemDetails(courseId, sem,size);
			System.out.println(res +" " +soen.getSemDetails());
			str[3] = res +" Updated list is "+soen.getSemDetails();
			str[0]="Success";
			return str;
		case "INSE":
			INSE_server inse = new INSE_server();
			if(inse.getSemDetails(sem).containsKey(courseId)){
				System.out.println("INVALID : "+sem+" already contains "+courseId+" "+inse.getSemDetails(sem));
				str[3] = "INVALID : "+sem+" already contains "+courseId+" "+inse.getSemDetails(sem);
				str[0] = "Failure";
				return str;
			}
			res = inse.setSemDetails(courseId, sem,size);
			System.out.println(res+" "+inse.getSemDetails());
			str[3] =res +" Updated list is "+inse.getSemDetails();
			str[0]="Success";
			return str;
		default:
			return null;
		}}
		catch(Exception ex){
			System.out.println("Exception"+ex);
			str[0]= "Exception"+ex;
			return str;
		}
	}
	@Override
	public String[] removeCourse(String courseId, String sem) throws Exception {
		// TODO Auto-generated method stub
		String res;
		String[] str =new String[4];
		String department = courseId.substring(0,4);
		System.out.println("Invoke department : "+department);
		str[1]="Invoke department : "+department;
		System.out.println("****Removing course "+courseId+" from "+sem+" term****");
		str[1] =str[1]+ "****Removing course "+courseId+" from "+sem+" term****";
		try{
		switch (department) {
		case "COMP":
			COMP_server comp = new COMP_server();
			if(comp.getSemDetails(sem).containsKey(courseId)){
				comp.delSemDetails(courseId, sem);
				str[2]="BEFORE : "+stu;
				try{
				for(String key: stu.keySet()){
					for(String value1:stu.get(key).keySet()){
						if(value1.equals(sem)){
							if(stu.get(key).get(value1).contains(courseId))
								stu.get(key).get(value1).remove(courseId);
						}
					}
				}
				System.out.println(stu);
				str[2]=str[2] + "\nAFTER : "+stu;
				}
				catch(Exception ex){
					System.out.println("No matching data found in stu for " +sem+" "+courseId+" "+ " in "+stu.values()+" "+ex);
				}
				System.out.println(comp.getSemDetails());
				str[3] = " Updated list is "+comp.getSemDetails();
				str[0]="Success";
				return str;
			}
			System.out.println("INVALID : "+sem+" doesnot contains "+courseId+" "+comp.getSemDetails(sem));
			str[3] = "INVALID : "+sem+" doesnot contains "+courseId+" "+comp.getSemDetails(sem);
			str[0] = "Failure";
			return str;
		case "SOEN":
			SOEN_server soen = new SOEN_server();
			if(soen.getSemDetails(sem).containsKey(courseId)){
				soen.delSemDetails(courseId, sem);
				str[2]="BEFORE : "+stu;
				try{
				for(String key: stu.keySet()){
					for(String value1:stu.get(key).keySet()){
						if(value1.equals(sem)){
							if(stu.get(key).get(value1).contains(courseId))
								stu.get(key).get(value1).remove(courseId);
						}
					}
				}
				System.out.println(stu);
				str[2]=str[2] + "\nAFTER : "+stu;
				}
				catch(Exception ex){
					System.out.println("No matching data found in stu for " +sem+" "+courseId+" "+ " in "+stu.values()+" "+ex);
				}
				System.out.println(soen.getSemDetails());
				str[3] = " Updated list is "+soen.getSemDetails();
				str[0]="Success";
				return str;
			}
			System.out.println("INVALID : "+sem+" doesnot contains "+courseId+" "+soen.getSemDetails(sem));
			str[3] = "INVALID : "+sem+" doesnot contains "+courseId+" "+soen.getSemDetails(sem);
			str[0] = "Failure";
			return str;
		case "INSE":
			INSE_server inse = new INSE_server();
			if(inse.getSemDetails(sem).containsKey(courseId)){
				inse.delSemDetails(courseId, sem);
				System.out.println(inse.getSemDetails());
				str[2]="BEFORE : "+stu;
				try{
				for(String key: stu.keySet()){
					for(String value1:stu.get(key).keySet()){
						if(value1.equals(sem)){
							if(stu.get(key).get(value1).contains(courseId))
								stu.get(key).get(value1).remove(courseId);
						}
					}
				}
				System.out.println(stu);
				str[2]=str[2] + "\nAFTER : "+stu;
				}
				catch(Exception ex){
					System.out.println("No matching data found in stu for " +sem+" "+courseId+" "+ " in "+stu.values()+" "+ex);
				}
				str[3] = " Updated list is "+inse.getSemDetails();
				str[0]="Success";
				return str;
			}
			System.out.println("INVALID : "+sem+" doesnot contains "+courseId+" "+inse.getSemDetails(sem));
			str[3] = "INVALID : "+sem+" doesnot contains "+courseId+" "+inse.getSemDetails(sem);
			str[0] = "Failure";
			return str;
		default:
			return null;
		}}
		catch(Exception ex){
			System.out.println("Exception"+ex);
			str[0]="Exception"+ex;
			return str;
		}
	}
	@Override
	public String listCourseAvailability(int first_dep_port,int second_dep_port,String sem,String userID) throws RemoteException {
		// TODO Auto-generated method stub
		String self,one,two; 
		String department = userID.substring(0,4);
		System.out.println("Invoke department : "+department);
		System.out.println("****Getting details of "+sem.toUpperCase()+" term****");
		switch (department){
		case "COMP":
			COMP_server comp = new COMP_server();
			self = comp.getSemDetails(sem).toString();
			one = comp.sendMessage(first_dep_port,sem);
			two = comp.sendMessage(second_dep_port, sem);
			return self+one+two;
		case "SOEN":
			SOEN_server soen = new SOEN_server();
			self = soen.getSemDetails(sem).toString();
			one=SOEN_server.sendMessage(first_dep_port,sem);
			two=SOEN_server.sendMessage(second_dep_port, sem);
			return self+one+two;
		case "INSE":
			INSE_server inse = new INSE_server();
			self = inse.getSemDetails(sem).toString();
			one = INSE_server.sendMessage(first_dep_port,sem);
			two = INSE_server.sendMessage(second_dep_port, sem);
			return self+one+two;
		default:
			System.out.println("*******INVALID DEPARTMENT*******");
			break;
		}
		return null;
	}
	@Override
	public String[] enrollCourse(String stuID, String courseId,int coursePort, String sem) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,ArrayList<String>> sem_and_course= new HashMap<>();
		sem_and_course.clear();
		ArrayList<String> str_ary = new ArrayList<>();
		str_ary.add(courseId);
		String[] for_writer = new String[4];
		int size=0;
		if(userData.get(stuID)==null)
			userData.put(stuID,0);
		String enroll_course_other_dep=null;
		String department = stuID.substring(0,4);
		String course_dep = courseId.substring(0,4);
		System.out.println("Invoke department : "+department);
		System.out.println("****Adding course " +sem+" "+courseId+" in "+stuID+"****");
		for_writer[1] = "****Adding course " +sem+" "+courseId+" in "+stuID+"****";
		try{
		switch (department) {
		case "COMP":
			COMP_server comp = new COMP_server();
			if(stu.containsKey(stuID)){
				if(stu.get(stuID).containsKey(sem)){
					ArrayList<String> stu_course = stu.get(stuID).get(sem);
					if(stu.get(stuID).get(sem).contains(courseId)==true){
						System.out.println("Failure : Student "+stuID+" ALREADY enrolled in : "+sem+" "+courseId);
						for_writer[0]="Failure : Student "+stuID+" ALREADY enrolled in : "+sem+" "+courseId;
						return for_writer;
					}
					else if(stu_course.size()>=3){
						System.out.println("Failure : Student "+stuID+ " has already reached max limit for sem "+sem +" "+stu.get(stuID).get(sem));
						for_writer[0]="Failure : Student "+stuID+ " has already reached max limit for sem "+sem +" "+stu.get(stuID).get(sem);
						return for_writer;
					}
					else{
						if(course_dep.equals(department)){
							size = comp.getSeatDetails(sem,courseId);
							if (size==0){
								System.out.println("Failure : No seat available in the course");
								for_writer[0]=("Failure : No seat available in the course");
								return for_writer;
							}
							else{
								comp.setSemDetails(courseId, sem, size-1);
								System.out.println(comp.getSemDetails());
								for_writer[2]= "Course size reduced by 1 ";
							}
						}
						else{
							if(userData.get(stuID)<2){
								enroll_course_other_dep =comp.sendMessage(coursePort,sem,courseId);
								if(enroll_course_other_dep.contains("Failure")){
									System.out.println("No seat available in the course");
									for_writer[0]=("Failure : No seat available in the course");
									return for_writer;
								}
								for_writer[2]= "Course size reduced by 1 ";
								userData.put(stuID, userData.get(stuID)+1);
							}
							else{
								System.out.println("Failure : Can enroll in max 2 courses from others department");
								for_writer[0]=("Failure : Can enroll in max 2 courses from others department");
								return for_writer;
							}
						}
						stu.get(stuID).get(sem).add(courseId);
						for_writer[0]= "SUCCESS";
						for_writer[3]= stu.toString()+" for student "+stuID;
					}
				}
				else{
					if(course_dep.equals(department)){
						size = comp.getSeatDetails(sem,courseId);
						if (size==0){
							System.out.println("No seat available in the course");
							for_writer[0]=("Failure : No seat available in the course");
							return for_writer;
						}
						else{
							comp.setSemDetails(courseId, sem, size-1);
							System.out.println(comp.getSemDetails());
							for_writer[2]= "Course size reduced by 1 "+comp.getSemDetails();
						}
					}
					else{
						if(userData.get(stuID)<2){
						enroll_course_other_dep =comp.sendMessage(coursePort,sem,courseId);
						if(enroll_course_other_dep.contains("Failure")){
							System.out.println("No seat available in the course");
							for_writer[0]=("Failure : No seat available in the course");
							return for_writer;
						}
						for_writer[2]= "Course size reduced by 1 ";
						userData.put(stuID, userData.get(stuID)+1);
					}
						else{
							System.out.println("Failure : Can enroll in max 2 courses from others department");
							for_writer[0]=("Failure : Can enroll in max 2 courses from others department");
							return for_writer;
						}
					}
					stu.get(stuID).put(sem,str_ary);
					for_writer[0]= "SUCCESS";
					for_writer[3]=stu.get(stuID).toString()+" for student "+stuID;
				}
			}
			else{
				if(course_dep.equals(department)){
					size = comp.getSeatDetails(sem,courseId);
					if (size==0){
						System.out.println("No seat available in the course");
						for_writer[0]=("Failure : No seat available in the course");
						return for_writer;
					}
					else{
						comp.setSemDetails(courseId, sem, size-1);
						System.out.println(comp.getSemDetails());
						for_writer[2]= "Course size reduced by 1 "+comp.getSemDetails();
					}
				}
				else{
					if(userData.get(stuID)<2){
					enroll_course_other_dep =comp.sendMessage(coursePort,sem,courseId);
					if(enroll_course_other_dep.contains("Failure")){
						System.out.println("No seat available in the course");
						for_writer[0]=("Failure : No seat available in the course");
						return for_writer;
					}
					for_writer[2]= "Course size reduced by 1 ";
					userData.put(stuID, userData.get(stuID)+1);
				}
					else{
						System.out.println("Failure : Can enroll in max 2 courses from others department");
						for_writer[0]=("Failure : Can enroll in max 2 courses from others department");
						return for_writer;
					}
				}
				sem_and_course.put(sem,str_ary);
				stu.put(stuID, sem_and_course);	
				for_writer[0]= "SUCCESS";
				for_writer[3]=stu.get(stuID).toString()+" for student "+stuID;
				}
			System.out.println(stu);
			System.out.println(userData);
			return for_writer;
		case "SOEN":
			SOEN_server soen = new SOEN_server();
			if(stu.containsKey(stuID)){
				if(stu.get(stuID).containsKey(sem)){
					ArrayList<String> stu_course = stu.get(stuID).get(sem);
					if(stu.get(stuID).get(sem).contains(courseId)==true){
						System.out.println("Failure : Student "+stuID+" ALREADY enrolled in : "+sem+" "+courseId);
						for_writer[0]="Failure : Student "+stuID+" ALREADY enrolled in : "+sem+" "+courseId;
						return for_writer;
					}
					else if(stu_course.size()>=3){
						System.out.println("Failure : Student "+stuID+ " has already reached max limit for sem "+sem +" "+stu.get(stuID).get(sem));
						for_writer[0]="Failure : Student "+stuID+ " has already reached max limit for sem "+sem +" "+stu.get(stuID).get(sem);
						return for_writer;
					}
					else{
						if(course_dep.equals(department)){
							size = soen.getSeatDetails(sem,courseId);
							if (size==0){
								System.out.println("Failure : No seat available in the course");
								for_writer[0]=("Failure : No seat available in the course");
								return for_writer;
							}
							else{
								soen.setSemDetails(courseId, sem, size-1);
								System.out.println(soen.getSemDetails());
								for_writer[2]= "Course size reduced by 1 ";
							}
						}
						else{
							if(userData.get(stuID)<2){
							enroll_course_other_dep =SOEN_server.sendMessage(coursePort,sem,courseId);
							if(enroll_course_other_dep.contains("Failure")){
								System.out.println("No seat available in the course");
								for_writer[0]=("Failure : No seat available in the course");
								return for_writer;
							}
							for_writer[2]= "Course size reduced by 1 ";
							userData.put(stuID, userData.get(stuID)+1);
							}
								else{
									System.out.println("Failure : Can enroll in max 2 courses from others department");
									for_writer[0]=("Failure : Can enroll in max 2 courses from others department");
									return for_writer;
								}
						}
						stu.get(stuID).get(sem).add(courseId);
						for_writer[0]= "SUCCESS";
						for_writer[3]= stu.toString()+" for student "+stuID;
					}
				}
				else{
					if(course_dep.equals(department)){
						size = soen.getSeatDetails(sem,courseId);
						if (size==0){
							System.out.println("No seat available in the course");
							for_writer[0]=("Failure : No seat available in the course");
							return for_writer;
						}
						else{
							soen.setSemDetails(courseId, sem, size-1);
							System.out.println(soen.getSemDetails());
							for_writer[2]= "Course size reduced by 1 "+soen.getSemDetails();
						}
					}
					else{
						if(userData.get(stuID)<2){
						enroll_course_other_dep =SOEN_server.sendMessage(coursePort,sem,courseId);
						if(enroll_course_other_dep.contains("Failure")){
							System.out.println("No seat available in the course");
							for_writer[0]=("Failure : No seat available in the course");
							return for_writer;
						}
						for_writer[2]= "Course size reduced by 1 ";
						userData.put(stuID, userData.get(stuID)+1);
						}
							else{
								System.out.println("Failure : Can enroll in max 2 courses from others department");
								for_writer[0]=("Failure : Can enroll in max 2 courses from others department");
								return for_writer;
							}
					}
					stu.get(stuID).put(sem,str_ary);
					for_writer[0]= "SUCCESS";
					for_writer[3]=stu.get(stuID).toString()+" for student "+stuID;
				}
			}
			else{
				if(course_dep.equals(department)){
					size = soen.getSeatDetails(sem,courseId);
					if (size==0){
						System.out.println("No seat available in the course");
						for_writer[0]=("Failure : No seat available in the course");
						return for_writer;
					}
					else{
						soen.setSemDetails(courseId, sem, size-1);
						System.out.println(soen.getSemDetails());
						for_writer[2]= "Course size reduced by 1 "+soen.getSemDetails();
					}
				}
				else{
					if(userData.get(stuID)<2){
					enroll_course_other_dep =SOEN_server.sendMessage(coursePort,sem,courseId);
					if(enroll_course_other_dep.contains("Failure")){
						System.out.println("No seat available in the course");
						for_writer[0]=("Failure : No seat available in the course");
						return for_writer;
					}
					for_writer[2]= "Course size reduced by 1 ";
					userData.put(stuID, userData.get(stuID)+1);
					}
						else{
							System.out.println("Failure : Can enroll in max 2 courses from others department");
							for_writer[0]=("Failure : Can enroll in max 2 courses from others department");
							return for_writer;
						}
				}
				sem_and_course.put(sem, str_ary);
				stu.put(stuID, sem_and_course);	
				for_writer[0]= "SUCCESS";
				for_writer[3]=stu.get(stuID).toString()+" for student "+stuID;
				}
			System.out.println(stu);
			return for_writer;
		case "INSE":
			INSE_server inse = new INSE_server();
			if(stu.containsKey(stuID)){
				if(stu.get(stuID).containsKey(sem)){
					ArrayList<String> stu_course = stu.get(stuID).get(sem);
					if(stu.get(stuID).get(sem).contains(courseId)==true){
						System.out.println("Failure : Student "+stuID+" ALREADY enrolled in : "+sem+" "+courseId);
						for_writer[0]="Failure : Student "+stuID+" ALREADY enrolled in : "+sem+" "+courseId;
						return for_writer;
					}
					else if(stu_course.size()>=3){
						System.out.println("Failure : Student "+stuID+ " has already reached max limit for sem "+sem +" "+stu.get(stuID).get(sem));
						for_writer[0]="Failure : Student "+stuID+ " has already reached max limit for sem "+sem +" "+stu.get(stuID).get(sem);
						return for_writer;
					}
					else{
						if(course_dep.equals(department)){
							size = inse.getSeatDetails(sem,courseId);
							if (size==0){
								System.out.println("Failure : No seat available in the course");
								for_writer[0]=("Failure : No seat available in the course");
								return for_writer;
							}
							else{
								inse.setSemDetails(courseId, sem, size-1);
								System.out.println(inse.getSemDetails());
								for_writer[2]= "Course size reduced by 1 ";
							}
						}
						else{
							if(userData.get(stuID)<2){
							enroll_course_other_dep =INSE_server.sendMessage(coursePort,sem,courseId);
							if(enroll_course_other_dep.contains("Failure")){
								System.out.println("No seat available in the course");
								for_writer[0]=("Failure : No seat available in the course");
								return for_writer;
							}
							for_writer[2]= "Course size reduced by 1 ";
							userData.put(stuID, userData.get(stuID)+1);
							}
								else{
									System.out.println("Failure : Can enroll in max 2 courses from others department");
									for_writer[0]=("Failure : Can enroll in max 2 courses from others department");
									return for_writer;
								}
						}
						stu.get(stuID).get(sem).add(courseId);
						for_writer[0]= "SUCCESS";
						for_writer[3]= stu.toString()+" for student "+stuID;
					}
				}
				else{
					if(course_dep.equals(department)){
						size = inse.getSeatDetails(sem,courseId);
						if (size==0){
							System.out.println("No seat available in the course");
							for_writer[0]=("Failure : No seat available in the course");
							return for_writer;
						}
						else{
							inse.setSemDetails(courseId, sem, size-1);
							System.out.println(inse.getSemDetails());
							for_writer[2]= "Course size reduced by 1 "+inse.getSemDetails();
						}
					}
					else{
						if(userData.get(stuID)<2){
						enroll_course_other_dep =INSE_server.sendMessage(coursePort,sem,courseId);
						if(enroll_course_other_dep.contains("Failure")){
							System.out.println("No seat available in the course");
							for_writer[0]=("Failure : No seat available in the course");
							return for_writer;
						}
						for_writer[2]= "Course size reduced by 1 ";
						userData.put(stuID, userData.get(stuID)+1);
						}
							else{
								System.out.println("Failure : Can enroll in max 2 courses from others department");
								for_writer[0]=("Failure : Can enroll in max 2 courses from others department");
								return for_writer;
							}
					}
					stu.get(stuID).put(sem,str_ary);
					for_writer[0]= "SUCCESS";
					for_writer[3]=stu.get(stuID).toString()+" for student "+stuID;
				}
			}
			else{
				if(course_dep.equals(department)){
					size = inse.getSeatDetails(sem,courseId);
					if (size==0){
						System.out.println("No seat available in the course");
						for_writer[0]=("Failure : No seat available in the course");
						return for_writer;
					}
					else{
						inse.setSemDetails(courseId, sem, size-1);
						System.out.println(inse.getSemDetails());
						for_writer[2]= "Course size reduced by 1 "+inse.getSemDetails();
					}
				}
				else{
					if(userData.get(stuID)<2){
					enroll_course_other_dep =INSE_server.sendMessage(coursePort,sem,courseId);
					if(enroll_course_other_dep.contains("Failure")){
						System.out.println("No seat available in the course");
						for_writer[0]=("Failure : No seat available in the course");
						return for_writer;
					}
					for_writer[2]= "Course size reduced by 1 ";
					userData.put(stuID, userData.get(stuID)+1);
					}
						else{
							System.out.println("Failure : Can enroll in max 2 courses from others department");
							for_writer[0]=("Failure : Can enroll in max 2 courses from others department");
							return for_writer;
						}
				}
				sem_and_course.put(sem, str_ary);
				stu.put(stuID, sem_and_course);	
				for_writer[0]= "SUCCESS";
				for_writer[3]=stu.get(stuID).toString()+" for student "+stuID;
				}
			System.out.println(stu);
			return for_writer;
		default:
			System.out.println("*******INVALID DEPARTMENT*******");
			return null;
		}
			}
		catch(Exception ex){
			System.out.println("Failure : Course not present in given term");
			for_writer[0]= "Failure : Course not present in given term";
			return for_writer;
		}
	}
	@Override
	public String getClassSchedule(String stuID) throws RemoteException {
		try{
		String data= stu.get(stuID).toString();
		return data;
		}
		catch(Exception ex){
			System.out.println("Exception caught : "+ex);
			return "No data for user";
		}
	}
	@Override
	public String dropCourseFromStu(String stuID, String courseID,String sem){
		String res;
		System.out.println("****Dropping course " +sem+" "+courseID+" for "+stuID+ "from stu****");
		if(stu.get(stuID).get(sem).contains(courseID)){
			res="BEFORE : "+stu.get(stuID);
			stu.get(stuID).get(sem).remove(courseID);
			res=res+"AFTER : "+stu.get(stuID);
			System.out.println(stu);
			return res;
		}
		else{
			System.out.println("Failure : Student "+stuID+ "not enrolled in "+sem+" "+courseID);
			res="Failure : Student "+stuID+ "not enrolled in "+sem+" "+courseID;
			return res;
		}
	}
	
	
	public String[] dropCourse(String stuID, String courseID,String sem) throws Exception {
		// TODO Auto-generated method stub
		int size=0;
		String[] for_writer = new String[5];
		String department = courseID.substring(0,4);
		System.out.println("Invoke department : "+department);
		System.out.println("****Dropping course " +sem+" "+courseID+" for "+stuID+"****");
		for_writer[1]= "****Dropping course " +sem+" "+courseID+" for "+stuID+"****";
		
			switch (department) {
			case "COMP":
				System.out.println("Inside comp");
				COMP_server comp = new COMP_server();
				size = comp.getSeatDetails(sem,courseID);
				comp.setSemDetails(courseID, sem, size+1);
				System.out.println(comp.getSemDetails());
				for_writer[4]="Course size increased by size 1 ";
				for_writer[0]="SUCCESS";
				return for_writer;
			case "SOEN":
				System.out.println("Inside comp");
				SOEN_server soen = new SOEN_server();
				size = soen.getSeatDetails(sem,courseID);
				soen.setSemDetails(courseID, sem, size+1);
				System.out.println(soen.getSemDetails());
				for_writer[4]="Course size increased by size 1 ";
				for_writer[0]="SUCCESS";
				return for_writer;
			case "INSE":
				System.out.println("Inside comp");
				INSE_server inse = new INSE_server();
				size = inse.getSeatDetails(sem,courseID);
				inse.setSemDetails(courseID, sem, size+1);
				System.out.println(inse.getSemDetails());
				for_writer[4]="Course size increased by size 1 ";
				for_writer[0]="SUCCESS";
				return for_writer;
			default:
				System.out.println("*******INVALID DEPARTMENT*******");
				return null;
				}
			}
		
//		try{
			
		
		
	}
//	}
//	catch(Exception ex){
//		System.out.println("Failure : Student not enrolled" + ex);
//		for_writer[0]= "Failure : Student not enrolled" + ex;
//		return for_writer;
//	}
