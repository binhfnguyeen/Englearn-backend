DROP TABLE IF EXISTS spring_ai_chat_memory;

CREATE TABLE spring_ai_chat_memory (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    conversation_id VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    type ENUM('USER', 'ASSISTANT', 'SYSTEM', 'TOOL') NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
