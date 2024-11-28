package com.dao;

import java.util.List;

public interface GenericDao<T> {
	void save(T t);
	void update(T t);
	T findById(int id);
	List<T> findAll();
	void delete(int id);
}
