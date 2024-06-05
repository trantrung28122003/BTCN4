package Hutech.StudentManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;


@Data
@Entity(name = "Lop")
@Table(name = "Lop")
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLop")
    private Long maLop;

    @NotNull(message = "Tên lớp không được để trống!")
    @Size(min = 1, max = 7, message = "Tên lớp phải dưới từ 1 đến 7 kí tự!")
    @Column(name = "TenLop", length = 7)
    private String tenLop;

    @OneToMany(mappedBy = "lop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SinhVien> sinhviens;
}

