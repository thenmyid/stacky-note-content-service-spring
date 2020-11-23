package com.stackynote.content.controller;

import com.stackynote.content.model.stack.StackConstant;
import com.stackynote.content.model.stack.Stack;
import com.stackynote.content.model.stack.StackResponse;
import com.stackynote.content.repository.StackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StackController {

    @Autowired
    StackRepository stackRepository;

    @GetMapping("/stack")
    public ResponseEntity<StackResponse> getStack (
            @RequestParam(required = true) String action,
            @RequestParam(required = false) String code
    ) {
        StackResponse stackResponse = new StackResponse();
        Exception BAD_REQUEST = new Exception(HttpStatus.BAD_REQUEST.toString());
        try{
            List<Stack> stacks = new ArrayList<Stack>();

            switch(action) {
                case StackConstant.REST_ACTION_CATEGORY:
                    if (code == null) throw BAD_REQUEST;
                    stackRepository.findByCategoryCodesContaining(code).forEach(stacks::add);
                    stackResponse.setCategory_code(code);
                    break;
                case StackConstant.REST_ACTION_SUB_CATEGORY:
                    if (code == null) throw BAD_REQUEST;
                    stackRepository.findBySubCategoryCodesContaining(code).forEach(stacks::add);
                    stackResponse.setSub_category_code(code);
                    break;
                case StackConstant.REST_ACTION_CODE:
                    if (code == null) throw BAD_REQUEST;
                    stackRepository.findByStackCode(code).forEach(stacks::add);
                    stackResponse.setStack_code(code);
                    break;
                case StackConstant.REST_ACTION_SUMMARY:
                    stackRepository.findAll().forEach(stacks::add);
                    break;
                default:
                    throw BAD_REQUEST;
            }

            Integer statusCode = stacks.isEmpty() ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value();

            stackResponse.setStatus(statusCode);
            stackResponse.setStackList(stacks);

            return new ResponseEntity<>(stackResponse, HttpStatus.OK);

        } catch (Exception e) {
            if(e == BAD_REQUEST)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
