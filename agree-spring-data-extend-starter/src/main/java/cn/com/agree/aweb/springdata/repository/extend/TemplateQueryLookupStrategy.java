package cn.com.agree.aweb.springdata.repository.extend;

import cn.com.agree.aweb.annotation.TemplateQuery;
import cn.com.agree.aweb.springdata.repository.extend.query.FreemarkerJpaQuery;
import java.lang.reflect.Method;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.lang.NonNull;


/**
 * @author zhongyi@agree.com.cn
 */
public class TemplateQueryLookupStrategy implements QueryLookupStrategy {

  private final EntityManager entityManager;

  private QueryLookupStrategy jpaQueryLookupStrategy;

  private QueryExtractor extractor;

  public TemplateQueryLookupStrategy(EntityManager entityManager, Key key, QueryExtractor extractor,
      QueryMethodEvaluationContextProvider evaluationContextProvider) {
    this.jpaQueryLookupStrategy = JpaQueryLookupStrategy
        .create(entityManager, key, extractor, evaluationContextProvider );
    this.extractor = extractor;
    this.entityManager = entityManager;
  }

  public static QueryLookupStrategy create(EntityManager entityManager, Key key,
      QueryExtractor extractor,
      QueryMethodEvaluationContextProvider evaluationContextProvider) {
    return new TemplateQueryLookupStrategy(entityManager, key, extractor,
        evaluationContextProvider);
  }

  @Override
  @NonNull
  public RepositoryQuery resolveQuery(Method method, @NonNull RepositoryMetadata metadata,
      @NonNull ProjectionFactory factory,
      @NonNull NamedQueries namedQueries) {
    TemplateQuery annotation = method.getAnnotation(TemplateQuery.class);
    if (annotation == null) {
      return jpaQueryLookupStrategy.resolveQuery(method, metadata, factory, namedQueries);
    } else {
      return new FreemarkerJpaQuery(new JpaQueryMethod(method, metadata, factory, extractor),
          entityManager, annotation);
    }
  }
}
