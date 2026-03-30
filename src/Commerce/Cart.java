package Commerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    //속성
    private String name;
    private int price;
    private String explain;
    private int quantity = 0;
    private List<Cart> cartList = new ArrayList<>();

    //생성자
    public Cart(){}
    public Cart(String name, int price, String explain, int quantity){
        this.name = name;
        this.price = price;
        this.explain = explain;
        this.quantity = quantity;
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
    public int getQuantity(){
        return quantity;
    }

    //장바구니 추가하기
    public void addCart(int input, String name, int price, String explain){
        if(input == 1){
            System.out.println(name+"가 장바구니에 추가 되었습니다.");
            cartList.add(new Cart(name, price, explain, ++quantity));
        }else {
            System.out.println("취소되었습니다.");
        }
    }




}
