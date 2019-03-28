package cn.edu.hit.entity;

import java.util.List;
/**
 * Answer�࣬�洢��
 *
 */
public class Answer {
	
	private String standardAnswer;
	private List<String> studentAnswer;
	private List<Integer> studentID;
	
	/**
	 * ����studentID��studentAnswerһһ��Ӧ
	 */
	public Answer(String standardAnswer, List<Integer> studentID, List<String> studentAnswer) {
		this.studentID = studentID;
		this.standardAnswer = standardAnswer;
		this.studentAnswer = studentAnswer;
	}
	
	public String getStandardAnswer() {
		return standardAnswer;
	}

	public void setStandardAnswer(String standardAnswer) {
		this.standardAnswer = standardAnswer;
	}

	public List<String> getStudentAnswer() {
		return studentAnswer;
	}

	public void setStudentAnswer(List<String> studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	public List<Integer> getStudentID() {
		return studentID;
	}

	public void setStudentID(List<Integer> studentID) {
		this.studentID = studentID;
	}
}