package crawl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import crawl.bean.Students;
import crawl.model.StudentRepository;

@RestController
public class ShowAllController {
	
	@Autowired
	StudentRepository sRepo;
	
	@PostMapping("all")
	public List<Students> showAllActivities() {
		
		return sRepo.findAll();
	}

}
