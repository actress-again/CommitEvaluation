package rule.impl;

import java.util.Map;

import model.CommitMessage;
import model.RuleResult;
import rule.CommitRule;
import rule.RuleConfig;

public class NgWordRule implements CommitRule {

	private RuleConfig config;

	@Override
	public String getKey() {
		return "ng_words";
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
		String trimmedText = message.getTrimmedText();
		String str = trimmedText.toLowerCase();

		Map<String, String> parameter = config.getParameter();
		String listParameter = parameter.get("list");
		String[] ngWordArr = listParameter.split(",");

		for (String ngWord : ngWordArr) {
			String ngWordLower = ngWord.toLowerCase();
			if (str.contains(ngWordLower)) {
				int penalty = config.getPenalty();
				String reason = "[NGワード] '" + ngWord + "' が使用されている";
				RuleResult result = new RuleResult(getKey(), true, penalty, reason);
				return result;
			}
		}

		RuleResult result = new RuleResult(getKey(), false, 0, "");
		return result;
	}
}
