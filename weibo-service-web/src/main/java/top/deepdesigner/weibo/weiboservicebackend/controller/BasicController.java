/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.deepdesigner.weibo.weiboservicebackend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonRest/a>
 */
@RestController
@Api(value = "基础接口", tags = "基础")
public class BasicController {

    @ApiOperation(value = "hello")
    @GetMapping("/padding")
    public String queryWithVersion(
        @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
        @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return startDate.toString();
    }
}
