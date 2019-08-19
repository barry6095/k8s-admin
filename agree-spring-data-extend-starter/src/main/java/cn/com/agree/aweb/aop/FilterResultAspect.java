
package cn.com.agree.aweb.aop;

import cn.com.agree.aweb.annotation.FilterResult;
import cn.com.agree.aweb.springdata.repository.extend.filter.ResultFilter;
import cn.com.agree.aweb.springdata.repository.extend.filter.ResultFilterCriteriaThreadLocal;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author zhongyi@agree.com.cn
 */
@Aspect
public class FilterResultAspect {

	@Autowired
	private ApplicationContext ac;

	@Around("@annotation(cn.com.agree.aweb.annotation.FilterResult)")
	public void before(ProceedingJoinPoint pjp) throws Throwable {

		FilterResult filterResult = this.getAnnotation(pjp).orElseThrow(IllegalStateException::new);
		String[] filters = filterResult.filters();
		String[] inputs = filterResult.values();
		ResultFilterCriteriaThreadLocal.set(IntStream.range(0, filters.length).mapToObj(index -> ResultFilter.class.cast(ac.getBean(filters[index])).getCriteria(index >= inputs.length ? "" : inputs[index])).collect(Collectors.toList()));
		pjp.proceed();
		ResultFilterCriteriaThreadLocal.get().ifPresent(item -> ResultFilterCriteriaThreadLocal.remove());
	}

	private Optional<FilterResult> getAnnotation(ProceedingJoinPoint pjp) throws SecurityException {
		return Optional.ofNullable(((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(FilterResult.class));
	}
}
