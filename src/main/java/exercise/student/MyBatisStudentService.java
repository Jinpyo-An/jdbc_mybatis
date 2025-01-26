package exercise.student;

import exercise.common.MyBatisAccess;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class MyBatisStudentService implements StudentService {

    final SqlSessionFactory sqlSessionFactory = MyBatisAccess.getSqlSessionFactory();

    @Override
    public List<Student> getStudents() {
        final List<Student> students;

        try (final SqlSession session = sqlSessionFactory.openSession()) {
            final var studentService = session.getMapper(StudentService.class);
            students = studentService.getStudents();
            return students;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Student getStudentByNo(final String studentNo) {
        final Student student;

        try (final var session = sqlSessionFactory.openSession()) {
            final var studentService = session.getMapper(StudentService.class);
            student = studentService.getStudentByNo(studentNo);
            return student;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return new Student();
        }
    }

    @Override
    public Student getStudentByName(final String studentName) {
        final Student student;

        try (final var session = sqlSessionFactory.openSession()) {
            final var studentService = session.getMapper(StudentService.class);
            student = studentService.getStudentByName(studentName);
            return student;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return new Student();
        }
    }

    @Override
    public Student getStudentByBirthday(final String studentBirthday) {
        final Student student;

        try (final var session = sqlSessionFactory.openSession()) {
            final var studentService = session.getMapper(StudentService.class);
            student = studentService.getStudentByBirthday(studentBirthday);
            return student;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return new Student();
        }
    }

    @Override
    public Integer insertStudent(final Student student) {
        try (final var session = sqlSessionFactory.openSession()) {
            final var studentService = session.getMapper(StudentService.class);
            final var result = studentService.insertStudent(student);

            session.commit();

            return result;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public Integer insertStudentMulti(final Student[] students) {
        try (final var session = sqlSessionFactory.openSession();) {
            final var studentService = session.getMapper(StudentService.class);
            int result = studentService.insertStudentMulti(students);
            session.commit();
            return result;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public Integer insertStudentMultiBatch(final Student[] students) {

        try (final var session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            final var studentService = session.getMapper(StudentService.class);

            for (Student student : students) {
                studentService.insertStudent(student);
            }

            session.commit();

            return students.length;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public Integer updateStudentBirthdayByNo(final String no, final String birthday) {
        return 0;
    }

    @Override
    public Integer updateStudent(final Student student) {
        try (final SqlSession session = sqlSessionFactory.openSession()) {
            final var studentService = session.getMapper(StudentService.class);
            int result = studentService.updateStudent(student);

            session.commit();

            return result;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public Integer updateStudentMultiBatch(final Student[] students) {
        try (final SqlSession session = sqlSessionFactory.openSession()) {
            final var studentService = session.getMapper(StudentService.class);
            for (Student student : students) {
                studentService.updateStudent(student);
            }
            session.commit();
            return students.length;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public Integer deleteStudentByNo(final String no) {
        try (final SqlSession session = sqlSessionFactory.openSession()) {
            final var studentService = session.getMapper(StudentService.class);
            int result = studentService.deleteStudentByNo(no);

            session.commit();

            return result;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    @Override
    public Integer deleteStudentNoMultiBatch(final Student[] students) {
        try (final SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            final var studentService = session.getMapper(StudentService.class);

            for (Student student : students) {
                studentService.deleteStudentByNo(student.getNo());
            }

            session.commit();

            return students.length;
        } catch (final Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
