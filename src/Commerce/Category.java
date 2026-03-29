package Commerce;

import java.util.List;

public class Category {
    //속성
    private List<Product> products;
    private String category;
    //생성자
    public Category(List<Product> products, String category){
        this.products = products;
        this.category = category;
    }
    //기능
    //카테고리 이름 반환 기능
    public String getCategory(){
        return category;
    }
    public List<Product> getProducts(){
        return products;
    }
}
