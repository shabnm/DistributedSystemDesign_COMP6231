package dcrs_interfaces;

import java.awt.List;
import java.io.Serializable;
import java.io.Writer;
import java.rmi.*;
import java.util.ArrayList;
import java.util.HashMap;

public interface AddInterface extends Remote{
	/*************** method for advisor ************************/
	public String[] addCourse(String courseId,String sem,int size) throws Exception;
	public String[] removeCourse(String courseId, String sem) throws Exception;
	public String listCourseAvailability(int first_dep_port,int second_dep_port,String sem,String userID) throws Exception;
	
	
	/*************** method for student ************************/
	public String[] enrollCourse(String stuID,String courseId,int coursePort,String sem) throws Exception;
	public String getClassSchedule(String stuID) throws Exception;
	public String[] dropCourse(String stuID,String courseId,String sem) throws Exception;
	public String dropCourseFromStu(String stuID, String courseID,String sem) throws Exception;
}
