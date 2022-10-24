package com.CarRentalAgency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/v1")

public class DocsController {

    /** this method will render the view to the user,
     * so he can read the documentation for better usage experience.
     * */

    @RequestMapping("/docs")
    public String getCarDocumentation() {
        return "docs";
    }

}
