package cn.com.agree.aweb.service.user;

import cn.com.agree.aweb.entity.po.auth.center.UserPO;
import cn.com.agree.aweb.service.BaseService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService extends BaseService<UserPO, String> {

	public void add(UserPO t, String[] roleIds, String[] groupIds);

	public void update(UserPO t, String[] roleIds, String[] groupIds);
	
	List<UserPO> findByName(String name);
}
