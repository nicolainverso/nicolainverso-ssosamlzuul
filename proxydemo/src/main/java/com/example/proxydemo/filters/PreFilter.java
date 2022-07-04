package com.example.proxydemo.filters;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PreFilter extends ZuulFilter {

  Logger log = LoggerFactory.getLogger(PreFilter.class);
  @Autowired
  FilterUtils filterUtils;

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  private boolean isCorrelationIdPresent(){
    if (filterUtils.getCorrelationId() !=null){
      return true;
    }

    return false;
  }

  private String generateCorrelationId(){
    return java.util.UUID.randomUUID().toString();
  }

  @Override
  public Object run() {

    if (isCorrelationIdPresent()) {
      log.debug("correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
    }
    else{
      filterUtils.setCorrelationId(generateCorrelationId());
      log.debug("correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
    }

    filterUtils.setIvSamlUser();
    filterUtils.setSessionId();

    RequestContext ctx = RequestContext.getCurrentContext();
    log.debug("Processing incoming request for {}.",  ctx.getRequest().getRequestURI());
    return null;
  }
}