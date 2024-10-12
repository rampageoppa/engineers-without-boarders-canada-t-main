package com.example.missingseven.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.missingseven.Database.Entity.Country
import com.example.missingseven.R
import com.example.missingseven.ui.theme.ItemBg
import com.example.missingseven.ui.theme.TextContent
import com.example.missingseven.ui.theme.TextDes

val resIdMap = hashMapOf(
    Pair("Canada", R.mipmap.ic_country_canada),
    Pair("Canadian First Nations", R.mipmap.ic_country_canada_first_nation),
    Pair("Kuwait", R.mipmap.ic_country_kuwait),
    Pair("South Africa", R.mipmap.ic_country_south_africa),
    Pair("Ghana", R.mipmap.ic_country_ghana),
    Pair("Kenya", R.mipmap.ic_country_kenya),
    Pair("Malawi", R.mipmap.ic_country_malawi),
)

/***
 * composable function for country selecting screen
 */
@Composable
fun CountryScreen(
    listA: List<Country>,
    listB: List<Country>,
    countryClick: (Country) -> Unit,
    backHandler: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Choose A Country",
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
        Text(
            text = "In this section you will be given some money to buy materials for the filter and a set of instructions on how to assemble the filter.",
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp),
            color = TextDes,
            fontSize = 16.sp
        )
        Text(
            text = "MAKE at least TWO filters:" +
                    "\nOne from list A ......and One from list B." +
                    "\nScroll/Click on a country to begin.",
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp),
            color = TextContent,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        Row(
            modifier = Modifier.padding(horizontal = 80.dp)
        ) {
            Text(
                text = "List A",

            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "List B")
        }
        Row(modifier = Modifier.weight(1f)) {
            LazyColumn(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                itemsIndexed(listA) { _, item ->
                    CountryItemView(item = item, countryClick)
                }
            }
            LazyColumn(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                itemsIndexed(listB) { _, item ->
                    CountryItemView(item = item, countryClick)
                }
            }
        }
        DeepLinkText(
            title = "*Source:",
            link = "www.watertoday.ca/",
            displayTextForLink = "www.watertoday.ca/")
        Row(Modifier.padding(16.dp)) {
            Button(onClick = { backHandler() }, Modifier.fillMaxWidth()) {
                Text(text = "BACK")
            }
        }
    }
}

@Composable
fun CountryItemView(
    item: Country,
    onClick: (Country) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                0.dp,
                brush = SolidColor(Color.Black),
                shape = RoundedCornerShape(5.dp)
            )
            .clickable {
                onClick(item)
            }
    ) {
        Card(
            backgroundColor = ItemBg,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(6.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = resIdMap[item.name] ?: R.mipmap.ic_country_canada
                    ),
                    contentDescription = "img",
                    modifier = Modifier.size(60.dp)
                )
                Text(
                    text = item.name,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}


