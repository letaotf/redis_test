/**  
 * All rights Reserved, Designed By www.maihaoche.com
 * 
 * @Package com.mhc.evo.test.base
 * @author: 天河（tianhe@maihaoche.com）
 * @date: 2018-07-11 13:56:19
 * @Copyright: 2017-2020 www.maihaoche.com Inc. All rights reserved. 
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目
 */ 
package com.taofeng.redis;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**  
 * <p> evo模块测试基类 </p>
 *   
 * @author: 天河（tianhe@maihaoche.com）
 * @date: 2018-07-11 13:56:19
 * @since V1.0 
 */
@SpringBootTest(classes = com.taofeng.redis.RedisTestApplication.class)
@ActiveProfiles("local")
public abstract class BaseTest {

}
