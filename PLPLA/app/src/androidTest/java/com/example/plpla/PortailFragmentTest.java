package com.example.plpla;


import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PortailFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void semestre1() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextNom),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Mazy"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextPrenom),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Mohamed"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button), withText("Suivant"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button = onView(
                allOf(withId(R.id.boutonEnregistrer),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        button.check(matches(not(isEnabled())));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.boutonSemestre2),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                2),
                        isDisplayed()));
        button2.check(matches(isEnabled()));

        ViewInteraction expansionHeader = onView(
                allOf(withText("BLOC MATH S1 : Fondements 1"),
                        isDisplayed()));
        expansionHeader.perform(click());

        ViewInteraction expansionHeader2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("com.github.florent37.expansionpanel.ExpansionLayout")),
                                0),
                        2),
                        isDisplayed()));
        expansionHeader2.perform(click());

        ViewInteraction expansionHeader3 = onView(
                allOf(withText("UE INFO S1 : Bases de l'informatique"),
                        isDisplayed()));
        expansionHeader3.perform(click());
        expansionHeader3.perform(click());
        expansionHeader3.perform(click());

        ViewInteraction expansionHeader6 = onView(
                allOf(withText("UE CHIMIE S1 : Structure microscopique de la matiere"),
                        isDisplayed()));
        expansionHeader6.perform(click());

        ViewInteraction expansionHeader10 = onView(
                allOf(withText("UE ELECTRONIQUE S1 : Electronique numerique - Bases"),
                        isDisplayed()));
        expansionHeader10.perform(click());

        ViewInteraction expansionHeader4 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("com.github.florent37.expansionpanel.ExpansionLayout")),
                                0),
                        4),
                        isDisplayed()));
        expansionHeader4.perform(click());

        ViewInteraction expansionHeader5 = onView(
                allOf(withText("UE MATHS S1 : Complements 1"),
                        isDisplayed()));
        expansionHeader5.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ViewInteraction expansionHeader7 = onView(
                allOf(withText("UE MATHS S1 : Complements 1"),
                        isDisplayed()));
        expansionHeader7.perform(click());

        ViewInteraction expansionHeader8 = onView(
                allOf(withText("UE MATHS S1 : Methodes - approche continue"),
                        isDisplayed()));
        expansionHeader8.perform(click());

        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());
        button.check(matches(isEnabled()))
                .perform(click());

    }

    @Test
    public void semestre2() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextNom),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Mazy"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextPrenom),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Mohamed"), closeSoftKeyboard());

        ViewInteraction appCompatButton1 = onView(
                allOf(withId(R.id.button), withText("Suivant"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton1.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button = onView(
                allOf(withId(R.id.boutonSemestre2),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                2),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.boutonSemestre2), withText("Semestre 2"),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction expansionHeader = onView(
                allOf(withText("BLOC MATH S2 : Methodes. approche discrete"),
                        isDisplayed()));
        expansionHeader.perform(click());

        ViewInteraction expansionHeader2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("com.github.florent37.expansionpanel.ExpansionLayout")),
                                0),
                        4),
                        isDisplayed()));
        expansionHeader2.perform(click());

        ViewInteraction expansionHeader3 = onView(
                allOf(withText("UE SV S2 : Diversite du vivant"),
                        isDisplayed()));
        expansionHeader3.perform(click());

        ViewInteraction expansionHeader4 = onView(
                allOf(withText("UE CHIMIE S2 : Reactions et reactivites chimiques"),
                        isDisplayed()));
        expansionHeader4.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());

        ViewInteraction expansionHeader5 = onView(
                allOf(withText("UE PHYSIQUE S2 : Optique"),
                        isDisplayed()));
        expansionHeader5.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.boutonEnregistrer2), withText("Enregistrer"),
                        isDisplayed()));
        appCompatButton2.perform(click());
    }

    @Test
    public void semestre3 () {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextNom),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Mazy"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextPrenom),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Mohamed"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button), withText("Suivant"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.boutonSemestre2), withText("Semestre 2"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.boutonSemestre3),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                2),
                        isDisplayed()));
        button.check(matches(isEnabled()))
                .perform(click());


        ViewInteraction expansionHeader = onView(
                allOf(withText("S3 Sciences et technologies"),
                        isDisplayed()));
        expansionHeader.perform(click());

        ViewInteraction expansionHeader2 = onView(
                allOf(withText("UE CHIMIE S1 : Structure microscopique de la matiere"),
                        isDisplayed()));
        expansionHeader2.perform(click());

        ViewInteraction expansionHeader3 = onView(
                allOf(withText("UE CHIMIE S3 : Chimie des solutions"),
                        isDisplayed()));
        expansionHeader3.perform(click());

        ViewInteraction expansionHeader4 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("com.github.florent37.expansionpanel.ExpansionLayout")),
                                0),
                        12),
                        isDisplayed()));
        expansionHeader4.perform(click());

        ViewInteraction expansionHeader5 = onView(
                allOf(withText("UE INFO S1 : Bases de l'informatique"),
                        isDisplayed()));
        expansionHeader5.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.boutonEnregistrer3), withText("Enregistrer"),
                        isDisplayed()));
        appCompatButton3.check(matches(not(isEnabled())));

        ViewInteraction expansionHeader6 = onView(
                allOf(withText("UE TERRE S3 : Physique de la Terre"),
                        isDisplayed()));
        expansionHeader6.perform(click());

        appCompatButton3.check(matches(isEnabled()));
    }

    private static Matcher<View> childAtPosition(
    final Matcher<View> parentMatcher,final int position)
    {


        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

