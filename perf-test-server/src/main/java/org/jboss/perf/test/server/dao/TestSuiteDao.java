package org.jboss.perf.test.server.dao;

import java.util.List;

import org.jboss.perf.test.server.model.TestSuite;


public interface TestSuiteDao {
	public TestSuite save(TestSuite testSuite);
	public TestSuite getTestSuiteByNameAndByProjectAndByBuild(String testSuite, String project, String build);
	public TestSuite getTestSuiteByNameAndByBuildId(String testSuite, Long buildId);
	public List<String> getUniqueTestSuiteNamesByProjectId(long projectId);
}
