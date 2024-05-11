DROP TABLE IF EXISTS students ;
CREATE TABLE students (
    student_id BIGSERIAL PRIMARY KEY,
    student_name varchar(100),
    student_email varchar(100),
    student_phone varchar(30)
);

DROP TABLE IF EXISTS teachers ;
CREATE TABLE teachers (
    teacher_id BIGSERIAL PRIMARY KEY,
    teacher_name varchar(100),
    teacher_email varchar(100),
    teacher_phone varchar(30)
);

DROP TABLE IF EXISTS notes ;
CREATE TABLE notes (
    note_id BIGSERIAL PRIMARY KEY,
    note_title TEXT,
    note_content TEXT,
    note_date DATE NOT NULL,
    student_id BIGINT,
    teacher_id BIGINT,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students(student_id),
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(teacher_id)
);