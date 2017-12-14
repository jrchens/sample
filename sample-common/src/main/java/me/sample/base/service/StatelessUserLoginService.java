package me.sample.base.service;

import me.sample.base.domain.StatelessUser;

public interface StatelessUserLoginService {
    StatelessUser login(String username,String password);
}
