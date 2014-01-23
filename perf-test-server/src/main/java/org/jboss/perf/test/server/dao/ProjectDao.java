package org.jboss.perf.test.server.dao;

import java.util.List;

import org.jboss.perf.test.server.model.Project;


public interface ProjectDao {
	public Project save(Project p);
	public void remove(Project p);
	public List<Project> getProjects();
	public Project getProjectByName(String project);
	public Project getProjectById(long id);
}
