package bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import bi.MessageControl;

@Component
public class BoonkerBot extends TelegramLongPollingBot {

	private String botUsername;
	private String botToken;

	@Autowired
	private MessageControl messageControl;

	
	public void onUpdateReceived(Update update) {
		
		
		try {
			execute(messageControl.processUpdate(update));
		} catch (TelegramApiException e) {

			e.printStackTrace();
		}

	}
	
	
	public void init() {
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(this);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public void setBotUsername(String botUsername) {
		this.botUsername = botUsername;
	}

	public String getBotUsername() {
		return botUsername;
	}

	public void setBotToken(String botToken) {
		this.botToken = botToken;
	}

	public String getBotToken() {
		return botToken;
	}
}