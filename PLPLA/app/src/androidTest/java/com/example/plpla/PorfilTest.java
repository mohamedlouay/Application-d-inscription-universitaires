package com.example.plpla;

import android.content.Intent;
import android.view.Gravity;
import android.widget.EditText;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class PorfilTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    private String Nom  = "Benabdelkrim1 ";
    private String Prenom = "Mohamed" ;
    private String Email =" MohamedBen@gmail.com" ;
    private String tel =  "06612838" ;

    @Test
    public void allerauProfil(){
        Intent startIntent = new Intent();
        mActivityRule.launchActivity(startIntent);
        MockitoAnnotations.initMocks(this);


        onView(withId(R.id.button)).perform(click());

        /*On vérifie que le menu est fermé puis on clique sur le bouton menu*/
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // le Side menu  doit etre fermé .
                .perform(DrawerActions.open()); // On ouvre le Side menu .

        /*On clique sur Mon Profil*/
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_tools));

        /* on clique maintenant sur le bouton edit */

        onView(withId(R.id.Edit)).perform(click());

        /* on supprime ce qui est écrit sur le  EditText du Nom : */
        onView(withId(R.id.nameInput)).perform(clearText());

        /* Modifie le nom : */
        onView(withId(R.id.nameInput)).perform(typeText(Nom));


        /* On ferme le clavier Tactile */

        closeSoftKeyboard();

        /* on supprime ce qui est écrit sur le  EditText du Prenom : */
        onView(withId(R.id.usernameInput)).perform(clearText());

        /* Modifie le Prenom : */
        onView(withId(R.id.usernameInput)).perform(typeText(Prenom));

        closeSoftKeyboard();
        /* on supprime ce qui est écrit sur le  EditText du telephone : */
        onView(withId(R.id.telInput)).perform(clearText());

        /* Modifie le numero de telephone : */
        onView(withId(R.id.telInput)).perform(typeText(tel));

        /* On ferme le clavier Tactile */

        closeSoftKeyboard();

        /* on supprime ce qui est écrit sur le  EditText de l'email : */
        onView(withId(R.id.emailInput)).perform(clearText());
        /* Modifie l'adresse Email : */
        onView(withId(R.id.emailInput)).perform(typeText(Email));

        /* On ferme le clavier Tactile */
        closeSoftKeyboard();

        /* On appuie sur le bouton Save */

        onView(withId(R.id.Save)).perform(click());


    }
}
