package com.crm.service.impl;
import com.crm.common.exception.ServerException;
import com.crm.security.cache.TokenStoreCache;
import com.crm.security.user.ManagerDetail;
import com.crm.security.utils.TokenUtils;
import com.crm.service.AuthService;
import com.crm.vo.SysAccountLoginVO;
import com.crm.vo.SysTokenVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 认证服务实现
 *
 * @Author crm
 * @Date 2023-05-18 17:31
 */
@Service
@AllArgsConstructor
@Slf4j // 新增日志注解，用于输出调试信息
public class AuthServiceImpl implements AuthService {

    private final TokenStoreCache tokenStoreCache;
    private final AuthenticationManager authenticationManager;
    // 新增：注入密码编码器（需在配置类中定义Bean）
    private final PasswordEncoder passwordEncoder;

    @Override
    public SysTokenVO loginByAccount(SysAccountLoginVO params) {
        log.info("开始处理账号【{}】的登录请求", params.getAccount());
        Authentication authentication;
        try {
            // 用户认证（Spring Security自动调用UserDetailsService查询用户并校验密码）
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(params.getAccount(), params.getPassword()));
            log.info("账号【{}】认证成功", params.getAccount());
        } catch (DisabledException e) {
            log.error("账号【{}】被禁用", params.getAccount(), e);
            throw new ServerException("该账号已被禁用");
        } catch (BadCredentialsException e) {
            log.error("账号【{}】密码错误", params.getAccount(), e);
            throw new ServerException("用户名或密码错误");
        } catch (Exception e) {
            log.error("账号【{}】登录异常", params.getAccount(), e);
            throw new ServerException("登录失败，请联系管理员");
        }

        // 用户信息
        ManagerDetail user = (ManagerDetail) authentication.getPrincipal();
        log.info("获取到用户信息，用户名：{}，用户ID：{}", user.getUsername(), user.getId());

        // 生成 accessToken
        String accessToken = TokenUtils.generator();
        log.info("生成登录Token：{}", accessToken);

        try {
            // 保存用户信息到缓存
            tokenStoreCache.saveUser(accessToken, user);
            log.info("用户信息缓存成功，Token：{}", accessToken);
        } catch (Exception e) {
            log.error("用户信息缓存失败，Token：{}", accessToken, e);
            throw new ServerException("登录失败，缓存服务异常");
        }

        return new SysTokenVO(accessToken);
    }

    @Override
    public void logout(String accessToken) {
        log.info("开始处理登出，Token：{}", accessToken);
        try {
            // 用户信息
            ManagerDetail manager = tokenStoreCache.getUser(accessToken);
            if (manager != null) {
                log.info("删除用户【{}】的缓存信息", manager.getUsername());
                // 删除用户信息
                tokenStoreCache.deleteUser(accessToken);
            } else {
                log.warn("Token【{}】对应的用户信息不存在，直接跳过删除", accessToken);
            }
        } catch (Exception e) {
            log.error("登出异常，Token：{}", accessToken, e);
            throw new ServerException("登出失败，请联系管理员");
        }
    }
}