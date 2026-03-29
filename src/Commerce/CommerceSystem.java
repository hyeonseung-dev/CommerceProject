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

    //생성자
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
    }

    //기능
    // 커머스 플랫폼 메인 실행
    public void start() {
        while (true) {
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
                continue;
            }

            // 사용자 카테고리 입력에 따른 카테고리 출력
            switch (selectCategory) {
                case 0:
                    return;
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
                    continue;
            }

            // 선택한 상품 출력
            try {
                selectProduct = scanner.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
                scanner.nextLine(); // 버퍼 초기화
                continue;
            }

            // 0이면 종료
            if(selectProduct == 0){
                continue;
            }

            // 선택한 상품 출력
            printSelectProduct(selectProduct);
        }
    }

    // 선택한 카테고리 상품들 출력 메서드
    public void printCategory(String category){
            System.out.println("[ "+category+" 카테고리 ]");
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getCategory().equals(category)) {
                    for (int j = 0; j < categories.get(i).getProducts().size(); j++) {
                        System.out.printf(
                                "%-3s %-15s | %,10d원 | %-30s\n",
                                (j + 1) + ".",
                                categories.get(i).getProducts().get(j).getName(),
                                categories.get(i).getProducts().get(j).getPrice(),
                                categories.get(i).getProducts().get(j).getExplain());
                    }
                    findCategoryIndex = i;
                }
            }

            System.out.printf("%-3s %-20s\n",
                    "0.",
                    "뒤로가기");
    }

    // 선택한 상품 출력 : 선택한 카테고리 상품들 출력 메서드에서 선택된 카테고리 인덱스를 findCategoryIndex 변수에 저장하여 현 메서드에서 호출해 해당 카테고리의 상품을 출력
    public void printSelectProduct(int selectProduct){
        int selectProductIndex = selectProduct -1;  // 리스트 인덱스 맞춰주기
        if(0 <= selectProductIndex && selectProductIndex < categories.get(findCategoryIndex).getProducts().size()){
            System.out.println("선택한 상품 : "+categories.get(findCategoryIndex).getProducts().get(selectProductIndex).getName()+" | "+
                    categories.get(findCategoryIndex).getProducts().get(selectProductIndex).getPrice()+" | "+
                    categories.get(findCategoryIndex).getProducts().get(selectProductIndex).getExplain());
        }else{
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
        }
    }
}
