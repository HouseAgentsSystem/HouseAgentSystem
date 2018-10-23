package com.houseAgent.common.web;

import java.util.List;

public class ExtResultJson<T> {
	private List<T> lists;

	public ExtResultJson(List<T> lists) {
		this.lists = lists;
	}
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
}
