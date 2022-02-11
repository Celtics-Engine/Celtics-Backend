package com.celticsengine.assetstore.s3;

import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.http.SdkHttpFullRequest;
import software.amazon.awssdk.http.SdkHttpRequest;
import software.amazon.awssdk.services.s3.internal.endpoints.S3EndpointResolverContext;
import software.amazon.awssdk.services.s3.internal.endpoints.S3EndpointResolverFactory;
import software.amazon.awssdk.services.s3.internal.endpoints.S3EndpointResolverFactoryContext;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class S3Utilities {
    /**
     * Returns the URL for an object stored in Amazon S3.
     *
     * If the object identified by the given bucket and key has public read permissions,
     * then this URL can be directly accessed to retrieve the object's data.
     *
     * <p>
     *     If same configuration options are set on both #GetUrlRequest and #S3Utilities objects (for example: region),
     *     the configuration set on the #GetUrlRequest takes precedence.
     * </p>
     *
     * @param getUrlRequest request to construct url
     * @return A URL for an object stored in Amazon S3.
     * @throws SdkException Generated Url is malformed
     */
/*    public URL getUrl(GetUrlRequest getUrlRequest) {
        Region resolvedRegion = resolveRegionForGetUrl(getUrlRequest);
        URI resolvedEndpoint = resolveEndpoint(getUrlRequest.endpoint(), resolvedRegion);

        SdkHttpFullRequest marshalledRequest = createMarshalledRequest(getUrlRequest, resolvedEndpoint);

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(getUrlRequest.bucket())
                .key(getUrlRequest.key())
                .versionId(getUrlRequest.versionId())
                .build();

        S3EndpointResolverContext resolverContext = S3EndpointResolverContext.builder()
                .request(marshalledRequest)
                .originalRequest(getObjectRequest)
                .region(resolvedRegion)
                .endpointOverride(getUrlRequest.endpoint())
                .serviceConfiguration(s3Configuration)
                .fipsEnabled(fipsEnabled)
                .build();

        S3EndpointResolverFactoryContext resolverFactoryContext = S3EndpointResolverFactoryContext.builder()
                .bucketName(getObjectRequest.bucket())
                .originalRequest(getObjectRequest)
                .build();

        SdkHttpRequest httpRequest = S3EndpointResolverFactory.getEndpointResolver(resolverFactoryContext)
                .applyEndpointConfiguration(resolverContext)
                .sdkHttpRequest();

        try {
            return httpRequest.getUri().toURL();
        } catch (MalformedURLException exception) {
            throw SdkException.create("Generated URI is malformed: " + httpRequest.getUri(),
                    exception);
        }
    }*/
}
