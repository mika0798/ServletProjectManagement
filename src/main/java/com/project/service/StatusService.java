package com.project.service;

import com.project.entity.Status;
import com.project.repository.StatusRepository;

import java.util.List;

public class StatusService {
    private StatusRepository statusRepository;

    public StatusService() {
        statusRepository = new StatusRepository();
    }

    public List<Status> getAllStatus() {
        return statusRepository.getAllStatus();
    }
}
