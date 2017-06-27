package com.fine.finedt.baidu.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Electric entity. @author MyEclipse Persistence Tools
 */
public class Electric implements java.io.Serializable {

	private static final long serialVersionUID = -8951779360748971055L;
	private Integer eid;
	private String ename;
	private Set<Latlngs> latlngses = new HashSet<Latlngs>();

	// Constructors

	/** default constructor */
	public Electric() {
	}

	/** minimal constructor */
	public Electric(Integer eid) {
		this.eid = eid;
	}

	/** full constructor */
	public Electric(Integer eid, String ename, Set<Latlngs> latlngses) {
		this.eid = eid;
		this.ename = ename;
		this.latlngses = latlngses;
	}


	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Set<Latlngs> getLatlngses() {
		return this.latlngses;
	}

	public void setLatlngses(Set<Latlngs> latlngses) {
		this.latlngses = latlngses;
	}

}