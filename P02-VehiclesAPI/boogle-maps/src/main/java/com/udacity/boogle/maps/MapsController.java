package com.udacity.boogle.maps;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/maps")
public class MapsController {

    @GetMapping
    public Address get(@RequestParam Double lat, @RequestParam Double lon) {
        return MockAddressRepository.getRandom();
    }
}
