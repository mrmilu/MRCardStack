package org.mrmilu.mrcardstack;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Project: shakn.android
 *
 * Created by Jorge Garrido Oval on 11/5/16.
 * Copyright © Webpartners 2016
 */
public class CardView extends BaseCardView {

    public CardView(Context context) {
        super(context);
        this.init();
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        inflate(getContext(), R.layout.mr_card_view, this);
    }
}
