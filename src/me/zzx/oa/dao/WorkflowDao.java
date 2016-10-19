package me.zzx.oa.dao;

import java.util.List;

import me.zzx.oa.model.Workflow;

public interface WorkflowDao {

	public void save(Workflow workflow);

	public void delete(Workflow workflow);

	public Workflow load(int id);

	public Workflow loadByName(String processName);

	public List<Workflow> searchWorkflows();

}
