package org.jboss.perf.test.client.listener.testng;

import org.jboss.perf.test.client.config.Config;

public class PerfListener extends BasePerfListener {
	public PerfListener() {
		initialize(new Config());
	}
}
