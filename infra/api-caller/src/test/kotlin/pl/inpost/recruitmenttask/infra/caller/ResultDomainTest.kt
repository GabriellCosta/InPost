package pl.inpost.recruitmenttask.infra.caller

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ResultDomainTest {

    @Test
    fun successOrNull_success_returnData() {
        val parameter = ResultDomain.Success("String")

        val result = parameter.successOrNull()

        assertEquals("String", result)
    }

    @Test
    fun successOrNull_error_returnNull() {
        val parameter = ResultDomain.Error("String")

        val result = parameter.successOrNull()

        assertNull(result)
    }

    @Test
    fun errorOrNull_success_returnNull() {
        val parameter = ResultDomain.Success("String")

        val result = parameter.errorOrNull()

        assertNull(result)
    }

    @Test
    fun errorOrNull_error_returnData() {
        val parameter = ResultDomain.Error("String")

        val result = parameter.errorOrNull()

        assertEquals("String", result)
    }
}
