package com.example.vishal.waterreports;
import com.example.vishal.waterreports.controller.ForgotPasswordActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import android.content.res.Resources;
import android.support.test.espresso.Root;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.os.IBinder;
import android.test.ActivityTestCase;
import android.view.WindowManager;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import android.support.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Shaun Chapman, Team42, CS2340, Spring 2017
 *
 * Instrumentation test, which will execute on an Android device.
 *
 * Tests the resetPassword() method in ForgotPasswordActivity controller
 *
 */
@LargeTest
public class ResetPasswordTest extends ActivityTestCase {

    // Used to test that the toast message matches a custom string
    private class ToastMatcher extends TypeSafeMatcher<Root> {
        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    return true;
                }
            }
            return false;
        }
    }

    public void isToastMessageDisplayed(int textId) {
        onView(withText(textId)).inRoot(isToast()).check(matches(isDisplayed()));
    }

    public Matcher<Root> isToast() {
        return new ToastMatcher();
    }

    @Rule
    public ActivityTestRule<ForgotPasswordActivity> activityTestRule =
            new ActivityTestRule<>(ForgotPasswordActivity.class);

    // The method should display a "Please enter valid email" toast message if
    // the email text field is empty upon clicking the submit button
    @Test
    public void testEmptyEmail() {
        // Enter an empty email and press submit
        onView(withId(R.id.editTextForgotPasswordEmail))
                .perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.buttonSendEmail)).perform(click());
        // Now test that the "Please enter valid email" message appears
        isToastMessageDisplayed(R.string.forgot_password_enter);;
    }

    // The method should display a "Invalid Email" toast message if
    // firebase was not able to send an email to the entered email address
    @Test
    public void testInvalidEmail() {
        // Enter a valid email and press submit
        onView(withId(R.id.editTextForgotPasswordEmail))
                .perform(typeText("28"), closeSoftKeyboard());
        onView(withId(R.id.buttonSendEmail)).perform(click());
        // Now test that the  "Invalid Email" message appears
    }

    // The method should display a "Email Sent!" toast message if
    // firebase was able to send an email to the entered email address
    @Test
    public void testValidEmail() {
        // Enter a valid email and press submit
        onView(withId(R.id.editTextForgotPasswordEmail))
                .perform(typeText("shaungchapman@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.buttonSendEmail)).perform(click());
        // Now test that the "Email Sent!" message appears
        ForgotPasswordActivity activity = activityTestRule.getActivity();
        onView(withText(R.id.editTextForgotPasswordEmail)).inRoot(new ToastMatcher())
                .check(matches(withText("Email Sent!")));
    }
}