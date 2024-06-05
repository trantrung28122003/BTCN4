package Hutech.StudentManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;


@Data
@Table(name = "MonHoc")
@Entity(name = "MonHoc")
public class MonHoc {
    @Id
    @Column(name = "MaMon",length = 10)
    @Size(min = 1,max = 10,message = "Mã môn học phải từ 1 đến 10 kí tự ")
    private String maMon;


    @Size(min = 5,max = 50,message = "Tên môn học phải từ 5 đến 50 kí tự")
    @Column(name = "TenMonHoc",length = 50)
    private String tenMonHoc;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SinhVien_MonHoc",
            joinColumns = {@JoinColumn(name = "MaMon")},
            inverseJoinColumns = {@JoinColumn(name = "MSSV")}

    )
    private Set<SinhVien> sinhViens;
}
