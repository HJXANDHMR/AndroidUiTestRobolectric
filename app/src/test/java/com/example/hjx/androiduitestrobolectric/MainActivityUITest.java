package com.example.hjx.androiduitestrobolectric;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2016/5/9.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityUITest {

    private Activity activity;

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }


    @Test
    public void clickButton_shouldTextViewChange() {

        Button button = (Button) activity.findViewById(R.id.button);
        TextView textView = (TextView) activity.findViewById(R.id.textView);

        String resultsText = textView.getText().toString();
        assertThat(resultsText, equalTo("Hello World!"));
        button.performClick();
        resultsText = textView.getText().toString();
        assertThat(resultsText, equalTo("Text change"));
    }

    @Test
    public void clickButton_shouldStartNextActivity() {
        activity.findViewById(R.id.button).performClick();
        Intent expectedIntent = new Intent(activity, NextActivity.class);
        Intent actualIntent = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent, actualIntent);
    }
}
