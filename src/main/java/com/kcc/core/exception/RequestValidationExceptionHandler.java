//package com.kcc.core.exception;
//
//import com.kcc.core.Result;
//import com.kcc.core.ResultCodeEnum;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.converter.HttpMessageConversionException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.List;
//
///**
// * 校验统一处理异常
// * 如果校验不通过，就在异常中处理，统一输出格式
// * created by chl on 2020/6/11
// **/
//@Slf4j
//@RestControllerAdvice
//public class RequestValidationExceptionHandler {
//    /**
//     * 校验错误拦截处理
//     *
//     * @param exception 错误信息集合
//     * @return 错误信息
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Result validationBodyException(MethodArgumentNotValidException exception) {
//        BindingResult result = exception.getBindingResult();
//        String message = "";
//        if (result.hasErrors()) {
//            List<ObjectError> errors = result.getAllErrors();
//            if (errors != null) {
//                errors.forEach(p -> {
//                    FieldError fieldError = (FieldError) p;
//                    log.error("Data check failure : object{" + fieldError.getObjectName() + "},field{" + fieldError.getField() +
//                            "},errorMessage{" + fieldError.getDefaultMessage() + "}");
//
//                });
//                if (errors.size() > 0) {
//                    FieldError fieldError = (FieldError) errors.get(0);
//                    message = fieldError.getDefaultMessage();
//                }
//            }
//        }
//        return Result.error(ResultCodeEnum.UNKNOWN_ERROR.getCode(), "请填写正确信息");
//        // return Message.fail("".equals(message) ? "请填写正确信息" : message);
//    }
//
//    /**
//     * 参数类型转换错误
//     *
//     * @param exception 错误
//     * @return 错误信息
//     */
//    @ExceptionHandler(HttpMessageConversionException.class)
//    public Result parameterTypeException(HttpMessageConversionException exception) {
//        log.error(exception.getCause().getLocalizedMessage());
//        return Result.error(ResultCodeEnum.UNKNOWN_ERROR.getCode(), "类型转换错误");
////        return Message.fail("类型转换错误");
//
//
//    }
//
////    @ExceptionHandler({Exception.class})
////    @ResponseBody
////    public Object exception(Exception e, HttpServletRequest request, HttpServletResponse response) {
////
////        //参数校验get
////        if (e instanceof ConstraintViolationException) {
////            StringBuffer sb = new StringBuffer();
////            ConstraintViolationException exs = (ConstraintViolationException) e;
////            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
////            for (ConstraintViolation<?> item : violations) {
////                sb.append(item.getMessage() + "/");
////            }
////            return new BaseRespBackEnd(101, "请求参数错误", sb.toString());
////        }
////
////        //参数校验post
////        if (e instanceof MethodArgumentNotValidException) {
////            MethodArgumentNotValidException exs = (MethodArgumentNotValidException) e;
////            BindingResult bindingResult = exs.getBindingResult();
////            StringBuffer sb = new StringBuffer();
////            for (FieldError fieldError : bindingResult.getFieldErrors()) {
////                sb.append(fieldError.getDefaultMessage() + "/");
////            }
////            return new BaseRespBackEnd(101, "请求参数错误", sb.toString());
////        }
////    }
//}
