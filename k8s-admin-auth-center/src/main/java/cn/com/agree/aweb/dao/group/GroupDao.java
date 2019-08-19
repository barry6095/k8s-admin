package cn.com.agree.aweb.dao.group;

import cn.com.agree.aweb.entity.po.auth.center.GroupPO;
import cn.com.agree.aweb.springdata.repository.extend.AgreeBaseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends AgreeBaseRepository<GroupPO, String> {
	List<GroupPO> findByName(String name);

	Optional<GroupPO> findById(String id);

	List<GroupPO> findAll();

	void deleteInBatch(Iterable<GroupPO> entities);

	<I extends GroupPO> I saveAndFlush(I ins);

	<S extends GroupPO> Page<S> findAll(Example<S> example, Pageable page);

	@Query("select g from GroupPO g left join fetch g.users u where u.id = ?1")
	List<GroupPO> findByUserId(String userId);
}