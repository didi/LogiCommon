package com.didiglobal.logi.metrics.helper.convert;

public class ConverterNotFoundException extends RuntimeException {
    private final Class<?> sourceType;

    private final Class<?> targetType;

    /**
     * Creates a new conversion executor not found exception.
     * @param sourceType the source type requested to convert from
     * @param targetType the target type requested to convert to
     */
    public ConverterNotFoundException(Class<?> sourceType, Class<?> targetType) {
        super("No converter found capable of converting from type " + sourceType + " to type " + targetType);
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    /**
     * Returns the source type that was requested to convert from.
     * @return Class
     */
    public Class<?> getSourceType() {
        return this.sourceType;
    }

    /**
     * Returns the target type that was requested to convert to.
     * @return Class
     */
    public Class<?> getTargetType() {
        return this.targetType;
    }

}
