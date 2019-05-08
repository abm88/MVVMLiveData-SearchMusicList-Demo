package net.mvvm.testApp.utils

import android.os.Handler

class TestClass {

    fun startTimer( handler : Handler ,
                       stuffToDo : () -> Unit ,
                       stopTimeInSeconds : Int ,
                       timePassed : Int ,
                       interval : Long){

        handler.postDelayed( {
            stuffToDo.invoke()
            if (timePassed < stopTimeInSeconds){
                startTimer(handler ,
                    stuffToDo ,
                    stopTimeInSeconds ,
                    timePassed + 1 ,
                    interval)
            }

        } , interval)

    }

}