package ch.bittime.bittime.security.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * @author Pascal
 */


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

//    @Override
//    protected void configure (HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/registration").permitAll()
////                .antMatchers("/user/**").hasAnyAuthority("USER")
//                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
//                .authenticated().and().csrf().disable().formLogin()
//                .loginPage("/login").failureUrl("/login?error=true")
//                .defaultSuccessUrl("/admin/home")
//                .usernameParameter("user_name")
//                .passwordParameter("password")
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login").and().exceptionHandling()
//                .accessDeniedPage("/access-denied");
//    }

        @Override
        protected void configure (HttpSecurity http) throws Exception{

//        String[] staticResources = {
//                "/css/**",
//                "/images/**",
//                "/fonts/**",
//                "/scripts/**",
//        };
       http.authorizeRequests()

               .antMatchers("/").permitAll()
//               .antMatchers(staticResources).permitAll()
               .antMatchers("/default").permitAll()
               .antMatchers("/login").permitAll()
               .antMatchers("/admin/**").hasAuthority("ADMIN")//admin home is secured
               .antMatchers("/deleteUser").hasAuthority("ADMIN")
               .antMatchers("/user/**").hasAuthority("USER")
               .anyRequest().authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .usernameParameter("user_name")
               .passwordParameter("password")
               .defaultSuccessUrl("/default") //https://stackoverflow.com/questions/31524426/securityconfig-2-success-url-for-different-roles
               .permitAll()
               .and().logout()
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .logoutSuccessUrl("/login").permitAll();
    }




    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}