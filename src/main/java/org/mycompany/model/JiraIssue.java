package org.mycompany.model;

import org.apache.camel.component.jpa.Consumed;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Set;
import java.util.UUID;



import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
@Entity
@Table(name = "issues")
@NamedQuery(name = "selectQuery", query = "select m from JiraIssue m where m.status = 'P'")
public class JiraIssue {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(name="issue_id", columnDefinition = "varchar(255)")
	private String issue_id;
	
	@Column(name="description", columnDefinition = "varchar(255)")
	private String description;
	
	@Column(name="created", columnDefinition = "varchar(255)")
	private String created;
	
	@Column(name="priority", columnDefinition = "varchar(255)")
	private String priority;
	
	@Column(name="updated", columnDefinition = "varchar(255)")
	private String updated;
	
	@Column(name="assignee", columnDefinition = "varchar(255)")
	private String assignee;
	
	@Column(name="status", columnDefinition = "varchar(255)")
	private String status = "P";
	
	
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

	public String getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(String issue_id) {
		this.issue_id = issue_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
    @Consumed
    public void afterConsume()
    {
        setStatus("D");
    }


	private void setStatus(String string) {
		this.status = string;
		
	}

	@Override
    public String toString() {
        return "JiraIssue {" +
        		"id=" + id + 
        		", issue_id='" + issue_id + '\'' +
        		", description='" + description + '\'' +
        		", created='" + created + '\'' + 
        		", prioroty='" + priority + '\'' +
        		", updated='" + updated + '\'' +
                ", assignee=" + assignee + '\'' + 
                ", status=" + status + '\'' +
                '}';
    }


}
