package rule.impl;

import java.util.Map;

import model.CommitMessage;
import model.RuleResult;
import rule.CommitRule;
import rule.RuleConfig;

public class PrefixRule implements CommitRule {

	private RuleConfig config;

	@Override
	public String getKey() {
		return "prefix";
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
		Map<String, String> parameter = config.getParameter();
		String valuesParameter = parameter.get("values");
		String[] prefixArr = valuesParameter.split(",");

		for (String prefix : prefixArr) {
			if (txt.startsWith(prefix)) {
				RuleResult okResult = new RuleResult(getKey(), false, 0, "");
				return okResult;
			}
		}

		int penalty = config.getPenalty();
		String reason = "[プレフィックス] " + valuesParameter + " のいずれかで始まっていない";
		RuleResult ngResult = new RuleResult(getKey(), true, penalty, reason);
		return ngResult;
	}
}
