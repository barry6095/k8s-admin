package cn.com.agree.aweb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BaseService<T, ID extends Serializable> {
	
	T add(T t);
	void delete(T t);
	void delete(Iterable<ID> ids);
	void delete(ID id);
	T update(T t);
	List<T> findAll();
	Page<T> findAll(Example<T> example, Pageable page);
	Optional<T> findById(ID id);
	
}
