package model;

public class CommitMessage {

	private String rawText;

	public CommitMessage(String rawText) {
		this.rawText = rawText;
	}

	public String getRawText() {
		return rawText;
	}

	public void setRawText(String rawText) {
		this.rawText = rawText;
	}

	public String getTrimmedText() {
		String trimStr = rawText.trim();
		return trimStr;
	}

	public int getLength() {
		String trimmedText = getTrimmedText();
		int len = trimmedText.length();
		return len;
	}
}
