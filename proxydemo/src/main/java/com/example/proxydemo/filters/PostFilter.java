package com.example.proxydemo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class PostFilter extends ZuulFilter {

  Logger log = LoggerFactory.getLogger(PostFilter.class);


  @Autowired
  FilterUtils filterUtils;

  @Override
  public String filterType() {
    return "post";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();

    log.debug("Adding the correlation id to the outbound headers. {}", filterUtils.getCorrelationId());
    ctx.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());

    log.debug("Completing outgoing request for {}.", ctx.getRequest().getRequestURI());


    return null;
  }

}