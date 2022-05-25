package com.acme.tasty.popup;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;
import com.acme.tasty.R;

public class PopupWindowService extends AppCompatActivity {
    public static PopupWindow getOrderConfirmationPopupWindow(LayoutInflater inflater, View view) {
        View popupView = inflater.inflate(R.layout.pop_up_order, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        return popupWindow;
    }

    public static void closePopUp(PopupWindow popupWindow, View view) {
        popupWindow.dismiss();
    }
}
