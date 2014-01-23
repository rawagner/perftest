package org.jboss.perf.test.server.dao;

import java.util.List;

import org.jboss.perf.test.server.model.Method;


public interface MethodDao {
	public Method save(Method method);
	public Method getMethodByName(String method);
	public Method getMethodById(long methodId);
	public List<Method> getAllMethodsByTestSuiteRunId(Long testSuiteRunId);
}
