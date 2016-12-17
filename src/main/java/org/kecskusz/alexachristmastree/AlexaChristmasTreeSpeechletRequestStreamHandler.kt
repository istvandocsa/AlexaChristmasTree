/**
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

 * http://aws.amazon.com/apache2.0/

 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package org.kecskusz.alexachristmastree

import java.util.HashSet

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * This class could be the handler for an AWS Lambda function powering an Alexa Skills Kit
 * experience. To do this, simply set the handler field in the AWS Lambda console to
 * "org.kecskusz.christmastree.AlexaChristmasTreeSpeechletRequestStreamHandler" For this to work, you'll also need to build
 * this project using the `lambda-compile` Ant task and upload the resulting zip file to power
 * your function.
 */
class AlexaChristmasTreeSpeechletRequestStreamHandler : SpeechletRequestStreamHandler(AlexaChristmasTreeSpeechlet(), AlexaChristmasTreeSpeechletRequestStreamHandler.supportedApplicationIds) {
    init {
        LOG.info("AlexaChristmasTreeSpeechletRequestStreamHandler constructor called")
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(AlexaChristmasTreeSpeechletRequestStreamHandler::class.java)
        private val supportedApplicationIds = HashSet<String>()

        init {
            /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
            supportedApplicationIds.add("amzn1.ask.skill.4990256c-1174-4513-aebc-edaccec28b03")
        }
    }
}
