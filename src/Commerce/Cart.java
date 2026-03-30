package Commerce;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    //속성
    private String name;
    private int price;
    private String explain;
    private int quantity;
    private List<Cart> cartList = new ArrayList<>();

    //생성자
    public Cart(){}
    public Cart(String name, int price, String explain,int quantity){
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

    //수량 증가 시키기
    public void addQuantity(){
        this.quantity++;
    }
    //장바구니 추가하기
    public void addCart(int input, String name, int price, String explain, int stock){
        if(stock < 1){
            System.out.println("해당 상품은 재고가 없습니다.");
            return;
        }

        if(input != 1){
            System.out.println("취소되었습니다.");
            return;
        }

        for(Cart cart : cartList) {
            // 상품이 기존 장바구니에 있으면 수량 증가
            if (cart.getName().equals(name)) {
                if (cart.quantity < stock) {
                    System.out.println(name + "수량이 1 증가 했습니다.");
                    cart.addQuantity();
                    // 장바구니 수량이 상품 재고보다 많으면 경고 메세지
                } else {
                    System.out.println("해당 상품 재고보다 많이 담을 수 없습니다.");
                }
                return;
            }
        }

        // 장바구니에 없는 상품이면 새로 추가
        cartList.add(new Cart(name, price, explain, 1));
        System.out.println(name + "가 장바구니에 추가되었습니다.");

    }




}
