package bot;

import bot.commands.*;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private static Map<Long, ArrayList<String>> userData;


    public Bot(String botName, String botToken){
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        register(new StartCommand("start", "Старт"));
        register(new AddCommand("add", "Добавить"));
        register(new HelpCommand("help","Помощь"));
        register(new StopRecordCommand("stoprecord", "Остановить запись"));
        register(new StartRecordCommand("startrecord", "Начать запись"));

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
}
