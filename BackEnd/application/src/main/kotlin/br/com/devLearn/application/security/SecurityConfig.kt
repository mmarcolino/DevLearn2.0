package br.com.devLearn.application.security

import br.com.devLearn.application.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
class SecurityConfig(private val service: UserService): WebSecurityConfigurerAdapter() {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(service).passwordEncoder(bCryptPasswordEncoder())
    }

    @Override
    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable().authorizeRequests()
            .antMatchers("/users/create", "users/create/**").permitAll().and().httpBasic()
        http!!.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}