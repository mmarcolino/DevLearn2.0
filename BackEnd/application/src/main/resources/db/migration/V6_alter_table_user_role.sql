ALTER TABLE user
ADD CONSTRAINT role_id
FOREIGN KEY (role_id) REFERENCES role;