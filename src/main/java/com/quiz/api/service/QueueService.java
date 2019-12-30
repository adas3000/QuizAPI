package com.quiz.api.service;

import com.quiz.api.model.Device;
import com.quiz.api.repo.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private DeviceRepository deviceRepository;

    public ResponseEntity<Object> pushClientToQueue(String serialNumber){

        Device d = deviceRepository.findBySerialNumber(serialNumber);

        if(d!=null){
            return new ResponseEntity<>("already_in_queue", HttpStatus.BAD_REQUEST);
        }

        d = new Device();
        d.setSerialNumber(serialNumber);

        deviceRepository.save(d);
        return new ResponseEntity<>("added_to_queue",HttpStatus.OK);
    }

    public ResponseEntity<Object> popClientFromQueue(String serialNumber){

        Device d = deviceRepository.findBySerialNumber(serialNumber);

        if(d==null){
            return new ResponseEntity<>("not_in_queue", HttpStatus.BAD_REQUEST);
        }

        deviceRepository.delete(d);
        return new ResponseEntity<>("deleted_from_queue",HttpStatus.OK);
    }





}
