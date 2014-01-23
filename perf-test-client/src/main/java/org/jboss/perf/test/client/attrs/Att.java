package org.jboss.perf.test.client.attrs;


public abstract class Att extends MAtt {
	public Att(String name, String unit) {
		super(name, unit);
	}

	public abstract double gainValue();
}
