package Commerce;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Cart {
    // 속성
    private List<CartItem> cartList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private int input;

    // 장바구니 추가하기 0 : 취소 / 1 : 추가
    public int addProduct(int input, Product product) {
        if (product.getStock() < 1) {
            System.out.println("해당 상품은 재고가 없습니다.");
            return 0;
        }
        // 주문 취소 시 취소
        if (input != 1) {
            System.out.println("취소되었습니다.");
            return 0;
        }

        for (CartItem cartItem : cartList) {
            // 상품이 기존 장바구니에 있으면 수량 증가
            if (cartItem.getProduct().getName().equals(product.getName())) {
                if (cartItem.getQuantity() < product.getStock()) {
                    System.out.println(product.getName() + " 수량이 1 증가했습니다.");
                    cartItem.addQuantity();
                    return 1;
                } else {
                    System.out.println("해당 상품 재고보다 많이 담을 수 없습니다.");
                    return 0;
                }
            }
        }

        // 장바구니에 없는 상품이면 새로 추가
        cartList.add(new CartItem(product, 1));
        System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
        return 1;
    }

    // 장바구니 확인 기능
    public void printCart() {
        int totalPrice = 0;

        if (!cartList.isEmpty()) {
            System.out.println("아래와 같이 주문 하시겠습니까?");
            System.out.println("[ 장바구니 내역 ]");

            for (int i = 0; i < cartList.size(); i++) {
                CartItem cartItem = cartList.get(i);
                Product product = cartItem.getProduct();

                System.out.printf("%d번 %s | %,d원 | %s | 수량 : %d개\n",
                        i + 1,
                        product.getName(),
                        product.getPrice(),
                        product.getExplain(),
                        cartItem.getQuantity());

                totalPrice += cartItem.getTotalPrice();
            }

            System.out.println("[ 총 주문 금액 ]");
            System.out.printf("%,d원\n", totalPrice);
        } else {
            System.out.println("장바구니가 비어있습니다.");
            return;
        }

        // 주문 확정 또는 메인으로 돌아가기 받기
        System.out.printf("%-3s %-10s %-3s %s\n", "1.", "주문 확정", "2.", "메인으로 돌아가기");

        try {
            input = scanner.nextInt();
            scanner.nextLine(); // 버퍼 초기화
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            scanner.nextLine(); // 버퍼 초기화
            return;
        }

        // 주문 확정 입력 시 재고 삭감
        if (input == 1) {
            for (int i = 0; i < cartList.size(); i++) {
                CartItem cartItem = cartList.get(i);
                Product product = cartItem.getProduct();
                int quantity = cartItem.getQuantity();
                int beforeStock = product.getStock();

                product.decreaseStock(quantity);

                System.out.println(product.getName() + " 재고가 "
                        + beforeStock + "개 -> "
                        + product.getStock() + "개로 업데이트 되었습니다.");
            }

            System.out.printf("주문이 완료되었습니다! 총 금액 : %,d원\n", totalPrice);
            cartList.clear();

        } else if (input == 2) {
            System.out.println("메인으로 돌아갑니다.");
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
        }
    }

    // 장바구니 주문 취소 기능
    public void cancelCart() {
        cartList.clear();
        System.out.println("주문을 취소합니다.");
    }

    // 장바구니가 비어있는지 확인
    public boolean isEmpty() {
        return cartList.isEmpty();
    }
}