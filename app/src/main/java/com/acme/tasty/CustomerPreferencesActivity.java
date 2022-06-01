package com.acme.tasty;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class CustomerPreferencesActivity extends AppCompatActivity {

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox_indisch:
                if (checked);
            else
                break;
            case R.id.checkbox_mexikanisch:
                if (checked);
            else
                break;
        }
    }
}
