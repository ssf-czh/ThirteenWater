package com.czh.pojo;

import java.util.ArrayList;
import java.util.List;


public class Choice {
	public List<Card> head = new ArrayList<Card>();  //??????
	public List<Card> mid = new ArrayList<Card>();//??????
	public List<Card> end = new ArrayList<Card>();//??????
	String headType,midType,endType;

	public List<Card> getHead() {
		return head;
	}

	public void setHead(List<Card> head) {
		this.head = head;
	}

	public List<Card> getMid() {
		return mid;
	}

	public void setMid(List<Card> mid) {
		this.mid = mid;
	}

	public List<Card> getEnd() {
		return end;
	}

	public void setEnd(List<Card> end) {
		this.end = end;
	}
}
