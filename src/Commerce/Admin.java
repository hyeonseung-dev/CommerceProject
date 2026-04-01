package Commerce;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Admin {
    //속성
    private String password = "admin";
    private Scanner scanner = new Scanner(System.in);
    private String inputPassword;
    private int inputMenu;
    private int chance = 3;
    private List<Category> categories;
    private List<Product> electronics;
    private List<Product> clothes;
    private List<Product> foods;
    private Cart cart;


    //생성자
    public Admin() {
    }

    public Admin(List<Product> electronics, List<Product> clothes, List<Product> foods, List<Category> categories, Cart cart) {
        this.electronics = electronics;
        this.clothes = clothes;
        this.foods = foods;
        this.categories = categories;
        this.cart = cart;
    }

    //기능
    public void login() {
        while (chance > 0) {
            System.out.println("관리자 비밀번호를 입력해 주세요");
            try {
                inputPassword = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                scanner.nextLine(); // 버퍼 초기화
            }

            if (inputPassword.equals(password)) {
                System.out.println("로그인 성공");

                //관리자 모드 실행
                adminMode();
                return;
            } else {
                chance -= 1;
                if (chance != 0) {
                    System.out.println("비밀번호가 틀렸습니다. 남은 기회" + chance + " 번");
                } else {
                    System.out.println("비밀번호가 3회 틀렸습니다. 메인메뉴로 돌아갑니다.");
                }
            }
        }
    }

    public void adminMode() {
        while (true) {
            System.out.println("[ 관리자 모드 ]");
            System.out.printf("%-3s %-14s\n%-3s %-14s\n%-3s %-14s\n%s\n%-3s %-14s\n%-3s %-14s\n",
                    "1.",
                    "상품 추가",
                    "2.",
                    "상품 수정",
                    "3.",
                    "상품 삭제",
                    "[ 주문 관리 ]",
                    "4.",
                    "전체 상품 현황",
                    "0.",
                    "메인으로 돌아가기");

            try {
                inputMenu = scanner.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                scanner.nextLine(); // 버퍼 초기화
            }

            scanner.nextLine(); // 버퍼 초기화

            // 메뉴 별 기능
            switch (inputMenu) {
                case 0:
                    return;
                case 1:
                    addProduct();
                    break;
                case 2:
                    setProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    getAllProduct();
                    break;
                default:
                    // 등록된 카테고리 외 선택 시 예외처리
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    return;
            }

        }
    }

    // 상품 추가
    public void addProduct() {
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        System.out.printf("%-3s %-14s\n%-3s %-14s\n%-3s %-14s\n",
                "1.",
                "전자제품",
                "2.",
                "의류",
                "3.",
                "식품");

        int inputCategoryInt = scanner.nextInt();
        scanner.nextLine();
        String inputCategoryString;
        if (inputCategoryInt == 1) {
            inputCategoryString = "전자제품";
        } else if (inputCategoryInt == 2) {
            inputCategoryString = "의류";
        } else if (inputCategoryInt == 3) {
            inputCategoryString = "식품";
        } else {
            System.out.println("없는 카테고리입니다. 다시 입력하세요.");
            return;
        }


        System.out.println("[ " + inputCategoryString + " ] 카테고리에 상품 추가");
        System.out.print("상품명을 임력해주세요:");
        String productName = scanner.nextLine();

        System.out.print("가격을 임력해주세요:");
        int productPrice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("상품 설명을 임력해주세요:");
        String productExplain = scanner.nextLine();

        System.out.print("재고를 임력해주세요:");
        int productStock = scanner.nextInt();
        scanner.nextLine();

        // 중복 상품명 검증
        for (Category category : categories) {
            if (category.getCategory().equals(inputCategoryString)) {
                for (int i = 0; i < category.getProducts().size(); i++) {
                    if (category.getProducts().get(i).getName().equals(productName)) {
                        System.out.println("중복된 상품명입니다. 다시 입력하세요.");
                        return;
                    }
                }
            }
        }

        System.out.println(productName + " | " + productPrice + "원 | " + productExplain + " | 재고 :" + productStock + "개");
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        int input = scanner.nextInt();
        scanner.nextLine();
        // 여기부터 진행
        if (input == 1) {
            for (int i = 0; i < categories.size(); i++) {
                if (inputCategoryString.equals(categories.get(i).getCategory())) {
                    categories.get(i).getProducts().add(new Product(productName, productPrice, productExplain, productStock));
                    System.out.println("상품이 추가되었습니다.");
                    return;
                }
            }
        }
    }

    // 상품 수정
    public void setProduct() {
        System.out.println("수정할 상품명을 입력해주세요.");
        String inputProductName = scanner.nextLine();
        Product selectedProduct = null;

        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                if (product.getName().equals(inputProductName)) {
                    System.out.println("현재 상품 정보: " +
                            product.getName() + " | " +
                            product.getExplain() + " | " +
                            product.getPrice() + " | 재고 :" +
                            product.getStock() + "개"
                    );
                    selectedProduct = product;
                    break;
                }
            }
            if (selectedProduct != null) {
                break;
            }
        }

        if (selectedProduct == null) {
            System.out.println("상품이 없습니다.");
            return;
        }


        System.out.println("수정할 항목을 선택해주세요:");
        System.out.printf("%-3s %-14s\n%-3s %-14s\n%-3s %-14s\n",
                "1.",
                "가격",
                "2.",
                "설명",
                "3.",
                "재고수량");
        int inputProductSet = scanner.nextInt();
        scanner.nextLine();  //버퍼 초기화

        switch (inputProductSet) {
            case 1:
                int inputNewPrice;
                System.out.printf("%s %,d원",
                        "현재 가격:",
                        selectedProduct.getPrice());
                int nowPrice = selectedProduct.getPrice();
                System.out.print("새로운 가격을 입력해 주세요:");

                try {
                    inputNewPrice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    scanner.nextLine(); // 버퍼 초기화
                    return;
                }
                scanner.nextLine();

                // 변경 가격 이상 확인
                try {
                    selectedProduct.changePrice(inputNewPrice);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

                System.out.printf("%s의 가격이 %,d원 -> %,d원으로 수정되었습니다.",
                        selectedProduct.getName(),
                        nowPrice,
                        inputNewPrice
                );

                break;
            case 2:
                System.out.println("설명은 바꾸지 마세요 그냥");
                break;
            case 3:
                int inputNewStock;
                System.out.printf("%s %,d개",
                        "현재 수량:",
                        selectedProduct.getStock());
                int nowStock = selectedProduct.getStock();
                System.out.print("새로운 수량을 입력해 주세요:");

                try {
                    inputNewStock = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    scanner.nextLine(); // 버퍼 초기화
                    return;
                }
                scanner.nextLine();

                // 변경 가격 이상 확인
                try {
                    selectedProduct.changeStock(inputNewStock);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

                System.out.printf("%s의 수량이 %,d개 -> %,d개로 수정되었습니다.",
                        selectedProduct.getName(),
                        nowStock,
                        inputNewStock
                );
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                return;
        }

    }

    // 상품 삭제 기능
    public void removeProduct() {
        System.out.println("삭제할 상품명을 입력해주세요.");
        String inputProductName = scanner.nextLine();
        Product selectedProduct = null;
        Category selectedCategory = null;

        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                if (product.getName().equals(inputProductName)) {
                    System.out.println("현재 상품 정보: " +
                            product.getName() + " | " +
                            product.getExplain() + " | " +
                            product.getPrice() + " | 재고 :" +
                            product.getStock() + "개"
                    );
                    selectedProduct = product;
                    selectedCategory = category;
                    break;
                }
            }
            if (selectedProduct != null) {
                break;
            }
        }

        if (selectedProduct == null) {
            System.out.println("상품이 없습니다.");
            return;
        }


        System.out.println("정말로 삭제하시겠습니까?");
        System.out.println("1. 예     2. 아니오");
        int inputAnswer = scanner.nextInt();
        scanner.nextLine();

        if (inputAnswer != 1) {
            System.out.println("삭제를 취소합니다.");
            return;
        } else {
            cart.removeProductFromCart(selectedProduct);
            selectedCategory.getProducts().remove(selectedProduct);
            System.out.println("해당 상품이 삭제 되었습니다.");
        }
    }

    // 전체 상품 조회
    public void getAllProduct() {
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            System.out.println("[ " + category.getCategory() + " ]");

            for (int j = 0; j < category.getProducts().size(); j++) {
                Product product = category.getProducts().get(j);

                System.out.printf(
                        "%-3s %-15s | %,10d원 | %-25s | %d개\n",
                        (j + 1) + ".",
                        product.getName(),
                        product.getPrice(),
                        product.getExplain(),
                        product.getStock()
                );
            }
        }
    }
}






