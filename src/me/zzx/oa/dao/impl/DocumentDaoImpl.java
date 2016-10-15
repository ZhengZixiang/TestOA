package me.zzx.oa.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.zzx.oa.dao.DocumentDao;
import me.zzx.oa.dao.PagerDao;
import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.ApproveInfo;
import me.zzx.oa.model.Document;

@Repository("documentDao")
public class DocumentDaoImpl implements DocumentDao {

	private PagerDao pagerDao;
	
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public Document load(int id) {
		return hibernateTemplate.load(Document.class, id);
	}

	@Override
	public void saveApproveInfo(ApproveInfo info) {
		hibernateTemplate.save(info);
	}

	@Override
	public void save(Document document) {
		hibernateTemplate.save(document);
	}

	@Override
	public void delete(Document doc) {
		hibernateTemplate.delete(doc);
	}

	@Override
	public void update(Document document) {
		hibernateTemplate.update(document);
	}

	@Override
	public void searchApproveInfo(int documentId, Pager pager) {
		String hql = "from ApproveInfo ai where ( ai.document.id = ? )";
		pagerDao.searchPaginated(hql, documentId, pager);
	}

	@Override
	public void searchCreatedDocuments(int userId, Pager pager) {
		String hql = "from Document doc where ( doc.creator.id = ? )";
		pagerDao.searchPaginated(hql, userId, pager);
	}

	@Override
	public void searchApprovedDocuments(int userId, Pager pager) {
		String hql = "select distinct ai.document from ApproveInfo ai where ( ai.approver.id = ? )";
		pagerDao.searchPaginated(hql, userId, pager);
	}

	public PagerDao getPagerDao() {
		return pagerDao;
	}

	@Resource
	public void setPagerDao(PagerDao pagerDao) {
		this.pagerDao = pagerDao;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Document loadByProcessInstanceId(long parseLong) {
		return (Document) hibernateTemplate.getSessionFactory().getCurrentSession()
			.createQuery("from Document doc where doc.processInstanceId = :var")
			.setParameter("var", parseLong)
			.getSingleResult();
	}
	
}
