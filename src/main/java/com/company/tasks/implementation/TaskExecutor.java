package com.company.tasks.implementation;

import com.company.tasks.interfaces.Executor;
import com.company.tasks.interfaces.Task;
import com.company.tasks.interfaces.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevgen on 26.03.2016 as a part of the project "JEE_Unit_2_Homework".
 */
class TaskExecutor<E> implements Executor<E> {
    private static final String EXECUTE_TASK_PATTERN = "Execute task <%s> (task N %d) ...";
    private static final String METHOD_EXECUTE_HAS_ALREADY_BEEN_CALLED = "Method <execute> has already been called!";
    private static final String METHOD_EXECUTE_HAS_NOT_BEEN_CALLED = "Method <execute> has not been called!";

    private boolean executeMethodHasBeenCalled = false;
    private List<Task<? extends E>> tasks = new ArrayList<>();
    private ArrayList<Validator<E>> validators = new ArrayList<>();
    private ArrayList<E> validResults = new ArrayList<>();
    private ArrayList<E> invalidResults = new ArrayList<>();

    private void checkIfExecuteMethodHasBeenCalled() throws Exception  {
        if (!executeMethodHasBeenCalled) {
            throw new Exception(METHOD_EXECUTE_HAS_NOT_BEEN_CALLED);
        }
    }

    private void checkIfExecuteMethodHasNotBeenCalled()  throws Exception {
        if (executeMethodHasBeenCalled) {
            throw new Exception(METHOD_EXECUTE_HAS_ALREADY_BEEN_CALLED);
        }
    }

    @Override
    public void addTask(Task<? extends E> task, Validator<E> validator) throws Exception {
        checkIfExecuteMethodHasNotBeenCalled();

        tasks.add(task);
        validators.add(validator);
    }

    @Override
    public void addTask(Task<? extends E> task) throws Exception {
        addTask(task, null);
    }

    private void executeOneTask(int taskIndex) {
        Task<? extends E> task = tasks.get(taskIndex);

        System.out.println(String.format(EXECUTE_TASK_PATTERN, task.getClass().getName(), taskIndex));
        task.execute();
        E result = task.getResult();

        Validator<E> validator = validators.get(taskIndex);
        if (validator == null) {
            validResults.add(result);
        } else {
            if (validator.isValid(result)) {
                validResults.add(result);
            } else {
                invalidResults.add(result);
            }
        }
    }

    @Override
    public void execute() {
        executeMethodHasBeenCalled = true;

        for (int i = 0; i < tasks.size(); i++) {
            executeOneTask(i);
        }
    }

    @Override
    public List<E> getValidResults() throws Exception {
        checkIfExecuteMethodHasBeenCalled();

        return validResults;
    }

    @Override
    public List<E> getInvalidResults() throws Exception {
        checkIfExecuteMethodHasBeenCalled();

        return invalidResults;
    }
}
