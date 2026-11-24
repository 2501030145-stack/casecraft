-- PostgreSQL production reference schema. Hibernate can create/update these tables on first run.
CREATE TABLE IF NOT EXISTS case_files (id UUID PRIMARY KEY, title VARCHAR(255) NOT NULL, summary VARCHAR(3000), status VARCHAR(40) NOT NULL DEFAULT 'OPEN', location VARCHAR(255), created_at TIMESTAMP, updated_at TIMESTAMP);
CREATE TABLE IF NOT EXISTS people (id UUID PRIMARY KEY, person_type VARCHAR(31) NOT NULL, case_id UUID NOT NULL REFERENCES case_files(id) ON DELETE CASCADE, name VARCHAR(255) NOT NULL, age VARCHAR(40), occupation VARCHAR(255), notes VARCHAR(1500));
CREATE INDEX IF NOT EXISTS idx_people_case ON people(case_id);
CREATE TABLE IF NOT EXISTS evidence (id UUID PRIMARY KEY, evidence_type VARCHAR(31) NOT NULL, case_id UUID NOT NULL REFERENCES case_files(id) ON DELETE CASCADE, title VARCHAR(255) NOT NULL, description VARCHAR(2500), category VARCHAR(100), reference_url VARCHAR(1000), collected_at TIMESTAMP, verified BOOLEAN NOT NULL DEFAULT FALSE);
CREATE INDEX IF NOT EXISTS idx_evidence_case ON evidence(case_id);
CREATE TABLE IF NOT EXISTS clues (id UUID PRIMARY KEY, case_id UUID NOT NULL REFERENCES case_files(id) ON DELETE CASCADE, title VARCHAR(255) NOT NULL, detail VARCHAR(2000), importance VARCHAR(20), x DOUBLE PRECISION NOT NULL DEFAULT 50, y DOUBLE PRECISION NOT NULL DEFAULT 50);
CREATE INDEX IF NOT EXISTS idx_clues_case ON clues(case_id);
CREATE TABLE IF NOT EXISTS clue_connections (id UUID PRIMARY KEY, source_clue_id UUID NOT NULL REFERENCES clues(id) ON DELETE CASCADE, target_clue_id UUID NOT NULL REFERENCES clues(id) ON DELETE CASCADE, relationship VARCHAR(100) NOT NULL DEFAULT 'RELATED_TO', CONSTRAINT uq_clue_connection UNIQUE(source_clue_id,target_clue_id), CONSTRAINT chk_clue_not_self CHECK(source_clue_id <> target_clue_id));
CREATE TABLE IF NOT EXISTS statements (id UUID PRIMARY KEY, case_id UUID NOT NULL REFERENCES case_files(id) ON DELETE CASCADE, speaker VARCHAR(255), content VARCHAR(5000), recorded_at TIMESTAMP);
CREATE INDEX IF NOT EXISTS idx_statements_case ON statements(case_id);
CREATE TABLE IF NOT EXISTS timeline_events (id UUID PRIMARY KEY, case_id UUID NOT NULL REFERENCES case_files(id) ON DELETE CASCADE, occurred_at TIMESTAMP, location VARCHAR(255), description VARCHAR(2000));
CREATE INDEX IF NOT EXISTS idx_events_case_time ON timeline_events(case_id,occurred_at);
