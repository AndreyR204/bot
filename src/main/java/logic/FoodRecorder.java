package logic;

import java.util.*;

public class FoodRecorder {
    public static HashMap<String, Product> products = new HashMap<>();
    private final DataManager dataManager = new DataManager();
    public FoodRecorder(){

    }
    public String startRecord(Long id){
        this.dataManager.setUserData(id, new ProductList());
        return "Начата запись продуктов";
    }

    public String stopRecord(ArrayList<String> userData){
        Date date = new Date();
        return String.format("За период с %s по %s, \n Вы съели %s белков," +
                "  %s жиров,  %s углеводов, \n в следующих продуктах: %s .", userData.get(0), date.toString(), userData.get(1),userData.get(2), userData.get(3), userData.get(4));
    }

    public ArrayList<String> add(ArrayList<String> userData, String productName){
        Product product = products.get(productName);

        return userData;
    }
}
