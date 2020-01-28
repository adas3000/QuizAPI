package com.quiz.api;

import com.quiz.api.controller.QueueController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueueTest {

    @Mock
    private QueueController queueController;

    @Test
    public void pushClientToQueueTest(){
        when(queueController.getAllQueue()).thenReturn(new ResponseEntity<>(List.of("device1","device2"), HttpStatus.OK));



    }

}
