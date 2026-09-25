package rule;

import model.CommitMessage;
import model.RuleResult;

public interface CommitRule {

	String getKey(); //ルール名取得

	void setConfig(RuleConfig config); //各種設定set

	RuleResult evaluate(CommitMessage message); //判定実行

	boolean isEnabled(); //有効確認
}
