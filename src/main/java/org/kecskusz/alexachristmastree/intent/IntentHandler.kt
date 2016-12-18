package org.kecskusz.alexachristmastree.intent

import com.amazon.speech.slu.Intent
import com.amazon.speech.speechlet.SpeechletResponse

/**
 * Created by NetHunter on 2016. 12. 18..
 */
interface IntentHandler {
    fun matchesIntent(intent: Intent): Boolean

    fun handle(intent: Intent): SpeechletResponse
}