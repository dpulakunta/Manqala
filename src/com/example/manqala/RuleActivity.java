package com.example.manqala;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class RuleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rule);
	}
	public void finishProcess(View v){
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rule, menu);
		return true;
	}

}
