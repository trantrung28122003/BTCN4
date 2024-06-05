package Hutech.StudentManagement.service;


import Hutech.StudentManagement.entity.MonHoc;
import Hutech.StudentManagement.entity.SinhVien;
import Hutech.StudentManagement.repository.IMonHocRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonHocService {
    @Autowired
    private IMonHocRep monHocRepository;

    public List<MonHoc> getAllMonHoc()
    {
        return monHocRepository.findAll();
    }

    public MonHoc getMonHocById(String id)
    {
        return monHocRepository.findById(id).orElse(null);
    }

    public void addMonHoc(MonHoc monhoc)
    {
        monHocRepository.save(monhoc);
    }

    public void updateMonHoc(MonHoc monhoc) {
        monHocRepository.save(monhoc);
    }

    public void deleteMonHoc(String id)
    {
        monHocRepository.deleteById(id);
    }
    public List<MonHoc> getMonHocByTenMonHoc(String tenMonHoc)
    {
        return monHocRepository.findByTenMonHocContainingIgnoreCase(tenMonHoc);
    }

}
