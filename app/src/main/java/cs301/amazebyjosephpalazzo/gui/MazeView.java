package cs301.amazebyjosephpalazzo.gui;

import cs301.amazebyjosephpalazzo.gui.Constants.StateGUI;


/**
 * Implements the screens that are displayed whenever the game is not in
 * the playing state. The screens shown are the title screen,
 * the generating screen with the progress bar during maze generation,
 * and the final screen when the game finishes.
 *
 * @author Peter Kemper
 *
 */
import android.content.Context;

/**
 * Implements the screens that are displayed whenever the game is not in
 * the playing state. The screens shown are the title screen,
 * the generating screen with the progress bar during maze generation,
 * and the final screen when the game finishes.
 * @author pk
 *
 */
public class MazeView {

	// need to know the maze model to check the state
	// and to provide progress information in the generating state
	private Controller controller;

	MazePanel wrapper;
	//MazePanel wrapper;

	public MazeView(Controller c) {
		super();
		controller = c;
	}

	public void initGraphics(Context context) {
		wrapper = new MazePanel(context);
	}

	public void redraw(Controller controller, StateGUI state, int px, int py, int view_dx,
					   int view_dy, int walk_step, int view_offset, RangeSet rset, int ang) {
		//dbg("redraw") ;


		switch (state) {
			case STATE_TITLE:
				redrawTitle(wrapper);
				break;
			case STATE_GENERATING:
				redrawGenerating(wrapper);
				break;
			case STATE_PLAY:
				// skip this one
				break;
			case STATE_FINISH:
				redrawFinish(wrapper);
				break;
		}
	}

	private void dbg(String str) {
		System.out.println("MazeView:" + str);
	}

	//

	/**
	 * Helper method for redraw to draw the title screen, screen is hard coded
	 *
	 * @param wrapper graphics is the off screen image
	 */
	void redrawTitle(MazePanel wrapper) {
		// produce white background
		wrapper.setGraphicsColor("pink");
		wrapper.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);


		wrapper.setGraphicsColor("cyan");

		// write the reference to falstad
		wrapper.setGraphicsColor("white");
		//wrapper.setGraphicsFont("small");


		// write the instructions
		wrapper.setGraphicsColor("black");
	}
//		wrapper.centerString( wrapper.getFontMetrics(), "To start, select a skill level.", 250);
//		wrapper.centerString( wrapper.getFontMetrics(), "(Press a number from 0 to 9,", 300);
//		wrapper.centerString( wrapper.getFontMetrics(), "or a letter from A to F)", 320);
//		wrapper.centerString( wrapper.getFontMetrics(), "Version 2.0", 350);
//	}

	/**
	 * Helper method for redraw to draw final screen, screen is hard coded
	 *
	 * @param wrapper graphics is the off screen image
	 */
	void redrawFinish(MazePanel wrapper) {
		// produce blue background
		wrapper.setGraphicsColor("grey");
		wrapper.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

		wrapper.setGraphicsColor("white");

		wrapper.setGraphicsColor("yellow");

		wrapper.setGraphicsColor("white");

		wrapper.setGraphicsColor("cyan");
	}

	/**
	 * Helper method for redraw to draw screen during phase of maze generation, screen is hard coded
	 * only attribute percentdone is dynamic
	 *
	 * @param wrapper graphics is the off screen image
	 */
	void redrawGenerating(MazePanel wrapper) {
		// produce yellow background
		wrapper.setGraphicsColor("yellow");
		wrapper.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		// write the title
		//wrapper.setGraphicsFont("large");
		//fm = wrapper.getFontMetrics();
		wrapper.setGraphicsColor("red");

		//fm = wrapper.getFontMetrics();
		// show progress
		wrapper.setGraphicsColor("black");
//		if (null != controller)
//			wrapper.centerString(wrapper.getFontMetrics(), controller.getPercentDone()+"% completed", 200);
//		else
//			wrapper.centerString(wrapper.getFontMetrics(), "Error: no controller, no progress", 200);
//		// write the instructions
//		wrapper.centerString(wrapper.getFontMetrics(), "Hit escape to stop", 300);
	}
}