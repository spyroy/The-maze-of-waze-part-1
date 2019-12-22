package dataStructure;

import utils.Point3D;

public class node implements node_data {
	private int key, tag;
	private Point3D location;
	private double weight;
	private String info;

	public node () {
		key = 0;
		tag=0;
		weight=0;
		info="";
	}
	
	protected node (node n) {
		key=n.key;
		tag=n.tag;
		weight=n.weight;
		info=n.info;
		location=n.location;
	}
	
	public node (int key ,Point3D location, double weight) {
		this.key=key;
		this.location=location;
		tag=0;
		this.weight=weight;
		info="";
	}
	
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = p;

	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
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
