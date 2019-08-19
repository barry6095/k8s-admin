package cn.com.agree.aweb.dao.user;

import cn.com.agree.aweb.entity.po.auth.center.UserPO;
import cn.com.agree.aweb.springdata.repository.extend.AgreeBaseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends AgreeBaseRepository<UserPO, String> {
	List<UserPO> findByName(String name);

	@Query("select u from UserPO u left join fetch u.roles where u.name=?1")
	List<UserPO> findByNameWithRoles(String name);

	Optional<UserPO> findById(String id);

	List<UserPO> findAll();

	void deleteInBatch(Iterable<UserPO> entities);

	<I extends UserPO> I saveAndFlush(I ins);

	<S extends UserPO> Page<S> findAll(Example<S> example, Pageable page);
}