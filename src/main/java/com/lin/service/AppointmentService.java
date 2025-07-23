package com.lin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.entity.Appointment;

public interface AppointmentService extends IService<Appointment> {
    Appointment getOne( Appointment  appointment);
}
