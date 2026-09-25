package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import rule.RuleConfig;

public class RuleRepository {

	private static String filePath = "data/commit_rules.properties";

	public Map<String, RuleConfig> loadConfigs() throws IOException {
		Properties properties = new Properties();

		FileInputStream input = new FileInputStream(filePath);
		properties.load(new InputStreamReader(input, StandardCharsets.UTF_8));
		input.close();

		Map<String, RuleConfig> configs = new HashMap<>();

		for (String propertyName : properties.stringPropertyNames()) {
			String[] parts = propertyName.split("\\.");

			String ruleKey = parts[1];
			String attr = parts[2];
			String val = properties.getProperty(propertyName);

			RuleConfig conf;
			if (configs.containsKey(ruleKey)) {
				conf = configs.get(ruleKey);
			} else {
				conf = new RuleConfig();
				configs.put(ruleKey, conf);
			}

			if (attr.equals("enabled")) {
				boolean enabledFlg = Boolean.parseBoolean(val);
				conf.setEnabled(enabledFlg);
			} else if (attr.equals("penalty")) {
				int penaltyNum = Integer.parseInt(val);
				conf.setPenalty(penaltyNum);
			} else {
				Map<String, String> paramMap = conf.getParameter();
				paramMap.put(attr, val);
			}
		}

		return configs;
	}
}
