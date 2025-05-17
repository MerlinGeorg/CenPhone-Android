package com.example.team11_mapd711_project_milestone2


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup

@LargeTest
@RunWith(AndroidJUnit4::class)
class FunctionalityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(LandingActivity::class.java)

    @Test
    fun functionalityTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.registerButton), withText("Register"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.editTextFirstName),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    1
                )
            )
        )
        appCompatEditText.perform(scrollTo(), replaceText("merlin"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.editTextLastName),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        appCompatEditText2.perform(scrollTo(), replaceText("geo"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.editTextEmail),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        appCompatEditText3.perform(scrollTo(), replaceText("merlin@gmail.com"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.editTextPassword),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    4
                )
            )
        )
        appCompatEditText4.perform(scrollTo(), replaceText("test"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.editTextAddress),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    5
                )
            )
        )
        appCompatEditText5.perform(scrollTo(), replaceText("rosebank dr"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.editTextCity),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    6
                )
            )
        )
        appCompatEditText6.perform(scrollTo(), replaceText("scarborough"), closeSoftKeyboard())

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.editTextPostalCode),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    7
                )
            )
        )
        appCompatEditText7.perform(scrollTo(), replaceText("m2n55b"), closeSoftKeyboard())

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.editTextCountry),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    8
                )
            )
        )
        appCompatEditText8.perform(scrollTo(), replaceText("canada"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.buttonRegister), withText("Register"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    9
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.emailEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText9.perform(replaceText("merlin@gmail.com"), closeSoftKeyboard())

        val appCompatEditText10 = onView(
            allOf(
                withId(R.id.passwordEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText10.perform(replaceText("test"), closeSoftKeyboard())

        val materialButton3 = onView(
            allOf(
                withId(R.id.loginButton), withText("Login"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val materialButton4 = onView(
            allOf(
                withId(R.id.orderButton), withText("Order Now"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        1
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.spinnerBrands),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())

        val materialTextView1 = onData(anything())
            .inRoot(isPlatformPopup())
            .atPosition(1)
        materialTextView1.perform(click())

        val materialButton5 = onView(
            allOf(
                withId(R.id.continueButton), withText("Continue"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.buyNowButton), withText("Buy Now"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.modelsRecyclerView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val materialRadioButton = onView(
            allOf(
                withId(R.id.storageOption128GB), withText("128 GB"),
                childAtPosition(
                    allOf(
                        withId(R.id.storageOptionsGroup),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            4
                        )
                    ),
                    1
                )
            )
        )
        materialRadioButton.perform(scrollTo(), click())

        val appCompatSpinner2 = onView(
            allOf(
                withId(R.id.colorSpinner),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        5
                    ),
                    1
                )
            )
        )
        appCompatSpinner2.perform(scrollTo(), click())

        val materialTextView2 = onData(anything())
            .inRoot(isPlatformPopup())
            .atPosition(1)
        materialTextView2.perform(click())


        val materialButton7 = onView(
            allOf(
                withId(R.id.checkoutButton), withText("Proceed To Checkout"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    6
                )
            )
        )
        materialButton7.perform(scrollTo(), click())

        val materialRadioButton2 = onView(
            allOf(
                withId(R.id.rbDoorDelivery), withText("Door Delivery"),
                childAtPosition(
                    allOf(
                        withId(R.id.deliveryOptionsGroup),
                        childAtPosition(
                            withId(R.id.main),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialRadioButton2.perform(click())

        val materialRadioButton3 = onView(
            allOf(
                withId(R.id.rbPickUpLocation), withText("Pick-Up Location"),
                childAtPosition(
                    allOf(
                        withId(R.id.deliveryOptionsGroup),
                        childAtPosition(
                            withId(R.id.main),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialRadioButton3.perform(click())

        val materialTextView3 = onData(anything())
            .inAdapterView(
                allOf(
                    withId(R.id.locationsListView),
                    childAtPosition(
                        withId(R.id.main),
                        3
                    )
                )
            )
            .atPosition(3)
        materialTextView3.perform(click())

        val materialButton8 = onView(
            allOf(
                withId(R.id.btnSatelliteView), withText("Satellite"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        2
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton8.perform(click())

        val materialButton9 = onView(
            allOf(
                withId(R.id.btnStandardView), withText("Standard"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton9.perform(click())

        val materialButton10 = onView(
            allOf(
                withId(R.id.btnSaveLocation), withText("Save Location"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        2
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton10.perform(click())

        val appCompatEditText11 = onView(
            allOf(
                withId(R.id.name),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText11.perform(replaceText("merlin"), closeSoftKeyboard())

        val appCompatEditText12 = onView(
            allOf(
                withId(R.id.street),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        3
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText12.perform(replaceText("rosebank"), closeSoftKeyboard())

        val appCompatEditText13 = onView(
            allOf(
                withId(R.id.city),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText13.perform(replaceText("scarbor"), closeSoftKeyboard())

        val appCompatEditText14 = onView(
            allOf(
                withId(R.id.postal_code),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        5
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText14.perform(replaceText("m1n 34n"), closeSoftKeyboard())

        val appCompatEditText15 = onView(
            allOf(
                withId(R.id.phone),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        7
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText15.perform(replaceText("3746647789"), closeSoftKeyboard())

        val appCompatEditText16 = onView(
            allOf(
                withId(R.id.email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        8
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText16.perform(replaceText("merlin@gmail.com"), closeSoftKeyboard())

        val materialButton11 = onView(
            allOf(
                withId(R.id.saveCustomerInfoButton), withText("Save"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    9
                ),
                isDisplayed()
            )
        )
        materialButton11.perform(click())

        val appCompatEditText17 = onView(
            allOf(
                withId(R.id.cardNumber),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText17.perform(replaceText("1237465663344344"), closeSoftKeyboard())

        val appCompatEditText18 = onView(
            allOf(
                withId(R.id.editTextDate),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText18.perform(replaceText("12/2024"), closeSoftKeyboard())

        val appCompatEditText19 = onView(
            allOf(
                withId(R.id.ccv),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatEditText19.perform(replaceText("253"), closeSoftKeyboard())

        val materialButton12 = onView(
            allOf(
                withId(R.id.savePaymentDetails), withText("SUBMIT"),
                childAtPosition(
                    allOf(
                        withId(R.id.main),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialButton12.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
