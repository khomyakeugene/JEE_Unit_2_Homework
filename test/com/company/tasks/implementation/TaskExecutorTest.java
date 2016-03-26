package com.company.tasks.implementation;

import com.company.utils.MethodArgumentType;
import com.company.utils.MethodDescriptor;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by Yevgen on 26.03.2016 as a part of the project "JEE_Unit_2_Homework".
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskExecutorTest {
    public static final String ADD_METHOD_NAME = "add";
    public static final String REMOVE_METHOD_NAME = "remove";

    private static final double emptyDoubleArray[] = {};
    private static final double calcAverageDoubleValueTaskInputData[] = {123.66, 55.0, 729.8, 44.0};
    private static final double TransformCelsiusToFahrenheitTaskInputData = 23.0;

    private static TaskExecutor<Number> taskExecutor = new TaskExecutor<>();

    @BeforeClass
    public static void setUpClass() throws Exception {
        taskExecutor.addTask(new CalcAverageDoubleValueTask(calcAverageDoubleValueTaskInputData), new NotNullValidator<>());
        taskExecutor.addTask(new CalcAverageDoubleValueTask(emptyDoubleArray), new NotNullValidator<>());
        taskExecutor.addTask(new TransformCelsiusToFahrenheitTask(TransformCelsiusToFahrenheitTaskInputData));
        taskExecutor.addTask(new CollectionPerformanceMeterTask(new ArrayList<>(),
                new MethodDescriptor(ADD_METHOD_NAME, MethodArgumentType.ONE_OBJECT)));
        taskExecutor.addTask(new CollectionPerformanceMeterTask(new ArrayList<>(),
                new MethodDescriptor(REMOVE_METHOD_NAME, MethodArgumentType.ONE_INT)));
    }

    @Test (timeout = 1000, expected = Exception.class)
    public void test1_testGetValidResultsBeforeExecute() throws Exception {
        taskExecutor.getValidResults();
    }

    @Test (timeout = 1000, expected = Exception.class)
    public void test2_testGetInvalidResultsBeforeExecute() throws Exception {
        taskExecutor.getInvalidResults();
    }

    @Test (timeout = 1000)
    public void test3_testAddTaskBeforeExecute() throws Exception {
        taskExecutor.addTask(new CollectionPerformanceMeterTask(new HashSet<>(   ),
                new MethodDescriptor(ADD_METHOD_NAME, MethodArgumentType.ONE_OBJECT)));
    }

    @Test (timeout = 1000)
    public void test4_testExecute() throws Exception {
        taskExecutor.execute();
    }

    @Test (timeout = 1000, expected = Exception.class)
    public void test5_testAddTaskAfterExecute() throws Exception {
        taskExecutor.execute();

        taskExecutor.addTask(new CollectionPerformanceMeterTask(new TreeSet<>(),
                new MethodDescriptor(REMOVE_METHOD_NAME, MethodArgumentType.ONE_OBJECT)));
    }

    @Test (timeout = 1000)
    public void test6_testGetValidResultsAfterExecute() throws Exception {
    }

    @Test (timeout = 1000)
    public void test7_testGetInvalidResultsAfterExecute() throws Exception {
    }
}