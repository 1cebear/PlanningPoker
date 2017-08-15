package ru.planningpoker.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.planningpoker.model.Vote;

import java.util.List;

/**
 * Created by Icebear on 13.08.2017.
 */
@Transactional(readOnly = true)
public interface CrudVoteRepository  extends JpaRepository<Vote, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.story.id=:storyId AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("storyId") int storyId, @Param("userId") int userId);

    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user u JOIN FETCH v.story s WHERE (v.story.id=:storyId AND v.user.id=:userId)")
    List<Vote> getAll(@Param("storyId") int storyId, @Param("userId") int userId );

    @Query("SELECT v FROM Vote v JOIN FETCH v.user u JOIN FETCH v.story s WHERE (v.story.storySet.id=:setId)")
    List<Vote> getAllForSet(@Param("setId") int setId);
}
