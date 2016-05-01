/*Created by Amar Skenderovic 11196041
*24-4-2016
*/
package com.example.admin.amarskenderovic_pset2;

        import android.app.Activity;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.InputStream;
        import java.util.HashSet;
        import java.util.Set;

/**
 * Created by Amar on 24-4-2016. This is the 3rd class that gets the input from Display (which is the second activity) and asks for input.
 * Then the input is being counted and saved and after the requirements are met the user is able to continue to the next activity and see the output.
 */
    public class Display2
        extends Activity{

    final static int SIMPLE = R.raw.madlib0_simple;
    final static int TARZAN = R.raw.madlib1_tarzan;
    final static int UNIVERSITY = R.raw.madlib2_university;
    final static int CLOTHES = R.raw.madlib3_clothes;
    final static int DANCE = R.raw.madlib4_dance;
    public static final String PLACEHOLDER_LIST_FILE = "placeholder_file";
    public static final String STORY_NUMBER_FILE = "story_number_file";

    TextView wordsLeft;
    Button submitButton;
    Story story;
    EditText placeField;
    Set<String> placeholderList;
    int storyIndex = 404;
    int Counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);

        // Restore preferences
        SharedPreferences placeholderSettings = getSharedPreferences(PLACEHOLDER_LIST_FILE, 0);
        placeholderList = placeholderSettings.getStringSet("placeholders", null);
        if (placeholderList == null) {
            placeholderList = new HashSet<>();
        }

        SharedPreferences storyNumberSettings = getSharedPreferences(STORY_NUMBER_FILE, 0);
        storyIndex = storyNumberSettings.getInt("story_number", 404);
        storyIndex = 404;

        if (storyIndex == 404) {
            storyIndex = getIntent().getIntExtra("selected_story", 404);
        }

        InputStream is;
        is = this.getResources().openRawResource(getStoryNumber(storyIndex));
        story = new Story(is);

        for (String currentPlaceholder : placeholderList) {
            story.fillInPlaceholder(currentPlaceholder);
        }

        // Create counter for the words left
        wordsLeft = (TextView) findViewById(R.id.words_left);
        Counter = story.getPlaceholderRemainingCount();
        wordsLeft.setText("Words left: " + Integer.toString(Counter));

        // Show the required placeholder
        placeField = (EditText) findViewById(R.id.placeholder_field);
        String currentPlaceholder = story.getNextPlaceholder();
        placeField.setHint("submit a/an " + currentPlaceholder);

        // To be able to press the button check if all requirements are met
        submitButton = (Button) findViewById(R.id.button3);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeholderUserText = placeField.getText().toString();
                if (!placeholderUserText.equals("") && !placeholderList.contains(placeholderUserText)) {
                    story.fillInPlaceholder(placeholderUserText);
                    placeholderList.add(placeholderUserText);
                    placeField.setText("");
                    Counter = story.getPlaceholderRemainingCount();
                    wordsLeft.setText("Words left: " + Integer.toString(Counter));

                    String currentPlaceholder = story.getNextPlaceholder();
                    placeField.setHint("submit a/an " + currentPlaceholder);

                    if (Counter == 0) {
                        placeholderList.clear();
                        storyIndex = 404;
                        Intent i = new Intent(getApplicationContext(), Display3.class);
                        i.putExtra("finished_story", story.toString());
                        startActivity(i);
                    }
                } else {
                    Toast errorEmptyField = Toast.makeText(getApplicationContext(), "The field is empty or already entered once, try again.",
                            Toast.LENGTH_LONG);
                    errorEmptyField.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    errorEmptyField.show();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences placeholderSettings = getSharedPreferences(PLACEHOLDER_LIST_FILE, 0);
        SharedPreferences.Editor editor1 = placeholderSettings.edit();
        editor1.putStringSet("placeholders", placeholderList);
        editor1.apply();

        SharedPreferences storyNumberSettings = getSharedPreferences(STORY_NUMBER_FILE, 0);
        SharedPreferences.Editor editor2 = storyNumberSettings.edit();
        editor2.putInt("story_number", storyIndex);
        editor2.apply();
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
    // Return the completed story
    private int getStoryNumber(int storyIndex) {
        switch (storyIndex) {
            case 0:
                return SIMPLE;
            case 1:
                return TARZAN;
            case 2:
                return UNIVERSITY;
            case 3:
                return CLOTHES;
            case 4:
                return DANCE;
            default:
                return 404;
        }
    }
}