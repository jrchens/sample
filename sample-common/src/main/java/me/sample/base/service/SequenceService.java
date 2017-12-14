package me.sample.base.service;

public interface SequenceService {
    Long next();
    public Long next(String code);
}
