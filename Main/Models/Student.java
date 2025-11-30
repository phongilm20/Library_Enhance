package Library_Enhance.Main.Models;

public class Student {
    private String name;
    private String uid;
    private String mail;
    private String phone;
    

    public Student(String name, String uid, String mail, String phone){
        this.name = name;
        this.uid = uid;
        this.mail = mail;
        this.phone = phone;
    }

    // Constructor
    @Override
    public String toString(){
        String output = "Name: " + this.name +"\nUID: "+this.uid +"\nMail: " + this.mail + "\nPhone: " + phone;
        return output;
    }
    public String getUid(){
        return this.uid;
    }
    public String getName(){
        return this.name;
    }
}
