package org.mrmilu.mrcardstack;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Project: shakn.android
 *
 * Created by Jorge Garrido Oval on 11/5/16.
 * Copyright © Webpartners 2016
 */
public class BaseCardView extends FrameLayout {

    public BaseCardView(Context context) {
        super(context);
        this.init();
    }

    public BaseCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public BaseCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {}
}
