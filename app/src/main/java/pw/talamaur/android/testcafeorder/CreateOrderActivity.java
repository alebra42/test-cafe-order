package pw.talamaur.android.testcafeorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private String name;
    private String password;
    private byte radioGroupDrinkType;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerCoffee;
    private Spinner spinnerTea;
    private StringBuilder resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        resultString = new StringBuilder();

        Intent intent = getIntent();
        name = intent.hasExtra("name") ?
                intent.getStringExtra("name") :
                "?";
        password = intent.hasExtra("password") ?
                intent.getStringExtra("password") :
                "?";

        setupTextViewHello(name);
        setupCheckBoxes();

        RadioGroup radioGroup = findViewById(R.id.radioGroupDrinks);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                spinnerCoffee = findViewById(R.id.spinnerCoffee);
                spinnerTea = findViewById(R.id.spinnerTea);
                TextView textViewAdditions = findViewById(R.id.textViewAdditions);
                switch (checkedId) {
                    case 1: // tea
                        spinnerCoffee.setVisibility(View.INVISIBLE);
                        spinnerCoffee.setEnabled(false);
                        spinnerTea.setVisibility(View.VISIBLE);
                        spinnerTea.setEnabled(true);
                        textViewAdditions.setText(R.string.additions_tea);
                        checkBoxLemon.setEnabled(true);
                        break;

                    case 2: //coffee
                        spinnerCoffee.setVisibility(View.VISIBLE);
                        spinnerCoffee.setEnabled(true);
                        spinnerTea.setVisibility(View.INVISIBLE);
                        spinnerTea.setEnabled(false);
                        textViewAdditions.setText(R.string.additions_coffee);
                        checkBoxLemon.setChecked(false);
                        checkBoxLemon.setEnabled(false);
                        break;
                }
                radioGroupDrinkType = (byte) checkedId;
            }
        });
    }

    private void setupTextViewHello(String newName) {
        TextView textViewHello = findViewById(R.id.textViewHello);
        String oldHelloText = textViewHello.getText().toString();
        String newHelloText = oldHelloText.replace("%USERNAME%", newName);
        textViewHello.setText(newHelloText);
    }

    private void setupCheckBoxes() {
        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxSugar = findViewById(R.id.checkboxSugar);
        checkBoxLemon = findViewById(R.id.checkboxLemon);
    }

    public void onClickSendOrder(View view) {
        String drinkOption = "";
        resultString.append("name: ").append(name).append('\n');
        resultString.append("password: ").append(password).append('\n');
        if (radioGroupDrinkType == -1) {
            resultString.append("Drink type: ").append(getString(R.string.not_selected)).append('\n');
        } else if (radioGroupDrinkType == 1) {
            resultString.append("Drink type: ").append(getString(R.string.tea)).append('\n');
            drinkOption = spinnerTea.getSelectedItem().toString();
        } else if (radioGroupDrinkType == 2) {
            resultString.append("Drink type: ").append(getString(R.string.coffee)).append('\n');
            drinkOption = spinnerCoffee.getSelectedItem().toString();
        }
        resultString.append("Drink additions: ");
        if (checkBoxMilk.isChecked()) {
            resultString.append(getString(R.string.milk)).append(' ');
        }
        if (checkBoxSugar.isChecked()) {
            resultString.append(getString(R.string.sugar)).append(' ');
        }
        if (checkBoxLemon.isChecked()) {
            resultString.append(getString(R.string.lemon)).append(' ');
        }
        resultString.append('\n');
        resultString.append("Drink option: ").append(drinkOption);

        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra("message", resultString.toString());
        startActivity(intent);
    }
}
