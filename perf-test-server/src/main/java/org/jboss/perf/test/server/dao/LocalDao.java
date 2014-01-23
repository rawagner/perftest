package org.jboss.perf.test.server.dao;

import java.util.List;

import org.jboss.perf.test.server.model.Local;

public interface LocalDao {
	List<Local> getLocalsByProjectId(long projectId);
}
