package com.company.tasks.interfaces;

/**
 * Created by Yevgen on 26.03.2016 as a part of the project "JEE_Unit_2_Homework".
 */
public interface Validator<E> {
    // Validates delivered value
    boolean isValid(E result);
}
