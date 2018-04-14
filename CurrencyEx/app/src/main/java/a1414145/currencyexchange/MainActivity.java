package a1414145.currencyexchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String CurArr[] = {"EUR", "USD", "VND"};
    Double ExArr[] = {0.80987988, 1.0, 22727.27272727};
    Integer inType = 0;
    Integer outType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, CurArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinFrom.setAdapter(adapter);
        final Spinner spinTo = (Spinner) findViewById(R.id.spinnerTo);
        spinTo.setAdapter(adapter);

        spinFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                inType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }
        });
        spinTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                outType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }
        });

        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    convert(editText);
                    return true;
                }
                return false;
            }
        });

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert(editText);
            }
        });
    }

    private void convert(EditText editText) {
        String text = "0.0";
        Editable temp = editText.getText();
        if(temp != null && !temp.toString().equals("")) text = temp.toString();
        Double input = Double.parseDouble(text);
        Double output = input/ExArr[inType]*ExArr[outType];

        NumberFormat format = NumberFormat.getInstance(Locale.US);
        format.setMaximumFractionDigits(2);

        TextView result = (TextView) findViewById(R.id.textViewResult);
        result.setText(format.format(output));
    }
}
