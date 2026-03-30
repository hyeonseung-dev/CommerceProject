package Commerce;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Cart {
    //속성
    private String name;
    private int price;
    private String explain;
    private int quantity;
    private List<Cart> cartList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    private int input;
    private Product product;


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
    public Product getProduct() {
        return product;
    }

    //수량 증가 시키기
    public void addQuantity(){
        this.quantity++;
    }
    //장바구니 추가하기
    public int addCart(int input, String name, int price, String explain, int stock){
        if(stock < 1){
            System.out.println("해당 상품은 재고가 없습니다.");
            return 0;
        }

        if(input != 1){
            System.out.println("취소되었습니다.");
            return 0;
        }

        for(Cart cart : cartList) {
            // 상품이 기존 장바구니에 있으면 수량 증가
            if (cart.getName().equals(name)) {
                if (cart.quantity < stock) {
                    System.out.println(name + "수량이 1 증가 했습니다.");
                    cart.addQuantity();
                    // 장바구니 수량이 상품 재고보다 많으면 경고 메세지
                    return 1;
                } else {
                    System.out.println("해당 상품 재고보다 많이 담을 수 없습니다.");
                    return 0;
                }
            }
        }

        // 장바구니에 없는 상품이면 새로 추가
        cartList.add(new Cart(name, price, explain, 1));
        System.out.println(name + "가 장바구니에 추가되었습니다.");
        return 1;
    }

    // 장바구니 확인 기능 -1 반환 시 종료 그 외는 장바구니 수량 반환
    public void printCart(){
        int totalPrice = 0;
        if(!cartList.isEmpty()) {
            System.out.println("아래와 같이 주문 하시겠습니까?");
            System.out.println("[ 장바구니 내역 ]");
            for (int i = 0; i < cartList.size(); i++) {
                System.out.printf("%s번 %s | %,d원 | %s | 수량 : %d개\n",
                        i + 1, cartList.get(i).getName(), cartList.get(i).getPrice(), cartList.get(i).getExplain(), cartList.get(i).getQuantity());
                totalPrice += (cartList.get(i).getPrice() * cartList.get(i).getQuantity());
            }
            System.out.println("[ 총 주문 금액 ]");
            System.out.printf("%,d원\n", totalPrice);
            // 장바구니가 비어있을 시 출력
        }else {
            System.out.println("장바구니가 비어있습니다.");
        }

        // 주문 확정 또는 메인으로 돌아가기 받기
        System.out.printf("%-3s %-10s %-3s %s\n", "1.","주문 확정","2.","메인으로 돌아가기");
        try {
            input = scanner.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            scanner.nextLine(); // 버퍼 초기화
            return;
        }

        // 주문확정 입력 시 재고 삭감 및 출
        if(input == 1){
            for(int i = 0; i < cartList.size(); i++) {
                Cart cart = cartList.get(i); // 이게 핵심이다. 이해해야한다.
                Product product1 = cart.getProduct();
                int quantity = cart.getQuantity();

                product1.decreaseStock(quantity); // 실제 상품 재고 삭감
                System.out.printf("%s %,d원\n", "주문이 완료되었습니다! 총 금액 :", totalPrice);
                System.out.println(product1.getName() + " 재고가 "
                        + (product1.getStock() + quantity) + "개 -> "
                        + product1.getStock() + "개로 업데이트 되었습니다.");
            }
            cartList.clear();

        }else if(input == 2){
            System.out.println("메인으로 돌아갑니다.");
        }else{
            System.out.println("잘못된 입력입니다. 다시 입력하세요");
        }
    }

    // 장바구니 주문 취소 기능
    public void cancelCart(){
        cartList.clear();
        System.out.println("주문을 취소합니다.");
    }


}
