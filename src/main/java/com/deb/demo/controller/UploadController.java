package com.deb.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UploadController {

	protected static  final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);



	@GetMapping("/upload")
	public ModelAndView getUploadView(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("upload");

		return modelAndView;

	}

	@GetMapping("/images")
	public List<String> getImages(HttpServletRequest request) throws IOException{
		String realPathtoUploads = request.getServletContext().getRealPath("resources/images/"); 
		/*Resource[] images =ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(realPathtoUploads);
*/
	    File[] f= new File(realPathtoUploads).listFiles();		
		return Arrays.asList(f).stream().map(File::getName).collect(Collectors.toList());		
	}
	
	
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request){

		if (file.isEmpty()) {			
			return "Please select a file . . .";
		}


		String realPathtoUploads = request.getServletContext().getRealPath("resources/images/"); 
		String orgName = file.getOriginalFilename();
		String filePath = realPathtoUploads + orgName;
		try{
			file.transferTo(new File(filePath));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}


	@GetMapping("/")
	public ModelAndView init(HttpServletRequest request,HttpServletResponse response){
		return getUploadView(request, response);
	}
@GetMapping("/start/{time}")
	public void startScheduler(@PathVariable Long time){
		System.out.println("Task size:"+ApplicationUtility.tasks.size());
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
		ScheduledFuture<?> task = scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("some task of time :"+time), 0,time, TimeUnit.SECONDS);		
		ApplicationUtility.tasks.add(task);
		System.out.println("Task size after:"+ApplicationUtility.tasks.size());
	}
	@GetMapping("/stop")
	public void stopScheduler(){
		System.out.println("Task size:"+ApplicationUtility.tasks.size());
		for(ScheduledFuture<?> task : ApplicationUtility.tasks){
			System.out.println("Stopping task:"+task);
			task.cancel(true);
		}
	}

}
