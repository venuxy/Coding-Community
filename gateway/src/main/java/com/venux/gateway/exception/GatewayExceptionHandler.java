package com.venux.gateway.exception;

import cn.dev33.satoken.exception.SaTokenException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venux.gateway.entity.Result;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关全局异常处理
 */
@Component // 将该类声明为 Spring 的组件，以便在应用程序上下文中注册
public class GatewayExceptionHandler implements ErrorWebExceptionHandler { // 实现 ErrorWebExceptionHandler 接口来处理 WebFlux 中的全局异常

    private ObjectMapper objectMapper = new ObjectMapper(); // 创建 ObjectMapper 实例，用于将 Java 对象序列化为 JSON 格式

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) { // 重写 handle 方法，处理传入的异常
        ServerHttpRequest request = serverWebExchange.getRequest(); // 获取当前请求对象
        ServerHttpResponse response = serverWebExchange.getResponse(); // 获取当前响应对象
        Integer code = 200; // 默认状态码设置为 200
        String message = ""; // 默认消息为空字符串

        // 判断异常类型
        if (throwable instanceof SaTokenException) { // 如果异常是 SaTokenException（用户认证相关异常）
            code = 401; // 将状态码设为 401，表示未授权
            message = "用户无权限"; // 设置错误消息
            throwable.printStackTrace(); // 打印堆栈跟踪，便于调试
        } else { // 对于其他类型的异常
            code = 500; // 状态码设为 500，表示服务器内部错误
            message = "系统繁忙"; // 设置错误消息
            throwable.printStackTrace(); // 打印堆栈跟踪
        }

        Result result = Result.fail(code, message); // 使用自定义的 Result 类封装错误响应信息
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON); // 设置响应头，指定内容类型为 JSON

        return response.writeWith(Mono.fromSupplier(() -> { // 异步写入响应
            DataBufferFactory dataBufferFactory = response.bufferFactory(); // 获取响应的 DataBuffer 工厂，用于生成数据缓冲区
            byte[] bytes = null; // 声明字节数组
            try {
                bytes = objectMapper.writeValueAsBytes(result); // 将 Result 对象序列化为 JSON 字节数组
            } catch (JsonProcessingException e) { // 捕获 JSON 处理异常
                e.printStackTrace(); // 打印异常信息
            }
            return dataBufferFactory.wrap(bytes); // 将字节数组包装成 DataBuffer，并返回用于写入响应
        }));
    }

}
