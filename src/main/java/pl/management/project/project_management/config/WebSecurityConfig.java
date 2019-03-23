package pl.management.project.project_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/changePassword").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER", "ROLE_VIEWER")
                // tutaj są URL wymagające autoryzacji - strefa chroniona
                // .antMatchers(/url) -> wymaga autoryzacji
                // .hasAnyAuthority("uprawnienie") -> dla określonego uprawnienia
                .antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
                // pozostałe URL udostępnij dla każdego
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                // formularz logowania
                .formLogin()
                // adres formularza logowania
                .loginPage("/login")
                // nazwa pola w formularzu dot. loginu
                .usernameParameter("login")
                // nazwa pola w formularzu dot. hasła
                .passwordParameter("pass")
                // adres gdzie przekazywane są parametry formularza
                .loginProcessingUrl("/login-process")
                // domyśly URL po poprawnym zalogowaniu
                .defaultSuccessUrl("/")
                // domyśly URL po błędnym logowaniu
                .failureUrl("/errorLogin")
                .and()
                // wylogowanie
                .logout()
                // adres URL przekierowyjący na wylogowanie
                .logoutUrl("/logout")
                // domyśly URL po poprawnym wylogowaniu
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                // SQL for logging users by email and password
                .usersByUsernameQuery("SELECT u.email, u.password, u.active FROM user u " +
                        "WHERE u.email = ?")
                // SQL giving roles for logged user
                .authoritiesByUsernameQuery("SELECT u.email, r.role FROM user u " +
                        "JOIN user_role ur ON ur.user_id = u.id " +
                        "JOIN role r ON ur.role_id = r.role_id " +
                        "WHERE u.email = ?")
                // wynik logowania
                .dataSource(dataSource)
                // password encoder
                .passwordEncoder(bCryptPasswordEncoder);
    }
}


