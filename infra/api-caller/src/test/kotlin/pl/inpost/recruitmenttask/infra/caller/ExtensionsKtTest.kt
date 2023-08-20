package pl.inpost.recruitmenttask.infra.caller

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExtensionsKtTest {

    @Test
    fun toSuccessResultDomain_returnSuccessDomain() {
        val result = "String".toSuccessResultDomain()

        assertTrue(result.isSuccess())
    }

    @Test
    fun toSuccessResultDomain_returnSuccessDomainData() {
        val result = "String".toSuccessResultDomain()

        assertEquals("String", result.successOrNull())
    }

    @Test
    fun toErrorResultDomain_returnErrorDomain() {
        val result = "String".toErrorResultDomain()

        assertTrue(result.isError())
    }

    @Test
    fun toErrorResultDomain_returnErrorDomainData() {
        val result = "String".toErrorResultDomain()

        assertEquals("String", result.errorOrNull())
    }
}
