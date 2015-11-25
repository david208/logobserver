package com.snowstore.log.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class SystemCodeUtil {

	private static final Map<String, String> systemCodeMapAppName = new HashMap<String, String>();

	static {
		systemCodeMapAppName.put("1005", "apollo");
		systemCodeMapAppName.put("2003", "fortune");
		systemCodeMapAppName.put("2006", "crm");
		systemCodeMapAppName.put("2007", "uc");
		systemCodeMapAppName.put("1007", "trident");
		systemCodeMapAppName.put("2002", "ares");
		systemCodeMapAppName.put("2010", "as");
		systemCodeMapAppName.put("2014", "jupiter");
		systemCodeMapAppName.put("2012", "juno");
		systemCodeMapAppName.put("1009", "atlantis");
		systemCodeMapAppName.put("1008", "neptune");
		systemCodeMapAppName.put("2004", "thalassa");
		systemCodeMapAppName.put("1004", "mars");
		systemCodeMapAppName.put("2016", "athene");
		systemCodeMapAppName.put("2009", "vesta");
		systemCodeMapAppName.put("2009", "vesta");
		systemCodeMapAppName.put("2015", "credit");
		systemCodeMapAppName.put("2010", "as");
		systemCodeMapAppName.put("2008", "tpp");
		systemCodeMapAppName.put("1002", "hera");
		systemCodeMapAppName.put("2001", "pluto");
		systemCodeMapAppName.put("1010", "terra");
		systemCodeMapAppName.put("1003", "prometheus");
		systemCodeMapAppName.put("1006", "logobserver");
		systemCodeMapAppName.put("2013", "uc-cas");
		systemCodeMapAppName.put("2018", "diana-console");
		systemCodeMapAppName.put("2017", "mars-callback");
		systemCodeMapAppName.put("2019", "diana-web");
	}

	public static String findAppNameBySystemCode(String systemCode) {

		return systemCodeMapAppName.get(StringUtils.substring(systemCode, 0, 4));

	}
}
