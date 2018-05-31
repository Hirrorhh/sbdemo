package com.study.topic;

import java.util.HashMap;

public class JsonObject {
	private HashMap<String, Object> map;
	
	public Object data;
	
	public JsonObject() {
		this.map = new HashMap<String, Object>();
	}
	
	public void set(String key, Object value) {
		this.map.put(key, value);
	}

	public Object get(String key) {
		return this.map.get(key);
	}

	protected HashMap<String, Object> getInternalMap() {
		return this.map;
	}
}
