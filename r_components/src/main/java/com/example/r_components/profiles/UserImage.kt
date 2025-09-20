import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.example.r_components.profiles.TextUserImage

/**
 * Displays a user image from a [bitmap].
 * If [bitmap] is null or empty, it falls back to [TextUserImage] showing the first letter of [username].
 *
 * @param modifier Modifier to adjust the size, layout, or styling of the image.
 * @param context Optional [Context] for Coil image loading; defaults to [LocalContext.current].
 * @param bitmap The image bytes to display.
 * @param username The user's name to show if bitmap is not available.
 */
@Composable
fun UserImage(
    modifier: Modifier = Modifier,
    context: Context? = null,
    bitmap: ByteArray? = null,
    username: String? = null
) {
    if (bitmap==null || bitmap.isEmpty() ) {
        TextUserImage(
            text = username?.firstOrNull()?.toString() ?: "R",
            modifier = modifier
        )
        return
    }

    val ctx = context ?: LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(ctx).data(bitmap).build(),
        imageLoader = ImageLoader(ctx)
    )

    Image(
        modifier = modifier.clip(CircleShape).fillMaxSize(),
        painter = painter,
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop,
    )
}

/**
 * Displays a user image from a [model] URL or URI.
 * If [model] is null or empty, it falls back to [TextUserImage] showing the first letter of [username].
 *
 * @param modifier Modifier to adjust the size, layout, or styling of the image.
 * @param model The URL or URI of the image to display.
 * @param username The user's name to show if image is not available.
 */
@Composable
fun UserImage(
    modifier: Modifier = Modifier,
    model: String? = null,
    username: String? = null
) {
    if (model.isNullOrEmpty()) {
        TextUserImage(
            text = username?.firstOrNull()?.toString() ?: "R",
            modifier = modifier
        )
        return
    }

    AsyncImage(
        model = model,
        modifier = modifier.clip(CircleShape).fillMaxSize(),
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop,
    )
}

/**
 * Preview for bitmap-based [UserImage].
 */
@Preview(showBackground = true)
@Composable
fun UserImageBitmapPreview() {
    UserImage(
        bitmap = null,
        modifier = Modifier.size(80.dp),
        username = "John Doe"
    )
}

/**
 * Preview for URL-based [UserImage].
 */
@Preview(showBackground = true)
@Composable
fun UserImageUrlPreview() {
    UserImage(
        model = "",
        modifier = Modifier.size(80.dp),
        username = "Jane Doe"
    )
}
