package kosaShoppingMall.mapper;

import java.util.List;

import kosaShoppingMall.domain.EmployeeDTO;

public interface EmployeeMapper {

	Integer empInsert(EmployeeDTO dto);

	List<EmployeeDTO> selectAll();

	EmployeeDTO selectOne(String empId);

	Integer empUpdate(EmployeeDTO dto);

	Integer empDelete(String empId);

}