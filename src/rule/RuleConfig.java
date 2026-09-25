package rule;

import java.util.HashMap;
import java.util.Map;

public class RuleConfig {

	private boolean enabled; //有効切り替え
	private int penalty; //減点
	private Map<String, String> parameter; //設定

	public RuleConfig() {
		this.parameter = new HashMap<>();
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public Map<String, String> getParameter() {
		return parameter;
	}

	public void setParameter(Map<String, String> parameter) {
		this.parameter = parameter;
	}
}
