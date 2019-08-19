package cn.com.agree.aweb.dao.authority;

import cn.com.agree.aweb.entity.po.auth.center.AuthorityPO;
import cn.com.agree.aweb.springdata.repository.extend.AgreeBaseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao extends AgreeBaseRepository<AuthorityPO, String> {
	List<AuthorityPO> findByName(String name);

	@Query("select a from AuthorityPO a where a.name=?1")
	List<AuthorityPO> findByNameWithAuthority(String name);

	Optional<AuthorityPO> findById(String id);

	List<AuthorityPO> findAll();

	void deleteInBatch(Iterable<AuthorityPO> entities);

	<I extends AuthorityPO> I saveAndFlush(I ins);

	<S extends AuthorityPO> Page<S> findAll(Example<S> example, Pageable page);
}