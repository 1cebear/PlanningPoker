package ru.planningpoker.controller.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.planningpoker.ActiveUser;
import ru.planningpoker.model.Role;
import ru.planningpoker.model.User;
import ru.planningpoker.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Set;

/**
 * Created by Icebear on 22.07.2017.
 */
@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController extends AbstractUserController {

    static final String REST_URL = "/rest/users";

    @Override
    @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user, @PathVariable("id") int id) {
        super.update(user, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        User created = super.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/active")
    public User getActiveUser() {
        return super.get(ActiveUser.id());
    }

    @PutMapping(value = "/setadmins", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setAdmins(@RequestBody int[] ids) {
        for (int id : ids) {
            User user = super.get(id);
            Set<Role> roles = user.getRoles();
            roles.add(Role.ROLE_ADMIN);
            user.setRoles(roles);
            super.update(user, id);
        }
    }

    @PutMapping(value = "/setusers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void setUsers(@RequestBody int[] ids) {
        for (int id : ids) {
            User user = super.get(id);
            Set<Role> roles = user.getRoles();
            if (roles.contains(Role.ROLE_ADMIN)) {
                roles.remove(Role.ROLE_ADMIN);
            }
            user.setRoles(roles);
            super.update(user, id);
        }
    }
}
