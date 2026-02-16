package ru.codicus.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.White) {
                ContactDetails(
                    contact = Contact(
                        name = "Евгений",
                        surname = "Андреевич",
                        familyName = "Лукашин",
                        isFavorite = true,
                        phone = "+7 495 495 95 95",
                        address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                        email = "ELukashin@practicum.ru",
                        imageRes = null
                    )
                )
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "13:00",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Text(
            text = stringResource(R.string.app_title),
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        ProfileHeader(contact)

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            InfoRow(label = stringResource(R.string.phone), value = contact.phone)
            InfoRow(label = stringResource(R.string.address), value = contact.address)

            contact.email?.let { email ->
                InfoRow(label = stringResource(R.string.email), value = email)
            }
        }
    }
}

@Composable
fun ProfileHeader(contact: Contact) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
        ) {
            if (contact.imageRes != null) {
                Image(
                    painter = painterResource(id = contact.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.fillMaxSize()
                )

                val initials = buildString {
                    append(contact.name.take(1))
                    contact.surname?.let { append(it.take(1)) }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = initials,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = buildString {
                append(contact.name)
                contact.surname?.let { append(" $it") }
            },
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)

        ) {
            Text(
                text = contact.familyName,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold

            )

            if (contact.isFavorite) {
                Image(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )

        Text(
            text = value,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactDetailsPreview1() {
    Surface(color = Color.White) {
        ContactDetails(
            contact = Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                email = "ELukashin@practicum.ru",
                imageRes = null
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactDetailsPreview2() {
    Surface(color = Color.White) {
        ContactDetails(
            contact = Contact(
                name = "Василий",
                surname = null,
                familyName = "Кузякин",
                isFavorite = false,
                phone = "---",
                address = "Ивановская область, дер. Крутово, д. 4",
                email = null,
                imageRes = R.drawable.photo
            )
        )
    }
}