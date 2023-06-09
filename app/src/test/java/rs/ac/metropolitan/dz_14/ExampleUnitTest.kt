package rs.ac.metropolitan.dz_14

import junit.framework.TestCase
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import rs.ac.metropolitan.dz_14.business.getPriority
import rs.ac.metropolitan.dz_14.data.Activity
import rs.ac.metropolitan.dz_14.data.Company
import rs.ac.metropolitan.dz_14.data.Traffic

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    val testingValues =
        listOf<Company>(
            Company(
                "false",
                Activity.IT,
                "asd",
                "123",
                "test",
                "com1",
                Traffic.LOW
            ),Company(
                "false",
                Activity.IT,
                "asd",
                "123",
                "test",
                "com2",
                Traffic.LOW
            ),Company(
                "false",
                Activity.MANAGEMENT,
                "asd",
                "123",
                "test",
                "com3",
                Traffic.LOW
            )

        )

    @Test
    fun testSorting() = runTest{
        val itCompany = testingValues[0]
        val array = flowOf(testingValues).getPriority(Activity.IT)
        val firstCom = array.first()
        assertEquals(itCompany, firstCom.first())
        TestCase.assertEquals(2, array.flatMapConcat { it.asFlow() }.count())
    }
}