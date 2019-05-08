package net.mvvm.testApp.utils;

import java.util.AbstractList;

public class MyList extends AbstractList<String> {
    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 1;
    }


    public int getUniqueId(){
        return 11;
    }


    public void testing(int arg){
        // do nothing
    }


}
