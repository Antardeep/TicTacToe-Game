package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean active = true;
    int activeUser = 1;
    int[] state = {0,0,0,0,0,0,0,0,0};
    int[][] winState = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6} };
    public void value(View v){
        if(!active){
            resetGame(v);
        }
        final LinearLayout model, model_back;
        model = findViewById(R.id.model);
        model_back = findViewById(R.id.model_back);
        final ImageView icon = findViewById(R.id.icon);
        TextView winnerText = findViewById(R.id.winner_text);
        Button back, home;
        back = findViewById(R.id.back);
        home = findViewById(R.id.home);

        final Animation anim_model, anim_show, anim_icon, anim_model_reverse;
        anim_model = AnimationUtils.loadAnimation(this, R.anim.anim_model);
        anim_show = AnimationUtils.loadAnimation(this, R.anim.anim_show);
        anim_icon = AnimationUtils.loadAnimation(this, R.anim.anim_icon);
        anim_model_reverse = AnimationUtils.loadAnimation(this, R.anim.anim_model_reverse);

        TextView userX = findViewById(R.id.user_x);
        TextView userO = findViewById(R.id.user_o);
        ImageView imageView = (ImageView) v;
        int clickedImage = Integer.parseInt(imageView.getTag().toString());

        if( state[clickedImage] == 0 ) {
            state[clickedImage] = activeUser;
            if(activeUser == 1){
                imageView.setImageResource(R.drawable.x);
                activeUser = 2;
                userX.setVisibility(View.GONE);
//                userO.setBackgroundColor(Color.parseColor("#32CD32"));
                userO.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_move_left);
                userO.startAnimation(animation);
            }else {
                imageView.setImageResource(R.drawable.o);
                activeUser = 1;
                userO.setVisibility(View.GONE);
//                userX.setBackgroundColor(Color.parseColor("#32CD32"));
                userX.setVisibility(View.VISIBLE);
                Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.anim_move_right);
                userX.startAnimation(animation1);
            }
        }
//        imageView.animate().translationYBy(1000f).setDuration(300);

        // Check if any player has won
        for(int[] winState: winState){
            if(state[winState[0]] == state[winState[1]] &&
                    state[winState[1]] == state[winState[2]] &&
                    state[winState[0]]!=0){
                // Somebody has won! - Find out who!
//                String winnerStr;
                active = false;
                if(state[winState[0]] == 1){
                    winnerText.setText("User X");
                }
                else{
                    winnerText.setText("User O");
                }

                icon.setVisibility(View.VISIBLE);
                icon.startAnimation(anim_icon);
                model.setAlpha(1);
                model.startAnimation(anim_model);
                model_back.setAlpha(1);
                model_back.startAnimation((anim_show));

            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame(view);
                icon.setVisibility(View.GONE);
                model.startAnimation(anim_model_reverse);
                model_back.setAlpha(0);

                ViewCompat.animate(model).setStartDelay(1000).alpha(0).start();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homePage = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homePage);
                overridePendingTransition(R.anim.anim_show,R.anim.anim_hide);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button resetButton = findViewById(R.id.reset);
        final Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.anim_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation4);
                resetGame(v);
            }
        });
    }

    public void resetGame(View v) {
        active = true;
        activeUser = 1;
        for(int i=0; i<state.length; i++){
            state[i] = 0;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView userX = findViewById(R.id.user_x);
        TextView userO = findViewById(R.id.user_o);
        userO.setVisibility(View.GONE);
        userX.setVisibility(View.VISIBLE);

    }
}
