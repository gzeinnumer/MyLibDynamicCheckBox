package com.gzeinnumer.mylibdynamiccheckbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DynamicCheckBox extends LinearLayout {
    private Context _context;
    private AttributeSet _attrs;
    private int _cbStyle = R.style.def_checkBoxStyle;

    private final ArrayList<Object> sendArray = new ArrayList<>();

    private OnCheckedChangeListener onCheckedChangeListener;

    public DynamicCheckBox(Context context) {
        this(context, null);
    }

    public DynamicCheckBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this._context = context;
        this._attrs = attrs;

        initView(View.VISIBLE);
    }

    public interface OnCheckedChangeListener<T> {
        void onCheckedChanged(ArrayList<T> items);

        void onCheckedShow(String clickedValue);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public <T> DynamicCheckBox setItemList(List<T> items) {
        initView(items);
        return this;
    }

    private <T> void initView(List<T> items) {
        initView(GONE);

        inflate(_context, R.layout.dynamic_check_box, this);

        LinearLayout linearLayout = findViewById(R.id.parent);

        TypedArray attributes = _context.obtainStyledAttributes(_attrs, R.styleable.DynamicCheckBox);

        if (attributes.getResourceId(R.styleable.DynamicCheckBox_style, -1) != -1)
            _cbStyle = attributes.getResourceId(R.styleable.DynamicCheckBox_style, -1);

        for (int i = 0; i < items.size(); i++) {
            final CheckBox checkBox = new CheckBox(_context);
            checkBox.setTextAppearance(_context, _cbStyle);
            checkBox.setText(String.valueOf(items.get(i).toString()));
            checkBox.setId(i);

            final int finalI = i;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (onCheckedChangeListener != null) {
                        addToArray(items.get(finalI), isChecked);
                    }
                }
            });
            linearLayout.addView(checkBox);
        }

        attributes.recycle();
    }

    private <T> void addToArray(T data, boolean isAdd) {
        if (isAdd) {
            sendArray.add(data);
        } else {
            sendArray.remove(data);
        }

        onCheckedChangeListener.onCheckedChanged(sendArray);
        String valueStr = "";

        for (int i = 0; i < sendArray.size(); i++)
            valueStr = valueStr + sendArray.get(i).toString() + ",";

        if (valueStr.length() > 0)
            onCheckedChangeListener.onCheckedShow(valueStr.substring(0, valueStr.length() - 1));
        else
            onCheckedChangeListener.onCheckedShow("");

    }

    private void initView(int isVisible) {
        inflate(_context, R.layout.dynamic_check_box, this);

        TypedArray attributes = _context.obtainStyledAttributes(_attrs, R.styleable.DynamicCheckBox);

        CheckBox checkBox = findViewById(R.id.dummy);
        checkBox.setVisibility(isVisible);

        if (attributes.getResourceId(R.styleable.DynamicCheckBox_style, -1) != -1)
            _cbStyle = attributes.getResourceId(R.styleable.DynamicCheckBox_style, -1);
        checkBox.setTextAppearance(_context, _cbStyle);

        attributes.recycle();
    }
}
