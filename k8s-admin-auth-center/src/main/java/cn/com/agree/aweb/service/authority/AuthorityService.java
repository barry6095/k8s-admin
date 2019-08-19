package cn.com.agree.aweb.service.authority;

import cn.com.agree.aweb.entity.po.auth.center.AuthorityPO;
import cn.com.agree.aweb.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AuthorityService extends BaseService<AuthorityPO, String> {

}
