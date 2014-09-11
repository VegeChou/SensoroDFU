package com.sensoro.dfu.settings;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;
import android.util.AttributeSet;
import android.widget.Toast;

import com.sensoro.dfu.demo.R;

public class AboutDfuPreference extends Preference {

	public AboutDfuPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AboutDfuPreference(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onClick() {
		final Context context = getContext();
		final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://devzone.nordicsemi.com/documentation/nrf51/5.0.0/html/examples_bootloader_dfu.html"));
		intent.addCategory(Intent.CATEGORY_DEFAULT);

		// is browser
		if (intent.resolveActivity(context.getPackageManager()) != null)
			context.startActivity(intent);
		else {
			Toast.makeText(getContext(), R.string.no_application, Toast.LENGTH_LONG).show();
		}
	}
}
