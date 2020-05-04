package com.sticsoft.automation.core;

public class Config {

	public static String defaultAdminUrl = "http://13.251.157.137/piz_admin/";
	public static String defaultFrontEndUrl = "http://13.251.157.137/piz_ui/";
	public static String defaultBrowser = "chrome";
	private static String reportsDir = getEnvVar("reports_dir");

	public static String getReportsDir() {
		if (reportsDir == null) {
			reportsDir = "reports/";
		}
		return reportsDir;
	}

	private static String getEnvVar(String name) {
		String value = System.getenv(name);
		value = value == null ? null : value.trim();
		if (value != null && !value.isEmpty()) {
			return value;
		}
		return null;
	}

}
