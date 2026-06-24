package com.example.ozimind.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ozimind.data.LocationData
import com.example.ozimind.ui.components.OziButton
import com.example.ozimind.ui.components.OziDatePicker
import com.example.ozimind.ui.components.OziDropdownMenu
import com.example.ozimind.ui.components.OziTextField
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileOnboardingScreen(
    onNavigateBack: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {
    var currentStep by remember { mutableStateOf(1) }
    val totalSteps = 4

    var age by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var maritalStatus by remember { mutableStateOf("") }
    var occupation by remember { mutableStateOf("") }

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
                        Color(0xFFE8ECD7)
                    )
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Üst Bar (Progress & Back)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 8.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (currentStep > 1) currentStep-- else onNavigateBack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, 
                        contentDescription = "Geri",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                
                LinearProgressIndicator(
                    progress = { currentStep.toFloat() / totalSteps.toFloat() },
                    modifier = Modifier
                        .weight(1f)
                        .height(8.dp)
                        .padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = Color.LightGray.copy(alpha = 0.5f)
                )
                
                Text(
                    text = "$currentStep/$totalSteps", 
                    fontSize = 14.sp, 
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // İçerik Kartı (Tüm ekranın altını kaplar)
            Surface(
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                color = Color.White,
                shadowElevation = 16.dp,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 40.dp)
                        .fillMaxSize()
                ) {
                    when (currentStep) {
                        1 -> Step1Location(age, { age = it }, country, { country = it }, city, { city = it })
                        2 -> Step2Gender(gender, { gender = it })
                        3 -> Step3MaritalStatus(maritalStatus, { maritalStatus = it })
                        4 -> Step4Occupation(occupation, { occupation = it })
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    if (isLoading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    } else {
                        OziButton(
                            text = if (currentStep == totalSteps) "Tamamla ve Başla" else "Devam Et",
                            onClick = {
                                if (currentStep < totalSteps) currentStep++ else isLoading = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Step1Location(
    age: String, onAgeChange: (String) -> Unit,
    country: String, onCountryChange: (String) -> Unit,
    city: String, onCityChange: (String) -> Unit
) {
    Text("Seni tanımaya başlayalım ✨", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    Spacer(modifier = Modifier.height(8.dp))
    Text("Doğum tarihin ve nerede yaşadığın, sana önereceğimiz tavsiyeleri özelleştirmemize yardımcı olacak.", color = Color.Gray, fontSize = 14.sp)
    
    Spacer(modifier = Modifier.height(40.dp))
    OziDatePicker(label = "Doğum Tarihi", placeholder = "Seçiniz", selectedDate = age, onDateSelected = onAgeChange)
    
    Spacer(modifier = Modifier.height(16.dp))
    OziDropdownMenu(label = "Ülke", placeholder = "Seçiniz", options = LocationData.countries, selectedOption = country, onOptionSelected = { onCountryChange(it); onCityChange("") })
    
    Spacer(modifier = Modifier.height(16.dp))
    OziDropdownMenu(label = "Şehir", placeholder = "Önce Ülke Seçiniz", options = LocationData.getCitiesForCountry(country), selectedOption = city, onOptionSelected = onCityChange)
}

@Composable
fun Step2Gender(selectedGender: String, onGenderSelected: (String) -> Unit) {
    Text("Cinsiyetin Nedir? 🧑", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    Spacer(modifier = Modifier.height(8.dp))
    Text("Uygulama içi iletişim dilimizi sana göre ayarlayabilmemiz için bilmemiz gerekli.", color = Color.Gray, fontSize = 14.sp)
    
    Spacer(modifier = Modifier.height(40.dp))
    val options = listOf("Kadın", "Erkek", "Belirtmek İstemiyorum", "Diğer")
    SelectableGrid(options, selectedGender, onGenderSelected)
}

@Composable
fun Step3MaritalStatus(selectedStatus: String, onStatusSelected: (String) -> Unit) {
    Text("Medeni Halin Nedir? 💍", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    Spacer(modifier = Modifier.height(8.dp))
    Text("Günlük konuları ve telkinleri seçerken yaşam tarzını göz önünde bulunduracağız.", color = Color.Gray, fontSize = 14.sp)

    Spacer(modifier = Modifier.height(40.dp))
    val options = listOf("Bekar", "Evli", "Dul", "Boşanmış", "İlişkisi Var", "Nişanlı")
    SelectableGrid(options, selectedStatus, onStatusSelected)
}

@Composable
fun Step4Occupation(occupation: String, onOccupationChange: (String) -> Unit) {
    Text("Son Olarak Mesleğin 💼", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    Spacer(modifier = Modifier.height(8.dp))
    Text("İş hayatı ve okul stresi ile başa çıkma içeriklerini sana özel getireceğiz.", color = Color.Gray, fontSize = 14.sp)

    Spacer(modifier = Modifier.height(40.dp))
    OziTextField(
        value = occupation, 
        onValueChange = onOccupationChange, 
        label = "Meslek / Eğitim Durumu", 
        placeholder = "Örn: Bilgisayar Mühendisliği Öğrencisi..."
    )
}

@Composable
fun SelectableGrid(options: List<String>, selectedOption: String, onOptionSelected: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), 
        horizontalArrangement = Arrangement.spacedBy(16.dp), 
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.height(250.dp) // Scroll kaymaması için sabit yükseklik veriyoruz
    ) {
        items(options) { option ->
            val isSelected = option == selectedOption
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.White,
                border = androidx.compose.foundation.BorderStroke(2.dp, if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray.copy(alpha = 0.5f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clickable { onOptionSelected(option) },
                shadowElevation = if (isSelected) 8.dp else 2.dp
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = option,
                        textAlign = TextAlign.Center,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}
