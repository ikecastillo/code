package com.dt.jira.plugin.innotas.service;

/**
 * @author sriram.rajaraman
 *
 */
public class InnotasProjectModel {
	public InnotasProjectModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @param id
	 * @param name
	 * @param buId
	 * @param bu
	 * @param sbuId
	 * @param sbu
	 * @param programId
	 * @param program
	 * @param statusId
	 * @param status
	 */
	public InnotasProjectModel(String id, String name, String buId, String bu, String sbuId, String sbu,
			String program) {
		super();
		this.id = id;
		this.name = name;
		this.buId = buId;
		this.bu = bu;
		this.sbuId = sbuId;
		this.sbu = sbu;
		this.program = program;
	}
	private String id;
	private String name;
	private String buId;
	private String bu;
	private String sbuId;
	private String sbu;
	private String program;
	private String programId;
	private String statusId;
	private String status;	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the buId
	 */
	public String getBuId() {
		return bu;
	}
	/**
	 * @param buId the buId to set
	 */
	public void setBuId(String buId) {
		this.buId = buId;
	}
	/**
	 * @return the bu
	 */
	public String getBu() {
		return bu;
	}
	/**
	 * @param bu the bu to set
	 */
	public void setBu(String bu) {
		this.bu = bu;
	}
	/**
	 * @return the sbuId
	 */
	public String getSbuId() {
		return sbuId;
	}
	/**
	 * @param sbuId the sbuId to set
	 */
	public void setSbuId(String sbuId) {
		this.sbuId = sbuId;
	}
	/**
	 * @return the sbu
	 */
	public String getSbu() {
		return sbu;
	}
	/**
	 * @param sbu the sbu to set
	 */
	public void setSbu(String sbu) {
		this.sbu = sbu;
	}
	/**
	 * @return the program
	 */
	public String getProgram() {
		return program;
	}
	/**
	 * @param program the program to set
	 */
	public void setProgram(String program) {
		this.program = program;
	}
	

}
