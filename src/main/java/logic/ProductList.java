package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ProductList {
    public Date startTime;
    public Date endTime;
    public ArrayList<Product> products;

    public ProductList(){
        this.startTime = new Date();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void add(Product product){
        this.products.add(product);
    }

    public HashMap<String, String> getList(){
        HashMap<String, String> info = new  HashMap<>();
        info.put("startTime", this.startTime.toString());
        info.put("endTime", this.startTime.toString());
        String products = "";
        Integer proteins = null;
        Integer fats = null;
        Integer carbohydrates = null;
        for (Product product : this.products){
            proteins += product.proteins;
            fats += product.fats;
            carbohydrates += product.carbohydrates;
            if (products.isBlank()){
                products += product.name;
            } else {
                products += ", " + product.name;
            }
        }
        info.put("proteins", proteins.toString());
        info.put("fats", fats.toString());
        info.put("carbohydrates", carbohydrates.toString());
        info.put("products", products);
        return info;
    }

    public void close(){
        this.endTime = new Date();
    }
}
