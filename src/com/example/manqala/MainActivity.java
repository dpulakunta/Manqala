package com.example.manqala;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	public int[] player1Pit = new int[5];
	public int[] player2Pit = new int[5];
	public int player1Capture;
	public int player2Capture;
	TextView t1;
	public static final String TAG = "Mancala";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeBoard();
	}
	public void onPressed(View v){
		int buttonClicked = v.getId();
		switch(buttonClicked){
		case R.id.button11:
			playGame("player1", 0);
			Log.i(TAG, "button pressed:1");
			break;
		case R.id.button12:
			playGame("player1", 1);
			Log.i(TAG, "button pressed:2");
			break;
		case R.id.button13:
			playGame("player1", 2);
			Log.i(TAG, "button pressed:3");
			break;
		case R.id.button14:
			playGame("player1", 3);
			Log.i(TAG, "button pressed:4");
			break;
		case R.id.button15:
			playGame("player1", 4);
			Log.i(TAG, "button pressed:5");
			break;
		}
		int pitN=2;
		checkPlayer1Cap(pitN); 
		printPit();
		isGameOver();
		Random rand = new Random();
		int n = rand.nextInt(5);
		TextView option = (TextView) findViewById(R.id.moveMade);
		String[] stringOption = option.getText().toString().split(":");
		option.setText(stringOption[0]+":"+Integer.toString((n+1)));
		Log.i(TAG, "value of random number:"+n);
		playGame("player2",n);
		checkPlayer2Cap(pitN);
		printPit();
		isGameOver();
	}
	private void checkPlayer2Cap(int pitNumber) {
		int capture=0;
		while(player1Pit[pitNumber]==2 || player1Pit[pitNumber]==3) {
			capture+=player1Pit[pitNumber];
			player1Pit[pitNumber]=0;
			pitNumber--;
		}
		TextView player2Capt = (TextView) findViewById(R.id.player2Capture);
		String val = (String) player2Capt.getText();
		String[] res = val.split(":");
		int result = Integer.parseInt(res[1]);
		result+=capture;
		player2Capture=result;
		player2Capt.setText(res[0]+":"+Integer.toString(result));
		
	}
	private void isGameOver() {
		if(player1Capture>25 || player2Capture>25){
			Log.i(TAG, "Game Over");
		}
		
	}
	private void checkPlayer1Cap(int pitNumber) {
		int capture=0;
		while(player2Pit[pitNumber]==2 || player2Pit[pitNumber]==3) {
			capture+=player2Pit[pitNumber];
			player2Pit[pitNumber]=0;
			pitNumber--;
		}
		TextView player1Capt = (TextView) findViewById(R.id.playerCapture);
		String val = (String) player1Capt.getText();
		String[] res = val.split(":");
		int result = Integer.parseInt(res[1]);
		result+=capture;
		player1Capture=result;
		player1Capt.setText(res[0]+":"+Integer.toString(result));
	}
	private void printPit() {
		for(int i=0;i<player1Pit.length;i++)
			Log.i(TAG,"P1 value in pit "+(i+1)+" "+player1Pit[i]);
		for(int i=0;i<player2Pit.length;i++)
			Log.i(TAG,"P2 value in pit "+(i+1)+" "+player2Pit[i]);
		TextView p1t1 = (TextView) findViewById(R.id.pitP11);
		p1t1.setText(Integer.toString(player1Pit[0]));
		p1t1 = (TextView) findViewById(R.id.pitP12);
		p1t1.setText(Integer.toString(player1Pit[1]));
		p1t1 = (TextView) findViewById(R.id.pitP23);
		p1t1.setText(Integer.toString(player1Pit[2]));
		p1t1 = (TextView) findViewById(R.id.pitP14);
		p1t1.setText(Integer.toString(player1Pit[3]));
		p1t1 = (TextView) findViewById(R.id.pitP15);
		p1t1.setText(Integer.toString(player1Pit[4]));
		p1t1 = (TextView) findViewById(R.id.pitP21);
		p1t1.setText(Integer.toString(player2Pit[0]));
		p1t1 = (TextView) findViewById(R.id.pitP22);
		p1t1.setText(Integer.toString(player2Pit[1]));
		p1t1 = (TextView) findViewById(R.id.pitP13);
		p1t1.setText(Integer.toString(player2Pit[2]));
		p1t1 = (TextView) findViewById(R.id.pitP24);
		p1t1.setText(Integer.toString(player2Pit[3]));
		p1t1 = (TextView) findViewById(R.id.pitP25);
		p1t1.setText(Integer.toString(player2Pit[4]));
		
		
	}
	private void playGame(String playerNumber, int pitNumber) {
		if(playerNumber=="player1"){
			int numberOfBeans = player1Pit[pitNumber];
			player1Pit[pitNumber] = 0;
			int pitIndex=pitNumber;
			pitIndex++;
			while(numberOfBeans>0){
				for(;pitIndex<player1Pit.length;pitIndex++){
					player1Pit[pitIndex]++;
					numberOfBeans--;
					if(numberOfBeans==0)
						return;
				}
				pitIndex--;
				Log.i(TAG, "pitIndex:"+pitIndex);
				for( ;pitIndex>=0;pitIndex--){
					player2Pit[pitIndex]++;
					numberOfBeans--;
					if(numberOfBeans==0)
						return;
				}
			}
			
		} else{
			Log.i(TAG, "In player2 choice:"+pitNumber);
			int numberOfBeans = player2Pit[pitNumber];
			player2Pit[pitNumber] = 0;
			int pitIndex=pitNumber;
			pitIndex--;
			while(numberOfBeans>0){
				for( ;pitIndex>=0;pitIndex--){
					player2Pit[pitIndex]++;
					numberOfBeans--;
					if(numberOfBeans==0)
						return;
				}
				pitIndex++;
				Log.i(TAG, "pitIndex:"+pitIndex);
				for(;pitIndex<player2Pit.length;pitIndex++){
					player1Pit[pitIndex]++;
					numberOfBeans--;
					if(numberOfBeans==0)
						return;
				}
			}
		}
	}
	private void initializeBoard() {
		for(int i=0;i<player1Pit.length;i++){
			player1Pit[i]=5;
		}
		player1Capture=0;
		for(int i=0;i<player2Pit.length;i++){
			player2Pit[i]=5;
		}
		player2Capture=0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
