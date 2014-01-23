package org.jboss.perf.test.server.dao;

import java.util.List;

import org.jboss.perf.test.server.model.Attr;


public interface AttrDao {
	public List<Attr> getAttrs();
	public Attr getAttrByName(String attr);
	public Attr getAttrById(long attrId);
	public Attr getAttrByUnit(String unit);
	public void save(Attr attr);
	public void remove(Attr attr);
}
