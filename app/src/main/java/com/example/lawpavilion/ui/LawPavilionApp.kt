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
import com.example.lawpavilion.ui.composables.*
import com.example.lawpavilion.ui.theme.*
import com.example.lawpavilion.ui.utils.FolderSaver
import com.example.lawpavilion.ui.utils.SizeClass
import com.example.lawpavilion.ui.utils.customShape
import com.example.lawpavilion.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LawPavilionApp(
    windowSizeClass: SizeClass,
    drawerState: DrawerState,
    railSizeIfSmallScreen: Dp,
    railSizeIfLargeScreen: Dp,
    mainActivityViewModel: MainActivityViewModel
) {
    LawPavilionTheme {
        //reusable coroutine scope
        val coroutineScope = rememberCoroutineScope()

        //keep track of notification count
        var notificationCount by rememberSaveable { mutableStateOf(2) }

        //keep track of navigation rail state
        var expanded by rememberSaveable{ mutableStateOf(false)}

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
                    //custom notification icon
                    NotificationIcon(notificationCount)

        } else {}
            }
        ) {

            TopBar(
                expanded = expanded,
                railSizeIfSmallScreen = railSizeIfSmallScreen,
                railSizeIfLargeScreen = railSizeIfLargeScreen,
                windowSizeClass = windowSizeClass
            )

            //alter layout direction, condigure modal drawer to appear on the right
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                ModalDrawer(
                    drawerShape = customShape(left = when (windowSizeClass) {
                        SizeClass.FOURHUNDRED -> 250f
                        SizeClass.FIVEHUNDRED -> 100f
                        SizeClass.SIXHUNDRED -> 240f
                        SizeClass.SEVENHUNDRED -> 550f
                        SizeClass.EIGHTHUNDRED -> 375f
                        SizeClass.NINEHUNDRED -> 925f
                        SizeClass.ONETHOUSAND -> 475f
                        SizeClass.ONEONE -> 600f
                        SizeClass.ONETWO -> 700f
                        SizeClass.ONETHREE -> 750f
                        SizeClass.ONEFOUR -> 1200f
                        SizeClass.ONEFIVE -> 1250f
                        SizeClass.ONESIX -> 1300f
                        SizeClass.ONESEVEN -> 1350f
                        SizeClass.ONEEIGHT -> 1400f
                        SizeClass.ONENINE -> 1400f
                        SizeClass.TWOTHOUSAND -> 1400f
                    }),
                    modifier = Modifier
                        .offset(y = 76.dp)
                        .padding(bottom = 76.dp),
                    drawerState = drawerState,
                    drawerContent = {

                         //drawer content
                         DrawerContent(
                             windowSizeClass = windowSizeClass,
                             mainActivityViewModel = mainActivityViewModel,
                             drawerState = drawerState,
                             folders = folders,
                             selectedFolder = selectedFolder,
                             coroutineScope = coroutineScope
                         )
                    }
                ) {
                    //folders
                    Body(
                        expanded = expanded,
                        railSizeIfSmallScreen = railSizeIfSmallScreen,
                        railSizeIfLargeScreen = railSizeIfLargeScreen,
                        windowSizeClass = windowSizeClass,
                        folders = folders,
                        selectedFolder = selectedFolder,
                        onSelectFolder = { folder ->
                            selectedFolder = folder
                            coroutineScope.launch {
                                if (drawerState.isClosed)
                                    drawerState.open()
                                else
                                    drawerState.close()
                            }

                        }
                    )

                }

            }

            NavigationRail(
                modifier = Modifier.width(width = if (!expanded) 76.dp else railSizeIfSmallScreen),
                backgroundColor = MaterialTheme.colors.primary,

                //navigation rail header
                header = {
                    //show or hide drawer icon depending on screen size
                    if (windowSizeClass ==  SizeClass.FOURHUNDRED ||
                        windowSizeClass ==  SizeClass.FIVEHUNDRED ||
                        windowSizeClass ==  SizeClass.SIXHUNDRED
                            ) {
                                Box(modifier = Modifier.size(60.dp)) }
                    else
                        NavRailHeader(expanded) { expanded = !expanded }
                }
            ) {

                //keep track of selected rail item
                var selectedRailId by rememberSaveable{ mutableStateOf(2) }

                //rail items
                for(railItemId in 0..9) {
                    NavRailItem(
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