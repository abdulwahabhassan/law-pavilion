package com.example.lawpavilion.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        //alter layout direction
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

                    //drawer content
                    Column(modifier = when (windowSizeClass) {
                        WindowSizeClass.COMPACT -> Modifier.offset(-(25.dp))
                        WindowSizeClass.MEDIUM -> Modifier.offset((0.dp))
                        WindowSizeClass.EXPANDED -> Modifier.offset((25.dp))
                    }) {
                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                            //case details view header
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colors.primary),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start

                                ) {

                                //close buttton
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(start = when (windowSizeClass) {
                                        WindowSizeClass.COMPACT -> 42.dp
                                        WindowSizeClass.MEDIUM -> 18.dp
                                        WindowSizeClass.EXPANDED -> 5.dp
                                    }, top = 16.dp, bottom = 16.dp, end = 42.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonRed, contentColor = TextWhite)
                                ) {
                                    Text(text = "Close", style = MaterialTheme.typography.h3)
                                }
                                //cas title
                                Column(
                                    modifier = when (windowSizeClass) {
                                        WindowSizeClass.COMPACT -> Modifier
                                        WindowSizeClass.MEDIUM -> Modifier.offset(x = 68.dp)
                                        WindowSizeClass.EXPANDED -> Modifier.offset(x = 124.dp)
                                    },
                                    horizontalAlignment = Alignment.End
                                ) {
                                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr){
                                        Text(
                                            modifier = Modifier,
                                            text = "ESSSEN & NOR V. MOHAMMED",
                                            color = TextWhite,
                                            style = MaterialTheme.typography.h2,
                                        )
                                        Text(
                                            modifier = Modifier,
                                            text = "(2005, CA)",
                                            color = TextWhite,
                                            style = MaterialTheme.typography.h2
                                        )
                                    }

                                }

                            }

                            //case details body
                            Column(
                                Modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Column(
                                    modifier = Modifier
                                        .width(width = when (windowSizeClass) {
                                            WindowSizeClass.COMPACT -> 410.dp
                                            WindowSizeClass.MEDIUM -> 460.dp
                                            WindowSizeClass.EXPANDED -> 500.dp
                                        })
                                        .padding(
                                            start = when (windowSizeClass) {
                                                WindowSizeClass.COMPACT -> 42.dp
                                                WindowSizeClass.MEDIUM -> 16.dp
                                                WindowSizeClass.EXPANDED -> 8.dp
                                            }, top = 32.dp, bottom = 32.dp, end = 8.dp
                                        ),
                                    horizontalAlignment = Alignment.End
                                ) {
                                    //header
                                    Text(
                                        text = "Principle",
                                        style = MaterialTheme.typography.h3,
                                        color = BackgroundNavyBlue
                                    )

                                    Spacer(Modifier.height(16.dp))

                                    //case code
                                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                        TextButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier.background(
                                            color = TransparentGreen,
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        ) {
                                            Text(
                                                textAlign = TextAlign.End,
                                                text = "(2020) LPELR-50514(CA)",
                                                style = MaterialTheme.typography.h5,
                                                color = TextGreen,
                                            )
                                        }
                                    }

                                    Spacer(Modifier.height(16.dp))

                                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                        //body
                                        Text(
                                            lineHeight = 20.sp,
                                            textAlign = TextAlign.Start,
                                            style = MaterialTheme.typography.body1,
                                            color = BackgroundNavyBlue,
                                            text = buildAnnotatedString {
                                                //type
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = BackgroundNavyBlue,
                                                        fontSize = 14.sp,
                                                        fontWeight = FontWeight.ExtraBold
                                                    )
                                                ) {
                                                    append("APPEAL - ")
                                                }

                                                //title
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = DarkRed,
                                                        fontSize = 14.sp,
                                                        fontWeight = FontWeight.ExtraBold
                                                    )
                                                ) {
                                                    append("FORMULATION OF ISSUE(S) FOR DETERMINATION - ")
                                                }

                                                //intro
                                                withStyle(
                                                    style = SpanStyle(
                                                        color = DarkTextGreen,
                                                        fontWeight = FontWeight.Bold)
                                                ) {
                                                    append(
                                                        "Whether Court can suo motu formulate/re-formulate" +
                                                                "issue(s) arising from ground(s) of appeal for " +
                                                                "determination of an appeal\n "
                                                    )
                                                }

                                                //body
                                                append("\"In Chabasaya vs. Anwasi (2010) 10 NWLR (1201) " +
                                                        "163 it was held by the Supreme Court " +
                                                        "that: - \"... The law permits an appellate " +
                                                        "Court to ignore some or all issues in the " +
                                                        "briefs of argument and formulate its own " +
                                                        "issues, the way it deems them to be material " +
                                                        "once they are distilled from the grounds of " +
                                                        "appeal.\" In the earlier case of Sha vs. " +
                                                        "Kwan (2000) 8 NWLR (670) 685 @ 700, the apex " +
                                                        "Court had stated that:- \"The Court of Appeal " +
                                                        "is at liberty and possess the jurisdiction to " +
                                                        "modify or reject all or any of the issues " +
                                                        "formulated by the parties and frame its own " +
                                                        "issues or, to reframe the issues by the " +
                                                        "parties if, in its views, such issues will " +
                                                        "not lead to proper determination of the " +
                                                        "appeal.\" \"The Court of Appeal is at liberty " +
                                                        "and possess such issues will not lead to " +
                                                        "proper determination of the appeal.\"See also " +
                                                        "Onochie vs. Odogwu (2006) 6 NWLR (975) 65, " +
                                                        "Governor Ekiti State vs. Olubunmo (2017) 3 " +
                                                        "NWLR (1551) 1 @ 23.\"")

                                                withStyle(
                                                    style = SpanStyle(
                                                        fontWeight = FontWeight.Bold,
                                                        color = BackgroundNavyBlue)
                                                ) {
                                                    append("Per GARBA ,J.C.A ( Pp. 4-5, paras. F-D )")
                                                }
                                            }
                                        )
                                    }

                                }
                            }


                    }

                    }
//                    //alter layout direction
//                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
//
//                    }

                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(28.dp)
                        .padding(end = if (!expanded) 76.dp else 230.dp)
                        .background(color = BodyGrey)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    //remember selected court
                    var selectedCourt by rememberSaveable{ mutableStateOf("Supreme Court")}

                    //heading
                    Text(
                        text = "Latest Judgements Overview",
                        color = HeaderTextGrey,
                        style = MaterialTheme.typography.h4
                    )

                    Row(Modifier.padding(top = 32.dp)) {
                        //supreme court text button
                        TextButton(
                            onClick = { selectedCourt = "Supreme Court" },
                            border = if (selectedCourt == "Supreme Court")
                                BorderStroke(1.dp, BodyTextGrey)
                            else
                                null
                        ) {
                            Text(text = "Supreme Court",
                                color = BodyTextGrey,
                                style = MaterialTheme.typography.h5)
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        //court of appeal text button
                        TextButton(
                            onClick = { selectedCourt = "Court of Appeal" },
                            border = if (selectedCourt == "Court of Appeal")
                                BorderStroke(1.dp, BodyTextGrey)
                            else
                                null
                        ) {
                            Text(text = "Court of Appeal",
                                color = BodyTextGrey,
                                style = MaterialTheme.typography.h5)
                        }

                    }
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