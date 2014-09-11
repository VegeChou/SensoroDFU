package com.sensoro.dfu.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import com.sensoro.dfu.demo.R;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
	public static final String SETTINGS_PACKET_RECEIPT_NOTIFICATION_ENABLED = "settings_packet_receipt_notification_enabled";
	public static final String SETTINGS_NUMBER_OF_PACKETS = "settings_number_of_packets";
	public static final int SETTINGS_NUMBER_OF_PACKETS_DEFAULT = 10;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.settings_dfu);

		// set initial values
		updateNumberOfPacketsSummary();
	}

	@Override
	public void onResume() {
		super.onResume();

		// attach the preference change listener. It will update the summary below interval preference
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause() {
		super.onPause();

		// unregister listener
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
		if (SETTINGS_NUMBER_OF_PACKETS.equals(key)) {
			updateNumberOfPacketsSummary();
		}
	}

	private void updateNumberOfPacketsSummary() {
		final PreferenceScreen screen = getPreferenceScreen();
		final SharedPreferences preferences = getPreferenceManager().getSharedPreferences();

		final String value = preferences.getString(SETTINGS_NUMBER_OF_PACKETS, String.valueOf(SETTINGS_NUMBER_OF_PACKETS_DEFAULT));
		screen.findPreference(SETTINGS_NUMBER_OF_PACKETS).setSummary(value);
	}
}
