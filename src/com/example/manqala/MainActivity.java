package com.example.manqala;

import java.util.Random;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
	@SuppressLint("NewApi")
	public void onPressed(View v) throws InterruptedException{
		int buttonClicked = v.getId();
		String pitDetails=new String();
		switch(buttonClicked){
		case R.id.button11:
			pitDetails=playGame("player1", 0);
			Log.i(TAG, "button pressed:1");
			break;
		case R.id.button12:
			pitDetails=playGame("player1", 1);
			Log.i(TAG, "button pressed:2");
			break;
		case R.id.button13:
			pitDetails=playGame("player1", 2);
			Log.i(TAG, "button pressed:3");
			break;
		case R.id.button14:
			pitDetails=playGame("player1", 3);
			Log.i(TAG, "button pressed:4");
			break;
		case R.id.button15:
			pitDetails=playGame("player1", 4);
			Log.i(TAG, "button pressed:5");
			break;
		}
		String[] pitReturned = pitDetails.split(",");
		if(pitReturned[0].equals("player2"))
			checkPlayer1Cap(Integer.parseInt(pitReturned[1])); 
		printPit();
		isGameOver();
		
		Button play2 = (Button) findViewById(R.id.player2Move);
		play2.setClickable(true);
		play2 = (Button) findViewById(R.id.button11);
		play2.setClickable(false);
		play2 = (Button) findViewById(R.id.button12);
		play2.setClickable(false);
		play2 = (Button) findViewById(R.id.button13);
		play2.setClickable(false);
		play2 = (Button) findViewById(R.id.button14);
		play2.setClickable(false);
		play2 = (Button) findViewById(R.id.button15);
		play2.setClickable(false);
		Toast.makeText(getApplicationContext(), "Press player 2", Toast.LENGTH_LONG).show();

	}
	private int getPlayer2move() {
		Random rand = new Random();
		int n;
		do{
			n = rand.nextInt(5);
			Log.i(TAG, "DD----DD:"+Integer.toString(n));
		} while(player2Pit[n]==0);

		return n;
	}
	private void checkPlayer2Cap(int pitNumber) {
		int capture=0;
		Log.i(TAG, " p1 check"+pitNumber+" "+player2Pit[pitNumber]);
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
		Toast.makeText(getApplicationContext(), "Captured:"+Integer.toString(capture),Toast.LENGTH_SHORT).show();
		if(capture>0)
			Toast.makeText(getApplicationContext(), "Good Move",Toast.LENGTH_SHORT).show();
	}
	public void player2Move(View v) throws InterruptedException{
		int n = getPlayer2move();

		TextView option = (TextView) findViewById(R.id.moveMade);
		String[] stringOption = option.getText().toString().split(":");
		Thread.sleep(1000);
		option.setText(stringOption[0]+":"+Integer.toString((n+1)));
		Log.i(TAG, "value of random number:"+n);
		String pitDetails=playGame("player2",n);
		String[] pitReturned = pitDetails.split(",");
		if(pitReturned[0].equals("player1"))
			checkPlayer2Cap(Integer.parseInt(pitReturned[1]));
		printPit();
		isGameOver();
		Button play2 = (Button) findViewById(R.id.player2Move);
		play2.setClickable(false);
		play2 = (Button) findViewById(R.id.button11);
		play2.setClickable(true);
		play2 = (Button) findViewById(R.id.button12);
		play2.setClickable(true);
		play2 = (Button) findViewById(R.id.button13);
		play2.setClickable(true);
		play2 = (Button) findViewById(R.id.button14);
		play2.setClickable(true);
		play2 = (Button) findViewById(R.id.button15);
		play2.setClickable(true);

	}
	private void isGameOver() {
		if(player1Capture>25 || player2Capture>25){
			Log.i(TAG, "Game Over");
		}

	}
	private void checkPlayer1Cap(int pitNumber) {
		int capture=0;
		Log.i(TAG, " p1 check DDDD"+pitNumber+" "+player2Pit[pitNumber]);
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
		Toast.makeText(getApplicationContext(), "Captured:"+Integer.toString(capture),Toast.LENGTH_SHORT).show();
		if(capture>0){
			Toast.makeText(getApplicationContext(), "Good Move",Toast.LENGTH_SHORT).show();
		}
	}
	private void printPit() throws InterruptedException {
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
	//	private void updateScreenPlayer1(int pitNumber, int numberOfbeans) throws InterruptedException{
	//		TextView pitNum;
	//		switch(pitNumber){
	//			case 0:
	//				pitNum = (TextView) findViewById(R.id.pitP11);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//			case 1:
	//				pitNum = (TextView) findViewById(R.id.pitP12);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//			case 2:
	//				pitNum = (TextView) findViewById(R.id.pitP23);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//			case 3:
	//				pitNum = (TextView) findViewById(R.id.pitP14);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//			case 4:
	//				pitNum = (TextView) findViewById(R.id.pitP15);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//		}
	//	}
	//	private void updateScreenPlayer2(int pitNumber, int numberOfbeans) throws InterruptedException{
	//		TextView pitNum;
	//		switch(pitNumber){
	//			case 0:
	//				pitNum = (TextView) findViewById(R.id.pitP21);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//			case 1:
	//				pitNum = (TextView) findViewById(R.id.pitP22);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//			case 2:
	//				pitNum = (TextView) findViewById(R.id.pitP13);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//			case 3:
	//				pitNum = (TextView) findViewById(R.id.pitP24);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//			case 4:
	//				pitNum = (TextView) findViewById(R.id.pitP25);
	//				pitNum.setText(Integer.toString(numberOfbeans));
	//				
	//				break;
	//		}
	//	}
	private String playGame(String playerNumber, int pitNumber) throws InterruptedException {
		if(playerNumber=="player1"){
			int numberOfBeans = player1Pit[pitNumber];
			player1Pit[pitNumber] = 0;
			int pitIndex=pitNumber;
			pitIndex++;
			while(numberOfBeans>0){
				for(;pitIndex<player1Pit.length;pitIndex++){
					player1Pit[pitIndex]++;
					numberOfBeans--;
					//updateScreenPlayer1(pitIndex,player1Pit[pitIndex]);

					if(numberOfBeans==0)
						return "player1,"+Integer.toString(pitIndex);
				}
				pitIndex--;
				Log.i(TAG, "pitIndex:"+pitIndex);
				for( ;pitIndex>=0;pitIndex--){
					player2Pit[pitIndex]++;
					numberOfBeans--;
					//updateScreenPlayer2(pitIndex,player2Pit[pitIndex]);
					if(numberOfBeans==0)
						return "player2,"+Integer.toString(pitIndex);
				}
				pitIndex++;
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
					//updateScreenPlayer2(pitIndex,player2Pit[pitIndex]);
					if(numberOfBeans==0)
						return "player2,"+Integer.toString(pitIndex);
				}
				pitIndex++;
				Log.i(TAG, "pitIndex:"+pitIndex);
				for(;pitIndex<player2Pit.length;pitIndex++){
					player1Pit[pitIndex]++;
					numberOfBeans--;
					//updateScreenPlayer1(pitIndex,player1Pit[pitIndex]);
					if(numberOfBeans==0)
						return "player1,"+Integer.toString(pitIndex);
				}
				pitIndex--;
			}
		}
		return null;
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
	public void showRules(View v){
		Intent showTheRule = new Intent(this,RuleActivity.class);
		startActivity(showTheRule);
	}
	public void newGameString(View v) throws InterruptedException{
		initializeBoard();
		printPit();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
