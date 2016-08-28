package net.naylinaung.meal_ordering.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import net.naylinaung.meal_ordering.utils.MMFontUtils;

/**
 * Created by Dell on 8/27/2016.
 */
public class MMButton extends Button {

    public MMButton(Context context) {
        super(context);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }

    public MMButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            MMFontUtils.setMMFont(this);
    }
}
