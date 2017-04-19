// IRemoteService.aidl
package com.soros.androidstudynotes.service;

// Declare any non-default types here with import statements
//import com.soros.androidstudynotes.service.Person;

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void threadSleep();

    int add(int a, int b);

    void getPerson(String name);
}
