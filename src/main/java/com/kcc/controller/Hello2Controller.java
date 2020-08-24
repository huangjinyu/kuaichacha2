package com.kcc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.kcc.pojo.vo.TestRequestVO;
import com.kcc.pojo.vo.UserVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/hello2")
public class Hello2Controller {
    //    @CrossOrigin(origins = "http://localhost:8613", methods = {RequestMethod.GET, RequestMethod.PUT})
    @CrossOrigin
    @PutMapping("/testcors")
    public String TestCors() {
        return "testcors";
    }

    //    @CrossOrigin(origins = {"http://localhost:8613"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}, allowedHeaders = "*")
//    @CrossOrigin(origins = "http://localhost:8613")
    @RequestMapping("/testcors2")
    public String TestCors2() {
        return "testcors2";
    }

    @GetMapping("/test1")
    public TestRequestVO Test1(@Validated(TestRequestVO.ICreate.class) TestRequestVO testRequestVO) {
        System.out.println("/hello2/test1");
//        StringUtils.isBlank
        return testRequestVO;
    }

    @GetMapping("/test2")
    public TestRequestVO Test2(@Validated(TestRequestVO.IUpdate.class) TestRequestVO testRequestVO) {
        System.out.println("/hello2/test2");
        return testRequestVO;
    }

    @GetMapping("/test3")
    public TestRequestVO Test3(@Validated(TestRequestVO.ICreate.class) @RequestBody TestRequestVO testRequestVO) {
        System.out.println("/hello2/test3");
        return testRequestVO;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @JsonView(UserVO.UserSimpleView.class)
    public List<UserVO> query(@PageableDefault(page = 1, size = 10, sort = "username,asc", direction = Sort.Direction.DESC) Pageable pageable) {
//    public List<UserVO> query(UserQueryCondition userQueryCondition, @PageableDefault(page = 1, size = 10, sort = "username,asc") Pageable pageable) {
        System.out.println("pageNumber = " + pageable.getPageNumber());
        System.out.println("pageSize = " + pageable.getPageSize());
        System.out.println("sort = " + pageable.getSort());

//        System.out.println(userQueryCondition.toString());
        List<UserVO> users = new ArrayList<>();
        users.add(new UserVO());
        users.add(new UserVO());
        users.add(new UserVO());
        return users;
    }

    @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
    @JsonView(UserVO.UserDetailView.class)
    public UserVO getInfo(@PathVariable String id) {
        UserVO userVO = new UserVO();
        userVO.setUsername("knyel");
        System.out.println(userVO);
        return userVO;
    }

//    @RequestMapping(value = "", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity post(@Valid @RequestBody Task task, Errors errors) {
//        if (errors.hasErrors()) {
//            return new ResponseEntity(new ApiErrors(errors), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity(task.save(), HttpStatus.CREATED);
//    }
}