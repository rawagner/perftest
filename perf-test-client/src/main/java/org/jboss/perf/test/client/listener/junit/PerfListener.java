package org.jboss.perf.test.client.listener.junit;

import org.jboss.perf.test.client.config.Config;

public class PerfListener extends BasePerfListener {
	public PerfListener() {
		initialize(new Config());
	}
}
