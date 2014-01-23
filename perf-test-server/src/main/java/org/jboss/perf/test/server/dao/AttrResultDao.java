package org.jboss.perf.test.server.dao;

import java.util.List;

import org.jboss.perf.test.server.model.AttrResult;


public interface AttrResultDao {
	public AttrResult save(AttrResult attrResult);
	public List<AttrResult> getAttrResultByTestRun(long testRunId);
	public AttrResult getFirstUsedAttrResultByAttrId(long attrId);
	public AttrResult getFirstUsedAttrResultByUnitId(long unitId);
	public List<AttrResult> getAttrResultByMethod(long methodId);
	public AttrResult getAttrResultByTestRunIdAndByAttrId(long testRunId, long attrId);
}
