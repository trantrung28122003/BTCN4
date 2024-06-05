package Hutech.StudentManagement.service;


import Hutech.StudentManagement.entity.SinhVien;
import Hutech.StudentManagement.repository.ISinhVienRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SinhVienService {
    @Autowired
    private ISinhVienRep sinhvienRepository;

    public List<SinhVien> getAllSinhVien()
    {
        return sinhvienRepository.findAll();
    }

    public SinhVien getSinhVienById(String id)
    {
        return sinhvienRepository.findById(id).orElse(null);
    }

    public void addSinhVien(SinhVien sinhVien)
    {
        sinhvienRepository.save(sinhVien);
    }

    public void updateSinhVien(SinhVien lop) {
        sinhvienRepository.save(lop);
    }

    public void deleteSinhVien(String id)
    {
        sinhvienRepository.deleteById(id);
    }

    public List<SinhVien> getSinhVienByLopId(long lopId)
    {
        return sinhvienRepository.findByLop_MaLop(lopId);
    }

    public void deleteSinhVienByLopId(long lopId)
    {
        List<SinhVien> sinhViens = sinhvienRepository.findByLop_MaLop(lopId);
        sinhvienRepository.deleteAll(sinhViens);
    }

    public List<SinhVien> getSinhVienByHoTen(String hoten)
    {
        return sinhvienRepository.findByHoTenContainingIgnoreCase(hoten);
    }


}
