package com.chenxt.bootdemo.base.expection.handler;

import com.chenxt.bootdemo.base.expection.enumeration.BusinessExceptionCodeEnum;
import com.chenxt.bootdemo.base.expection.enumeration.ValidationExceptionCodeEnum;
import com.chenxt.bootdemo.base.expection.BaseException;
import com.chenxt.bootdemo.base.expection.BusinessException;
import com.chenxt.bootdemo.base.expection.ValidationException;
import com.chenxt.bootdemo.base.expection.i18n.I18nComponent;
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
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
@ConditionalOnBean(name = "serviceExceptionHandler")
public class UnifiedServiceExceptionHandler {
    /**
     * The constant MESSAGE_FORMAT.
     */
    public static final String MESSAGE_FORMAT = "%s|%s|%s|%s";

    @Resource
    private I18nComponent i18nComponent;

    /**
     * 参数异常
     *
     * @param e        the e
     * @param response the response
     */
    @ExceptionHandler(value = ValidationException.class)
    public void handleArgumentException(ValidationException e, HttpServletResponse response) {
        try {
            log.error(e.getMessage(), e);
            String message = String.format(MESSAGE_FORMAT, "VALIDATION", ValidationExceptionCodeEnum.VALID_ERROR.getCode(), getMessage(e), "");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 业务异常
     *
     * @param e        异常
     * @param response the response
     */
    @ExceptionHandler(value = BusinessException.class)
    public void handleBusinessException(BaseException e, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        try {
            log.error(e.getMessage(), e);
            String message = String.format(MESSAGE_FORMAT, "BUSINESS", e.getResponseEnum().getCode(), getMessage(e), "");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 其他自定义异常
     *
     * @param e        异常
     * @param response the response
     */
    @ExceptionHandler(value = BaseException.class)
    public void handleBaseException(BaseException e, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        log.error(e.getMessage(), e);
        try {
            log.error(e.getMessage(), e);
            String message = String.format(MESSAGE_FORMAT, "BASE", e.getResponseEnum().getCode(), getMessage(e), "");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 参数绑定异常
     *
     * @param e        异常
     * @param response the response
     */
    @ExceptionHandler(value = BindException.class)
    public void handleBindException(BindException e, HttpServletResponse response) {
        log.error("参数绑定校验异常", e);
        wrapperBindingResult(e.getBindingResult(), response);
    }

    /**
     * 参数校验 (Valid) 异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e        异常
     * @param response the response
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public void handleValidException(MethodArgumentNotValidException e, HttpServletResponse response) {
        log.error(" 参数绑定校验异常 ", e);
        wrapperBindingResult(e.getBindingResult(), response);
    }

    /**
     * 未定义异常
     *
     * @param e        异常
     * @param response the response
     */
    @ExceptionHandler(value = Exception.class)
    public void handleException(Exception e, HttpServletResponse response) {
        log.error(e.getMessage(), e);

        String message = String.format(MESSAGE_FORMAT, "UNKNOWN", BusinessExceptionCodeEnum.FAIL.getCode(), e.getMessage(), "");
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     */
    private void wrapperBindingResult(BindingResult bindingResult, HttpServletResponse response) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            msg.append(", ");
            msg.append(error.getDefaultMessage());
        }
        String message = String.format(MESSAGE_FORMAT, "VALIDATION", ValidationExceptionCodeEnum.VALID_ERROR.getCode(), msg.substring(2), "");
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
