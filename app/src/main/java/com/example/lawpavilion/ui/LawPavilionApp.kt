package com.example.lawpavilion.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lawpavilion.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lawpavilion.ui.theme.BackgroundWhite
import com.example.lawpavilion.ui.theme.LawPavilionTheme
import com.example.lawpavilion.ui.theme.TextWhite
import com.example.lawpavilion.ui.utils.WindowSizeClass

@Composable
fun LawPavilionApp() {
    LawPavilionTheme {
        val drawerState = rememberDrawerState(DrawerValue.Open)
        var expanded by rememberSaveable{ mutableStateOf(false)}

        NavigationRail(
            modifier = if (!expanded) Modifier.width(60.dp) else Modifier.width(120.dp) ,
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
                        modifier = Modifier.size(24.dp),
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

//        ModalDrawer(
//            modifier = Modifier,
//            drawerState = drawerState,
//            drawerContent = {
//                AppDrawer(
//                    closeDrawer = { /*TODO*/ },
//                    currentRoute = "",
//                    navigateToDashBoard = { /*TODO*/ },
//                    navigateToLatestJudgements = { /*TODO*/ },
//                    navigateToLawReports = { /*TODO*/ },
//                    navigateToLawsOfNigeria = { /*TODO*/ },
//                    navigateToCPRules = { /*TODO*/ },
//                    navigateToIndexAndDigest = { /*TODO*/ },
//                    navigateToTextBooksAndJournal = { /*TODO*/ },
//                    navigateToResearchFolder = { /*TODO*/ },
//                    navigateToWordsInGold = { /*TODO*/ }) {
//
//                }
//            }
//        ) {
//
//
//
//        }

    }
}