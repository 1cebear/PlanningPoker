package ru.planningpoker.controller.storyset;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.planningpoker.model.StorySet;

import java.net.URI;
import java.util.List;

/**
 * Created by Icebear on 10.08.2017.
 */
@RestController
@RequestMapping(value = StorySetRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class StorySetRestController extends AbstractStorySetController {

    static final String REST_URL = "/rest/storyset";

    @Override
    @GetMapping
    public List<StorySet> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public StorySet get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody StorySet storySet, @PathVariable("id") int id) {
        super.update(storySet, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StorySet> createWithLocation(@RequestBody StorySet storySet) {
        StorySet created = super.create(storySet);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
