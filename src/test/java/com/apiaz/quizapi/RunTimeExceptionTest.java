package com.apiaz.quizapi;

import org.junit.jupiter.api.Test;

public class RunTimeExceptionTest {

    @Test
    public void ifNoExceptionThenOk(){

        String count1 = "123";
        String count2 = "123abc5";

        System.out.println(Integer.parseInt(count1));
        System.out.println(Integer.parseInt(count2));



    }


}
