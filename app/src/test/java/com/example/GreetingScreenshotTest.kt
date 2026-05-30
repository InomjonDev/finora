package com.example

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.ui.theme.MyApplicationTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel8, sdk = [36])
class GreetingScreenshotTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun greeting_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        AppNavigation()
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/greeting.png")
  }

  @Test
  fun navigation_and_input_flow_test() {
    composeTestRule.setContent {
      MyApplicationTheme {
        AppNavigation()
      }
    }

    // Verify Onboarding Screen is shown
    composeTestRule.onNodeWithTag("onboarding_screen").assertExists()

    // Click on Get Started to go to Sign Up
    composeTestRule.onNodeWithTag("get_started_btn").performClick()
    composeTestRule.waitForIdle()

    // Verify Sign Up Screen is shown
    composeTestRule.onNodeWithTag("signup_screen").assertExists()

    // Input Name, Email, Password, Confirm Password, Accept terms
    composeTestRule.onNodeWithTag("signup_name_input").performTextInput("John Doe")
    composeTestRule.onNodeWithTag("signup_email_input").performTextInput("john.doe@example.com")
    composeTestRule.onNodeWithTag("signup_password_input").performTextInput("password123")
    composeTestRule.onNodeWithTag("signup_confirm_password_input").performTextInput("password123")
    
    // Click Sign Up button
    composeTestRule.onNodeWithTag("signup_btn").performClick()
    composeTestRule.waitForIdle()
  }
}
