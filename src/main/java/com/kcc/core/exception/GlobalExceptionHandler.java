//package com.kcc.core.exception;
//
//import com.kcc.core.Result;
//import com.kcc.core.ResultFieldError;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//@Slf4j
//@RestControllerAdvice
////@ControllerAdvice
////public class MyExceptionHandlerAdvice {
//public class GlobalExceptionHandler {
//    /**
//     * 未找到接口，需要在 application.properties 文件做如下配置
//     * spring.mvc.throw-exception-if-no-handler-found=true
//     * spring.resources.add-mappings=false
//     *
//     * @param request
//     * @param response
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler({NoHandlerFoundException.class})
////    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "找不到页面")
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public Result errorHandler(HttpServletRequest request, HttpServletResponse response, NoHandlerFoundException ex) {
////        response.setStatus(HttpStatus.NOT_FOUND.value());
//
//        return Result.error(CustomExceptionTypeEnum.NOT_FOUND.getCode(), CustomExceptionTypeEnum.NOT_FOUND.getDesc());
//
////        return Result.error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
////        return Result.error(CustomExceptionTypeEnum.NOT_FOUND.getCode(), ex.getMessage());
//    }
//
//    /**
//     * 已知异常，可预知的异常可以单独处理，支持自定义异常
//     *
//     * @return
//     */
//    @ExceptionHandler({BusinessException.class})
//    public Result errorHandler(HttpServletRequest request, HttpServletResponse response, BusinessException ex) {
//        return Result.error(CustomExceptionTypeEnum.SERVER_ERROR.getCode(), ex.getMessage());
//    }
//
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public Result errorHandler(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException ex) {
//        Result result = new Result();
//        result.setMsg("MethodArgumentNotValidException");
//        return result;
//    }
//
//    //
//
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    public Result errorHandler(HttpServletRequest request, HttpServletResponse response, HttpMessageNotReadableException ex) {
//        Result result = new Result();
//        result.setCode(HttpStatus.BAD_REQUEST.value());
//        result.setMsg("RequestBody 数据转换出错啦！");
//        return result;
//    }
//
//    @ExceptionHandler({BindException.class})
//    public Result errorHandler(HttpServletRequest request, HttpServletResponse response, BindException ex) {
//        Result result = new Result();
//
////        HttpStatusWrapper.ILLEGAL_REQUEST_PARAMETERS
//        response.setStatus(HttpStatus.BAD_REQUEST.value());
//
//        result.setCode(HttpStatus.BAD_REQUEST.value());
////            String msg = ((BindException) ex).getAllErrors().get(0).getDefaultMessage();
//        result.setMsg("出错啦！");
//
//        List<ResultFieldError> resultErrors;
//
//        BindingResult bindingResult = ex.getBindingResult();
//
//        if (bindingResult.hasErrors()) {
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//            resultErrors = new ArrayList<>(fieldErrors.size());
//
//            for (FieldError fieldError : fieldErrors) {
//                ResultFieldError resultFieldError = new ResultFieldError();
//                resultFieldError.setField(fieldError.getField());
//                resultFieldError.setMessage(fieldError.getDefaultMessage());
//                resultErrors.add(resultFieldError);
////                resultErrors.add(resultFieldError);
//                //maps.put(error.getField(), error.getDefaultMessage());
//            }
//        } else {
//            resultErrors = Collections.EMPTY_LIST;
//        }
//
//
////        List<ObjectError> objectErrors = ((BindException) ex).getAllErrors();
////        objectErrors.forEach(p -> {
////            FieldError fieldError = (FieldError) p;
////            ResultFieldError resultError = new ResultFieldError();
////            resultError.setField(fieldError.getField());
////            resultError.setMessage(fieldError.getDefaultMessage());
//////            resultError.setMessage(fieldError.toString());
////
////            resultErrors.add(resultError);
////        });
//
//        if (resultErrors.size() > 0) {
//            result.setErrors(resultErrors);
//        }
//
////        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//
//        result.setTimestamp(new Date());
//
//        return result;
//    }
//
//    /**
//     * 对方法参数校验异常处理方法
//     */
////    @ExceptionHandler(BindException.class)
////    public ResponseEntity<Map<String, Object>> handlerNotValidException(BindException exception) {
////        log.debug("begin resolve argument exception");
////        BindingResult result = exception.getBindingResult();
////        Map<String, Object> maps;
////
////        if (result.hasErrors()) {
////            List<FieldError> fieldErrors = result.getFieldErrors();
////            maps = new HashMap<>(fieldErrors.size());
////            fieldErrors.forEach(error -> {
////                maps.put(error.getField(), error.getDefaultMessage());
////            });
////        } else {
////            maps = Collections.EMPTY_MAP;
////        }
////
////        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(maps);
////    }
//
//
//    /**
//     * 处理所有未知异常（不可预知的异常可以直接用 Exception 捕获）
//     *
//     * @param request
//     * @param response
//     * @param ex
//     * @return
//     * @throws Exception
//     */
//    @ExceptionHandler({Exception.class})
//    public Result errorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
//        ex.printStackTrace();
//
//        Result result = new Result();
//
//        // response.setContentType("application/json;charset=UTF-8");
//
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//        // 未找到接口，需要在 application.properties 文件做如下配置
//        // spring.mvc.throw-exception-if-no-handler-found=true
//        // spring.resources.add-mappings=false
//        if (ex instanceof NoHandlerFoundException) {
//            response.setStatus(HttpStatus.NOT_FOUND.value());
//            // HttpStatus 系统错误代码
//            result.setCode(HttpStatus.NOT_FOUND.value());
//            result.setMsg(ex.getMessage());
//
//            // 自定义错误代码
////            result.setCode(CustomExceptionTypeEnum.NOT_FOUND.getCode());
//        } else if (ex instanceof MethodArgumentNotValidException) {
//            String msg = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().get(0).getDefaultMessage();
//            result.setMsg(msg);
//        }
////        else if (ex instanceof BindException) {
////            response.setStatus(HttpStatus.BAD_REQUEST.value());
////            result.setCode(HttpStatus.BAD_REQUEST.value());
//////            String msg = ((BindException) ex).getAllErrors().get(0).getDefaultMessage();
////            result.setMsg("出错啦！");
////
////            List<ResultError> resultErrors = new ArrayList<>();
////
////            List<ObjectError> errors = ((BindException) ex).getAllErrors();
////            errors.forEach(p -> {
////                ResultError resultError = new ResultError();
////                resultError.setMessage(p.getDefaultMessage());
////                resultErrors.add(resultError);
////            });
////
////            if (errors.size() > 0) {
////                result.setErrors(resultErrors);
////            }
////        }
//        else {
//            // HttpStatus 系统错误代码
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            result.setMsg(ex.getMessage());
//
//            // 自定义错误代码
////            result.setCode(CustomExceptionTypeEnum.SERVER_ERROR.getCode());
//        }
//
//        return result;
//
//        //判断请求是否为Ajax请求
////        if (MyUtils.isAjax(request)) { //如果是的话，就直接返回错误信息
////            Result<String> result = new Result();
////            result.setCode(400);
////            result.setMsg(e.getMessage());
////            result.setData(null);
////            return result;
////
////            //return WebResponse.errorException(e.getMessage());
////        } else { //如果不是的话，就跳转到错误页面
////            ModelAndView modelAndView = new ModelAndView();
////            modelAndView.setViewName("errorAaa"); //这里需要在templates文件夹下新建一个error.html文件用作错误页面
////            return modelAndView;
////        }
//    }
//
//
////    /**
////     * 请求方式不支持
////     */
////    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
////    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
////    public R handleException(HttpRequestMethodNotSupportedException e) {
////        logger.error(e.getMessage(), e);
////        return R.error("不支持' " + e.getMethod() + "'请求");
////    }
////
////    /**
////     * 拦截未知的运行时异常
////     */
////    @ExceptionHandler(RuntimeException.class)
////    public R notFount(RuntimeException e) {
////        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
////            throw e;
////        }
////        logger.error("运行时异常:", e);
////        return R.error("运行时异常:" + e.getMessage());
////    }
////
////    /**
////     * 处理自定义异常
////     */
////    @ExceptionHandler(galenException.class)
////    public R handleWindException(galenException e) {
////        return R.error(e.getCode(), e.getMessage());
////    }
////
////    @ExceptionHandler(DuplicateKeyException.class)
////    public R handleDuplicateKeyException(DuplicateKeyException e) {
////        logger.error(e.getMessage(), e);
////        return R.error("数据库中已存在该记录");
////    }
////
////    @ExceptionHandler(Exception.class)
////    public R handleException(Exception e) throws Exception {
////        logger.error(e.getMessage(), e);
////        return R.error("服务器错误，请联系管理员");
////    }
////
////    /**
////     * 捕获并处理未授权异常
////     *
////     * @param e 授权异常
////     * @return 统一封装的结果类, 含有代码code和提示信息msg
////     */
////    @ExceptionHandler(UnauthorizedException.class)
////    public R handle401(UnauthorizedException e) {
////        return R.error(401, e.getMessage());
////    }
////
////    // 验证码错误
////    @ExceptionHandler(ValidateCodeException.class)
////    public R handleCaptcha(ValidateCodeException e) {
////        return R.error(e.getMessage());
////    }
//
////    @ExceptionHandler(Exception.class)
////    public Result handleException(Exception e) {
////        log.error(e.getMessage(), e);
////
////        if (e instanceof KevinException) {
////            return ResultUtil.error(e.getMessage());
////        } else if (e instanceof NoHandlerFoundException) {
////            return ResultUtil.error(ResultEnum.NO_HANDLER_FOUND_ERROR);
////        } else if (e instanceof IllegalArgumentException) {
////            return ResultUtil.error(e.getMessage());
////        } else if (e instanceof IllegalStateException) {
////            return ResultUtil.error(e.getMessage());
////        } else if (e instanceof BindException) {
////            BindException ex = (BindException) e;
////            List<ObjectError> allErrors = ex.getAllErrors();
////            ObjectError error = allErrors.get(0);
////            String defaultMessage = error.getDefaultMessage();
////            return ResultUtil.error(defaultMessage);
////        } else if (e instanceof MethodArgumentNotValidException) {
////            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
////            List<ObjectError> errors = ex.getBindingResult().getAllErrors();
////            String message = errors.get(0).getDefaultMessage();
////            return ResultUtil.error(message);
////        } else {
////            return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
////        }
////    }
//}
