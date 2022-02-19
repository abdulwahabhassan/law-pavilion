package com.example.lawpavilion.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lawpavilion.R
import com.example.lawpavilion.ui.theme.BackgroundNavyBlue
import com.example.lawpavilion.ui.theme.NavTextHighLightTeal
import com.example.lawpavilion.ui.theme.NavTextLightPurple


@Composable
fun AppDrawer(
    closeDrawer: () -> Unit,
    currentRoute: String,
    modifier: Modifier = Modifier,
    navigateToDashBoard: () -> Unit,
    navigateToLatestJudgements: () -> Unit,
    navigateToLawReports: () -> Unit,
    navigateToLawsOfNigeria: () -> Unit,
    navigateToCPRules: () -> Unit,
    navigateToIndexAndDigest: () -> Unit,
    navigateToTextBooksAndJournal: () -> Unit,
    navigateToResearchFolder: () -> Unit,
    navigateToWordsInGold: () -> Unit,
    navigateToNewContents: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {

        //Drawer Icon
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Drawer icon",
                tint = MaterialTheme.colors.onPrimary
            )
        }

        //pavilion logo
        Image(
            painter = painterResource(R.drawable.ic_law_pavilion),
            contentDescription = "law pavilion logo"
        )

        //dashboard
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })
        //latest judgements
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })
        //law reports
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })
        //laws of nigeria
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })
        //cp rules
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })
        //index and digest
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })
        //text books and journals
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })
        //research folder
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })
        //words in gold
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })
        //new contents
        DrawerItem(icon = Icons.Filled.Home, label = "", isSelected = false, action = { /*TODO*/ })

    }

}


@Composable
private fun DrawerItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colors
    val textIconColor = if (isSelected) {
        NavTextHighLightTeal
    } else {
        colors.onBackground
    }
    val backgroundColor = if (isSelected) {
        colors.surface
    } else {
        Color.Transparent
    }

    val surfaceModifier = modifier
        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        TextButton(
            onClick = action,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    style = MaterialTheme.typography.body2,
                    color = textIconColor
                )
            }
        }
    }
}
