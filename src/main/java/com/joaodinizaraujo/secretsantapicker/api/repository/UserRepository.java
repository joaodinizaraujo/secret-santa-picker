package com.joaodinizaraujo.secretsantapicker.api.repository;

import com.joaodinizaraujo.secretsantapicker.api.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, String> {
}
