package Commerce;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Admin {
    //속성
    private String password = "admin";
    Scanner scanner = new Scanner(System.in);
    String inputPassword;
    int inputMenu;
    int chance = 3;
    private List<Category> categories;
    List<Product> electronics;
    List<Product> clothes;
    List<Product> foods;


    //생성자
    public Admin(){}
    public Admin(List<Product> electronics, List<Product> clothes, List<Product> foods, List<Category> categories)
{
        this.electronics = electronics;
        this.clothes = clothes;
        this.foods = foods;
        this.categories = categories;
        }


    //기능
    public void login(){
        while(chance > 0) {
            System.out.println("관리자 비밀번호를 입력해 주세요");
            try {
            inputPassword = scanner.nextLine();}
            catch (InputMismatchException e) {
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
                if(chance != 0) {
                    System.out.println("비밀번호가 틀렸습니다. 남은 기회" + chance + " 번");
                }else{
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

            // 메뉴 별 기능
            switch (inputMenu) {
                case 0:
                    return;
                case 1:
                    addProduct();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    break;
                default:
                    // 등록된 카테고리 외 선택 시 예외처리
                    System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                    return;
            }

        }
    }

    // 상품 추가
    public void addProduct(){
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
        if(inputCategoryInt == 1){
            inputCategoryString = "전자제품";
        }else if(inputCategoryInt == 2){
            inputCategoryString = "의류";
        }else if(inputCategoryInt == 3){
            inputCategoryString = "식품";
        }else {
            System.out.println("없는 카테고리입니다. 다시 입력하세요.");
            return;
        }


        System.out.println("[ "+ inputCategoryString +" ] 카테고리에 상품 추가");
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

        System.out.println(productName+" | "+productPrice+"원 | "+productExplain+" | 재고 :"+productStock+"개");
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        int input = scanner.nextInt();
        scanner.nextLine();
        // 여기부터 진행
        if(input == 1){
            for(int i = 0 ; i < categories.size(); i++){
                if(inputCategoryString.equals(categories.get(i).getCategory())){
                    categories.get(i).getProducts().add(new Product(productName, productPrice, productExplain, productStock));
                    System.out.println("상품이 추가되었습니다.");
                    return;
                }
            }
        }

    }

}
