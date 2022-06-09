package logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ProductList {
    public Date startTime;
    public Date endTime;
    public Integer proteins = null;
    public Integer fats = null;
    public Integer carbohydrates = null;
    public ArrayList<Product> products;

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
