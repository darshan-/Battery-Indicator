/*
    Copyright (c) 2010-2021 Darshan Computing, LLC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
*/

package com.darshancomputing.BatteryIndicatorPro;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
//import android.content.SharedPreferences;
import android.os.Bundle;
//import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmEditActivity extends AppCompatActivity {
    private Resources res;
    //private SharedPreferences settings;
    private AlarmEditFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        res = getResources();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setElevation(0);
        }

        int c = getResources().getColor(R.color.windowBackground);
        //getActionBar().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(c));
        Window w = getWindow();
        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        w.setStatusBarColor(c);

        setWindowSubtitle(res.getString(R.string.alarm_settings_subtitle));

        setContentView(R.layout.prefs);

        // if (savedInstanceState == null) {
            frag = new AlarmEditFragment();

            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, frag, "aef")
                .commit();
        //} else {
        //    frag = (AlarmEditFragment) getSupportFragmentManager().findFragmentByTag("aef");
        //}

        frag.setScreen();
    }

    private void setWindowSubtitle(String subtitle) {
        if (res.getBoolean(R.bool.long_activity_names))
            setTitle(res.getString(R.string.app_full_name) + " - " + subtitle);
        else
            setTitle(subtitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alarm_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_delete) {
            frag.deleteAlarm();
            finish();

            return true;
        }

        if (item.getItemId() == R.id.menu_help) {
            ComponentName comp = new ComponentName(getPackageName(), SettingsHelpActivity.class.getName());
            Intent intent = new Intent().setComponent(comp).putExtra(SettingsActivity.EXTRA_SCREEN,
                                                                     SettingsFragment.KEY_ALARM_EDIT_SETTINGS);
            startActivity(intent);

            return true;
        }

        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void enableNotifsButtonClick(android.view.View v) {
        frag.enableNotifsButtonClick();
    }
}
