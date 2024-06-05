package Hutech.StudentManagement.repository;



import Hutech.StudentManagement.entity.Lop;
import Hutech.StudentManagement.entity.MonHoc;
import Hutech.StudentManagement.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMonHocRep extends JpaRepository<MonHoc,String> {
    List<MonHoc> findByTenMonHocContainingIgnoreCase(String tenMonHoc);
}
