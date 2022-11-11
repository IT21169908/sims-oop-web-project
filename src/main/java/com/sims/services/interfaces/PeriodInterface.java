package com.sims.services.interfaces;

import java.util.ArrayList;

import com.sims.models.Period;

public interface PeriodInterface extends CrudUtils<Period, Integer> {

   ArrayList<Period> allByTeacher(int teacher_id) throws Exception;

}
