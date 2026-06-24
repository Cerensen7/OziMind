package com.example.ozimind.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ozimind.R
import com.example.ozimind.ui.components.OziButton

@Composable
fun LandingScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        // Üst Kısım Logo Resmi
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "OziMind Logo",
            modifier = Modifier
                .height(100.dp) // Logoyu büyüttük
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Oluşturduğumuz İllüstrasyon Resmi (Yuvarlak formda)
        Image(
            painter = painterResource(id = R.drawable.meditation_illustration),
            contentDescription = "Meditation Illustration",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(280.dp)
                .clip(CircleShape) // Resmi yuvarlak keser
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Ana Başlık
        Text(
            text = "Zihninizi dinlendirmek\niçin doğru yerdesiniz.",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface // Okunabilir renk
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Alt Yazı
        Text(
            text = "Bugün kendiniz için ne yapmak istersiniz?",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f) // Daha koyu ve okunabilir bir alt yazı
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Başla Butonu
        OziButton(
            text = "Hadi Başlayalım", // Oku kaldırdık
            onClick = onNavigateToRegister
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Giriş Yap Linki
        TextButton(onClick = onNavigateToLogin) {
            Text(
                text = "Zaten bir hesabınız var mı? Giriş yapın",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
