package pw.talamaur.android.testcafeorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickCreateOrder(View view) {
        Intent intent = new Intent(this, CreateOrderActivity.class);
        startActivity(intent);
    }
}
