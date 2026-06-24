package com.example.ozimind.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OziButton(
    text: String, // Butonun üzerinde ne yazacağı
    onClick: () -> Unit, // Butona basılınca ne olacağı
    modifier: Modifier = Modifier // Dışarıdan verilebilecek ekstra tasarım ayarları
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = androidx.compose.foundation.shape.CircleShape, // Tam yuvarlak (hap şeklinde) köşeler
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Arka plan rengi
            contentColor = MaterialTheme.colorScheme.onPrimary // Yazı rengi
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
