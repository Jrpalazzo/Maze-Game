package cs301.amazebyjosephpalazzo.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import cs301.amazebyjosephpalazzo.R;

/**
 * Interacts with seekbar, spinners, and buttons.
 * it listen to the selected size,drivers and builders
 * when explore is clicked it will use those to create
 * a specific maze.
 * AMazeActivity takes to the GeneratingActivity
 * and it is passed down to PlayActivity
 * when a certain maze is picked
 * Collaborators:
 *  android.support.v7.app.AppCompatActivity;
 android.os.Bundle;
 android.util.Log;
 android.widget.ArrayAdapter;
 android.widget.Spinner;
 android.view.View;
 android.content.Intent;
 android.widget.Toast;
 *
 */
public class AMazeActivity extends AppCompatActivity {

    public MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private TextView size;
    private static boolean animation = false;


    public static boolean getAnimation()
    {
        return animation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaze);

        Spinner builder = (Spinner) findViewById(R.id.builderSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.builder, R.layout.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        builder.setAdapter(adapter);

        Spinner driver = (Spinner) findViewById(R.id.driverSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.driver, R.layout.spinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driver.setAdapter(adapter2);
        size = (TextView) findViewById(R.id.size);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                size.setText("Maze Skill Level: " + i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        builder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        driver.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    /**
     * listen to button clicks for
     * generate,explore,revist.
     * Everytime they are clicked it
     * Logs the clicked button and
     * creates a tost for the specific button
     * if generate is clicked then
     * it starts a new activity to switch to generating
     *
     *
     * @param view
     */
    public void onButtonClick(View view) {
        if (view.getId() == R.id.explore) {
            animation = false;
            Intent intent = new Intent(this, GeneratingActivity.class);
            startActivity(intent);

            Log.v("PlayActivity","Generate Button");
            Toast.makeText(this, "Generate Button", Toast.LENGTH_LONG).show();

        }
        if (view.getId()== R.id.revisit){
            Log.v("PlayActivity","Revisit Button");
            Toast.makeText(this, "Revisit Button", Toast.LENGTH_LONG).show();
        }
        if (view.getId()== R.id.explore){
            Log.v("PlayActivity","Explore Button");
            Toast.makeText(this, "Explore Button", Toast.LENGTH_LONG).show();
        }
        if (view.getId() == R.id.animation) {
            animation = true;
            Intent intent = new Intent(this, GeneratingActivity.class);
            startActivity(intent);


            Log.v("PlayActivity","Generate Button");
            Toast.makeText(this, "Generate Button", Toast.LENGTH_LONG).show();

        }

    }

}