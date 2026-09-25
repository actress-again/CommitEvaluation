package rule.impl;

import java.util.Map;

import model.CommitMessage;
import model.RuleResult;
import rule.CommitRule;
import rule.RuleConfig;

public class MaxLengthRule implements CommitRule {

	private RuleConfig config;

	@Override
	public String getKey() {
		return "max_length";
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
		int maxLen = Integer.parseInt(valStr);
		int len = message.getLength();

		if (len > maxLen) {
			int penalty = config.getPenalty();
			String reason = "[最大文字数] " + maxLen + "文字を超えるメッセージ";
			RuleResult result = new RuleResult(getKey(), true, penalty, reason);
			return result;
		}

		RuleResult result = new RuleResult(getKey(), false, 0, "");
		return result;
	}
}
