package org.kecskusz.alexachristmastree.alexa

import com.amazon.speech.ui.SimpleCard

/**
 * Created by NetHunter on 2016. 12. 18..
 */
interface SimpleCardFactory {

    fun getSimpleCard(title: String, content: String): SimpleCard {
        val card = SimpleCard()
        card.title = title
        card.content = content
        return card
    }
}