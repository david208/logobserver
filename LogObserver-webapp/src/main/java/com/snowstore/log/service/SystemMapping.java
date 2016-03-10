package com.snowstore.log.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@ManagedResource
@Component
public class SystemMapping {

	private Lock lock = new ReentrantLock();

	private final Map<String, String> systemCodeMapAppName = new HashMap<String, String>();

	@ManagedAttribute
	public Map<String, String> getMap() {
		return systemCodeMapAppName;
	}

	@ManagedOperation
	public void addMap(String systemCode, String systemName) {
		lock.lock();
		systemCodeMapAppName.put(systemCode, systemName);
		lock.unlock();
	}

	@ManagedOperation
	public String findAppNameBySystemCode(String systemCode) {
		if (CollectionUtils.isEmpty(systemCodeMapAppName)) {
			lock.lock();
			if (CollectionUtils.isEmpty(systemCodeMapAppName)) {
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
				systemCodeMapAppName.put("2020", "mars-callback");
				systemCodeMapAppName.put("2019", "diana-web");
				systemCodeMapAppName.put("2021", "sisyphus");
				systemCodeMapAppName.put("2022", "spiders");
				systemCodeMapAppName.put("2017", "hermes-tt");
				systemCodeMapAppName.put("2023", "nox-web");
				systemCodeMapAppName.put("2024", "nox-console");
				systemCodeMapAppName.put("2025", "nox-web-html5");
				systemCodeMapAppName.put("2026", "poseidon-mobile");
				systemCodeMapAppName.put("3003", "cupid");
				systemCodeMapAppName.put("3002", "venus");
				systemCodeMapAppName.put("2020", "mars-callback");
				systemCodeMapAppName.put("2011", "tpp-job");
			}
			lock.unlock();
		}

		return systemCodeMapAppName.get(StringUtils.substring(systemCode, 0, 4));

	}
}
