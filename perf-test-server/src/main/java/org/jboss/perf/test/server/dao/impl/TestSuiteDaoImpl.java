package org.jboss.perf.test.server.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.perf.test.server.dao.TestSuiteDao;
import org.jboss.perf.test.server.model.TestSuite;



@Stateless
public class TestSuiteDaoImpl implements TestSuiteDao {
	@Inject
	private EntityManager em;

	@Override
	public TestSuite save(TestSuite testSuite) {
		return em.merge(testSuite);
	}
	
	@Override
	public TestSuite getTestSuiteByNameAndByProjectAndByBuild(String testSuite, String project, String build) {
		List<TestSuite> testSuites = em.createNamedQuery("testSuiteByNameAndByProjectAndByBuild", TestSuite.class)
										.setParameter("testSuite", testSuite)
										.setParameter("project", project)
										.setParameter("build", build)
										.setMaxResults(2)
										.getResultList();
		
		return testSuites.isEmpty()? null : testSuites.get(0); 		
	}
	
	@Override
	public TestSuite getTestSuiteByNameAndByBuildId(String testSuite, Long buildId) {
		List<TestSuite> testSuites = em.createNamedQuery("testSuiteByNameAndByBuildId", TestSuite.class)
				.setParameter("testSuite", testSuite)
				.setParameter("buildId", buildId)
				.setMaxResults(2)
				.getResultList();

		return testSuites.isEmpty()? null : testSuites.get(0);
	}
	
	@Override
	public List<String> getUniqueTestSuiteNamesByProjectId(long projectId) {
		return em.createNamedQuery("uniqueTestSuiteNamesByProjectId", String.class)
					.setParameter("projectId", projectId)
					.getResultList();
	}
}
