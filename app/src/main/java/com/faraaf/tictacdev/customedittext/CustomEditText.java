package com.faraaf.tictacdev.customedittext;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

public class CustomEditText extends LinearLayout {

    private View rootView;
    private TypedArray typedArray;
    private ImageView editTextIcon;
    private EditText mainEditText;
    private Drawable srcIcon;
    private int defaultTextColor;
    private int inputType;
    private int backgroundIconColor;
    private float textSize;
    private String hint;
    private String defaultText;
    private String fontFamily;


    public CustomEditText(Context context) {
        super(context);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setHint(attrs);
        setDefaultText(attrs);
        setInputType(attrs);
        setDefaultTextColor(attrs);
        setIconResource(attrs);
        setBackgroundIconColor(attrs);
        setTextSize(attrs);
        setFont(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setHint(attrs);
        setDefaultText(attrs);
        setInputType(attrs);
        setDefaultTextColor(attrs);
        setIconResource(attrs);
        setBackgroundIconColor(attrs);
        setTextSize(attrs);
        setFont(context, attrs);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init(Context context) {
        setSaveEnabled(true);
        rootView = inflate(context, R.layout.layout_custom_edittext, this);
        editTextIcon = rootView.findViewById(R.id.editTextIcon);
        mainEditText = rootView.findViewById(R.id.mainEditText);
    }

    private void setHint(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomEditText);
        hint = typedArray.getString(R.styleable.CustomEditText_hint);
        mainEditText.setHint(hint);
        typedArray.recycle();
    }

    private void setDefaultText(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomEditText);
        defaultText = typedArray.getString(R.styleable.CustomEditText_defaultText);
        mainEditText.setText(defaultText);
        typedArray.recycle();
    }


    private void setInputType(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomEditText);
        inputType = typedArray.getInt(R.styleable.CustomEditText_inputType, 0);
        switch (inputType) {
            case 0:
                mainEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;

            case 1:
                mainEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                break;

            case 2:
                mainEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;

            case 3:
                mainEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
        }
        typedArray.recycle();
    }


    private void setDefaultTextColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomEditText);
        defaultTextColor = typedArray.getInt(R.styleable.CustomEditText_defaultTextColor, Color.BLACK);
        mainEditText.setTextColor(defaultTextColor);
        typedArray.recycle();
    }

    private void setIconResource(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomEditText);
        srcIcon = typedArray.getDrawable(R.styleable.CustomEditText_srcIconEditText);
        editTextIcon.setImageDrawable(srcIcon);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setBackgroundIconColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomEditText);
        backgroundIconColor = typedArray.getColor(R.styleable.CustomEditText_backgroundIconColor, Color.GRAY);
        editTextIcon.setBackgroundTintList(ColorStateList.valueOf(backgroundIconColor));
    }


    private void setTextSize(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.CustomEditText);
        textSize = typedArray.getDimension(R.styleable.CustomEditText_textSize, 18f);
        mainEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    private void setFont(Context ctx, AttributeSet attrs) {
        checkNullSet(attrs);
        typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.CustomEditText);
        fontFamily = typedArray.getString(R.styleable.CustomEditText_customFontFamily);
        prepareFont(ctx, fontFamily);
        typedArray.recycle();
    }

    public boolean prepareFont(Context ctx, String asset) {
        Typeface tf;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), asset);
        } catch (Exception e) {
            Log.e("TAG", "Could not get typeface: " + e.getMessage());
            return false;
        }
        mainEditText.setTypeface(tf);
        return true;
    }

    private void checkNullSet(AttributeSet set) {
        if (set == null) {
            return;
        }
    }

}
