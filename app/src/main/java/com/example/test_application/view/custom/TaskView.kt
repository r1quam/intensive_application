package com.example.test_application.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import com.example.test_application.R
import com.example.test_application.domain.entity.Task
import com.example.test_application.utils.dpToPx
import com.example.test_application.utils.getHour
import com.example.test_application.utils.getHourFormatted
import com.example.test_application.utils.lazyUi

class TaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val view: View by lazyUi { inflate(context, R.layout.view_task, this) }
    private val llHours: LinearLayout by lazyUi { view.findViewById(R.id.llHours) }
    private val llTasks: LinearLayout by lazyUi { view.findViewById(R.id.llTasks) }

    init {
        for (i in 0..24) {
            val textView = TextView(context).apply {
                layoutParams = LayoutParams(MATCH_PARENT, HOUR_HEIGHT.dpToPx)
                gravity = Gravity.CENTER
                text = getHourFormatted(i)
            }
            llHours.addView(textView)
        }
    }

    fun setItems(items: List<Task>, listener: (Int) -> Unit) {
        llTasks.removeAllViews()

        items.forEach { task ->
            val textView = TextView(context).apply {
                val hourStart = getHour(task.dateStart)
                val hourFinish = getHour(task.dateFinish)
                val duration = (hourFinish - hourStart).coerceAtLeast(1) + 1
                val params = LayoutParams(WRAP_CONTENT, HOUR_HEIGHT.dpToPx * duration)
                params.setMargins(0, HOUR_HEIGHT.dpToPx * hourStart, 0, 0)

                layoutParams = params
                setPadding(PADDING.dpToPx)
                setBackgroundResource(R.drawable.background_circle)
                text = task.name.ifEmpty { context.getString(R.string.without_name) }
                setOnClickListener { listener.invoke(task.id) }
            }
            llTasks.addView(textView)
        }
    }

    companion object {
        private const val HOUR_HEIGHT = 32
        private const val PADDING = 8
    }
}
