package com.example.lawpavilion.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lawpavilion.R
import com.example.lawpavilion.ui.theme.*
import com.example.lawpavilion.ui.utils.SizeClass

@Composable
fun TopBar(
    expanded: Boolean,
    railSizeIfSmallScreen: Dp,
    railSizeIfLargeScreen: Dp,
    windowSizeClass: SizeClass
) {
    Row(
        Modifier
            .height(76.dp)
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)

            .padding(start = if (expanded && railSizeIfSmallScreen == 230.dp) {
                if (railSizeIfLargeScreen == 230.dp) 260.dp else 112.dp
            }
            else {
                112.dp
            }),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var searchText by rememberSaveable{ mutableStateOf("") }

        //search bar
        TextField(
            colors =  TextFieldDefaults.textFieldColors(textColor = TextWhite),
            modifier = Modifier
                .height(52.dp)
                .width(
                    width = when (windowSizeClass) { //adjust search bar width
                        SizeClass.FOURHUNDRED -> 250.dp
                        SizeClass.FIVEHUNDRED -> 250.dp
                        SizeClass.SIXHUNDRED -> 250.dp
                        SizeClass.SEVENHUNDRED -> 300.dp
                        SizeClass.EIGHTHUNDRED -> 300.dp
                        SizeClass.NINEHUNDRED -> 300.dp
                        SizeClass.ONETHOUSAND -> 350.dp
                        SizeClass.ONEONE -> 350.dp
                        SizeClass.ONETWO -> 350.dp
                        SizeClass.ONETHREE -> 400.dp
                        SizeClass.ONEFOUR -> 400.dp
                        SizeClass.ONEFIVE -> 400.dp
                        SizeClass.ONESIX -> 450.dp
                        SizeClass.ONESEVEN -> 450.dp
                        SizeClass.ONEEIGHT -> 450.dp
                        SizeClass.ONENINE -> 500.dp
                        SizeClass.TWOTHOUSAND -> 500.dp
                    }
                )
                .background(color = NavTextLightPurple, shape = RoundedCornerShape(6.dp)),
            value = searchText,
            singleLine = true,
            onValueChange = { newText ->
                searchText = newText
            },
            placeholder = {
                //search place holder
                Text(text = "Search cases and files", color = SearchBarTextLightGrey, style = MaterialTheme.typography.body1)
            },
            leadingIcon = {
                //magnifying glass search icon
                Icon(imageVector = Icons.Default.Search, contentDescription = "", tint = SearchBarTextLightGrey)
            },
            trailingIcon = {
                //search button
                Button(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 36.dp, end = 6.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = SearchButtonPurple)
                ) {
                    Text(text = "Search", color = TextWhite, style = MaterialTheme.typography.body1)
                }
            }

        )

        if (windowSizeClass == SizeClass.FOURHUNDRED ||
            windowSizeClass == SizeClass.FIVEHUNDRED ||
            windowSizeClass == SizeClass.SIXHUNDRED ||
            windowSizeClass == SizeClass.SEVENHUNDRED
        ) {} else {
            //history button
            Button(
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                onClick = {  }
            ) {
                Text(text = "History", color = TextWhite)
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "history button",
                    tint = TextWhite
                )
            }

            //user avatar
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    modifier = Modifier
                        .padding(end = 36.dp)
                        .background(
                            color = TransparentPurple,
                            shape = RoundedCornerShape(30.dp)
                        ),
                    onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user_avatar),
                        contentDescription = "",
                        tint = BackgroundWhite
                    )
                }
            }

        }


    }
}