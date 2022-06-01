package logic;

import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class FoodRecorder {
    public FoodRecorder(){

    }
    public String startRecord(Long id){
        Date date = new Date();
        List<String> nullData = Arrays.asList(date.toString(), "0", "0", "0", "", "");
        bot.Bot.setUserData(id, nullData);
        return date.toString();
    }

    public  String stopRecord(){
        Date date = new Date();

    }

    public void add(){

    }
}
