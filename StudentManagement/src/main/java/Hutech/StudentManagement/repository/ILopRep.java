package Hutech.StudentManagement.repository;



import Hutech.StudentManagement.entity.Lop;
import Hutech.StudentManagement.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILopRep extends JpaRepository<Lop,Long> {
    List<Lop> findByTenLopContainingIgnoreCase(String tenLop);
}
