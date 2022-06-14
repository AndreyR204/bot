package logic;

import java.util.*;

public class FoodRecorder {
    public HashMap<String, Product> products = new HashMap<>();
    private final DataManager dataManager = new DataManager();
    public FoodRecorder(){
        products.put("Котлета (100 г)", new Product("Котлета (100 г)", 21.6, 16.8, 2.7));
        products.put("Йогурт (100 г)", new Product("Йогурт (100 г)", 2.8, 6.0, 10.0));
        products.put("Кефир (100 г)", new Product("Кефир (100 г)", 3.0, 2.5, 4.0));
        products.put("Сыр (100 г)", new Product("Сыр (100 г)", 11.7, 17.6, 2.1));
        products.put("Хлеб (100 г)", new Product("Хлеб (100 г)", 9.5, 4.5, 62.0));

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
