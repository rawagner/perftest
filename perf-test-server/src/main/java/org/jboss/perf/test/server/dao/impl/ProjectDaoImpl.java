package org.jboss.perf.test.server.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.perf.test.server.dao.ProjectDao;
import org.jboss.perf.test.server.model.Project;


@Stateless
public class ProjectDaoImpl implements ProjectDao {
	@Inject
	private EntityManager em;
	
	@Override
	public Project save(Project p) {
		return em.merge(p);
	}

	@Override
	public void remove(Project p) {
		em.remove(em.merge(p));
	}
	
	@Override
	public List<Project> getProjects() {
		return em.createNamedQuery("projects", Project.class).getResultList();
	}

	@Override
	public Project getProjectByName(String project) {
		List<Project> projects = em.createNamedQuery("projectsByName", Project.class)
									.setParameter("project", project)
									.setMaxResults(2)
									.getResultList();
		
		return projects.isEmpty()? null : projects.get(0);
	}
	
	@Override
	public Project getProjectById(long id) {
		return em.find(Project.class, id);
	}
}
