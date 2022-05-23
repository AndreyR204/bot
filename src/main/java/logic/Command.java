package logic;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;

abstract class Command extends BotCommand {
    Command(String identifier, String description) {
        super(identifier, description);
    }
}
