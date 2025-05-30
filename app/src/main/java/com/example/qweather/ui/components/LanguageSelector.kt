package com.example.qweather.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.qweather.R
import com.example.qweather.utils.LocaleUtils

@Composable
fun LanguageSelector(
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    var showLanguageDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showLanguageDialog = true }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = "Language",
                tint = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(
                    id = if (currentLanguage == LocaleUtils.ARABIC) R.string.arabic
                    else R.string.english
                ),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    if (showLanguageDialog) {
        AlertDialog(
            onDismissRequest = { showLanguageDialog = false },
            title = { Text(stringResource(R.string.select_language)) },
            text = {
                Column {
                    LanguageOption(
                        language = LocaleUtils.ENGLISH,
                        isSelected = currentLanguage == LocaleUtils.ENGLISH,
                        onClick = {
                            onLanguageSelected(LocaleUtils.ENGLISH)
                            showLanguageDialog = false
                        }
                    )
                    LanguageOption(
                        language = LocaleUtils.ARABIC,
                        isSelected = currentLanguage == LocaleUtils.ARABIC,
                        onClick = {
                            onLanguageSelected(LocaleUtils.ARABIC)
                            showLanguageDialog = false
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showLanguageDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}

@Composable
private fun LanguageOption(
    language: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(
                id = if (language == LocaleUtils.ARABIC) R.string.arabic
                else R.string.english
            ),
            style = MaterialTheme.typography.bodyLarge
        )
    }
} 