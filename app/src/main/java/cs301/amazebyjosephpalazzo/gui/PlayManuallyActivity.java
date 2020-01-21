package cs301.amazebyjosephpalazzo.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cs301.amazebyjosephpalazzo.R;

/**
 * @Author: Joseph Palazzo
 * Interacts with AMazeActivity,GeneratingActivity,FinishActivityWin,FinishActivityLose.
 * Takes information from AMazeActivity and builds the maze and sends information to GeneratingActivity
 * once it is done building. Takes input from user by Map,Exit,Wall visibility, Pause/play, and control keys
 * Collaborator:
 * android.content.Intent;
  android.os.Bundle;
  android.support.v4.app.FragmentManager;
  android.support.v7.app.AppCompatActivity;
  android.util.Log;
  android.view.MenuItem;
  android.view.View;
  android.widget.Toast;
 */

public class PlayManuallyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    /**
                     * Changes activity to AMazeActivity when back
                     * button is pressed
                     */
                    @Override
                    public void onBackStackChanged() {
                        Intent intent = new Intent(PlayManuallyActivity.this, AMazeActivity.class);
                        startActivity(intent);
                        //finish();

                    }
                }
        );




        DisplayMetrics metrics = new DisplayMetrics();

    }

    /**
     * Function that takes item as parameter
     * and if parameter is the back button then
     * it finishes the activity and returns to
     * AMazeActivity
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent = new Intent(PlayManuallyActivity.this, AMazeActivity.class);
            startActivity(intent);
            Log.v("PlayActivity","Home Button");
            Toast.makeText(this, "Home Button", Toast.LENGTH_LONG).show();
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * listen for win,lose
     * left,right,up and down
     * it creates log and toast for all
     * if win or lose it clicked
     * then it switches to win or lose finishactivity
     * @param view
     */
    public void onButtonClick(View view) {
        if (view.getId() == R.id.win) {
            Intent intent = new Intent(this, WinningActivity.class);
            startActivity(intent);
            Log.v("PlayActivity","Win Button");
            Toast.makeText(this, "Win Button", Toast.LENGTH_LONG).show();
            this.finish();


        }
        if (view.getId() == R.id.lose) {
            Intent intent = new Intent(this, LosingActivity.class);
            startActivity(intent);
            Log.v("PlayActivity","Lose Button");
            Toast.makeText(this, "Lose Button", Toast.LENGTH_LONG).show();
            this.finish();


        }
        if (view.getId() == R.id.left) {
            Log.v("PlayActivity","Left Button");
            Toast.makeText(this, "Left Button", Toast.LENGTH_LONG).show();


        }
        if (view.getId() == R.id.right) {
            Log.v("PlayActivity","Right Button");
            Toast.makeText(this, "Right Button", Toast.LENGTH_LONG).show();


        }
        if (view.getId() == R.id.up) {
            Log.v("PlayActivity","Up Button");
            Toast.makeText(this, "Up Button", Toast.LENGTH_LONG).show();

        }
        if (view.getId() == R.id.down) {
            Log.v("PlayActivity","Down Button");
            Toast.makeText(this, "Down Button", Toast.LENGTH_LONG).show();


        }
        if (view.getId() == R.id.win) {
            Log.v("PlayActivity","Win Button");
            Toast.makeText(this, "Win Button", Toast.LENGTH_LONG).show();

        }
        if (view.getId() == R.id.lose) {
            Log.v("PlayActivity","Lose Button");
            Toast.makeText(this, "Lose Button", Toast.LENGTH_LONG).show();

        }
    }

    public void onBackPressed(){
        this.startActivity(new Intent(PlayManuallyActivity.this, AMazeActivity.class));

    }

}
