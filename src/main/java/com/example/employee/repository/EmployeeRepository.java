package com.example.employee.repository;

import com.example.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //以下所有的*都代表变量
    //1.查询名字是*的第一个employee
    @Query("select e from Employee e where e.name=:name")
    Employee findEmployeeByName(@Param("name") String name);

    //2.找出Employee表中第一个姓名包含`*`字符并且薪资大于*的雇员个人信息
    @Query("select e from Employee e where name like %:name% and salary >:salary")
    Employee findEmployeeNameContainsValueAndGreaterThanSalary(@Param("name") String name, @Param("salary") int salary);

    //3.找出一个薪资最高且公司ID是*的雇员以及该雇员的姓名
    @Query(value="select * from Employee e where e.companyId = :companyId order by e.salary desc limit 1",nativeQuery = true)
    Employee findEmployeeWhoHasMaxSalaryById(@Param("companyId") Integer companyId);

    //4.实现对Employee的分页查询，每页两个数据
    Page<Employee> findAll(Pageable pageable);
    //5.查找**的所在的公司的公司名称
    @Query("select companyName from Company where id=(select companyId from Employee where name =:name )")
    String findCompanyByEmployeeName(@Param("name") String name);

    //6.将*的名字改成*,输出这次修改影响的行数
    @Modifying
    @Query("update Employee set name=:newName where name =:oldName")
    Integer modifyNameReturnRows(@Param("newName") String newName,@Param("oldName") String oldName);

    //7.删除姓名是*的employee
    @Modifying
    @Query("delete from Employee where name=?1")
    Integer deleteEmployeeByName(String name);
}