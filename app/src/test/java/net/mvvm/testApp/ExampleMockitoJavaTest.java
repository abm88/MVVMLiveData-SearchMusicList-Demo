package net.mvvm.testApp;


import androidx.lifecycle.LiveData;
import net.mvvm.testApp.data.model.account.AuthenticateModel;
import net.mvvm.testApp.utils.MyList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class ExampleMockitoJavaTest {



    @Mock private List<String> mockList ;
    @Mock private LiveData<AuthenticateModel> mockData;
    @Spy private ArrayList<String> spylist = new ArrayList() ;
    @Mock private ArrayList<String> captorList ;
    @Captor private ArgumentCaptor<String> args ;


    @Mock
    MyList listMock ;


    @Mock
    private AuthenticateModel auth ;
    @Spy
    private List<AuthenticateModel> authList = new ArrayList<>();


    @Test
    public void whenNotUseMockAnnotation_thenCorrect(){


        mockList.add("one") ;
        Mockito.verify(mockList).add("one") ;
        assertEquals(0 , mockList.size()) ;


        Mockito.when(mockList.size()).thenReturn(100) ;
        assertEquals(mockList.size() , 100 );

    }


    @Test
    public void whenDataAvailableThenCorrect(){

        spylist.add("one") ;
        spylist.add("two") ;

        Mockito.verify(spylist).add("one") ;
        Mockito.verify(spylist).add("two") ;


        assertEquals(2 , spylist.size());
        Mockito.doReturn(100).when(spylist).size()  ;
//        Mockito.when(spylist.size()).thenReturn(100) ;
        assertEquals(100 , spylist.size());

    }


    @Test
    public void whenArgumentCaptorThenCorrect(){

//        ArgumentCaptor<String> args = ArgumentCaptor.forClass(String.class) ;
        captorList.add("one") ;
        Mockito.verify(captorList).add("one")  ;
        Mockito.verify(captorList).add(args.capture()) ;

        assertEquals("one" , args.getValue());
    }


    @Test
    public void whenInjectMockThenCorrect(){


        Mockito.when(auth.component1()).thenReturn("access token") ;
        Mockito.when(auth.component2()).thenReturn("token type") ;
        Mockito.when(auth.component3()).thenReturn("refresh token") ;
        Mockito.when(auth.component4()).thenReturn(1234) ;

        authList.add(auth) ;

        Mockito.verify(authList).add(auth) ;


        assertEquals(authList.get(0).component1() , auth.component1());


    }


    @Test
    public void whenSomeTestThenCorrect(){

//        Mockito.when(mockList.add(anyString())).thenReturn(false) ;
        Mockito.doReturn(false).when(mockList).add(anyString()) ;
        Boolean added = mockList.add("") ;
        assertEquals( added , false );
    }


    @Test(expected = IllegalStateException.class)
    public void whenSomeTestThenThrowExeption(){
//        Mockito.when(mockList.add(anyString())).thenThrow(IllegalStateException.class) ;
        Mockito.doThrow(IllegalStateException.class).when(mockList).add(anyString()) ;
        mockList.add("asd") ;
    }


    @Test(expected = NullPointerException.class)
    public void givenMockList_whenClearList_ThenThrowneXCEPTION(){
        Mockito.doThrow(NullPointerException.class).when(mockList).clear();

        mockList.clear();
    }


    @Test(expected = IllegalStateException.class)
    public void givenMockList_whenAddAnyString_thenThrowException(){

        Mockito.when(mockList.add(anyString()))
                .thenReturn(false).thenThrow(IllegalStateException.class) ;

        mockList.add("one") ;
        mockList.add("two") ;

    }



    @Spy
    MyList spyList2 ;
    @Test(expected = NullPointerException.class)
    public void givenListData_whenGetSize_thenThrownULLpOINTEReXCEPTION(){
//        MyList list = new MyList() ;

//        Mockito.when(spylist2.size()).thenThrow(NullPointerException.class) ;
        Mockito.doThrow(NullPointerException.class).when(spyList2).size() ;

        spyList2.size() ;
    }


    @Spy
    MyList spyList3 ;
    @Test
    public void givenListData_whenGetSize_thenCallRealMethod(){
        Mockito.when(spyList3.size()).thenCallRealMethod() ;
        assertEquals(spyList3.size() , 1);


    }


    @Test
    public void givenListData_whenVerfiy_thenCCorrect(){


        mockList.size() ;
        mockList.size() ;
        Mockito.verify(mockList , Mockito.times(2)).size() ;


        Mockito.verify(mockList , Mockito.never()).add(anyString());
        Mockito.verify(mockList , Mockito.never()).clear() ;

        mockList.add("one") ;

        Mockito.verify(mockList).add("one") ;

//        Mockito.verify(mockList).clear();

    }


    @Mock private MyList mocklist3 ;
    @Test
    public void givenListData_whenVerfiyFunctionOrder_thenCCorrect(){


        mocklist3.size();
        mocklist3.add("a parameter");
        mocklist3.clear();

        InOrder inOrder = Mockito.inOrder(mocklist3);
        inOrder.verify(mocklist3).size();
        inOrder.verify(mocklist3).add("a parameter");
        inOrder.verify(mocklist3).clear();

    }




    @Mock
    List mMockLIst1 ;
    @Mock
    Iterator<String> mList ;
    @Mock
    Comparable mComp1 ;
    @Test(expected = NullPointerException.class)
    public void testReturnMoreThanOneValue(){
        Mockito.when(mList.next())
                .thenReturn("first arg")
                .thenReturn("second arg");

        String tempValue = mList.next() + " " + mList.next() ;

        assertEquals("first arg second arg" , tempValue);

        when(mComp1.compareTo("first")).thenReturn(1) ;
        when(mComp1.compareTo("second")).thenReturn(-1) ;
        when(mComp1.compareTo(anyInt())).thenReturn(0) ;
        when(mComp1.compareTo(isA(Iterator.class))).thenReturn(-1) ;

        when(mMockLIst1.get(0)).thenThrow(NullPointerException.class) ;


        assertEquals(1 , mComp1.compareTo("first"));
        assertEquals(0 , mComp1.compareTo(21902938));
        assertEquals(-1 , mComp1.compareTo(mList));
        mMockLIst1.get(0) ;
        verify(mMockLIst1.get(0)) ;

    }


    @Spy
    LinkedList mSpyLinkedList ;
    @Test(expected = IndexOutOfBoundsException.class)
    public void spyTest(){


        when(mSpyLinkedList.get(0)).thenReturn("foo") ;

        // throws IndexOutOfBoundsException exception
        assertEquals("foo" , mSpyLinkedList.get(0));

        doReturn("foo").when(mSpyLinkedList).get(0) ;
        assertEquals("foo" , mSpyLinkedList.get(3));

    }


    @Mock
    MyList myVerifyList ;
    @Test
    public void verifyTest(){

        when(myVerifyList.getUniqueId()).thenReturn(213) ;
        myVerifyList.testing(12);
        myVerifyList.getUniqueId() ;
        myVerifyList.getUniqueId() ;

        verify(myVerifyList).testing(ArgumentMatchers.eq(12));
        verify(myVerifyList , times(2)).getUniqueId() ;
        verify(myVerifyList , never()).size() ;

        verifyNoMoreInteractions();


    }

}
