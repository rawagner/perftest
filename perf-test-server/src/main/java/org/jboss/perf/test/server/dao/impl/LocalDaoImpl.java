package org.jboss.perf.test.server.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.perf.test.server.dao.LocalDao;
import org.jboss.perf.test.server.model.Local;

@Stateless
public class LocalDaoImpl implements LocalDao {
	@Inject
	private EntityManager em;

	@Override
	public List<Local> getLocalsByProjectId(long projectId) {
		return em.createNamedQuery("localsByProjectId", Local.class)
					.setParameter("projectId", projectId)
					.getResultList();
	}
}
