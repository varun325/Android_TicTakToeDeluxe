package com.example.tictaktoedeluxe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //red:1,yellow:0,empty:2

    int gamestate[]={2,2,2,2,2,2,2,2,2,}; //all  of each tagthe game states of each tag
    int winningstates[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}}; // the patterns that means that someone has won

    int activeplayer=0; //tells you that whose turn is gong on
    String winner=""; // the string on the top that is invisible till someone has won
    boolean gameactive=true; // signifies is someone has won or if the game is active or not
    int redcount=0; // score of the red player
    int yellowcount=0; //score of the yellow player


    public void dropin(View view){ //definition of the method for each drop in of the resources it also has teh code and algorithm for deciding the winner and updating the scores


        ImageView counter=(ImageView) view;

        TextView redscore =(TextView) findViewById(R.id.redscore);
        TextView yellowscore =(TextView) findViewById(R.id.yellowscore);

        int tappedCounter= Integer.parseInt(counter.getTag().toString()); //counter for which position has been clicked
        Log.i("tapped counter",counter.getTag().toString());

        if(gamestate[tappedCounter]==2 && gameactive){ //check if a game is already active so that someone can't randomly change the values
            counter.setTranslationY(-1500); //keeps the resources on the top of he screen
            gamestate[tappedCounter]=activeplayer;
            if(activeplayer == 0){ //alternates between the turns of the red and yellow player
                counter.setImageResource(R.drawable.yellow);
                activeplayer=1;
            }
            else{
                counter.setImageResource(R.drawable.red);
                activeplayer=0;
            }
            counter.animate().translationYBy(1500).rotation(720).setDuration(500); //puts the resources at the desired position with a spining and falling animation

            for(int[] winningstate : winningstates){ //checks all the possible winning states

                if(gamestate[winningstate[0]]==gamestate[winningstate[1]] && gamestate[winningstate[1]]==gamestate[winningstate[2]] && gamestate[winningstate[0]]!=2){ //used to check if someone has won

                    gameactive=false; //someone has won so the game is now not active until play again is pressed
                    if(activeplayer==0){

                        winner="Red";
                        redcount+=1; //increments the score of the red player
                        String redred=Integer.toString(redcount);

                        redscore.setText("Red: "+redred); //displays the score of the red player


                    }

                    else{
                        winner="Yellow";
                        yellowcount+=1; //increments he score of the yellow player
                        String yelyel=Integer.toString(yellowcount);
                        yellowscore.setText("Yellow: "+yelyel); //displays the score of the yellow player


                    }

                    TextView text2= (TextView) findViewById(R.id.Scorecard);
                    Button btn1=(Button) findViewById(R.id.replay);
                    text2.setText(winner.toString()+" has won!!"); //toasts that someone has won
                    text2.setVisibility(View.VISIBLE);
                    Toast.makeText(this, winner+" has won", Toast.LENGTH_SHORT).show(); // displays who has won on the top of the screen


                }
            }



        }

        else{

            if(winner==""){
                Toast.makeText(this, "don't cheat ;)", Toast.LENGTH_SHORT).show(); //displays text in case someone is trying to cheat
            }

            else{
                Toast.makeText(this, winner+" has already won", Toast.LENGTH_SHORT).show(); //displays text if someone tries to play after someone has alreday won

            }

        }



    }



    public  void gameReplay(View view){ //definition of the method for play again

        TextView text2= (TextView) findViewById(R.id.Scorecard);
        Button btn1=(Button) findViewById(R.id.replay);
        text2.setVisibility(View.INVISIBLE); //sets the winners name back to invisible


       // GridLayout grid1=(GridLayout) findViewById(R.id.gridLayout); // this didn't work coz of marshmallow maybe

        android.support.v7.widget.GridLayout grid1 = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout); //ways to create a variable for the grid layout

        for(int i=0; i<grid1.getChildCount(); i++) { //iterates through all the children of the grid layout
            ImageView child = (ImageView) grid1.getChildAt(i);
            // do stuff with child view

            child.setImageDrawable(null); //setting each child to null

        }

        for (int i=0;i<gamestate.length;i++){ //iterates through all the game states
            gamestate[i]=2; //setting the gamestates of all the slots back to empty
        }

        activeplayer=0; //first player is set back to yellow
        winner=""; // winner title card is set back to null and invisible
        gameactive=true; // game state is active once again

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
