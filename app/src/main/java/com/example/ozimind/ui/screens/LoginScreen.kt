package com.example.ozimind.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ozimind.R
import com.example.ozimind.ui.components.OziButton
import com.example.ozimind.ui.components.OziSocialButton
import com.example.ozimind.ui.components.OziTextField
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    onNavigateBack: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    if (isLoading) {
        LaunchedEffect(Unit) {
            delay(1500)
            isLoading = false
            onNavigateToHome()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color(0xFFE8ECD7) // OziMind krem arka planı
                    )
                )
            )
    ) {
        // Geri Tuşu
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier
                .padding(top = 32.dp, start = 8.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Geri Dön",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            
            // Maskot veya Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "OziMind Logo",
                modifier = Modifier
                    .height(100.dp)
                    .padding(bottom = 16.dp)
            )

            // Beyaz Kart
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                shadowElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Tekrar hoş geldiniz",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    OziTextField(
                        value = email,
                        onValueChange = { 
                            email = it
                            errorMessage = ""
                        },
                        label = "E-posta",
                        placeholder = "E-posta adresinizi girin",
                        leadingIcon = {
                            Icon(Icons.Outlined.Email, contentDescription = "Email", tint = MaterialTheme.colorScheme.primary)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OziTextField(
                        value = password,
                        onValueChange = { 
                            password = it
                            errorMessage = ""
                        },
                        label = "Şifre",
                        placeholder = "Şifrenizi girin",
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(Icons.Outlined.Lock, contentDescription = "Şifre", tint = MaterialTheme.colorScheme.primary)
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Şifremi unuttum",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier.align(Alignment.End)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    if (isLoading) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    } else {
                        OziButton(
                            text = "Giriş Yap",
                            onClick = {
                                if (email.isEmpty() || !email.contains("@")) {
                                    errorMessage = "Lütfen geçerli bir e-posta girin."
                                } else if (password.length < 8) {
                                    errorMessage = "Şifreniz en az 8 karakter olmalıdır."
                                } else {
                                    isLoading = true
                                }
                            }
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Text(
                        text = "veya",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    OziSocialButton(
                        text = "Google ile Devam et",
                        onClick = { isLoading = true }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OziSocialButton(
                        text = "Apple ile Devam et",
                        isApple = true,
                        onClick = { isLoading = true }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(onClick = onNavigateToRegister) {
                Text(
                    text = "Hesabınız yok mu? Kaydol",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
