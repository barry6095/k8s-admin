package cn.com.agree.aweb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	@Override
	public T add(T t) {
		return getDao().save(t);
	}

	@Override
	public void delete(T t) {
		getDao().delete(t);
	}

	@Override
	public void delete(Iterable<ID> ids) {
		ids.forEach(this::delete);
	}

	@Override
	public void delete(ID id) {
		this.findById(id).ifPresent(this::delete);
	}
	
	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public Page<T> findAll(Example<T> example, Pageable page) {
		return getDao().findAll(example, page);
	}
	
	@Override
	public T update(T t) {
		return getDao().save(t);
	}

	@Override
	public Optional<T> findById(ID id) {
		return getDao().findById(id);
	}
	
	public abstract JpaRepository<T, ID> getDao();

}
