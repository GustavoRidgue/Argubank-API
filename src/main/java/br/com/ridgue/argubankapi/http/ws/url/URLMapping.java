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

    public static final String ROOT_API_WS_FIND_ALL_ACCOUNT = "/account/all";
    public static final String ROOT_API_WS_FIND_ACCOUNT_BY_ID = "/account/{id}";
    public static final String ROOT_API_WS_FIND_ACCOUNT_BY_NUMBER_AND_DIGIT = "/account/{number}/{digit}";
    public static final String ROOT_API_WS_FIND_ACCOUNT_BY_CLIENT_ID = "/account/client/{clientId}";
    public static final String ROOT_API_WS_CREATE_ACCOUNT = "/account/create/{clientId}";
    public static final String ROOT_API_WS_UPDATE_ACCOUNT = "/account/update/{id}";
    public static final String ROOT_API_WS_DELETE_ACCOUNT = "/account/delete/{id}";

    public static final String ROOT_API_WS_ALL_CARD = "/card/all";
    public static final String ROOT_API_WS_FIND_ALL_CARD = "/account/{id}/card/all";
    public static final String ROOT_API_WS_FIND_CARD_BY_ID = "/account/{id}/card/{cardId}";
    public static final String ROOT_API_WS_FIND_CARD_BY_NUMBER = "/account/{id}/card/number/{number}";
    public static final String ROOT_API_WS_CREATE_CARD = "/account/{id}/card/create";
    public static final String ROOT_API_WS_UPDATE_CARD = "/account/{id}/card/update/{cardId}";
    public static final String ROOT_API_WS_DELETE_CARD = "/account/{id}/card/delete/{cardId}";

    public static final String ROOT_API_WS_FIND_ALL_CITY = "/city/all";
    public static final String ROOT_API_WS_FIND_CITY_BY_ID = "/city/{id}";
    public static final String ROOT_API_WS_FIND_CITY_BY_NAME = "/city/name/{name}";

    public static final String ROOT_API_WS_FIND_ALL_STATE = "/state/all";
    public static final String ROOT_API_WS_FIND_STATE_BY_ID = "/state/{id}";
    public static final String ROOT_API_WS_FIND_STATE_BY_NAME = "/state/name/{name}";
}
