package com.example.test.View.Dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.R;


public class ConfrimDialog extends AlertDialog implements View.OnClickListener {

    private Context mContext;

    private TextView mTv;
    private Button mBtnYes;
    private Button mBtnNo;

    private OnAceptListener onAceptListener;

    //accept listener
    public interface OnAceptListener {
        void setOnAceptListener();
    }

    public void setOnAceptListener(OnAceptListener onAceptListener) {
        this.onAceptListener = onAceptListener;
    }

    public ConfrimDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
        this.onAceptListener = (OnAceptListener) context;
    }

    public ConfrimDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public ConfrimDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_dialog);

        //InitView
        mTv = findViewById(R.id.tv_title_confirm_dialog);

        mBtnYes = findViewById(R.id.btn_yes_confirm_dialog);
        mBtnYes.setOnClickListener(this);
        mBtnNo = findViewById(R.id.btn_no_confirm_dialog);
        mBtnNo.setOnClickListener(this);

    }

    /**
     * @return
     */
    public TextView getTv() {
        return mTv;
    }

    /**
     * @return
     */
    public Button getmBtnYes() {
        return mBtnYes;
    }

    /**
     * @return
     */
    public Button getBtnNo() {
        return mBtnNo;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_no_confirm_dialog:
                break;
            case R.id.btn_yes_confirm_dialog:
                this.onAceptListener.setOnAceptListener();
                break;
        }
        dismiss();
    }
}
