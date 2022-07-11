package br.com.ridgue.argubankapi.http.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse {
    protected String status = "SUCCESS";
    private Integer totalPages = null;
    private Long totalElements = null;
    private List<String> messages = new ArrayList<>();

    public DefaultResponse(String status, String message){
        this.status = status;
        this.messages = Collections.singletonList(message);
    }

    public DefaultResponse(Integer totalPages, Long totalElements) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public void setMessage(String message){
        messages = Collections.singletonList(message);
    }

    public void add(String message) {
        messages.add(message);
    }
}
