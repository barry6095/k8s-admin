package cn.com.agree.aweb.springdata.repository.extend.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author zhongyi@agree.com.cn
 */
@Slf4j
public class SpringElSqlResultFilter implements ResultFilter {
	
	@Override
	public String getCriteria(String el) {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression(el, 
		        new TemplateParserContext());
		EvaluationContext context = new StandardEvaluationContext();
        log.debug("生成条件信息: {}", expression.getValue(context, String.class));
		return expression.getValue(context, String.class);
	}
	
}
