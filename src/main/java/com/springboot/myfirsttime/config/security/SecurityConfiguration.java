package com.springboot.myfirsttime.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration{
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .httpBasic().disable() // UI 사용 기본 값으로 가진 시큐리티 설정 비활성화

                .csrf().disable() // CSRF 보안 비활성화

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                /*
                 *       SessionCreationPolicy.ALWAYS      - 스프링시큐리티가 항상 세션을 생성
                 *       SessionCreationPolicy.IF_REQUIRED - 스프링시큐리티가 필요시 생성(기본)
                 *       SessionCreationPolicy.NEVER       - 스프링시큐리티가 생성하지않지만 기존에 존재하면 사용
                 *       SessionCreationPolicy.STATELESS   - 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음
                 */
            .and()
                .authorizeRequests()
                //authorizeRequests() - HttpServletRequest를 사용하겠다
                .antMatchers("/sign-api/sign-in","/sign-api/sign-up",
                        "/sign-api/exception").permitAll()
                .antMatchers(HttpMethod.GET, "/board-api/**").permitAll()
                .antMatchers("/board-api/**").hasRole("USER")
                .antMatchers("**exception**").permitAll()
                .anyRequest().hasRole("ADMIN")

                /*
                 * authorizeRequests() 애플리케이션에 들어오는 요청에 대한 사용 권한 체크
                 * antMatchers() antPattern을 통해 권한을 설정하는 역할
                 * permitAll()
                 * anyRequest().hasRole("ADMIN") - ADMIN 권한을 가진 사람에게만
                 */

            .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                // 권한 확인 중 예외 발생할 경우 CustomAccessDeniedHandler()로 예외 전달
            .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                // 인증 과정 중 예외 발생할 경우 CustomAuthenticationEntryPoint()로 예외 전달
            .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        // 필터 등록을 어느 필터 앞에 추가할 것인지 설정한다.
        // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 앞에 추가하겠다는 의미

        return httpSecurity.build();
        
    }

    /*
     * WebSecurity는 스프링 시큐리티 영향권 밖에 있어 인증, 인가가 적용 전에 동작하는 설정
     * 인증, 인가가 적용되지 않는 리소스 접근에만 사용된다.
     * ignoring 메소드를 사용해 인증과 인가를 무시하는 경로를 설정
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers(
                "/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html","/webjars/**", "sign-api/exception");
    }

    //패스워드 암호화 저장을 위한 빈
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
