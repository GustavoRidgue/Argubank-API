package br.com.ridgue.argubankapi.http.ws.url;

public final class URLMapping {
    private URLMapping() {}

    public static final String ROOT_API_PATH = "/api";

    public static final String ROOT_API_WS_FIND_ALL_CLIENT = "/client/all";
    public static final String ROOT_API_WS_FIND_CLIENT_BY_ID = "/client/{id}";
    public static final String ROOT_API_WS_FIND_CLIENT_BY_NAME = "/client/name/{name}";
    public static final String ROOT_API_WS_CREATE_CLIENT = "/client/create";
    public static final String ROOT_API_WS_UPDATE_CLIENT = "/client/update/{id}";
    public static final String ROOT_API_WS_DELETE_CLIENT = "/client/delete/{id}";

    public static final String ROOT_API_WS_FIND_ALL_CITY = "/city/all";
    public static final String ROOT_API_WS_FIND_CITY_BY_ID = "/city/{id}";
    public static final String ROOT_API_WS_FIND_CITY_BY_NAME = "/city/name/{name}";

    public static final String ROOT_API_WS_FIND_ALL_STATE = "/state/all";
    public static final String ROOT_API_WS_FIND_STATE_BY_ID = "/state/{id}";
    public static final String ROOT_API_WS_FIND_STATE_BY_NAME = "/state/name/{name}";
}
