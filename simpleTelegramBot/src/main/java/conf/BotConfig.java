package conf;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import bi.MessageControl;
import bi.MessageDAOImpl;
import bi.UserDAOImpl;
import bot.BoonkerBot;

@Configuration
@ComponentScan("bot")
public class BotConfig {
	@Bean
	@Autowired
	@Scope(value = "singleton")
	public MessageControl messageControl() {
		return new MessageControl();
	}

	@Bean
	@Autowired
	@Scope(value = "singleton")
	public MessageDAOImpl messageDAOImpl() {
		return new MessageDAOImpl();
	}

	@Bean
	@Autowired
	@Scope(value = "singleton")
	public UserDAOImpl userDAOImpl() {
		return new UserDAOImpl();
	}

	@Bean
	@Autowired
	public BoonkerBot boonkerBot() {
		BoonkerBot bot = new BoonkerBot();
		bot.setBotToken("yourBotToken");
		bot.setBotUsername("BoonkerBot");
		return bot;
	}

	@Bean(name = "dataSource")
	public DataSource getMSQLDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(
				"jdbc:mysql://localhost/telegramBoonkerBot?useLegacyDatetimeCode=false&serverTimezone=Australia/Sydney&allowPublicKeyRetrieval=true&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("Your DB password");
		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		Properties prop = new Properties();
		prop.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		prop.put("hibernate.show_sql", "true"); // optional
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		prop.put("hibernate.current_session_context_class", "thread");

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(getMSQLDataSource());
		sessionBuilder.addProperties(prop);
		sessionBuilder.addAnnotatedClasses(entity.User.class, entity.Message.class);

		SessionFactory sessionFactory = sessionBuilder.buildSessionFactory();
		return sessionFactory;
	}

}
