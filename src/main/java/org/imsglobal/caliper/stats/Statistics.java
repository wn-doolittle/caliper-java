package org.imsglobal.caliper.stats;


public class Statistics extends StatisticsMap {

	private static final long serialVersionUID = 5469315718941515883L;

	private static String MEASURE_KEY = "Measure";
	private static String DESCRIBE_KEY = "Describe";

	private static String SUCCESSFUL_KEY = "Successful";
	private static String FAILED_KEY = "Failed";

/**
	 * Returns the statistic representing how many 
	 * {@link Caliper#describe
	 * calls happened
	 * @return
	 */
	public Statistic getDescribes() {
		return ensure(DESCRIBE_KEY);
	}

	public void updateDescribes(double val) {
		update(MEASURE_KEY, val);
	}

	public Statistic getMeasures() {
		return ensure(MEASURE_KEY);
	}

	public void updateMeasures(double val) {
		update(MEASURE_KEY, val);
	}

	public Statistic getSuccessful() {
		return ensure(SUCCESSFUL_KEY);
	}

	public void updateSuccessful(double val) {
		update(SUCCESSFUL_KEY, val);
	}

	public Statistic getFailed() {
		return ensure(FAILED_KEY);
	}

	public void updateFailed(double val) {
		update(FAILED_KEY, val);
	}
}