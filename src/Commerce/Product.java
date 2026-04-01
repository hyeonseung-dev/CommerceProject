package Commerce;

public class Product {
    //속성
    private String name;
    private int price;
    private String explain;
    private int stock;

    //생성자
    public Product(String name, int price, String explain, int stock){
        this.name = name;
        this.price = price;
        this.explain = explain;
        this.stock = stock;
    }
    //기능
    //속성 접근 기능 - 속성 꺼내오기
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public String getExplain(){
        return explain;
    }
    public int getStock(){
        return stock;
    }

    //속성 접근 기능 - 속성 수정하기
    public void setName(String name){
        this.name = name;
    }
    public void changePrice(int newPrice){
        validatePrice(newPrice);
        this.price = newPrice;
    }
    public void validatePrice(int price){
        if(price < 0){
            throw new IllegalArgumentException("가격은 0 이상이어여합니다.");
        }
    }

    public void setExplain(String explain){
        this.explain = explain;
    }
    public void changeStock(int newStock){
        validatePrice(newStock);
        this.stock = newStock;
    }
    public void validateStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("수량은 0 이상이어여합니다.");
        }
    }

    // 장바구니에서 구매 시 재고 삭감 기능
    public void decreaseStock(int quantity) {
        if (quantity > stock) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        stock -= quantity;
    }

}
