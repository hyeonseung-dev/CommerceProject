package Commerce;

public class Product {
    //속성
    private String name;
    private int price;
    private String explain;
    private int stock;

    //생성자
    public Product(String name, int price, String explain, int stock){
        this.name = name;
        this.price = price;
        this.explain = explain;
        this.stock = stock;
    }
    //기능
    //속성 접근 기능 - 속성 꺼내오기
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public String getExplain(){
        return explain;
    }
    public int getStock(){
        return stock;
    }

    //속성 접근 기능 - 속성 수정하기
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setExplain(String explain){
        this.explain = explain;
    }
    public void setStock(int stock){
        this.stock = stock;
    }

}
