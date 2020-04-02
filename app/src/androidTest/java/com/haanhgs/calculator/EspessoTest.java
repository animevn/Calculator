package com.haanhgs.calculator;

import android.content.Context;
import com.haanhgs.calculator.view.MainActivity;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class EspessoTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.haanhgs.calculator", appContext.getPackageName());
    }

    @Test
    public void activityLaunch() {
        onView(withId(R.id.bnOne)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(isDisplayed()));
        onView(withId(R.id.bnAdd)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(isDisplayed()));
    }

    @Test
    public void numberInput() {
        onView(withId(R.id.bnOne)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("1")));
        onView(withId(R.id.bnTwo)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("12")));
        onView(withId(R.id.bnThree)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("123")));
        onView(withId(R.id.bnFour)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("1234")));
        onView(withId(R.id.bnFive)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("12345")));
        onView(withId(R.id.bnSix)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("123456")));
        onView(withId(R.id.bnSeven)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("1234567")));
        onView(withId(R.id.bnEight)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("12345678")));
        onView(withId(R.id.bnNine)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("123456789")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("12345678")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("1234567")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("123456")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("12345")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("1234")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("123")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("12")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("1")));
    }

    @Test
    public void testDot() {
        onView(withId(R.id.bnDot)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0.")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0")));
        onView(withId(R.id.bnDot)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0.")));
        onView(withId(R.id.bnNine)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0.9")));
        onView(withId(R.id.bnDot)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0.9")));
        onView(withId(R.id.bnDot)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0.9")));
        onView(withId(R.id.bnDot)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0.9")));
        onView(withId(R.id.bnDot)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0.9")));
        onView(withId(R.id.bnNine)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0.99")));
        onView(withId(R.id.bnDot)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0.99")));
    }

    @Test
    public void testSign() {
        onView(withId(R.id.bnSign)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-")));
        onView(withId(R.id.bnSign)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("")));
        onView(withId(R.id.bnZero)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("0")));
        onView(withId(R.id.bnCE)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("")));
        onView(withId(R.id.bnSign)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-")));
        onView(withId(R.id.bnZero)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-0")));
        onView(withId(R.id.bnDot)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-0.")));
        onView(withId(R.id.bnNine)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-0.9")));
        onView(withId(R.id.bnNine)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-0.99")));
        onView(withId(R.id.bnNine)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-0.999")));
        onView(withId(R.id.bnNine)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-0.9999")));
        onView(withId(R.id.bnNine)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-0.99999")));
        onView(withId(R.id.bnNine)).perform(click());
        onView(withId(R.id.tvDisplay)).check(matches(withText("-0.999999")));
    }















}
