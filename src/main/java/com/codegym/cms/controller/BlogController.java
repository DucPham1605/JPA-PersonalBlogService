package com.codegym.cms.controller;

import com.codegym.cms.model.Blog;
import com.codegym.cms.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
private BlogService blogService;

@GetMapping("/blogs")
    public ModelAndView listBlogs (){
    List<Blog> blogs = blogService.findAll();
    ModelAndView modelAndView = new ModelAndView("/list");
    modelAndView.addObject("blogs",blogs);
    return modelAndView;
}
@GetMapping("/create-blog")
public ModelAndView showCreateForm(){
    ModelAndView modelAndView = new ModelAndView("/create");
    modelAndView.addObject("blog",new Blog());
    return  modelAndView;
}

@PostMapping("/create-blog")
    public ModelAndView saveBlog(@ModelAttribute ("blog") Blog blog){
    blogService.save(blog);
    ModelAndView modelAndView = new ModelAndView("/create");
    modelAndView.addObject("blog",new Blog());
    modelAndView.addObject("message","Added succesfully");
    return modelAndView;
}

@GetMapping("/view-detail/{id}")
public ModelAndView showDetail(@PathVariable Long id){
    Blog blog = blogService.findById(id);
    if(blog != null){
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
    else{
        ModelAndView modelAndView = new ModelAndView("/error.404");
        return modelAndView;
    }
}
@GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
    Blog blog = blogService.findById(id);
    if (blog != null){
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
    else {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}

@PostMapping("delete-blog")
public String deleteBlog(@ModelAttribute("blog") Blog blog){
    blogService.remove(blog.getId());
    return "redirect:blogs";
}

@GetMapping("/update-blog/{id}")
public ModelAndView showUpdateForm(@PathVariable Long id){
    Blog blog = blogService.findById(id);
    if (blog != null){
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("blog",blog);
        return modelAndView;
    }
    else{
        ModelAndView modelAndView = new ModelAndView("/error.404");
        return modelAndView;
        }
    }

    @PostMapping("/update-blog")
    public ModelAndView updateBlog(@ModelAttribute ("blog")Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("blog",blog);
        modelAndView.addObject("message","Updated succesfully");
        return modelAndView;
    }
}
