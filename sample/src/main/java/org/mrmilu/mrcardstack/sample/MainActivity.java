package org.mrmilu.mrcardstack.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import org.mrmilu.mrcardstack.CardStackView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CardStackView cardStackView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardStackView = (CardStackView) findViewById(R.id.card_stack_view);


        cardStackView.onFirstCardLoaded(new CardStackView.FirstElementLoadedOnStackListener() {
            @Override public void loaded(Object peek) {

            }
        });

        cardStackView.onCardStackFinishQueueListener(new CardStackView.CardStackFinishQueueListener() {
            @Override public void finish() {

            }
        });

        cardStackView.onCardStackDismissElement(new CardStackView.CardStackDismissElementListener() {
            @Override
            public void dismiss(Object view) {

            }
        });

        final ArrayList<View> views = new ArrayList<>();
        final View view = LayoutInflater.from(this).inflate(R.layout.random_view, null);

        views.add(view);
        views.add(view);
        views.add(view);

        cardStackView.addViews(views);

        findViewById(R.id.card_swipe_left).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cardStackView.dismissCardToLeft();
            }
        });

        findViewById(R.id.card_reload).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                cardStackView.addViews(views);
            }
        });
    }
}
