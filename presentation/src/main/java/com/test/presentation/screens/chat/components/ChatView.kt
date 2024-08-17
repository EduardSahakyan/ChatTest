package com.test.presentation.screens.chat.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.test.domain.models.Message
import com.test.domain.models.MessagePreferences
import kotlinx.coroutines.flow.Flow

@Composable
fun ChatView(
    modifier: Modifier = Modifier,
    messagePreferences: MessagePreferences,
    pagingState: Flow<PagingData<Message>>
) {

    val pagedItems = pagingState.collectAsLazyPagingItems()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = pagedItems.itemSnapshotList) {
        lazyListState.scrollToItem(0)
    }

    LazyColumn(
        state = lazyListState,
        reverseLayout = true,
        modifier = modifier
    ) {
        items(
            count = pagedItems.itemCount,
            key = pagedItems.itemKey { it.id }
        ) { index ->
            val item = pagedItems[index]
            item?.let {
                MessageItem(
                    message = item,
                    messagePreferences = messagePreferences
                )
            }
        }
    }

}