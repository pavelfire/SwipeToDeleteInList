package com.vk.directop.swipetodeleteinlist

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.vk.directop.swipetodeleteinlist.ui.theme.DeleteColor
import com.vk.directop.swipetodeleteinlist.ui.theme.EmailColor
import com.vk.directop.swipetodeleteinlist.ui.theme.Purple40

@Composable
fun ContactScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val contacts = remember {
        mutableStateListOf(
            *(1..100).map {
                ContactUi(
                    id = it,
                    name = "Contact $it",
                    isOptionsRevealed = false,
                )
            }.toTypedArray()
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        itemsIndexed(
            items = contacts,
        ) { index, contact ->
            SwipeableItemWithActions(
                isRevealed = contact.isOptionsRevealed,
                onExpanded = {
                    contacts[index] = contact.copy(isOptionsRevealed = true)
                },
                onCollapsed = {
                    contacts[index] = contact.copy(isOptionsRevealed = false)
                },
                actions = {
                    ActionIcon(
                        onClick = {
                            contacts.remove(contact)
                            Toast.makeText(
                                context,
                                "Contact ${contact.name} was deleted.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                        backgroundColor = DeleteColor,
                        icon = Icons.Default.Delete,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            Toast.makeText(
                                context,
                                "Contact ${contact.name} was sent an email.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                        backgroundColor = EmailColor,
                        icon = Icons.Default.Email,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            Toast.makeText(
                                context,
                                "Contact ${contact.name} was shared.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                        backgroundColor = Purple40,
                        icon = Icons.Default.Share,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            ) {
                Text(
                    text = "Contact ${contact.id}",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
