package Hutech.StudentManagement.controller;


import Hutech.StudentManagement.entity.Lop;
import Hutech.StudentManagement.entity.SinhVien;
import Hutech.StudentManagement.service.LopService;
import Hutech.StudentManagement.service.MonHocService;
import Hutech.StudentManagement.service.SinhVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lop")
public class LopController {
    @Autowired
    private LopService lopService;
    private SinhVien sinhVien;
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private MonHocService monHocService;

    @GetMapping
    public String showAllLop(Model model){
        List<Lop> dsLop = lopService.getAllLop();
        model.addAttribute("dsLop",dsLop);
        return "lop/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("lop",new Lop());
        return "lop/add";
    }
    @PostMapping("/add")
    public String addLop(@ModelAttribute("lop") @Valid Lop lop, BindingResult result){
        if (result.hasErrors()) {
            return "/lop/add";
        }
        lopService.addLop(lop);
        return "redirect:/lop";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Lop lop = lopService.getLopById(id);
        if(lop == null){
            return "redirect:/lop";
        }
        model.addAttribute("lop", lop);

        return "/lop/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateLop(@PathVariable("id") long id, @Valid Lop lop, BindingResult result) {
        if (result.hasErrors())
        {
            lop.setMaLop(id);
            return "/lop/edit";
        }
        lopService.updateLop(lop);
        return "redirect:/lop";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model) {
        Lop lop = lopService.getLopById(id);
        if(lop == null){
            return "redirect:/lop";
        }
        sinhVienService.deleteSinhVienByLopId(id);
        lopService.deleteLop(id);
        return "redirect:/lop";
    }

    @PostMapping("/search")
    public String searchLopHoc(Model model, String tenLop) {
        List<Lop> dsLop = lopService.getLopHocByTenLop(tenLop);
        model.addAttribute("dsLop", dsLop);
        return "lop/list";
    }
}
