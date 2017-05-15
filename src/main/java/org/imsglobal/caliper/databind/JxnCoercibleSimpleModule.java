package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class CoercibleSimpleModule extends SimpleModule {
    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.addBeanSerializerModifier(new CoercibleSerializerModifier());
    }
}