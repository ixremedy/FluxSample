package com.forthreal

import com.forthreal.classes.ListFiller
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class AppMain {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = SpringApplication(AppMain::class.java)
            val factory = app.run(*args)

            ListFiller.start()
        }
    }
}