package com.example.ozimind.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ozimind.R
import com.example.ozimind.ui.components.OziButton

@Composable
fun WelcomeScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Üst Kısımdaki Devasa Kavisli Yeşil Alan
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Ekranın üst kısmını tamamen kaplaması için
            color = MaterialTheme.colorScheme.primary, // Nil Yeşili
            shape = RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp) // Alt kısmı tam kavisli
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                // Ortadaki logo (Arka plansız, şeffaf)
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "OziMind Logo",
                    modifier = Modifier.size(160.dp) // Logoyu biraz daha büyüterek ekrana yaydık
                )
            }
        }

        // Alt Kısım
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "OziMind",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Zihniniz için huzurlu bir liman",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Noktalar (Sayfa belirteci)
            // Şimdilik sadece statik noktalar ekliyoruz
            androidx.compose.foundation.layout.Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.LightGray))
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary)) // Aktif nokta
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color.LightGray))
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Başlayalım Butonu
            OziButton(
                text = "Başlayalım",
                onClick = onNavigateToRegister
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Giriş Yap Linki
            TextButton(onClick = onNavigateToLogin) {
                Text(
                    text = "Zaten bir hesabınız var mı? Giriş Yap",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
