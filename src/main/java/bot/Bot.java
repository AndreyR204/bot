package bot;

import logic.FoodRecorder;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//#TODO:5. Убрать Config.txt, добавить все кодом

public class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final FoodRecorder foodRecorder = new FoodRecorder();


    public Bot(String botName, String botToken){
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;

    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }


    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()){
            Long id1 = update.getCallbackQuery().getFrom().getId();
            this.foodRecorder.add(id1, update.getCallbackQuery().getData());
        } else {
            if (update.hasMessage()){
                Long id = update.getMessage().getChatId();
                if (update.getMessage().hasText()){
                    switch (update.getMessage().getText()){
                        case ("/start") :
                            sendMessage("Здравствуйте, чтобы начать запись продуктов введите /startrecord", id, null);
                            break;
                        case ("/help") :
                            sendMessage("Бот для подсчета съеденных белков, жиров и углеводов за преиод", id, null);
                            break;
                        case ("/startrecord") :
                            sendMessage(this.foodRecorder.startRecord(id), id, null);
                            break;
                        case ("/stoprecord") :
                            sendMessage(this.foodRecorder.stopRecord(id), id, null);
                            break;
                        case ("/add") :
                            sendMessage("Выберите продукт", id, createKeyboard(this.foodRecorder.getAddKeyboard()));
                            break;
                        default:
                            sendMessage("Ошибка", id, null);
                            break;
                    }
                }
            }
        }
    }

    public InlineKeyboardMarkup createKeyboard(Set<String> buttons){
        InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        for (String key : buttons){
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(key.toString());
            inlineKeyboardButton.setCallbackData(key.toString());
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(inlineKeyboardButton);
            keyboardButtons.add(keyboardButtonsRow1);
        }
        inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        return inlineKeyboardMarkup;
    }

    public void sendMessage(String text, Long chatId, InlineKeyboardMarkup keyboard){
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        answer.setReplyMarkup(keyboard);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
