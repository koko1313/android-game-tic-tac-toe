package com.velichkov.kaloyan.tic_tac_toe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    public static TextView roundsTextView, xWinsTextView, oWinsTextView;
    public static Button startGameButton, resetButton, aboutButton, startGameVsComputerButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        roundsTextView = (TextView) findViewById(R.id.roundsTextView);
        xWinsTextView = (TextView) findViewById(R.id.xWinsTextView);
        oWinsTextView = (TextView) findViewById(R.id.oWinsTextView);
        startGameButton = (Button) findViewById(R.id.startGameButton);
        startGameVsComputerButton = (Button) findViewById(R.id.startGameVsComputerButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        aboutButton = (Button) findViewById(R.id.aboutButton);
        exitButton = (Button) findViewById(R.id.exitButton);

        startGameButton.setOnClickListener(onClick);
        startGameVsComputerButton.setOnClickListener(onClick);
        resetButton.setOnClickListener(onClick);
        aboutButton.setOnClickListener(onClick);
        exitButton.setOnClickListener(onClick);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reset();
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.startGameVsComputerButton :
                    GameActivity.vsComputer = true;
                    intent = new Intent(MenuActivity.this, GameActivity.class); startActivity(intent); break;
                case R.id.startGameButton :
                    GameActivity.vsComputer = false;
                    intent = new Intent(MenuActivity.this, GameActivity.class); startActivity(intent); break;
                case R.id.resetButton : reset(); break;
                case R.id.aboutButton : intent = new Intent(MenuActivity.this, AboutActivity.class); startActivity(intent); break;
                case R.id.exitButton : finish();
            }
        }
    };

    public void reset(){
        GameActivity.round = 0;
        GameActivity.winsO = 0;
        GameActivity.winsX = 0;
        roundsTextView.setText("Рунд 0");
        xWinsTextView.setText("победи: 0/3");
        oWinsTextView.setText("победи: 0/3");
        xWinsTextView.setTextColor(Color.GRAY);
        oWinsTextView.setTextColor(Color.GRAY);
        startGameButton.setEnabled(true);
        startGameVsComputerButton.setEnabled(true);
        resetButton.setEnabled(false);
    }

}
