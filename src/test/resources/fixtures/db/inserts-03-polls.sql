INSERT INTO polldemo.poll (id, subject, creation_time, active) VALUES ('poll-01', 'This is a sample Poll', '2019-01-16 00:00:00.000000', TRUE);
INSERT INTO polldemo.poll (id, subject, creation_time, active) VALUES ('poll-02', 'This is another poll with no proposals', '2019-01-16 00:00:00.000000', TRUE);
INSERT INTO polldemo.poll (id, subject, creation_time, active) VALUES ('poll-03', 'This is a closed', '2018-01-16 00:00:00.000000', FALSE);

INSERT INTO polldemo.proposal (id, poll_id, index, subject) VALUES ('proposal-01', 'poll-01', 1, 'A proposal to be voted #1');
INSERT INTO polldemo.proposal (id, poll_id, index, subject) VALUES ('proposal-02', 'poll-01', 2, 'A proposal to be voted #1');
INSERT INTO polldemo.proposal (id, poll_id, index, subject) VALUES ('proposal-03', 'poll-03', 1, 'A proposal');

INSERT INTO polldemo.vote (id, proposal_id, voter_id) VALUES ('vote-01', 'proposal-01', '@alex');
INSERT INTO polldemo.vote (id, proposal_id, voter_id) VALUES ('vote-02', 'proposal-01', '@rafa');
INSERT INTO polldemo.vote (id, proposal_id, voter_id) VALUES ('vote-03', 'proposal-01', '@pablo');
INSERT INTO polldemo.vote (id, proposal_id, voter_id) VALUES ('vote-04', 'proposal-02', '@oscar');