package com.project.util;

public class QueriesConst {

    //User
    public static final String GET_USERS = """
                                           SELECT u.id, u.email, u.name, u.password, u.phone, u.address, u.role_id,
                                           r.id, r.name, r.description
                                           FROM user u
                                           JOIN role r ON u.role_id = r.id
                                           ORDER BY u.role_id
                                           """;

    public static final String GET_USER_BY_ID = """
                                                SELECT u.id, u.email, u.name, u.password, u.phone, u.address, u.role_id,
                                                r.id, r.name, r.description
                                                FROM user u
                                                JOIN role r ON u.role_id = r.id
                                                WHERE u.id = ?
                                                """;

    public static final String GET_USER_BY_ROLE = """
                                                  SELECT u.id, u.email, u.name, u.password, u.phone, u.address, u.role_id,
                                                  r.id, r.name, r.description
                                                  FROM user u
                                                  JOIN role r ON u.role_id = r.id
                                                  WHERE u.role_id = ?
                                                  """;
    public static final String CHECK_LOGIN = """
                                             SELECT u.id, u.email, u.name, u.password, u.phone, u.address, u.role_id,
                                             r.id, r.name, r.description
                                             FROM user u
                                             JOIN role r ON u.role_id = r.id
                                             WHERE email = ?
                                             """;

    public static final String INSERT_USER = """
                                             INSERT INTO user (email, name, password, phone, address, role_id)
                                             VALUES (?, ?, ?, ?, ?, ?)
                                             """;

    public static final String UPDATE_USER = """
                                             UPDATE user SET email = ?, name = ?, password = ?, phone = ?, address = ?,
                                             role_id = ?
                                             WHERE id = ?
                                             """;

    public static final String DELETE_USER = """
                                             DELETE FROM user WHERE id = ?
                                             """;

    //Project
    public static final String GET_PROJECTS = """
                                              SELECT p.id, p.name, p.description, p.start_date, p.end_date, p.created_by,
                                              u.id, u.email, u.name, u.password, u.phone, u.address, u.role_id
                                              FROM project p
                                              JOIN user u ON p.created_by = u.id
                                              ORDER BY p.id
                                              """;

    public static final String GET_PROJECT_BY_ID = """
                                                   SELECT p.id, p.name, p.description, p.start_date, p.end_date, p.created_by,
                                                   u.id, u.email, u.name, u.password, u.phone, u.address, u.role_id
                                                   FROM project p
                                                   JOIN user u ON p.created_by = u.id
                                                   WHERE p.id = ?
                                                   """;

    public static final String GET_PROJECT_CREATED_BY_ID = """
                                                SELECT p.created_by
                                                FROM project p
                                                WHERE p.id = ?
                                               """;

    public static final String INSERT_PROJECT = """
                                                INSERT INTO project (name, description, start_date, end_date, created_by)
                                                VALUES (?, ?, ?, ?, ?)
                                                """;

    public static final String UPDATE_PROJECT = """
                                                 UPDATE project SET name = ?, description = ?, start_date = ?, end_date = ?,
                                                 created_by = ?
                                                 WHERE id = ?
                                                 """;

    public static final String DELETE_PROJECT = """
                                                DELETE FROM project WHERE id = ?
                                                """;

    public static final String DELETE_PROJECT_CREATED_BY = """
                                                           DELETE FROM project WHERE created_by = ?
                                                           """;

    //Task
    public static final String GET_TASKS = """
                                           SELECT t.id, t.name, t.description, t.start_date, t.end_date, t.assignee, t.project_id, t.status_id,
                                           p.id, p.name, p.description, p.start_date, p.end_date, p.created_by,
                                           s.id, s.name, s.description,
                                           u.id, u.email, u.name, u.password, u.phone, u.address, u.role_id
                                           FROM task t
                                           JOIN project p ON t.project_id = p.id
                                           JOIN status s ON t.status_id = s.id
                                           JOIN user u ON t.assignee = u.id
                                           ORDER BY t.project_id, t.status_id
                                           """;

    public static final String GET_TASK_BY_ID = """
                                                SELECT t.id, t.name, t.description, t.start_date, t.end_date, t.assignee, t.project_id, t.status_id,
                                                p.id, p.name, p.description, p.start_date, p.end_date, p.created_by,
                                                s.id, s.name, s.description,
                                                u.id, u.email, u.name, u.password, u.phone, u.address, u.role_id
                                                FROM task t
                                                JOIN project p ON t.project_id = p.id
                                                JOIN status s ON t.status_id = s.id
                                                JOIN user u ON t.assignee = u.id
                                                WHERE t.id = ?
                                                """;

    public static final String INSERT_TASK = """
                                              INSERT INTO task (name, description, start_date, end_date, assignee, project_id, status_id)
                                              VALUES (?, ?, ?, ?, ?, ?, ?)
                                              """;

    public static final String UPDATE_TASK = """
                                              UPDATE task SET name = ?, description = ?, start_date = ?, end_date = ?,
                                              assignee = ?, project_id = ?, status_id = ?
                                              WHERE id = ?
                                              """;

    public static final String DELETE_TASK = """
                                              DELETE FROM task WHERE id = ?
                                              """;

    //Role
    public static final String GET_ROLES = "SELECT * FROM role";

    //Status
    public static final String GET_STATUS = "SELECT * FROM status";

}
