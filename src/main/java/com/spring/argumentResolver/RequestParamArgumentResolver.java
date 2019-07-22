package com.spring.argumentResolver;

import com.spring.annotation.CustomRequestParam;
import com.spring.annotation.CustomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Description TODO
 * @Author yk
 * @Date 2019/7/22 20:58
 */
@CustomService("requestParamArgumentResolver")
public class RequestParamArgumentResolver implements ArgumentResolver {

    @Override
    public boolean support(Class<?> type, int paramIndex, Method method) {
        // type = class java.lang.String
        // @CustomRequestParam("name")String name
        //获取当前方法的参数
        Annotation[][] an = method.getParameterAnnotations();
        Annotation[] paramAns = an[paramIndex];

        for (Annotation paramAn : paramAns) {
            //判断传进的paramAn.getClass()是不是 CustomRequestParam 类型
            if (CustomRequestParam.class.isAssignableFrom(paramAn.getClass())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object argumentResolver(HttpServletRequest request,
                                   HttpServletResponse response, Class<?> type, int paramIndex,
                                   Method method) {

        //获取当前方法的参数
        Annotation[][] an = method.getParameterAnnotations();
        Annotation[] paramAns = an[paramIndex];

        for (Annotation paramAn : paramAns) {
            //判断传进的paramAn.getClass()是不是 CustomRequestParam 类型
            if (CustomRequestParam.class.isAssignableFrom(paramAn.getClass())) {
                CustomRequestParam cr = (CustomRequestParam) paramAn;
                String value = cr.value();

                return request.getParameter(value);
            }
        }
        return null;
    }

}
