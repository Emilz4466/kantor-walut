package nowewaluty.strategies;

import nowewaluty.exceptions.DaoSessionException;

public interface Dao<T> {
	T get(long id) throws DaoSessionException;

	default T get(String name) throws DaoSessionException {
		return null;
	}

	default T get(Long id1, Long id2) throws DaoSessionException {
		return null;
	}

	void save(T t) throws DaoSessionException;

	void update(long id, T t) throws DaoSessionException;
}
