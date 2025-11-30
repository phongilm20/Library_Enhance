package Library_Enhance.Main.Services;

import java.util.ArrayList;

import Library_Enhance.Main.Models.Student;

public class StudentServices {
    ArrayList <Student> studentsList = new ArrayList<>();


    // Add new student to the list
    public void registerStudent(Student student){
        if(!studentsList.contains(student)){
            studentsList.add(student);
        }
    }

    // Check for if that uid is already exist
    public boolean isUidExist(String uid){
        for (Student student: studentsList){
            if(student.getUid().equals(uid)){
                return true;
            }
        }
        return false;
    }

    // Find Student by UID
    public Student findStudent(String uid){
        for (Student student: studentsList){
            if(student.getUid().equals(uid)){
                return student;
            }
        }
        return null;
    }
}
