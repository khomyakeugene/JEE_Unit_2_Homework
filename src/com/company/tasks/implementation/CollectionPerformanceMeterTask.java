package com.company.tasks.implementation;

import com.company.tasks.implementation.collections.CollectionPerformanceMeter;
import com.company.tasks.interfaces.Task;
import com.company.utils.MethodDescriptor;

import java.util.AbstractCollection;

/**
 * Created by Yevgen on 26.03.2016 as a part of the project "JEE_Unit_2_Homework".
 */
public class CollectionPerformanceMeterTask implements Task<Long> {
    public static final int ATTEMPT_COUNT = 100;
    public static final int COLLECTION_SIZE_100000 = 100000;

    private CollectionPerformanceMeter collectionPerformanceMeter;

    private AbstractCollection<Integer> collection;
    private MethodDescriptor methodDescriptor;
    private Long result;

    public CollectionPerformanceMeterTask(AbstractCollection<Integer> collection, MethodDescriptor methodDescriptor) {
        this.collection = collection;
        this.methodDescriptor = methodDescriptor;

        collectionPerformanceMeter = new CollectionPerformanceMeter(COLLECTION_SIZE_100000, ATTEMPT_COUNT);
    }

    @Override
    public void execute() {
        result = collectionPerformanceMeter.measurePerformance(collection, methodDescriptor);
   }

    @Override
    public Long getResult() {
        return result;
    }
}
