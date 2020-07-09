package ir.javafundamental.android.testdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        Button btn_Call = (Button)findViewById(R.id.btn_Call);
        btn_Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt_PhoneNumber = (EditText)findViewById(R.id.txt_PhoneNumber);
                if (txt_PhoneNumber.getText().length() == 0){
                    Toast.makeText(getApplicationContext(), "Phone Number is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + txt_PhoneNumber.getText()));
                startActivity(intent);
            }
        });
    }
}