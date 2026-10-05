package io.legado.app.ui.widget.components.focus

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.legado.app.utils.isTv

/**
 * 遥控器/键盘 D-pad 导航时的焦点高亮描边。
 *
 * 默认仅在电视（[isTv]）启用，触屏设备的视觉与交互保持不变：
 * Compose 的触摸点击不会抢占焦点，因此即便开启也不会在手机上误显描边。
 * 焦点事件会沿焦点树向祖先传播，外层 modifier 可以观察到内部可聚焦子项。
 */
@Composable
fun Modifier.appFocusRing(
    enabled: Boolean = LocalContext.current.isTv,
    width: Dp = 3.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(12.dp),
): Modifier {
    // remember 放在早退之前：enabled 恒定时组合结构保持稳定
    var focused by remember { mutableStateOf(false) }
    if (!enabled) return this
    return this
        .onFocusChanged { focused = it.isFocused }
        .border(
            width = if (focused) width else 0.dp,
            color = color,
            shape = shape
        )
}
