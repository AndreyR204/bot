package bot;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import logic.*;

import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private static Map<Long, List<String>> userData;

    public Bot(){
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        register(new StartCommand("start", "Старт"));
        register(new AddCommand("add", "Добавить"));
        register(new HelpCommand("help","Помощь"));
        register(new StopCommand("stop", "Стоп"));
        register(new StopRecordCommand("stoprecord", "Остановить запись"));
        register(new StartRecordCommand("startrecord", "Начать запись"));

    }
    public static List<String> getUserData(Long chatId) {
        List<String> data = userData.get(chatId);
        return data;
    }

    private void setUserData(Long chatId, List<String> settings) {
        userData.put(chatId, settings);
        userData.remove(chatId);
    }

    private void removeUserData(Long chatId) {
        userData.remove(chatId);
    }

    void sendAnswer(AbsSender absSender, Long chatId, String commandName, String userName, String text) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
        }
    }
}
