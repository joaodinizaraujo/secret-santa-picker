package com.joaodinizaraujo.secretsantapicker.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Node("User")
public class User {
    @Id
    @NotNull(message = "The email cannot be null")
    @Size(min = 5, max = 100, message = "The name should have length between 5 and 100")
    @Email(message = "Email is not valid",
            regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Schema(description = "User email", example = "jvdinizaraujo@gmail.com")
    private String email;

    @NotNull(message = "The password cannot be null")
    @Schema(description = "Encrypted user password")
    private String password;

    @NotNull(message = "The name cannot be null")
    @Size(min = 3, max = 150, message = "The name should have length between 3 and 150")
    @Schema(description = "Real user name", example = "João Victor Diniz Araujo")
    private String name;

    @Size(min = 10, message = "The picture url have at least length 10")
    @Schema(description = "User picture", example = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnGUKEObKP7a2L1helo1SZY1HLowd4ACTvqw&s")
    private String pictureUrl;

    @Schema(description = "The date that the user joined", example = "2024-11-18")
    private LocalDate joinDate = LocalDate.now();
}
