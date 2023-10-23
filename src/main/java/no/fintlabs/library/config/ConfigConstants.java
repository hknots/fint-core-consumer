package no.fintlabs.library.config;

public class ConfigConstants {

    public static final String DOMAIN = "${fint.consumer.domain}";

    public static final String PACKAGE = "${fint.consumer.package}";

    public static final String ORG_ID = "${fint.consumer.org-id}";

    public static final String RESOURCE_PATH = DOMAIN + "/" + PACKAGE + "/{resource}";



}
