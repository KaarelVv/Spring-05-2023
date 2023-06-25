package com.kaarel.webshop.controller;

import com.kaarel.webshop.entity.Carousel;
import com.kaarel.webshop.entity.Category;
import com.kaarel.webshop.repository.CarouselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class CarouselController {
    @Autowired
    CarouselRepository carouselRepository;
        @GetMapping("carousel")
    public List<Carousel> getCarouselPictures(){
        return carouselRepository.findAll();
    }
    @PostMapping("carousel/add")
    public List<Carousel> addCarousel(@RequestBody Carousel carousel){
        carouselRepository.save(carousel);
        return carouselRepository.findAll();
    }
    @PutMapping("carousel/edit")
    public List<Carousel> editCarousel(@RequestBody Carousel carousel){
            if (carouselRepository.existsById(carousel.getId())){
                carouselRepository.save(carousel);
            }
            return carouselRepository.findAll();
    }
    @DeleteMapping("carousel/delete/{id}")
    public List<Carousel> deleteById(@PathVariable Long id){
            carouselRepository.deleteById(id);
            return carouselRepository.findAll();
    }
}
