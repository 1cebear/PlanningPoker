package ru.planningpoker.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.planningpoker.model.Story;

import java.util.List;

/**
 * Created by Icebear on 09.08.2017.
 */
@Transactional(readOnly = true)
public interface CrudStoryRepository extends JpaRepository<Story, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Story s WHERE s.id=:id AND s.storySet.id=:setId")
    int delete(@Param("id") int id, @Param("setId") int setId);

    Story save(Story story);

    @Query("SELECT s FROM Story s WHERE s.storySet.id=:setId")
    List<Story> getAll(@Param("setId") int setId);
}

