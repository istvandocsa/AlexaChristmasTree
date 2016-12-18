package org.kecskusz.alexachristmastree.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by NetHunter on 2016. 12. 18..
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class ChristmasTreeState(val desired: DesiredChristmasTreeState)