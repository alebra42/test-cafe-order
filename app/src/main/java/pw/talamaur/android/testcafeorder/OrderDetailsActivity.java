package pw.talamaur.android.testcafeorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView textViewOrderDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        textViewOrderDetails = findViewById(R.id.textViewOrderDetails);
        Intent intent = getIntent();
        if (intent.hasExtra("message")) {
            textViewOrderDetails.setText(intent.getStringExtra("message"));
        }
    }
}
