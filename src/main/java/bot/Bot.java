package bot;

import bot.commands.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//#TODO:1. Статический FoodRecorder -> нестатический и передавать везде в констркуторы
//#TODO:2. Отделяемость логики от телеграма
//#TODO:3. Токен и имя брать из переменных среды, не хардкодить
//#TODO:4. Класс для БЖУ вместо ArrayList
//#TODO:5. Убрать Config.txt, добавить все кодом

public class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private static Map<Long, ArrayList<String>> userData;


    public Bot(String botName, String botToken){
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;

    }
    public static ArrayList<String> getUserData(Long chatId) {
        ArrayList<String> data = userData.get(chatId);
        return data;
    }

    public static void setUserData(Long chatId, ArrayList<String> data) {
        userData.put(chatId, data);
        userData.remove(chatId);
    }

    private void removeUserData(Long chatId) {
        userData.remove(chatId);
    }


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage()){
            if (update.getMessage().hasText()){
                switch (update.getMessage().getText()){
                    case "/start" : {}
                    case "/help" : {}
                    case "/startrecord" : {}
                    case "/stoprecord" : {}
                    case "/add" : {}
                }
            }
        }
        if(update.hasCallbackQuery()){
            logic.FoodRecorder.add(update.getMessage().getChatId(), update.getCallbackQuery().getData());
        } else {
            Message msg = update.getMessage();
            Long chatId = msg.getChatId();

            SendMessage answer = new SendMessage();
            answer.setText("Ошибка");
            answer.setChatId(chatId.toString());
            try {
                execute(answer);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
