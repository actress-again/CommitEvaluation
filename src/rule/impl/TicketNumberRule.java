package rule.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.CommitMessage;
import model.RuleResult;
import rule.CommitRule;
import rule.RuleConfig;

public class TicketNumberRule implements CommitRule {

	private RuleConfig config;

	@Override
	public String getKey() {
		return "ticket_number";
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
		String ptn = parameter.get("pattern");

		Pattern compiledPattern = Pattern.compile(ptn);
		Matcher m = compiledPattern.matcher(txt);

		if (!m.find()) {
			int penalty = config.getPenalty();
			String reason = "[チケット番号] チケット番号が含まれていない";
			RuleResult result = new RuleResult(getKey(), true, penalty, reason);
			return result;
		}

		RuleResult result = new RuleResult(getKey(), false, 0, "");
		return result;
	}
}
