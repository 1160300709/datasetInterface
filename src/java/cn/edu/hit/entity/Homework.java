package cn.edu.hit.entity;

import java.sql.Timestamp;

public class Homework {
	private Timestamp date;
	private Timestamp deadline;
	private String answer;
	private int cutoff;
	
	/**
	 *存储Homework的所有信息
	 * @param cutoff  0未截止     1已经截止
	 */
	public Homework(Timestamp date, Timestamp deadline, String answer, int cutoff) {
		this.date = date;
		this.deadline = deadline;
		this.answer = answer;
		this.cutoff = cutoff;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp startTime) {
		this.date = startTime;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getCutoff() {
		return cutoff;
	}

	public void setCutoff(int cutoff) {
		this.cutoff = cutoff;
	}

}
