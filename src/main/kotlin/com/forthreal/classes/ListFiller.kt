package com.forthreal.classes

import org.apache.logging.log4j.LogManager
import java.security.SecureRandom
import java.util.TimerTask
import java.util.Timer
import java.util.concurrent.ConcurrentLinkedDeque

class ListFiller : TimerTask() {
    private val logger = LogManager.getLogger(ListFiller::class.java)

    companion object {
        val list = ConcurrentLinkedDeque<Int>()
        private val timer = Timer(true)

        fun start() {
            timer.scheduleAtFixedRate(ListFiller(), 0, 1_000)
        }
    }

    override fun run() {
        val random = SecureRandom()
        val v = random.nextInt(100_000)
        logger.info("Adding value $v")
        list.add(v)
    }
}