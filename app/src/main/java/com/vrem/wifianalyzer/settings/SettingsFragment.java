/*
 * WiFiAnalyzer
 * Copyright (C) 2019 VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.vrem.wifianalyzer.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import hkcc.ccn2279.gp.canemovie.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        AlertDialog.Builder skipQuestionBuilder = new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.reset_title))
                .setMessage(getString(R.string.reset_message))
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                    }
                })
                .setPositiveButton(getString(R.string.reset_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        // Get this application SharedPreferences editor
                        SharedPreferences.Editor preferencesEditor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                        // Clear all the saved preference values.
                        preferencesEditor.clear();
                        // Read the default values and set them as the current values.
                        PreferenceManager.setDefaultValues(getContext(), R.xml.settings, true);
                        // Commit all changes.
                        preferencesEditor.commit();

                        // Call this method to trigger the execution of the setOnPreferenceChangeListener() method at the PrefsActivity
                        // https://android--code.blogspot.com/2016/03/android-how-to-restart-activity.html
                        getActivity().recreate();
                    }
                });
        AlertDialog skip = skipQuestionBuilder.create();
        skip.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                skip.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
                    skip.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                }
            }
        });
        skip.show();
    }
}
