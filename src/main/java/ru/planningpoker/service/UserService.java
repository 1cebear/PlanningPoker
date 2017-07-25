package ru.planningpoker.service;

import ru.planningpoker.model.User;
import ru.planningpoker.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Icebear on 22.07.2017.
 */
public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);
}
