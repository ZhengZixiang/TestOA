package me.zzx.oa.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.PagerDao;
import me.zzx.oa.dao.UserDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.Role;
import me.zzx.oa.model.User;
import me.zzx.oa.model.UserRoleMapping;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	private PagerDao pagerDao;
	
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(User user) {
		user.setCreateTime(new Date());
		hibernateTemplate.save(user);
	}

	@Override
	public User load(int id) {
		return hibernateTemplate.load(User.class, id);
	}

	@Override
	public User load(String username) {
		String hql = "from User u where u.username = :var";
		List<User> list = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createQuery(hql, User.class).setParameter("var", username)
				.getResultList();
		if(list.size() == 0) return null;
		return list.get(0);
	}

	@Override
	public void delete(User user) {
		hibernateTemplate.delete(user);
	}

	@Override
	public void update(User user) {
		User temp = hibernateTemplate.load(User.class, user.getId());
		temp.setExpireTime(user.getExpireTime());
		temp.setUsername(user.getUsername());
		temp.setPassword(user.getPassword());
		hibernateTemplate.update(temp);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void addOrUpdateUserRoleMapping(int userId, int roleId, int orderNo) {
		UserRoleMapping ur = this.findUserRoleMapping(userId, roleId);
		
		if(ur == null) {
			ur = new UserRoleMapping();
			ur.setOrderNo(orderNo);
			ur.setUser(hibernateTemplate.load(User.class, userId));
			ur.setRole(hibernateTemplate.load(Role.class, roleId));
			hibernateTemplate.save(ur);
		} else {
			ur.setOrderNo(orderNo);
			hibernateTemplate.save(ur);
		}
	}

	@Override
	public void deleteUserRoleMapping(int userId, int roleId) {
		UserRoleMapping ur = this.findUserRoleMapping(userId, roleId);
		hibernateTemplate.delete(ur);
	}

	@Override
	public  UserRoleMapping findUserRoleMapping(int userId, int roleId) {
		UserRoleMapping ur = hibernateTemplate.execute(
				new HibernateCallback<UserRoleMapping>() {

					@Override
					public UserRoleMapping doInHibernate(Session session) throws HibernateException {
						String hql = "from UserRoleMapping ur where ur.user.id = ? and ur.role.id = ?";
						UserRoleMapping urMapping = (UserRoleMapping) session.createQuery(hql)
								.setParameter(0, userId)
								.setParameter(1, roleId)
								.getSingleResult();
						return urMapping;
					}
				});
		
		return ur;
	}

	@Override
	public void searchUserRoleMappings(int userId, Pager pager) {
		String hql = "from UserRoleMapping ur where ur.user.id = ? order by ur.orderNo";
		pagerDao.searchPaginated(hql, userId, pager);
	}
	

	@Override
	public List<Role> searchSameUser(int userId) {
		String hql = "select r from UserRoleMapping ur join ur.role r where ur.user.id = ? order by ur.orderNo";
		List<Role> roles = hibernateTemplate.execute(new HibernateCallback<List<Role>>() {

			@Override
			public List<Role> doInHibernate(Session session) throws HibernateException {
				List<Role> list = (List<Role>) session.createQuery(hql, Role.class)
					.setParameter(0, userId)
					.getResultList();
				return list;
			}
		});
		return roles;
	}


	public PagerDao getPagerDao() {
		return pagerDao;
	}

	@Resource
	public void setPagerDao(PagerDao pagerDao) {
		this.pagerDao = pagerDao;
	}

}
