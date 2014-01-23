package listener;

import org.jboss.perf.test.client.listener.testng.BasePerfListener;

/*
 * User defined TestNG listener using UserConfig instance.
 */
public class UserPerfTestNGListener extends BasePerfListener {
	public UserPerfTestNGListener() {
		initialize(new UserConfig());
	}
}
