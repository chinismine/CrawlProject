package crawl.controller;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crawl.service.CrawlCleanService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CrawlController {
	
	@Autowired
	CrawlCleanService service;
	
	@PostMapping("/crawl")
	public  Object getPageContent(@RequestBody Map<String, String> reqbody) {
		String url = reqbody.get("url");
		
		return service.ReturnService(url);
		
	}

}

