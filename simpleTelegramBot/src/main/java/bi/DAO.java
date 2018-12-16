package bi;

import java.util.List;

public interface DAO<T,R> {
	public T getOne(long id);

	public List<T> getAll(String table);

	public void save(T element );

	public void update(T update, T updatable);
}
