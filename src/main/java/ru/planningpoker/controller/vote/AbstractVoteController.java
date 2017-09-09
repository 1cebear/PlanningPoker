package ru.planningpoker.controller.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.planningpoker.model.Vote;
import ru.planningpoker.service.VoteService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.planningpoker.util.ValidationUtil.checkIdConsistent;
import static ru.planningpoker.util.ValidationUtil.checkNew;

/**
 * Created by Icebear on 13.08.2017.
 */
public class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public List<Vote> getAll(int storyId, int userId) {
        log.info("getAll");
        return service.getAll(storyId, userId);
    }

    public Vote get(int id, int storyId, int userId) {
        log.info("get {}", id);
        return service.get(id, storyId, userId);
    }

    public Vote create(Vote vote, int storyId, int userId) {
        log.info("create {}", vote);
        checkNew(vote);
        return service.save(vote, storyId, userId);
    }

    public void delete(int id, int storyId, int userId) {
        log.info("delete {}", id);
        service.delete(id, storyId, userId);
    }

    public void update(Vote vote, int id, int storyId, int userId) {
        log.info("update {}", vote);
        checkIdConsistent(vote, id);
        service.update(vote, storyId, userId);
    }

    public Map<String, Integer> getAllForSet(int setId) {
        log.info("getAll");
        List<Vote> list = service.getAllForSet(setId);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Vote v : list) {
            String vote = v.getVote();
            if (map.containsKey(vote)) {
                map.put(vote, map.get(vote));
            } else {
                map.put(vote, 1);
            }
        }
        return map;
    }
}
