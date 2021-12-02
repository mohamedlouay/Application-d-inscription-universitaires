package com.example.plpla;


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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ParcoursFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sansEnregistrement() {
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

        ViewInteraction appCompatImageButton = onView(
                allOf(childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView = onView(withId(R.id.parcours_vide));
        textView.check(matches(withText("Aucun parcours enregistré")));

        ViewInteraction button = onView(withId(R.id.reinitialiser_button));
        button.check(matches(not(isEnabled())));
    }

    @Test
    public void semestre1(){
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

        ViewInteraction expansionHeader = onView(
                allOf(withText("BLOC MATH S1 : Methodes. approche continue"),
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

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());

        ViewInteraction expansionHeader4 = onView(
                allOf(withText("UE CHIMIE S1 : Structure microscopique de la matiere"),
                        isDisplayed()));
        expansionHeader4.perform(click());

        ViewInteraction expansionHeader5 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("com.github.florent37.expansionpanel.ExpansionLayout")),
                                0),
                        12),
                        isDisplayed()));
        expansionHeader5.perform(click());

        ViewInteraction expansionHeader6 = onView(
                allOf(withText("ECUE MIASHS S1 : Culture economie 1"),
                        isDisplayed()));
        expansionHeader6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.boutonEnregistrer),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView2 = onView(withId(R.id.parcours_vide));
        textView2.check(matches(not(isDisplayed())));

        ViewInteraction button = onView(withId(R.id.reinitialiser_button));
        button.check(matches(isEnabled()));

        ViewInteraction textView = onView(
                allOf(withText("SEMESTRE 1"),
                        isDisplayed()));
        textView.check(matches(withText("SEMESTRE 1")));


        ViewInteraction expansionHeader7 = onView(
                allOf(withText("SEMESTRE 1"),
                        isDisplayed()));
        expansionHeader7.perform(click());

        expansionHeader3.check(matches(isDisplayed()));
        expansionHeader4.check(matches(isDisplayed()));
        expansionHeader6.check(matches(isDisplayed()));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.reinitialiser_button),
                        isDisplayed()));
        appCompatButton3.perform(click());

        textView2.check(matches(withText("Aucun parcours enregistré")));
        button.check(matches(not(isEnabled())));
    }

    @Test
    public void semestre2(){
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
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction expansionHeader = onView(
                allOf(withText("BLOC MATH S2 : Methodes. approche discrete"),
                        isDisplayed()));
        expansionHeader.perform(click());

        ViewInteraction expansionHeader2 = onView(
                allOf(withText("UE CHIMIE S2 : Reactions et reactivites chimiques"),
                        isDisplayed()));
        expansionHeader2.perform(click());

        ViewInteraction expansionHeader3 = onView(
                allOf(withText("UE ELECTRONIQUE S2 : Communication sans fil"),
                        isDisplayed()));
        expansionHeader3.perform(click());

        ViewInteraction expansionHeader4 = onView(
                allOf(withText("UE ELECTRONIQUE S2 : Electronique analogique"),
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

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.boutonEnregistrer2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                        0)),
                        1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView2 = onView(withId(R.id.parcours_vide));
        textView2.check(matches(not(isDisplayed())));

        ViewInteraction button = onView(withId(R.id.reinitialiser_button));
        button.check(matches(isEnabled()));

        ViewInteraction textView = onView(
                allOf(withText("SEMESTRE 2"),
                        isDisplayed()));
        textView.check(matches(withText("SEMESTRE 2")));


        ViewInteraction expansionHeader7 = onView(
                allOf(withText("SEMESTRE 2"),
                        isDisplayed()));
        expansionHeader7.perform(click());

        expansionHeader2.check(matches(isDisplayed()));
        expansionHeader3.check(matches(isDisplayed()));
        expansionHeader4.check(matches(isDisplayed()));

        ViewInteraction appCompatButtonRe = onView(
                allOf(withId(R.id.reinitialiser_button),
                        isDisplayed()));
        appCompatButtonRe.perform(click());

        textView2.check(matches(withText("Aucun parcours enregistré")));
        button.check(matches(not(isEnabled())));
    }

    @Test
    public void semestre3(){
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
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButtons3 = onView(
                allOf(withId(R.id.boutonSemestre3), withText("Semestre 3"),
                        isDisplayed()));
        appCompatButtons3.perform(click());

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
                allOf(withText("UE ELECTRONIQUE S1 : Electronique numerique - Bases"),
                        isDisplayed()));
        expansionHeader4.perform(click());

        ViewInteraction expansionHeader5 = onView(
                allOf(withText("UE CHIMIE S3 : Materiaux 1"),
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
                allOf(withId(R.id.boutonEnregistrer3),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                        0)),
                        1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView2 = onView(withId(R.id.parcours_vide));
        textView2.check(matches(not(isDisplayed())));

        ViewInteraction button = onView(withId(R.id.reinitialiser_button));
        button.check(matches(isEnabled()));

        ViewInteraction textView = onView(
                allOf(withText("SEMESTRE 3"),
                        isDisplayed()));
        textView.check(matches(withText("SEMESTRE 3")));

        ViewInteraction expansionHeader7 = onView(
                allOf(withText("SEMESTRE 3"),
                        isDisplayed()));
        expansionHeader7.perform(click());

        expansionHeader2.check(matches(isDisplayed()));
        expansionHeader3.check(matches(isDisplayed()));
        expansionHeader4.check(matches(isDisplayed()));
        expansionHeader5.check(matches(isDisplayed()));

        ViewInteraction appCompatButtonRe = onView(
                allOf(withId(R.id.reinitialiser_button),
                        isDisplayed()));
        appCompatButtonRe.perform(click());

        textView2.check(matches(withText("Aucun parcours enregistré")));
        button.check(matches(not(isEnabled())));
    }



    @Test
    public void les3Semestre(){
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

        ViewInteraction expansionHeader = onView(
                allOf(withText("BLOC MATH S1 : Methodes. approche continue"),
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

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());

        ViewInteraction expansionHeader4 = onView(
                allOf(withText("UE CHIMIE S1 : Structure microscopique de la matiere"),
                        isDisplayed()));
        expansionHeader4.perform(click());

        ViewInteraction expansionHeader5 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(is("com.github.florent37.expansionpanel.ExpansionLayout")),
                                0),
                        12),
                        isDisplayed()));
        expansionHeader5.perform(click());

        ViewInteraction expansionHeader6 = onView(
                allOf(withText("ECUE MIASHS S1 : Culture economie 1"),
                        isDisplayed()));
        expansionHeader6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.boutonEnregistrer),
                        isDisplayed()));
        appCompatButton2.perform(click());


        ViewInteraction appCompatButtonS2 = onView(
                allOf(withId(R.id.boutonSemestre2), withText("Semestre 2"),
                        isDisplayed()));
        appCompatButtonS2.perform(click());

        ViewInteraction expansionHeader11 = onView(
                allOf(withText("BLOC MATH S2 : Methodes. approche discrete"),
                        isDisplayed()));
        expansionHeader11.perform(click());

        ViewInteraction expansionHeader22 = onView(
                allOf(withText("UE CHIMIE S2 : Reactions et reactivites chimiques"),
                        isDisplayed()));
        expansionHeader22.perform(click());

        ViewInteraction expansionHeader33 = onView(
                allOf(withText("UE ELECTRONIQUE S2 : Communication sans fil"),
                        isDisplayed()));
        expansionHeader33.perform(click());

        ViewInteraction expansionHeader44 = onView(
                allOf(withText("UE ELECTRONIQUE S2 : Electronique analogique"),
                        isDisplayed()));
        expansionHeader44.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());

        ViewInteraction appCompatButton000 = onView(
                allOf(withId(R.id.boutonEnregistrer2),
                        isDisplayed()));
        appCompatButton000.perform(click());

        ViewInteraction appCompatButtons333 = onView(
                allOf(withId(R.id.boutonSemestre3), withText("Semestre 3"),
                        isDisplayed()));
        appCompatButtons333.perform(click());

        ViewInteraction expansionHeader111 = onView(
                allOf(withText("S3 Sciences et technologies"),
                        isDisplayed()));
        expansionHeader111.perform(click());

        ViewInteraction expansionHeader333 = onView(
                allOf(withText("UE CHIMIE S3 : Chimie des solutions"),
                        isDisplayed()));
        expansionHeader333.perform(click());

        ViewInteraction expansionHeader555 = onView(
                allOf(withText("UE CHIMIE S3 : Materiaux 1"),
                        isDisplayed()));
        expansionHeader555.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(160);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());

        ViewInteraction expansionHeader222 = onView(
                allOf(withText("UE TERRE S3 : Physique de la Terre"),
                        isDisplayed()));
        expansionHeader222.perform(click());

        ViewInteraction expansionHeader444 = onView(
                allOf(withText("UE TERRE S3 : Materiaux terrestres"),
                        isDisplayed()));
        expansionHeader444.perform(click());

        ViewInteraction appCompatButton332 = onView(
                allOf(withId(R.id.boutonEnregistrer3),
                        isDisplayed()));
        appCompatButton332.perform(click());


        ViewInteraction appCompatImageButton = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.toolbar),
                                childAtPosition(
                                        withClassName(is("com.google.android.material.appbar.AppBarLayout")),
                                        0)),
                        1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView2 = onView(withId(R.id.parcours_vide));
        textView2.check(matches(not(isDisplayed())));

        ViewInteraction button = onView(withId(R.id.reinitialiser_button));
        button.check(matches(isEnabled()));


        ViewInteraction textView = onView(
                allOf(withText("SEMESTRE 1"),
                        isDisplayed()));
        textView.check(matches(withText("SEMESTRE 1")));

        ViewInteraction textViewe = onView(
                allOf(withText("SEMESTRE 2"),
                        isDisplayed()));
        textViewe.check(matches(withText("SEMESTRE 2")));

        ViewInteraction textViewee = onView(
                allOf(withText("SEMESTRE 3"),
                        isDisplayed()));
        textViewee.check(matches(withText("SEMESTRE 3")));

        ViewInteraction expansionHeader7 = onView(
                allOf(withText("SEMESTRE 3"),
                        isDisplayed()));
        expansionHeader7.perform(click());

       /* expansionHeader222.check(matches(isDisplayed()));
        expansionHeader333.check(matches(isDisplayed()));
        expansionHeader444.check(matches(isDisplayed()));
        expansionHeader555.check(matches(isDisplayed()));*/

        ViewInteraction expansionHeader777 = onView(
                allOf(withText("SEMESTRE 2"),
                        isDisplayed()));
        expansionHeader777.perform(click());

   /*     expansionHeader22.check(matches(isDisplayed()));
        expansionHeader33.check(matches(isDisplayed()));
        expansionHeader44.check(matches(isDisplayed()));*/

        ViewInteraction expansionHeader7777 = onView(
                allOf(withText("SEMESTRE 1"),
                        isDisplayed()));
        expansionHeader7777.perform(click());

       /* expansionHeader3.check(matches(isDisplayed()));
        expansionHeader4.check(matches(isDisplayed()));
        expansionHeader6.check(matches(isDisplayed()));*/

        onView(withId(R.id.nav_host_fragment)).perform(swipeUp());

        ViewInteraction appCompatButtonRe = onView(
                allOf(withId(R.id.reinitialiser_button),
                        isDisplayed()));
        appCompatButtonRe.perform(click());

        textView2.check(matches(withText("Aucun parcours enregistré")));
        button.check(matches(not(isEnabled())));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

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
