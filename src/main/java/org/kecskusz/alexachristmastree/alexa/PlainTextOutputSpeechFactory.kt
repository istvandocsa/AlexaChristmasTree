package org.kecskusz.alexachristmastree.alexa

import com.amazon.speech.ui.PlainTextOutputSpeech

/**
 * Created by NetHunter on 2016. 12. 18..
 */
interface PlainTextOutputSpeechFactory {
    fun getPlainTextOutputSpeech(speechText: String) : PlainTextOutputSpeech {
        val speech = PlainTextOutputSpeech()
        speech.text = speechText
        return speech
    }
}