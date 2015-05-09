package game;

import java.util.HashMap;
import java.util.Map;

public class Question {
	private String question;
	private String category;
	private String correctAnswer;
	private String additionalAnswer2;
	private String additionalAnswer3;
	private String additionalAnswer4;

	public Question () {
		
	}

	public Question(String question, String correctAnswer, 
			String additionalAnswer2, String additionalAnswer3, 
			String additionalAnswer4, SquareCategory category) {
		this.question = question;
		this.category = category.toString();
		this.correctAnswer = correctAnswer;
		this.additionalAnswer2 = additionalAnswer2;
		this.additionalAnswer3 = additionalAnswer3;
		this.additionalAnswer4 = additionalAnswer4;
	}
	
	public Map<String, String> getAllQuestionInfo() {
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("question", question);
		ret.put("category", category);
		ret.put("correctAnswer", correctAnswer);
		ret.put("additionalAnswer2", additionalAnswer2);
		ret.put("additionalAnswer3", additionalAnswer3);
		ret.put("additionalAnswer4", additionalAnswer4);
		return ret;
	}
	
	public void setAllQuestionInfo(String question, String correctAnswer, 
			String additionalAnswer2, String additionalAnswer3, 
			String additionalAnswer4, String category) {
		this.question = question;
		this.category = category;
		this.correctAnswer = correctAnswer;
		this.additionalAnswer2 = additionalAnswer2;
		this.additionalAnswer3 = additionalAnswer3;
		this.additionalAnswer4 = additionalAnswer4;
	}
	
	public String toString() {
		return question;
	}

	public String getQuestion() {
		return question;
	}

	public String getCategory() {
		return category;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public String getAdditionalAnswer2() {
		return additionalAnswer2;
	}

	public String getAdditionalAnswer3() {
		return additionalAnswer3;
	}

	public String getAdditionalAnswer4() {
		return additionalAnswer4;
	}

}
