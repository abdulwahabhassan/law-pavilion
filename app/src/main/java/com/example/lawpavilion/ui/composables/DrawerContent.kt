package com.example.lawpavilion.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lawpavilion.domain.model.Folder
import com.example.lawpavilion.ui.theme.*
import com.example.lawpavilion.ui.utils.FolderSaver
import com.example.lawpavilion.ui.utils.SizeClass
import com.example.lawpavilion.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    windowSizeClass: SizeClass,
    mainActivityViewModel: MainActivityViewModel,
    drawerState: DrawerState,
    folders: List<Folder>,
    selectedFolder: Folder,
    coroutineScope: CoroutineScope
) {


    Column(modifier = Modifier.offset((0.dp))) {
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
                            SizeClass.FOURHUNDRED -> 300.dp
                            SizeClass.FIVEHUNDRED -> 300.dp
                            SizeClass.SIXHUNDRED -> 300.dp
                            SizeClass.SEVENHUNDRED -> 350.dp
                            SizeClass.EIGHTHUNDRED -> 350.dp
                            SizeClass.NINEHUNDRED -> 350.dp
                            SizeClass.ONETHOUSAND -> 350.dp
                            SizeClass.ONEONE -> 350.dp
                            SizeClass.ONETWO -> 400.dp
                            SizeClass.ONETHREE -> 475.dp
                            SizeClass.ONEFOUR -> 450.dp
                            SizeClass.ONEFIVE -> 450.dp
                            SizeClass.ONESIX -> 700.dp
                            SizeClass.ONESEVEN -> 700.dp
                            SizeClass.ONEEIGHT -> 700.dp
                            SizeClass.ONENINE -> 700.dp
                            SizeClass.TWOTHOUSAND -> 700.dp
                        }
                    )
                    .padding(
                        end = 16.dp, top = 12.dp, bottom = 16.dp, start = 24.dp
                    ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //close button
                    Button(
                        onClick = { coroutineScope.launch { drawerState.close() } },
                        colors = ButtonDefaults.buttonColors(backgroundColor = ButtonRed, contentColor = TextWhite)
                    ) {
                        Text(
                            text = "Close",
                            style = MaterialTheme.typography.h3,
                            fontSize = if (windowSizeClass == SizeClass.FOURHUNDRED ||
                                windowSizeClass == SizeClass.FIVEHUNDRED ||
                                windowSizeClass == SizeClass.SIXHUNDRED ||
                                windowSizeClass == SizeClass.SEVENHUNDRED ||
                                windowSizeClass == SizeClass.EIGHTHUNDRED
                            ) 12.sp else 14.sp
                        )
                    }

                    //case title
                    Column(
                        modifier = Modifier.offset(x = 0.dp),
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

                                SizeClass.FOURHUNDRED -> 300.dp
                                SizeClass.FIVEHUNDRED -> 300.dp
                                SizeClass.SIXHUNDRED -> 300.dp
                                SizeClass.SEVENHUNDRED -> 350.dp
                                SizeClass.EIGHTHUNDRED -> 350.dp
                                SizeClass.NINEHUNDRED -> 350.dp
                                SizeClass.ONETHOUSAND -> 350.dp
                                SizeClass.ONEONE -> 350.dp
                                SizeClass.ONETWO -> 400.dp
                                SizeClass.ONETHREE -> 475.dp
                                SizeClass.ONEFOUR -> 450.dp
                                SizeClass.ONEFIVE -> 450.dp
                                SizeClass.ONESIX -> 700.dp
                                SizeClass.ONESEVEN -> 700.dp
                                SizeClass.ONEEIGHT -> 700.dp
                                SizeClass.ONENINE -> 700.dp
                                SizeClass.TWOTHOUSAND -> 700.dp
                            }
                        )
                        .padding(
                            start = 24.dp,
                            top = 32.dp,
                            bottom = 32.dp,
                            end = 16.dp
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
                                        color = BackgroundNavyBlue
                                    )
                                ) {
                                    append("${selectedFolder.extra}")
                                }
                            }
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

                        //track selected button
                        var selectedButton by rememberSaveable{ mutableStateOf("summary") }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        ) {

                            //view summary
                            OutlinedButton(
                                modifier = Modifier.fillMaxWidth(0.5f)
                                ,
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
                                    fontWeight = FontWeight.Bold,
                                    fontSize = if (windowSizeClass == SizeClass.FOURHUNDRED ||
                                        windowSizeClass == SizeClass.FIVEHUNDRED ||
                                        windowSizeClass == SizeClass.SIXHUNDRED ||
                                        windowSizeClass == SizeClass.SEVENHUNDRED ||
                                        windowSizeClass == SizeClass.EIGHTHUNDRED
                                    ) 10.sp else 14.sp
                                )

                            }

                            Spacer(modifier = Modifier.width(24.dp))

                            //read full judgement
                            OutlinedButton(
                                modifier = Modifier.fillMaxWidth(1f)
                                ,
                                border = if (selectedButton == "full")
                                    BorderStroke(width = 1.dp, color = OutlineGrey) else
                                    BorderStroke(width = 1.dp, color = Orange),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Orange,
                                    contentColor = TextWhite

                                ), onClick = { selectedButton = "full" }) {

                                Text(
                                    modifier = Modifier.padding(6.dp),
                                    text = if (windowSizeClass == SizeClass.FOURHUNDRED ||
                                        windowSizeClass == SizeClass.FIVEHUNDRED ||
                                        windowSizeClass == SizeClass.SIXHUNDRED ||
                                        windowSizeClass == SizeClass.SEVENHUNDRED ||
                                        windowSizeClass == SizeClass.EIGHTHUNDRED ||
                                        windowSizeClass == SizeClass.NINEHUNDRED ||
                                        windowSizeClass == SizeClass.ONETHOUSAND ||
                                        windowSizeClass == SizeClass.ONEONE

                                    ) "Read Full" else "Read Full Judgement",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = if (windowSizeClass == SizeClass.FOURHUNDRED ||
                                        windowSizeClass == SizeClass.FIVEHUNDRED ||
                                        windowSizeClass == SizeClass.SIXHUNDRED ||
                                        windowSizeClass == SizeClass.SEVENHUNDRED ||
                                        windowSizeClass == SizeClass.EIGHTHUNDRED
                                    ) 10.sp else 14.sp
                                )
                            }
                        }
                    }

                }
            }
        }

    }
}