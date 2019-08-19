package cn.com.agree.aweb.service.authority;

import cn.com.agree.aweb.dao.authority.AuthorityDao;
import cn.com.agree.aweb.entity.po.auth.center.AuthorityPO;
import cn.com.agree.aweb.service.BaseServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl extends BaseServiceImpl<AuthorityPO, String> implements AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public JpaRepository<AuthorityPO, String> getDao() {
		return authorityDao;
	}

	@Override
	public AuthorityPO add(AuthorityPO t) {
		return authorityDao.save(t);
	}

	public void delete(AuthorityPO t) {
		authorityDao.delete(t);
	}

	public void delete(String id) {
		this.findById(id).ifPresent(this::delete);
	}

	public AuthorityPO update(AuthorityPO t) {
		return authorityDao.saveAndFlush(t);
	}

	public List<AuthorityPO> findAll() {
		return authorityDao.findAll();
	}

	public Optional<AuthorityPO> findById(String id) {
		return authorityDao.findById(id);
	}

	public Page<AuthorityPO> findAll(Example<AuthorityPO> example, Pageable page) {
		return authorityDao.findAll(example, page);
	}

	public void delete(Iterable<String> ids) {
		authorityDao.deleteAll(authorityDao.findAllById(ids));
	}
}
