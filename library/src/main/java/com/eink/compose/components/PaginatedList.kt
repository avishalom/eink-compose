package com.eink.compose.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eink.compose.theme.eInkColorScheme
import com.eink.compose.theme.eInkTypography
import com.eink.compose.utils.EInkConstants

/**
 * E-Ink optimized paginated list that replaces scrolling with discrete page navigation.
 * Follows guideline #3 (page-based content loading) and #4 (avoid scrolling/dragging).
 * Uses HorizontalPager for swipe-based navigation between content pages.
 * 
 * @param items List of items to display
 * @param modifier Modifier for the component
 * @param itemsPerPage Number of items to display per page
 * @param pagerState State for controlling the pager
 * @param showPageIndicator Whether to show page indicator at bottom
 * @param indicatorStyle Style of page indicator (Dots or Numbers)
 * @param pageContent Composable to render each page of items
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> PaginatedList(
    items: List<T>,
    modifier: Modifier = Modifier,
    itemsPerPage: Int = 10,
    pagerState: PagerState = run {
        val pageCount = remember(items.size, itemsPerPage) {
            if (items.isEmpty()) 1 else (items.size + itemsPerPage - 1) / itemsPerPage
        }
        rememberPagerState(pageCount = { pageCount })
    },
    showPageIndicator: Boolean = true,
    indicatorStyle: PageIndicatorStyle = PageIndicatorStyle.Numbers,
    pageContent: @Composable (items: List<T>, pageIndex: Int) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pager content
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { pageIndex ->
            val startIndex = pageIndex * itemsPerPage
            val endIndex = minOf(startIndex + itemsPerPage, items.size)
            val pageItems = if (startIndex < items.size) {
                items.subList(startIndex, endIndex)
            } else {
                emptyList()
            }
            
            pageContent(pageItems, pageIndex)
        }
        
        // Page indicator
        if (showPageIndicator && pagerState.pageCount > 1) {
            Spacer(modifier = Modifier.height(EInkConstants.Spacing.MEDIUM))
            
            when (indicatorStyle) {
                PageIndicatorStyle.Dots -> {
                    DotPageIndicator(
                        pageCount = pagerState.pageCount,
                        currentPage = pagerState.currentPage
                    )
                }
                PageIndicatorStyle.Numbers -> {
                    NumberPageIndicator(
                        pageCount = pagerState.pageCount,
                        currentPage = pagerState.currentPage
                    )
                }
            }
        }
    }
}

/**
 * Simple paginated list with default item rendering in a column
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> SimplePaginatedList(
    items: List<T>,
    modifier: Modifier = Modifier,
    itemsPerPage: Int = 10,
    pagerState: PagerState = run {
        val pageCount = remember(items.size, itemsPerPage) {
            if (items.isEmpty()) 1 else (items.size + itemsPerPage - 1) / itemsPerPage
        }
        rememberPagerState(pageCount = { pageCount })
    },
    showPageIndicator: Boolean = true,
    indicatorStyle: PageIndicatorStyle = PageIndicatorStyle.Numbers,
    itemContent: @Composable (item: T, index: Int) -> Unit
) {
    PaginatedList(
        items = items,
        modifier = modifier,
        itemsPerPage = itemsPerPage,
        pagerState = pagerState,
        showPageIndicator = showPageIndicator,
        indicatorStyle = indicatorStyle
    ) { pageItems, pageIndex ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(EInkConstants.Spacing.MEDIUM),
            verticalArrangement = Arrangement.spacedBy(EInkConstants.Spacing.SMALL)
        ) {
            pageItems.forEachIndexed { localIndex, item ->
                val globalIndex = pageIndex * itemsPerPage + localIndex
                itemContent(item, globalIndex)
            }
        }
    }
}

/**
 * Paginated grid layout for displaying items in a grid format
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> PaginatedGrid(
    items: List<T>,
    columns: Int,
    modifier: Modifier = Modifier,
    itemsPerPage: Int = columns * 5, // Default to 5 rows per page
    pagerState: PagerState = run {
        val pageCount = remember(items.size, itemsPerPage) {
            if (items.isEmpty()) 1 else (items.size + itemsPerPage - 1) / itemsPerPage
        }
        rememberPagerState(pageCount = { pageCount })
    },
    showPageIndicator: Boolean = true,
    indicatorStyle: PageIndicatorStyle = PageIndicatorStyle.Numbers,
    itemContent: @Composable (item: T, index: Int) -> Unit
) {
    PaginatedList(
        items = items,
        modifier = modifier,
        itemsPerPage = itemsPerPage,
        pagerState = pagerState,
        showPageIndicator = showPageIndicator,
        indicatorStyle = indicatorStyle
    ) { pageItems, pageIndex ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(EInkConstants.Spacing.MEDIUM),
            verticalArrangement = Arrangement.spacedBy(EInkConstants.Spacing.SMALL)
        ) {
            pageItems.chunked(columns).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(EInkConstants.Spacing.SMALL)
                ) {
                    rowItems.forEachIndexed { colIndex, item ->
                        val globalIndex = pageIndex * itemsPerPage + 
                            (pageItems.indexOf(item))
                        Box(modifier = Modifier.weight(1f)) {
                            itemContent(item, globalIndex)
                        }
                    }
                    // Fill remaining columns if needed
                    repeat(columns - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

/**
 * Style options for page indicators
 */
enum class PageIndicatorStyle {
    Dots,
    Numbers
}

/**
 * Dot-style page indicator
 */
@Composable
private fun DotPageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(EInkConstants.Spacing.SMALL),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            Box(
                modifier = Modifier
                    .size(if (isSelected) 12.dp else 8.dp)
                    .background(
                        color = if (isSelected) {
                            eInkColorScheme().primary
                        } else {
                            eInkColorScheme().outline
                        },
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
            )
        }
    }
}

/**
 * Number-style page indicator
 */
@Composable
private fun NumberPageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    EInkText(
        text = "Page ${currentPage + 1} of $pageCount",
        style = eInkTypography().labelLarge,
        color = eInkColorScheme().onSurface,
        modifier = modifier.padding(EInkConstants.Spacing.SMALL)
    )
}