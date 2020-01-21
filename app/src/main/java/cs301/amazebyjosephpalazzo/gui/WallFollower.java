package cs301.amazebyjosephpalazzo.gui;

import cs301.amazebyjosephpalazzo.generation.Distance;
import cs301.amazebyjosephpalazzo.gui.Robot.Direction;
import cs301.amazebyjosephpalazzo.gui.Robot.Turn;



public class WallFollower implements RobotDriver {
	private int pathLength=0;
	private int width;
	private int height;
	private Distance dist;
	private float Battery;
	private Robot robot;
	private Controller controller;
	
	public WallFollower() {
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
		//setMazeBoard(new int[width][height]);
	}

	@Override
	public void setDistance(Distance distance) {
		this.setDist(distance);	
	}

	@Override
	public boolean drive2Exit() throws Exception {
				
		while(!robot.isAtExit()) {
			
			if (robot.getBatteryLevel() == 0) {
				return false;
			}
			if (robot.distanceToObstacle(Direction.FORWARD) > 0 && robot.distanceToObstacle(Direction.LEFT) == 0) {
				robot.move(1, false);			}
			else {
				if (robot.distanceToObstacle(Direction.LEFT) > 0) {

					robot.rotate(Turn.LEFT); 
					robot.move(1, false);	
				}
				else if (robot.distanceToObstacle(Direction.RIGHT) > 0) {

					robot.rotate(Turn.RIGHT);
					robot.move(1, false);	
				}
				else {

					robot.rotate(Turn.AROUND);
					robot.move(1, false);	
				}
			}
						
		}
			
			
		checkExit();
		
		
		return true;
		
		
		
	}

	void checkExit() throws Exception {
		System.out.println("Ja, Papa");
		if (robot.canSeeExit(Direction.FORWARD)) {
			setEndGame();
		}
		else if (robot.canSeeExit(Direction.BACKWARD)) {
			setEndGame();
		}
		else if (robot.canSeeExit(Direction.RIGHT)) {
			setEndGame();
		}
		else if (robot.canSeeExit(Direction.LEFT)) {
			setEndGame();
		}
	}

	
	private void setEndGame() throws Exception {
		robot.isAtExit();
		controller.switchFromPlayingToWinning(pathLength);

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
