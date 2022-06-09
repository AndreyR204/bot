package logic;

public class Product {
    public String name;
    public Integer proteins;
    public Integer fats;
    public Integer carbohydrates;

    public Product(String name, Integer proteins, Integer fats, Integer carbohydrates){
        this.name = name;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
}
