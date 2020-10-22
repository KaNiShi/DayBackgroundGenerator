package com.github.kanishi.day.background.generator

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.*
import kotlinx.browser.document
import kotlin.js.Date
import kotlin.math.max

fun main() {

    val dayOfWeeks = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    val monthNames = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val textSize = 48
    val margin = 16.0

    GlobalScope.launch {
        while (true) {
            val canvas = document.getElementById("main") as? HTMLCanvasElement ?: return@launch
            val ctx = canvas.getContext("2d") as? CanvasRenderingContext2D ?: return@launch

            val date = Date()
            ctx.fillStyle = "#424242"
            ctx.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
            ctx.fillStyle = "#FFFFFF"
            ctx.font = "${textSize}px 'Orbitron', sans-serif"
            ctx.textAlign = CanvasTextAlign.CENTER
            ctx.textBaseline = CanvasTextBaseline.TOP

            val yearText = date.getFullYear().toString()
            val yearTextWidth = ctx.measureText(yearText).width
            val dayText = "${(date.getMonth() + 1).toString().padStart(2, '0')}/${date.getDate().toString().padStart(2, '0')}"
            val dayTextWidth = ctx.measureText(dayText).width
            val dayOfWeekText = dayOfWeeks[date.getDay()]
            val dayOfWeekTextWidth = ctx.measureText(dayOfWeekText).width

            val textCenterX = canvas.width.toDouble() - max(yearTextWidth, max(dayTextWidth, dayOfWeekTextWidth)) / 2.0 - margin

            ctx.fillText(yearText, textCenterX, margin)
            ctx.fillText(dayText, textCenterX, margin + textSize)
            ctx.fillText(dayOfWeekText, textCenterX, margin + textSize * 2)
            delay(100)
        }
    }

    (document.getElementById("download_image") as? HTMLButtonElement)?.let { button ->
        button.onclick = {
            (document.createElement("a") as? HTMLAnchorElement)?.let anchor@ { anchor ->
                val canvas = document.getElementById("main") as? HTMLCanvasElement ?: return@anchor
                anchor.download = "image.png"
                anchor.href = canvas.toDataURL()
                anchor.click()
            }
        }
    }
}
