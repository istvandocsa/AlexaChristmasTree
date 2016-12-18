package org.kecskusz.alexachristmastree.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AWSIoTChristmasTreeState(val state: ChristmasTreeState)