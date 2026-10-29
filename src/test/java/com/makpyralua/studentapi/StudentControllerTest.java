package com.makpyralua.studentapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

class StudentControllerTest {

    private final StudentRepository repository = mock(StudentRepository.class);
    private final StudentController controller = new StudentController(repository);

    @Test
    void updateChangesStudentData() {
        Student existing = new Student("Alua", "Makpyr", "alua@example.com", "IT2-2306");
        existing.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Student.class))).thenAnswer(inv -> inv.getArgument(0));

        Student data = new Student("Alua", "Makpyr", "alua.makpyr@example.com", "IT2-2306");
        Student updated = controller.update(1L, data);

        assertEquals("wrong-email@example.com", updated.getEmail());
        assertEquals("Makpyr", updated.getLastName());
    }

    @Test
    void getByIdThrowsForNonexistentStudent() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> controller.getById(999L));
    }
}
