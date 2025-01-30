package me.abdallah_abdelfattah.DocAPIpointment.shared

// 2010-12-03T10:15:30
const val nowEpoch = 1291371330000

// 25/1/2011 20:00
const val futureDateEpoch = 1357063200000

fun mintToMillis(mints: Long): Long = mints * 60 * 1000
