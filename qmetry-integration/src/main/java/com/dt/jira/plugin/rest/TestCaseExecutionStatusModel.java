package com.dt.jira.plugin.rest;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCaseExecutionStatusModel {

	@XmlElement(name = "build")
	private String buildName;
	@XmlElement(name = "totdefects")
	private long totDefects;
	@XmlElement(name = "tottestcases")
	private long totTestCases;
	@XmlElement(name = "tottestcasesexec")
	private long totTestCasesExec;
	@XmlElement(name = "passed")
	private long passed;
	@XmlElement(name = "failed")
	private long failed;
	@XmlElement(name = "notrun")
	private long notRun;
	@XmlElement(name = "notapplicable")
	private long notApplicable;
	@XmlElement(name = "blocked")
	private long blocked;

	public TestCaseExecutionStatusModel() {
	}

	public TestCaseExecutionStatusModel(String buildName, long totDefects,
			long totTestCases, long totTestCasesExec, long passed, long failed,
			long notRun, long notApplicable, long blocked) {
		super();
		this.buildName = buildName;
		this.totDefects = totDefects;
		this.totTestCases = totTestCases;
		this.totTestCasesExec = totTestCasesExec;
		this.passed = passed;
		this.failed = failed;
		this.notRun = notRun;
		this.notApplicable = notApplicable;
		this.blocked = blocked;
	}

	public long getTotTestCasesExec() {
		return totTestCasesExec;
	}

	public void setTotTestCasesExec(long totTestCasesExec) {
		this.totTestCasesExec = totTestCasesExec;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public long getTotDefects() {
		return totDefects;
	}

	public void setTotDefects(long totDefects) {
		this.totDefects = totDefects;
	}

	public long getTotTestCases() {
		return totTestCases;
	}

	public void setTotTestCases(long totTestCases) {
		this.totTestCases = totTestCases;
	}

	public long getPassed() {
		return passed;
	}

	public void setPassed(long passed) {
		this.passed = passed;
	}

	public long getFailed() {
		return failed;
	}

	public void setFailed(long failed) {
		this.failed = failed;
	}

	public long getNotRun() {
		return notRun;
	}

	public void setNotRun(long notRun) {
		this.notRun = notRun;
	}

	public long getNotApplicable() {
		return notApplicable;
	}

	public void setNotApplicable(long notApplicable) {
		this.notApplicable = notApplicable;
	}

	public long getBlocked() {
		return blocked;
	}

	public void setBlocked(long blocked) {
		this.blocked = blocked;
	}

}