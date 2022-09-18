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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.*
import androidx.wear.compose.material.Icon
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.braininavet.felicaemu.converter.A
import kotlin.math.floor
import kotlin.math.min

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
                Main(contentModifier, navController, cardList)
            }
            composable("add_card") {
                AddCard(contentModifier)
            }
        }
    }
}

@Composable
fun Main(
    modifier: Modifier,
    navController : NavController,
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
                    Card(
                        modifier = modifier,
                        onClick = {},
                        enabled = false,
                        contentPadding = CardDefaults.ContentPadding
                    ) {
                        Text(
                            text = "No cards",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.LightGray
                        )
                    }
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
                        //cardList.add("Card sample")
                        navController.navigate("add_card")
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

fun randomHex4Bytes(): String {
    var hexString = min(floor(Math.random() * 65536).toInt(), 65535).toString(16)

    if (hexString.length < 4)
        hexString = "0".repeat(4 - hexString.length) + hexString

    return hexString.uppercase()
}

fun makeSid(): String {
    val sid: String
    val sid2 = randomHex4Bytes()
    val sid3 = randomHex4Bytes()
    val sid4 = randomHex4Bytes()

    sid = "02FE$sid2$sid3$sid4".uppercase()

    if (sid.length != 16){
        return ""
    }

    if (sid.replace(Regex("/[^0-9A-F]/g"), "") !== sid) {
        return ""
    }

    return sid
}

fun makeCardNumber(sid: String): String {
    val converter = A()

    if (sid.length != 16 || !sid.startsWith("02FE"))
        return ""

    return converter.toKonamiID(sid)
}

@Composable
fun AddCard(modifier: Modifier) {
    var cardName by remember { mutableStateOf("Enter card name") }
    var sid by remember { mutableStateOf(makeSid())}
    var cardNumber by remember { mutableStateOf(makeCardNumber(sid)) }
    ScalingLazyColumn(
        modifier = Modifier.fillMaxWidth(),
        anchorType = ScalingLazyListAnchorType.ItemCenter
    ) {
        item {
            ListHeader {
                Text("Add")
            }
        }
        item {
            Card(
                modifier = modifier,
                onClick = {

                },
                contentPadding = CardDefaults.ContentPadding
            ) {
                Text("Name")
                Text(
                    text = cardName,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
        item {
            Card(
                modifier = modifier,
                onClick = {
                    sid = makeSid()
                    cardNumber = makeCardNumber(sid)
                },
                contentPadding = CardDefaults.ContentPadding
            ) {
                Row {
                    Text("SID")
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                        contentDescription = "Refresh"
                    )
                }
                Text(
                    text = sid,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
        item {
            Card(
                modifier = modifier,
                enabled = false,
                onClick = {},
                contentPadding = CardDefaults.ContentPadding
            ) {
                Text("Card Number")
                Text(
                    text = cardNumber,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }
    }
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
