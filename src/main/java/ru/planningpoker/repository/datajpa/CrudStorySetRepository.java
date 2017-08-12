package ru.planningpoker.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.planningpoker.model.StorySet;

import java.util.List;

/**
 * Created by Icebear on 06.08.2017.
 */
@Transactional(readOnly = true)
public interface CrudStorySetRepository extends JpaRepository<StorySet, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM StorySet s WHERE s.id=:id")
    int delete(@Param("id") int id);


    @Transactional
    StorySet save(StorySet storySet);


    StorySet findOne(Integer id);


    List<StorySet> findAll(Sort sort);

}
