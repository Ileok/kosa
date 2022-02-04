package kosaShoppingMall.mapper;

import kosaShoppingMall.domain.EmployeeDTO;

public interface EmployeeShipMapper {

	EmployeeDTO selectOne(String empId);

	void updatePassword(EmployeeDTO dto);

	Integer empUpdate(EmployeeDTO dto);

}
