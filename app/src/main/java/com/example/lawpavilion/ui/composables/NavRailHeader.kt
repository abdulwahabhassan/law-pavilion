package com.example.lawpavilion.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lawpavilion.R
import com.example.lawpavilion.ui.theme.TextWhite

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