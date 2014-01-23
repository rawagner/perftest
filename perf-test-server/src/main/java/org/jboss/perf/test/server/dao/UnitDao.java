package org.jboss.perf.test.server.dao;

import java.util.List;

import org.jboss.perf.test.server.model.Unit;


public interface UnitDao {
	public List<Unit> getUnits();
	public Unit getUnitByName(String unitName);
	public void save(Unit unit);
	public void remove(Unit unit);
}
