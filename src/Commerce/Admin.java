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


    //생성자

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

}
