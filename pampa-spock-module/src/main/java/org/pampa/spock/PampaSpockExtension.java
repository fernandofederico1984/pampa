package org.pampa.spock;

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension;
import org.spockframework.runtime.model.SpecInfo;

/**
 *
 */
public class PampaSpockExtension extends AbstractAnnotationDrivenExtension<Pampa>
{

    public PampaSpockExtension()
    {
    }

   public void visitSpecAnnotation(Pampa annotation, SpecInfo spec) {
    }

    @Override
    public void visitSpec(SpecInfo spec)
    {
       spec.addListener(new PampaSpockListener());

    }
}