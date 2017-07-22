package ru.planningpoker.service;

import ru.planningpoker.model.User;

import java.util.List;

/**
 * Created by Icebear on 22.07.2017.
 */
public interface UserService {

    User save(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    void update(User user);

    void evictCache();

    void enable(int id, boolean enable);
}
