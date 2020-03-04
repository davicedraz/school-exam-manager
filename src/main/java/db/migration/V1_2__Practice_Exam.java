package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V1_2__Practice_Exam extends BaseJavaMigration {

    public void migrate(Context context) {
        seedExamForStudent1(context);
        seedExamForStudent2(context);
        seedExamForStudent3(context);
    }

    private void seedExamForStudent1(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO practice_exam (id, date, grade, student_number) " +
                        "VALUES(hibernate_sequence.nextval, '2020-03-26 15:00:00.000', '9ANO', 1)");
    }

    private void seedExamForStudent2(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO practice_exam (id, date, grade, student_number) " +
                        "VALUES(hibernate_sequence.nextval, '2020-03-26 15:00:00.000', '9ANO', 2)");
    }

    private void seedExamForStudent3(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO practice_exam (id, date, grade, student_number) " +
                        "VALUES(hibernate_sequence.nextval, '2020-03-26 15:00:00.000', '9ANO', 3)");
    }

}
