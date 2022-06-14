package logic;

import java.util.*;

public class FoodRecorder {
    public HashMap<String, Product> products = new HashMap<>();
    private final DataManager dataManager = new DataManager();
    public FoodRecorder(){
        products.put("test", new Product("test", 1.1, 1.2, 1.3));

    }
    public String startRecord(Long id){
        ProductList list = new ProductList();
        this.dataManager.setUserData(id, list);
        return "Начата запись продуктов";
    }

    public String stopRecord(Long id){
        try {
            ProductList list = this.dataManager.getUserData(id);
            list.close();
            return String.format("За период с %s по %s, \n Вы съели %s г. белков," +
                    "  %s г. жиров,  %s г. углеводов, \n в следующих продуктах: %s .", list.startTime, list.endTime, list.proteins, list.fats, list.carbohydrates, list.getProductNames());
        } catch (NullPointerException e){
            return "Ошибка";
        }

    }

    public String add(Long id, String productName){
        try {
            Product product = products.get(productName);
            ProductList list = this.dataManager.getUserData(id);
            list.add(product);
            this.dataManager.setUserData(id, list);
            return String.format("Добавлен продукт: %s", product.name);
        } catch (NullPointerException e){
            return "Ошибка";
        }

    }

    public Set<String> getAddKeyboard(){
        return products.keySet();
    }
}
