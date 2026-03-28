package Commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    //속성
    List<Product> products;
    Scanner scanner = new Scanner(System.in);

    //생성자

    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    //기능
    public void start(){
        System.out.printf("%35s\n","[ 실시간 커머스 플랫폼 - 전자제품 ]");
        for(int i = 0 ; i < products.size();i++){
            System.out.printf(
                    "%-3s %-15s | %,10d원 | %-30s\n",
                    (i + 1) + ".",
                    products.get(i).getName(),
                    products.get(i).getPrice(),
                    products.get(i).getExplain());
        }

        System.out.printf("%-3s %-14s | %-20s\n",
                "0.",
                "종료",
                "프로그램 종료");
        int input = scanner.nextInt();
    }
}
