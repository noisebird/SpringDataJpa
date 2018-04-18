package com.example.employee;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class EmployeeJPATest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
        //本地启动mysql，创建employee_db数据库
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:mysql://localhost:3306/employee_db", "root", "root");
        //清除schema_version中记录所有表结构，视图，存储过程，函数以及所有的数据等都会被清除。
        flyway.clean();
        // 此命令会自动检查数据库脚本是否有变化，如果有变化，则执行脚本，更新数据库版本，如果数据库初始状态是空库，
        // 则会自动创建schema_version 表，用于存储数据库操作的版本记录，
        flyway.migrate();
    }
    @Test
    public void should_return_employee_when_input_employee_name() {
        //1.查询名字是小红的employee
        String actualName = "xiaohong";
        Employee expectedEmployee = employeeRepository.findEmployeeByName(actualName);
        assertThat(actualName).isEqualTo(expectedEmployee.getName());
    }

    @Test
    public void should_return_employee_given_character_in_name_and_salary_large_than() {
        //2.找出Employee表中第一个姓名包含`n`字符的雇员所有个人信息
        //此处补充测试：且工资大于6000
        String actualName = "xiaohong";
        Employee expectedEmployee = employeeRepository.findEmployeeNameContainsValueAndGreaterThanSalary("n", 6000);
        assertThat(actualName).isEqualTo(expectedEmployee.getName());
        System.out.println(expectedEmployee.getName() + ":" + expectedEmployee.getSalary());
        actualName = "xiaozhi";
        expectedEmployee = employeeRepository.findEmployeeNameContainsValueAndGreaterThanSalary("x", 7000);
        assertThat(actualName).isEqualTo(expectedEmployee.getName());
    }

}