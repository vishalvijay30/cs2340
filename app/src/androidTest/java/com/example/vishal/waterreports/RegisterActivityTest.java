package com.example.vishal.waterreports;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.vishal.waterreports.controller.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anyOf;

/**
 * Created by vishal on 4/3/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterActivityTest {
    @Rule
    public IntentsTestRule<RegisterActivity> mSignUpActivityTestRule =
            new IntentsTestRule<>(RegisterActivity.class);


    @Test
    public void testSuccessfulRegistration() {
        String name = "Jim Apple";
        String email = "jim@gatech.edu";
        String password = "mynameisjim";

        onView(withId(R.id.editTextName)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.editTextRegisterEmail)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.editTextRegisterPassword)).perform(typeText(password), closeSoftKeyboard());

        onView(withId(R.id.buttonRegister)).perform(click());

        //intended(hasComponent(ProfileActivity.class.getName()));
        String successString = "Hello Jim Apple";
        onView(withId(2131624150)).check(matches(anyOf(withText(successString), isDisplayed())));
    }

    @Test
    public void testInvalidEmailEmptyRegistration() {
        String name = "Jimmy";
        String email = "";
        onView(withId(R.id.editTextName)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.editTextRegisterEmail)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(anyOf(withText("Jimmy"), isDisplayed())));
    }

    @Test
    public void testInvalidEmailContainsSpecialCharacterRegistration() {
        String name = "Jimmy";
        String email = "livermore";
        onView(withId(R.id.editTextName)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.editTextRegisterEmail)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(anyOf(withText("Jimmy"), isDisplayed())));
    }

    @Test
    public void testInvalidNameRegister() {
        String name = "";
        String email = "vishal@gatech.edu";
        onView(withId(R.id.editTextName)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.editTextRegisterEmail)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.editTextRegisterEmail)).check(matches(anyOf(withText("vishal@gatech.edu"), isDisplayed())));
    }

    @Test
    public void testInvalidPassword() {
        String name = "Vishal";
        String email = "vishal@gatech.edu";
        String password = "12345";
        onView(withId(R.id.editTextName)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.editTextRegisterEmail)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.editTextRegisterPassword)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.buttonRegister)).perform(click());
        onView(withId(R.id.editTextName)).check(matches(anyOf(withText("Vishal"), isDisplayed())));
        onView(withId(R.id.editTextRegisterEmail)).check(matches(anyOf(withText("vishal@gatech.edu"), isDisplayed())));
    }

}
