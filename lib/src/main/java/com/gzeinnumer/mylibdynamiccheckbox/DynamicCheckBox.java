package com.gzeinnumer.mylibdynamiccheckbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DynamicCheckBox extends LinearLayout {
    private final Context _context;
    private final AttributeSet _attrs;
    private int _cbStyle = R.style.def_checkBoxStyle;
    private int _orientation = VERTICAL;

    private final ArrayList<Object> sendArray = new ArrayList<>();

    private OnCheckedChangeListener onCheckedChangeListener;

    public DynamicCheckBox(Context context) {
        this(context, null);
    }

    public DynamicCheckBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this._context = context;
        this._attrs = attrs;

        // Set Layout
        initView(sendArray);
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
        TypedArray attributes = _context.obtainStyledAttributes(_attrs, R.styleable.DynamicCheckBox);

        if (attributes.getInt(R.styleable.DynamicCheckBox_orientation, 1) == 0)
            _orientation = HORIZONTAL;

        setOrientation(_orientation);

        if (attributes.getResourceId(R.styleable.DynamicCheckBox_style, -1) != -1)
            _cbStyle = attributes.getResourceId(R.styleable.DynamicCheckBox_style, -1);

        LinearLayout linearLayout = new LinearLayout(_context);
        linearLayout.setOrientation(_orientation);

        CheckBox checkBoxPreview = new CheckBox(_context);
        checkBoxPreview.setId(View.generateViewId());
        checkBoxPreview.setText("Dynamic CheckBox");
        checkBoxPreview.setTextAppearance(_context, _cbStyle);
        if (items.isEmpty()) {
            addView(checkBoxPreview);
        } else {
            removeViewAt(0);

            // Add Item Set User
            for (int i = 0; i < items.size(); i++) {
                final CheckBox checkBox = new CheckBox(_context);
                checkBox.setTextAppearance(_context, _cbStyle);
                checkBox.setText(items.get(i).toString());
                checkBox.setId(i);

                final int finalI = i;
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (onCheckedChangeListener != null) {
                        addToArray(items.get(finalI), isChecked);
                    }
                });
                linearLayout.addView(checkBox);
            }

            attributes.recycle();
            addView(linearLayout);
        }
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

    @Override
    protected void removeDetachedView(View child, boolean animate) {
        super.removeDetachedView(child, false);
    }
}