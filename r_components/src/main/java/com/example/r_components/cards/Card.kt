package com.example.r_components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A simple card composable that provides a customizable container for content.
 *
 * @param modifier The modifier to be applied to the card.
 * @param shape The shape of the card. Defaults to a rounded rectangle with 8.dp corners.
 * @param elevation The elevation of the card. Defaults to 1.dp.
 * @param onClick A lambda to be executed when the card is clicked. If null, the card will not be clickable.
 * @param colors The colors to be used for the card. Defaults to [CardDefaults.cardColors].
 * @param content The content to be displayed inside the card. This is a composable lambda that will be placed inside a [ColumnScope].
 */
@Composable
fun RevCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    elevation: Dp = 1.dp,
    onClick: (() -> Unit)? = null,
    colors: CardColors = CardDefaults.cardColors(),
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        shape = shape,
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation,
        ),
        colors = colors,
        content = content,
    )
}

/**
 * A flexible and theme-aware card component for the CMP design system.
 *
 * This composable abstracts over Material3 [Card], [ElevatedCard], and [OutlinedCard],
 * and selects the appropriate variant based on [CardVariant].
 *
 * @param modifier Modifier applied to the card container. *(Default: [Modifier])*
 * @param onClick Lambda triggered when the card is clicked. *(Default: `{}`)*
 * @param enabled Whether the card is enabled and responds to click events. *(Default: `true`)*
 * @param variant Determines the visual style of the card. *(Default: [CardVariant.FILLED])*
 * @param shape The shape of the card. *(Optional; defaults to the variant's shape via [CardDefaults])*
 * @param colors The color configuration of the card. *(Optional; defaults to the variant's colors via [CardDefaults])*
 * @param elevation Elevation of the card surface. *(Optional; defaults to the variant's elevation via [CardDefaults])*
 * @param borderStroke Border stroke for the card.
 * (Optional; defaults to [CardDefaults.outlinedCardBorder] when [variant] is [CardVariant.OUTLINED], `null` otherwise)*
 * @param interactionSource The [MutableInteractionSource] to observe user interaction states. *(Optional)*
 * @param content The content of the card, scoped to a [ColumnScope] for vertical layout flexibility.
 */

@Composable
fun RevCustomCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    variant: CardVariant = CardVariant.FILLED,
    shape: Shape? = null,
    colors: CardColors? = null,
    elevation: CardElevation? = null,
    borderStroke: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    when (variant) {
        CardVariant.FILLED -> Card(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape ?: CardDefaults.shape,
            colors = colors ?: CardDefaults.cardColors(),
            elevation = elevation ?: CardDefaults.cardElevation(),
            border = borderStroke,
            interactionSource = interactionSource,
        ) {
            content()
        }

        CardVariant.ELEVATED -> ElevatedCard(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape ?: CardDefaults.elevatedShape,
            colors = colors ?: CardDefaults.elevatedCardColors(),
            elevation = elevation ?: CardDefaults.elevatedCardElevation(),
            interactionSource = interactionSource,
        ) {
            content()
        }

        CardVariant.OUTLINED -> OutlinedCard(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape ?: CardDefaults.outlinedShape,
            colors = colors ?: CardDefaults.outlinedCardColors(),
            elevation = elevation ?: CardDefaults.outlinedCardElevation(),
            border = borderStroke ?: CardDefaults.outlinedCardBorder(enabled),
            interactionSource = interactionSource,
        ) {
            content()
        }
    }
}

/**
 * A card component used to display the state of an upload operation.
 *
 * This composable uses [RevCustomCard] with a specific configuration for
 * displaying upload-related information. It features a text label, an icon,
 * and an onClick action.
 *
 * @param text The text to display on the card, typically indicating the upload action or status.
 * @param icon The [ImageVector] to display as an icon on the card.
 * @param onClick Lambda triggered when the card is clicked.
 * @param modifier Modifier applied to the card container. *(Default: [Modifier])*
 * @param height The height of the card. *(Default: `112.dp`)*
 */
@Composable
fun RevUploadStateCard(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp = 112.dp,
) {
    RevCustomCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        onClick = onClick,
        enabled = true,
        variant = CardVariant.FILLED,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        borderStroke = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.secondaryContainer,
        ),
    ) {
        RevUploadStateCardContent(
            text = text,
            icon = icon,
            modifier = modifier,
        )
    }
}

/**
 * Composable function that defines the content for an upload state card.
 *
 * This function displays an icon and a text label, centered within the card.
 * It's used as the content for [RevUploadStateCard].
 *
 * @param text The text to be displayed below the icon.
 * @param icon The [ImageVector] to be displayed above the text.
 * @param modifier The [Modifier] to be applied to the content's `Column`. Defaults to [Modifier].
 */
@Composable
fun RevUploadStateCardContent(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

/**
 * A composable function that displays a card for an uploaded file with options to remove, view, or select a new file.
 *
 * @param icon The icon to be displayed on the card.
 * @param label The label text to be displayed above the card.
 * @param fileName The name of the uploaded file.
 * @param fileSize The size of the uploaded file.
 * @param onRemoveClick A lambda function to be executed when the "Remove" button is clicked.
 * @param onViewClick A lambda function to be executed when the "View" button is clicked.
 * @param onSelectNewClick A lambda function to be executed when the "Select New" button is clicked.
 * @param modifier Optional [Modifier] for the card.
 * @param removeText The text for the "Remove" button.
 * @param selectText The text for the "Select New" button.
 * @param viewText The text for the "View" button.
 * @param height The height of the card. Defaults to 112.dp.
 */
@Composable
fun RevUploadedStateCard(
    icon: ImageVector,
    label: String,
    fileName: String,
    fileSize: String,
    onRemoveClick: () -> Unit,
    onViewClick: () -> Unit,
    onSelectNewClick: () -> Unit,
    modifier: Modifier = Modifier,
    removeText: String,
    selectText: String,
    viewText: String,
    height: Dp = 112.dp,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        RevCustomCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            enabled = false,
            variant = CardVariant.OUTLINED,
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
            borderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.secondaryContainer),
        ) {
            RevUploadedCardContent(
                icon = icon,
                fileName = fileName,
                fileSize = fileSize,
                onRemoveClick = onRemoveClick,
                onViewClick = onViewClick,
                onSelectNewClick = onSelectNewClick,
                removeText = removeText,
                selectText = selectText,
                viewText = viewText,
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .offset(y = (-7).dp),
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(horizontal = 2.dp),
            )
        }
    }
}

/**
 * Displays the content of an uploaded file card, including file details and action buttons.
 *
 * @param removeText Text for the "Remove" button.
 * @param selectText Text for the "Select New File" button.
 * @param viewText Text for the "View File" button.
 * @param icon The [ImageVector] representing the file type.
 * @param fileName The name of the uploaded file.
 * @param fileSize The size of the uploaded file.
 * @param onRemoveClick Lambda triggered when the "Remove" button is clicked.
 * @param onViewClick Lambda triggered when the "View" button is clicked.
 * @param onSelectNewClick Lambda triggered when the "Select New File" button is clicked.
 * @param modifier Modifier applied to the content container. *(Default: [Modifier])*
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RevUploadedCardContent(
    removeText: String,
    selectText: String,
    viewText: String,
    icon: ImageVector,
    fileName: String,
    fileSize: String,
    onRemoveClick: () -> Unit,
    onViewClick: () -> Unit,
    onSelectNewClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(4.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                imageVector = icon,
                contentDescription = "file icon",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center),
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = fileName,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.onSurface,
            )

            Text(
                text = fileSize,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
            )

            Spacer(modifier = Modifier.height(24.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    modifier = Modifier.clickable {
                        onRemoveClick()
                    },
                    text = removeText,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                )

                Text(
                    modifier = Modifier.clickable {
                        onViewClick()
                    },
                    text = viewText,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                )

                Text(
                    modifier = Modifier.clickable {
                        onSelectNewClick()
                    },
                    text = selectText,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

/**
 * A specialized card component for "Explore" sections, displaying an icon and text.
 * This card is typically used to represent categories or options that users can explore.
 * It utilizes [RevCustomCard] with an outlined variant and specific styling.
 *
 * @param text The text content to display on the card.
 * @param icon The [ImageVector] to display as an icon on the card.
 * @param modifier Modifier applied to the card container. *(Default: [Modifier])*
 * @param multiline If `true`, the first space in the [text] will be replaced with a newline character,
 *                  allowing the text to wrap to a new line. *(Default: `true`)*
 * @param maxLines The maximum number of lines for the text. *(Default: `2`)*
 * @param onClick Lambda triggered when the card is clicked.
 */
@Composable
fun RevExploreCard(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    multiline: Boolean = true,
    maxLines: Int = 2,
    onClick: () -> Unit,
) {
    RevCustomCard(
        modifier = modifier
            .height(56.dp)
            .border(
                1.dp,
                MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(12.dp),
            ),
        variant = CardVariant.OUTLINED,
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Image(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            )

            Text(
                text = if (multiline) {
                    text.replaceFirst(" ", "\n")
                } else {
                    text
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = maxLines,
                overflow = TextOverflow.MiddleEllipsis,
                textAlign = TextAlign.Start,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Rev_Explore_Card_Preview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row {
            // Method 1: Automatic multiline (spaces become line breaks)
            RevExploreCard(
                modifier = Modifier.weight(0.5f, true),
                icon = Icons.Default.Explore,
                text = "Home Loan",
                onClick = { /* Handle click */ },
            )

            // Method 2: Custom line breaks with explicit control
            RevExploreCard(
                modifier = Modifier.weight(0.5f, true),
                icon = Icons.Default.Explore,
                text = "Personal Loan",
                onClick = { /* Handle click */ },
            )
        }

        // Method 3: Single line version
        RevExploreCard(
            icon = Icons.Default.Explore,
            text = "Savings Account",
            multiline = true,
            onClick = { /* Handle click */ },
        )

        RevExploreCard(
            icon = Icons.Default.Explore,
            text = "Business Account",
            multiline = true,
            onClick = { /* Handle click */ },
        )
    }
}

@Preview
@Composable
private fun Upload_Card_Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Different variant cards",
            style = MaterialTheme.typography.headlineMedium,
        )
        CardVariant.entries.forEach {
            RevCustomCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(112.dp),
                onClick = {},
                enabled = true,
                variant = it,
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (it == CardVariant.ELEVATED) {
                        25.dp
                    } else {
                        0.dp
                    },
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
                borderStroke = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.secondaryContainer,
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = Icons.Default.Explore,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Upload Documents",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }

        Text(
            text = "Upload Card",
            style = MaterialTheme.typography.headlineMedium,
        )

        RevUploadStateCard(
            text = "Upload Your Id",
            icon = Icons.Default.Explore,
            onClick = {},
        )
    }
}

@Preview
@Composable
fun FloatingTitleCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        RevUploadedStateCard(
            label = "Profile Photo",
            icon = Icons.Default.Explore,
            fileName = "profile photo 67883.png",
            fileSize = "346 KB",
            onRemoveClick = {},
            onViewClick = {},
            onSelectNewClick = {},
            removeText = "Remove File",
            selectText = "Select New File",
            viewText = "View File",
        )
    }
}

enum class CardVariant {

    /**
     * A standard filled card with background color and elevation.
     * Corresponds to [Card].
     * Recommended for most use cases where elevation and theming are desired.
     */
    FILLED,

    /**
     * A card with extra emphasis via elevation and contrast, but no border.
     * Corresponds to [ElevatedCard].
     * Useful for drawing attention to important or interactive content.
     */
    ELEVATED,

    /**
     * A low-emphasis card with a visible border and no elevation.
     * Corresponds to [OutlinedCard].
     * Suitable for lightweight, non-intrusive containers or when visual grouping is needed without elevation.
     */
    OUTLINED,
}