package org.thiki.lark.foundation.assmbler;

import org.thiki.lark.foundation.resource.ErrorResource;
import org.thiki.lark.foundation.resource.RestResource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xubitao on 1/2/16.
 */
public abstract class LarkAssembler extends ResourceAssemblerSupport<Object, RestResource> {
    public LarkAssembler(Class<?> controllerClass, Class<RestResource> resourceType) {
        super(controllerClass, resourceType);
    }

    protected Object[] pathVariables;

    public abstract RestResource toRestResource(Object o, Object... pathVariables) throws Exception;

    protected List<ResourceSupport> buildResources(List domains, LarkAssembler larkAssembler, Object... pathVariables) {
        larkAssembler.pathVariables = pathVariables;
        List<ResourceSupport> providerResources = new ArrayList<ResourceSupport>();
        for (Object domain : domains) {
            ResourceSupport providerResource = larkAssembler.toResource(domain, pathVariables);
            providerResources.add(providerResource);
        }
        return providerResources;
    }

    public RestResource toResource(Object o) {
        return null;
    }

    public ResourceSupport toResource(Object domain, Object[] pathVariables) {
        try {
            this.pathVariables = pathVariables;
            return toRestResource(domain, pathVariables);
        } catch (Exception e) {
            return ErrorResource.error(e.getMessage());
        }
    }
}
