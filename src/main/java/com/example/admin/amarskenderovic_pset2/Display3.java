/*Created by Amar Skenderovic 11196041
*24-4-2016
*/
package com.example.admin.amarskenderovic_pset2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Amar on 24-4-2016. This class is the 'final' activity that shows the output that
 * the user has created with his/her input. Also with an option to create a new story.
 */
public class Display3
        extends Activity {
    private static Button button4;
    private static TextView storyText;
    public static String finishedStory;

    // Show the output
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output);
        finishedStory = getIntent().getStringExtra("finished_story");

        storyText = (TextView) findViewById(R.id.outview14);
        storyText.setText(finishedStory);

        // Button for going back to choosing a new story
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Display.class);
                startActivity(i);
            }
        });
    }
}