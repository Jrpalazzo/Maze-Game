package cs301.amazebyjosephpalazzo.gui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cs301.amazebyjosephpalazzo.R;

/**
 * @Author: Joseph Palazzo
 * Finish win screen if the user completes the maze
 * allows the user to return to the main menu with the
 * Menu button
 *  Colaborators:
 *  import android.content.Intent;
  android.os.Bundle;
  android.support.v4.app.FragmentManager;
  android.support.v7.app.AppCompatActivity;
  android.util.Log;
  android.view.MenuItem;
  android.view.View;
  android.widget.Toast;
 */

public class WinningActivity extends AppCompatActivity {

    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

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
                        Intent intent = new Intent(WinningActivity.this, AMazeActivity.class);
                        startActivity(intent);

                    }
                }
        );

    }






    /**
     * Listen for button lick
     * on loseback button
     * and when loseback button is clicked
     * it ends this activity and returns back
     * to AMazeActivity
     * also creates log and toast
     * @param view
     */
    public void onButtonClick(View view) {
        if (view.getId() == R.id.menuback) {
            Intent intent = new Intent(this, AMazeActivity.class);
            startActivity(intent);
            Log.v("FinishActivityWin","Menu Button");
            Toast.makeText(this, "Menu Button", Toast.LENGTH_LONG).show();
            //  stopMusic();
            this.finish();


        }

    }


    /**
     *  listen for back button
     *  and when it is clicked it
     *  returns to the AMazeActivity
     *  creates log and toast
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            Log.v("FinishActivityWin","Home Button");
            Toast.makeText(this, "Home Button", Toast.LENGTH_LONG).show();
            //   stopMusic();
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed(){
        this.startActivity(new Intent(WinningActivity.this, AMazeActivity.class));

    }
}
