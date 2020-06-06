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
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

/**
 * @author Faramarz Afzali
 * @since Feb 2, 2019
 */
public class FastEditText extends LinearLayout {


    private View rootView;
    private TypedArray typedArray;
    private ImageView editTextIcon;
    private EditText mainEditText;
    private Drawable srcIcon;
    private int defaultTextColor;
    private int inputType;
    private int maxLength;
    private int backgroundIconColor;
    private float textSize;
    private String hint;
    private String defaultText;
    private String fontFamily;


    public FastEditText(Context context) {
        super(context);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FastEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setHint(attrs);
        setDefaultText(attrs);
        setInputType(attrs);
        setDefaultTextColor(attrs);
        setIconResource(attrs);
        setBackgroundIconColor(attrs);
        setTextSize(attrs);
        setFont(context, attrs);
        setMaxLength(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FastEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
        setMaxLength(context, attrs);
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
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.FastEditText);
        hint = typedArray.getString(R.styleable.FastEditText_hint);
        mainEditText.setHint(hint);
        typedArray.recycle();
    }

    private void setDefaultText(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.FastEditText);
        defaultText = typedArray.getString(R.styleable.FastEditText_defaultText);
        mainEditText.setText(defaultText);
        typedArray.recycle();
    }


    private void setInputType(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.FastEditText);
        inputType = typedArray.getInt(R.styleable.FastEditText_inputType, 0);
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
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.FastEditText);
        defaultTextColor = typedArray.getInt(R.styleable.FastEditText_defaultTextColor, Color.BLACK);
        mainEditText.setTextColor(defaultTextColor);
        typedArray.recycle();
    }

    private void setIconResource(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.FastEditText);
        srcIcon = typedArray.getDrawable(R.styleable.FastEditText_srcIconEditText);
        editTextIcon.setImageDrawable(srcIcon);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setBackgroundIconColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.FastEditText);
        backgroundIconColor = typedArray.getColor(R.styleable.FastEditText_backgroundIconColor, Color.GRAY);
        editTextIcon.setBackgroundTintList(ColorStateList.valueOf(backgroundIconColor));
    }


    private void setTextSize(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.FastEditText);
        textSize = typedArray.getDimension(R.styleable.FastEditText_textSize, 18f);
        mainEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    private void setFont(Context ctx, AttributeSet attrs) {
        checkNullSet(attrs);
        typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.FastEditText);
        fontFamily = typedArray.getString(R.styleable.FastEditText_customFontFamily);
        prepareFont(ctx, fontFamily);
        typedArray.recycle();
    }

    private void setMaxLength(Context ctx, AttributeSet attrs) {
        checkNullSet(attrs);
        typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.FastEditText);
        maxLength = typedArray.getInteger(R.styleable.FastEditText_maxLength, Integer.MAX_VALUE);
        setMaxLength(maxLength);
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

    public String getText() {
        return mainEditText.getText().toString();
    }

    public int getLength() {
        return mainEditText.length();
    }

    public void setText(String text) {
        mainEditText.setText(text);
    }

    private void setMaxLength(final int maxLength) {
        mainEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lent = mainEditText.length();
                if (lent >= maxLength) {
                    blockTextView(maxLength);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void blockTextView(int maxLength) {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(maxLength);
        mainEditText.setFilters(filterArray);
    }

}
