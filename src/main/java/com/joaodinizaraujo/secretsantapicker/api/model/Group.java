package com.joaodinizaraujo.secretsantapicker.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Node("Group")
public class Group {
    @Id
    @GeneratedValue
    @Schema(description = "Group generated ID", example = "1")
    private Long id;

    @NotNull(message = "The name cannot be null")
    @Size(min = 3, max = 150, message = "The name should have length between 3 and 150")
    @Schema(description = "Name of the group", example = "SalaF")
    private String name;

    @NotNull(message = "The description cannot be null")
    @Size(min = 3, max = 300, message = "The description should have length between 3 and 300")
    @Schema(description = "Description of the group", example = "A good class.")
    private String description;

    @Size(min = 10, message = "The picture URL must have at least length 10")
    @Schema(description = "Group picture", example = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnGUKEObKP7a2L1helo1SZY1HLowd4ACTvqw&s")
    private String pictureUrl;

    @Schema(description = "The date and time that the group was created", example = "2024-11-18 21:34:19")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Relationship(value = "BELONGS_TO")
    @Schema(description = "A list of users representing the members of the group", example = "['João Victor', 'Davi Brito']")
    private List<User> members;

    @Schema(description = "A list of maps representing which user the user will have to buy the gift for",
            example = "[{'giver': 'João Victor', 'receiver': 'Davi Brito'}]")
    private List<Map<String, User>> matches;

    public void generateRandomMatches() {
        Collections.shuffle(this.members);

        List<Map<String, User>> matches = new ArrayList<>();
        for (int i = 0; i < this.members.size(); i++) {
            User giver = this.members.get(i);
            User receiver = this.members.get((i + 1) % this.members.size());

            Map<String, User> match = new HashMap<>();
            match.put(giver.getEmail(), receiver);
            matches.add(match);
        }

        this.matches = matches;
    }
}