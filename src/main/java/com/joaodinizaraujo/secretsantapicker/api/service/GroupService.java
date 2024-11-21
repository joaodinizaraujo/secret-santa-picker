package com.joaodinizaraujo.secretsantapicker.api.service;

import com.joaodinizaraujo.secretsantapicker.api.exception.RegisterNotFoundException;
import com.joaodinizaraujo.secretsantapicker.api.model.Group;
import com.joaodinizaraujo.secretsantapicker.api.model.User;
import com.joaodinizaraujo.secretsantapicker.api.repository.GroupRepository;
import com.joaodinizaraujo.secretsantapicker.api.repository.UserRepository;
import com.joaodinizaraujo.secretsantapicker.api.util.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public Group getById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty())
            throw new RegisterNotFoundException("Group with ID " + id + " not found.");

        return group.get();
    }

    public Group insert(Group group) {
        group.setName(group.getName().strip().toUpperCase());
        group.setDescription(group.getDescription().strip());
        return groupRepository.save(group);
    }

    public Group updatePartially(Long id, Map<String, Object> updates) throws RuntimeException {
        Group existingGroup = getById(id);

        updates.forEach((key, value) -> {
            try {
                switch (key) {
                    case "name" -> existingGroup.setName((String) value);
                    case "description" -> existingGroup.setDescription((String) value);
                    case "pictureUrl" -> existingGroup.setPictureUrl((String) value);
                    default -> throw new IllegalArgumentException("Field " + key + " is not updatable.");
                }
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Invalid value for field " + key + ": " + e.getMessage(), e);
            }
        });

        Map<String, String> errors = ControllerUtils.verifyObject(existingGroup, new ArrayList<>(updates.keySet()));
        if (!errors.isEmpty())
            throw new IllegalArgumentException("Validation errors: " + errors);

        Group updatedGroup = insert(existingGroup);
        if (updatedGroup == null)
            throw new RuntimeException("Error during group update.");

        return updatedGroup;
    }

    public Group generateMatches(Long id) throws IllegalArgumentException {
        Group group = getById(id);

        List<User> members = group.getMembers();
        if (members.isEmpty())
            throw new RegisterNotFoundException("Group has no members to generate matches.");

        group.generateRandomMatches();
        return insert(group);
    }

    public Group addUserToGroup(Long id, User user) throws IllegalArgumentException {
        Group group = getById(id);

        Optional<User> existingUser = group.getMembers().stream()
                .filter(u -> u.getEmail().equals(user.getEmail()))
                .findFirst();
        if (existingUser.isPresent())
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already in group.");

        group.getMembers().add(user);
        return insert(group);
    }

    public User getReceiverByGiver(Long id, String giverEmail) throws IllegalArgumentException {
        Group group = getById(id);

        Optional<User> receiver = group.getMatches().stream()
                .map(m -> m.get(giverEmail))
                .findFirst();

        if (receiver.isEmpty())
            throw new RegisterNotFoundException("The provided email does not match with a receiver.");

        return receiver.get();
    }
}
