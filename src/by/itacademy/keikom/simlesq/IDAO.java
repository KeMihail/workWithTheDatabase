package by.itacademy.keikom.simlesq;

import java.util.List;

public interface IDAO <T> {

	void create(T obj);
	Boolean delete(Integer id);
	void update(T obj);
	List<T> allRecords();
	T getById(Integer id);
}
