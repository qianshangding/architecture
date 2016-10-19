package com.architecture.third.druid.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.druid.filter.logging.Log4jFilterMBean;
import com.alibaba.druid.filter.logging.LogFilter;

/**
 * @FileName Log4j2Filter.java
 * @Description:
 * @Date 2016年3月31日 下午3:07:05
 * @author Fish Exp
 * @version V1.0
 */
public class Log4j2Filter extends LogFilter implements Log4jFilterMBean {

	private Logger dataSourceLogger = LogManager.getLogger(dataSourceLoggerName);
	private Logger connectionLogger = LogManager.getLogger(connectionLoggerName);
	private Logger statementLogger = LogManager.getLogger(statementLoggerName);
	private Logger resultSetLogger = LogManager.getLogger(resultSetLoggerName);

	@Override
	public String getDataSourceLoggerName() {
		return dataSourceLoggerName;
	}

	@Override
	public void setDataSourceLoggerName(String dataSourceLoggerName) {
		this.dataSourceLoggerName = dataSourceLoggerName;
		dataSourceLogger = LogManager.getLogger(dataSourceLoggerName);
	}

	public void setDataSourceLogger(Logger dataSourceLogger) {
		this.dataSourceLogger = dataSourceLogger;
		this.dataSourceLoggerName = dataSourceLogger.getName();
	}

	@Override
	public String getConnectionLoggerName() {
		return connectionLoggerName;
	}

	@Override
	public void setConnectionLoggerName(String connectionLoggerName) {
		this.connectionLoggerName = connectionLoggerName;
		connectionLogger = LogManager.getLogger(connectionLoggerName);
	}

	public void setConnectionLogger(Logger connectionLogger) {
		this.connectionLogger = connectionLogger;
		this.connectionLoggerName = connectionLogger.getName();
	}

	@Override
	public String getStatementLoggerName() {
		return statementLoggerName;
	}

	@Override
	public void setStatementLoggerName(String statementLoggerName) {
		this.statementLoggerName = statementLoggerName;
		statementLogger = LogManager.getLogger(statementLoggerName);
	}

	public void setStatementLogger(Logger statementLogger) {
		this.statementLogger = statementLogger;
		this.statementLoggerName = statementLogger.getName();
	}

	@Override
	public String getResultSetLoggerName() {
		return resultSetLoggerName;
	}

	@Override
	public void setResultSetLoggerName(String resultSetLoggerName) {
		this.resultSetLoggerName = resultSetLoggerName;
		resultSetLogger = LogManager.getLogger(resultSetLoggerName);
	}

	public void setResultSetLogger(Logger resultSetLogger) {
		this.resultSetLogger = resultSetLogger;
		this.resultSetLoggerName = resultSetLogger.getName();
	}

	@Override
	public boolean isConnectionLogErrorEnabled() {
		return connectionLogger.isEnabled(Level.ERROR) && super.isConnectionLogErrorEnabled();
	}

	@Override
	public boolean isDataSourceLogEnabled() {
		return dataSourceLogger.isDebugEnabled() && super.isDataSourceLogEnabled();
	}

	@Override
	public boolean isConnectionLogEnabled() {
		return connectionLogger.isDebugEnabled() && super.isConnectionLogEnabled();
	}

	@Override
	public boolean isStatementLogEnabled() {
		return statementLogger.isDebugEnabled() && super.isStatementLogEnabled();
	}

	@Override
	public boolean isResultSetLogEnabled() {
		return resultSetLogger.isDebugEnabled() && super.isResultSetLogEnabled();
	}

	@Override
	public boolean isResultSetLogErrorEnabled() {
		return resultSetLogger.isEnabled(Level.ERROR) && super.isResultSetLogErrorEnabled();
	}

	@Override
	public boolean isStatementLogErrorEnabled() {
		return statementLogger.isEnabled(Level.ERROR) && super.isStatementLogErrorEnabled();
	}

	@Override
	protected void connectionLog(String message) {
		connectionLogger.debug(message);
	}

	@Override
	protected void statementLog(String message) {
		statementLogger.debug(message);
	}

	@Override
	protected void resultSetLog(String message) {
		resultSetLogger.debug(message);
	}

	@Override
	protected void resultSetLogError(String message, Throwable error) {
		resultSetLogger.error(message, error);
	}

	@Override
	protected void statementLogError(String message, Throwable error) {
		statementLogger.error(message, error);
	}
}
