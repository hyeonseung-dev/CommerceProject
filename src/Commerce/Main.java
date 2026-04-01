package Commerce;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Category> categories = new ArrayList<>();
        List<Product> electronics = new ArrayList<>();
        List<Product> clothes = new ArrayList<>();
        List<Product> foods = new ArrayList<>();
        Cart cart = new Cart();
        Admin admin = new Admin(electronics, clothes, foods,categories,cart);
        CommerceSystem system = new CommerceSystem(categories,admin,cart);

        // 전자제품 상품 등록
        electronics.add(new Product("iPhone 16", 1350000, "Apple's latest smartphone", 0));
        electronics.add(new Product("MacBook Pro", 2400000, "Laptop powered by the M3 chipset", 10));
        electronics.add(new Product("Galaxy S25", 1200000, "Latest Android smartphone", 10));
        electronics.add(new Product("AirPods Pro", 350000, "Wireless earbuds with noise cancellation", 10));
        categories.add(new Category(electronics, "전자제품"));

        // 의류 상품 등록
        clothes.add(new Product("Nike Hoodie", 89000, "Comfortable fit hoodie", 20));
        clothes.add(new Product("Adidas Pants", 79000, "Sports training pants", 15));
        clothes.add(new Product("Uniqlo T-Shirt", 19900, "Basic cotton t-shirt", 30));
        clothes.add(new Product("ZARA Shirt", 59000, "Semi-casual shirt", 10));
        categories.add(new Category(clothes, "의류"));

        // 식품 상품 등록
        foods.add(new Product("Korean Beef", 45000, "Fresh domestic beef", 10));
        foods.add(new Product("Pork Belly", 18000, "Domestic pork", 20));
        foods.add(new Product("Shin Ramyun", 4500, "Spicy instant noodles", 50));
        foods.add(new Product("Milk", 2800, "Fresh 1L milk", 25));
        categories.add(new Category(foods, "식품"));

        // 커머스 플랫폼 실행
        system.start();
    }
}
