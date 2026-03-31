package Commerce;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    //속성
    private List<Category> categories;
    Scanner scanner = new Scanner(System.in);
    private int selectCategory;
    private int findCategoryIndex;
    private int selectProduct;
    private int selectProductIndex;
    Cart cart = new Cart();
    int cartStart = 0;

    //생성자
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    //기능
    // 커머스 플랫폼 메인 실행
    public void start() {
        while (true) {
            if(cartStart == 0) {
                // 메인 플랫폼 실행, 장바구니 미 추가시
                int mainResult = printMain1();
                if (mainResult == 0) {
                    return;
                } else if (mainResult == -1) {
                    continue;
                }
            }else {
                // 메인 플랫폼 실행, 장바구니 추가시
                int mainResult = printMain2();
                if (mainResult == 0) {
                    return;
                } else if (mainResult == -1) {
                    continue;
                }
            }
            // 선택한 상품 출력
            try {
                selectProduct = scanner.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                scanner.nextLine(); // 버퍼 초기화
                continue;
            }

            // 선택한 메뉴가 0이면 메인메뉴로 이동, 유효하지 않은 상품 번호 입력 시 메인메뉴로 이동, 정상이면 출력
            if(selectProduct == 0){
                continue;
            }else if(printSelectProduct(selectProduct) == -1){
                continue;
            }


            // 선택된 상품 객체화
            Product selectedProduct = categories.get(findCategoryIndex).getProducts().get(selectProductIndex);

            //장바구니 추가 여부 묻기
            System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
            System.out.printf("%-3s %10s\n",
                    "1. 확인",
                    "2. 취소");
            int input = scanner.nextInt();
            scanner.nextLine(); //버퍼 초기화

            // 장바구니 추가 메서드 실행
            int cartInput = cart.addProduct(input,selectedProduct);

            // 장바구니에 상품을 담았다면 cartStart 변수에 수를 증가시켜 장바구니 메뉴가 실행될 수 있게 함
            if(cartInput == 1){
                cartStart++;
            }
        }
    }

    // 장바구니 미 실행 시 메인 출력문 메서드 -> -1 : 컨티뉴 0: 종료 1 : 계속
    public int printMain1(){
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        System.out.printf("%-3s %-14s\n%-3s %-14s\n%-3s %-14s\n%-3s %-4s | %-10s\n",
                "1.",
                "전자제품",
                "2.",
                "의류",
                "3.",
                "식품",
                "0.",
                "종료",
                "프로그램 종료");

        // 사용자 카테고리 선택 입력
        try {
            selectCategory = scanner.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            scanner.nextLine(); // 버퍼 초기화
            return -1;
        }

        // 사용자 카테고리 입력에 따른 카테고리 출력
        switch (selectCategory) {
            case 0:
                return 0;
            case 1:
                // 선택한 카테고리 출력
                printCategory("전자제품");
                break;
            case 2:
                // 선택한 카테고리 출력
                printCategory("의류");
                break;
            case 3:
                // 선택한 카테고리 출력
                printCategory("식품");
                break;
            default:
                // 등록된 카테고리 외 선택 시 예외처리
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                return -1;
        }
        return 1;
    }

    // 장바구니 실행 시 메인 출력문 메서드 -> -1 : 컨티뉴 0: 종료 1 : 계속
    public int printMain2(){
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        System.out.printf("%-3s %-14s\n%-3s %-14s\n%-3s %-14s\n%s\n%-3s %-14s\n%-3s %-14s\n%-3s %-4s | %-10s\n",
                "1.",
                "전자제품",
                "2.",
                "의류",
                "3.",
                "식품",
                "[ 주문 관리 ]",
                "4.",
                "장바구니 확인",
                "5.",
                "주문 취소",
                "0.",
                "종료",
                "프로그램 종료");

        // 사용자 카테고리 선택 입력
        try {
            selectCategory = scanner.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            scanner.nextLine(); // 버퍼 초기화
            return -1;
        }

        // 사용자 카테고리 입력에 따른 카테고리 출력
        switch (selectCategory) {
            case 0:
                return 0;
            case 1:
                // 선택한 카테고리 출력
                printCategory("전자제품");
                break;
            case 2:
                // 선택한 카테고리 출력
                printCategory("의류");
                break;
            case 3:
                // 선택한 카테고리 출력
                printCategory("식품");
                break;
            case 4:
                // 장바구니 확인 기능 실행
                cart.printCart();
                return -1;
            case 5:
                // 주문 취소 기능
                cart.cancelCart();
                cartStart =0;
                return -1;
            default:
                // 등록된 카테고리 외 선택 시 예외처리
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                return -1;
        }
        return 1;
    }

    // 선택한 카테고리 상품들 출력 메서드
    public void printCategory(String category){
        System.out.println("[ "+category+" 카테고리 ]");
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCategory().equals(category)) {
                for (int j = 0; j < categories.get(i).getProducts().size(); j++) {
                    System.out.printf(
                            "%-3s %-15s | %,10d원 | %-25s | %s개\n",
                            (j + 1) + ".",
                            categories.get(i).getProducts().get(j).getName(),
                            categories.get(i).getProducts().get(j).getPrice(),
                            categories.get(i).getProducts().get(j).getExplain(),
                            categories.get(i).getProducts().get(j).getStock());
                }
                findCategoryIndex = i;
            }
        }

        System.out.printf("%-3s %-20s\n",
                "0.",
                "뒤로가기");
    }

    // 선택한 상품 출력 : 선택한 카테고리 상품들 출력 메서드에서 선택된 카테고리 인덱스를 findCategoryIndex 변수에 저장하여 현 메서드에서 호출해 해당 카테고리의 상품을 출력
    public int printSelectProduct(int selectProduct){
        selectProductIndex = selectProduct -1;  // 리스트 인덱스 맞춰주기
        if(0 <= selectProductIndex && selectProductIndex < categories.get(findCategoryIndex).getProducts().size()){
            System.out.printf("%s %,d원 %s\n ",
                    "선택한 상품 : "+categories.get(findCategoryIndex).getProducts().get(selectProductIndex).getName()+" | ",
                    categories.get(findCategoryIndex).getProducts().get(selectProductIndex).getPrice()," | "+
                            categories.get(findCategoryIndex).getProducts().get(selectProductIndex).getExplain());
        }else {
            System.out.println("유효하지 않은 상품 번호 입력입니다.");
            return -1;
        }
        return 1;
    }
}