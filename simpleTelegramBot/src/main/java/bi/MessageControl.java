package bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import entity.Message;
import entity.User;

@Component
public class MessageControl {

	@Autowired
	private UserDAOImpl userDAOImpl;
	@Autowired
	private MessageDAOImpl messageDAOImpl;

	public SendMessage processUpdate(Update update) {
		
		SendMessage message = new SendMessage();
		message.setChatId(update.getMessage().getChatId());
		
		String name = update.getMessage().getFrom().getUserName();
		long id = update.getMessage().getFrom().getId();
		User tmp = new User(id,name);
		
		tmp.getMessagees().add(new Message(update.getMessage().getText()));
		userDAOImpl.save(tmp);
		
		message.setText("Object saved or updated");
		
		return message;

	}
}
