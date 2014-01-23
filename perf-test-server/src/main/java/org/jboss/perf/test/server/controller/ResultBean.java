package org.jboss.perf.test.server.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;

import org.jboss.perf.test.server.controller.objects.SummaryData;
import org.jboss.perf.test.server.dao.AttrResultDao;
import org.jboss.perf.test.server.dao.ResultDao;
import org.jboss.perf.test.server.dao.TestRunDao;
import org.jboss.perf.test.server.model.AttrResult;
import org.jboss.perf.test.server.model.Result;
import org.jboss.perf.test.server.model.TestRun;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;


@ManagedBean
@RequestScoped
public class ResultBean {
	@EJB
	private TestRunDao testRunDao;
	@EJB
	private ResultDao resultDao;
	@EJB
	private AttrResultDao attrResultDao;

	private Long testRunId;
	private TestRun testRun;
	private List<AttrResult> testRunAttrResults = null;
	private List<Result> results = null;
	private SummaryData summaryData;
	private CartesianChartModel model;
	
	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		testRunId = Long.parseLong(fc.getExternalContext().getRequestParameterMap().get("testrunid"));
		testRun = testRunDao.getTestRunById(testRunId);
		testRunAttrResults = attrResultDao.getAttrResultByTestRun(testRunId);
		summaryData = new SummaryData();
	}
	
	public TestRun getTestRun() {
		return testRun;
	}
	
	public void initSummaryData(long attrResultId) {
		summaryData.setMean(resultDao.getAvgAttributeResultsValue(attrResultId));
		summaryData.setMin(resultDao.getMinAttributeResultsValue(attrResultId));
		summaryData.setMax(resultDao.getMaxAttributeResultsValue(attrResultId));
		summaryData.setStddev(resultDao.getStdDevAttributeResultsValue(attrResultId));
	}
	
	public SummaryData getSummaryData() {
		return summaryData;
	}
	
	public List<AttrResult> getTestRunAttrResults() {
		return testRunAttrResults;
	}
	
	public CartesianChartModel getModel() {
        return model;
    }
	
	public List<Result> getResults() {
		// this method must be called after createGraph() method
		return results;
	}
	@Transient
	public int getResultsSize() {
		// this method must be called after createGraph() method
		return results != null ? results.size() : 0;
	}
	public Double createGraph(AttrResult attrResult) {
        model = new CartesianChartModel();
        LineChartSeries series = new LineChartSeries();
        series.setLabel(attrResult.getAttr().getName());
        // these results are also used for rendering the table
        results = resultDao.getAttributeResultsOrderById(attrResult.getId());        
		for(Result item: results) {
        	series.set(item.getDatetime(), item.getValue());
        }
		model.addSeries(series);	
        return null;
    }
}
