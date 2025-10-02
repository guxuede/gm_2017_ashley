package com.guxuede.gm.net.client.pack.utils;


import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public class PackQueue<T> extends ConcurrentLinkedQueue<T> {

    public void consumerAll(Consumer<T> consumer){
        T p;
        while((p=poll())!=null){
            consumer.accept(p);
        }
    }

}
