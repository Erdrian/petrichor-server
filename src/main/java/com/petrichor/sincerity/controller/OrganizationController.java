package com.petrichor.sincerity.controller;

import com.petrichor.sincerity.api.CommonResult;
import com.petrichor.sincerity.util.OrganizationCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationController {
    @GetMapping("api/organizationValid")
    public CommonResult<Boolean> isOrganizationCodeUtilValid(@RequestParam String code) {
        return CommonResult.success(OrganizationCodeUtil.isValidSocialCreditCode(code));
    }
}
