package exercise.student;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return service.getStudents();
    }

    @GetMapping("/{no}")
    public Student getStudentByNo(
            @PathVariable("no") final String no
    ) {
        return service.getStudentByNo(no);
    }

    @GetMapping(params = "name")
    public Student getStudentByName(
            @RequestParam("name") final String name
    ) {
        return service.getStudentByName(name);
    }

    @GetMapping(params = "birthday")
    public Student getStudentByBirthday(
            @RequestParam("birthday") final String birthday
    ) {
        return service.getStudentByBirthday(birthday);
    }

    @PostMapping
    public String addStudent(
            @RequestBody final Student student
    ) {
        final var result = service.insertStudent(student);
        return result + "명의 학생이 등록되었습니다.";
    }

    @PostMapping("/batch")
    public String addStudents(
            @RequestBody final List<Student> students
    ) {
        int size = students.size();
        Student[] arrStudents = students.toArray(new Student[size]);

        final var result = service.insertStudentMultiBatch(arrStudents);
        return result + "명의 학생이 등록되었습니다.";
    }

    @PatchMapping
    public String updateStudent(
            @RequestBody final Student student
    ) {
        final var result = service.updateStudent(student);
        return result + "명의 학생이 수정되었습니다.";
    }

    @PatchMapping("/batch")
    public String updateStudents(
            @RequestBody final List<Student> students
    ) {
        int size = students.size();
        Student[] arrStudents = students.toArray(new Student[size]);

        final var result = service.updateStudentMultiBatch(arrStudents);
        return result + "명의 학생이 수정되었습니다.";
    }

    @DeleteMapping
    public String deleteStudent(
            @RequestBody final Student student
    ) {
        final var result = service.deleteStudentByNo(student.getNo());
        return result + "명의 학생이 삭제되었습니다.";
    }

    @DeleteMapping("/batch")
    public String deleteStudents(
            @RequestBody final List<Student> students
    ) {
        int size = students.size();
        Student[] arrStudents = students.toArray(new Student[size]);

        final var result = service.deleteStudentNoMultiBatch(arrStudents);
        return result + "명의 학생이 삭제되었습니다.";
    }
}
