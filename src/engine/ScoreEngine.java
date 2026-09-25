package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.CommitMessage;
import model.EvaluationResult;
import model.RuleResult;
import rule.CommitRule;
import rule.RuleConfig;

public class ScoreEngine {

	private List<CommitRule> rules;

	public ScoreEngine() {
		rules = new ArrayList<CommitRule>();
	}

	public List<CommitRule> getRules() {
		return rules;
	}

	public void setRules(List<CommitRule> rules) {
		this.rules = rules;
	}

	public void registerRule(CommitRule rule) {
		rules.add(rule);
	}

	public EvaluationResult execute(
			CommitMessage message,
			Map<String, RuleConfig> configs) {

		EvaluationResult result = new EvaluationResult();

		for (CommitRule rule : rules) {

			String key = rule.getKey();

			RuleConfig config = configs.get(key);

			if (config != null) {

				rule.setConfig(config);

				if (rule.isEnabled()) {

					RuleResult ruleResult = rule.evaluate(message);

					result.addResult(ruleResult);
				}
			}
		}

		return result;
	}

}
