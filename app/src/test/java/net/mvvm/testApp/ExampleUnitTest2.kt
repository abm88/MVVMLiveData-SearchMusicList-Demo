package net.mvvm.testApp

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.regex.Pattern


@RunWith(MockitoJUnitRunner::class)
public class ExampleUnitTest2 {

    @Mock
    private lateinit var mockedList : MutableList<String>

    @Spy
    private var spiedList : MutableList<String> = mutableListOf()

    @Mock
    private lateinit var captorList : MutableList<String>



    @Test
    fun whenNotUseMockAnnotation_thenCorrect(){

        mockedList.add("one")
        Mockito.verify(mockedList).add("one")
        assertEquals(0 , mockedList.size)

        Mockito.`when`(mockedList.get(0)).thenReturn("one")

        assertEquals("one" , mockedList.get(0))

    }


    @Test
    fun whenNotUseSpyAnnotation_thenCorrect(){

        spiedList.add("one")
        spiedList.add("two")

        Mockito.verify(spiedList).add("one")
        Mockito.verify(spiedList).add("two")

        assertEquals(2 , spiedList.size)

        Mockito.`when`(spiedList.get(0)).
            thenReturn("three").
            thenReturn("fore").thenAnswer {
            it.callRealMethod()
        }

        assertEquals("three" , spiedList.get(0))

        Mockito.doReturn(100).`when`(spiedList).size
        assertEquals(100 , spiedList.size)

    }


    @Test
    fun whenNoCaptureAnnotation_thenCorrect(){

        val args : ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)

        captorList.add("one")

        Mockito.verify(captorList).add(args.capture())


        val regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"

        val pattern = Pattern.compile(regex)

        val matcher = pattern.matcher("myemail..asd@gmail.com")

        assertEquals(true  ,matcher.matches())



        assertEquals("one" , args.value)

    }


    @Test
    fun whenCaptureThenCorrect(){



    }



    @Test
    fun whenAnswerThenTrue(){


    }

}