package com.chenxt.bootdemo.base.expection.handler;

import com.chenxt.bootdemo.base.enumeration.BusinessExceptionCodeEnum;
import com.chenxt.bootdemo.base.enumeration.ValidationExceptionCodeEnum;
import com.chenxt.bootdemo.base.expection.BaseException;
import com.chenxt.bootdemo.base.expection.BusinessException;
import com.chenxt.bootdemo.base.expection.ValidationException;
import com.chenxt.bootdemo.base.expection.i18n.I18nComponent;
import com.chenxt.bootdemo.base.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;


/**
 * 统一异常处理器
 *
 * @author chenxt
 * @date 2020/07/09
 */
@Slf4j
@Component
@RestControllerAdvice
@ConditionalOnWebApplication
@ConditionalOnBean(name = "apiExceptionHandler")
public class UnifiedApiExceptionHandler {

    @Resource
    private I18nComponent i18nComponent;

    /**
     * 参数异常
     *
     * @param e the e
     * @return the error response
     */
    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    public ResponseVO handleArgumentException(ValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseVO(e.getResponseEnum().getCode(), getMessage(e), null);
    }

    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果 error response
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseVO handleBusinessException(BaseException e) {
        log.error(e.getMessage(), e);
        return new ResponseVO(e.getResponseEnum().getCode(), getMessage(e), null);
    }

    /**
     * 其他自定义异常
     *
     * @param e 异常
     * @return 异常结果 error response
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ResponseVO handleBaseException(BaseException e) {
        log.error(e.getMessage(), e);
        return new ResponseVO(e.getResponseEnum().getCode(), getMessage(e), null);
    }

    /**
     * 参数绑定异常
     *
     * @param e 异常
     * @return 异常结果 error response
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseVO handleBindException(BindException e) {
        log.error("参数绑定校验异常", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验 (Valid) 异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果 error response
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVO handleValidException(MethodArgumentNotValidException e) {
        log.error(" 参数绑定校验异常 ", e);
        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果 error response
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseVO(BusinessExceptionCodeEnum.FAIL.getCode(), e.getMessage(), null);
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private ResponseVO wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            msg.append(error.getDefaultMessage());
        }
        return new ResponseVO(ValidationExceptionCodeEnum.VALID_ERROR.getCode(), msg.substring(2), null);
    }

    /**
     * 获取国际化消息
     *
     * @param e 异常
     * @return message message
     */
    public String getMessage(BaseException e) {
        String code = e.getResponseEnum().toString();
        String message = i18nComponent.getLocaleMessage(e.getResponseEnum().getMessage(), code, e.getArgs());
        // 如果还是没有定义，读取Runtime message
        if (StringUtils.isEmpty(message)) {
            return e.getMessage();
        }
        return message;
    }
}
