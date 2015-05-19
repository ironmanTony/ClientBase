package com.qunar.ironman.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qunar.ironman.R;

/**
 * Created by ironmanli on 15-4-13.
 */
public class ImageButton extends LinearLayout{

    private ImageView imageView;
    private TextView textView;


    public ImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_widget_image_button, this, true);
        imageView = (ImageView) findViewById(R.id.widget_ib_iv);
        textView = (TextView) findViewById(R.id.widget_ib_tv);
        handleAttrs(context, attrs);
    }

    private void handleAttrs(Context context, AttributeSet attrs){
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ImButton, 0, 0);
        // get values;
        Drawable src = null;
        String text = "";
        int padding = -1;
        try {
             src = array.getDrawable(R.styleable.ImButton_src);
             text = array.getString(R.styleable.ImButton_text);
             padding = array.getInt(R.styleable.ImButton_padding, -1);

        }finally {
            array.recycle();
        }
        //set values
        if(src != null){
            imageView.setImageDrawable(src);
        }
        if(padding!=-1){
            imageView.setPadding(padding, padding, padding, padding);
        }
        textView.setText(text);
    }
}
