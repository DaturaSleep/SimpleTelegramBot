package bi;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.User;

@Component
public class UserDAOImpl implements DAO<User, String> {

	@Autowired
	private SessionFactory sessionFactory;

	public User getOne(long id) {
		

		return null;
	}

	public List<User> getAll(String table) {
		
		return null;
	}

	public void save(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try {
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
		session.close();
		}
	}

	public void update(User update, User updatable) {

	}

}
