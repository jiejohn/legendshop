package com.done.util;

import java.util.Map;

import bingosoft.jcf.tag.TableCache;

public class CodeTablesCache implements TableCache{
	private Map<String, Map<String, String>> codeTables;
	
	public Map<String, Map<String, String>> getCodeTables() {
		return codeTables;
	}

	public void setCodeTables(Map<String, Map<String, String>> codeTables) {
		this.codeTables = codeTables;
	}

	public Map<String, String> getCodeTable(String beanName) {
		if (beanName == null || beanName.trim().length() == 0) {
			return null;
		}
		Map<String, String> table = codeTables.get(beanName);
		return table;
	}

}
