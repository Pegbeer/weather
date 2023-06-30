package me.pegbeer.expresate.ui.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import me.pegbeer.expresate.R

class TextIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val iconView: ImageView
    private val textView: TextView

    private var iconTextSpacing: Int = 0

    init {
        // Configurar la orientación del LinearLayout
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

        // Inflar el diseño personalizado para el CustomView
        inflate(context, R.layout.icon_text_view, this)

        // Obtener referencias a las vistas
        iconView = findViewById(R.id.imageViewIcon)
        textView = findViewById(R.id.textViewText)

        // Leer los atributos personalizados del AttributeSet
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TextIconView)
        val text = attributes.getString(R.styleable.TextIconView_android_text)
        val icon = attributes.getDrawable(R.styleable.TextIconView_icon)
        iconTextSpacing = attributes.getDimensionPixelSize(
            R.styleable.TextIconView_iconTextSpacing,
            0
        )

        // Configurar el texto y el icono
        setText(text)
        setIcon(icon)

        // Liberar los recursos del AttributeSet
        attributes.recycle()

        // Aplicar el espacio entre el icono y el texto
        applyIconTextSpacing()
    }

    fun setText(text: String?) {
        textView.text = text
    }

    fun setIcon(icon: Drawable?) {
        iconView.setImageDrawable(icon)
    }

    private fun applyIconTextSpacing() {
        val layoutParams = textView.layoutParams as LinearLayout.LayoutParams
        layoutParams.setMargins(iconTextSpacing, 0, 0, 0)
        textView.layoutParams = layoutParams
    }
}