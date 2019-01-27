package pw.talamaur.android.testcafeorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");

        TextView textViewHello = findViewById(R.id.textViewHello);
        String oldHelloText = textViewHello.getText().toString();
        String newHelloText = oldHelloText.replace("%USERNAME%", name);
        textViewHello.setText(newHelloText);

        RadioGroup radioGroup = findViewById(R.id.radioGroupDrinks);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Spinner spinnerCoffee = findViewById(R.id.spinnerCoffee);
                Spinner spinnerTea = findViewById(R.id.spinnerTea);
                switch (checkedId) {
                    case 1: // tea
                        spinnerCoffee.setVisibility(View.INVISIBLE);
                        spinnerCoffee.setEnabled(false);
                        spinnerTea.setVisibility(View.VISIBLE);
                        spinnerTea.setEnabled(true);
                        break;

                    case 2: //coffee
                        spinnerCoffee.setVisibility(View.VISIBLE);
                        spinnerCoffee.setEnabled(true);
                        spinnerTea.setVisibility(View.INVISIBLE);
                        spinnerTea.setEnabled(false);
                        break;
                }
            }
        });
    }
}
