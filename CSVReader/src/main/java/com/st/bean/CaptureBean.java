package com.stride.bean;

import java.util.Map;

/**
 * @author Badrinath Bhalkiker
 * Bean class for setting and getting the type and chocos[map] 
 */
public class CaptureBean {

	private String type;
	private Map<String, Integer> chocos;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, Integer> getChocos() {
		return chocos;
	}

	public void setChocos(Map<String, Integer> map) {
		this.chocos = map;
	}
}