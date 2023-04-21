/**
 * 
 */
package com.nagarro.orderservice.configuration;


import com.nagarro.orderservice.constant.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@SuppressWarnings("Deprecated")
public class WebConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf()
				.disable()
				.authorizeRequests()
				.requestMatchers(
						new AntPathRequestMatcher(Constant.INVENTORY_ITEMS, HttpMethod.POST.name()),
						new AntPathRequestMatcher(Constant.INVENTORY_ITEMS, HttpMethod.PUT.name()),
						new AntPathRequestMatcher(Constant.INVENTORY_ITEMS, HttpMethod.PATCH.name()),
						new AntPathRequestMatcher(Constant.INVENTORY_ITEMS, HttpMethod.DELETE.name())
				).access("hasHeader('X-ROLE') && request.getHeader('X-ROLE').equals('ROLE_ADMIN')")
				.antMatchers(HttpMethod.GET, Constant.INVENTORY_ITEMS)
				.permitAll()
				.antMatchers(HttpMethod.POST, "/inventory/orders").permitAll()
				.antMatchers(HttpMethod.GET,"/inventory/orders/{id}").permitAll()
				.antMatchers(HttpMethod.GET,"/inventory/orders/byEmail/{email}").permitAll()
				.antMatchers(HttpMethod.GET,"/inventory/orders/timeline").permitAll()
				.antMatchers(HttpMethod.GET,"/inventory/customers/{id}/orderHistory").permitAll()
				.and().addFilterBefore(headerFilter(), ChannelProcessingFilter.class);


	}

	private Filter headerFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
				if ((request.getMethod().equals(HttpMethod.GET.name())&&
						!(request.getRequestURI().equals("/inventory/orders")||
								request.getRequestURI().equals("/inventory/orders/byStatus/{status}")))||
						(request.getRequestURI().equals("/inventory/orders")&&request.getMethod().equals(HttpMethod.POST.name()))) {
					// allow GET request without header check
					System.out.println("URI"+request.getRequestURI());
					filterChain.doFilter(request, response);
				} else {
					String role = request.getHeader("X-ROLE");
					if (role != null && role.equals("ROLE_ADMIN")) {
						// allow request with ROLE_ADMIN header
						filterChain.doFilter(request, response);
					} else {
						response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
					}
				}
			}
		};
	}
}
