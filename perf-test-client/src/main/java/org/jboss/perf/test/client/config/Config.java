package org.jboss.perf.test.client.config;

import org.hyperic.sigar.SigarException;
import org.jboss.perf.test.client.attrs.Att;
import org.jboss.perf.test.client.constants.A;
import org.jboss.perf.test.client.constants.U;

public class Config extends BaseConfig {
	
	public Config() {
		// attributes CPU_TIME and JVM_MEMORY_DELTA are implicitly measured
		
		addAutoAttribute(new Att(A.JVM_MEMORY, U.KB) {
			@Override
			public double gainValue() {
				return (double) ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / KB_CONST);
			}
		});
		
		// including buffers
		addAutoAttribute(new Att(A.SYS_MEMORY, U.KB) {
			@Override
			public double gainValue() {
				double value = 0.0;
				try {
					value = sigar.getMem().getUsed() / KB_CONST;
				} catch (SigarException e) {
					e.printStackTrace();
				}
				return value;
			}
		});
	}
}