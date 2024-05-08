package crawl.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import crawl.service.CrawlCleanService;

@RestController
public class CrawlController {
	
	@Autowired
	CrawlCleanService service;
	
	@PostMapping("/crawl")
	public  Object getPageContent(@RequestBody String url) {
		
		return service.ReturnService("https://www.goodjob.life/experiences/662799cc6f3226447aa24ac5");
		
	}

}

