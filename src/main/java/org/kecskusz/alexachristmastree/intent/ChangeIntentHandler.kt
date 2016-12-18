package org.kecskusz.alexachristmastree.intent

import com.amazon.speech.slu.Intent
import com.amazon.speech.speechlet.SpeechletResponse
import org.kecskusz.alexachristmastree.alexa.PlainTextOutputSpeechFactory
import org.kecskusz.alexachristmastree.alexa.SimpleCardFactory
import org.kecskusz.alexachristmastree.iot.ChristmasTreeShadowUpdater
import org.slf4j.LoggerFactory

/**
 * Created by NetHunter on 2016. 12. 18..
 */
class ChangeIntentHandler : IntentHandler, SimpleCardFactory, PlainTextOutputSpeechFactory {

    override fun matchesIntent(intent: Intent): Boolean {
        val matches = intent.name.matches(Regex("Change.*Intent"))
        LOG.debug("Checking match [matches = ${matches}, intentName = ${intent.name}]")
        return matches
    }

    override fun handle(intent: Intent): SpeechletResponse {
        LOG.info("Handling change intent. [slots = ${intent.slots.keys}]")
        val changedFragments = mutableListOf<String>()

        var state = christmasTreeShadowUpdater.getTreeState()
        LOG.info("Current state. [state = ${state}]")
        intent.slots.forEach {
            val slot = it.value
            LOG.debug("Changing slot. [name = ${slot.name}, value = ${slot.value}]")
            when (it.key) {
                "Color" -> state = state.copy(color = slot.value)
                "Mode" -> state = state.copy(mode = slot.value)
            }
            changedFragments.add("${it.key} to ${slot.value}")
        }
        LOG.info("Updating state with following. [state = ${state}]")
        christmasTreeShadowUpdater.updateTreeState(state)

        val responseText = "Changed ${changedFragments.joinToString(separator = ", and ")}.".toLowerCase()

        return SpeechletResponse.newTellResponse(getPlainTextOutputSpeech(responseText), getSimpleCard("Changed tree!", responseText))
    }

    companion object {
        private val christmasTreeShadowUpdater = ChristmasTreeShadowUpdater()
        private val LOG = LoggerFactory.getLogger(ChangeIntentHandler::class.java)
    }

}
