package com.didiglobal.logi.metrics.helper.convert;

/**
 * 
 * 
 * @author liujianhui
 * @version:2015年12月22日 下午7:16:07
 */
public final class ConvertiblePair {

    private final Class<?> sourceType;

    private final Class<?> targetType;

    /**
     * Create a new source-to-target pair.
     * @param sourceType the source type
     * @param targetType the target type
     */
    public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    public Class<?> getSourceType() {
        return this.sourceType;
    }

    public Class<?> getTargetType() {
        return this.targetType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != ConvertiblePair.class) {
            return false;
        }
        ConvertiblePair other = (ConvertiblePair) obj;
        return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);

    }

    @Override
    public int hashCode() {
        return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
    }
}