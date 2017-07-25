package ru.planningpoker.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.planningpoker.model.User;

import java.util.List;

/**
 * Created by Icebear on 22.07.2017.
 */
@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);


    @Transactional
    User save(User user);


    User findOne(Integer id);


    List<User> findAll(Sort sort);

    User getByEmail(String email);
}
