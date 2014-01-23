package org.jboss.perf.test.server.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.perf.test.server.dao.MethodDao;
import org.jboss.perf.test.server.model.Method;



@Stateless
public class MethodDaoImpl implements MethodDao {
	@Inject
	private EntityManager em;

	public Method save(Method method) {
		return em.merge(method);
	}
	
	@Override
	public Method getMethodByName(String method) {
		List<Method> methods = em.createNamedQuery("methodByName", Method.class)
									.setParameter("method", method)
									.setMaxResults(2)
									.getResultList();
		
		return methods.isEmpty()? null : methods.get(0);
	}
	
	@Override
	public Method getMethodById(long methodId) {
		return em.find(Method.class, methodId);
	}
	
	@Override
	public List<Method> getAllMethodsByTestSuiteRunId(Long testSuiteRunId) {
		return em.createNamedQuery("allMethodsByTestSuiteRunId", Method.class)
					.setParameter("testSuiteRunId", testSuiteRunId)
					.getResultList();
	}
}
