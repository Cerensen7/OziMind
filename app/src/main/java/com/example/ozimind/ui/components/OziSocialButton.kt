package com.example.ozimind.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ozimind.R // İleride gerçek ikonları eklemek için

@Composable
fun OziSocialButton(
    text: String,
    isApple: Boolean = false, // Apple mı Google mı olduğunu belirlemek için
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isApple) {
        // Klasik Apple Butonu (Siyah arka plan, beyaz yazı)
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            // İleride buraya gerçek Apple ikonunu ekleyebiliriz
            Text(
                text = "", // Apple logosu karakteri (bazı cihazlarda görünmeyebilir)
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    } else {
        // Klasik Google Butonu (Beyaz arka plan, gri çerçeve, koyu yazı)
        OutlinedButton(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.LightGray),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF333333) // Koyu gri metin
            )
        ) {
            // İleride buraya gerçek renkli Google ikonunu ekleyebiliriz
            Text(
                text = "G", // Google logosunu temsil etmesi için
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue, // Şimdilik mavi G harfi
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
