package org.mycompany.repository;

import org.mycompany.model.JiraIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface JiraIssueRepository extends JpaRepository<JiraIssue, UUID>{

}
