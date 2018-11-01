package com.joshmlwood.kotlinJpaTest

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class JpaTest {

    @Autowired
    val wrapperRepository: WrapperRepository? = null
    @Autowired
    val nestedRepository: NestedRepository? = null

    @Test
    fun `save wrapper then modify nested and save again`() {
        val nested = Nested(null, "data")
        val wrapper = Wrapper(null, "data", listOf(nested))
        val saved = wrapperRepository!!.save(wrapper)
        val nestedNew = Nested(saved.nested!![0].id, "someNewData")
        val wrapperNew = Wrapper(saved.id, saved.data, listOf(nestedNew))
        val saved2 = wrapperRepository!!.save(wrapperNew)
        println(saved2)
    }
}