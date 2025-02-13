package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {

    private ExpenseService expenseService;

    @BeforeEach
    public void init() {
        expenseService = new ExpenseService();
    }

    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.INTERNAL, "project");

        // when	        // when
        ExpenseType expenseType = expenseService.getExpenseCodeByProjectTypeAndName(project);

        // then	        // then
        Assertions.assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE, expenseType);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project A");
        // when	        // when
        ExpenseType expenseType = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then	        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_A, expenseType);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project B");
        // when	        // when
        ExpenseType expenseType = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then	        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_B, expenseType);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project project = new Project(ProjectType.EXTERNAL, "Project");
        // when	        // when
        ExpenseType expenseType = expenseService.getExpenseCodeByProjectTypeAndName(project);
        // then	        // then
        Assertions.assertEquals(ExpenseType.OTHER_EXPENSE, expenseType);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // give
        Project project = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "Project");
        //when
        UnexpectedProjectTypeException unexpectedProjectTypeException = Assertions.assertThrows(UnexpectedProjectTypeException.class, ()->{ expenseService.getExpenseCodeByProjectTypeAndName(project);});
        //then
        Assertions.assertEquals("You enter invalid project type", unexpectedProjectTypeException.getMessage());
    }
}