package com.example.missingseven.Screen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.missingseven.Component.HeaderView
import com.example.missingseven.ViewModel.TaskViewModel

/***
 * composable function for workshop contact screen
 */
@Composable
fun WorkshopContactScreen(
    viewModel: TaskViewModel
){
    Column {
        HeaderView(
            header = "Water for the World (W4TW)",
            subtitle = ""
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Workshop are delivered by Engineers Without Borders(EWB) volunteers to school" +
                    " and university students across Canada.Workshops are also available for" +
                    " corporate and community events by contacting W4TW at:"
        )
        DeepLinkText(
            title = "Email: ",
            link = "waterfortheworldworkshops@gmail.com",
            deepLinkEnabled = false,
            null
        )
        DeepLinkText(
            title = "Facebook: ",
            link = "www.facebook.com/WaterForTheWorld/",
            displayTextForLink = null)
        DeepLinkText(
            title = "Instagram: ",
            link = "www.instagram.com/water4tworld/?hl=en",
            displayTextForLink = null)
        DeepLinkText(
            title = "Twitter: ",
            link = "twitter.com/EWBWater4World",
            displayTextForLink = null)
        DeepLinkText(
            title = "Website: ",
            link = "waterfortheworldto.wordpress.com/contact/",
            displayTextForLink = null)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { viewModel.navigateBack() }) {
                Text(text = "Back")
            }
        }
    }
}

@Composable
fun DeepLinkText(
    title: String,
    link: String,
    deepLinkEnabled: Boolean = true,
    displayTextForLink: String?
){
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    Row(modifier = Modifier.padding(10.dp)) {
        Text(text = title)

        if (displayTextForLink != null){
            Text(
                text = displayTextForLink,
                modifier = Modifier.clickable {
                    if (deepLinkEnabled){
                        uriHandler.openUri("https://$link")
                    } else {
                        sendMail(context, link, "", "")
                    }
                },
                color = Color.Blue,
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
        }else{
            Text(
                text = link,
                modifier = Modifier.clickable {
                    if (deepLinkEnabled){
                        uriHandler.openUri("https://$link")
                    } else {
                        sendMail(context, link, "", "")
                    }
                },
                color = Color.Blue,
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
        }

    }
}

fun sendMail(
    context: Context,
    to: String,
    subject: String, text: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // TODO: Handle case where no email app is available
    } catch (t: Throwable) {
        // TODO: Handle potential other type of exceptions
    }
}