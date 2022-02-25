package com.example.lawpavilion.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lawpavilion.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lawpavilion.domain.model.Folder
import com.example.lawpavilion.data.database.entity.FolderLocal
import com.example.lawpavilion.ui.theme.*
import com.example.lawpavilion.ui.utils.FolderSaver
import com.example.lawpavilion.ui.utils.WindowSizeClass
import com.example.lawpavilion.ui.utils.customShape
import com.example.lawpavilion.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LawPavilionApp(
    windowSizeClass: WindowSizeClass,
    drawerState: DrawerState,
    railSizeIfSmallScreen: Dp,
    railSizeIfLargeScreen: Dp,
    mainActivityViewModel: MainActivityViewModel
) {
    LawPavilionTheme {
        //keep track of notification count
        var notificationCount by rememberSaveable { mutableStateOf(2) }

        //keep track of navigation rail state
        var expanded by rememberSaveable{ mutableStateOf(false)}
        //reusable coroutine scope
        val coroutineScope = rememberCoroutineScope()

        //collect folders as state, every time state changes as a result of
        //new folder(s) being added or deleted, recomposition ensues on every
        //composable that uses this folders
        val folders by mainActivityViewModel.folders.collectAsState()
        //keep track of selected folder
        var selectedFolder by rememberSaveable(stateSaver = FolderSaver) {
            mutableStateOf(Folder())
        }

        Scaffold(
            modifier = Modifier.background(color = BodyGrey),
            //floating action button
            floatingActionButton = { if (drawerState.isClosed)
                FloatingActionButton(
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 24.dp
                        )
                        .background(color = Color.Transparent, shape = RoundedCornerShape(50.dp)),
                    backgroundColor = Color.Transparent,
                    elevation =  FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        pressedElevation = 2.dp,
                        focusedElevation = 0.dp
                    ),
                    onClick = { notificationCount += 1 }
                ) {

                    Column(Modifier.padding(20.dp)) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(
                                    shape = RoundedCornerShape(50.dp),
                                    color = Orange
                                )
                                .size(65.dp)) {
                            Icon(
                                painter = painterResource(id = R.drawable.notification),
                                contentDescription = "",
                                tint = Color.White
                            )
                            Box(
                                Modifier
                                    .offset(x = -(8.dp), y = -(4.dp))
                                    .align(Alignment.TopStart)
                                    .background(
                                        shape = RoundedCornerShape(50.dp),
                                        color = Color.White.copy(alpha = 0.3f)
                                    )
                                    .size(32.dp)
                            ) {

                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .background(
                                            shape = RoundedCornerShape(50.dp),
                                            color = Color.White
                                        )
                                        .size(24.dp)
                                ){
                                    Text(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = "$notificationCount",
                                        color = Orange)
                                }
                            }
                        }
                    }

        } else {}
            }) {

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
                var searchText by rememberSaveable{ mutableStateOf("")}

                //search bar
                TextField(
                    colors =  TextFieldDefaults.textFieldColors(textColor = TextWhite),
                    modifier = Modifier
                        .height(52.dp)
                        .width(
                            width = when (windowSizeClass) { //adjust search bar width
                                WindowSizeClass.COMPACT -> 250.dp
                                WindowSizeClass.MEDIUMLAND -> 350.dp //done
                                WindowSizeClass.MEDIUMPORTRAIT -> 250.dp //done
                                WindowSizeClass.EXPANDEDLAND -> 350.dp //done
                                WindowSizeClass.EXPANDEDPORTRAIT -> 350.dp //done
                                WindowSizeClass.EXTRALAND -> 450.dp
                                WindowSizeClass.EXTRAPORTRAIT -> 450.dp
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
                        WindowSizeClass.COMPACT -> 600f
                        WindowSizeClass.MEDIUMLAND -> 550f //done
                        WindowSizeClass.MEDIUMPORTRAIT -> 200f //done
                        WindowSizeClass.EXPANDEDLAND -> 1500f //done
                        WindowSizeClass.EXPANDEDPORTRAIT -> 850f //done
                        WindowSizeClass.EXTRALAND -> 1750f //done
                        WindowSizeClass.EXTRAPORTRAIT -> 720f
                    }),
                    modifier = Modifier
                        .offset(y = 76.dp)
                        .padding(bottom = 76.dp),
                    drawerState = drawerState,
                    drawerContent = {

                        //drawer content
                        Column(modifier = when (windowSizeClass) {
                            WindowSizeClass.COMPACT -> Modifier.offset((0.dp))
                            WindowSizeClass.MEDIUMLAND -> Modifier.offset((0.dp)) //done
                            WindowSizeClass.MEDIUMPORTRAIT -> Modifier.offset((0.dp)) //done
                            WindowSizeClass.EXPANDEDLAND -> Modifier.offset((0.dp)) //done
                            WindowSizeClass.EXPANDEDPORTRAIT -> Modifier.offset((0.dp)) //done
                            WindowSizeClass.EXTRALAND -> Modifier.offset((0.dp))
                            WindowSizeClass.EXTRAPORTRAIT -> Modifier.offset((0.dp))

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

                                    Row(modifier = Modifier
                                        .width(
                                            width = when (windowSizeClass) {
                                                WindowSizeClass.COMPACT -> 370.dp
                                                WindowSizeClass.MEDIUMLAND -> 390.dp //done
                                                WindowSizeClass.MEDIUMPORTRAIT -> 320.dp //done
                                                WindowSizeClass.EXPANDEDLAND -> 460.dp //done
                                                WindowSizeClass.EXPANDEDPORTRAIT -> 400.dp //done
                                                WindowSizeClass.EXTRALAND -> 460.dp
                                                WindowSizeClass.EXTRAPORTRAIT -> 390.dp
                                            }
                                        )
                                        .padding(
                                            end = when (windowSizeClass) {
                                                WindowSizeClass.COMPACT -> 1.dp
                                                WindowSizeClass.MEDIUMLAND -> 8.dp //done
                                                WindowSizeClass.MEDIUMPORTRAIT -> 8.dp
                                                WindowSizeClass.EXPANDEDLAND -> 72.dp //done
                                                WindowSizeClass.EXPANDEDPORTRAIT -> 65.dp //done
                                                WindowSizeClass.EXTRALAND -> 72.dp
                                                WindowSizeClass.EXTRAPORTRAIT -> 8.dp
                                            }, top = 12.dp, bottom = 16.dp, start = 20.dp
                                        ),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        //close button
                                        Button(
                                            onClick = { coroutineScope.launch { drawerState.close() } },
                                            colors = ButtonDefaults.buttonColors(backgroundColor = ButtonRed, contentColor = TextWhite)
                                        ) {
                                            Text(text = "Close", style = MaterialTheme.typography.h3)
                                        }

                                        //case title
                                        Column(
                                            modifier = when (windowSizeClass) {
                                                WindowSizeClass.COMPACT -> Modifier
                                                WindowSizeClass.MEDIUMLAND -> Modifier.offset(x = 12.dp) //done
                                                WindowSizeClass.MEDIUMPORTRAIT -> Modifier.offset(x = 12.dp) //done
                                                WindowSizeClass.EXPANDEDLAND -> Modifier.offset(x = 60.dp) //done
                                                WindowSizeClass.EXPANDEDPORTRAIT -> Modifier.offset(x = 60.dp) //done
                                                WindowSizeClass.EXTRALAND -> Modifier.offset(x = 60.dp) //done
                                                WindowSizeClass.EXTRAPORTRAIT -> Modifier.offset(x = 12.dp)
                                            },
                                            horizontalAlignment = Alignment.End
                                        ) {
                                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr){
                                                Text(
                                                    modifier = Modifier,
                                                    text = selectedFolder.title?.uppercase() ?: "",
                                                    color = TextWhite,
                                                    style = MaterialTheme.typography.h2,
                                                )
                                                Text(
                                                    modifier = Modifier,
                                                    text = if (selectedFolder.code == null) "" else "(2005, CA)".uppercase(),
                                                    color = TextWhite,
                                                    style = MaterialTheme.typography.h2
                                                )
                                            }
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
                                            .verticalScroll(
                                                state = rememberScrollState(),
                                                enabled = true
                                            )
                                            .width(
                                                width = when (windowSizeClass) {
                                                    WindowSizeClass.COMPACT -> 410.dp
                                                    WindowSizeClass.MEDIUMLAND -> 400.dp
                                                    WindowSizeClass.MEDIUMPORTRAIT -> 330.dp //done
                                                    WindowSizeClass.EXPANDEDLAND -> 457.dp //done
                                                    WindowSizeClass.EXPANDEDPORTRAIT -> 400.dp //done
                                                    WindowSizeClass.EXTRALAND -> 457.dp
                                                    WindowSizeClass.EXTRAPORTRAIT -> 400.dp
                                                }
                                            )
                                            .padding(
                                                start = when (windowSizeClass) {
                                                    WindowSizeClass.COMPACT -> 42.dp
                                                    WindowSizeClass.MEDIUMLAND -> 16.dp
                                                    WindowSizeClass.MEDIUMPORTRAIT -> 16.dp
                                                    WindowSizeClass.EXPANDEDLAND -> 20.dp
                                                    WindowSizeClass.EXPANDEDPORTRAIT -> 20.dp
                                                    WindowSizeClass.EXTRALAND -> 20.dp
                                                    WindowSizeClass.EXTRAPORTRAIT -> 16.dp
                                                }, top = 32.dp, bottom = 32.dp, end = 8.dp
                                            ),
                                        horizontalAlignment = Alignment.End
                                    ) {


                                        //case code
                                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                            //header
                                            Text(
                                                text = "Principle",
                                                style = MaterialTheme.typography.h3,
                                                color = BackgroundNavyBlue
                                            )

                                            Spacer(Modifier.height(16.dp))

                                            TextButton(
                                                onClick = { /*TODO*/ },
                                                modifier = Modifier
                                                    .background(
                                                        color = TransparentGreen,
                                                        shape = RoundedCornerShape(6.dp)
                                                    )
                                                    .padding(0.dp)
                                            ) {
                                                Text(
                                                    text = selectedFolder.code ?: "",
                                                    style = MaterialTheme.typography.h5,
                                                    color = TextGreen,
                                                    fontSize = 14.sp
                                                )
                                            }
                                        }

                                        Spacer(Modifier.height(16.dp))

                                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                            //body
                                            Text(
                                                lineHeight = 20.sp,
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
                                                        append("${selectedFolder.type} - ")
                                                    }

                                                    //topic
                                                    withStyle(
                                                        style = SpanStyle(
                                                            color = DarkRed,
                                                            fontSize = 14.sp,
                                                            fontWeight = FontWeight.ExtraBold
                                                        )
                                                    ) {
                                                        append("${selectedFolder.topic} - ".uppercase())
                                                    }

                                                    //intro
                                                    withStyle(
                                                        style = SpanStyle(
                                                            color = DarkTextGreen,
                                                            fontWeight = FontWeight.Bold)
                                                    ) {
                                                        append(
                                                            "${selectedFolder.intro}"
                                                        )
                                                    }

                                                    //body
                                                    append("${selectedFolder.text}")

                                                    withStyle(
                                                        style = SpanStyle(
                                                            fontWeight = FontWeight.Bold,
                                                            color = BackgroundNavyBlue)
                                                    ) {
                                                        append("${selectedFolder.extra}")
                                                    }
                                                }
                                            )
                                        }

                                        Spacer(Modifier.height(20.dp))

                                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

                                            //track selected button
                                            var selectedButton by rememberSaveable{ mutableStateOf("summary")}

                                            Row(
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .align(Alignment.CenterHorizontally)
                                            ) {

                                                //view summary
                                                OutlinedButton(
                                                    modifier = Modifier.fillMaxWidth(0.5f),
                                                    border = if (selectedButton == "summary")
                                                        BorderStroke(width = 1.dp, color = OutlineGrey) else
                                                        BorderStroke(width = 0.5.dp, color = OutlineGrey),
                                                    colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = BackgroundWhite,
                                                        contentColor = BackgroundNavyBlue

                                                    ), onClick = { selectedButton = "summary" }) {

                                                    Text(
                                                        modifier = Modifier.padding(6.dp),
                                                        text = "View Summary",
                                                        fontWeight = FontWeight.Bold
                                                    )

                                                }

                                                Spacer(modifier = Modifier.width(24.dp))

                                                //read full judgement
                                                OutlinedButton(
                                                    modifier = Modifier.fillMaxWidth(1f),
                                                    border = if (selectedButton == "full")
                                                        BorderStroke(width = 1.dp, color = OutlineGrey) else
                                                        BorderStroke(width = 1.dp, color = Orange),
                                                    colors = ButtonDefaults.buttonColors(
                                                        backgroundColor = Orange,
                                                        contentColor = TextWhite

                                                    ), onClick = { selectedButton = "full" }) {

                                                    Text(
                                                        modifier = Modifier.padding(6.dp),
                                                        text = when (windowSizeClass) {
                                                            WindowSizeClass.COMPACT -> "Read Full Judgement"
                                                            WindowSizeClass.MEDIUMLAND -> "Read Full"
                                                            WindowSizeClass.MEDIUMPORTRAIT -> "Read Full"
                                                            WindowSizeClass.EXPANDEDLAND -> "Read Full Judgement"
                                                            WindowSizeClass.EXPANDEDPORTRAIT -> "Read Full"
                                                            WindowSizeClass.EXTRALAND -> "Read Full Judgement"
                                                            WindowSizeClass.EXTRAPORTRAIT -> "Read Full Judgement"
                                                        },
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }

                                    }
                                }
                            }

                        }

                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 28.dp, top = 32.dp)
                            .padding(
                                end = if (expanded && railSizeIfSmallScreen == 230.dp) {
                                    if (railSizeIfLargeScreen == 230.dp) 230.dp else 76.dp
                                }
                                else {
                                    76.dp
                                }
                            )
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        //remember selected court
                        var selectedCourt by rememberSaveable{ mutableStateOf("Supreme Court")}

                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                            //heading
                            Text(
                                text = "Latest Judgements Overview",
                                color = HeaderTextGrey,
                                style = MaterialTheme.typography.h4
                            )

                            Row(Modifier.padding(top = 32.dp, bottom = 16.dp)) {
                                //supreme court text button
                                TextButton(
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = if (selectedCourt == "Supreme Court")
                                            BackgroundWhite else BodyGrey),
                                    onClick = { selectedCourt = "Supreme Court" },
                                    border = if (selectedCourt == "Supreme Court")
                                        BorderStroke(1.dp, BodyTextGrey)
                                    else
                                        null
                                ) {
                                    Text(
                                        text = "Supreme Court",
                                        color = BodyTextGrey,
                                        style = MaterialTheme.typography.h5)
                                }

                                Spacer(modifier = Modifier.width(16.dp))

                                //court of appeal text button
                                TextButton(
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = if (selectedCourt == "Court of Appeal")
                                            BackgroundWhite else BodyGrey),
                                    onClick = { selectedCourt = "Court of Appeal" },
                                    border = if (selectedCourt == "Court of Appeal")
                                        BorderStroke(1.dp, BodyTextGrey)
                                    else
                                        null
                                ) {
                                    Text(
                                        text = "Court of Appeal",
                                        color = BodyTextGrey,
                                        style = MaterialTheme.typography.h5)
                                }

                            }
                        }

                        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

                            //folders
                            LazyVerticalGrid(
                                cells = GridCells.Adaptive(minSize = 230.dp),
                                contentPadding = PaddingValues(top = 16.dp)
                            ) {
                                items(folders) { folder ->

                                    Box(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .width(230.dp)
                                            .padding(end = 16.dp, top = 8.dp, bottom = 16.dp),
                                        contentAlignment = Alignment.CenterStart

                                    ) {
                                        Image(
                                            painter = painterResource(
                                                id = if (selectedFolder == folder) R.drawable.ic_folder_orange
                                                else
                                                    R.drawable.ic_case_folder
                                            ),
                                            contentDescription = "folder"
                                        )

                                        Column(
                                            modifier = Modifier
                                                .background(
                                                    color = Color.Transparent,
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                .clickable(
                                                    //disable ripple effect
                                                    interactionSource = remember { MutableInteractionSource() },
                                                    indication = null
                                                ) {

                                                    //set selected folder
                                                    selectedFolder = folder

                                                    coroutineScope.launch {
                                                        if (drawerState.isClosed)
                                                            drawerState.open()
                                                        else
                                                            drawerState.close()
                                                    }
                                                }
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.Start
                                        ) {

                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {

                                                //date
                                                folder.date?.let {
                                                    Text(
                                                        text = it,
                                                        fontSize = 10.sp
                                                    )
                                                }

                                                //pavilion logo
                                                Image(
                                                    modifier = Modifier.requiredSize(32.dp),
                                                    painter = painterResource(
                                                        id = R.drawable.ic_pavilion_logo
                                                    ),
                                                    contentDescription = "pavilion logo"
                                                )

                                            }

                                            Spacer(modifier = Modifier.height(12.dp))

                                            //title
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = buildAnnotatedString {
                                                    append(folder.title?.split("v.")?.get(0) ?: "")
                                                    append("v.")
                                                    withStyle(
                                                        style = SpanStyle(
                                                            fontSize = 16.sp,
                                                            fontWeight = FontWeight.ExtraBold
                                                        )
                                                    ) {
                                                        append(folder.title?.split("v.")?.get(1) ?: "")
                                                    }
                                                },
                                                textAlign = TextAlign.Start,
                                            )

                                            Spacer(modifier = Modifier.height(24.dp))

                                            //code
                                            Text(
                                                text = folder.code ?: "",
                                                textAlign = TextAlign.Start,
                                                fontSize = 12.sp
                                            )
                                        }
                                    }


                                }
                            }
                        }


                    }

                }

            }

            NavigationRail(
                modifier = Modifier.width(width = if (!expanded) 76.dp else railSizeIfSmallScreen),
                backgroundColor = MaterialTheme.colors.primary,

                //navigation rail header
                header = {
                    //show or hide drawer icon depending on screen size
                    when (windowSizeClass) {
                        WindowSizeClass.COMPACT -> { Box(modifier = Modifier.size(60.dp)) }
                        WindowSizeClass.MEDIUMLAND -> NavRailHeader(expanded) { expanded = !expanded }
                        WindowSizeClass.MEDIUMPORTRAIT -> { Box(modifier = Modifier.size(60.dp)) }
                        WindowSizeClass.EXPANDEDLAND -> NavRailHeader(expanded) { expanded = !expanded }
                        WindowSizeClass.EXPANDEDPORTRAIT -> NavRailHeader(expanded) { expanded = !expanded }
                        WindowSizeClass.EXTRAPORTRAIT -> NavRailHeader(expanded) { expanded = !expanded }
                        WindowSizeClass.EXTRALAND -> NavRailHeader(expanded) { expanded = !expanded }
                    }

                }
            ) {

                //keep track of selected rail item
                var selectedRailId by rememberSaveable{ mutableStateOf(2) }

                //rail items
                for(railItemId in 0..9) {
                    RailItem(
                        railSize = railSizeIfSmallScreen,
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
}

@Composable
fun Folder(folder: FolderLocal) {
    TODO("Not yet implemented")
}

@Composable
fun RailItem(
    modifier: Modifier = Modifier,
    railSize: Dp,
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
            text = if (drawerExpanded && railSize == 230.dp) itemTitle else "",
            style = MaterialTheme.typography.body1,
            color = if (isSelected) NavTextHighLightTeal else MaterialTheme.colors.onPrimary  )
    }
}

@Composable
fun NavRailHeader(expanded: Boolean, toggleExpandedState: () -> Unit) {
    Column( Modifier.fillMaxWidth()) {
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
                onClick = { toggleExpandedState.invoke() }) {
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

}