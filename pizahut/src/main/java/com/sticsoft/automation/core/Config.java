package com.sticsoft.automation.core;

public class Config {

	public static String qaAdminUrl = "http://pizzahutqa.suntechsolutions.us/piz_admin";
	public static String qaFrontEndUrl = "http://pizzahutqa.suntechsolutions.us/piz_ui";
	public static String uatAdminUrl = "http://pizzahutuat.suntechsolutions.us/piz_admin";
	public static String uatFrontEndUrl = "http://pizzahutuat.suntechsolutions.us/piz_ui";
	public static String prodAdminUrl = "https://admin.pizzahut.suntechsolutions.us/Login";
	public static String prodFrontEndUrl = "https://pizzahut.suntechsolutions.us/piz_ui";
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

	public static String getURL() {
		String env = getEnv();
		if (env.equalsIgnoreCase("uat")) {
			return uatFrontEndUrl;
		} else if (env.equalsIgnoreCase("prod")) {
			return prodFrontEndUrl;
		} else {
			return qaAdminUrl;
		}

	}

	public static String getEnv() {
		// TODO Auto-generated method stub
		String env = null;
		if (getEnvVar("env") == null || getEnvVar("env") == "qa" ) {
			env = "qa";
		} else {
			env = getEnvVar("env").equalsIgnoreCase("prod") ? "prod" : "uat";
		}
		return env;
	}

}
