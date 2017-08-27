package ru.planningpoker.controller.vote;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.planningpoker.ActiveStory;
import ru.planningpoker.ActiveUser;
import ru.planningpoker.model.Vote;

import java.net.URI;
import java.util.List;

/**
 * Created by Icebear on 13.08.2017.
 */
@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {

    static final String REST_URL = "/rest/votes";

    @GetMapping
    public List<Vote> getAll() {
        int storyId = ActiveStory.id();
        int userId = ActiveUser.id();
        return super.getAll(storyId, userId);
    }

    @GetMapping("/{id}")
    public Vote get(int id) {
        int storyId = ActiveStory.id();
        int userId = ActiveUser.id();
        return super.get(id, storyId, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(int id) {
        int storyId = ActiveStory.id();
        int userId = ActiveUser.id();
        super.delete(id, storyId, userId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(Vote vote, int id) {
        int storyId = ActiveStory.id();
        int userId = ActiveUser.id();
        super.update(vote, id, storyId, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote) {
        int storyId = ActiveStory.id();
        int userId = ActiveUser.id();
        Vote created = super.create(vote, storyId, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/foruserandstory/{storyId}/{userId}")
    public List<Vote> getAll(@PathVariable("storyId") int storyId, @PathVariable("userId") int userId) {
        return super.getAll(storyId, userId);
    }

    @GetMapping("/foruserandstory/{storyId}/{userId}/{id}")
    public Vote get(@PathVariable("id") int id, @PathVariable("storyId") int storyId, @PathVariable("userId") int userId) {
        return super.get(id, storyId, userId);
    }

    @GetMapping("/forset/{setId}")
    public List<Vote> getAllForSet(@PathVariable("setId") int setId) {
        return super.getAllForSet(setId);
    }

    @DeleteMapping("/foruserandstory/{storyId}/{userId}/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("storyId") int storyId, @PathVariable("userId") int userId) {
        super.delete(id, storyId, userId);
    }

    @PutMapping(value = "/foruserandstory/{storyId}/{userId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Vote vote, @PathVariable("id") int id, @PathVariable("storyId") int storyId, @PathVariable("userId") int userId) {
        super.update(vote, id, storyId, userId);
    }

    @PostMapping(value = "/foruserandstory/{storyId}/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote, @PathVariable("storyId") int storyId, @PathVariable("userId") int userId) {
        Vote created = super.create(vote, storyId, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
