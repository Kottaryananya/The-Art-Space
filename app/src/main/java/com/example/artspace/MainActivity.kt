package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F1EA) // Soft aesthetic background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {

    var currentArtwork by remember { mutableIntStateOf(0) }
    var showFactDialog by remember { mutableStateOf(false) }

    val artworks = remember {
        listOf(
            Artwork(
                R.drawable.art1,
                "Starry Night",
                "Vincent van Gogh (1889)",
                "The painting depicts the view from the east-facing window of his asylum room at Saint-Rémy-de-Provence."
            ),
            Artwork(
                R.drawable.art2,
                "Mona Lisa",
                "Leonardo da Vinci (1503)",
                "The Mona Lisa is one of the most valuable paintings in the world."
            ),
            Artwork(
                R.drawable.art3,
                "The Scream",
                "Edvard Munch (1893)",
                "The Scream has been the target of several high-profile art thefts."
            ),
            Artwork(
                R.drawable.art4,
                "The Persistence of Memory",
                "Salvador Dalí (1931)",
                "Dalí’s melting clocks were inspired by Camembert cheese."
            ),
            Artwork(
                R.drawable.art5,
                "Girl with a Pearl Earring",
                "Johannes Vermeer (c. 1665)",
                "The painting is not a portrait but a 'tronie'."
            ),
            Artwork(
                R.drawable.art6,
                "American Gothic",
                "Grant Wood (1930)",
                "The models were Grant Wood's sister and their family dentist."
            ),
            Artwork(
                R.drawable.art7,
                "Bharat Mata",
                "Abanindranath Tagore (1905)",
                "Created during the Swadeshi movement, it became a symbol of Indian nationalism."
            ),
            Artwork(
                R.drawable.art8,
                "Shakuntala",
                "Raja Ravi Varma (1870)",
                "Depicts Shakuntala searching for her lover Dushyanta."
            )
        )
    }

    val artwork = artworks[currentArtwork]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        // 🖼 POLAROID FRAME
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(14.dp, RoundedCornerShape(12.dp))
                .clickable { showFactDialog = true },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {

                Image(
                    painter = painterResource(id = artwork.imageRes),
                    contentDescription = artwork.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                )

                Spacer(modifier = Modifier.height(28.dp)) // Bigger bottom like polaroid

                Text(
                    text = artwork.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = artwork.artist,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 🔄 Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = {
                    showFactDialog = false
                    currentArtwork =
                        if (currentArtwork > 0) currentArtwork - 1
                        else artworks.size - 1
                }
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    showFactDialog = false
                    currentArtwork =
                        if (currentArtwork < artworks.size - 1) currentArtwork + 1
                        else 0
                }
            ) {
                Text("Next")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    // 📝 FACT DIALOG
    if (showFactDialog) {
        AlertDialog(
            onDismissRequest = { showFactDialog = false },
            title = {
                Text(
                    text = artwork.title,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(text = artwork.fact)
            },
            confirmButton = {
                TextButton(onClick = { showFactDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}

data class Artwork(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val fact: String
)
