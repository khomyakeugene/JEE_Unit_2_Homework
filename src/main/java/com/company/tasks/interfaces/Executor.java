package com.company.tasks.interfaces;

import java.util.List;

/**
 * Created by Yevgen on 26.03.2016 as a part of the project "JEE_Unit_2_Homework".
 */
public interface Executor<E> {
    // Add task to execution. The result will be available through the method getValidResult(),
    // Should throw an exception in case if the method execute() was already called
    void addTask(Task<? extends E> task) throws Exception;

    // Add task to execution and add result validator. The result of the task should be stored into ValidResults if
    // validator.isValid() returns true for such result.
    // Should throw an exception in case if the method execute() was already called
    void addTask(Task<? extends E>  task, Validator<E> validator) throws Exception;

    // Execute all added tasks
    void execute();

    // Get valid results. Should throw an exception if the method execute() has not been called
    List<E> getValidResults() throws Exception;

    // Get invalid results. Should throw an exception if the method execute() has not been called
    List<E> getInvalidResults() throws Exception;
}
