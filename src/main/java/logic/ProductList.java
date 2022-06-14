package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ProductList {
    public Date startTime;
    public Date endTime;
    public Double proteins = 0.0;
    public Double fats = 0.0;
    public Double carbohydrates = 0.0;
    public ArrayList<Product> products = new ArrayList<>();

    public ProductList(){
        this.startTime = new Date();
    }


    public void add(Product product){
        this.products.add(product);
        this.proteins += product.proteins;
        this.fats += product.fats;
        this.carbohydrates += product.carbohydrates;
    }

    public String getProductNames(){
        String products = "";
        for (Product product : this.products){
            if (products.isBlank()){
                products += product.name;
            } else {
                products += ", " + product.name;
            }
        }
        return products;
    }

    public void close(){
        this.endTime = new Date();
    }
}
