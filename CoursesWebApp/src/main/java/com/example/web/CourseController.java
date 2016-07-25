package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import com.example.core.Course;
import com.example.core.CourseRepository;
import com.example.core.Lesson;


@Controller
public class CourseController {
	
	private CourseRepository CourseRepo;
	
	public CourseRepository getCourseRepo() {
		return CourseRepo;
	}

	@Autowired
	public void setCourseRepo(CourseRepository courseRepo) {
		CourseRepo = courseRepo;
	}

	@RequestMapping("/")
	public String rootPath(){
		return "redirect:/courses";
	}
	
	@RequestMapping("courses")
	public String courses (ModelMap model)
	{
		List<Course> courses = CourseRepo.findAll(); 
		model.put("courses",courses );
		return "courses";
	}
	
	@RequestMapping(value="editCourse/{courseId}", method=RequestMethod.GET)
	public String editCourseGet (@PathVariable Long courseId, ModelMap model)
	{
		Course course = CourseRepo.findOne(courseId);
		model.put("course", course);
		model.put("lessons", course.getLessons());
		return "editCourse";
	}
	
	@RequestMapping(value="createCourse", method = RequestMethod.GET)
	public String createCourseGet(ModelMap model){
		Course course = new Course();
		model.put("course", course);
		return "createCourse";
	}
	
	@RequestMapping(value="createCourse", method=RequestMethod.POST)
	public String createCoursePost(@ModelAttribute Course course ,ModelMap model){
		CourseRepo.save(course);
		return "redirect:/";
	}
	
	@RequestMapping(value="editCourse/addLesson/{courseId}", method = RequestMethod.GET)
	public String addLessonGet(@PathVariable Long courseId, ModelMap model){
		Course course = CourseRepo.findOne(courseId);
		model.put("course", course);
		Lesson lesson = new Lesson();
		model.put("lesson", lesson);
		return "addLesson";
	}
	
	@RequestMapping(value="editCourse/addLesson/{courseId}", method = RequestMethod.POST)
	public String addLessonPost(@ModelAttribute Lesson lesson, @PathVariable Long courseId, ModelMap model)
	{
		Course course = CourseRepo.findOne(courseId);
		lesson.setCourse(course);
		course.getLessons().add(lesson);
		CourseRepo.save(course);
		return "redirect:/";
	 
	}

}
