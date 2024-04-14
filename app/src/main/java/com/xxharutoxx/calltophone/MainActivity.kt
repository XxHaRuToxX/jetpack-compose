package com.xxharutoxx.calltophone

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xxharutoxx.calltophone.ui.theme.CallToPhoneTheme
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
    private val _intentFlow = MutableStateFlow<Intent?>(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CallToPhoneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    Column(
                        Modifier
                            .fillMaxSize()
                            .height(900.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(

                        ) {
                            sendWhatsAppMessage(context = LocalContext.current)
                        }
                        Row(
                            Modifier.height(400.dp)
                        ){
                            openDialer()
                        }
                        Row(
                            Modifier.height(200.dp)
                        ) {
                            DisplayAlertDialog()
                        }

                    }
                }
            }
        }
    }

    @Composable
    fun openDialer() {

        // in the below line, we are
        // creating variables for URL
        val phoneNumber = remember {
            mutableStateOf(TextFieldValue())
        }

        // on below line we are creating
        // a variable for a context
        val ctx = LocalContext.current

        // on below line we are creating a column
        Column(
            // on below line we are specifying modifier
            // and setting max height and max width
            // for our column
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth()
                // on below line we are
                // adding padding for our column
                .padding(5.dp),
            // on below line we are specifying horizontal
            // and vertical alignment for our column
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // in the below line, we are creating a
            // text field for getting URL from user.
            TextField(
                // on below line we are specifying value
                // for our  text field.
                value = phoneNumber.value,

                // on below line we are adding on value
                // change for text field.
                onValueChange = { phoneNumber.value = it },

                // on below line we are adding place holder as text
                placeholder = { Text(text = "Enter your phone number") },

                // on below line we are adding modifier to it
                // and adding padding to it and filling max width
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),

                // on below line we are adding text style
                // specifying color and font size to it.
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

                // on below line we are adding single line to it.
                singleLine = true,
            )

            // on below line adding a spacer.
            Spacer(modifier = Modifier.height(20.dp))

            // on below line adding a button to open URL
            Button(onClick = {
                // on below line we are opening the dialer of our
                // phone and passing phone number.
                // Use format with "tel:" and phoneNumber created is
                // stored in u.
                val u = Uri.parse("tel:" + phoneNumber.value.text)

                // Create the intent and set the data for the
                // intent as the phone number.
                val i = Intent(Intent.ACTION_DIAL, u)
                try {

                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    ctx.startActivity(i)
                } catch (s: SecurityException) {

                    // show() method display the toast with
                    // exception message.
                    Toast.makeText(ctx, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }


            }) {
                // on below line creating a text for our button.
                Text(
                    // on below line adding a text ,
                    // padding, color and font size.
                    text = "Dial",
                    modifier = Modifier.padding(10.dp),
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
        }
    }

    @Composable
    fun sendWhatsAppMessage(context: Context) {

        // on below line creating a variable
        // for phone number and message.
        val message = remember {
            mutableStateOf("")
        }
        val phoneNumber = remember {
            mutableStateOf("")
        }

        // on below line we are creating a column,
        Column(
            // on below line we are adding a modifier to it,
            modifier = Modifier
                .fillMaxSize()
                // on below line we are adding a padding.
                .padding(all = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // on below line we are adding a text for heading.
            Text(
                // on below line we are specifying text
                text = "Send SMS to WhatsApp",
                // on below line we are
                // specifying text color,
                // font size and font weight
                color = Color.Green,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // on below line we are creating a
            // text field for our phone number.
            TextField(
                // on below line we are specifying
                // value for our email text field.
                value = phoneNumber.value,
                // on below line we are adding on value
                // change for text field.
                onValueChange = { phoneNumber.value = it },
                // on below line we are adding place holder as text
                // as "Enter your email"
                placeholder = { Text(text = "Enter your whatsapp phone number") },
                // on below line we are adding modifier to it
                // and adding padding to it and filling max width
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                // on below line we are adding text style
                // specifying color and font size to it.
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                // on below line we are adding single line to it.
                singleLine = true,
            )

            // on below line we are adding a spacer.
            Spacer(modifier = Modifier.height(10.dp))

            // on below line we are creating a text
            // field for our message number.
            TextField(
                // on below line we are specifying
                // value for our message text field.
                value = message.value,
                // on below line we are adding on value
                // change for text field.
                onValueChange = { message.value = it },
                // on below line we are adding place holder
                // as text as "Enter your email"
                placeholder = { Text(text = "Enter your message") },
                // on below line we are adding modifier to it
                // and adding padding to it and filling max width
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                // on below line we are adding text style
                // specifying color and font size to it.
                textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
                // on below line we are adding single line to it.
                singleLine = true,
            )
            // on below line adding a spacer.
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    // on below line we are starting activity
                    // to send the sms from whatsapp.
                    context.startActivity(
                        // on below line we are opening the intent.
                        Intent(
                            // on below line we are calling
                            // uri to parse the data
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                // on below line we are passing uri,
                                // message and whats app phone number.
                                java.lang.String.format(
                                    "https://api.whatsapp.com/send?phone=%s&text=%s",
                                    phoneNumber.value,
                                    message.value
                                )
                            )
                        )
                    )

                },
                // on below line adding
                // a modifier for our button.
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                // on below line adding a text for our button.
                Text(text = "Send Message on WhatsApp")
            }
        }
    }

    @Composable
    fun CustomAlertDialog(
        onDismiss: ()-> Unit
    ){
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {},
            modifier = Modifier.height(250.dp),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Privacy" )
                    Text(text = "Privacy policy",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(180.dp)
                            .padding(10.dp)
                    ) {
                        Text(text = "Privacy link")
                    }
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(180.dp)
                            .padding(10.dp)
                    ) {
                        Text(text = "Otra vista")
                    }
                }
            }
        )
    }

    @Composable
    fun DisplayAlertDialog(){

        var showDialog by remember {
            mutableStateOf(false)
        }

        if(showDialog){
            CustomAlertDialog(onDismiss = { showDialog = false })
        }

        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Button(onClick = { showDialog = true}) {
                    Text(text = "Privacy policy")
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        CallToPhoneTheme {
            Greeting("Android")
        }
    }
}
