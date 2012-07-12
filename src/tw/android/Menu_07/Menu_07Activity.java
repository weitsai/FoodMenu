package tw.android.Menu_07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu_07Activity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/**
	 * go LiteralInterfaceActivity
	 * */
	public void goLiteralInterfaceActivity(View v) {
		Intent it = new Intent();
		it.setClass(Menu_07Activity.this, LiteralInterfaceActivity.class);
		startActivity(it);
	}

	/**
	 * go GraphicalInterfaceActivity
	 * */
	public void goGraphicalInterfaceActivity(View v) {
		Intent it = new Intent();
		it.setClass(Menu_07Activity.this, GraphicalInterfaceActivity.class);
		startActivity(it);
	}
}