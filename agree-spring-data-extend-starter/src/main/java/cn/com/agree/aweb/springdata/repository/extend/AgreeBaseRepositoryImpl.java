package cn.com.agree.aweb.springdata.repository.extend;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * @author zhongyi@agree.com.cn
 */
class AgreeBaseRepositoryImpl<T, ID extends Serializable> extends
    SimpleJpaRepository<T, ID> implements AgreeBaseRepository<T, ID> {

    AgreeBaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}
}
