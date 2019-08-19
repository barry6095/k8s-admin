package cn.com.agree.aweb.service.user;

import cn.com.agree.aweb.dao.group.GroupDao;
import cn.com.agree.aweb.dao.role.RoleDao;
import cn.com.agree.aweb.dao.user.UserDao;
import cn.com.agree.aweb.entity.po.auth.center.GroupPO;
import cn.com.agree.aweb.entity.po.auth.center.UserPO;
import cn.com.agree.aweb.entity.security.DefaultUserDetails;
import cn.com.agree.aweb.service.BaseServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserPO, String> implements UserDetailsService,
    UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private GroupDao groupDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findByNameWithRoles(username).stream().findFirst().map(DefaultUserDetails::new).orElse(null);
	}

	@Override
	public JpaRepository<UserPO, String> getDao() {
		return userDao;
	}

	@Override
	public UserPO add(UserPO t) {
		return userDao.save(t);
	}
	
	@Override
	public void add(UserPO t, String[] roleIds, String[] groupIds) {
		t.setPassword("{bcrypt}" + new BCryptPasswordEncoder().encode(t.getPassword()));
		t.setRoles(roleDao.findAllById(Arrays.asList(roleIds)));
		userDao.save(t);
		groupDao.findAllById(Arrays.asList(groupIds)).stream().map(group -> {group.getUsers().add(t); return group;}).forEach(groupDao::save);
	}

	@Override
	public void delete(UserPO t) {
		userDao.delete(t);
	}

	@Override
	public void delete(String id) {
		this.findById(id).ifPresent(this::delete);
	}

	@Override
	public UserPO update(UserPO t) {
		return userDao.save(t);
	}

	@Override
	public void update(UserPO t, String[] roleIds, String[] groupIds) {
		t.setPassword(t.getPassword() != null && t.getPassword().length() > 0?"{bcrypt}" + new BCryptPasswordEncoder().encode(t.getPassword()):findById(t.getId()).orElseThrow(IllegalArgumentException::new).getPassword());
		t.setRoles(roleDao.findAllById(Arrays.asList(roleIds)));
		userDao.save(t);
		groupDao.findAll(Example.of(new GroupPO().setUsers(Arrays.asList(t)))).stream().map(group -> {group.getUsers().remove(t); return group;}).forEach(groupDao::save);
		groupDao.findAllById(Arrays.asList(groupIds)).stream().map(group -> {group.getUsers().add(t); return group;}).forEach(groupDao::save);
	}

	@Override
	public List<UserPO> findAll() {
		return userDao.findAll();
	}

	@Override
	public Optional<UserPO> findById(String id) {
		return userDao.findById(id);
	}

	@Override
	public List<UserPO> findByName(String name) {
		return userDao.findByName(name);
	}

	@Override
	public Page<UserPO> findAll(Example<UserPO> example, Pageable page) {
		return userDao.findAll(example, page);
	}

	@Override
	public void delete(Iterable<String> ids) {
		userDao.deleteAll(userDao.findAllById(ids));
	}

}
