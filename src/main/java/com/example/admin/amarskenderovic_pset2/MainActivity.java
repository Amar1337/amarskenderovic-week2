/*Created by Amar Skenderovic 11196041
*22-4-2016
*/
package com.example.admin.amarskenderovic_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Amar on 22-4-2016.
 * A class that is the main activity (the first page). The activity has explanation about the app
 * and a button to get started.
 */
public class MainActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // When clicked on the button continue to next activity
    public void OnButtonClick(View v)
    {
        if (v.getId() == R.id.button)
        {
            Intent i = new Intent(MainActivity.this, Display.class);
            startActivity(i);
        }
    }
}