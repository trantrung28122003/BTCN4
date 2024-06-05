package Hutech.StudentManagement.controller;

import Hutech.StudentManagement.entity.Lop;
import Hutech.StudentManagement.entity.MonHoc;
import Hutech.StudentManagement.entity.SinhVien;
import Hutech.StudentManagement.service.LopService;
import Hutech.StudentManagement.service.MonHocService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/monhoc")
public class MonHocController {
    @Autowired
    private MonHocService monHocService;

    @GetMapping
    public String showAllMonHoc(Model model){
        List<MonHoc> dsMonHoc = monHocService.getAllMonHoc();
        model.addAttribute("dsMonHoc",dsMonHoc);
        return "monhoc/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("monhoc",new MonHoc());
        return "monhoc/add";
    }
    @PostMapping("/add")
    public String addMonHoc(@ModelAttribute("monhoc") @Valid MonHoc monhoc, BindingResult result){
        if (result.hasErrors()) {
            return "/monhoc/add";
        }
        monHocService.addMonHoc(monhoc);
        return "redirect:/monhoc";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        MonHoc monHoc = monHocService.getMonHocById(id);
        if(monHoc == null){
            return "redirect:/lop";
        }
        model.addAttribute("monhoc", monHoc);
        return "/monhoc/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateLop(@PathVariable("id") String id, @Valid MonHoc monhoc, BindingResult result) {
        if (result.hasErrors())
        {
            monhoc.setMaMon(id);
            return "/monhoc/edit";
        }
        monHocService.updateMonHoc(monhoc);
        return "redirect:/monhoc";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id, Model model) {
        MonHoc monHoc = monHocService.getMonHocById(id);
        if(monHoc == null){
            return "redirect:/monhoc";
        }
        monHocService.deleteMonHoc(id);
        return "redirect:/monhoc";
    }

    @PostMapping("/search")
    public String searchMonHoc(Model model, String tenMonHoc) {
        List<MonHoc> dsMonHoc = monHocService.getMonHocByTenMonHoc(tenMonHoc);
        model.addAttribute("dsMonHoc", dsMonHoc);
        return "monhoc/list";
    }

}