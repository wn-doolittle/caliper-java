package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class JxnCoercibleSimpleModule extends SimpleModule {
    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.addBeanSerializerModifier(new JxnCoercibleSerializerModifier());
    }
}