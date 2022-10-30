package com.acme.app1.controllers;

import com.acme.app1.dtos.EmployeeDto;
import com.acme.app1.models.Employee;
import com.acme.app1.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Autowired
    EmployeeController employeeController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    private static final String REST_URL = "/api/employees/";
    private static final long ID = 1L;
    private static final String FIRSTNAME = "firstname1";
    private static final String LASTNAME = "lastname1";
    private static final String EMAIL = "test1@test.com";

    Employee employee;
    public EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(ID).firstname(FIRSTNAME).lastname(LASTNAME).email(EMAIL)
                .build();
        employeeDto = EmployeeDto.builder()
                .firstname(FIRSTNAME).lastname(LASTNAME).email(EMAIL)
                .build();
    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(employeeController).isNotNull();
    }

    @Test
    void getAllEmployees() throws Exception {

        when(employeeService.getAll()).thenReturn(Collections.singletonList(employee));

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstname", is(employee.getFirstname())))
                .andExpect(jsonPath("$[0].lastname", is(employee.getLastname())))
                .andExpect(jsonPath("$[0].email", is(employee.getEmail())));
    }

    @Test
    void getEmployeeById() throws Exception {

        when(employeeService.findById(any(Long.class))).thenReturn(Optional.of(employee));

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname", is(employee.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(employee.getLastname())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    @Test
    void getEmployeeById_NotFound() throws Exception {
        when(employeeService.findById(any(Long.class))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void addEmployee() throws Exception {
        when(employeeService.save(any(Employee.class))).thenReturn(employee);
        when(employeeService.findByEmail(any(String.class))).thenReturn(Optional.empty());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employeeDto);

        mockMvc.perform(
            MockMvcRequestBuilders
            .post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstname").value(employee.getFirstname()))
        .andExpect(jsonPath("$.lastname", is(employee.getLastname())))
        .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    @Test
    void addEmployeeAlreadyExist() throws Exception {
        when(employeeService.save(any(Employee.class))).thenReturn(employee);
        when(employeeService.findByEmail(any(String.class))).thenReturn(Optional.of(employee));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employeeDto);

        mockMvc.perform(
            MockMvcRequestBuilders
            .post(REST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        )
        .andExpect(status().isConflict());
    }
}
