package net.naylinaung.meal_ordering.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import net.naylinaung.meal_ordering.utils.MMFontUtils;

/**
 * Created by Dell on 8/27/2016.
 */
public class MMEditText extends EditText {

    public MMEditText(Context context) {
        super(context);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }
}