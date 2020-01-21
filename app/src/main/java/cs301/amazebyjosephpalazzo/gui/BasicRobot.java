package cs301.amazebyjosephpalazzo.gui;

import cs301.amazebyjosephpalazzo.generation.CardinalDirection;
import cs301.amazebyjosephpalazzo.generation.Cells;
import cs301.amazebyjosephpalazzo.gui.Constants.UserInput;
import cs301.amazebyjosephpalazzo.generation.MazeConfiguration;

/**
 * The BasicRobot Class implements the Robot interface, the BasicRobot object called inside the MazeApplication. The BasicRobot  setMaze is set in mazeController inside switch to switchToPlayingScreen() so that
 *  it receives the properties of a maze after it has been created by setting the controller to this.controller. 
 *  
 *  The BasicRobot Class takes inputs from the ManualDriver class for moving or rotating the robot.
 *  The rotate method takes a turn parameter and calls to the maze controller, which rotates the robot. The same applies to the move method. 
 *  The distanceToObstacle Method is used to see if there is an obstacle in from of the robot. 
 *  
 *
 */

//@author Joseph Palazzo
public class BasicRobot implements Robot {
	
	protected CardinalDirection curDir ;
	public static boolean hasStopped;

	protected StatePlaying playstate;
	protected Controller controller;
	protected static float batteryLevel;
	private static final float FULL_ROTATION = 12;
	public final int STEP_ENERGY = 5;
	
	
	protected MazeConfiguration maze;
	private int curPos[];

	protected static int odometer;
	
	private Cells cells;
	
	

	public BasicRobot()
	{
     
	 this.hasStopped = false;
	 batteryLevel = 3000;
	 //set to the default direction
	 this.curDir = CardinalDirection.East;
	 
	 //set the current position to the correct maze location starting point
	 this.curPos = new int[2];
	 this.curPos[0] = 0;
	 this.curPos[1] = 0;
	 
	}

	@Override
	public void rotate(Turn turn) {
		// TODO Auto-generated method stub
		
		if(!hasStopped()) {
						
			//if the robot has not stopped
			//we check for a turn input and if the batterLevel is not less than 3
			//if it is stop the rotation functionality
			switch(turn) {
			case LEFT:
				if(batteryLevel < 3) {
					this.hasStopped = true;	
					break;
				}
				

				batteryLevel = batteryLevel-3;
                //set the current direction to rotateClockwise
				//and allow the controller to input the left rotation
				curDir = curDir.rotateClockwise();
				controller.keyDown(UserInput.Left, 1);

				break;


			case RIGHT:
				if(batteryLevel < 3) {
					hasStopped = true;	
					break;
				}
				

				curDir = curDir.rotateClockwise();
				controller.keyDown(UserInput.Right, 1);

				batteryLevel = batteryLevel-3;

				break;

			case AROUND:
				if(batteryLevel<6) {
					hasStopped = true;
					break;
				}
				
				
				if(batteryLevel >= 6) {

					curDir = curDir.oppositeDirection();
					controller.keyDown(UserInput.Left, 1);
					controller.keyDown(UserInput.Left, 1);

					batteryLevel = batteryLevel - 6;

					break;
				}
			}
		}	
		System.out.println("Rotate: " + this.hasStopped());
	}

	@Override
	public void move(int distance, boolean manual) { 
		// TODO Auto-generated method stub
		/**
		 * Provides the current position as (x,y) coordinates for the maze cell as an array of length 2 with [x,y].
		 * @postcondition 0 <= x < width, 0 <= y < height of the maze. 
		 * @return array of length 2, x = array[0], y=array[1]
		 * @throws Exception if position is outside of the maze
		 * 
		 * checks if it is a manual case
		 * while the distance is greater than 0 and the battery level is < 5
		 * check if the 'up' key is pressed
		 * decrement the battery level by five and the distance by 1
		 * Increment the odometer for the end screen
		 */

		
		if (hasStopped) {
			System.out.println("Error");
			return;
		}
		MazeConfiguration mazeConfiguration = this.controller.getMazeConfiguration();
		
		while(distance > 0){ 
			batteryLevel = batteryLevel - 1;
			int[] pos = this.controller.getCurrentPosition();
			int x = pos[0];
			int y = pos[1];
			
		
			System.out.println(this.getCurDir());
			if (!hasWall(x, y, curDir)) {
				batteryLevel = batteryLevel -5;
				controller.keyDown(UserInput.Up,1);
			
			} else if (!manual) {
				this.hasStopped = true;
				System.out.println("Move: " + this.hasStopped());
				this.switchToExitScreen();
			}
			
			
			pos = this.controller.getCurrentPosition();
			x = pos[0];
			y = pos[1];
			if (!mazeConfiguration.isValidPosition(x, y)) {
				System.out.println("It happened 2");
				this.switchToExitScreen();
			}
			
			
		}
		
		this.odometer++;
		distance--;

	}

	
	@Override
	public int[] getCurrentPosition() throws Exception {
		// TODO Auto-generated method stub
		return controller.getCurrentPosition();
	}

	//PMD Reviews
	@Override
	public void setMaze(Controller controller) {
		this.controller=controller;
		
		curPos[0] = this.controller.getCurrentPosition()[0];
		curPos[1] = this.controller.getCurrentPosition()[1];	
		
		cells= new Cells(this.controller.getMazeConfiguration().getWidth(),this.controller.getMazeConfiguration().getHeight());
	    cells = this.controller.getMazeConfiguration().getMazecells();		
	}

	@Override
	public boolean isAtExit() {
		// TODO Auto-generated method stub
		
		
		System.out.println("Exited, Yes");
		
		int[] pos = this.controller.getCurrentPosition();
		int x = pos[0];
		int y = pos[1];
		MazeConfiguration mazeConfiguration = this.controller.getMazeConfiguration();
		
		
		batteryLevel = batteryLevel -1;
		if (!hasWall(x, y, CardinalDirection.North) && !mazeConfiguration.isValidPosition(x, y + 1)) {
			return true;
		}
		
		batteryLevel = batteryLevel -1;

		if (!hasWall(x, y, CardinalDirection.East) && !mazeConfiguration.isValidPosition(x + 1, y)) {
			return true;
		}
		
		batteryLevel = batteryLevel -1;

		if (!hasWall(x, y, CardinalDirection.West) && !mazeConfiguration.isValidPosition(x - 1, y)) {
			return true;
		}
		
		batteryLevel = batteryLevel -1;

		if (!hasWall(x, y, CardinalDirection.South) && !mazeConfiguration.isValidPosition(x, y - 1)) {
			return true;
		}
		
		return false;
				    
	}
	
	public void switchToExitScreen() {
		if(hasStopped()) {
			this.controller.switchFromPlayingToWinning(odometer);;
		}
		else {
			return;
		}
	}

	@Override
	public boolean canSeeExit(Direction direction) throws UnsupportedOperationException {
		// TODO Auto-generated method stub	
			MazeConfiguration mazeConfiguration = this.controller.getMazeConfiguration();
			CardinalDirection cardinalDirection = getCardinalDirection(direction);
			
			int[] pos = this.controller.getCurrentPosition();
			int x = pos[0];
			int y = pos[1];
			
			while (mazeConfiguration.isValidPosition(x, y)) {
				if (hasWall(x, y, cardinalDirection)) {
					return false;
				}
				
				switch(cardinalDirection) {
				case North:
					y++;
				case East:
					x++;
				case South:
					y--;
				case West:
					x--;
				}
			}
			
			return true;
		
	}
			
			/**
			 * Determines if a wall in a certain CardinalDirection at position (x, y) exists.
			 * @param x
			 * @param y
			 * @param direction
			 * @return whether or not there is a wall
			 */
			private boolean hasWall(int x, int y, CardinalDirection direction) {
				if (direction == CardinalDirection.North || direction == CardinalDirection.South) {
					direction = direction.oppositeDirection();
				}
				return this.controller.getMazeConfiguration().hasWall(x, y, direction);
			}
			
			@Override
			public boolean hasWallInDirection(CardinalDirection cardinalDirection) {
				int[] pos = this.controller.getCurrentPosition();
				return this.hasWall(pos[0], pos[1], cardinalDirection);
			}
			
			
	@Override
	public boolean isInsideRoom() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
       curPos = this.controller.getCurrentPosition();
		
		return cells.isInRoom(curPos[0], curPos[1]);
	}

	//Implemented simple return function to tell if the room has a sensor
	@Override
	public boolean hasRoomSensor() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public CardinalDirection getCurrentDirection() {
		// TODO Auto-generated method stub
		return this.controller.getCurrentDirection();
	}

	//Implemented simple return function to return the robot's battery level
	@Override
	public float getBatteryLevel() {
		// TODO Auto-generated method stub
		return batteryLevel;
	}

	@Override
	public void setBatteryLevel(float level) {
		// TODO Auto-generated method stub
		batteryLevel = level;
	}

	@Override
	public int getOdometerReading() {
		// TODO Auto-generated method stub
		return odometer;
	}

	//Implemented simple set function to reset the odometer to 0
	@Override
	public void resetOdometer() {
		// TODO Auto-generated method stub
		odometer = 0;
		
	}

	@Override
	public float getEnergyForFullRotation() {
		// TODO Auto-generated method stub
		return FULL_ROTATION;
	}

	@Override
	public float getEnergyForStepForward() {
		// TODO Auto-generated method stub
		return STEP_ENERGY;
	}

	//Implemented simple return function to tell if the robot has stopped

	@Override
	public boolean hasStopped() {
		// TODO Auto-generated method stub
		return hasStopped;
	}

	
	@Override
	public int distanceToObstacle(Direction direction) throws UnsupportedOperationException {

		//While there is no walls in the current direction
		//if it is not a valid position return the max maze value
		//switch statement to for each cardinal directions
		//increment x or y according to each direction
		//the x and y update to check fort a valid position
		//increment steps and return them to get the distance from an obstacle
		
		if(!hasDistanceSensor(direction)) {

			throw new UnsupportedOperationException();
	    }
		int steps = 0;
		curPos = this.controller.getCurrentPosition();
		int curX = this.controller.getCurrentPosition()[0];
		int curY = this.controller.getCurrentPosition()[1];
		if(batteryLevel>0){
			batteryLevel = batteryLevel - 1;
		}
		else {
			hasStopped = true;
			System.out.println("Exit 3");
			controller.switchFromPlayingToWinning(odometer);
		}
		CardinalDirection SensorDirection;
		SensorDirection = getCurDir();
		
		switch(direction) {
		
		case LEFT:
			SensorDirection = getCurDir().rotateClockwise();
			break;
		case RIGHT:
			SensorDirection = getCurDir().rotateCounterClockwise();
			break;
		case BACKWARD:
			SensorDirection = getCurDir().oppositeDirection();
			break;
		case FORWARD:
			SensorDirection = getCurDir();
			break;		
		}

		int mWidth = this.controller.getMazeConfiguration().getWidth();
		int mHeight = this.controller.getMazeConfiguration().getHeight();
		
		while(true) {
			if (curX>= mWidth || curY>=mHeight || curX < 0 || curY<0){
				return Integer.MAX_VALUE;	
			}
			else {
			switch(SensorDirection){
			case East:
				if(cells.hasWall(curX, curY, CardinalDirection.East)){
					return steps;
				}
				curX++;
				break;
			case West:
				if(cells.hasWall(curX, curY, CardinalDirection.West)){
					return steps;
				}
				curX--;
				break;
			case South:
				if(cells.hasWall(curX, curY, CardinalDirection.South)){
					return steps;
				}
				curY++;
				break;
			case North:
				if(cells.hasWall(curX, curY, CardinalDirection.North)){
					return steps;
				}
				curY--;
				break;	

			}
			steps++;

		}	
	}		
			
	}

	@Override
	public boolean hasDistanceSensor(Direction direction) {
		// TODO Auto-generated method stub
		if (direction == Direction.FORWARD)
			return true;
		else if (direction == Direction.BACKWARD)
			return true;
		else if (direction == Direction.LEFT)
			return true;
		else if (direction == Direction.RIGHT)
			return true;
		else
			return false;
	}
	
	
	
	public CardinalDirection getCurDir() {
		return curDir;
	}

	//Helper method that gets a direction and converts it to a cardinalDirection. 
	private CardinalDirection getCardinalDirection(Direction direction) {
		CardinalDirection cardinalDirection = this.getCurrentDirection();
		
		switch(direction) {
		case LEFT:
			cardinalDirection = cardinalDirection.rotateClockwise().rotateClockwise().rotateClockwise();
			break;
		case BACKWARD:
			cardinalDirection = cardinalDirection.rotateClockwise().rotateClockwise();
			break;
		case RIGHT:
			cardinalDirection = cardinalDirection.rotateClockwise();
			break;
		default:
			break;
		}
		
		return cardinalDirection;
	}
	
	
	
	
	public void setCurDir(CardinalDirection curDir) {
		this.curDir = curDir;
	}
	
	@Override
	public void turnToDirection(CardinalDirection direction) {
		while (getCurrentDirection() != direction) {
			rotate(Turn.RIGHT);
		}
	}
	
}
