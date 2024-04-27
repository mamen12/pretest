package com.general.apisubsaccount.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.general.apisubsaccount.beans.Request;
import com.general.apisubsaccount.entity.PayloadProperties;
import com.general.apisubsaccount.repo.PayloadRepository;
import com.general.apisubsaccount.util.JwtService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(value = 2)
public class HttpLoggingFilter  extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(HttpLoggingFilter.class);

	@Autowired
	private JwtService jwtService; 
	
	@Autowired
	private PayloadRepository payloadRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		ContentCachingRequestWrapper rqWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper rsWrapper = new ContentCachingResponseWrapper(response);
		
		filterChain.doFilter(rqWrapper, rsWrapper);
		
		String authHeader = request.getHeader("Authorization"); 
        String token = null; 
        String username = null; 
        if (authHeader != null && authHeader.startsWith("Bearer ")) { 
            token = authHeader.substring(7); 
            username = jwtService.extractUsername(token); 
        } 

		String requestBody = getStringValue(rqWrapper.getContentAsByteArray(), request.getCharacterEncoding());
		String responseBody = getStringValue(rsWrapper.getContentAsByteArray(), request.getCharacterEncoding());
		logReqRes(requestBody, responseBody, rqWrapper.getRequestURI(), username, response.getStatus()); 
		rsWrapper.copyBodyToResponse();
	}

	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		try {
			return new String(contentAsByteArray, characterEncoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@Async
	private void logReqRes(String request, String response, String uri, String username, int statusCode) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<Request<?>> type = new TypeReference<Request<?>>() {};
		Request<?> res = mapper.reader().forType(type).readValue(request);
		PayloadProperties payload = new PayloadProperties().builder()
				.idPayload(UUID.randomUUID().toString())
				.idRequest(res.getRequestHeader().getRequestId())
				.payloadRequest(request)
				.payloadResponse(response)
				.username(StringUtils.isEmpty(username) ? "" : username)
				.url(uri)
				.timeCreated(new Date())
				.build();
		payloadRepo.save(payload);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String now = sdf.format(new Date());
		String log = String.format("%s - %s - %s response status: %d", now, uri, payload.getIdRequest(), statusCode);
		logger.info(log);
	}
    

    
}

