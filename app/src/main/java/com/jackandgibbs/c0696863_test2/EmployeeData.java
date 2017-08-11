package com.jackandgibbs.c0696863_test2;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by jackandgibbs on 2017-08-10.
 */

public class EmployeeData extends RealmObject
{
    @PrimaryKey
    String employeeID;
    @Required
    String employeeName;
    @Required
    String employeeBirthDate;
    @Required
    String employeeSalary;
}
