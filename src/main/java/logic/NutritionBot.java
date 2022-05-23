package logic;

import java.util.List;
import java.util.Map;

public class NutritionBot {
    private static Map<Long, List<String>> userData;

    public NutritionBot(){

    }

    public void receiveMessage(String message, String from){

    }

    public void sendMessage(String message, String to){

    }

    public static List<String> getUserData(Long chatId) {
        List<String> data = userData.get(chatId);
        return data;
    }

    private void saveUserData(Long chatId, List<String> settings) {
            userData.put(chatId, settings);
            userData.remove(chatId);
    }

    private void removeUserData(Long chatId) {
        userData.remove(chatId);
    }

}
