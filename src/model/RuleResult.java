package model;

public class RuleResult {

	private String ruleName;
	private boolean violated;
	private int penalty;
	private String reason;

	public RuleResult(String ruleName, boolean violated, int penalty, String reason) {
		this.ruleName = ruleName;
		this.violated = violated;
		this.penalty = penalty;
		this.reason = reason;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public boolean isViolated() {
		return violated;
	}

	public void setViolated(boolean violated) {
		this.violated = violated;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
