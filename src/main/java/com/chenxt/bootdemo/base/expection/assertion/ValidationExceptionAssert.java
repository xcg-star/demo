package com.chenxt.bootdemo.base.expection.assertion;


import com.chenxt.bootdemo.base.expection.BaseException;
import com.chenxt.bootdemo.base.expection.ValidationException;
import com.chenxt.bootdemo.base.expection.core.Assert;
import com.chenxt.bootdemo.base.expection.core.IResponseEnum;

import java.text.MessageFormat;

/**
 * The interface Argument exception assert.
 *
 * @author chenxt
 * @date 2020/07/09
 */
public interface ValidationExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new ValidationException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new ValidationException(this, args, msg, t);
    }

}