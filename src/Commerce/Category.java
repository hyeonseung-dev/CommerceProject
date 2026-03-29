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
    //속성 접근 기능 - 속성 꺼내오기
    public String getCategory(){
        return category;
    }
    public List<Product> getProducts(){
        return products;
    }
    //속성 접근 기능 - 속성 수정하기
    public void setCategory(String category){
        this.category = category;
    }
    public void setProducts(List<Product> products){
        this.products = products;
    }
}
