package no.fintlabs.controller;

public class EndPoints {

    public static final String COMPONENT = "${fint.consumer.domain}/${fint.consumer.package}";
    public static final String RESOURCE = "/{resource}";
    public static final String RESOURCE_LAST_UPDATED = "/{resource}/last-updated";
    public static final String RESOURCE_CACHE_SIZE = "/{resource}/cache/size";
    public static final String RESOURCE_STATUS_ID = "/{resource}/status/{idValue}";
    public static final String RESOURCE_ID = "/{resource}/{idField}/{idValue}";

}
