package com.portal.portalforbusiness.controllers;

import com.google.gson.Gson;
import com.portal.portalforbusiness.dto.*;

import com.portal.portalforbusiness.dto.responses.FeedbackDto;
import com.portal.portalforbusiness.services.CommentService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.portal.portalforbusiness.utils.Utils.getBody;
import static com.portal.portalforbusiness.utils.Utils.sendParametersJsonResponse;

@Controller
public class CommentController {

    private final CommentService commentService = CommentService.getInstance();


    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        ProductDto productDto = gson.fromJson(request.getParameter("product"), ProductDto.class);
        commentService.getAllCommentsByProduct(productDto).
                ifPresent(comments -> {
                    try {
                        sendParametersJsonResponse(comments, response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String body = getBody(request);
        Gson gson = new Gson();
        FeedbackDto feedback = gson.fromJson(body, FeedbackDto.class);
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");

        List<MarkDto> marks = new ArrayList<>();
        for (Map.Entry<Integer, Integer> evaluation : feedback.getEvaluation().entrySet()) {
            marks.add(MarkDto.builder().
                    parameterId(evaluation.getKey()).
                    evaluate(evaluation.getValue()).
                    build());
        }

        CommentDto commentDto = CommentDto.builder().
                productId(feedback.getProductId()).
                user(userDto).
//                userId(user.getId()).
        comment(String.valueOf(feedback.getComment())).
                marks(marks).
                build();

        commentService.save(commentDto);
//        TODO add redirect to some another page (because after sending feedback comment and others are left)
    }
}
