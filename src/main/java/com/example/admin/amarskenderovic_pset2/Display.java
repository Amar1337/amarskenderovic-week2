/*Created by Amar Skenderovic 11196041
*22-4-2016
*/
package com.example.admin.amarskenderovic_pset2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Amar on 22-4-2016.
 */
public class Display
        extends Activity
        implements View.OnClickListener {

    // Declare variables
    private static Button button2;
    Spinner spinner;
    int selectedStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        // Create button listener
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.story, android.R.layout.simple_spinner_item);

        // Specify the layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStory = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // When the button is clicked continue to the next activity
    public void onClick(View v)
    {
        Intent i = new Intent(getApplicationContext(),Display2.class);
        i.putExtra("selected_story", selectedStory);
        startActivity(i);
    }
}