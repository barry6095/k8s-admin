package cn.com.agree.aweb.service.group;

import cn.com.agree.aweb.entity.po.auth.center.GroupPO;
import cn.com.agree.aweb.service.BaseService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GroupService extends BaseService<GroupPO, String> {

	public void add(GroupPO t, String[] roleIds);

	public void update(GroupPO t, String[] roleIds);

	List<GroupPO> findByUserId(String userId);
}
