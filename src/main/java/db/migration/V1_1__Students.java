package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V1_1__Students extends BaseJavaMigration {

    public void migrate(Context context) {
        seedStudent1(context);
        seedStudent2(context);
        seedStudent3(context);
        seedStudent4(context);
        seedStudent5(context);
    }

    private void seedStudent1(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO students (id, name, registration_number, password, created_at, updated_at) VALUES(hibernate_sequence.nextval, 'João', 1, '$2a$10$JxNPBBRhpNUxUktn4GOFXetNXzb1lQUqm2RFNH1esKR7I333/vZFi', '2020-01-01 00:00:00.000', '2020-01-01 00:00:00.000')");
    }

    private void seedStudent2(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO students(id, name, registration_number, password, created_at, updated_at) VALUES(hibernate_sequence.nextval, 'Pedro', 2, '$2a$10$JxNPBBRhpNUxUktn4GOFXetNXzb1lQUqm2RFNH1esKR7I333/vZFi', '2020-01-01 00:00:00.000', '2020-01-01 00:00:00.000')");
    }

    private void seedStudent3(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO students(id, name, registration_number, password, created_at, updated_at) VALUES(hibernate_sequence.nextval, 'Maria', 3, '$2a$10$JxNPBBRhpNUxUktn4GOFXetNXzb1lQUqm2RFNH1esKR7I333/vZFi', '2020-01-01 00:00:00.000', '2020-01-01 00:00:00.000')");
    }

    private void seedStudent4(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO students(id, name, registration_number, password, created_at, updated_at) VALUES(hibernate_sequence.nextval, 'Antonio', 4, '$2a$10$JxNPBBRhpNUxUktn4GOFXetNXzb1lQUqm2RFNH1esKR7I333/vZFi', '2020-01-01 00:00:00.000', '2020-01-01 00:00:00.000')");
    }

    private void seedStudent5(Context context) {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO students(id, name, registration_number, password, created_at, updated_at) VALUES(hibernate_sequence.nextval, 'José', 5, '$2a$10$JxNPBBRhpNUxUktn4GOFXetNXzb1lQUqm2RFNH1esKR7I333/vZFi', '2020-01-01 00:00:00.000', '2020-01-01 00:00:00.000')");
    }

}