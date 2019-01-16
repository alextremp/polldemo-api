CREATE TABLE polldemo.poll
(
    id VARCHAR(40) PRIMARY KEY NOT NULL,
    subject VARCHAR(500) NOT NULL,
    creation_time TIMESTAMP NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE polldemo.proposal
(
    id VARCHAR(40) PRIMARY KEY NOT NULL,
    poll_id VARCHAR(40) NOT NULL,
    index INTEGER NOT NULL,
    subject VARCHAR(500) NOT NULL,
    CONSTRAINT proposal_poll_id_fk FOREIGN KEY (poll_id) REFERENCES poll (id)
);
CREATE UNIQUE INDEX proposal_poll_id_index_uindex ON polldemo.proposal (poll_id, index);

CREATE TABLE polldemo.vote
(
    id VARCHAR(40) PRIMARY KEY NOT NULL,
    proposal_id VARCHAR(40) NOT NULL,
    voter_id VARCHAR(40) NOT NULL,
    CONSTRAINT vote_proposal_id_fk FOREIGN KEY (proposal_id) REFERENCES proposal (id)
);
CREATE UNIQUE INDEX vote_proposal_id_voter_id_uindex ON polldemo.vote (proposal_id, voter_id);
