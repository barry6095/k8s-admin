package cn.com.agree.aweb.service.group;

import cn.com.agree.aweb.dao.group.GroupDao;
import cn.com.agree.aweb.dao.user.UserDao;
import cn.com.agree.aweb.entity.enums.constant.RoleContant;
import cn.com.agree.aweb.entity.po.auth.center.GroupPO;
import cn.com.agree.aweb.service.BaseServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends BaseServiceImpl<GroupPO, String> implements GroupService {

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private UserDao userDao;

	@Override
	public JpaRepository<GroupPO, String> getDao() {
		return groupDao;
	}

	@Override
	public GroupPO add(GroupPO t) {
		return groupDao.save(t);
	}

	@Override
	public void add(GroupPO t, String[] userIds) {
		t.setUsers(userDao.findAllById(Arrays.asList(userIds)));
		groupDao.save(t);
	}

	@Override
	public void delete(GroupPO t) {
		groupDao.delete(t);
	}

	@Override
	public void delete(String id) {
		this.findById(id).ifPresent(this::delete);
	}

	@Override
	public GroupPO update(GroupPO t) {
		return groupDao.saveAndFlush(t);
	}

	@Override
	public void update(GroupPO t, String[] userIds) {
		t.setUsers(userDao.findAllById(Arrays.asList(userIds)));
		groupDao.saveAndFlush(t);
	}

	@Override
	public List<GroupPO> findByUserId(String userId) {
		return userDao.findById(userId).orElseThrow(IllegalArgumentException::new).getRoles().stream().filter(rolePO -> RoleContant.ADMIN_ID.getName().equals(rolePO.getId())).collect(Collectors.toList()).size() > 0 ? groupDao.findAll() : groupDao.findByUserId(userId);
	}
	@Override
	public List<GroupPO> findAll() {
		return groupDao.findAll();
	}

	@Override
	public Optional<GroupPO> findById(String id) {
		return groupDao.findById(id);
	}

	@Override
	public Page<GroupPO> findAll(Example<GroupPO> example, Pageable page) {
		return groupDao.findAll(example, page);
	}

	@Override
	public void delete(Iterable<String> ids) {
		groupDao.deleteAll(groupDao.findAllById(ids));
	}

}
