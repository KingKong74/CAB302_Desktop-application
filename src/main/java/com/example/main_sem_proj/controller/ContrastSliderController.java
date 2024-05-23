package com.example.main_sem_proj.controller;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class ContrastSliderController {

    private Slider colourSlider;
    private Label sliderValue;

    public void setColourSlider(Slider slider) {
        this.colourSlider = slider;
    }

    public void setSliderValueLabel(Label label) {
        this.sliderValue = label;
    }

    public void initializeSlider() {
        if (colourSlider != null && sliderValue != null) {
            colourSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double roundedValue = Math.round(newValue.doubleValue() / 50) * 50;
                sliderValue.setText(String.format("%.0fK", roundedValue));
            });
        } else {
            System.err.println("An error occurred: ColourSlider or SliderValueLabel not initialized.");
        }
    }
}
