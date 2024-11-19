package com.joaodinizaraujo.secretsantapicker.api.repository;

import com.joaodinizaraujo.secretsantapicker.api.model.Group;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GroupRepository extends Neo4jRepository<Group, Long> {
}
