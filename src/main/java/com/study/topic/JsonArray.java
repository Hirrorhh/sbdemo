package com.study.topic;

import java.util.ArrayList;

public class JsonArray {
	private ArrayList<Object> array;
	
	public JsonArray() {
		this.array = new ArrayList<Object>();
	}
	
	public void add(Object object) {
		this.array.add(object);
	}

	public void add(int index, Object object) {
		this.array.add(index, object);
	}

	public int size() {
		return this.array.size();
	}
	
	protected ArrayList<Object> getInternalArray() {
		return this.array;
	}
}
