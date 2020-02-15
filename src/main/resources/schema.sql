CREATE TABLE ISSUES (
  id uuid default random_uuid() primary key,
  issue_id VARCHAR(255),
  description VARCHAR(255),
  created VARCHAR(255),
  priority VARCHAR(255),
  updated VARCHAR(255),
  assignee VARCHAR(255),
  status VARCHAR(255) default 'P'
);