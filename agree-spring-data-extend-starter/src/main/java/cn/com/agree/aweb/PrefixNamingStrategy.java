package cn.com.agree.aweb;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

/**
 * @author zhongyi@agree.com.cn
 */
public class PrefixNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {
	private static final long serialVersionUID = 1998475338924625071L;
	public static final PrefixNamingStrategy INSTANCE = new PrefixNamingStrategy();

    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        return super.determineBasicColumnName(source);
    }
}
