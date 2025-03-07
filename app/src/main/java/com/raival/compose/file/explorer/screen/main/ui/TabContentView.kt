package com.raival.compose.file.explorer.screen.main.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.raival.compose.file.explorer.App.Companion.globalClass
import com.raival.compose.file.explorer.screen.main.tab.apps.AppsTab
import com.raival.compose.file.explorer.screen.main.tab.apps.ui.AppsTabContentView
import com.raival.compose.file.explorer.screen.main.tab.files.FilesTab
import com.raival.compose.file.explorer.screen.main.tab.files.ui.FilesTabContentView
import com.raival.compose.file.explorer.screen.main.tab.home.HomeTab
import com.raival.compose.file.explorer.screen.main.tab.home.ui.MainTabContentView

@Composable
fun ColumnScope.TabContentView() {
    val mainActivityManager = globalClass.mainActivityManager

    Column(modifier = Modifier.weight(1f)) {
        if (mainActivityManager.tabs.isNotEmpty()) {
            val currentTabIndex = mainActivityManager.selectedTabIndex
            val currentTab = mainActivityManager.tabs[currentTabIndex]

            LaunchedEffect(key1 = currentTab.id) {
                currentTab.apply {
                    if (!isCreated) onTabStarted() else onTabResumed()
                }
            }

            if (currentTab is FilesTab) {
                FilesTabContentView(currentTab)
            } else if (currentTab is HomeTab) {
                MainTabContentView(currentTab)
            } else if (currentTab is AppsTab) {
                AppsTabContentView(currentTab)
            }
        }
    }
}