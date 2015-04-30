package com.snowstore.log.service.esb.filter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.snowstore.log.service.esb.filter.LogFilter;
import com.snowstore.log.service.esb.filter.LogFilterChain;
import com.zendaimoney.hera.connector.vo.Datagram;

/**
 * @description: 过滤器链实现
 * @author sm
 */
@Component
public class LogFilterChainImp implements LogFilterChain {

	private List<? extends LogFilter> filters = new ArrayList<LogFilter>();

	public void setFilters(List<? extends LogFilter> filters) {
		this.filters = new ArrayList<LogFilter>(filters);
	}

	@Override
	public void doFilter(Datagram datagram) {
		new VirtualFilterChain(filters).doFilter(datagram);

	}

	/**
	 * @description: 虚拟过滤器链，实现真正的链式过滤.
	 * @author sm
	 */
	private static class VirtualFilterChain implements LogFilterChain {

		private List<? extends LogFilter> filters = new ArrayList<LogFilter>();

		private int currentPosition = 0;

		private VirtualFilterChain(List<? extends LogFilter> additionalFilters) {
			this.filters = additionalFilters;
		}

		@Override
		public void doFilter(Datagram datagram) {
			if (currentPosition == filters.size()) {
				return;
			} else {
				currentPosition++;
				LogFilter nextFilter = filters.get(currentPosition - 1);
				nextFilter.doFilter(datagram, this);
			}

		}
	}
}
