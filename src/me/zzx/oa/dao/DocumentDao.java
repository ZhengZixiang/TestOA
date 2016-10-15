package me.zzx.oa.dao;

import me.zzx.oa.dto.Pager;
import me.zzx.oa.model.ApproveInfo;
import me.zzx.oa.model.Document;

public interface DocumentDao {

	public Document load(int id);

	public void saveApproveInfo(ApproveInfo info);

	public void save(Document document);

	public void delete(Document doc);

	public void update(Document document);

	public void searchApproveInfo(int documentId, Pager pager);

	public void searchCreatedDocuments(int userId, Pager pager);

	public void searchApprovedDocuments(int userId, Pager pager);

	public Document loadByProcessInstanceId(long parseLong);

}
