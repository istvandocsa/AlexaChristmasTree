/**
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

 * http://aws.amazon.com/apache2.0/

 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
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
import com.amazon.speech.ui.SimpleCard

/**
 * This sample shows how to create a simple speechlet for handling speechlet requests.
 */
class AlexaChristmasTreeSpeechlet : Speechlet {

    @Throws(SpeechletException::class)
    override fun onSessionStarted(request: SessionStartedRequest, session: Session) {
        LOG.info("onSessionStarted requestId={}, sessionId={}", request.requestId,
                session.sessionId)
        // any initialization logic goes here
    }

    @Throws(SpeechletException::class)
    override fun onLaunch(request: LaunchRequest, session: Session): SpeechletResponse {
        LOG.info("onLaunch requestId={}, sessionId={}", request.requestId,
                session.sessionId)
        return welcomeResponse
    }

    @Throws(SpeechletException::class)
    override fun onIntent(request: IntentRequest, session: Session): SpeechletResponse {
        LOG.info("onIntent requestId={}, sessionId={}", request.requestId,
                session.sessionId)

        val intent = request.intent
        val intentName = intent?.name

        if ("ChangeColorIntent" == intentName) {
            return getChangeColorResponse(intent)
        } else if ("AMAZON.HelpIntent" == intentName) {
            return helpResponse
        } else {
            throw SpeechletException("Invalid Intent")
        }
    }

    @Throws(SpeechletException::class)
    override fun onSessionEnded(request: SessionEndedRequest, session: Session) {
        LOG.info("onSessionEnded requestId={}, sessionId={}", request.requestId,
                session.sessionId)
        // any cleanup logic goes here
    }

    /**
     * Creates and returns a `SpeechletResponse` with a welcome message.

     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private // Create the plain text output.
            // Create reprompt
    val welcomeResponse: SpeechletResponse
        get() {
            val speechText = "Hi there, I am your christmas tree that is not just beautiful, but also smart. You can ask me to change the color, the mode."
            val speech = PlainTextOutputSpeech()
            speech.text = speechText
            val reprompt = Reprompt()
            reprompt.outputSpeech = speech

            return SpeechletResponse.newAskResponse(speech, reprompt)
        }

    /**
     * Creates a `SpeechletResponse` for the hello intent.

     * @return SpeechletResponse spoken and visual response for the given intent
     * *
     * @param intent
     */
    private fun getChangeColorResponse(intent: Intent): SpeechletResponse {
        val speechText = "Changing color to " + intent.getSlot("Color").value

        // Create the Simple card content.
        val card = SimpleCard()
        card.title = "Color change"
        card.content = speechText

        // Create the plain text output.
        val speech = PlainTextOutputSpeech()
        speech.text = speechText

        return SpeechletResponse.newTellResponse(speech, card)
    }

    /**
     * Creates a `SpeechletResponse` for the help intent.

     * @return SpeechletResponse spoken and visual response for the given intent
     */
    private // Create the plain text output.
            // Create reprompt
    val helpResponse: SpeechletResponse
        get() {
            val speechText = "Never forget, I am an awesome christmas tree. Ask me to change the color or the mode!"
            val speech = PlainTextOutputSpeech()
            speech.text = speechText
            val reprompt = Reprompt()
            reprompt.outputSpeech = speech

            return SpeechletResponse.newAskResponse(speech, reprompt)
        }

    companion object {
        private val LOG = LoggerFactory.getLogger(AlexaChristmasTreeSpeechlet::class.java)
    }
}
