package org.leanin.connect.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Deliberately small request contract for creating a career win. */
public record CreateCareerWinRequest(
    @NotBlank(message = "Please share a little more.")
    @Size(max = 360, message = "A win can be up to 360 characters.")
    String message
) { }
