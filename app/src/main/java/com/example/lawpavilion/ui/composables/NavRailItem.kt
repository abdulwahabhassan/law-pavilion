package com.example.lawpavilion.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lawpavilion.ui.theme.NavTextHighLightTeal

@Composable
fun NavRailItem(
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