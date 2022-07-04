package com.example.proxydemo.filters;

import com.netflix.zuul.context.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class FilterUtils {

    public static final String CORRELATION_ID = "correlation-id";
    public static final String USER_ID        = "user-id";
    public static final String ORG_ID         = "org-id";
    public static final String IV_SAML_USER   = "iv-saml-user";
    public static final String SESSION_ID     = "SESSION-ID";



    public String getCorrelationId(){
        RequestContext ctx = RequestContext.getCurrentContext();

        if (ctx.getRequest().getHeader(CORRELATION_ID) !=null) {
            return ctx.getRequest().getHeader(CORRELATION_ID);
        }
        else{
            return  ctx.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    public  final String getOrgId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(ORG_ID) !=null) {
            return ctx.getRequest().getHeader(ORG_ID);
        }
        else{
            return  ctx.getZuulRequestHeaders().get(ORG_ID);
        }
    }

    public void setOrgId(String orgId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(ORG_ID,  orgId);
    }

    public void setIvSamlUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(IV_SAML_USER, authentication.getName());
    }

    public void setSessionId(){
        HttpSession session = RequestContext.getCurrentContext().getRequest().getSession();
        if (session != null) {
            RequestContext.getCurrentContext().addZuulRequestHeader(SESSION_ID, session.getId());
        }
    }

    public final String getUserId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(USER_ID) !=null) {
            return ctx.getRequest().getHeader(USER_ID);
        }
        else{
            return  ctx.getZuulRequestHeaders().get(USER_ID);
        }
    }

    public void setUserId(String userId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(USER_ID,  userId);
    }

    public String getServiceId(){
        RequestContext ctx = RequestContext.getCurrentContext();

        //We might not have a service id if we are using a static, non-eureka route.
        if (ctx.get("serviceId")==null) return "";
        return ctx.get("serviceId").toString();
    }


}