package cn.edu.hit.entity;

import java.util.List;
/**
 * 返回该次作业的所有学生的答案以及标准答案
 *
 */
public class Answer {
	
	private String standardAnswer;
	private List<String> studentAnswer;
	private List<Integer> studentID;
	
	/**
	 * 这里解释一下，StudentID和StudentAnswer是一一对应的
	 * 比如说，学号为1001的学生在studentID中index为9，那么
	 * 他的answer就存储在studentAnswer.get(9)中
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