package com.example.lawpavilion.ui.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.lawpavilion.R
import com.example.lawpavilion.domain.model.Folder
import com.example.lawpavilion.ui.theme.BackgroundWhite
import com.example.lawpavilion.ui.theme.BodyGrey
import com.example.lawpavilion.ui.theme.BodyTextGrey
import com.example.lawpavilion.ui.theme.HeaderTextGrey
import com.example.lawpavilion.ui.utils.SizeClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Body(
    expanded: Boolean,
    railSizeIfSmallScreen: Dp,
    railSizeIfLargeScreen: Dp,
    windowSizeClass: SizeClass,
    folders: List<Folder>,
    selectedFolder: Folder,
    onSelectFolder: (folder: Folder) -> Unit,

) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 28.dp, top = 32.dp)
            .padding(
                end = if (expanded && railSizeIfSmallScreen == 230.dp) {
                    if (railSizeIfLargeScreen == 230.dp) 230.dp else 76.dp
                } else {
                    76.dp
                }
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        //remember selected court
        var selectedCourt by rememberSaveable{ mutableStateOf("Supreme Court") }

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
                            BackgroundWhite else BodyGrey
                    ),
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
                            BackgroundWhite else BodyGrey
                    ),
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
                                    onSelectFolder.invoke(folder)

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