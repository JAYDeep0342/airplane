INSERT INTO users (id, username, password, name)
VALUES (
  10,
  'superadmin',
  '$2a$10$Q7u6dZ1LZpW4vZ1Z5FzJrO9H4e0qJZyK5XcXJ0s5sY8wYw9XG',
  'System Super Admin'
);

INSERT INTO users_roles (user_id, role)
VALUES (10, 'SUPER_ADMIN');
