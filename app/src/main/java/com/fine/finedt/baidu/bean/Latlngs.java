package com.fine.finedt.baidu.bean;

/**
 * Latlngs entity. @author MyEclipse Persistence Tools
 */
public class Latlngs implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 767005262035845840L;
	private Integer aid;
	private Double mlatitude;
	private Double mlongitude;
	private Integer mposition;

	// Constructors

	/** default constructor */
	public Latlngs() {
	}

	/** full constructor */
	public Latlngs(Integer aid, Double mlatitude,
			Double mlongitude, Integer mposition) {
		this.aid = aid;
		this.mlatitude = mlatitude;
		this.mlongitude = mlongitude;
		this.mposition = mposition;
	}

	// Property accessors

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Double getMlatitude() {
		return this.mlatitude;
	}

	public void setMlatitude(Double mlatitude) {
		this.mlatitude = mlatitude;
	}

	public Double getMlongitude() {
		return this.mlongitude;
	}

	public void setMlongitude(Double mlongitude) {
		this.mlongitude = mlongitude;
	}

	public Integer getMposition() {
		return this.mposition;
	}

	public void setMposition(Integer mposition) {
		this.mposition = mposition;
	}

}