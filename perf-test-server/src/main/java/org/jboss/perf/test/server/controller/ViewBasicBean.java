package org.jboss.perf.test.server.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import org.jboss.perf.test.server.dao.AttrDao;
import org.jboss.perf.test.server.dao.ProjectDao;
import org.jboss.perf.test.server.dao.TestSuiteDao;
import org.jboss.perf.test.server.enums.Fce;
import org.jboss.perf.test.server.model.Attr;
import org.jboss.perf.test.server.model.Project;

public class ViewBasicBean {
	
	@EJB
	private ProjectDao projectDao;
	@EJB
	private TestSuiteDao testSuiteDao;
	@EJB
	private AttrDao attrDao;
	
	protected Long projectId;
	protected Project project;
	protected List<String> uniqueTestSuites;
	protected List<Attr> attributes;
	protected List<Fce> functions;
	
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		projectId = Long.parseLong(fc.getExternalContext().getRequestParameterMap().get("projectid"));
		project = projectDao.getProjectById(projectId);
		uniqueTestSuites = testSuiteDao.getUniqueTestSuiteNamesByProjectId(projectId);
		attributes = attrDao.getAttrs();
		functions = new LinkedList<Fce>(Arrays.asList(Fce.values()));
	}
	
	public Long getProjectId() {
		return projectId;
	}
	
	public Project getProject() {
		return project;
	}
	
	public List<String> getUniqueTestSuites() {
		return uniqueTestSuites;
	}
	
	public List<Attr> getAttributes() {
		return attributes;
	}
	
	public List<Fce> getFunctions() {
		return functions;
	}
}
