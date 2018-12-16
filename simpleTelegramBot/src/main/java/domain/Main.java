package domain;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;

import bot.BoonkerBot;

public class Main {

	public static void main(String args[]) {
		ApiContextInitializer.init();

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(conf.BotConfig.class);
		BoonkerBot bot = context.getBean(BoonkerBot.class);
		bot.init();
	}

}
