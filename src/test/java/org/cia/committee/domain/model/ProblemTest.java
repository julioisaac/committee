package org.cia.committee.domain.problem.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class ProblemTest {

    @Test
    void givenAName_whenCreateAProblem_thenAProblemIsReturned() {
        String problemName = "Problem 1";
        Problem problem = new Problem(problemName);

        Assertions.assertNotNull(problem.getUUID());
        Assertions.assertEquals(ProblemState.OPENED, problem.getState());
        Assertions.assertEquals(problemName, problem.getName());
        Assertions.assertFalse(problem.canBeAssigned());
    }

    @Test
    void givenAComment_whenAddedInProblem_thenShouldBeReady() {
        String problemName = "Problem 2";

        Comment comment = new Comment("problem 2 comment");

        Problem problem = new Problem(problemName);
        problem.addComment(comment);

        Assertions.assertEquals(ProblemState.READY, problem.getState());
    }

    @Test
    void givenACommitteeReference_whenAProblemIsAssigned_thenCommitteeUuidIsNotNull() {
        String problemName = "Problem 3";
        UUID committeeUUID = UUID.randomUUID();

        Comment comment = new Comment("problem 3 comment");

        Problem problem = new Problem(problemName);
        problem.addComment(comment);
        problem.assign(committeeUUID);

        Assertions.assertNotNull(problem.getCommitteeUUID());
    }

    @Test
    void givenACommitteeReference_whenAProblemHasNoComment_thenItCannotBeAssigned() {
        String problemName = "Problem 4";
        UUID committeeUUID = UUID.randomUUID();

        Problem problem = new Problem(problemName);
        problem.assign(committeeUUID);

        Assertions.assertNull(problem.getCommitteeUUID());
    }

    @Test
    void givenAProblem_whenItClosed_thenTheStateShouldChangeToClosed() {
        String problemName = "Problem 5";
        UUID committeeUUID = UUID.randomUUID();

        Comment comment = new Comment("problem 5 comment");

        Problem problem = new Problem(problemName);
        problem.addComment(comment);
        problem.assign(committeeUUID);

        problem.close();

        Assertions.assertEquals(ProblemState.CLOSED, problem.getState());
    }
}