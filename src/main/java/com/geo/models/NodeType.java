package com.geo.models;

public enum NodeType {

	NODE("NODE"), LEAF("LEAF");

	private final String value;

	NodeType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
