package cn.com.agree.aweb.springdata.repository.extend;

import java.io.Serializable;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.lang.Nullable;

/**
 * @author zhongyi@agree.com.cn
 */
public class AgreeJpaRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    public AgreeJpaRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        RepositoryFactorySupport factorySupport = new AgreeJpaRepositoryFactory<T, I>(entityManager);
        factorySupport.setRepositoryBaseClass(AgreeBaseRepositoryImpl.class);
        return factorySupport;
    }

    private static class AgreeJpaRepositoryFactory<T, I extends Serializable> extends
        JpaRepositoryFactory {
        private final EntityManager entityManager;

        private final PersistenceProvider extractor;


        /**
         * Creates a new {@link JpaRepositoryFactory}.
         *
         * @param entityManager must not be {@literal null}
         */
        AgreeJpaRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
            this.extractor = PersistenceProvider.fromEntityManager(entityManager);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information,
                                                                        EntityManager entityManager) {
            return new AgreeBaseRepositoryImpl<T, I>((Class<T>) information.getDomainType(), entityManager);
        }

        @Override
        protected Optional<QueryLookupStrategy> getQueryLookupStrategy(@Nullable QueryLookupStrategy.Key key,
                                                                       QueryMethodEvaluationContextProvider evaluationContextProvider) {
            return Optional.of(TemplateQueryLookupStrategy
                .create(entityManager, key, extractor, evaluationContextProvider));
        }

    }
}
