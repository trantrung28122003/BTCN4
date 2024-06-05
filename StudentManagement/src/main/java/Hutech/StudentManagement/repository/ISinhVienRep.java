package Hutech.StudentManagement.repository;



import Hutech.StudentManagement.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISinhVienRep extends JpaRepository<SinhVien,String> {
    List<SinhVien> findByLop_MaLop(Long maLop);
    List<SinhVien> findByHoTenContainingIgnoreCase(String hoTen);
}
