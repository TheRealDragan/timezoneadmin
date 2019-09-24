package com.petar.timezoneadmin.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Timezone {
	private Long id;
	private Long userId;
	private String name;
	private String nameOfCity;
	@Min(-720)
	@Max(720)
	private Integer gmtOffsetInMinutes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameOfCity() {
		return nameOfCity;
	}
	public void setNameOfCity(String nameOfCity) {
		this.nameOfCity = nameOfCity;
	}
	public Integer getGmtOffsetInMinutes() {
		return gmtOffsetInMinutes;
	}
	public void setGmtOffsetInMinutes(Integer gmtOffsetInMinutes) {
		this.gmtOffsetInMinutes = gmtOffsetInMinutes;
	}
	
}
