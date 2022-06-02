package logic;

import java.util.*;

public class FoodRecorder {
    public static final HashMap<String, ArrayList<String>> products = new HashMap<>();
    public FoodRecorder(){


    }
    public static String startRecord(Long id){
        Date date = new Date();
        ArrayList<String> nullData = (ArrayList<String>) Arrays.asList(date.toString(), "0", "0", "0", "");
        bot.Bot.setUserData(id, nullData);
        return String.format("%s \n Начата запись продуктов", date.toString());
    }

    public static  String stopRecord(Long id){
        Date date = new Date();
        ArrayList<String> userData = bot.Bot.getUserData(id);
        return String.format("За период с %s по %s, \n Вы съели %s белков," +
                "  %s жиров,  %s углеводов, \n в следующих продуктах: %s .", userData.get(0), date.toString(), userData.get(1),userData.get(2), userData.get(3), userData.get(4));
    }

    public static String add(Long id, String productName){
        ArrayList<String> userData = bot.Bot.getUserData(id);
        ArrayList<String> product = products.get(productName);
        userData.set(0, "");
        userData.set(1, Integer.toString(Integer.parseInt(userData.get(1)) + Integer.parseInt(product.get(1))));
        userData.set(2, Integer.toString(Integer.parseInt(userData.get(2)) + Integer.parseInt(product.get(2))));
        userData.set(3, Integer.toString(Integer.parseInt(userData.get(3)) + Integer.parseInt(product.get(3))));
        userData.set(4, userData.get(4) + ", " + product.get(0));
        return String.format("Добавлен продукт: %s", product.get(0));
    }


}
