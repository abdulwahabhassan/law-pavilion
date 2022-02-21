package com.example.lawpavilion.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lawpavilion.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.lawpavilion.ui.theme.*
import com.example.lawpavilion.ui.utils.WindowSizeClass
import com.example.lawpavilion.ui.utils.customShape

@Composable
fun LawPavilionApp(windowSizeClass: WindowSizeClass) {
    LawPavilionTheme {
        val drawerState = rememberDrawerState(DrawerValue.Open)
        var expanded by rememberSaveable{ mutableStateOf(false)}

        Row(
            Modifier
                .height(76.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
                .padding(start = if (!expanded) 112.dp else 260.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var searchText by rememberSaveable{ mutableStateOf("")}

            //search bar
            TextField(
                modifier = Modifier
                    .height(52.dp)
                    .width(350.dp)
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

            //history button
            Button(
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                onClick = {}
            ) {
                Text(text = "History", color = TextWhite)
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "history button",
                    tint = TextWhite)
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
                        tint = BackgroundWhite)
                }
            }

        }

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            ModalDrawer(
                drawerShape = customShape(left = when (windowSizeClass) {
                    WindowSizeClass.COMPACT -> 900f
                    WindowSizeClass.MEDIUM -> 1500f
                    WindowSizeClass.EXPANDED -> 2100f
                }),
                modifier = Modifier.offset(y = 76.dp),
                drawerState = drawerState,
                drawerContent = {
                    Row(Modifier.align(Alignment.End)) {
                        Text(text = "Hello", color = Color.Black)
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(end = if (!expanded) 76.dp else 230.dp)
                        .background(color = BodyGrey)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = "Google", color = Color.Black)
                }

            }

        }

        NavigationRail(
            modifier = if (!expanded) Modifier.width(76.dp) else Modifier.width(230.dp) ,
            backgroundColor = MaterialTheme.colors.primary,
            header = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .align(Alignment.Start)
                        .padding(start = 8.dp, end = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    //drawer icon button
                    IconButton(
                        onClick = { expanded = !expanded }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "", tint = TextWhite)
                    }

                    //law pavilion prime logo
                    Image(
                        painter = painterResource(id = R.drawable.ic_law_pavilion),
                        contentDescription = "logo",
                        alpha = if (expanded) 1f else 0f
                    )
                }

            }
        ) {

            //keep track of selected rail item
            var selectedRailId by rememberSaveable{ mutableStateOf(2) }

            //rail items
            for(railItemId in 0..9) {
                RailItem(
                    itemId = railItemId,
                    isSelected = selectedRailId == railItemId, //toggle isSelected if ids match
                    onClicked = { selectedRailId = railItemId }, //update selected rail id
                    drawerExpanded = expanded, //observe drawer state
                    modifier = Modifier
                        .align(Alignment.Start)
                        .background(
                            color = if (selectedRailId == railItemId) MaterialTheme.colors.surface
                            else Color.Transparent,
                            shape = RectangleShape
                        )
                        .padding(start = 2.dp),
                    drawableId = when (railItemId) {
                        0 -> R.drawable.ic_dashboard
                        1 -> R.drawable.ic_latest_judgements
                        2 -> R.drawable.ic_briefcase
                        3 -> R.drawable.ic_book_open
                        4 -> R.drawable.ic_cp_rules
                        5 -> R.drawable.ic_inbox
                        6 -> R.drawable.ic_textbooks_journals
                        7 -> R.drawable.ic_folder
                        8 -> R.drawable.ic_award
                        9 -> R.drawable.ic_new_contents
                        else -> R.drawable.ic_new_contents
                                                   },
                    itemTitle = when (railItemId) {
                        0 -> "Dashboard"
                        1 -> "Latest Judgements"
                        2 -> "Law Reports"
                        3 -> "Laws of Nigeria"
                        4 -> "Civil Procedure Rules"
                        5 -> "Index & Digest"
                        6 -> "TextBooks & Journals"
                        7 -> "My Research Folder"
                        8 -> "Words in Gold"
                        9 -> "New Contents"
                        else -> ""
                    }
                )
                }
            }
        }
    }

@Composable
fun RailItem(
    modifier: Modifier = Modifier,
    itemId: Int, drawableId: Int,
    isSelected: Boolean = false,
    itemTitle: String,
    drawerExpanded: Boolean,
    onClicked: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .clickable { onClicked.invoke() }, //make row clickable
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationRailItem(
            modifier = modifier,
            selected = isSelected,
            onClick = onClicked,
            icon = {
                Icon(
                    painter = painterResource(id = drawableId),
                    contentDescription = ""
                )
                   },
            selectedContentColor = NavTextHighLightTeal,
            unselectedContentColor = MaterialTheme.colors.onPrimary
        )

        //if drawer state is expanded, show item title else empty string
        Text(
            text = if (drawerExpanded) itemTitle else "",
            style = MaterialTheme.typography.body1,
            color = if (isSelected) NavTextHighLightTeal else MaterialTheme.colors.onPrimary  )
    }
}