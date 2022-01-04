package com.upgrad.bookmyconsultation.service;

import com.upgrad.bookmyconsultation.entity.Doctor;
import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.repository.DoctorRepository;
import com.upgrad.bookmyconsultation.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RatingsService {

    private final ApplicationEventPublisher publisher;

    private final RatingRepository ratingRepository;

    private final DoctorRepository doctorRepository;

    @Autowired
    public RatingsService(ApplicationEventPublisher publisher, RatingRepository ratingRepository, DoctorRepository doctorRepository) {
        this.publisher = publisher;
        this.ratingRepository = ratingRepository;
        this.doctorRepository = doctorRepository;
    }

    //create a method name submitRatings with void return type and parameter of type Rating
    //set a UUID for the rating
    //save the rating to the database
    //get the doctor id from the rating object
    //find that specific doctor with the using doctor id
    //modify the average rating for that specific doctor by including the new rating
    //save the doctor object to the database

    public void submitRatings(Rating rating) {
        ratingRepository.save(rating);

        Optional<Doctor> doctor = doctorRepository.findById(rating.getDoctorId());
        if (doctor.isPresent()) {
            doctor.get().setRating(rating.getRating().doubleValue());
            doctorRepository.save(doctor.get());
        }

    }


}
