package com.example.lawpavilion.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lawpavilion.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.lawpavilion.ui.theme.*
import com.example.lawpavilion.ui.utils.WindowSizeClass
import com.example.lawpavilion.ui.utils.customShape

@Composable
fun LawPavilionApp() {
    LawPavilionTheme {
        val drawerState = rememberDrawerState(DrawerValue.Open)
        var expanded by rememberSaveable{ mutableStateOf(false)}

        Row(
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
                .padding(start = if (!expanded) 60.dp else 200.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                onClick = { /*TODO*/ }) {
                Text(text = "History", color = TextWhite)
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "history button", tint = TextWhite)
            }

        }

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            ModalDrawer(
                drawerShape = customShape(),
                modifier = Modifier.offset(y = 48.dp),
                drawerState = drawerState,
                drawerContent = {
                    Row(Modifier.align(Alignment.End)) {
                        Text(text = "Hello", color = Color.Black)
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = if (!expanded) 60.dp else 200.dp)
                        .background(color = BodyGrey)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = "Google", color = Color.Black)
                }

            }

        }

        NavigationRail(
            modifier = if (!expanded) Modifier.width(60.dp) else Modifier.width(200.dp) ,
            backgroundColor = MaterialTheme.colors.primary,
            header = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                        .padding(start = 4.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "", tint = TextWhite)
                    }

                    Image(
                        modifier = if (!expanded) Modifier.size(0.dp) else Modifier.size(48.dp),
                        painter = painterResource(id = R.drawable.ic_law_pavilion),
                        contentDescription = "logo" )
                }

            }
        ) {
            NavigationRailItem(
                modifier = Modifier.align(Alignment.Start),
                selected = false,
                onClick = { /*TODO*/ },
                icon = {Icon(imageVector = Icons.Default.Menu, contentDescription = "", tint = Color.Black)},
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Black)

            NavigationRailItem(
                modifier = Modifier.align(Alignment.Start),
                selected = false,
                onClick = { /*TODO*/ },
                icon = {Icon(imageVector = Icons.Default.Menu, contentDescription = "", tint = Color.Black)},
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Black)

        }

    }
}