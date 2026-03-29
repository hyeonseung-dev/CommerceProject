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
    //속성 접근 기능 - 속성 꺼내오기
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getGrade(){
        return grade;
    }

    //속성 접근 기능 - 속성 수정하기
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setGrade(String grade){
        this.grade = grade;
    }
}
