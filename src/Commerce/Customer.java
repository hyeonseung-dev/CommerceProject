package Commerce;

public class Customer {
    //속성
    private String name;
    private String email;
    private String grade;

    //생성자
    public Customer(String name, String email, String grade){
        this.name = name;
        this.email = email;
        this.grade = grade;
    }
    //기능
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getGrade(){
        return grade;
    }
}
