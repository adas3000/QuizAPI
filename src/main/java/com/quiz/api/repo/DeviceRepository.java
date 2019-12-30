package com.quiz.api.repo;

import com.quiz.api.model.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DeviceRepository extends CrudRepository<Device,Long> {
    Device findBySerialNumber(@Param(("serialNumber"))String serialNumber);
}
