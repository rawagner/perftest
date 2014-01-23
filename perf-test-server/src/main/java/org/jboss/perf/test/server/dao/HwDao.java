package org.jboss.perf.test.server.dao;

import java.util.List;

import org.jboss.perf.test.server.model.Hw;


public interface HwDao {
	public List<Hw> getHws();
	public Hw getHwByName(String hw);
	public List<Hw> getUniqueHwsByProjectIdAndByBuildAndByTestSuite(long projectId, String build, String testSuite);
	public Hw save(Hw hw);
}
