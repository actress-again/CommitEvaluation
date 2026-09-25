package rule.impl;

import model.CommitMessage;
import model.RuleResult;
import rule.CommitRule;
import rule.RuleConfig;

public class NoEndingPeriodRule implements CommitRule {

	private RuleConfig config;

	@Override
	public String getKey() {
		return "no_ending_period";
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
		String txt = message.getTrimmedText();
		if (txt.endsWith(".")) {
			int penalty = config.getPenalty();
			String reason = "[末尾ピリオド] 文末の '.' は不要";
			RuleResult result = new RuleResult(getKey(), true, penalty, reason);
			return result;
		}

		RuleResult result = new RuleResult(getKey(), false, 0, "");
		return result;
	}
}
