package org.kecskusz.alexachristmastree.iot

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.iotdata.AWSIotDataClient
import com.amazonaws.services.iotdata.model.UpdateThingShadowRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.kecskusz.alexachristmastree.config.ConfigurationProvider
import org.kecskusz.alexachristmastree.model.AWSIoTChristmasTreeState
import org.kecskusz.alexachristmastree.model.ChristmasTreeState
import org.kecskusz.alexachristmastree.model.DesiredChristmasTreeState
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer

/**
 * Created by NetHunter on 2016. 12. 18..
 */
class ChristmasTreeShadowUpdater {
    private val iotClient: AWSIotDataClient
    private val objectMapper: ObjectMapper

    constructor() {
        iotClient = AWSIotDataClient(BasicAWSCredentials(CONFIG.awsAccessKey, CONFIG.awsSecretKey))
        iotClient.setEndpoint(CONFIG.awsIoTEndpoint)
        iotClient.setRegion(Region.getRegion(Regions.fromName(CONFIG.awsRegion)))

        objectMapper = ObjectMapper().registerModule(KotlinModule())
    }

    fun updateTreeState(state: DesiredChristmasTreeState) {
        LOG.info("Updating tree state {}", state)
        val awsChristmasTreeState = AWSIoTChristmasTreeState(ChristmasTreeState(state))
        LOG.info("Tree state json {}", objectMapper.writeValueAsString(awsChristmasTreeState))
        val awsChristmasTreeStateByteBuffer = ByteBuffer.wrap(objectMapper.writeValueAsBytes(awsChristmasTreeState))
        val updateRequest = UpdateThingShadowRequest().withThingName("Anton").withPayload(awsChristmasTreeStateByteBuffer)
        iotClient.updateThingShadow(updateRequest)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ChristmasTreeShadowUpdater::class.java)
        private val CONFIG = ConfigurationProvider().getConfig()
    }
}