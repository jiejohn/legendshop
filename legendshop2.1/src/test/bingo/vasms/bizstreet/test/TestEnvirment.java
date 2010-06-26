package bingo.vasms.bizstreet.test;

import bingosoft.jcf.util.EnvironmentConfig;

public class TestEnvirment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = EnvironmentConfig.getInstance().getPropertyValue("config/common.properties", "path");
		System.out.println(path);
	}

}
