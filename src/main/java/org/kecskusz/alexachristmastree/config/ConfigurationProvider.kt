package org.kecskusz.alexachristmastree.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File


class ConfigurationProvider {

    fun getConfig(): Configuration {
        return config
    }


    companion object ConfigReader {
        private val mapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
        private val config = mapper.readValue(File("config.yaml"), Configuration::class.java)
    }

    data class Configuration(val awsAccessKey: String, val awsSecretKey: String, val awsRegion: String, val awsIoTEndpoint: String, val thingName: String, val supportedApplicationId: String)
}
