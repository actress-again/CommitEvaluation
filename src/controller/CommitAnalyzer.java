package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import config.RuleRepository;
import engine.ScoreEngine;
import model.CommitMessage;
import model.EvaluationResult;
import rule.RuleConfig;
import rule.impl.MaxLengthRule;
import rule.impl.MinLengthRule;
import rule.impl.NgWordRule;
import rule.impl.NoEndingPeriodRule;
import rule.impl.PrefixRule;
import rule.impl.TicketNumberRule;

public class CommitAnalyzer {

	public static void main(String[] args) {

		int argCount = args.length;

		if (argCount < 1) {
			System.out.println("ファイルのパスが指定されていません");
			System.exit(1);
		}

		String messageFilePath = args[0];

		String inputText = null;

		try {
			inputText = Files.readString(Paths.get(messageFilePath));
		} catch (IOException e) {
			String errMsg = e.getMessage();
			System.out.println("読み込みに失敗しました: " + errMsg);
			System.exit(1);
		}

		CommitMessage message = new CommitMessage(inputText);

		RuleRepository repository = new RuleRepository();

		Map<String, RuleConfig> configs = null;

		try {
			configs = repository.loadConfigs();
		} catch (IOException e) {
			String errMsg2 = e.getMessage();
			System.out.println("設定の読み込みに失敗しました: " + errMsg2);
			System.exit(1);
		}

		ScoreEngine engine = new ScoreEngine();

		engine.registerRule(new NgWordRule());
		engine.registerRule(new MinLengthRule());
		engine.registerRule(new MaxLengthRule());
		engine.registerRule(new PrefixRule());
		engine.registerRule(new NoEndingPeriodRule());
		engine.registerRule(new TicketNumberRule());

		EvaluationResult result = engine.execute(message, configs);

		String output = result.getFormattedOutput();

		System.out.println();
		System.out.println(output);
	}
}