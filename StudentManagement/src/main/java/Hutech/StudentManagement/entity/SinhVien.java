package Hutech.StudentManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;


@Data
@Entity(name = "SinhVien")
@Table(name = "SinhVien")
public class SinhVien {
    @Id
    @Column(name = "MSSV", length = 10)
    @Size(min = 10, max = 10, message = "MSSV phải có 10 chũ số")
    private String mssv;

    @Size(max = 50, message = "Họ tên của sinh viên chỉ tối đa 50 kí tự")
    @NotNull(message = "Họ tên của sinh viên không được để trống")
    @Column(name = "HoTen", length = 50)
    private String hoTen;

    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    @Temporal(TemporalType.DATE)
    @Column(name = "NgaySinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @Email(message = "Email phải hợp lệ")
    @NotNull(message = "Email không đuợc để trống")
    @Column(name = "Email")
    private String email;

    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "MaLop",
            referencedColumnName = "MaLop",
            foreignKey = @ForeignKey(name = "Fk_SINHVIEN_LOP")
    )
    private Lop lop;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "SinhVien_MonHoc",
            joinColumns = {@JoinColumn(name = "MSSV")},
            inverseJoinColumns ={@JoinColumn(name = "MaMon")}
    )
    private Set<MonHoc> monHocs;
}