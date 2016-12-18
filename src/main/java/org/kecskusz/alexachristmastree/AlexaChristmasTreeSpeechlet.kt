package org.kecskusz.alexachristmastree

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.amazon.speech.slu.Intent
import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.LaunchRequest
import com.amazon.speech.speechlet.Session
import com.amazon.speech.speechlet.SessionEndedRequest
import com.amazon.speech.speechlet.SessionStartedRequest
import com.amazon.speech.speechlet.Speechlet
import com.amazon.speech.speechlet.SpeechletException
import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.PlainTextOutputSpeech
import com.amazon.speech.ui.Reprompt
import org.kecskusz.alexachristmastree.intent.ChangeIntentHandler
import org.kecskusz.alexachristmastree.intent.HelpIntentHandler
import org.kecskusz.alexachristmastree.intent.IntentHandler
import java.util.*

/**
 * This sample shows how to create a simple speechlet for handling speechlet requests.
 */
class AlexaChristmasTreeSpeechlet : Speechlet{

    @Throws(SpeechletException::class)
    override fun onSessionStarted(request: SessionStartedRequest, session: Session) {
        LOG.info("onSessionStarted requestId = ${request.requestId}, sessionId = ${session.sessionId}")
    }

    @Throws(SpeechletException::class)
    override fun onLaunch(request: LaunchRequest, session: Session): SpeechletResponse {
        //TODO: Refactor
        LOG.info("onLaunch requestId = ${request.requestId}, sessionId = ${session.sessionId}")

        val speechText = "Hi there, I am your christmas tree that is not just beautiful, but also smart. You can ask me to change the color, the mode."
        val speech = PlainTextOutputSpeech()
        speech.text = speechText
        val reprompt = Reprompt()
        reprompt.outputSpeech = speech

        return SpeechletResponse.newAskResponse(speech, reprompt)
    }

    @Throws(SpeechletException::class)
    override fun onIntent(request: IntentRequest, session: Session): SpeechletResponse {
        LOG.info("onIntent requestId = ${request.requestId}, sessionId = ${session.sessionId}")

        val intent = request.intent
        try {
            return intentHandlers.first { it.matchesIntent(intent) }.handle(intent)
        } catch (e : NoSuchElementException) {
            throw SpeechletException("Invalid intent.")
        }
    }

    @Throws(SpeechletException::class)
    override fun onSessionEnded(request: SessionEndedRequest, session: Session) {
        LOG.info("onSessionEnded requestId = ${request.requestId}, sessionId = ${session.sessionId}")
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(AlexaChristmasTreeSpeechlet::class.java)
        private val intentHandlers : MutableSet<IntentHandler> = mutableSetOf(ChangeIntentHandler(), HelpIntentHandler())
    }
}
