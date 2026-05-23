package com.github.gerdreiss.springcore.demo

import com.github.gerdreiss.springcore.demo.common.CricketCoach
import com.github.gerdreiss.springcore.demo.rest.DemoController
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DemoControllerTest {
    @Test
    fun getDailyWorkout() {
        val controller = DemoController(CricketCoach())
        assertEquals("Practice fast bowling for 15 minutes", controller.getDailyWorkout())
    }

}