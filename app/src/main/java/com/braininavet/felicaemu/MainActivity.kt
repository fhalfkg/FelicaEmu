package com.braininavet.felicaemu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import androidx.wear.compose.material.Icon

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
        val contentModifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            EmulatorSwitch(contentModifier)
            Chip(
                modifier = contentModifier,
                label = {
                    Text(
                        text = "Settings",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colors.onPrimary),
                onClick = {}
            )
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
                text = "Emulation",
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
