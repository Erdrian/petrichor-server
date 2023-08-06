package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.api.ResultCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomErrorController implements ErrorController {
    @RequestMapping("${server.error.path:${error.path:/error}}")
    public CommonResult<String> notFound() {
        return CommonResult.failed(ResultCode.VALIDATE_FAILED);
    }
}
