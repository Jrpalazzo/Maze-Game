package cs301.amazebyjosephpalazzo.gui;

import cs301.amazebyjosephpalazzo.generation.Distance;
import cs301.amazebyjosephpalazzo.gui.Constants.UserInput;
import cs301.amazebyjosephpalazzo.gui.Robot.Turn;

/**
 *  The ManualDriver Class implements the RobotDriver interface, the ManualDriver object is created inside the MazeApplication.
 *  The controller is set inside this class so we can influence the SimpleKeyListener class. 
 *  Keydown is used to redirect commands from manualDriver instead of mazeController commands. Keydown uses 
 *  UserInput Keys to then run BasicRobot's turn or move methods
 *  
 */
//@Author Joseph Palazzo
public class ManualDriver implements RobotDriver {
	
	public Robot robot;
	public float Battery;
	
	public int width;
	public int height;
	
	private int pathLength;
	private Controller c;
	
	public Distance dist;

	public ManualDriver()
	{
		robot  = new BasicRobot();
		pathLength = 0;
	}

	public ManualDriver(Controller controller){
		 this.c = controller;
	 }
	
	@Override
	public void setRobot(Robot r) {
		// TODO Auto-generated method stub
		
		assert r != null;
		robot = (BasicRobot)(r);
		Battery = robot.getBatteryLevel();
	}

	@Override
	public void setDimensions(int width, int height) {
		// TODO Auto-generated method stub
		this.height = height;
		this.width = width;
	}

	@Override
	public void setDistance(Distance distance) {
		// TODO Auto-generated method stub
		this.dist = distance;
	}

	@Override
	public boolean drive2Exit() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getEnergyConsumption() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPathLength() {
		// TODO Auto-generated method stub
		return pathLength;
	}
	
	//Gets the key inputs from SimpleKeyListener and runs the appropriate 
	//turn or move
	public void keyDown(UserInput key){

		switch(key){
		case Up:
			robot.move(1, true);
			break;
		case Down:
			robot.rotate(Turn.AROUND);
			break;
		case Left:
			robot.rotate(Turn.LEFT);
			break;
		case Right:
			robot.rotate(Turn.RIGHT);
			break;
		default:
			break;
		}
}
        
       

}
