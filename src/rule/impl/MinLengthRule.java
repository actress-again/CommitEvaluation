package rule.impl;

import java.util.Map;

import model.CommitMessage;
import model.RuleResult;
import rule.CommitRule;
import rule.RuleConfig;

public class MinLengthRule implements CommitRule {

	private RuleConfig config;

	@Override
	public String getKey() {
		return "min_length";
	}

	@Override
	public void setConfig(RuleConfig config) {
		this.config = config;
	}

	@Override
	public boolean isEnabled() {
		return config.isEnabled();
	}

	@Override
	public RuleResult evaluate(CommitMessage message) {
		Map<String, String> parameter = config.getParameter();
		String valStr = parameter.get("value");
		int minLen = Integer.parseInt(valStr);
		int len = message.getLength();

		if (len < minLen) {
			int penalty = config.getPenalty();
			String reason = "[最小文字数] " + minLen + "文字未満のメッセージ";
			RuleResult result = new RuleResult(getKey(), true, penalty, reason);
			return result;
		}

		RuleResult result = new RuleResult(getKey(), false, 0, "");
		return result;
	}
}
