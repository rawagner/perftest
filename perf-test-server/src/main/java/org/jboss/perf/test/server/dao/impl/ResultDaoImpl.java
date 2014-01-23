package org.jboss.perf.test.server.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.jboss.perf.test.server.dao.ResultDao;
import org.jboss.perf.test.server.model.Result;
import org.jboss.perf.test.server.rest.RepRESTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Stateless
public class ResultDaoImpl implements ResultDao {
	private static final Logger logger = LoggerFactory.getLogger(ResultDaoImpl.class);
	@Inject
	private EntityManager em;

	private Double buildAndExecuteQuery(TypedQuery<Double> query, long testSuiteRunId, long methodId, Long attributeId) {
		return query.setParameter("testSuiteRunId", testSuiteRunId)
						.setParameter("methodId", methodId)
						.setParameter("attributeId", attributeId)
						.getSingleResult();
	}
	
	@Override
	public List<Result> getAttributeResultsOrderById(long attrResultId) {
		return em.createNamedQuery("attributeResultsOrderById", Result.class)
					.setParameter("attrResultId", attrResultId)
					.getResultList();
	}
	
	@Override
	public Double getAvgResultByTestSuiteRunIdAndByMethodIdAndByAttributeId(long testSuiteRunId,
			long methodId, long attributeId) {
	
		TypedQuery<Double> query = em.createNamedQuery("getAvgResultByTestSuiteRunIdAndByMethodIdAndByAttributeId", Double.class);
		return buildAndExecuteQuery(query, testSuiteRunId, methodId, attributeId);
	}
	
	@Override
	public Double getMinResultByTestSuiteRunIdAndByMethodIdAndByAttributeId(long testSuiteRunId,
			long methodId, long attributeId) {
		
		TypedQuery<Double> query = em.createNamedQuery("getMinResultByTestSuiteRunIdAndByMethodIdAndByAttributeId", Double.class);
		return buildAndExecuteQuery(query, testSuiteRunId, methodId, attributeId);
	}

	@Override
	public Double getMaxResultByTestSuiteRunIdAndByMethodIdAndByAttributeId(long testSuiteRunId,
			long methodId, long attributeId) {
		
		TypedQuery<Double> query = em.createNamedQuery("getMaxResultByTestSuiteRunIdAndByMethodIdAndByAttributeId", Double.class);
		return buildAndExecuteQuery(query, testSuiteRunId, methodId, attributeId);
	}
	
	@Override
	public Double getAvgAttributeResultsValue(long attrResultId) {
		return em.createNamedQuery("getAvgAttributeResultsValue", Double.class)
					.setParameter("attrResultId", attrResultId)
					.getSingleResult();
	}

	@Override
	public Double getMinAttributeResultsValue(long attrResultId) {
		return em.createNamedQuery("getMinAttributeResultsValue", Double.class)
					.setParameter("attrResultId", attrResultId)
					.getSingleResult();
	}

	@Override
	public Double getMaxAttributeResultsValue(long attrResultId) {
		return em.createNamedQuery("getMaxAttributeResultsValue", Double.class)
					.setParameter("attrResultId", attrResultId)
					.getSingleResult();
	}

	@Override
	public Double getStdDevAttributeResultsValue(long attrResultId) {
		try{
			return (Double) em.createNativeQuery("select stddev(value) from result where attrresult_id = :attrResultId")
					.setParameter("attrResultId", attrResultId)
					.getSingleResult();
		} catch (Throwable t){
			logger.warn("Unable to calculate STDDEV: " + t);
			return null;	
		}
	}
}
