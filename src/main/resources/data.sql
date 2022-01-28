INSERT INTO users (id, username, email, password)
SELECT * FROM (SELECT random_uuid(), 'bbi', 'thisisatest@gmail.com', '$2a$12$1Mbcvr0LpSjyoy7OwP6W9erWViVoh5TUCpoRx9QtZ92Z6AH9yyT2i') AS tmp
WHERE NOT EXISTS (
        SELECT username FROM users WHERE username = 'bbi'
    ) LIMIT 1;