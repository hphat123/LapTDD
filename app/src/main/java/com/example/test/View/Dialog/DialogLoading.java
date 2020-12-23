package com.example.test.View.Dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.test.R;


public class DialogLoading extends AlertDialog {
    private Context mContext;

    public DialogLoading(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public DialogLoading(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public DialogLoading(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_dialog_progress);
        setCancelable(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
}
