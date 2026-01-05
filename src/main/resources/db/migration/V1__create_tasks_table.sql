CREATE TABLE IF NOT EXISTS tasks (
  id BIGSERIAL PRIMARY KEY,

  title VARCHAR(120) NOT NULL,
  description VARCHAR(500),

  status VARCHAR(255) NOT NULL DEFAULT 'TODO',
  priority VARCHAR(255) NOT NULL DEFAULT 'MEDIUM',

  due_date DATE,

  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_tasks_status ON tasks(status);
CREATE INDEX IF NOT EXISTS idx_tasks_priority ON tasks(priority);
CREATE INDEX IF NOT EXISTS idx_tasks_due_date ON tasks(due_date);
