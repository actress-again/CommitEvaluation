package model;

import java.util.ArrayList;
import java.util.List;

public class EvaluationResult {

	private static int initialScore = 100;
	private List<RuleResult> results;

	public EvaluationResult() {
		this.results = new ArrayList<>();
	}

	public List<RuleResult> getResults() {
		return results;
	}

	public void setResults(List<RuleResult> results) {
		this.results = results;
	}

	public void addResult(RuleResult result) {
		results.add(result);
	}

	public int calculateFinalScore() {
		int score = initialScore;

		for (RuleResult result : results) {
			if (result.isViolated()) {
				score -= result.getPenalty();
			}
		}

		if (score < 0) {
			score = 0;
		}

		return score;
	}

	public String getFormattedOutput() {
		String lineItems = "";

		for (RuleResult result : results) {
			if (result.isViolated()) {
				String reason = result.getReason();
				int penalty = result.getPenalty();
				lineItems = lineItems + " " + reason + " (-" + penalty + "点)\n";
			}
		}

		int finalScore = calculateFinalScore();

		String output = "==========================================\n"
				+ "採点レポート\n"
				+ "==========================================\n"
				+ "[減点項目]\n"
				+ lineItems
				+ "------------------------------------------\n"
				+ " 総合スコア: " + finalScore + " 点 / 100 点\n"
				+ "==========================================\n";
		return output;
	}
}
