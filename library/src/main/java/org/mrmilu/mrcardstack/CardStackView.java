package org.mrmilu.mrcardstack;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Project: shakn.android
 *
 * Created by Jorge Garrido Oval on 11/5/16.
 * Copyright © Webpartners 2016
 */
public class CardStackView<T> extends FrameLayout {

    /**
     * In dp's
     */
    public static int CARD_STACK_PADDING_TOP = 7;
    public static int CARD_STACK_VISIBLE_NUM = 3;

    private Queue<View> cards;
    private boolean animationFinished = true;

    private CardStackFinishQueueListener callback;
    private CardStackDismissElementListener callbackElement;
    private FirstElementLoadedOnStackListener firstCallback;

    public boolean isEmpty() {
        return this.getChildCount() == 0;
    }

    public interface FirstElementLoadedOnStackListener<T> {
        void loaded(T peek);
    }

    public interface CardStackFinishQueueListener {
        void finish();
    }

    public interface CardStackDismissElementListener<T> {
        void dismiss(T view);
    }

    public CardStackView(Context context) {
        super(context);
    }

    public CardStackView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardStackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onFirstCardLoaded(FirstElementLoadedOnStackListener<T> callback) {
        this.firstCallback = callback;
    }

    public void onCardStackFinishQueueListener(CardStackFinishQueueListener callback) {
        this.callback = callback;
    }

    public void onCardStackDismissElement(CardStackDismissElementListener<T> callbackElement) {
        this.callbackElement = callbackElement;
    }

    @SuppressWarnings("unchecked") public void addViews(Collection views) {
        cards = new LinkedList<>();
        cards.addAll(views);
        int size = cards.size() > CARD_STACK_VISIBLE_NUM ? CARD_STACK_VISIBLE_NUM : cards.size();

        for (int i = 0; i < size; i++)
            this.addView(cards.poll());

        firstCallback.loaded(getChildAt(getChildCount() - 1));

        this.redrawPaddings();
    }

    public void dismissCardToLeft() {
        if (animationFinished && cards != null && getChildCount() > 0) {
            final View v = getChildAt(getChildCount() - 1);
            v.animate().alpha(0).rotation(-30.f).translationXBy(-500).setDuration(500).setListener(new Animator.AnimatorListener() {
                @Override public void onAnimationStart(Animator animation) {
                    animationFinished = false;
                }

                @Override public void onAnimationEnd(Animator animation) {
                    animationFinished = true;

                    removeView(v);

                    if (getChildCount() == 0 && callback != null)
                        callback.finish();
                    else
                        addNewView();
                }

                @Override public void onAnimationCancel(Animator animation) {
                    animationFinished = true;

                    if (cards.size() == 0 && callback != null)
                        callback.finish();
                }

                @Override public void onAnimationRepeat(Animator animation) {}
            });
        }
    }

    @SuppressWarnings("unchecked")private void addNewView() {
        View[] views;

        View v = cards.poll();

        int index = 0;
        if (v != null) {
            views = new View[getChildCount() + 1];
            views[0] = v;
            views[0].setPadding(0, (int) pxFromDp(getContext(), 0), 0, 0);
            index = 1;
        } else {
            views = new View[getChildCount()];
        }

        for (int i = 0; i < getChildCount(); i++) {
            views[index] = getChildAt(i);
            views[index].setPadding(0, (int) pxFromDp(getContext(), CARD_STACK_PADDING_TOP * index), 0, 0);
            index++;
        }
        removeAllViews();

        for (View view : views) addView(view);

        callbackElement.dismiss(getChildAt(getChildCount() - 1));
    }

    private void redrawPaddings() {
        for (int i = 0; i < getChildCount(); i++)
            getChildAt(i).setPadding(0, (int) pxFromDp(getContext(), i * CARD_STACK_PADDING_TOP), 0, 0);
    }

    private float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
