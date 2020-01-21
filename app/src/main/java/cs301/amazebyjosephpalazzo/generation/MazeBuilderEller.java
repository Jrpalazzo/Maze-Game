package cs301.amazebyjosephpalazzo.generation;


/*
 * @author Joseph Palazzo
 */

public class MazeBuilderEller extends MazeBuilder implements Runnable {

public MazeBuilderEller() {
    super();
}
           
public MazeBuilderEller(boolean det) {
    super(det);
    System.out.println("MazeBuilderEller uses Eller's algorithm to generate maze.");
}

@Override
protected void generatePathways (){
	/*
	 * Builds a two dimensional which will store the rows and columns of the maze, defined as x and y.
	 */
    int[][] mazeSet = new int[height][width] ;
  
    int setId = 1 ;
    
    int verticalWalls = 0; //variable to store the vertical walls
    /*
     * Two for loops iterate through the rows and columns and sets the Id's for each row; 
     */
    
        setEllerIds(mazeSet, setId);         
    
        //call the function to remove walls and join sets
        removeRightWalls(mazeSet, verticalWalls, setId);
 }

protected int[][] setEllerIds(int [][] mazeSet, int setId)
{
	for(int y=0; y<height; y++){
		for(int x=0; x<width; x++){
			mazeSet[y][x] = setId++;

		}
	}

	return mazeSet;


}


private void removeRightWalls(int [][] mazeSet, int verticalWalls, int setId) {
	
	 for(int y=0; y<height; y++){
        for(int x=0; x<width; x++){                                         
           /*
             * Randomly it will pick a number between 0 and 1 if it is equal to 1 it will merge and 
             * delete the wall to the right, else it will continue to iterate through the array
             * 
           */
            
         if (x != width-1){
            if(x != width - 1 && mazeSet[y][x] != mazeSet[y][x+1])
             {
               if(random.nextIntWithinInterval(0, 1) == 1) {
                 cells.deleteWall(new Wall(x, y, CardinalDirection.East));
				 mazeSet[y][x+1] = mazeSet[y][x] ;						

               }
             }
           }
          
          } 
               
        if (y < height - 1){
           for(int x=0; x<width; x++){
        	  
             
             int w = random.nextIntWithinInterval(0, x);
             
             //sets the vertical walls to none if the Id is not equal to the current set
             if ((x <=width-1) || (mazeSet[y][x] != setId)){
                 verticalWalls = 0;
             }
             
             //adds a random vertical wall, checks if there is one already
             boolean walltest1 = (w%2==0 && x<width-1) && (verticalWalls ==0);
             //anther condition to check if there already is a vertical wall
             boolean walltest2 = (verticalWalls == 0) && ((x == width - 1));
             
             if (walltest1 || walltest2 || (mazeSet[y][x] != mazeSet[y][x+1]))
             {
       
                  Wall wall = new Wall(x, y, CardinalDirection.South);
                  mazeSet[y+1][x] = mazeSet[y][x];
                  cells.deleteWall(wall);
                 
                   verticalWalls++;
                
             }
               //reset the Id to equal to the current one
               setId = mazeSet[y][x];
            } 
        } 
        
      //Merges sets and deletes walls for the final row
        if (y == height -1){
              
              for(int x=0; x<width; x++){        
              	//if the x position is equal to the width-1, delete the wall and sets to the previous position
            	 //otherwise just removes.
                    if (x==width-1){
                        cells.deleteWall(new Wall(x-1, y, CardinalDirection.East));
                        mazeSet[y][x] = mazeSet[y][x-1] ;
                    }
                    else
                    {
                 
                       cells.deleteWall(new Wall(x, y, CardinalDirection.East));
                       mazeSet[y][x+1] = mazeSet[y][x] ;
                    }
               
             }    
         }
        
        
       }
   }
}






