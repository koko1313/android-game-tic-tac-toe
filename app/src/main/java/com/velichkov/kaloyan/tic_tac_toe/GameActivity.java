package com.velichkov.kaloyan.tic_tac_toe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    ImageView r1c1, r1c2, r1c3, r2c1, r2c2, r2c3, r3c1, r3c2, r3c3;
    ImageView winnerLineR1, winnerLineR2, winnerLineR3, winnerLineC1, winnerLineC2, winnerLineC3, winnerLineDR, winnerLineDL;
    TextView whosTurnTextView;
    ImageView whosTurnImageView;
    int turn = 0;
    int tictactoe [][] = {{0,0,0},{0,0,0},{0,0,0}};
    public static boolean vsComputer = false;
    public static int round = 0, winsX = 0, winsO = 0;
    android.os.Handler handler = new android.os.Handler(); // за delay

    // Ще генерираме random число 0, или 1, което ще определя кой ще играе първи
    Random rand = new Random();
    int firstTurn = rand.nextInt(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        r1c1 = (ImageView) findViewById(R.id.r1c1);
        r1c2 = (ImageView) findViewById(R.id.r1c2);
        r1c3 = (ImageView) findViewById(R.id.r1c3);
        r2c1 = (ImageView) findViewById(R.id.r2c1);
        r2c2 = (ImageView) findViewById(R.id.r2c2);
        r2c3 = (ImageView) findViewById(R.id.r2c3);
        r3c1 = (ImageView) findViewById(R.id.r3c1);
        r3c2 = (ImageView) findViewById(R.id.r3c2);
        r3c3 = (ImageView) findViewById(R.id.r3c3);
        whosTurnTextView = (TextView) findViewById(R.id.whosTurnTextView);
        whosTurnImageView = (ImageView) findViewById(R.id.whosTurnImageView);
        winnerLineR1 = (ImageView) findViewById(R.id.winnerLineR1);
        winnerLineR2 = (ImageView) findViewById(R.id.winnerLineR2);
        winnerLineR3 = (ImageView) findViewById(R.id.winnerLineR3);
        winnerLineC1 = (ImageView) findViewById(R.id.winnerLineC1);
        winnerLineC2 = (ImageView) findViewById(R.id.winnerLineC2);
        winnerLineC3 = (ImageView) findViewById(R.id.winnerLineC3);
        winnerLineDR = (ImageView) findViewById(R.id.winnerLineDR);
        winnerLineDL = (ImageView) findViewById(R.id.winnerLineDL);

        if(turn%2 == firstTurn) {
            whosTurnTextView.setText("Ред е на");
            whosTurnImageView.setImageResource(R.drawable.tictactoeo);
        } else {
            whosTurnTextView.setText("Ред е на");
            whosTurnImageView.setImageResource(R.drawable.tictactoex);
        }

        r1c1.setOnClickListener(onClick);
        r1c2.setOnClickListener(onClick);
        r1c3.setOnClickListener(onClick);
        r2c1.setOnClickListener(onClick);
        r2c2.setOnClickListener(onClick);
        r2c3.setOnClickListener(onClick);
        r3c1.setOnClickListener(onClick);
        r3c2.setOnClickListener(onClick);
        r3c3.setOnClickListener(onClick);

        if(turn%2 == firstTurn && vsComputer) computerTurn(); // Ако е ред на компютъра - играе
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.r1c1 : doTurn(r1c1,0,0); break;
                case R.id.r1c2 : doTurn(r1c2,0,1); break;
                case R.id.r1c3 : doTurn(r1c3,0,2); break;
                case R.id.r2c1 : doTurn(r2c1,1,0); break;
                case R.id.r2c2 : doTurn(r2c2,1,1); break;
                case R.id.r2c3 : doTurn(r2c3,1,2); break;
                case R.id.r3c1 : doTurn(r3c1,2,0); break;
                case R.id.r3c2 : doTurn(r3c2,2,1); break;
                case R.id.r3c3 : doTurn(r3c3,2,2); break;
            }
        }
    };

    protected void showWinnerLine(ImageView winnerRC){winnerRC.setVisibility(View.VISIBLE);}

    int turnType = 0; // глобална променлива за вида на хода (за него или ще затваря противника)
    boolean played = false; // глобална променлива, в която записваме дали е игран умен ход, защото ако не е - да играе произволен

    protected boolean compareCells(int r1, int c1, int r2, int c2) {return tictactoe[r1][c1] == tictactoe[r2][c2] && tictactoe[r1][c1] == turnType;}

    protected void doTurn(ImageView rc, int r, int c) {
        rc.setOnClickListener(null);

        if(turn%2 != firstTurn) {
            rc.setImageResource(R.drawable.tictactoex);
            tictactoe[r][c] = 1;
            whosTurnTextView.setText("Ред е на");
            whosTurnImageView.setImageResource(R.drawable.tictactoeo);
        }
        else {
            rc.setImageResource(R.drawable.tictactoeo);
            tictactoe[r][c] = 2;
            whosTurnTextView.setText("Ред е на");
            whosTurnImageView.setImageResource(R.drawable.tictactoex);
            played = true; // показва, че хода е изигран (служи ни когато играем срешу компютър)
        }
        turn++;

        if(!checkForWin() && vsComputer && turn%2 == firstTurn) computerTurn(); // проверява за победа и ако няма - играе ход за комютъра
    }

    protected void computerTurn() {
        played = false; // хода още не е изигран
        for(turnType = 2; turnType > 0; turnType--) { // първо играе ход за него, ако не играе - след това затваря противника
            if( tictactoe[0][0] == 0 && (compareCells(1,0, 2,0) || compareCells(0,1, 0,2) || compareCells(1,1, 2,2)) ){doTurn(r1c1,0,0);} // r1c1
            else
            if( tictactoe[1][0] == 0 && (compareCells(0,0, 2,0) || compareCells(1,1, 1,2)) ){doTurn(r2c1,1,0);} // r2c1
            else
            if( tictactoe[2][0] == 0 && (compareCells(0,0, 1,0) || compareCells(2,1, 2,2) || compareCells(0,2, 1,1) ) ){doTurn(r3c1,2,0);} // r3c1
            else
            if( tictactoe[0][1] == 0 && (compareCells(0,0, 0,2) || compareCells(1,1, 2,1)) ){doTurn(r1c2, 0, 1);} // r1c2
            else
            if( tictactoe[1][1] == 0 && (compareCells(0,1, 2,1) || compareCells(1,0, 1,2) || compareCells(0,0, 2,2) || compareCells(0,2, 2,0)) ){doTurn(r2c2, 1, 1);} // r2c2
            else
            if( tictactoe[2][1] == 0 && (compareCells(2,0, 2,2) || compareCells(0,1, 1,1)) ){doTurn(r3c2, 2, 1);} // r3c2
            else
            if( tictactoe[0][2] == 0 && (compareCells(0,0, 0,1) || compareCells(1,2, 2,2) || compareCells(2,0, 1,1)) ){doTurn(r1c3, 0, 2);} // r1c3
            else
            if( tictactoe[1][2] == 0 && (compareCells(0,2, 2,2) || compareCells(1,0, 1,1)) ){doTurn(r2c3, 1, 2);} // r2c3
            else
            if( tictactoe[2][2] == 0 && (compareCells(0,2, 1,2) || compareCells(2,0, 2,1) || compareCells(0,0, 1,1)) ){doTurn(r3c3, 2, 2);} // r3c3
        }
        if(!played) doRandomTurn(); // ако не е играло умен ход, прави произволен
    }

    protected void doRandomTurn() {
        ImageView numberImageView = null;
        int randomR = rand.nextInt(3);
        int randomC = rand.nextInt(3);

        switch (randomR) {
            case 0 :
                switch (randomC){
                    case 0 : numberImageView = r1c1; break;
                    case 1 : numberImageView = r1c2; break;
                    case 2 : numberImageView = r1c3; break;
                } break;
            case 1 :
                switch (randomC){
                    case 0 : numberImageView = r2c1; break;
                    case 1 : numberImageView = r2c2; break;
                    case 2 : numberImageView = r2c3; break;
                } break;
            case 2 :
                switch (randomC){
                    case 0 : numberImageView = r3c1; break;
                    case 1 : numberImageView = r3c2; break;
                    case 2 : numberImageView = r3c3; break;
                } break;
        }

        if(tictactoe[randomR][randomC] == 0) doTurn(numberImageView, randomR, randomC);
        else doRandomTurn();
    }

    protected boolean checkForWin() {
        boolean flag = false; // ако няма победа е false
        //проверка за първия ред
        if( (tictactoe[0][0] != 0) && (tictactoe[0][0] == tictactoe[0][1]) && (tictactoe[0][1] == tictactoe[0][2]) ) {
            showWinnerLine(winnerLineR1);
            if(tictactoe[0][0] == 1) endGame(1); else endGame(2);
            flag=true;
        } else

        //проверка за втория ред
        if( (tictactoe[1][0] != 0) && (tictactoe[1][0] == tictactoe[1][1]) && (tictactoe[1][1] == tictactoe[1][2]) ) {
            showWinnerLine(winnerLineR2);
            if(tictactoe[1][0] == 1) endGame(1); else endGame(2);
            flag=true;
        } else

        //проверка за третия ред
        if( (tictactoe[2][0] != 0) && (tictactoe[2][0] == tictactoe[2][1]) && (tictactoe[2][1] == tictactoe[2][2]) ) {
            showWinnerLine(winnerLineR3);
            if(tictactoe[2][0] == 1) endGame(1); else endGame(2);
            flag=true;
        } else

        //проверка за първата колона
        if( (tictactoe[0][0] != 0) && (tictactoe[0][0] == tictactoe[1][0]) && (tictactoe[1][0] == tictactoe[2][0]) ) {
            showWinnerLine(winnerLineC1);
            if(tictactoe[0][0] == 1) endGame(1); else endGame(2);
            flag=true;
        } else

        //проверка за втората колона
        if( (tictactoe[0][1] != 0) && (tictactoe[0][1] == tictactoe[1][1]) && (tictactoe[1][1] == tictactoe[2][1]) ) {
            showWinnerLine(winnerLineC2);
            if(tictactoe[0][1] == 1) endGame(1); else endGame(2);
            flag=true;
        } else

        //проверка за третата колона
        if( (tictactoe[0][2] != 0) && (tictactoe[0][2] == tictactoe[1][2]) && (tictactoe[1][2] == tictactoe[2][2]) ) {
            showWinnerLine(winnerLineC3);
            if(tictactoe[0][2] == 1) endGame(1); else endGame(2);
            flag=true;
        } else

        //проверка за десния диагонал
        if( (tictactoe[0][0] != 0) && (tictactoe[0][0] == tictactoe[1][1]) && (tictactoe[1][1] == tictactoe[2][2]) ) {
            showWinnerLine(winnerLineDR);
            if(tictactoe[0][0] == 1) endGame(1); else endGame(2);
            flag=true;
        } else

        //проверка за левия диагонал
        if( (tictactoe[0][2] != 0) && (tictactoe[0][2] == tictactoe[1][1]) && (tictactoe[1][1] == tictactoe[2][0]) ) {
            showWinnerLine(winnerLineDL);
            if(tictactoe[0][2] == 1) endGame(1); else endGame(2);
            flag=true;
        }

        //Без победител
        else if(turn == 9) {endGame(0); flag=true;}

        return flag;
    }

    protected void endGame(int who) {
        r1c1.setOnClickListener(null);
        r1c2.setOnClickListener(null);
        r1c3.setOnClickListener(null);
        r2c1.setOnClickListener(null);
        r2c2.setOnClickListener(null);
        r2c3.setOnClickListener(null);
        r3c1.setOnClickListener(null);
        r3c2.setOnClickListener(null);
        r3c3.setOnClickListener(null);

        whosTurnTextView.setText("Победител в рунда е");

        if(who == 1) {
            whosTurnImageView.setImageResource(R.drawable.tictactoex);
            Toast.makeText(getBaseContext(),"X печели рунда!", Toast.LENGTH_SHORT).show();
            winsX++;
            MenuActivity.xWinsTextView.setText("победи: " + winsX + "/3");
        } else
        if(who == 2) {
            whosTurnImageView.setImageResource(R.drawable.tictactoeo);
            Toast.makeText(getBaseContext(),"O печели рунда!", Toast.LENGTH_SHORT).show();
            winsO++;
            MenuActivity.oWinsTextView.setText("победи: " + winsO + "/3");
        } else {
            whosTurnTextView.setText("Рунда не беше спечелен от никого");
            whosTurnImageView.setVisibility(View.GONE);
            Toast.makeText(getBaseContext(),"Рунда не беше спечелен от никого!", Toast.LENGTH_SHORT).show();
        }
        round++;
        MenuActivity.roundsTextView.setText("Рунд " + round);

        if(winsX == 3 || winsO == 3) {
            if(who == 1) {
                MenuActivity.xWinsTextView.setText("Победител");
                MenuActivity.xWinsTextView.setTextColor(Color.GREEN);
                MenuActivity.oWinsTextView.setText("Загубил");
                MenuActivity.oWinsTextView.setTextColor(Color.RED);
                Toast.makeText(getBaseContext(),"X ПЕЧЕЛИ ИГРАТА!", Toast.LENGTH_LONG).show();
            } else
            if(who == 2) {
                MenuActivity.oWinsTextView.setText("Победител");
                MenuActivity.oWinsTextView.setTextColor(Color.GREEN);
                MenuActivity.xWinsTextView.setText("Загубил");
                MenuActivity.xWinsTextView.setTextColor(Color.RED);
                Toast.makeText(getBaseContext(),"O ПЕЧЕЛИ ИГРАТА!", Toast.LENGTH_LONG).show();
            }

            MenuActivity.startGameButton.setEnabled(false);
            MenuActivity.startGameVsComputerButton.setEnabled(false);
        }

        turn = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                tictactoe[i][j] = 0;
            }
        }

        MenuActivity.resetButton.setEnabled(true);

        // забавяме затварянето
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}
