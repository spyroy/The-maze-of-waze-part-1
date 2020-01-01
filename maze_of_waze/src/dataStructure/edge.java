package dataStructure;

import java.io.Serializable;

public class edge implements edge_data,Serializable {

	private int src, dest, tag;
	private double weight;
	private String info;

	public edge(int src, int dest, double w) {
		this.src = src;
		this.dest = dest;
		this.tag = 0;
		this.weight = w;
		this.info = "";
	}

	protected edge(edge e) {
		this.src = e.src;
		this.dest = e.dest;
		this.tag = e.tag;
		this.weight = e.weight;
		this.info = e.info;
	}

	@Override
	public int getSrc() {
		return this.src;
	}

	@Override
	public int getDest() {
		return this.dest;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}

}
