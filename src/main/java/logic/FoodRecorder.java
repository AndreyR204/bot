package logic;

import java.util.*;

public class FoodRecorder {
    public HashMap<String, Product> products = new HashMap<>();
    private final DataManager dataManager = new DataManager();
    public FoodRecorder(){
        products.put("test", new Product("test", 1, 1, 1));

    }
    public String startRecord(Long id){
        ProductList list = new ProductList();
        this.dataManager.setUserData(id, list);
        return "Начата запись продуктов";
    }

    public String stopRecord(Long id){
        ProductList list = this.dataManager.getUserData(id);
        list.close();
        return String.format("За период с %s по %s, \n Вы съели %s белков," +
                "  %s жиров,  %s углеводов, \n в следующих продуктах: %s .", list.startTime, list.endTime, list.proteins, list.fats, list.carbohydrates, list.getProductNames());
    }

    public String add(Long id, String productName){
        Product product = products.get(productName);
        ProductList list = this.dataManager.getUserData(id);
        list.add(product);
        this.dataManager.setUserData(id, list);
        return String.format("Добавлен продукт: %s", product.name);
    }

    public Set<String> getAddKeyboard(){
        return products.keySet();
    }
}
