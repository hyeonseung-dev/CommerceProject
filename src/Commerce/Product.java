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

}
