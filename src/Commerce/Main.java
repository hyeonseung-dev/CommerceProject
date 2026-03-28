package Commerce;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Galaxy S25 ",1200000,"최신 안드로이드 스마트폰", 10));
        products.add(new Product("MacBook Pro ",1350000,"Apple의 최신 스마트폰", 10));
        products.add(new Product("Galaxy S25 ",2400000,"M3 칩셋이 탑재된 노트북", 10));
        products.add(new Product("AirPods Pro ",350000,"노이즈 캔슬링 무선 이어폰", 10));

        CommerceSystem system = new CommerceSystem(products);
        system.start();
    }
}
