package com.example.demo.entity.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Category;
import com.example.demo.entity.OrderFood;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OrderFoodRepo;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderFoodService;


@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	CategoryService cs;
	@Autowired
	OrderFoodRepo ofr;
	@Autowired
	OrderFoodService ofs;
	@Autowired
	CategoryRepository  cr;
	@Value("${dir.images}")
	private String imagesDir;
	
	
	
	
	
	
	@RequestMapping(value="/home")
	public String home()
	{
		return "Admin";
	}
	
@RequestMapping(value="/indexcateg")
public String Index(Model model ) {
	
	
	List<Category>categories= cs.getAllCategory();
	
	model.addAttribute("categories", categories);
	return  "Categorie/index";
}
	
@RequestMapping(value="/indexfood")
public String index(Model model,@PageableDefault(size = 5) Pageable pageable) {
Page<OrderFood> page=ofr.findAll(pageable);
model.addAttribute("page",page);
model.addAttribute("categories", cs.getAllCategory());

return "Orderfood/indexfood";

}


@RequestMapping("/deletecategorie")

public String deleteCategorie(Long id) {
	
	cs.deleteCategory(id);
	
return "redirect:/admin/indexcateg";

}
@RequestMapping("/deletefood")
public String deleteFood(Long id) {
	ofs.deleteFoodById(id);
	return "redirect:/admin/indexfood";
}

@RequestMapping("/formaddcategorie")
public String addForm(Model model) {
	model.addAttribute("Categorie",new Category());
	return "Categorie/AddCategorie";
	
}


@RequestMapping("/addcategorie")
public String addCategory(Category category) {
	cs.addCategory(category);
	return "redirect:/admin/indexcateg";
}
@RequestMapping("/foodForm")
public String foodForm(Model model) {
	List<Category> categories= cs.getAllCategory();
	model.addAttribute("categories",categories);
	model.addAttribute("OrderFood",new OrderFood());
	return "OrderFood/AddFood";
}

@RequestMapping("/formupdatecategorie")
public String updateForm(Model model,@RequestParam Long id) {
	Category category= cs.getCategoryById(id);
	model.addAttribute("category",category);
return "Categorie/UpdateCategorie";

}


@PostMapping("/updatecategorie")
public String updateCategory(@ModelAttribute("category") Category category ) {
	System.out.println(category.getId());
       cr.save(category);
return "redirect:/admin/indexcateg";
}


/*@PostMapping("/saveFood")
public String addProduit(@ModelAttribute("OrderFood")  
String OrderFood, @RequestParam("file") MultipartFile
file)throws Exception {
ofs.addFood(OrderFood, file);
return "redirect:/admin/indexfood";
}*/
@RequestMapping(value="/saveFood" , method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String addFood(@Valid OrderFood food, @RequestParam("file") MultipartFile file,BindingResult bindingresult)throws Exception {
if(bindingresult.hasErrors()) {
	return "OrderFood/AddFood";
}
if(!(file.isEmpty())) {
	String nomFile=file.getOriginalFilename();// ali.jpg->ali_1234656.jpg
	String tab[]=nomFile.split("\\.");//ali|jpg
	String nomModif=tab[0]+System.currentTimeMillis()+"."+tab[1];
	System.out.println(nomModif);
	food.setImg(nomModif);
	food.setImg(file.getOriginalFilename());
}
ofr.save(food);
if(!(file.isEmpty())) {
	
	String nomFile=file.getOriginalFilename();// ali.jpg->ali_1234656.jpg
	String tab[]=nomFile.split("\\.");//ali|jpg
	String nomModif=tab[0]+System.currentTimeMillis()+"."+tab[1];
	System.out.println(nomModif);
	food.setImg(nomModif);
	file.transferTo(new File(System.getProperty("user.home")+"/book-store/src/assets/img/"+nomModif));
	}
ofr.save(food);


return "redirect:/admin/indexfood";}

@RequestMapping(value="/getImage" ,produces=MediaType.IMAGE_JPEG_VALUE)
@ResponseBody
public byte[] getphoto(Long id) throws Exception{
	
	File  f = new File(System.getProperty("user.home")+"/book-store/src/assets/img/"+id);
	return IOUtils.toByteArray(new FileInputStream(f));
}



@RequestMapping("/formupdatefood")
public String updateFormFood(Model model,@RequestParam Long id) {
	OrderFood food= ofs.getOrderFoodById(id);
	List<Category> categories= cs.getAllCategory();
	model.addAttribute("categories",categories);
	model.addAttribute("food",food);
return "OrderFood/Edit";

}


@RequestMapping(value="/update" , method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public String updateFood(@Valid OrderFood food, @RequestParam("file") MultipartFile file,BindingResult bindingresult)throws Exception {
if(bindingresult.hasErrors()) {
	return "OrderFood/AddFood";
}
if(!(file.isEmpty())) {
	String nomFile=file.getOriginalFilename();// ali.jpg->ali_1234656.jpg
	String tab[]=nomFile.split("\\.");//ali|jpg
	String nomModif=tab[0]+System.currentTimeMillis()+"."+tab[1];
	System.out.println(nomModif);
	food.setImg(nomModif);
	food.setImg(file.getOriginalFilename());
}
ofr.save(food);
if(!(file.isEmpty())) {
	food.setImg(file.getOriginalFilename());
	String nomFile=file.getOriginalFilename();// ali.jpg->ali_1234656.jpg
	String tab[]=nomFile.split("\\.");//ali|jpg
	String nomModif=tab[0]+System.currentTimeMillis()+"."+tab[1];
	System.out.println(nomModif);
	file.transferTo(new File(System.getProperty("user.home")+"/book-store/src/assets/img/"+nomModif));
	}
ofr.save(food);
return "redirect:/admin/indexfood";
}

@PostMapping("parcat")
public String findProduitparCat(@RequestParam Long idcat,Model m,@PageableDefault(size = 5) Pageable pageable) {

if(idcat!=0L) {
	List<OrderFood> page=ofr.findByCategoryId(idcat, pageable);
	m.addAttribute("page",page);
	
	
}
else {
	Page<OrderFood> page=ofr.findAll(pageable);
	m.addAttribute("page",page);
	m.addAttribute("categories", cs.getAllCategory());}

return "Orderfood/indexfood";







}}