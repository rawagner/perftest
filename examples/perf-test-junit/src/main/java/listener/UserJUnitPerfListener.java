package listener;

import org.jboss.perf.test.client.listener.junit.BasePerfListener;

/*
 * User defined JUnit listener using UserConfig instance.
 */
public class UserJUnitPerfListener extends BasePerfListener {
	public UserJUnitPerfListener() {
		initialize(new UserConfig());
	}
}
