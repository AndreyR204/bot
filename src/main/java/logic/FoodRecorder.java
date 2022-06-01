package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class FoodRecorder {
    public FoodRecorder(){

    }
    public String startRecord(Long id){
        Date date = new Date();
        ArrayList<String> nullData = (ArrayList<String>) Arrays.asList(date.toString(), "0", "0", "0", "");
        bot.Bot.setUserData(id, nullData);
        return String.format("%s \n Начата запись продуктов", date.toString());
    }

    public  String stopRecord(Long id){
        Date date = new Date();
        ArrayList<String> userData = bot.Bot.getUserData(id);
        return String.format("За период с %s по %s, \n Вы съели %s белков," +
                "  %s жиров,  %s углеводов, \n в следующих продуктах: %s .", userData.get(0), date.toString(), userData.get(1),userData.get(2), userData.get(3), userData.get(4));
    }

    public String add(Long id, ArrayList<String> product){
        ArrayList<String> userData = bot.Bot.getUserData(id);
        userData.set(0, "");
        userData.set(1, Integer.toString(Integer.parseInt(userData.get(1)) + Integer.parseInt(product.get(1))));
        userData.set(2, Integer.toString(Integer.parseInt(userData.get(2)) + Integer.parseInt(product.get(2))));
        userData.set(3, Integer.toString(Integer.parseInt(userData.get(3)) + Integer.parseInt(product.get(3))));
        userData.set(4, userData.get(4) + ", " + product.get(0));
        return String.format("Добавлен продукт: %s", product.get(0));
    }

}
