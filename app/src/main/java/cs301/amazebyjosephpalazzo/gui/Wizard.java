package cs301.amazebyjosephpalazzo.gui;

import cs301.amazebyjosephpalazzo.generation.CardinalDirection;
import cs301.amazebyjosephpalazzo.gui.Robot.Direction;
import cs301.amazebyjosephpalazzo.generation.Distance;
import cs301.amazebyjosephpalazzo.gui.Robot.Turn;
import java.util.Arrays;


public class Wizard implements RobotDriver{
	private int pathLength = 0;
	private int width;
	private int height;
	private Distance dist;
	private float Battery;
	private Robot robot;
	private Controller controller;
	
	public Wizard() {
		setDist(null);
		pathLength = 0;
		this.controller = new Controller();
	
	}
	
	@Override
	public void setRobot(Robot r) {
		assert r != null;
		this.robot = r;
		setBattery(robot.getBatteryLevel());
	}

	@Override
	public void setDimensions(int width, int height) {
		this.setHeight(height);
		this.setWidth(width);
	}

	@Override
	public void setDistance(Distance distance) {
		this.setDist(distance);	
	}

	
	@Override
	public boolean drive2Exit() throws Exception {
		
		
		return true;
	}
	


	@Override
	public float getEnergyConsumption() {
		return 3000 - robot.getBatteryLevel();
	}

	@Override
	public int getPathLength() {
		return pathLength;
	}

	public float getBattery() {
		return Battery;
	}

	public void setBattery(float battery) {
		Battery = battery;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	public Distance getDist() {
		return dist;
	}

	public void setDist(Distance dist) {
		this.dist = dist;
	}


}
