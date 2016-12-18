package org.kecskusz.alexachristmastree.intent

import com.amazon.speech.slu.Intent
import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt

class HelpIntentHandler : IntentHandler {
    override fun matchesIntent(intent: Intent): Boolean {
        return intent.name == "AMAZON.HelpIntent"
    }

    override fun handle(intent: Intent): SpeechletResponse {
        val speechText = "Never forget, I am an awesome christmas tree. Ask me to change the color or the mode!"
        val speech = PlainTextOutputSpeech()
        speech.text = speechText
        val reprompt = Reprompt()
        reprompt.outputSpeech = speech

        return SpeechletResponse.newAskResponse(speech, reprompt)
    }
}
