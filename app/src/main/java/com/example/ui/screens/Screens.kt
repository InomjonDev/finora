package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.AuthUiState
import com.example.ui.viewmodel.AuthViewModel

/**
 * Onboarding / Welcome screen
 */
@Composable
fun OnboardingScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .testTag("onboarding_screen")
    ) {
        // Subtle dotted background pattern
        Canvas(modifier = Modifier.fillMaxSize()) {
            val columns = (size.width / 60).toInt() + 1
            val rows = (size.height / 60).toInt() + 1
            for (i in 0 until columns) {
                for (j in 0 until rows) {
                    drawCircle(
                        color = Color(0xFFBFC9C3).copy(alpha = 0.25f),
                        radius = 2f,
                        center = Offset(i * 60f, j * 60f)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SpendTrack",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = ForestGreenPrimary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.5).sp
                    )
                )
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = "Language Selector",
                        tint = ForestGreenPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Hero Stacking Illustration Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.1f)
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                // Background overlapping layers
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .fillMaxHeight(0.75f)
                        .rotate(4f)
                        .shadow(2.dp, RoundedCornerShape(24.dp))
                        .background(ForestGreenContainer.copy(alpha = 0.08f), RoundedCornerShape(24.dp))
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .fillMaxHeight(0.75f)
                        .rotate(-5f)
                        .shadow(4.dp, RoundedCornerShape(24.dp))
                        .background(OrangeContainer.copy(alpha = 0.06f), RoundedCornerShape(24.dp))
                )

                // Master layout containing Phone + Paper documents
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.82f)
                        .fillMaxHeight(0.85f)
                        .shadow(12.dp, RoundedCornerShape(24.dp))
                        .border(1.dp, OutlineVariantColor.copy(alpha = 0.5f), RoundedCornerShape(24.dp)),
                    colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Underneath: Receipts visual collage (Drawn inside)
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .fillMaxHeight(0.7f)
                                .align(Alignment.BottomStart)
                                .offset(x = 12.dp, y = (-20).dp)
                        ) {
                            val path = Path().apply {
                                moveTo(0f, 0f)
                                lineTo(140f, 0f)
                                lineTo(160f, 220f)
                                lineTo(140f, 210f)
                                lineTo(120f, 220f)
                                lineTo(100f, 210f)
                                lineTo(80f, 220f)
                                lineTo(60f, 210f)
                                lineTo(40f, 220f)
                                lineTo(20f, 210f)
                                lineTo(00f, 220f)
                                close()
                            }
                            drawPath(
                                path = path,
                                color = Color(0xFFF9FAFB),
                            )
                            drawPath(
                                path = path,
                                color = Color(0xFFE5E7EB),
                                style = Stroke(width = 2f)
                            )
                            // Draw receipt content lines
                            drawLine(Color(0xFF9CA3AF), Offset(20f, 30f), Offset(120f, 30f), strokeWidth = 3f)
                            drawLine(Color(0xFFD1D5DB), Offset(20f, 50f), Offset(100f, 50f), strokeWidth = 2f)
                            drawLine(Color(0xFFD1D5DB), Offset(20f, 70f), Offset(110f, 70f), strokeWidth = 2f)
                            drawLine(Color(0xFFD1D5DB), Offset(20f, 90f), Offset(80f, 90f), strokeWidth = 2f)
                            drawLine(Color(0xFFD1D5DB), Offset(20f, 110f), Offset(95f, 110f), strokeWidth = 2f)
                            // Total text representation
                            drawLine(Color(0xFF4B5563), Offset(20f, 150f), Offset(110f, 150f), strokeWidth = 4f)
                        }

                        // Superimposed: High fidelity smartphone graphic
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.55f)
                                .fillMaxHeight(0.85f)
                                .align(Alignment.CenterEnd)
                                .offset(x = (-16).dp, y = 0.dp)
                                .shadow(8.dp, RoundedCornerShape(18.dp))
                                .background(ForestGreenPrimary, RoundedCornerShape(18.dp))
                                .border(3.dp, Color(0xFFE5E7EB), RoundedCornerShape(18.dp))
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Camera notch
                                Box(
                                    modifier = Modifier
                                        .width(36.dp)
                                        .height(10.dp)
                                        .background(Color.Black, RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp))
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("SpendTrack", fontSize = 7.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                    Icon(Icons.Default.Wifi, contentDescription = null, modifier = Modifier.size(7.dp), tint = Color.White)
                                }

                                Spacer(modifier = Modifier.height(10.dp))
                                // Balance Card inside widget
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF0D4335), RoundedCornerShape(6.dp))
                                        .padding(6.dp)
                                ) {
                                    Column {
                                        Text("TOTAL SPEND", fontSize = 5.sp, color = Color(0xFF80BEA6))
                                        Text("$1,842.50", fontSize = 9.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("vs last month", fontSize = 4.sp, color = Color(0xFF80BEA6))
                                            Text("-12.4%", fontSize = 4.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4ADE80))
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Sparkline graph inside smartphone
                                Canvas(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(vertical = 4.dp)
                                ) {
                                    val points = listOf(
                                        Offset(0f, size.height * 0.8f),
                                        Offset(size.width * 0.2f, size.height * 0.7f),
                                        Offset(size.width * 0.4f, size.height * 0.3f),
                                        Offset(size.width * 0.6f, size.height * 0.5f),
                                        Offset(size.width * 0.8f, size.height * 0.1f),
                                        Offset(size.width, size.height * 0.2f)
                                    )
                                    val path = Path().apply {
                                        if (points.isNotEmpty()) {
                                            moveTo(points[0].x, points[0].y)
                                            for (i in 1 until points.size) {
                                                lineTo(points[i].x, points[i].y)
                                            }
                                        }
                                    }
                                    drawPath(
                                        path = path,
                                        color = OrangeContainer,
                                        style = Stroke(width = 3f)
                                    )
                                    // Draw shaded area
                                    val fillPath = Path().apply {
                                        addPath(path)
                                        lineTo(size.width, size.height)
                                        lineTo(0f, size.height)
                                        close()
                                    }
                                    drawPath(
                                        path = fillPath,
                                        brush = Brush.verticalGradient(
                                            colors = listOf(OrangeContainer.copy(alpha = 0.3f), Color.Transparent)
                                        )
                                    )
                                }
                            }
                        }

                        // Floating Micro-UI component over-lying bottom right corner
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = 10.dp, y = 14.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .shadow(12.dp, RoundedCornerShape(14.dp))
                                    .border(1.dp, OutlineVariantColor.copy(alpha = 0.8f), RoundedCornerShape(14.dp)),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(OrangeContainer, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.DocumentScanner,
                                            contentDescription = "Scanner icon",
                                            modifier = Modifier.size(16.dp),
                                            tint = Color.White
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = "RECEIPT SCANNED",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontSize = 8.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = OutlineColor
                                            )
                                        )
                                        Text(
                                            text = "$42.50",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.Black,
                                                color = OnSurfaceColor
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Value Prop Section
            Text(
                text = "Take control of your spending.",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = OnBackgroundColor,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Scan receipts, track expenses, and gain insights effortlessly with our automated AI assistant.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = OnSurfaceVariantColor,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(40.dp))

            // Floating Bottom CTA Buttons
            Button(
                onClick = onNavigateToSignUp,
                colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary),
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("get_started_btn"),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Play"
                    )
                    Text(
                        text = "Get Started",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account? ",
                    style = MaterialTheme.typography.bodySmall.copy(color = OutlineColor)
                )
                Text(
                    text = "Log In",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = ForestGreenPrimary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable { onNavigateToLogin() }
                )
            }
        }
    }
}

/**
 * Login Screen
 */
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignUp: () -> Unit,
    onAuthSuccess: () -> Unit
) {
    val email by authViewModel.loginEmail.collectAsState()
    val password by authViewModel.loginPassword.collectAsState()
    val passwordVisible by authViewModel.loginPasswordVisible.collectAsState()
    val uiState by authViewModel.uiState.collectAsState()

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    // Error logging overlay trigger
    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Error) {
            Toast.makeText(context, (uiState as AuthUiState.Error).error, Toast.LENGTH_LONG).show()
            authViewModel.resetState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
            .testTag("login_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            // Icon box wrapper with Wallet Icon inside
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(ForestGreenContainer.copy(alpha = 0.1f), RoundedCornerShape(20.dp))
                    .border(2.dp, ForestGreenPrimary.copy(alpha = 0.15f), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBalanceWallet,
                    contentDescription = "SpendTrack wallet logo",
                    modifier = Modifier.size(38.dp),
                    tint = ForestGreenPrimary
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Log in to SpendTrack",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = OnBackgroundColor,
                    letterSpacing = (-0.5).sp
                )
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Email text field input
            OutlinedTextField(
                value = email,
                onValueChange = { authViewModel.updateLoginEmail(it) },
                placeholder = { Text("Email address", color = OutlineColor.copy(alpha = 0.6f)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("login_email_input"),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = SurfaceVariantColor,
                    unfocusedContainerColor = SurfaceVariantColor,
                    focusedBorderColor = ForestGreenPrimary,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = OnBackgroundColor,
                    unfocusedTextColor = OnBackgroundColor
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password text field input with visibility toggler
            OutlinedTextField(
                value = password,
                onValueChange = { authViewModel.updateLoginPassword(it) },
                placeholder = { Text("Password", color = OutlineColor.copy(alpha = 0.6f)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("login_password_input"),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordTransformation,
                trailingIcon = {
                    IconButton(onClick = { authViewModel.toggleLoginPasswordVisibility() }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Toggle password visibility",
                            tint = OutlineColor.copy(alpha = 0.8f)
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = SurfaceVariantColor,
                    unfocusedContainerColor = SurfaceVariantColor,
                    focusedBorderColor = ForestGreenPrimary,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = OnBackgroundColor,
                    unfocusedTextColor = OnBackgroundColor
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // "Continue" primary button with micro-interactions
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                if (uiState is AuthUiState.Loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                            .background(ForestGreenContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    }
                } else {
                    Button(
                        onClick = {
                            focusManager.clearFocus()
                            authViewModel.performLogin(onAuthSuccess)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ForestGreenPrimary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("login_continue_btn")
                    ) {
                        Text(
                            text = "Continue",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Elegant Divider "or"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f).height(1.dp).background(OutlineVariantColor.copy(alpha = 0.5f)))
                Text(
                    text = "or",
                    style = MaterialTheme.typography.bodySmall.copy(color = OutlineColor.copy(alpha = 0.8f)),
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                Box(modifier = Modifier.weight(1f).height(1.dp).background(OutlineVariantColor.copy(alpha = 0.5f)))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Social Buttons Grid (Google / Apple)
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Continue with Google
                OutlinedButton(
                    onClick = {
                        Toast.makeText(context, "Google authentication demo!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, OutlineVariantColor)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Drawing miniature G emblem inside canvas to bypass dependency issue
                        Canvas(modifier = Modifier.size(20.dp)) {
                            // Circular colored parts mimicking standard logo visual
                            drawCircle(color = Color(0xFFEA4335), radius = size.minDimension / 2.3f, center = center)
                            drawCircle(color = Color.White, radius = size.minDimension / 4f, center = center)
                            // Clean geometric line to stylize the "G" shape
                            drawLine(Color.White, Offset(center.x, center.y), Offset(size.width * 0.85f, center.y), strokeWidth = 4f)
                        }
                        Text(
                            text = "Continue with Google",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = OnBackgroundColor
                            )
                        )
                    }
                }

                // Continue with Apple
                OutlinedButton(
                    onClick = {
                        Toast.makeText(context, "Apple authentication demo!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, OutlineVariantColor)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Canvas(modifier = Modifier.size(20.dp)) {
                            // Draw apple body (rounded circle with slight bottom dip)
                            drawCircle(
                                color = OnBackgroundColor,
                                radius = size.minDimension / 2.5f,
                                center = Offset(center.x, center.y + 1f)
                            )
                            // Draw leaf
                            drawCircle(
                                color = OnBackgroundColor,
                                radius = size.minDimension / 6.0f,
                                center = Offset(center.x + 3f, center.y - 7f)
                            )
                        }
                        Text(
                            text = "Continue with Apple",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = OnBackgroundColor
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Bottom Navigation Footer Links
            Text(
                text = "New to SpendTrack? Create an account",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = OutlineColor,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .clickable { onNavigateToSignUp() }
                    .padding(8.dp)
            )

            Text(
                text = "Forgot password?",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = OutlineColor.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .clickable {
                        Toast.makeText(context, "Password recovery link sent!", Toast.LENGTH_LONG).show()
                    }
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

/**
 * Sign Up Screen
 */
@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    onNavigateToLogin: () -> Unit,
    onAuthSuccess: () -> Unit
) {
    val name by authViewModel.signUpName.collectAsState()
    val email by authViewModel.signUpEmail.collectAsState()
    val password by authViewModel.signUpPassword.collectAsState()
    val confirmPassword by authViewModel.signUpConfirmPassword.collectAsState()
    val passwordVisible by authViewModel.signUpPasswordVisible.collectAsState()
    val termsAccepted by authViewModel.termsAccepted.collectAsState()
    val uiState by authViewModel.uiState.collectAsState()

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Error) {
            Toast.makeText(context, (uiState as AuthUiState.Error).error, Toast.LENGTH_LONG).show()
            authViewModel.resetState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .statusBarsPadding()
            .testTag("signup_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Upper Header section with Mini-logo
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(ForestGreenContainer.copy(alpha = 0.1f), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBalanceWallet,
                    contentDescription = "SpendTrack wallet logo",
                    modifier = Modifier.size(28.dp),
                    tint = ForestGreenPrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = OnBackgroundColor
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Join SpendTrack and take control of your financial journey today.",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = OutlineColor,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Form container card mimicking LEVEL 1 outline and structure from mockups
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, RoundedCornerShape(16.dp))
                    .border(1.dp, OutlineVariantColor, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = SurfaceColor),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Full Name input field section
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Full Name",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = OnSurfaceColor
                            )
                        )
                        OutlinedTextField(
                            value = name,
                            onValueChange = { authViewModel.updateSignUpName(it) },
                            placeholder = { Text("John Doe", color = OutlineColor.copy(alpha = 0.5f)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = OutlineColor
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("signup_name_input"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = OnBackgroundColor,
                                unfocusedTextColor = OnBackgroundColor,
                                focusedBorderColor = ForestGreenPrimary,
                                unfocusedBorderColor = OutlineVariantColor
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    // Email input field section
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Email Address",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = OnSurfaceColor
                            )
                        )
                        OutlinedTextField(
                            value = email,
                            onValueChange = { authViewModel.updateSignUpEmail(it) },
                            placeholder = { Text("john@example.com", color = OutlineColor.copy(alpha = 0.5f)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null,
                                    tint = OutlineColor
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("signup_email_input"),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = OnBackgroundColor,
                                unfocusedTextColor = OnBackgroundColor,
                                focusedBorderColor = ForestGreenPrimary,
                                unfocusedBorderColor = OutlineVariantColor
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    // Password input field section
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = OnSurfaceColor
                            )
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = { authViewModel.updateSignUpPassword(it) },
                            placeholder = { Text("••••••••", color = OutlineColor.copy(alpha = 0.5f)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = null,
                                    tint = OutlineColor
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = { authViewModel.toggleSignUpPasswordVisibility() }) {
                                    Icon(
                                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                        contentDescription = "Toggle password visibility",
                                        tint = OutlineColor
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("signup_password_input"),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordTransformation,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = OnBackgroundColor,
                                unfocusedTextColor = OnBackgroundColor,
                                focusedBorderColor = ForestGreenPrimary,
                                unfocusedBorderColor = OutlineVariantColor
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    // Confirm Password input field section
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = "Confirm Password",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = OnSurfaceColor
                            )
                        )
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { authViewModel.updateSignUpConfirmPassword(it) },
                            placeholder = { Text("••••••••", color = OutlineColor.copy(alpha = 0.5f)) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.LockReset,
                                    contentDescription = null,
                                    tint = OutlineColor
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("signup_confirm_password_input"),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordTransformation,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = OnBackgroundColor,
                                unfocusedTextColor = OnBackgroundColor,
                                focusedBorderColor = ForestGreenPrimary,
                                unfocusedBorderColor = OutlineVariantColor
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                    }

                    // Terms check agreement row with custom Orange styling
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .toggleable(
                                value = termsAccepted,
                                onValueChange = { authViewModel.updateTermsAccepted(it) }
                            ),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Checkbox(
                            checked = termsAccepted,
                            onCheckedChange = { authViewModel.updateTermsAccepted(it) },
                            colors = CheckboxDefaults.colors(
                                checkedColor = OrangeSecondary,
                                uncheckedColor = OutlineVariantColor
                            ),
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "I agree to the Terms of Service and Privacy Policy.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = OutlineColor,
                                lineHeight = 16.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // "Sign Up" button inside card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        if (uiState is AuthUiState.Loading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .background(OrangeContainer.copy(alpha = 0.8f)),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                            }
                        } else {
                            Button(
                                onClick = {
                                    focusManager.clearFocus()
                                    authViewModel.performSignUp(onAuthSuccess)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = OrangeContainer),
                                shape = CircleShape,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .testTag("signup_btn")
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PersonAdd,
                                        contentDescription = null,
                                        tint = OnOrangeContainer,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "Sign Up",
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = OnOrangeContainer
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Footer Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account? ",
                    style = MaterialTheme.typography.bodySmall.copy(color = OutlineColor)
                )
                Text(
                    text = "Log in",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = ForestGreenPrimary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable { onNavigateToLogin() }
                )
            }
        }
    }
}

// Custom Password Visual Transformation defining neat, heavy bullets instead of characters
object PasswordTransformation : VisualTransformation {
    override fun filter(text: androidx.compose.ui.text.AnnotatedString): TransformedText {
        val bulletChar = '•'
        val bulletString = bulletChar.toString().repeat(text.text.length)
        return TransformedText(
            androidx.compose.ui.text.AnnotatedString(bulletString),
            OffsetMapping.Identity
        )
    }
}
