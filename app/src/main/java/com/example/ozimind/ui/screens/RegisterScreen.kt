package com.example.ozimind.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
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
import com.example.ozimind.ui.components.OziTextField
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            
            // Maskot veya Logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "OziMind Logo",
                modifier = Modifier
                    .height(80.dp)
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
                        text = "Kayıt Ol",
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
                        value = name,
                        onValueChange = { 
                            name = it
                            errorMessage = ""
                        },
                        label = "Ad Soyad",
                        placeholder = "Adınızı girin",
                        leadingIcon = {
                            Icon(Icons.Outlined.Person, contentDescription = "İsim", tint = MaterialTheme.colorScheme.primary)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

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
                        placeholder = "Şifrenizi oluşturun",
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(Icons.Outlined.Lock, contentDescription = "Şifre", tint = MaterialTheme.colorScheme.primary)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OziTextField(
                        value = confirmPassword,
                        onValueChange = { 
                            confirmPassword = it
                            errorMessage = ""
                        },
                        label = "Şifre Tekrarı",
                        placeholder = "Şifrenizi onaylayın",
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(Icons.Outlined.Lock, contentDescription = "Şifre Tekrar", tint = MaterialTheme.colorScheme.primary)
                        }
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    if (isLoading) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    } else {
                        OziButton(
                            text = "Kayıt Ol",
                            onClick = {
                                if (name.isEmpty() || email.isEmpty() || !email.contains("@")) {
                                    errorMessage = "Lütfen tüm alanları geçerli doldurun."
                                } else if (password.length < 8) {
                                    errorMessage = "Şifreniz en az 8 karakter olmalıdır."
                                } else if (password != confirmPassword) {
                                    errorMessage = "Şifreler eşleşmiyor."
                                } else {
                                    isLoading = true
                                }
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            TextButton(onClick = onNavigateToLogin) {
                Text(
                    text = "Zaten hesabınız var mı? Giriş Yap",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
