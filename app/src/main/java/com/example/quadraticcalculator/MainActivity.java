package com.example.quadraticcalculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quadraticcalculator.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;


    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        //retrieve number values
        EditText editText_a = (EditText) findViewById(R.id.editTextNumberDecimal);
        EditText editText_b = (EditText) findViewById(R.id.editTextNumberDecimal2);
        EditText editText_c = (EditText) findViewById(R.id.editTextNumberDecimal3);

        // convert number values to Double
        Double var_a = Double.parseDouble(editText_a.getText().toString());
        Double var_b = Double.parseDouble(editText_b.getText().toString());
        Double var_c = Double.parseDouble(editText_c.getText().toString());

        // get switch values
        Switch switch_a = (Switch) findViewById(R.id.switch1);
        Switch switch_b = (Switch) findViewById(R.id.switch2);
        Switch switch_c = (Switch) findViewById(R.id.switch3);

        // apply switch values to doubles
        if (switch_a.isChecked())
        {
            var_a = (0 - var_a);
        }
        if (switch_b.isChecked())
        {
            var_b = (0 - var_b);
        }
        if (switch_c.isChecked())
        {
            var_c = (0 - var_c);
        }


        // calculate the message
            //default message
        String message = ("Error: did not calculate. \nDetected input: a = " + (var_a) + ", b = " + (var_b) + ", c = " + (var_c));

            //find the discriminant
        Double discriminant = ((Math.pow(var_b, 2.0)) - (4.0 * var_a * var_c));

            //give responses based on discriminant value
        if (discriminant < 0.0) // has no real answers
        {
            message = "This function has no real answers. \nA graph of this function does not intersect the x-axis.";
        }
        else if (discriminant == 0.0) // has one real answer
        {
            Double answer = ((0.0 - var_b) / (var_a * 2));
            message = "This function has one answer. \nA graph of this function intersects the x-axis exactly once. \nAnswer: " + (answer);
        }
        else // has two answers (discriminant > 0)
        {
            Double ans_1 = (((0.0 - var_b) + Math.sqrt(discriminant))/ (var_a * 2));
            Double ans_2 = (((0.0 - var_b) - Math.sqrt(discriminant))/ (var_a * 2));
            message = "This function has two answers. \nA graph of this function intersects the x-axis twice. \nAnswer 1: " + (ans_1) + " \nAnswer 2: " + (ans_2);
        }

        // pass the message
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
/**
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
 */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}