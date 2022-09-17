package com.braininavet.felicaemu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import androidx.wear.compose.material.Icon
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    MaterialTheme {
        val navController = rememberSwipeDismissableNavController()
        val cardList = remember { mutableStateListOf<String>() }
        val contentModifier = Modifier
            .fillMaxWidth()

        SwipeDismissableNavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                Main(contentModifier, cardList)
            }
            composable("add_card") {
                AddCard()
            }
        }
    }
}

@Composable
fun Main(
    modifier: Modifier,
    cardList: SnapshotStateList<String>
) {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxWidth(),
        anchorType = ScalingLazyListAnchorType.ItemCenter,
        content = {
            item {
                ListHeader {
                    Text("Cards")
                }
            }
            if (cardList.size == 0) {
                item {
                    Chip(
                        modifier = modifier,
                        colors = ChipDefaults.chipColors(MaterialTheme.colors.surface),
                        onClick = {},
                        label = {
                            Text(
                                text = "No cards",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.LightGray
                            )
                        }
                    )
                }
            } else {
                itemsIndexed(cardList) { _, _ ->
                    EmulatorSwitch(modifier)
                }
            }
            item {
                Spacer(Modifier.size(0.dp, 8.dp))
            }
            item {
                Button(
                    modifier = Modifier.size(ButtonDefaults.DefaultButtonSize),
                    onClick = {
                        cardList.add("Card sample")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_add_24),
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(ButtonDefaults.DefaultIconSize)
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }
        }
    )
}

@Composable
fun AddCard() {

}

@Composable
fun EmulatorSwitch(modifier: Modifier) {
    var checked by remember { mutableStateOf(false) }
    ToggleChip(
        modifier = modifier,
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = ToggleChipDefaults.toggleChipColors(
            uncheckedToggleControlColor = ToggleChipDefaults.SwitchUncheckedIconColor
        ),
        toggleControl = {
            Icon(
                imageVector = ToggleChipDefaults.switchIcon(checked = checked),
                contentDescription = if (checked) "On" else "Off"
            )
        },
        label = {
            Text(
                text = "Card sample",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}
