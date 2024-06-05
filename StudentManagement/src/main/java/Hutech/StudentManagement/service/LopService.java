package Hutech.StudentManagement.service;

import Hutech.StudentManagement.entity.Lop;
import Hutech.StudentManagement.entity.MonHoc;
import Hutech.StudentManagement.repository.ILopRep;
import Hutech.StudentManagement.repository.ISinhVienRep;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LopService {
    @Autowired
    private ILopRep lopRepository;
    @Autowired
    private SinhVienService sinhVienService;
    public List<Lop> getAllLop()
    {
        return lopRepository.findAll();
    }

    public Lop getLopById(long id)
    {
        return lopRepository.findById(id).orElse(null);
    }

    public void addLop(Lop lop)
    {
        lopRepository.save(lop);
    }

    public void updateLop(Lop lop) {
        lopRepository.save(lop);
    }

    public void deleteLop(long id)
    {

        lopRepository.deleteById(id);
    }
    public List<Lop> getLopHocByTenLop(String tenLopHoc)
    {
        return lopRepository.findByTenLopContainingIgnoreCase(tenLopHoc);
    }
}
