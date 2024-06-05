package Hutech.StudentManagement.controller;

import Hutech.StudentManagement.entity.Lop;
import Hutech.StudentManagement.entity.SinhVien;
import Hutech.StudentManagement.service.LopService;
import Hutech.StudentManagement.service.MonHocService;
import Hutech.StudentManagement.service.SinhVienService;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private LopService lopService;
    @Autowired
    private MonHocService monHocService;

    @GetMapping()
    public String showSinhVienList(Model model) {
        model.addAttribute("listSinhVien", sinhVienService.getAllSinhVien());
        return "/sinhvien/list";
    }

    private String saveImage(MultipartFile imageUrl){
        try {

            String uploadDir = "StudentManagement/src/main/resources/static/images/";
            String fileName = imageUrl.getOriginalFilename();

            Path filePath = Paths.get(uploadDir + fileName);

            Files.copy(imageUrl.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return "/images/" + fileName;

        } catch (Exception e) {
            System.out.println("Cannot save file: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("listLop", lopService.getAllLop());
        model.addAttribute("listMonHoc", monHocService.getAllMonHoc());
        return "/sinhvien/add";
    }

    @PostMapping("/add")
    public String addSinhVien(@Valid SinhVien sinhvien, BindingResult result, @RequestParam("image") MultipartFile imageFile, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("listLop", lopService.getAllLop());
            return "/sinhvien/add";
        }
        if (!imageFile.isEmpty()) {
            sinhvien.setImageUrl(saveImage(imageFile)); // Assuming you have a field imagePath in your Product entity
        }
        sinhVienService.addSinhVien(sinhvien);
        return "redirect:/sinhvien";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        SinhVien sinhVien = sinhVienService.getSinhVienById(id);
        if(sinhVien == null) {
            return "redirect:/sinhvien";
        }
        model.addAttribute("sinhVien", sinhVien);
        model.addAttribute("listLop", lopService.getAllLop());
        model.addAttribute("listMonHoc", monHocService.getAllMonHoc());
        return "/sinhvien/edit";
    }
    // Process the form for updating a product
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable String id, @Valid SinhVien sinhvien, BindingResult result,
                                @RequestParam("image") MultipartFile imageFile) {
        if (result.hasErrors()) {
            sinhvien.setMssv(id); // set id to keep it in the form in case of errors
            return "/sinhvien/edit";
        }
        SinhVien existingProduct = sinhVienService.getSinhVienById(id);
        if (imageFile.getOriginalFilename() !=null && !imageFile.getOriginalFilename().isBlank()) {
            sinhvien.setImageUrl(saveImage(imageFile));
        } else {
            sinhvien.setImageUrl(existingProduct.getImageUrl());
        }
        sinhVienService.updateSinhVien(sinhvien);
        return "redirect:/sinhvien";
    }
    @GetMapping("/delete/{id}")
    public String deleteSinhVien(@PathVariable("id") String id, Model model) {
        SinhVien sinhvien = sinhVienService.getSinhVienById(id);
        if(sinhvien == null){
            return "redirect:/sinhvien";
        }
        sinhVienService.deleteSinhVien(id);
        return "redirect:/sinhvien";
    }

    @PostMapping("/search")
    public String searchSinhVien(Model model, String hoten) {
        List<SinhVien> listSinhVien = sinhVienService.getSinhVienByHoTen(hoten);
        model.addAttribute("listSinhVien", listSinhVien);
        return "sinhvien/list";
    }


}
